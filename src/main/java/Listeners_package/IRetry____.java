package Listeners_package;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//retry the failed test method a specified number of times
public class IRetry____ implements IRetryAnalyzer
{
	private int count=0; // Initial count
	private final int maxLimit=2; // Max limit

	@Override
	public boolean retry(ITestResult result)
	{
		if(count<maxLimit)
		{
			count++;
			return true;
		}
	return false;
	}

}

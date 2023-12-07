package Listeners_package;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Baseclass.BaseClass;
import Report.ExtentReport_______;

public class Listeners___ extends BaseClass implements ITestListener {

//experimental
	public static String apiResponse;
	public static String apiResponse1;


	// Static method
	ExtentReports extentReport = ExtentReport_______.extentReport();
	ExtentTest extentTest;
	// Useful in scenarios where you have parallel test execution
	ThreadLocal<ExtentTest> local = new ThreadLocal<>();

	// Useful for logging or reporting the start of a test.
	@Override
	public void onTestStart(ITestResult result) {
		String description = result.getMethod().getDescription();
		extentTest = extentReport.createTest(description != null ? description : result.getMethod().getMethodName());
		local.set(extentTest);
	}

	// Useful for logging or reporting successful test executions
	@Override
	public void onTestSuccess(ITestResult result) {
		local.get().log(Status.PASS, result.getMethod().getMethodName());

//experimental
		local.get().log(Status.INFO,"<br>"+ apiResponse1);
		local.get().log(Status.INFO,"testScriptOutput"+"<br>"+ apiResponse);
	}

	// Useful for logging or performing specific actions on test failure
	@Override
	public void onTestFailure(ITestResult result) {
		local.get().log(Status.FAIL, result.getMethod().getMethodName());
		Throwable exception = result.getThrowable();
		if (exception != null) {
			local.get().fail(exception);
		}
	}

	// Useful for cleanup tasks, generating final reports
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}


	// To display API responses in an Extent Report
	public static void info(String output) {
		apiResponse=output;
	}

	// To display API responses in an Extent Report
	public static void info_custom(String output1) {
		apiResponse1 = output1;
	}


}
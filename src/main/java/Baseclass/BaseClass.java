package Baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;

	@Test
	public WebDriver getWebAddress() throws InterruptedException, IOException {
		FileInputStream inputStream = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\Utility\\global.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String loginURL = properties.getProperty("URL_2");


		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-notifications");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(""+loginURL+"");
		return driver;
	}


//	public String screenShot(String testcase,WebDriver driver) throws IOException
//	{
//	TakesScreenshot screenshot=(TakesScreenshot)driver;
//	File src = screenshot.getScreenshotAs(OutputType.FILE);
//	String path=System.getProperty("user.dir")+"//Screenshots//"+testcase+".png";
//	File dst=new File(path);
//	FileUtils.copyFile(src, dst);
//	return path;
//	}


}

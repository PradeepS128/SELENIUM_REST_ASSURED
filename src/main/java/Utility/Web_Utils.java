package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.path.json.JsonPath;

public class Web_Utils {
	private static final String PREFIX = "QAA-";
	public static String email;
	public WebDriver driver;

	public Web_Utils(WebDriver driver) {
		this.driver = driver;
	}


	public static String propertyFile(String url) throws IOException {

		FileInputStream inputStream = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\Utility\\global.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String baseURI = properties.getProperty(url);
		return baseURI;
	}


	// parsing
	public static String jsonPath(String response, String name) {
		JsonPath jsonPath = new JsonPath(response);
		String output = jsonPath.getString(name);
		return output;	}

	// An unique email generator
	public static String mailGen() {
		Random random = new Random();
		int range = random.nextInt(900) + 100;
		return email = PREFIX + range;
	}

	// Java script executor
	public void javascriptExecutor(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	// visibility of By element
	public void byElementToAppear(By by) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	// Visibility of WebElement
	public void webElementToAppearBy(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20000));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}

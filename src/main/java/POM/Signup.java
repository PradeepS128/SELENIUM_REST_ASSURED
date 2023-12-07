package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utility.Web_Utils;

public class Signup extends Web_Utils {

	public WebDriver driver; // global variable
	public String emailID;
	public String subjectName;

	// Webdriver initialization
	public Signup(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	// Address of the element stored in a variable
	private By getMyFirstInbox = By.xpath("//button[.='Get my first inbox!']");
	private By userName = By.xpath("//input[@id=\"username\"]");
	private By addInbox = By.xpath("//button[.='Add Inbox']");
	private By otpMail = By.xpath("//span[.='noreply@quantumkn...']");
	private By otp = By.xpath("//td[@align=\"center\"]/h1");

	public void getMyFirstInbox() {
		byElementToAppear(getMyFirstInbox);
		driver.findElement(getMyFirstInbox).click();
	}

	public void userName() {
		email = Web_Utils.mailGen();
		byElementToAppear(userName);
		driver.findElement(userName).sendKeys("" + email + "");
	}

	public void addInbox() {
		byElementToAppear(addInbox);
		driver.findElement(addInbox).click();
	}

	public String extractingTheMail() {
		emailID = email + "@blondmail.com";
		return emailID;
	}

	public String otpMail() {
		byElementToAppear(otpMail);
		WebElement verify = driver.findElement(otpMail);
		subjectName = verify.getText();
		if (subjectName.equals("noreply@quantumkn...")) {
			javascriptExecutor(verify);
			verify.click();
		}
		return driver.findElement(otp).getText();
	}

}
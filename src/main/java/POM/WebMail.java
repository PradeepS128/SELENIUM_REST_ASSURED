package POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utility.Web_Utils;
//https://tempmailo.com/
public class WebMail extends Web_Utils
{
	public WebDriver driver;
	public List<WebElement> subjects;
	public String eMail;
	public String OTP;

public WebMail(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

// Address of the element stored in a variable
private By changeButton=By.xpath("//span[.='Change']");
private By okButton=By.cssSelector(".pure-button-primary");
private By mailSub=By.cssSelector(".mail-item-sub");
private By randomEMail=By.xpath("//input[@class=\"vs-input\"]");
private By extractOTP=By.xpath("//td[@align=\"center\"]/h1");
private By refreshButton=By.xpath("//span[.='Refresh']");

public void changeButton() {
	driver.findElement(changeButton).click();  }

public void okButton() {
	driver.findElement(okButton).click();	}

public String randomEMail() {
	byElementToAppear(randomEMail);
//	eMail=(String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerText;", randomEMail);
	eMail=driver.findElement(randomEMail).getText();
	return eMail;
}

public void mailSub(String input) {
	byElementToAppear(mailSub);
	subjects = driver.findElements(mailSub);
	for(WebElement subject:subjects) {
		webElementToAppearBy(subject);
		String name=subject.getText();
		if(name.equals(input)) {
			subject.click();
		}}}

public String extractOTP() throws InterruptedException {
	javascriptExecutor(driver.findElement(extractOTP));
	OTP=driver.findElement(extractOTP).getText();
	Thread.sleep(4000);
	return OTP;
}

public void refreshButton() {
	driver.findElement(refreshButton).click();
}
}

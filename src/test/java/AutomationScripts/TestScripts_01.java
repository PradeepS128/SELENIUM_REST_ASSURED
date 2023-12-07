package AutomationScripts;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Baseclass.BaseClass;
import Listeners_package.Listeners___;
import POM.Signup;
import Utility.Web_Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestScripts_01 extends BaseClass {
		//Global variables
		public Set<Cookie> cookies;
		public String email;
		public String sessionToken;
		public WebDriver driver;
		public Signup signup;
		public String otpText;
		public SoftAssert softAssert;

		@Test(description = "Extracting Email to acquire 30-day Trial License Acquisition", priority = 1,retryAnalyzer = Listeners_package.IRetry____.class)
		public void extracting_Email() throws InterruptedException, IOException {

			driver = getWebAddress();
			signup = new Signup(driver);
			signup.getMyFirstInbox();
			signup.userName();
			signup.addInbox();
			email = signup.extractingTheMail();
			String emailText="A new email has been generated: "+email+"";

			Listeners___.info(emailText); // response will be added to the extent report
			cookies = driver.manage().getCookies(); // retrieve all cookies for the current browsing session
		}

		@Test(description = "authentication API", priority = 2)
		public void valid_Credentials() throws IOException {
			RestAssured.baseURI = Web_Utils.propertyFile("RestAssured.baseURI") + "/AUTHENTICATE?";

			// request
			RequestSpecification request = given().header("Content-Type", "application/json").
					queryParam("CMD", "doMinimalSignupCLI").
					queryParam("email", email).
					queryParam("fname", "ISAGI").
					queryParam("lname", "YOICHI").
					queryParam("distributorID", "QKI-CLR").
					queryParam("textPW", "test1234");

			// response
			Response response = request.post();
			String responseBody = response.getBody().prettyPrint().toString();

			Listeners___.info(responseBody); // response will be added to the extent report

			// parsing with jsonPath
			String fname = Web_Utils.jsonPath(responseBody, "fname");
			String lname = Web_Utils.jsonPath(responseBody, "lname");
			String email = Web_Utils.jsonPath(responseBody, "email");
			sessionToken = Web_Utils.jsonPath(responseBody, "sessionToken");
			System.out.println(sessionToken);

			// validation
			softAssert = new SoftAssert();
			softAssert.assertEquals(fname, "ISAGI");
			softAssert.assertEquals(lname, "YOICHI");
			softAssert.assertEquals(email, "" + email + "");
			int code = response.statusCode();
			softAssert.assertEquals(code, 200);
		}

		@Test(description = "Extracting OTP during 30-day Trial License Acquisition", priority = 3)
		public void extracting_OTP() throws InterruptedException {
			// allows you to set cookies for the current domain
			for (Cookie cookie : cookies) {
				driver.manage().addCookie(cookie);
			}
			//OTP has been extracted and stored in a variable
			otpText=signup.otpMail();
			String otp="Received OTP: "+otpText+"";
			Listeners___.info(otp); // response will be added to the extent report
			driver.close();
		}



		@Test(description = "Registration with existing email", priority = 4)
		public void existing_EmailCredentials() throws IOException {
			String existingEmail = email;

			RestAssured.baseURI = Web_Utils.propertyFile("RestAssured.baseURI") + "/AUTHENTICATE?";
			// request
			RequestSpecification request = given().header("Content-Type", "application/json").
					queryParam("CMD", "doMinimalSignupCLI").
					queryParam("email", existingEmail).
					queryParam("fname", "ISAGI").
					queryParam("lname", "YOICHI").
					queryParam("distributorID", "QKI-CLR").
					queryParam("textPW", "test1234");

			// response
			Response response = request.post();
			String responseBody = response.getBody().prettyPrint().toString();

			Listeners___.info_custom(email);
			Listeners___.info(responseBody); // response will be added to the extent report

			// parsing with jsonPath
			String errorMessage = Web_Utils.jsonPath(responseBody, "errorMessage");

			// validation
			softAssert = new SoftAssert();
			softAssert.assertEquals(errorMessage, "Account with this email already exists. Please try Signing in.");
			int code = response.statusCode();
			softAssert.assertEquals(code, 403);

		}













}

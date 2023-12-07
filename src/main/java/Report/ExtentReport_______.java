package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_______ {

public static ExtentReports extentReport()
{

String path = System.getProperty("user.dir")+"//src//main//java//Report//ICE-ITAutomationResults.html";
ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
sparkReporter.config().setDocumentTitle("ICE-IT");
sparkReporter.config().setReportName("AUTOMATION RESULTS");
sparkReporter.config().setTheme(Theme.DARK);


ExtentReports extentReports=new ExtentReports();
extentReports.attachReporter(sparkReporter);
extentReports.setSystemInfo("QA_TEAM: ", " ");
extentReports.setSystemInfo("OS: ", System.getProperty("os.name"));
extentReports.setSystemInfo("Browser: ", "Google Chrome");
extentReports.setSystemInfo("Environment: ","Dev");
extentReports.setSystemInfo("Web-Automation: ", "Selenium");
extentReports.setSystemInfo("API-Automation: ", "Rest Assured");


return extentReports;
}

}

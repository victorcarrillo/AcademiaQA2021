package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.CommonUtilities;
import utilities.Log;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public Properties prop = CommonUtilities.loadProperties("src/main/resources/testDara.properties");
    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void setUp(){
        Log.startLog("Starting logger");
        Log.info("Setting up test execution");
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        Log.info("Opening browser");

        //ExtentHtmlReporter = reporter = new ExtentHtmlReporter(projectPath + "/report/TestExecutionReport.html");
        //extent = new ExtentReports();
        //extent.attachReporter(reporter);
        //logger = extent.createTest("Amazon test");

        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterTest
    public void cleanUp(){
        Log.info("Cleaning up");
        driver.close();
        driver.quit();
        Log.info("Testing Complete");
    }
}

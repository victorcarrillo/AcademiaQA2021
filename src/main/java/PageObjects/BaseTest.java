package PageObjects;

import Utilities.CommonUtilities;
import Utilities.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties props;

    String projectPath = System.getProperty("user.dir");
    String osEnv = System.getProperty("os.name");

    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeSuite
    public void setUp() {
        Log.startLog("TestSuite is starting");
        Log.info("Setting up test execution");

        ExtentHtmlReporter reporter = new ExtentHtmlReporter(projectPath+"/report/TestExecutionReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        if(osEnv.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            props = CommonUtilities
                    .loadProperties(projectPath+"src/main/resources/testData.properties");

            ChromeOptions op = new ChromeOptions();
            if(props.getProperty("incognito").equals("true")) {
                op.addArguments("--incognito");
            }
            if(props.getProperty("headless").equals("true")) {
                op.addArguments("--headless");
            }
            if(props.getProperty("maximize").equals("true")) {
                op.addArguments("--start-maximized");
            }
            driver = new ChromeDriver(op);
            Log.info("Opening Browser");

        } else if(osEnv.contains("Win")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            props = CommonUtilities
                    .loadProperties(projectPath+"src\\main\\resources\\testData.properties");

            ChromeOptions op = new ChromeOptions();
            if(props.getProperty("incognito").equals("true")) {
                op.addArguments("--incognito");
            }
            if(props.getProperty("headless").equals("true")) {
                op.addArguments("--headless");
            }
            if(props.getProperty("maximize").equals("true")) {
                op.addArguments("--start-maximized");
            }
            driver = new ChromeDriver(op);
            Log.info("Opening Browser");
        } else {
            Log.fatal("No valid OS");
            throw new IllegalArgumentException("OS name not found");
        }
    }

    @AfterMethod
    public void testResults(ITestResult result) {
        if(result.getStatus()==ITestResult.SUCCESS) {
            logger.fail("Test "+ result.getTestName() +" passed");
            extent.flush();
        } else if(result.getStatus()==ITestResult.FAILURE) {
            logger.fail("Test "+ result.getTestName() +" failed");
            extent.flush();
        } else if(result.getStatus()==ITestResult.SKIP) {
            logger.fail("Test "+ result.getTestName() +" skipped");
            extent.flush();
        }
    }

    @AfterSuite
    public void shutDown() {
        Log.endLog("TestSuite is closing");
        driver.close();
        driver.quit();
    }

}

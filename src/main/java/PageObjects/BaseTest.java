package PageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.CommonUtilities;
import utilities.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    public Properties props;
    String osEnv = System.getProperty("os.name");
    public ExtentReports extent;
    public ExtentTest logger;
    SimpleDateFormat df = new SimpleDateFormat("MMM-dd-yyyy_HH-mm-ss");

    @BeforeSuite
    public void setUpReport(){
        Log.startLog("Test Suite is starting");
        if(osEnv.contains("Mac")) {
            ExtentHtmlReporter reporter = new ExtentHtmlReporter(projectPath + "/reports/TestExecutionReport_"+df.format(new Date())+".html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        } else if(osEnv.contains("Windows")) {
            ExtentHtmlReporter reporter = new ExtentHtmlReporter(projectPath + "\\reports\\TestExecutionReport_"+df.format(new Date())+".html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        else{
            Log.fatal("OS Not Supported");
            throw new IllegalArgumentException("OS Not supported");
        }
    }

    @BeforeMethod
    public void setUp(){
        Log.info("Setting up test execution");

        if(osEnv.contains("Mac")){
            System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
            props = CommonUtilities.loadProperties(projectPath+"/src/main/resources/testData.properties");

            ChromeOptions op = new ChromeOptions();
            if(props.getProperty("webapp.incognito").equals("true")){
                op.addArguments("--incognito");
            }
            if(props.getProperty("webapp.headlessExecution").equals("true")){
                op.addArguments("--headless");
            }

            driver = new ChromeDriver(op);
            Log.info("Opening Browser");

        } else if(osEnv.contains("Windows")){
            System.setProperty("webdriver.chrome.driver",projectPath+"\\drivers\\chromedriver.exe");
            props = CommonUtilities.loadProperties(projectPath+"\\src\\main\\resources\\testData.properties");

            ChromeOptions op = new ChromeOptions();
            if(props.getProperty("webapp.incognito").equals("true")){
                op.addArguments("--incognito");
            }
            if(props.getProperty("webapp.headlessExecution").equals("true")) {
                op.addArguments("--headless");
            }

            driver = new ChromeDriver(op);
            Log.info("Opening Browser");

        }
        else{
            Log.fatal("OS Not Supported");
            throw new IllegalArgumentException("OS Not supported");
        }
    }


    @AfterMethod
    public void cleanUp(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            logger.fail("Test "+result.getName() + " failed");
        }else if(result.getStatus()==ITestResult.SUCCESS){
            logger.pass("Test "+result.getName() + " passed");
        }
        extent.flush();
        Log.endLog("Tear down test execution");
        driver.close();
        driver.quit();
    }

}

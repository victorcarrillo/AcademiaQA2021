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

    public Properties props = CommonUtilities.loadProperties("src/main/resources/testData.properties");
    String projectPath = System.getProperty("user.dir");
    String osEnv = System.getProperty("os.name");
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeClass
    public void setUpReport(){
        //concatenenar String de fecha para reporte con fecha
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("C:\\Users\\bram\\IdeaProjects\\AcademiaQA2021\\report\\TestExecutionReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setUp(){
        Log.startLog("Test is starting");
        Log.info("Setting up test execution");

        if (osEnv.contains("Mac")){
            System.out.println("Mac");
        }
        if (osEnv.contains("Windows")){
            System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
            props = CommonUtilities.loadProperties("src/main/resources/testData.properties");
            ChromeOptions op = new ChromeOptions();
            op.addArguments("--incognito");
            if (props.getProperty("headlessExecution").equals("true")){
                op.addArguments("--headless");
            }
            driver = new ChromeDriver(op);
            Log.info("Opening browser");
        }else{
            Log.fatal("no OS found");
            throw new IllegalArgumentException("OS name not found");
        }



    }

    @AfterMethod
    public void cleanUp(ITestResult result){

        System.out.println(result.getStatus());
        if(result.getStatus()==ITestResult.FAILURE){
            logger.fail("Test "+ result.getMethod()+" failed.");
            extent.flush();
        }else if(result.getStatus()==ITestResult.SUCCESS){
            logger.pass("Test "+ result.getMethod()+ " passed");
            extent.flush();
        }else if(result.getStatus()==ITestResult.SKIP){
            logger.skip("Test "+ result.getMethod()+ " skipped");
            extent.flush();
        }

        Log.endLog("Ending execution");
        driver.close();
        driver.quit();
    }



}

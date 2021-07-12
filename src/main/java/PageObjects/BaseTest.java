package PageObjects;

import Utilities.CommonUtilities;
import Utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public Properties props = CommonUtilities.loadProperties("src/main/resources/testData.properties");
    String projectPath = System.getProperty("user.dir");
    String osEnv = System.getProperty("os.name");

    @BeforeMethod
    public void setUp(){
        Log.startLog("Test suite is starting");
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
    public void cleanUp(){
        Log.endLog("Ending execution");
        driver.close();
        driver.quit();
    }
}

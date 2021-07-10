package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.CommonUtilities;
import utilities.Log;

import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    public Properties props;
    String osEnv = System.getProperty("os.name");

    @BeforeMethod
    public void setUp(){
        Log.startLog("Test Suite is starting");
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
    public void cleanUp(){
        Log.endLog("Tear down test execution");
        driver.close();
        driver.quit();
    }

}

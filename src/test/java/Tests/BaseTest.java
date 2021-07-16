package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.CommonUtilities;
import utilities.Log;

import java.util.Properties;

public class BaseTest {
    public WebDriver driver ;
    public Properties props ;
    private String projectPath= System.getProperty("user.dir");
    String osEnv = System.getProperty("os.name");

    @BeforeMethod
    public void setUp(){
        String driverPath;
        String propertiesPath;

        Log.startLog("Test Suite is starting");
        Log.info("Setting up test execution");

        if(osEnv.contains("Mac")){
            driverPath = "drivers/chromedriver";
            propertiesPath = projectPath+ "/src/main/resources/testData.properties";
        } else if(osEnv.contains("Windows")){
            driverPath = projectPath + "\\drivers\\chromedriver.exe";
            propertiesPath = projectPath+ "\\src\\main\\resources\\testData.properties";
        } else {
            Log.fatal("No valid OS Name");
            throw new IllegalArgumentException("OS name not found");
        }

        System.setProperty("webdriver.chrome.driver", driverPath);
        props = CommonUtilities.loadProperties(propertiesPath);

        ChromeOptions op = new ChromeOptions();

        if(props.getProperty("webapp.incognito").equals("true")) {
            op.addArguments("--incognito");
        }
        if(props.getProperty("webapp.headlessExecution").equals("true")){
            op.addArguments("--headless");
        }

        driver = new ChromeDriver(op);
        Log.info("Opening Browser");
    }

    @AfterMethod
    public void cleanUp(){
        Log.endLog("Ending Execution");
        driver.close();
        driver.quit();
    }
}

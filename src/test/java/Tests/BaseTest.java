package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utilities.CommonUtilities;
import utilities.Log;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    String projectPath= System.getProperty("user.dir");
    public Properties prop = CommonUtilities.loadProperties("src/main/resources/testDara.properties");
    String osEnv = System.getProperty("os.name");


    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void setUp(){
        Log.startLog("Suite de pruebas inicializando");
        Log.info("Iniciando ejecución de pruebas");

        if(osEnv.contains("Mac")){

            System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
            prop=CommonUtilities.loadProperties(projectPath+ "/src/main/resources/testData.properties");

            ChromeOptions op = new ChromeOptions();

            if(prop.getProperty("webapp.incognito").equals("true")) {
                op.addArguments("--incognito");
            }
            if(prop.getProperty("webapp.headlessExecution").equals("true")){
                op.addArguments("--headless");
            }


            driver = new ChromeDriver(op);
            Log.info("Opening Browser");

        } else if(osEnv.contains("Windows")){

            System.setProperty("webdriver.chrome.driver",projectPath + "\\drivers\\chromedriver.exe");
            prop = CommonUtilities.loadProperties(projectPath+ "\\src\\main\\resources\\testData.properties");
            driver = new ChromeDriver();
            Log.info("Opening Browser");

        } else {

            Log.fatal("No valid OS Name");
            throw new IllegalArgumentException("OS name not found");
        }

    }

    @AfterTest
    public void cleanUp(){
        Log.info("Limpiando");
        driver.close();
        driver.quit();
        Log.endLog("Finalizando ejecución");
    }
}

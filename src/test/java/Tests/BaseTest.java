package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITest;
import org.testng.ITestClass;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.CommonUtilities;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public Properties prop = CommonUtilities.loadProperties("src/main/resources/testData.properties");
    String projectPath= System.getProperty("user.dir");
    String osEnv = System.getProperty("os.name");


    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeClass
    public void extentSetUp() {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(projectPath + "/report/" + this.getClass().getName() + "TestExecutionReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
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

            ChromeOptions op = new ChromeOptions();

            if(prop.getProperty("webapp.incognito").equals("true")) {
                op.addArguments("--incognito");
            }
            if(prop.getProperty("webapp.headlessExecution").equals("true")){
                op.addArguments("--headless");
            }

            driver = new ChromeDriver(op);
            Log.info("Opening Browser");

        } else {

            Log.fatal("No valid OS Name");
            throw new IllegalArgumentException("OS name not found");
        }

    }

    @AfterMethod
    public void cleanUp(ITestResult result){

        if(result.getStatus() == ITestResult.FAILURE){
            logger.fail("Prueba " + result.getTestName() + " fallo");
        }else if(result.getStatus() == ITestResult.SUCCESS){
            logger.pass("Prueba " + result.getTestName() + " pasada");
        }
        Log.info("Limpiando");
        driver.close();
        driver.quit();
        Log.endLog("Finalizando ejecución");
    }

    @AfterClass
    public void extentCleanUp(){
        extent.flush();
    }

    public List<Float> convertToFloat(List<WebElement> elements){
        List<Float> prices = new ArrayList<>();
        String tempPrice;

        for(int i = 0; i < elements.size(); i++){
            tempPrice = elements.get(i).getText();
            tempPrice= tempPrice.replace("$", "");
            prices.add(Float.parseFloat(tempPrice));
        }
        return prices;
    }
}

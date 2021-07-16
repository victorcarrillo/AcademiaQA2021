package Tests;

import PageObjects.BaseTest;
import PageObjects.LoginPage;
import PageObjects.InventoryPage;
import Utilities.Log;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    String URL = props.getProperty("webapp");
    /**Test data**/
    String VALID_USERNAME ;
    String MASTER_PASSWORD ;
    String LOCKED_OUT_USER ;
    String INVALID_USERNAME ;
    String EMPTY_FIELD = "";
    String ORDER_CRITERIA;
    /**EXPECTED**/
    String INVENTORY_PAGE_TITLE;
    String INVALID_CREDENTIALS_MESSAGE;
    String EMPTY_USERNAME_MESSAGE ;
    String EMPTY_PASSWORD_MESSAGE ;


    @Test
    public void validLoginCase()  {

        Log.info("Valid login case started");
        //html output initialization
        logger = extent.createTest("Valid login test");

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        MASTER_PASSWORD = props.getProperty("masterPassword");
        INVENTORY_PAGE_TITLE = props.getProperty("productsPageTitle");

        //test run
        logger.info("Opening "+URL);
        driver.get(URL);
        logger.info("Resource located");


        logger.info("Locating login page elements");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.validateLoginPage();
        logger.info("Login elements located");
        logger.info("Attempting login");
        loginPage.login(VALID_USERNAME, MASTER_PASSWORD);
        logger.info("Login attempt completed");
        logger.info("Validating login");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.validateLogin(INVENTORY_PAGE_TITLE);
        logger.info("Login validation completed");

        Log.info("Test completed: inventory page located");
        logger.info("Test completed: inventory page located");
    }

    @Test
    public void filterFunctionalityCase()  {

        Log.info("Filter functionality test case started");
        //html output initialization
        logger = extent.createTest("Sorting function test");

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        MASTER_PASSWORD = props.getProperty("masterPassword");
        INVENTORY_PAGE_TITLE = props.getProperty("productsPageTitle");
        ORDER_CRITERIA = props.getProperty("orderCriteria");

        //test run
        logger.info("Opening "+URL);
        driver.get(URL);
        logger.info("Resource located");


        logger.info("Locating login page elements");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.validateLoginPage();
        logger.info("Login elements located");
        logger.info("Attempting login");
        loginPage.login(VALID_USERNAME, MASTER_PASSWORD);
        logger.info("Login attempt completed");
        logger.info("Validating login");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.validateLogin(INVENTORY_PAGE_TITLE);
        logger.info("Inventory page located.");

        //filter validation
        logger.info("Starting list sorting validation");
        inventoryPage.validateSorting(ORDER_CRITERIA);
        logger.info("List sorting validation completed.");

        Log.info("Test completed: filter verification succesful");
        logger.info("Test completed: filter verification succesful");

    }

    @Test
    public void invalidLoginCase(){

        Log.info("Invalid login test case started");
        logger = extent.createTest("Invalid login message test");

        //data setup
        INVALID_USERNAME = props.getProperty("invalidUsername");
        MASTER_PASSWORD = props.getProperty("masterPassword");
        INVALID_CREDENTIALS_MESSAGE = props.getProperty("invalidCredentialsMessage");

        //test run
        logger.info("Opening "+URL);
        driver.get(URL);
        logger.info("Resource located");

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Locating login page elements");
        loginPage.validateLoginPage();
        logger.info("Login page elements found");
        logger.info("Logging in with username/password credentials -> "+INVALID_USERNAME+":"+MASTER_PASSWORD );
        loginPage.login(INVALID_USERNAME, MASTER_PASSWORD);
        logger.info("Login attempt completed");

        //test result validation
        logger.info("Validating invalid credentials message load");
        loginPage.validateErrorMessage(INVALID_CREDENTIALS_MESSAGE);
        logger.info("Message verification completed");

        Log.info("Test completed: message matches expected result");
        logger.info("Test completed: message matches expected result");

    }

    @Test
    public void emptyUsernameMessageCase(){

        Log.info("Empty username message test case started");
        logger = extent.createTest("Empty username field message test");

        //data setup
        EMPTY_USERNAME_MESSAGE = props.getProperty("emptyUsernameMessage");

        //test run
        logger.info("Opening "+URL);
        driver.get(URL);
        logger.info("Resource located");

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Locating login page elements");
        loginPage.validateLoginPage();
        logger.info("Login page elements found");
        logger.info("Login attempt with empty username field");
        loginPage.login(EMPTY_FIELD, EMPTY_FIELD);
        logger.info("Login attempt completed");

        logger.info("Validating empty user message");
        loginPage.validateErrorMessage(EMPTY_USERNAME_MESSAGE);
        logger.info("Error message validation complete");

        Log.info("Test completed: message matches expected result");
        logger.info("Test completed: message matches expected result");
    }

    @Test
    public void emptyPasswordMessageCase(){

        Log.info("Empty password message case started");
        logger = extent.createTest("Empty password field error message");

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        EMPTY_PASSWORD_MESSAGE = props.getProperty("emptyPasswordMessage");

        //test run
        logger.info("Opening "+URL);
        driver.get(URL);
        logger.info("Resource located");

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Locating login page elements");
        loginPage.validateLoginPage();
        logger.info("Login page elements found");
        logger.info("Login attempt with empty password field");
        loginPage.login(VALID_USERNAME, EMPTY_FIELD);
        logger.info("LOgin attempt completed");

        logger.info("Validating empty password message");
        loginPage.validateErrorMessage(EMPTY_PASSWORD_MESSAGE);
        logger.info("Error message validation complete");

        Log.info("Test completed: message matches expected result");
        logger.info("Test completed: message matches expected result");
    }

//    @Test(dependsOnMethods = "validLoginCase")
//    public void loginImage(){
//        ProductsPage productsPage = new ProductsPage(driver);
//        Assert.assertEquals(productsPage.validateLogin(),true, "Login image not found");
//        System.out.println("Login image found");
//    }

}


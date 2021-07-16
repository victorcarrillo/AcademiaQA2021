package Tests;

import PageObjects.*;
import Utilities.Log;
import org.testng.annotations.Test;

public class TestCases extends BaseTest {
    String URL = props.getProperty("webapp");
    /**Test data**/
    String VALID_USERNAME ;
    String VALID_USERNAME2;
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
    public void cartFunctionalityCase()  {

        Log.info("Cart functionality case started");
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

        logger.info("Cart functionality verification started");
        inventoryPage.addTestItem();
        NavbarPage navbarPage = new NavbarPage(driver);
        navbarPage.validateCartAdded();
        inventoryPage.deleteTestItem();
        navbarPage.validateCartEmpty();

        Log.info("Test completed: cart functionality");
        logger.info("Test completed: cart functionality");
    }

    @Test
    public void checkoutCase()  {

        Log.info("Checkout functionality case started");
        //html output initialization
        logger = extent.createTest("Checkout test");

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

        logger.info("Adding items to cart");
        inventoryPage.addTestItem();
        NavbarPage navbarPage = new NavbarPage(driver);
        navbarPage.validateCartAdded();
        logger.info("Items added");
        navbarPage.goToCart();
        logger.info("Starting checkout process");
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        logger.info("Validating checkout form elements");
        checkoutPage.validateStepOne();
        checkoutPage.stepOneFormSubmit("abraham", "rueda", "45567");
        logger.info("Validating checkout summary elements");
        checkoutPage.validateSummary();
        checkoutPage.finishCheckout();
        logger.info("Validating checkout complete message");
        checkoutPage.validateCheckout();
        logger.info("Message foung");


        Log.info("Test completed: checkout functionality");
        logger.info("Test completed: checkout functionality");
    }

    @Test
    public void cartSessionCase()  {

        Log.info("Cart session reset case started");
        //html output initialization
        logger = extent.createTest("Cart session test");

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        VALID_USERNAME2 = props.getProperty("validUsername2");
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

        logger.info("Adding items to cart");
        inventoryPage.addTestItem();
        NavbarPage navbarPage = new NavbarPage(driver);
        navbarPage.validateCartAdded();
        logger.info("Items added");

        Log.info("Switching user session");

        navbarPage.logout();

        loginPage = new LoginPage(driver);
        logger.info("Attempting login with different user");
        loginPage.login(VALID_USERNAME2, MASTER_PASSWORD);
        logger.info("Login attempt completed");
        logger.info("Validating login");
        inventoryPage = new InventoryPage(driver);
        inventoryPage.validateLogin(INVENTORY_PAGE_TITLE);
        logger.info("Login validation completed");
        logger.info("Starting cart session reset validation");
        navbarPage = new NavbarPage(driver);
        navbarPage.validateCartEmpty();
        logger.info("Validation complete");
        Log.info("Test completed: checkout functionality");
        logger.info("Test completed: checkout functionality");

    }


    @Test
    public void emptyCheckoutCase()  {

        Log.info("Empty checkout case started");
        //html output initialization
        logger = extent.createTest("Empty checkout test");

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

        NavbarPage navbarPage = new NavbarPage(driver);

        navbarPage.goToCart();
        logger.info("Starting checkout process");
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.validateEmptyCheckout();


        Log.info("Test completed: empty checkout alert functionality");
        logger.info("Test completed: empty checkout alert functionality");
    }

    //@Test
    public void sessionTimeoutFeatureCase() throws Exception {

        Log.info("Session timeout feature case started");
        //html output initialization
        logger = extent.createTest("Timeout feature test");

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


        logger.info("Starting timeout validation");
        Thread.sleep(400000);
        driver.navigate().refresh();
        loginPage.validateLoginPage();
        logger.info("Login validation completed");


        Log.info("Test completed: timeout functionality verified");
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


}


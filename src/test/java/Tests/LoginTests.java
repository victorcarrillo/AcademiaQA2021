package Tests;

import PageObjects.BaseTest;
import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    String URL = props.getProperty("webapp");
    /**Test data**/
    String VALID_USERNAME ;
    String MASTER_PASSWORD ;
    String LOCKED_OUT_USER ;
    String INVALID_USERNAME ;
    /**EXPECTED**/
    String PRODUCTS_PAGE_TITLE;
    String INVALID_CREDENTIALS_MESSAGE;
    String EMPTY_USERNAME_MESSAGE ;
    String EMPTY_PASSWORD_MESSAGE ;

    @Test
    public void validLoginCase()  {

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        MASTER_PASSWORD = props.getProperty("masterPassword");
        PRODUCTS_PAGE_TITLE = props.getProperty("productsPageTitle");

        //test run
        System.out.println("Opening "+URL);
        driver.get(URL);

        Log.info("Locating login page elements");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.validateLoginPage(),true, "Login page elements validation");
        Log.info("Login page elements found");
        Log.info("Logging in with username/password credentials -> "+VALID_USERNAME+":"+MASTER_PASSWORD );
        loginPage.login(VALID_USERNAME, MASTER_PASSWORD);

        Log.info("Validating products page load");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.validateLogin(PRODUCTS_PAGE_TITLE), "Products page validation");
        Log.info("Test successful: Products page found.");
    }

    @Test
    public void invalidLoginCase(){

        //data setup
        INVALID_USERNAME = props.getProperty("invalidUsername");
        MASTER_PASSWORD = props.getProperty("masterPassword");
        INVALID_CREDENTIALS_MESSAGE = props.getProperty("invalidCredentialsMessage");

        //test run
        Log.info("Opening "+URL);
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        Log.info("Locating login page elements");
        Assert.assertEquals(loginPage.validateLoginPage(),true, "Login page elements validation");
        Log.info("Login page elements found");
        Log.info("Logging in with username/password credentials -> "+INVALID_USERNAME+":"+MASTER_PASSWORD );
        loginPage.login(INVALID_USERNAME, MASTER_PASSWORD);

        Log.info("Validating invalid credentials message load");
        Assert.assertEquals(loginPage.validateErrorMessage(INVALID_CREDENTIALS_MESSAGE), true, "Invalid message validation");
        Log.info("Test successful: Invalid credentials message found");

    }

    @Test
    public void emptyUsernameMessageCase(){

        //data setup
        EMPTY_USERNAME_MESSAGE = props.getProperty("emptyUsernameMessage");

        //test run
        System.out.println("Opening "+URL);
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        Log.info("Locating login page elements");
        Assert.assertEquals(loginPage.validateLoginPage(),true, "Login page elements validation");
        Log.info("Login page elements found");
        Log.info("Login attempt with empty username field");
        loginPage.login();

        Log.info("Validating empty user message");
        Assert.assertEquals(loginPage.validateErrorMessage(EMPTY_USERNAME_MESSAGE), true, "Empty username message validation");
        Log.info("Test successful: Empty username message found");
    }

    @Test
    public void emptyPasswordMessageCase(){

        //data setup
        VALID_USERNAME = props.getProperty("validUsername");
        EMPTY_PASSWORD_MESSAGE = props.getProperty("emptyPasswordMessage");

        //test run
        Log.info("Opening "+URL);
        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        Log.info("Locating login page elements");
        Assert.assertEquals(loginPage.validateLoginPage(),true, "Login page elements validation");
        Log.info("Login page elements found");
        Log.info("Login attempt with empty password field");
        loginPage.login(VALID_USERNAME);

        Log.info("Validating empty password message");
        Assert.assertEquals(loginPage.validateErrorMessage(EMPTY_PASSWORD_MESSAGE), true, "Empty password message validation");
        Log.info("Test successful: Empty password message found");
    }

//    @Test(dependsOnMethods = "validLoginCase")
//    public void loginImage(){
//        ProductsPage productsPage = new ProductsPage(driver);
//        Assert.assertEquals(productsPage.validateLogin(),true, "Login image not found");
//        System.out.println("Login image found");
//    }

}


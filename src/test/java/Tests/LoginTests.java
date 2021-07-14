package Tests;

import PageObjects.BaseTest;
import PageObjects.LoginPage;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    String URL;
    String username;
    String password;
    String expectedPage;
    String errorMessage;

    @Test(testName = "TestCase 1")
    public void loginForm() {
        logger = extent.createTest("Test Case 1");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Validating Username Field");
        loginPage.isUserField();

        Log.info("Validating Password Field");
        loginPage.isPassField();

        Log.info("Validating Login Button");
        loginPage.isLoginButton();
    }

    @Test(testName = "TestCase 2")
    public void successLogin() {
        logger = extent.createTest("Test Case 2");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with valid user and correct password");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());
    }

    @Test(testName = "TestCase 3")
    public void failLoginUser() {
        logger = extent.createTest("Test Case 3");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("not_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.invalid_user");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with invalid user and password");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertTrue(loginPage.currentPageIsLogin());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 4")
    public void failLoginPassword() {
        logger = extent.createTest("Test Case 4");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("not_pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.invalid_password");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with valid user and wrong password");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertTrue(loginPage.currentPageIsLogin());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 5")
    public void failLoginBlockedUser() {
        logger = extent.createTest("Test Case 5");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("blocked_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.blocked_user");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with blocked user and correct password");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertTrue(loginPage.currentPageIsLogin());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 6")
    public void incompleteLoginUser() {
        logger = extent.createTest("Test Case 6");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = "";
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.missing_username");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with only password");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertTrue(loginPage.currentPageIsLogin());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 7")
    public void incompleteLoginPassword() {
        logger = extent.createTest("Test Case 7");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = "";
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.missing_password");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with only user");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertTrue(loginPage.currentPageIsLogin());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 8")
    public void closeMessageError() {
        logger = extent.createTest("Test Case 8");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = "";
        password = "";

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with invalid data to get an error message");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying error message shown");
        errorMessage = loginPage.errorMessage();

        Log.info("Closing error message: "+errorMessage);
        loginPage.closeErrorMessage();
    }
}

package Tests;

import PageObjects.BaseTest;
import PageObjects.LoginPage;
import Utilities.Log;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    ExtentTest steps;
    String URL;
    String username;
    String password;
    String expectedPage;
    String errorMessage;
    boolean result_bool;
    String result_string;

    @Test(testName = "TestCase 1", priority = 1)
    public void loginForm() {
        logger = extent.createTest("Test Case 1");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Verifying presence of Username Field");
        steps.info("Verifying presence of Username Field");
        result_bool = loginPage.isUserField();
        if(result_bool)
            logger.pass("Username Field found");
        else
            logger.fail("Username Field not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of Password Field");
        steps.info("Verifying presence of Password Field");
        result_bool = loginPage.isPassField();
        if(result_bool)
            logger.pass("Password Field found");
        else
            logger.fail("Password Field not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of Login Button\n");
        steps.info("Verifying presence of Login Button");
        result_bool = loginPage.isLoginButton();
        if(result_bool)
            logger.pass("Login button found");
        else
            logger.fail("Login button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 2", priority = 2)
    public void successLogin() {
        logger = extent.createTest("Test Case 2");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with valid user and correct password");
        steps.info("Filling up username field with "+ username);
        steps.info("Filling up password field with "+ password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Login success");
        else
            logger.fail("Login failed");
        Assert.assertEquals(expectedPage,result_string);
    }

    @Test(testName = "TestCase 3", priority = 3)
    public void failLoginUser() {
        logger = extent.createTest("Test Case 3");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("not_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.invalid_user");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with invalid user and password");
        steps.info("Filling up username field with "+username);
        steps.info("Filling up password field with "+password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_bool = loginPage.currentPageIsLogin();
        if(result_bool)
            logger.pass("Login form is current page");
        else
            logger.fail("Login form is not current page");
        Assert.assertTrue(result_bool);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage() ;
        if(errorMessage.equals(result_string))
            logger.pass("Error message is correct");
        else
            logger.fail("Error message is incorrect");
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 4", priority = 4)
    public void failLoginPassword() {
        logger = extent.createTest("Test Case 4");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("not_pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.invalid_password");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with valid user and wrong password");
        steps.info("Filling up username field with "+ username);
        steps.info("Filling up password field with "+ password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_bool = loginPage.currentPageIsLogin();
        if(result_bool)
            logger.pass("Login form is current page");
        else
            logger.fail("Login form is not current page");
        Assert.assertTrue(result_bool);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage() ;
        if(errorMessage.equals(result_string))
            logger.pass("Error message is correct");
        else
            logger.fail("Error message is incorrect");
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 5", priority = 5)
    public void failLoginBlockedUser() {
        logger = extent.createTest("Test Case 5");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("blocked_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.blocked_user");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with blocked user and correct password");
        steps.info("Filling up username field with "+username);
        steps.info("Filling up password field with "+password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_bool = loginPage.currentPageIsLogin();
        if(result_bool)
            logger.pass("Login form is current page");
        else
            logger.fail("Login form is not current page");
        Assert.assertTrue(result_bool);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage() ;
        if(errorMessage.equals(result_string))
            logger.pass("Error message is correct");
        else
            logger.fail("Error message is incorrect");
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 6", priority = 6)
    public void incompleteLoginUser() {
        logger = extent.createTest("Test Case 6");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = "";
        password = props.getProperty("pass");
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.missing_username");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with only password");
        steps.info("Filling up username field with "+username);
        steps.info("Filling up password field with "+password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_bool = loginPage.currentPageIsLogin();
        if(result_bool)
            logger.pass("Login form is current page");
        else
            logger.fail("Login form is not current page");
        Assert.assertTrue(result_bool);

        Log.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage() ;
        if(errorMessage.equals(result_string))
            logger.pass("Error message is correct");
        else
            logger.fail("Error message is incorrect");
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 7", priority = 7)
    public void incompleteLoginPassword() {
        logger = extent.createTest("Test Case 7");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = "";
        expectedPage = props.getProperty("login_page");
        errorMessage = props.getProperty("error.missing_password");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with only user");
        steps.info("Filling up username field with "+username);
        steps.info("Filling up password field with "+password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_bool = loginPage.currentPageIsLogin();
        if(result_bool)
            logger.pass("Login form is current page");
        else
            logger.fail("Login form is not current page");
        Assert.assertTrue(result_bool);

        Log.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage() ;
        if(errorMessage.equals(result_string))
            logger.pass("Error message is correct");
        else
            logger.fail("Error message is incorrect");
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 8", priority = 8)
    public void closeMessageError() {
        logger = extent.createTest("Test Case 8");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);

        URL = props.getProperty("webapp");
        username = "";
        password = "";

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Filling up login form with invalid data to get an error message");
        steps.info("Filling up username field with "+username);
        steps.info("Filling up password field with "+password);
        steps.info("Clicking login button");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying error message shown");
        steps.info("Verifying error message shown");
        errorMessage = loginPage.errorMessage();

        Log.info("Closing error message: "+errorMessage);
        steps.info("Closing error message: "+errorMessage);
        result_bool = loginPage.closeErrorMessage();
        if(result_bool)
            logger.pass("Error message closed");
        else
            logger.fail("Error message didn't close");
        Assert.assertTrue(result_bool);
    }
}

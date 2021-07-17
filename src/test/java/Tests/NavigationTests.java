package Tests;

import PageObjects.*;
import Utilities.Log;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {
    ExtentTest steps;
    String URL;
    String username;
    String password;
    String expectedPage;
    boolean result_bool;
    String result_string;

    @Test(testName = "TestCase 38", priority = 1)
    public void headerElements() {
        logger = extent.createTest("Test Case 38");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'burger' menu");
        result_bool = navigationPage.isBurgerMenu();
        if(result_bool)
            logger.pass("Burger menu found");
        else
            logger.fail("Burger menu not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of app logo");
        result_bool = navigationPage.isAppLogo();
        if(result_bool)
            logger.pass("App logo found");
        else
            logger.fail("App logo not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'cart' button");
        result_bool = navigationPage.isCartButton();
        if(result_bool)
            logger.pass("Cart button found");
        else
            logger.fail("Cart button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 39", priority = 2)
    public void footerElements() {
        logger = extent.createTest("Test Case 39");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of twitter link");
        steps.info("Verifying presence of twitter link");
        result_bool = navigationPage.isTwitterLink();
        if(result_bool)
            logger.pass("Twitter link found");
        else
            logger.fail("Twitter link not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of facebook link");
        steps.info("Verifying presence of facebook link");
        result_bool = navigationPage.isFacebookLink();
        if(result_bool)
            logger.pass("Facebook link found");
        else
            logger.fail("Facebook link not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of linkedin link");
        steps.info("Verifying presence of linkedin link");
        result_bool = navigationPage.isLinkedinLink();
        if(result_bool)
            logger.pass("Linkedin link found");
        else
            logger.fail("Linkedin link not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of copyright message");
        steps.info("Verifying presence of copyright message");
        result_bool = navigationPage.isCopyright();
        if(result_bool)
            logger.pass("Copyright message found");
        else
            logger.fail("Copyright message not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 40", priority = 3)
    public void menuNavigationProducts() {
        logger = extent.createTest("Test Case 40");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        steps.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in all products option");
        steps.info("Clicking in all products option");
        navigationPage.clickOnProductsOption();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking all products option");
        else
            logger.fail("Clicking all products option didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);
    }

    @Test(testName = "TestCase 41", priority = 4)
    public void menuNavigationAbout() {
        logger = extent.createTest("Test Case 41");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        steps.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in about option");
        steps.info("Clicking in about option");
        navigationPage.clickOnAboutOption();

        URL = props.getProperty("about_page");
        Log.info("Verifying if current webpage is "+URL);
        steps.info("Verifying if current webpage is "+URL);
        result_string = driver.getCurrentUrl();
        if(URL.equals(result_string))
            logger.pass("Going to "+URL+" by clicking all products option");
        else
            logger.fail("Clicking all products option didn't lead to "+URL);
        Assert.assertEquals(URL, result_string);
    }

    @Test(testName = "TestCase 42", priority = 5)
    public void menuNavigationLogout() {
        logger = extent.createTest("Test Case 42");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        steps.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in logout option");
        steps.info("Clicking in logout option");
        navigationPage.clickOnLogout();

        expectedPage = props.getProperty("login_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = driver.getCurrentUrl();
        if(URL.equals(result_string))
            logger.pass("Going to "+URL+" by clicking all products option");
        else
            logger.fail("Clicking all products option didn't lead to "+URL);
        Assert.assertEquals(URL, result_string);
    }
}

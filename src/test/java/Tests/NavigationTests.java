package Tests;

import PageObjects.*;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {
    String URL;
    String username;
    String password;
    String expectedPage;

    @Test(testName = "TestCase 38", priority = 1)
    public void headerElements() {
        logger = extent.createTest("Test Case 38");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'burger' menu");
        navigationPage.isBurgerMenu();

        Log.info("Verifying presence of app logo");
        navigationPage.isAppLogo();

        Log.info("Verifying presence of 'cart' button");
        navigationPage.isCartButton();
    }

    @Test(testName = "TestCase 39", priority = 2)
    public void footerElements() {
        logger = extent.createTest("Test Case 38");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of twitter link");
        navigationPage.isTwitterLink();

        Log.info("Verifying presence of facebook link");
        navigationPage.isFacebookLink();

        Log.info("Verifying presence of linkedin link");
        navigationPage.isLinkedinLink();

        Log.info("Verifying presence of copyright message");
        navigationPage.isCopyright();
    }

    @Test(testName = "TestCase 40", priority = 3)
    public void menuNavigationProducts() {
        logger = extent.createTest("Test Case 40");
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in all products option");
        navigationPage.clickOnProductsOption();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());
    }

    @Test(testName = "TestCase 41", priority = 4)
    public void menuNavigationAbout() {
        logger = extent.createTest("Test Case 41");
        LoginPage loginPage = new LoginPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in about option");
        navigationPage.clickOnAboutOption();

        URL = props.getProperty("about_page");
        Log.info("Verifying if current webpage is "+URL);
        Assert.assertEquals(URL,driver.getCurrentUrl());
    }

    @Test(testName = "TestCase 42", priority = 5)
    public void menuNavigationLogout() {
        logger = extent.createTest("Test Case 42");
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in burger menu");
        navigationPage.clickOnBurgerMenu();

        Log.info("Clicking in logout option");
        navigationPage.clickOnLogout();

        expectedPage = props.getProperty("login_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(URL,driver.getCurrentUrl());
    }
}

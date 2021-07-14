package Tests;

import PageObjects.BaseTest;
import PageObjects.CatalogPage;
import PageObjects.LoginPage;
import Utilities.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CatalogTests extends BaseTest {
    String URL;
    String username;
    String password;
    String expectedPage;
    String testProduct;
    String productName;
    List<WebElement> productsList;

    @Test(testName = "TestCase 9")
    public void catalogPage() {
        logger = extent.createTest("Test Case 9");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

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

        Log.info("Validating if products in inventory");
        catalogPage.productsInInventory();

        Log.info("Validating sort option");
        catalogPage.isSortOption();
    }

    @Test(testName = "TestCase 10")
    public void productCardElements() {
        logger = extent.createTest("Test Case 10");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

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

        Log.info("Validating if name in products' cards");
        catalogPage.productsCardsHaveName();

        Log.info("Validating if image in products' cards");
        catalogPage.productsCardsHaveImage();

        Log.info("Validating if description in products' cards");
        catalogPage.productsCardsHaveDescription();

        Log.info("Validating if price in products' cards");
        catalogPage.productsCardsHavePrice();

        Log.info("Validating if 'add to cart' button in products' cards");
        catalogPage.productsCardsHaveButton();
    }

    @Test(testName = "TestCase 11")
    public void productCardNavigation() {
        logger = extent.createTest("Test Case 11");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_to_test");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying if current page is "+productName+"'s page");
        Assert.assertEquals(productName,catalogPage.currentPage());
    }

    @Test(testName = "TestCase 12")
    public void productPageElements() {
        logger = extent.createTest("Test Case 12");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_to_test");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying presence of name in "+productName+"'s page");
        catalogPage.isNameInPage();

        Log.info("Verifying presence of image in "+productName+"'s page");
        catalogPage.isImageInPage();

        Log.info("Verifying presence of description in "+productName+"'s page");
        catalogPage.isDescriptionInPage();

        Log.info("Verifying presence of price in "+productName+"'s page");
        catalogPage.isPriceInPage();

        Log.info("Verifying presence of 'add to cart' button in "+productName+"'s page");
        catalogPage.isButtonInPage();

        Log.info("Verifying presence of 'back to products' button in "+productName+"'s page");
        catalogPage.isBackInPage();
    }

    @Test(testName = "TestCase 13")
    public void productPageNavigation() {
        logger = extent.createTest("Test Case 13");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_to_test");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Going back to inventory page from "+productName+"'s page");
        catalogPage.toInventoryFromProduct();

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

    }
}

package Tests;

import PageObjects.BaseTest;
import PageObjects.CatalogPage;
import PageObjects.LoginPage;
import PageObjects.NavigationPage;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class CatalogTests extends BaseTest {
    String URL;
    String username;
    String password;
    String expectedPage;
    String testProduct;
    String productName;
    String sortBy;
    int numberOfBefore;
    int numberOfAfter;
    List<String> listBeforeSortName;
    List<String> listAfterSortName;
    List<Float> listBeforeSortPrice;
    List<Float> listAfterSortPrice;

    @Test(testName = "TestCase 9", priority = 1)
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

        Log.info("Validating if 'sort option' in inventory");
        catalogPage.isSortOption();
    }

    @Test(testName = "TestCase 10", priority = 2)
    public void productCardElements() {
        logger = extent.createTest("Test Case 10");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
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

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

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

    @Test(testName = "TestCase 11", priority = 3)
    public void productCardNavigation() {
        logger = extent.createTest("Test Case 11");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

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

    @Test(testName = "TestCase 12", priority = 4)
    public void productPageElements() {
        logger = extent.createTest("Test Case 12");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

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

    @Test(testName = "TestCase 13", priority = 5)
    public void productPageNavigation() {
        logger = extent.createTest("Test Case 13");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Clicking in 'back to products' button from "+productName+"'s page");
        catalogPage.toInventoryFromProduct();

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());
    }

    @Test(testName = "TestCase 14", priority = 6)
    public void sortNameAToZ() {
        logger = extent.createTest("Test Case 14");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_az");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's names in inventory BEFORE sorting");
        listBeforeSortName = catalogPage.getNamesInInventory();

        Log.info("Sorting list gotten BEFORE sorting");
        Collections.sort(listBeforeSortName);

        Log.info("Sorting products in page by name from 'a' to 'z'");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's names in inventory AFTER sorting");
        listAfterSortName = catalogPage.getNamesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        Assert.assertEquals(listBeforeSortName,listAfterSortName);
    }

    @Test(testName = "TestCase 15", priority = 7)
    public void sortNameZToA() {
        logger = extent.createTest("Test Case 15");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_za");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's names in inventory BEFORE sorting");
        listBeforeSortName = catalogPage.getNamesInInventory();

        Log.info("Sorting list gotten BEFORE sorting");
        Collections.sort(listBeforeSortName);
        Collections.reverse(listBeforeSortName);

        Log.info("Sorting products in page by name from 'z' to 'a'");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's names in inventory AFTER sorting");
        listAfterSortName = catalogPage.getNamesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        Assert.assertEquals(listBeforeSortName,listAfterSortName);
    }

    @Test(testName = "TestCase 16", priority = 8)
    public void sortPriceLowToHigh() {
        logger = extent.createTest("Test Case 16");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_lohi");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's prices in inventory BEFORE sorting");
        listBeforeSortPrice = catalogPage.getPricesInInventory();

        Log.info("Sorting list gotten BEFORE sorting");
        Collections.sort(listBeforeSortPrice);

        Log.info("Sorting products in page by price from low to high");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's prices in inventory AFTER sorting");
        listAfterSortPrice = catalogPage.getPricesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        Assert.assertEquals(listBeforeSortPrice,listAfterSortPrice);
    }

    @Test(testName = "TestCase 17", priority = 9)
    public void sortPriceHighToLow() {
        logger = extent.createTest("Test Case 17");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_hilo");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's prices in inventory BEFORE sorting");
        listBeforeSortPrice = catalogPage.getPricesInInventory();

        Log.info("Sorting list gotten BEFORE sorting");
        Collections.sort(listBeforeSortPrice);
        Collections.reverse(listBeforeSortPrice);

        Log.info("Sorting products in page by price from high to low");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's prices in inventory AFTER sorting");
        listAfterSortPrice = catalogPage.getPricesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        Assert.assertEquals(listBeforeSortPrice,listAfterSortPrice);
    }

    @Test(testName = "TestCase 18", priority = 10)
    public void productCardAddToCart() {
        logger = extent.createTest("Test Case 18");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Getting how many products in cart BEFORE adding new product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Getting how many products in cart AFTER adding new product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        Assert.assertTrue(numberOfAfter > numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'remove'");
        catalogPage.isRemove(testProduct);
    }

    @Test(testName = "TestCase 19", dependsOnMethods = "productCardAddToCart", priority = 11)
    public void productCardRemoveFromCart() {
        logger = extent.createTest("Test Case 19");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        productName = catalogPage.removeFromCart(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        Assert.assertTrue(numberOfAfter < numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        catalogPage.isAddTo(testProduct);
    }

    @Test(testName = "TestCase 20", priority = 12)
    public void productPageAddToCart() {
        logger = extent.createTest("Test Case 20");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying if current page is "+productName+"'s page");
        Assert.assertEquals(productName,catalogPage.currentPage());

        Log.info("Getting how many products in cart BEFORE adding new product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCartPage(testProduct);

        Log.info("Getting how many products in cart AFTER adding new product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        Assert.assertTrue(numberOfAfter > numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'remove'");
        catalogPage.isRemovePage(testProduct);
    }

    @Test(testName = "TestCase 21",dependsOnMethods = "productPageAddToCart", priority = 13)
    public void productPageRemoveFromCart() {
        logger = extent.createTest("Test Case 21");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Verifying if current page is "+productName+"'s page");
        Assert.assertEquals(productName,catalogPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        productName = catalogPage.removeFromCartPage(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        Assert.assertTrue(numberOfAfter < numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        catalogPage.isAddToPage(testProduct);
    }
}

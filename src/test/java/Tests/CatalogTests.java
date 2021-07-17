package Tests;

import PageObjects.BaseTest;
import PageObjects.CatalogPage;
import PageObjects.LoginPage;
import PageObjects.NavigationPage;
import Utilities.Log;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class CatalogTests extends BaseTest {
    ExtentTest steps;
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
    boolean result_bool;
    String result_string;

    @Test(testName = "TestCase 9", priority = 1)
    public void catalogPage() {
        logger = extent.createTest("Test Case 9");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

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
        loginPage.currentPage();

        Log.info("Validating if products in inventory");
        steps.info("Validating if products in inventory");
        result_bool = catalogPage.productsInInventory();
        if(result_bool)
            logger.pass("Products in inventory found");
        else
            logger.fail("Products in inventory not found");
        Assert.assertTrue(result_bool);

        Log.info("Validating if sort option in inventory");
        steps.info("Validating if sort option in inventory");
        result_bool = catalogPage.isSortOption();
        if(result_bool)
            logger.pass("Sort option found");
        else
            logger.fail("Sort option not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 10", priority = 2)
    public void productCardElements() {
        logger = extent.createTest("Test Case 10");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
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

        Log.info("Resetting app state");
        steps.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Validating if name in products' cards");
        steps.info("Validating if name in products' cards");
        result_bool = catalogPage.productsCardsHaveName();
        if(result_bool)
            logger.pass("Products names found");
        else
            logger.fail("Products names not found");
        Assert.assertTrue(result_bool);

        Log.info("Validating if image in products' cards");
        steps.info("Validating if image in products' cards");
        result_bool = catalogPage.productsCardsHaveImage();
        if(result_bool)
            logger.pass("Products images found");
        else
            logger.fail("Products images not found");
        Assert.assertTrue(result_bool);

        Log.info("Validating if description in products' cards");
        steps.info("Validating if description in products' cards");
        result_bool = catalogPage.productsCardsHaveDescription();
        if(result_bool)
            logger.pass("Products descriptions found");
        else
            logger.fail("Products descriptions not found");
        Assert.assertTrue(result_bool);

        Log.info("Validating if price in products' cards");
        steps.info("Validating if price in products' cards");
        result_bool = catalogPage.productsCardsHavePrice();
        if(result_bool)
            logger.pass("Products prices found");
        else
            logger.fail("Products prices not found");
        Assert.assertTrue(result_bool);

        Log.info("Validating if 'add to cart' button in products' cards");
        steps.info("Validating if 'add to cart' button in products' cards");
        result_bool = catalogPage.productsCardsHaveButton();
        if(result_bool)
            logger.pass("Products add to cart button found");
        else
            logger.fail("Products add to cart button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 11", priority = 3)
    public void productCardNavigation() {
        logger = extent.createTest("Test Case 11");
        LoginPage loginPage = new LoginPage(driver);
        steps = logger.createNode("Steps of test execution");
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        steps.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying if current page is "+productName+"'s page");
        steps.info("Verifying if current page is "+productName+"'s page");
        result_string = catalogPage.currentPage();
        if(productName.equals(result_string))
            logger.pass("Going to "+productName+"'s page by clicking its name");
        else
            logger.fail("Product name didn't lead to "+productName+"'s page");
        Assert.assertEquals(productName, result_string);
    }

    @Test(testName = "TestCase 12", priority = 4)
    public void productPageElements() {
        logger = extent.createTest("Test Case 12");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        steps.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's name");
        steps.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying presence of name in "+productName+"'s page");
        steps.info("Verifying presence of name in "+productName+"'s page");
        result_bool = catalogPage.isNameInPage();
        if(result_bool)
            logger.pass("Product name found");
        else
            logger.fail("Product name not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of image in "+productName+"'s page");
        steps.info("Verifying presence of image in "+productName+"'s page");
        result_bool = catalogPage.isImageInPage();
        if(result_bool)
            logger.pass("Product image found");
        else
            logger.fail("Product image not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of description in "+productName+"'s page");
        steps.info("Verifying presence of description in "+productName+"'s page");
        result_bool = catalogPage.isDescriptionInPage();
        if(result_bool)
            logger.pass("Product description found");
        else
            logger.fail("Product description not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of price in "+productName+"'s page");
        steps.info("Verifying presence of price in "+productName+"'s page");
        result_bool = catalogPage.isPriceInPage();
        if(result_bool)
            logger.pass("Product price found");
        else
            logger.fail("Product price not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'add to cart' button in "+productName+"'s page");
        steps.info("Verifying presence of 'add to cart' button in "+productName+"'s page");
        result_bool = catalogPage.isButtonInPage();
        if(result_bool)
            logger.pass("Product add to cart button found");
        else
            logger.fail("Product add to cart button not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'back to products' button in "+productName+"'s page");
        steps.info("Verifying presence of 'back to products' button in "+productName+"'s page");
        result_bool = catalogPage.isBackInPage();
        if(result_bool)
            logger.pass("Back to products button found");
        else
            logger.fail("Back to products button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 13", priority = 5)
    public void productPageNavigation() {
        logger = extent.createTest("Test Case 13");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in product's name");
        steps.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Clicking in 'back to products' button from "+productName+"'s page");
        steps.info("Clicking in 'back to products' button from "+productName+"'s page");
        catalogPage.toInventoryFromProduct();

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking back to products button");
        else
            logger.fail("Back to products button didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);
    }

    @Test(testName = "TestCase 14", priority = 6)
    public void sortNameAToZ() {
        logger = extent.createTest("Test Case 14");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_az");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's names in inventory BEFORE sorting");
        steps.info("Getting all product's names in inventory BEFORE sorting");
        listBeforeSortName = catalogPage.getNamesInInventory();

        Log.info("Sorting list gotten BEFORE sorting by name from a to z");
        steps.info("Sorting list gotten BEFORE sorting by name from a to z");
        Collections.sort(listBeforeSortName);

        Log.info("Sorting products in page by name from a to z");
        steps.info("Sorting products in page by name from a to z");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's names in inventory AFTER sorting");
        steps.info("Getting all product's names in inventory AFTER sorting");
        listAfterSortName = catalogPage.getNamesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        steps.info("Comparing both lists to check if have same sort order");
        if(listBeforeSortName.equals(listAfterSortName))
            logger.pass("Products sorted by name from a to z");
        else
            logger.fail("Products sorted in wrong order");
        Assert.assertEquals(listBeforeSortName,listAfterSortName);
    }

    @Test(testName = "TestCase 15", priority = 7)
    public void sortNameZToA() {
        logger = extent.createTest("Test Case 15");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_za");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's names in inventory BEFORE sorting");
        steps.info("Getting all product's names in inventory BEFORE sorting");
        listBeforeSortName = catalogPage.getNamesInInventory();

        Log.info("Sorting list gotten BEFORE sorting by name from z to a");
        steps.info("Sorting list gotten BEFORE sorting by name from z to a");
        Collections.sort(listBeforeSortName);
        Collections.reverse(listBeforeSortName);

        Log.info("Sorting products in page by name from z to a");
        steps.info("Sorting products in page by name from z to a");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's names in inventory AFTER sorting");
        steps.info("Getting all product's names in inventory AFTER sorting");
        listAfterSortName = catalogPage.getNamesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        steps.info("Comparing both lists to check if have same sort order");
        if(listBeforeSortName.equals(listAfterSortName))
            logger.pass("Products sorted by name from z to a");
        else
            logger.fail("Products sorted in wrong order");
        Assert.assertEquals(listBeforeSortName,listAfterSortName);
    }

    @Test(testName = "TestCase 16", priority = 8)
    public void sortPriceLowToHigh() {
        logger = extent.createTest("Test Case 16");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_lohi");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's prices in inventory BEFORE sorting");
        steps.info("Getting all product's prices in inventory BEFORE sorting");
        listBeforeSortPrice = catalogPage.getPricesInInventory();

        Log.info("Sorting list gotten BEFORE sorting by price from low to high");
        steps.info("Sorting list gotten BEFORE sorting by price from low to high");
        Collections.sort(listBeforeSortPrice);

        Log.info("Sorting products in page by price from low to high");
        steps.info("Sorting products in page by price from low to high");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's prices in inventory AFTER sorting");
        steps.info("Getting all product's prices in inventory AFTER sorting");
        listAfterSortPrice = catalogPage.getPricesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        steps.info("Comparing both lists to check if have same sort order");
        if(listBeforeSortName.equals(listAfterSortName))
            logger.pass("Products sorted by price from low to high");
        else
            logger.fail("Products sorted in wrong order");
        Assert.assertEquals(listBeforeSortPrice,listAfterSortPrice);
    }

    @Test(testName = "TestCase 17", priority = 9)
    public void sortPriceHighToLow() {
        logger = extent.createTest("Test Case 17");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        sortBy = props.getProperty("sort_hilo");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting all product's prices in inventory BEFORE sorting");
        steps.info("Getting all product's prices in inventory BEFORE sorting");
        listBeforeSortPrice = catalogPage.getPricesInInventory();

        Log.info("Sorting list gotten BEFORE sorting by price from high to low");
        steps.info("Sorting list gotten BEFORE sorting by price from high to low");
        Collections.sort(listBeforeSortPrice);
        Collections.reverse(listBeforeSortPrice);

        Log.info("Sorting products in page by price from high to low");
        steps.info("Sorting products in page by price from high to low");
        catalogPage.sortProducts(sortBy);

        Log.info("Getting all product's prices in inventory AFTER sorting");
        steps.info("Getting all product's prices in inventory AFTER sorting");
        listAfterSortPrice = catalogPage.getPricesInInventory();

        Log.info("Comparing both lists to check if have same sort order");
        steps.info("Comparing both lists to check if have same sort order");
        if(listBeforeSortName.equals(listAfterSortName))
            logger.pass("Products sorted by price from high to low");
        else
            logger.fail("Products sorted in wrong order");
        Assert.assertEquals(listBeforeSortPrice,listAfterSortPrice);
    }

    @Test(testName = "TestCase 18", priority = 10)
    public void productCardAddToCart() {
        logger = extent.createTest("Test Case 18");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        steps.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Getting how many products in cart BEFORE adding new product");
        steps.info("Getting how many products in cart BEFORE adding new product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Getting how many products in cart AFTER adding new product");
        steps.info("Getting how many products in cart AFTER adding new product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        steps.info("Verifying if "+productName+" added to cart");
        if(numberOfAfter > numberOfBefore)
            logger.pass("Product were added to cart");
        else
            logger.fail("Product were not added to cart");
        Assert.assertTrue(numberOfAfter > numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'remove'");
        steps.info("Verifying if "+productName+"'s button changed to 'remove'");
        result_bool = catalogPage.isRemove(testProduct);
        if(result_bool)
            logger.pass("Remove button appeared");
        else
            logger.fail("Remove button didn't appear");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 19", dependsOnMethods = "productCardAddToCart", priority = 11)
    public void productCardRemoveFromCart() {
        logger = extent.createTest("Test Case 19");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        steps.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        steps.info("Clicking in product's 'remove' button");
        productName = catalogPage.removeFromCart(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        steps.info("Getting how many products in cart AFTER removing product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" removed from cart");
        steps.info("Verifying if "+productName+" removed from cart");
        if(numberOfAfter < numberOfBefore)
            logger.pass("Product were removed from cart");
        else
            logger.fail("Product were not removed from cart");
        Assert.assertTrue(numberOfAfter < numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        steps.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        result_bool = catalogPage.isAddTo(testProduct);
        if(result_bool)
            logger.pass("Add to cart button appeared");
        else
            logger.fail("Add to cart button didn't appear");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 20", priority = 12)
    public void productPageAddToCart() {
        logger = extent.createTest("Test Case 20");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to "+URL);
        steps.info("Going to "+URL);
        driver.get(URL);

        Log.info("Logging into system");
        steps.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        steps.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's name");
        steps.info("Clicking in product's name");
        productName = catalogPage.toProductPage(testProduct);

        Log.info("Verifying if current page is "+productName+"'s page");
        steps.info("Verifying if current page is "+productName+"'s page");
        Assert.assertEquals(productName,catalogPage.currentPage());

        Log.info("Getting how many products in cart BEFORE adding new product");
        steps.info("Getting how many products in cart BEFORE adding new product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCartPage(testProduct);

        Log.info("Getting how many products in cart AFTER adding new product");
        steps.info("Getting how many products in cart AFTER adding new product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" added to cart");
        steps.info("Verifying if "+productName+" added to cart");
        if(numberOfAfter > numberOfBefore)
            logger.pass("Product were added to cart");
        else
            logger.fail("Product were not added to cart");
        Assert.assertTrue(numberOfAfter > numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'remove'");
        steps.info("Verifying if "+productName+"'s button changed to 'remove'");
        result_bool = catalogPage.isRemovePage(testProduct);
        if(result_bool)
            logger.pass("Remove button appeared");
        else
            logger.fail("Remove button didn't appear");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 21",dependsOnMethods = "productPageAddToCart", priority = 13)
    public void productPageRemoveFromCart() {
        logger = extent.createTest("Test Case 21");
        steps = logger.createNode("Steps of test execution");
        CatalogPage catalogPage = new CatalogPage(driver);

        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Verifying if current page is "+productName+"'s page");
        steps.info("Verifying if current page is "+productName+"'s page");
        Assert.assertEquals(productName,catalogPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        steps.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        steps.info("Clicking in product's 'remove' button");
        productName = catalogPage.removeFromCartPage(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        steps.info("Getting how many products in cart AFTER removing product");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" removed from cart");
        steps.info("Verifying if "+productName+" removed from cart");
        if(numberOfAfter < numberOfBefore)
            logger.pass("Product were removed from cart");
        else
            logger.fail("Product were not removed from cart");
        Assert.assertTrue(numberOfAfter < numberOfBefore);

        Log.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        steps.info("Verifying if "+productName+"'s button changed to 'add to cart'");
        result_bool = catalogPage.isAddToPage(testProduct);
        if(result_bool)
            logger.pass("Add to cart button appeared");
        else
            logger.fail("Add to cart button didn't appear");
        Assert.assertTrue(result_bool);
    }
}

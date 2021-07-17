package Tests;

import PageObjects.*;
import Utilities.Log;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    ExtentTest steps;
    String URL;
    String username;
    String password;
    String expectedPage;
    String testProduct;
    String productName;
    String firstName;
    String lastName;
    String zipCode;
    String errorMessage;
    int numberOfProducts;
    int numberOfBefore;
    int numberOfAfter;
    boolean result_bool;
    int result_int;
    String result_string;

    @Test(testName = "TestCase 22", priority = 1)
    public void catalogPage() {
        logger = extent.createTest("Test Case 22");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

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

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'cart list'");
        steps.info("Verifying presence of 'cart list'");
        result_bool = cartPage.isCartList();
        if(result_bool)
            logger.pass("Cart list found");
        else
            logger.fail("Cart list not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'continue shopping' button");
        steps.info("Verifying presence of 'continue shopping' button");
        result_bool = cartPage.isBackButton();
        if(result_bool)
            logger.pass("Continue shopping button found");
        else
            logger.fail("Continue shopping button not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of checkout button");
        steps.info("Verifying presence of checkout button");
        result_bool = cartPage.isCheckoutButton();
        if(result_bool)
            logger.pass("Checkout button found");
        else
            logger.fail("Checkout button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 23", priority = 2)
    public void cartListElements() {
        logger = extent.createTest("Test Case 23");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        Log.info("Getting how many products are in cart");
        steps.info("Getting how many products are in cart");
        numberOfProducts = catalogPage.productsInButtonCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of quantity in all "+numberOfProducts+" elements in cart list");
        steps.info("Verifying presence of quantity in all "+numberOfProducts+" elements in cart list");
        result_int = cartPage.howManyQuantityInCart();
        if(numberOfProducts == result_int)
            logger.pass("All products in cart list have quantity");
        else
            logger.fail("Different number of quantities and products");
        Assert.assertEquals(numberOfProducts, result_int);

        Log.info("Verifying presence of product's name in all "+numberOfProducts+" elements in cart list");
        steps.info("Verifying presence of product's name in all "+numberOfProducts+" elements in cart list");
        result_int = cartPage.howManyNamesInCart();
        if(numberOfProducts == result_int)
            logger.pass("All products in cart list have name");
        else
            logger.fail("Different number of names and products");
        Assert.assertEquals(numberOfProducts, result_int);

        Log.info("Verifying presence of product's description in all "+numberOfProducts+" elements in cart list");
        steps.info("Verifying presence of product's description in all "+numberOfProducts+" elements in cart list");
        result_int = cartPage.howManyDescriptionsInCart();
        if(numberOfProducts == result_int)
            logger.pass("All products un cart list have description");
        else
            logger.fail("Different number of descriptions and products");
        Assert.assertEquals(numberOfProducts, result_int);

        Log.info("Verifying presence of product's price in all "+numberOfProducts+" elements in cart list");
        steps.info("Verifying presence of product's price in all "+numberOfProducts+" elements in cart list");
        result_int = cartPage.howManyPricesInCart();
        if(numberOfProducts == result_int)
            logger.pass("All products un cart list have price");
        else
            logger.fail("Different number of prices and products");
        Assert.assertEquals(numberOfProducts, result_int);

        Log.info("Verifying presence of 'remove button' in all "+numberOfProducts+" elements in cart list");
        steps.info("Verifying presence of 'remove button' in all "+numberOfProducts+" elements in cart list");
        result_int = cartPage.howManyRemovesInCart();
        if(numberOfProducts == result_int)
            logger.pass("All products un cart list have remove button");
        else
            logger.fail("Different number of remove buttons and products");
        Assert.assertEquals(numberOfProducts, result_int);
    }

    @Test(testName = "TestCase 24", priority = 3)
    public void cartListRemove() {
        logger = extent.createTest("Test Case 24");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        steps.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        steps.info("Clicking in product's 'remove' button");
        productName = cartPage.removeInCart(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        steps.info("Getting how many products in cart AFTER removing product");
        numberOfProducts = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" removed from cart list");
        steps.info("Verifying if "+productName+" removed from cart list");
        if(numberOfProducts < numberOfBefore)
            logger.pass("Product were removed from cart");
        else
            logger.fail("Product were not removed from cart");
        Assert.assertTrue(numberOfProducts < numberOfBefore);
    }

    @Test(testName = "TestCase 25", priority = 4)
    public void continueShopping() {
        logger = extent.createTest("Test Case 25");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

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

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Clicking in 'continue shopping' button");
        steps.info("Clicking in 'continue shopping' button");
        cartPage.continueShopping();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is " + expectedPage);
        steps.info("Verifying if current page is " + expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking continue shopping button");
        else
            logger.fail("Continue shopping button didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);
    }

    @Test(testName = "TestCase 26", priority = 5)
    public void checkoutForm() {
        logger = extent.createTest("Test Case 26");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'first name' field");
        steps.info("Verifying presence of 'first name' field");
        result_bool = cartPage.isCheckoutFirstName();
        if(result_bool)
            logger.pass("First name field found");
        else
            logger.fail("First name field not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'last name' field");
        steps.info("Verifying presence of 'last name' field");
        result_bool = cartPage.isCheckoutLastName();
        if(result_bool)
            logger.pass("Last name field found");
        else
            logger.fail("Last name field not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'zip/postal code' field");
        steps.info("Verifying presence of 'zip/postal code' field");
        result_bool = cartPage.isCheckoutZip();
        if(result_bool)
            logger.pass("zip/postal code field found");
        else
            logger.fail("zip/postal code field not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'cancel checkout' button");
        steps.info("Verifying presence of 'cancel checkout' button");
        result_bool = cartPage.isCheckoutCancel();
        if(result_bool)
            logger.pass("cancel button found");
        else
            logger.fail("cancel button not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'continue checkout' button");
        steps.info("Verifying presence of 'continue checkout' button");
        result_bool = cartPage.isCheckoutContinue();
        if(result_bool)
            logger.pass("continue button found");
        else
            logger.fail("continue button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 27", priority = 6)
    public void checkoutIncompleteFirstName() {
        logger = extent.createTest("Test Case 27");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = "";
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");
        errorMessage = props.getProperty("error.missing_first_name");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing first name");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 28", priority = 7)
    public void checkoutIncompleteLastName() {
        logger = extent.createTest("Test Case 28");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = "";
        zipCode = props.getProperty("valid_zip");
        errorMessage = props.getProperty("error.missing_last_name");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing last name");steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 29", priority = 8)
    public void checkoutIncompleteZip() {
        logger = extent.createTest("Test Case 29");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = "";
        errorMessage = props.getProperty("error.missing_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing zip code");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 30", priority = 9)
    public void checkoutFailureFirstName() {
        logger = extent.createTest("Test Case 30");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("invalid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");
        errorMessage = props.getProperty("error.invalid_first_name");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid first name");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 31", priority = 10)
    public void checkoutFailureLastName() {
        logger = extent.createTest("Test Case 31");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("invalid_last_name");
        zipCode = props.getProperty("valid_zip");
        errorMessage = props.getProperty("error.invalid_last_name");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid first name");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 32", priority = 11)
    public void checkoutFailureZip() {
        logger = extent.createTest("Test Case 32");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("invalid_zip");
        errorMessage = props.getProperty("error.invalid_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid zip code");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Current page is "+expectedPage);
        else
            logger.fail("Current page is not "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Verifying if error message is: "+errorMessage);
        steps.info("Verifying if error message is: "+errorMessage);
        result_string = loginPage.errorMessage();
        if(errorMessage.equals(result_string))
            logger.pass("Error message is "+errorMessage);
        else
            logger.fail("Error message is not "+errorMessage);
        Assert.assertEquals(errorMessage, result_string);
    }

    @Test(testName = "TestCase 33", priority = 12)
    public void checkoutCancel() {
        logger = extent.createTest("Test Case 33");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE canceling checkout");
        steps.info("Getting how many products in cart BEFORE canceling checkout");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'cancel' button");
        steps.info("Clicking in 'cancel' button");
        cartPage.cancelCheckout();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking cancel button");
        else
            logger.fail("Cancel button didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Getting how many products in cart AFTER canceling checkout");
        steps.info("Getting how many products in cart AFTER canceling checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products were kept in cart");
        steps.info("Verifying if products were kept in cart");
        if(numberOfAfter == numberOfBefore)
            logger.pass("Products were kept in cart");
        else
            logger.fail("Number of products in cart changed");
        Assert.assertEquals(numberOfAfter, numberOfBefore);
    }

    @Test(testName = "TestCase 34", priority = 13)
    public void checkoutSuccess() {
        logger = extent.createTest("Test Case 34");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by filling up verification form successfully");
        else
            logger.fail("Filling up successfully verification form didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);
    }

    @Test(testName = "TestCase 35", priority = 14)
    public void checkoutOverview() {
        logger = extent.createTest("Test Case 35");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'cart list' element in checkout overview");
        steps.info("Verifying presence of 'cart list' element in checkout overview");
        result_bool = cartPage.isOverviewCartList();
        if(result_bool)
            logger.pass("Cart list found");
        else
            logger.fail("Cart list not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'summary info' element in checkout overview");
        steps.info("Verifying presence of 'summary info' element in checkout overview");
        result_bool = cartPage.isOverviewSummaryInfo();
        if(result_bool)
            logger.pass("Summary info found");
        else
            logger.fail("Summary info not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'cancel' button in checkout overview");
        steps.info("Verifying presence of 'cancel' button in checkout overview");
        result_bool = cartPage.isOverviewCancel();
        if(result_bool)
            logger.pass("Cancel button found");
        else
            logger.fail("Cancel button not found");
        Assert.assertTrue(result_bool);

        Log.info("Verifying presence of 'finish' button in checkout overview");
        steps.info("Verifying presence of 'finish' button in checkout overview");
        result_bool = cartPage.isOverviewFinish();
        if(result_bool)
            logger.pass("Finish button found");
        else
            logger.fail("Finish button not found");
        Assert.assertTrue(result_bool);
    }

    @Test(testName = "TestCase 36", priority = 15)
    public void finishBuyCancel() {
        logger = extent.createTest("Test Case 36");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE canceling buy");
        steps.info("Getting how many products in cart BEFORE canceling buy");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'cancel' button");
        steps.info("Clicking in 'cancel' button");
        cartPage.cancelOverview();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking cancel button");
        else
            logger.fail("Cancel button didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Getting how many products in cart AFTER canceling checkout");
        steps.info("Getting how many products in cart AFTER canceling checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products were kept in cart");
        steps.info("Verifying if products were kept in cart");
        if(numberOfAfter == numberOfBefore)
            logger.pass("Products were kept in cart");
        else
            logger.fail("Number of products in cart changed");
        Assert.assertEquals(numberOfAfter, numberOfBefore);
    }

    @Test(testName = "TestCase 37", priority = 16)
    public void finishBuySuccess() {
        logger = extent.createTest("Test Case 37");
        steps = logger.createNode("Steps of test execution");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);
        NavigationPage navigationPage = new NavigationPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");
        firstName = props.getProperty("valid_first_name");
        lastName = props.getProperty("valid_last_name");
        zipCode = props.getProperty("valid_zip");

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

        Log.info("Clicking in product's 'add to cart' button");
        steps.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        steps.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        steps.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        steps.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        steps.info("Filling up first name field with "+firstName);
        steps.info("Filling up last name field with "+lastName);
        steps.info("Filling up zip/postal code field with "+zipCode);
        steps.info("Clicking continue button");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE completing buy");
        steps.info("Getting how many products in cart BEFORE completing buy");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'finish' button");
        steps.info("Clicking in 'finish' button");
        cartPage.finishOverview();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is "+expectedPage);
        steps.info("Verifying if current page is "+expectedPage);
        result_string = loginPage.currentPage();
        if(expectedPage.equals(result_string))
            logger.pass("Going to "+expectedPage+" by clicking finish button");
        else
            logger.fail("Cancel button didn't lead to "+expectedPage);
        Assert.assertEquals(expectedPage, result_string);

        Log.info("Getting how many products in cart AFTER completing checkout");
        steps.info("Getting how many products in cart AFTER completing checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products deleted from cart");
        steps.info("Verifying if products deleted from cart");
        if(numberOfAfter < numberOfBefore)
            logger.pass("Products were deleted from cart");
        else
            logger.fail("Number of products didn't change");
        Assert.assertTrue(numberOfAfter < numberOfBefore);
    }
}

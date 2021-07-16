package Tests;

import PageObjects.*;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
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

    @Test(testName = "TestCase 22", priority = 1)
    public void catalogPage() {
        logger = extent.createTest("Test Case 22");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);

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

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'cart list'");
        cartPage.isCartList();

        Log.info("Verifying presence of 'continue shopping' button");
        cartPage.isBackButton();

        Log.info("Verifying presence of 'checkout' button");
        cartPage.isCheckoutButton();
    }

    @Test(testName = "TestCase 23", priority = 2)
    public void cartListElements() {
        logger = extent.createTest("Test Case 23");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        Log.info("Getting how many products are in cart");
        numberOfProducts = catalogPage.productsInButtonCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of quantity in all "+numberOfProducts+" elements in cart list");
        Assert.assertEquals(numberOfProducts,cartPage.howManyQuantityInCart());

        Log.info("Verifying presence of product's name in all "+numberOfProducts+" elements in cart list");
        Assert.assertEquals(numberOfProducts,cartPage.howManyNamesInCart());

        Log.info("Verifying presence of product's description in all "+numberOfProducts+" elements in cart list");
        Assert.assertEquals(numberOfProducts,cartPage.howManyDescriptionsInCart());

        Log.info("Verifying presence of product's price in all "+numberOfProducts+" elements in cart list");
        Assert.assertEquals(numberOfProducts,cartPage.howManyPricesInCart());

        Log.info("Verifying presence of 'remove button' in all "+numberOfProducts+" elements in cart list");
        Assert.assertEquals(numberOfProducts,cartPage.howManyRemovesInCart());
    }

    @Test(testName = "TestCase 24", priority = 3)
    public void cartListRemove() {
        logger = extent.createTest("Test Case 24");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is " + expectedPage);
        Assert.assertEquals(expectedPage, loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE removing product");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in product's 'remove' button");
        productName = cartPage.removeInCart(testProduct);

        Log.info("Getting how many products in cart AFTER removing product");
        numberOfProducts = catalogPage.productsInButtonCart();

        Log.info("Verifying if "+productName+" removed from cart list");
        Assert.assertTrue(numberOfProducts < numberOfBefore);
    }

    @Test(testName = "TestCase 25", priority = 4)
    public void continueShopping() {
        logger = extent.createTest("Test Case 25");
        LoginPage loginPage = new LoginPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);

        URL = props.getProperty("webapp");
        username = props.getProperty("valid_user");
        password = props.getProperty("pass");
        expectedPage = props.getProperty("inventory_page");
        testProduct = props.getProperty("product_one_id");

        Log.info("Going to " + URL);
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username, password);

        Log.info("Verifying if current page is " + expectedPage);
        Assert.assertEquals(expectedPage, loginPage.currentPage());

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is " + expectedPage);
        Assert.assertEquals(expectedPage, loginPage.currentPage());

        Log.info("Clicking in 'continue shopping' button");
        cartPage.continueShopping();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is " + expectedPage);
        Assert.assertEquals(expectedPage, loginPage.currentPage());
    }

    @Test(testName = "TestCase 26", priority = 5)
    public void checkoutForm() {
        logger = extent.createTest("Test Case 26");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'first name' field");
        cartPage.isCheckoutFirstName();

        Log.info("Verifying presence of 'last name' field");
        cartPage.isCheckoutLastName();

        Log.info("Verifying presence of 'zip/postal code' field");
        cartPage.isCheckoutZip();

        Log.info("Verifying presence of 'cancel checkout' button");
        cartPage.isCheckoutCancel();

        Log.info("Verifying presence of 'continue checkout' button");
        cartPage.isCheckoutContinue();
    }

    @Test(testName = "TestCase 27", priority = 6)
    public void checkoutIncompleteFirstName() {
        logger = extent.createTest("Test Case 27");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing first name");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 28", priority = 7)
    public void checkoutIncompleteLastName() {
        logger = extent.createTest("Test Case 28");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing last name");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 29", priority = 8)
    public void checkoutIncompleteZip() {
        logger = extent.createTest("Test Case 29");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form missing zip code");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 30", priority = 9)
    public void checkoutFailureFirstName() {
        logger = extent.createTest("Test Case 30");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid first name");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 31", priority = 10)
    public void checkoutFailureLastName() {
        logger = extent.createTest("Test Case 31");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid first name");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 32", priority = 11)
    public void checkoutFailureZip() {
        logger = extent.createTest("Test Case 32");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with invalid zip code");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if error message is: "+errorMessage);
        Assert.assertEquals(errorMessage,loginPage.errorMessage());
    }

    @Test(testName = "TestCase 33", priority = 12)
    public void checkoutCancel() {
        logger = extent.createTest("Test Case 33");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE canceling checkout");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'cancel' button");
        cartPage.cancelCheckout();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart AFTER canceling checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products kept in cart");
        Assert.assertEquals(numberOfAfter, numberOfBefore);
    }

    @Test(testName = "TestCase 34", priority = 13)
    public void checkoutSuccess() {
        logger = extent.createTest("Test Case 34");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());
    }

    @Test(testName = "TestCase 35", priority = 14)
    public void checkoutOverview() {
        logger = extent.createTest("Test Case 35");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying presence of 'cart list' element in checkout overview");
        cartPage.isOverviewCartList();

        Log.info("Verifying presence of 'summary info' element in checkout overview");
        cartPage.isOverviewSummaryInfo();

        Log.info("Verifying presence of 'cancel' button in checkout overview");
        cartPage.isOverviewCancel();

        Log.info("Verifying presence of 'finish' button in checkout overview");
        cartPage.isOverviewFinish();
    }

    @Test(testName = "TestCase 36", priority = 15)
    public void finishBuyCancel() {
        logger = extent.createTest("Test Case 36");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE canceling buy");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'cancel' button");
        cartPage.cancelOverview();

        expectedPage = props.getProperty("inventory_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart AFTER canceling checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products kept in cart");
        Assert.assertEquals(numberOfAfter, numberOfBefore);
    }

    @Test(testName = "TestCase 37", priority = 16)
    public void finishBuySuccess() {
        logger = extent.createTest("Test Case 37");
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
        driver.get(URL);

        Log.info("Logging into system");
        loginPage.fillLoginForm(username,password);

        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Resetting app state");
        navigationPage.clickOnBurgerMenu();
        navigationPage.clickOnResetOption();

        Log.info("Clicking in product's 'add to cart' button");
        productName = catalogPage.addToCart(testProduct);

        Log.info("Clicking in 'cart' button");
        cartPage.toCart();

        expectedPage = props.getProperty("cart_page");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Verifying if at least 1 product in cart list");
        numberOfProducts = catalogPage.productsInButtonCart();
        Assert.assertTrue(numberOfProducts > 0);

        Log.info("Clicking in 'checkout' button");
        cartPage.checkoutBuy();

        expectedPage = props.getProperty("checkout_form");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Filling up checkout form with valid data");
        cartPage.fillCheckoutForm(firstName,lastName,zipCode);

        expectedPage = props.getProperty("checkout_overview");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart BEFORE completing buy");
        numberOfBefore = catalogPage.productsInButtonCart();

        Log.info("Clicking in 'finish' button");
        cartPage.finishOverview();

        expectedPage = props.getProperty("checkout_finish");
        Log.info("Verifying if current page is "+expectedPage);
        Assert.assertEquals(expectedPage,loginPage.currentPage());

        Log.info("Getting how many products in cart AFTER completing checkout");
        numberOfAfter = catalogPage.productsInButtonCart();

        Log.info("Verifying if products kept in cart");
        Assert.assertTrue(numberOfAfter < numberOfBefore);
    }
}

package Tests.Frontend;

import PageObjects.*;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

import java.util.concurrent.TimeUnit;

public class SauceDemoTest extends BaseTest {

    /** Test Data **/
    String WEBAPP;
    String USER;
    String PASSWORD;
    String ERROR_MESSAGE;
    String PRODUCTS;
    String MESSAGE_INVENTORY_BUTTON;
    String SORTED_BY;
    String URL_ABOUT;
    String INDEX_PRODUCT;
    String INDEX_PRODUCT2;
    String NUM_NOTIFICATIONS_EXPECTED;
    String HEADER_CART;
    String EMPTY_NOTIFICATIONS;
    String HEADER_STEP_ONE;
    String NAME_STEP_ONE;
    String LAST_NAME_STEP_ONE;
    String POSTAL_CODE_STEP_ONE;
    String HEADER_STEP_TWO;
    String PAYMENT_INFO;
    String SHIPPING_INFO;
    String HEADER_CHECKOUT_COMPLETE;
    String URL_TWITTER;
    String URL_FACEBOOK;
    String URL_LINKEDIN;

    /**Auxiliary Constants**/
    int i;
    JSONArray arrayProducts;

    @Test
    public void successLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.correct");

        logger = extent.createTest("Success login");
        Log.info("Start of success login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert "+USER );
        loginPage.insertUser(USER);
        logger.info("Password insert "+PASSWORD);
        loginPage.insertPassword(PASSWORD);
        logger.info("Click login button");
        loginPage.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Validate Inventory Container");
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of success login Case");
    }

    //This method is to shows correctly TestExecutionReport.html and is used by other test methods.
    public void successLogin(Boolean isRequired){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.correct");

        Log.info("Start of success login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert "+USER);
        loginPage.insertUser(USER);
        logger.info("Password insert "+PASSWORD);
        loginPage.insertPassword(PASSWORD);
        logger.info("Click login button");
        loginPage.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Validate Inventory Container");
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of success login Case");
    }

    @Test
    public void failedUserLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.incorrect");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        logger = extent.createTest("Login user fail");
        Log.info("Start of failed user login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert: "+USER);
        loginPage.insertUser(USER);
        logger.info("Password insert: "+PASSWORD);
        loginPage.insertPassword(PASSWORD);
        logger.info("Click login button");
        loginPage.clickLoginButton();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed user login Case");
    }

    @Test
    public void failedPasswordLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.incorrect");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        logger = extent.createTest("Login password fail");
        Log.info("Start of failed password login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert "+USER);
        loginPage.insertUser(USER);
        logger.info("User password "+PASSWORD);
        loginPage.insertPassword(PASSWORD);
        logger.info("Click login button");
        loginPage.clickLoginButton();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed password login Case");
    }

    @Test
    public void lockedLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.locked");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.lockedError");

        logger = extent.createTest("Login locked");
        Log.info("Start of locked login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert "+USER);
        loginPage.insertUser(USER);
        logger.info("User password "+PASSWORD);
        loginPage.insertPassword(PASSWORD);
        logger.info("Click login button");
        loginPage.clickLoginButton();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of locked login Case");
    }

    @Test
    public void emptyUserLogin(){
        WEBAPP = props.getProperty("webapp");
        ERROR_MESSAGE = props.getProperty("login.emptyUserError");

        logger = extent.createTest("Login empty user");
        Log.info("Start of empty user login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("Click login button");
        loginPage.clickLoginButton();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of empty user login Case");
    }

    @Test
    public void emptyPasswordLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        ERROR_MESSAGE = props.getProperty("login.emptyPasswordError");

        logger = extent.createTest("Login empty password");
        Log.info("Start of empty password login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validation login logo");
        Assert.assertTrue(loginPage.validateLoginLogo());
        logger.info("User insert "+USER);
        loginPage.insertUser(USER);
        logger.info("Click login button");
        loginPage.clickLoginButton();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of empty password login Case");
    }

    @Test
    public void sortedAtoZ(){
        logger = extent.createTest("Sorted A to Z");
        successLogin(true);

        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueAZ");

        Log.info("Start of sorted A to Z Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Sorted products by A to Z");
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted A to Z Case");
    }

    @Test
    public void sortedZtoA(){
        logger = extent.createTest("Sorted Z to A");
        successLogin(true);
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueZA");

        Log.info("Start of sorted Z to A Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Sorted products by A to Z");
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted Z to A Case");
    }

    @Test
    public void sortedPriceLoToHi(){
        logger = extent.createTest("Sorted Low price to High price");
        successLogin(true);
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueLoHi");

        Log.info("Start of sorted price low to high Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Sorted products by Low price to High price");
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price low to high Case");
    }

    @Test
    public void sortedPriceHiToLo(){
        logger = extent.createTest("Sorted High price to Low price");
        successLogin(true);
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueHiLo");

        Log.info("Start of sorted price high to low Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Sorted products by High price to Low price");
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price high to low Case");
    }

    @Test
    public void visualizeMenu(){
        logger = extent.createTest("Validate menu list");
        successLogin(true);
        URL_ABOUT = props.getProperty("menu.urlAbout");

        Log.info("Start of visualize Menu Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on burger button");
        menuList.clickBurgerButton();
        logger.info("Validate if menu is displayed");
        Assert.assertTrue(menuList.menuIsDisplayed());
        logger.info("Click on All Items button");
        menuList.clickAllItems();
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Validate if Inventory page is displayed");
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        logger.info("Validate if about url is correct");
        menuList.checkURLAbout(URL_ABOUT);
        logger.info("Click on Logout button");
        menuList.clickLogout();
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Validate if Login page is displayed");
        Assert.assertTrue(loginPage.validateLoginLogo());
        Log.info("End of visualize Menu Case");
    }

    @Test
    public void resetApp(){
        logger = extent.createTest("Validate reset app button");
        successLogin(true);
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonRemove");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        INDEX_PRODUCT2 = props.getProperty("inventory.indexProduct2");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedResetAppCase");
        EMPTY_NOTIFICATIONS = props.getProperty("menu.emptyNotificationsCart");

        Log.info("Start of reset app Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on burger button");
        menuList.clickBurgerButton();
        logger.info("Validate if menu is displayed");
        Assert.assertTrue(menuList.menuIsDisplayed());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Click on burger cross button");
        menuList.clickBurgerCrossButton();
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Adding into cart two products");
        inventoryPage.addToCarElement(INDEX_PRODUCT, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.addToCarElement(INDEX_PRODUCT2, MESSAGE_INVENTORY_BUTTON);
        logger.info("Validate if menu is closed");
        Assert.assertFalse(menuList.menuIsDisplayed());
        logger.info("Click on burger button");
        menuList.clickBurgerButton();
        logger.info("Validate if notifications are "+NUM_NOTIFICATIONS_EXPECTED);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        logger.info("Click on reset app button");
        menuList.clickResetApp();
        logger.info("Validate if notifications aren't displayed");
        menuList.checkNotificationsCart(EMPTY_NOTIFICATIONS);
        Log.info("End of reset app Case");
    }

    @Test
    public void productIntoCart(){
        logger = extent.createTest("Adding product into cart");
        successLogin(true);
        String SwitchButtonMessage = props.getProperty("inventory.messageButtonRemove");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        PRODUCTS = props.getProperty("inventory.productsList");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedAddCar");
        HEADER_CART = props.getProperty("cart.title");
        String headerCart;

        Log.info("Start of product into cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Adding product into cart and check change of button");
        String product = inventoryPage.addToCarElement(INDEX_PRODUCT, SwitchButtonMessage);
        logger.info("Checking product information and obtaining product position");
        i = inventoryPage.checkProductInformation(arrayProducts, product);
        MenuList menuList = new MenuList(driver);
        logger.info("Validate if notifications are "+NUM_NOTIFICATIONS_EXPECTED);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Getting cart page header");
        headerCart = cartPage.CartHeader();
        logger.info("Validate cart page header");
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        logger.info("Validate if the buttons are displayed on the cart page");
        Assert.assertTrue(cartPage.cartButtonsDisplayed());
        logger.info("Validate if the selected product is on the cart page");
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),SwitchButtonMessage);
        logger.info("Click on continue shopping button");
        cartPage.clickButtonContinueShopping();
        logger.info("Validate if notifications are "+NUM_NOTIFICATIONS_EXPECTED);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        Log.info("End of product into cart Case");
    }

    //This method is to shows correctly TestExecutionReport.html and is used by other test methods.
    public void productIntoCart(boolean isRequired){
        successLogin(true);
        String SwitchButtonMessage = props.getProperty("inventory.messageButtonRemove");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        PRODUCTS = props.getProperty("inventory.productsList");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedAddCar");
        HEADER_CART = props.getProperty("cart.title");
        String headerCart;

        Log.info("Start of product into cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Get products and check if are displayed correctly on the Inventory page");
        arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        logger.info("Adding product into cart and check change of button");
        String product = inventoryPage.addToCarElement(INDEX_PRODUCT, SwitchButtonMessage);
        logger.info("Checking product information and obtaining product position");
        i = inventoryPage.checkProductInformation(arrayProducts, product);
        MenuList menuList = new MenuList(driver);
        logger.info("Validate if notifications are "+NUM_NOTIFICATIONS_EXPECTED);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Getting cart page header");
        headerCart = cartPage.CartHeader();
        logger.info("Validate cart page header");
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        logger.info("Validate if the buttons are displayed on the cart page");
        Assert.assertTrue(cartPage.cartButtonsDisplayed());
        logger.info("Validate if the selected product is still on the cart page");
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),SwitchButtonMessage);
        logger.info("Click on continue shopping button");
        cartPage.clickButtonContinueShopping();
        logger.info("Validate if notifications are "+NUM_NOTIFICATIONS_EXPECTED);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        Log.info("End of product into cart Case");
    }

    @Test
    public void productOutCart(){
        logger = extent.createTest("Remove product from cart");
        productIntoCart(true);
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        EMPTY_NOTIFICATIONS = props.getProperty("menu.emptyNotificationsCart");

        Log.info("Start of product out cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Removing product from cart and check change of button");
        String product = inventoryPage.removeCarElement(INDEX_PRODUCT, MESSAGE_INVENTORY_BUTTON);
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        logger.info("Validate if notifications aren't displayed");
        menuList.checkNotificationsCart(EMPTY_NOTIFICATIONS);
        CartPage cartPage = new CartPage(driver);
        logger.info("Validate if the selected product is removed from the cart page");
        Assert.assertTrue(cartPage.verifyProductOutCar(product));
        Log.info("End of product out cart Case");
    }

    @Test
    public void checkOutStepOneCancelPurchase(){
        logger = extent.createTest("Checkout-Step-One Cancel Purchase");
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        HEADER_CART = props.getProperty("cart.title");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonRemove");
        String headerStepOne;
        String headerCart;

        Log.info("Start of cancel purchase in step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Click on cancel button");
        stepOnePage.clickOnButtonCancel();
        logger.info("Getting cart page header");
        headerCart = cartPage.CartHeader();
        logger.info("Validate cart page header");
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        logger.info("Validate if the selected product is still on the cart page");
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),MESSAGE_INVENTORY_BUTTON);
        Log.info("End of cancel purchase in step one Case");
    }

    @Test
    public void checkOutStepOneNameError(){
        logger = extent.createTest("Checkout-Step-One name error");
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorName");
        String headerStepOne;

        Log.info("Start of name error in step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Click on continue button");
        stepOnePage.clickOnButtonContinue();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of name error in step one Case");
    }

    @Test
    public void checkOutStepOneLastNameError(){
        logger = extent.createTest("Checkout-Step-One lastname error");
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorLastName");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        String headerStepOne;

        Log.info("Start of last name error in step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Insert name in input");
        stepOnePage.insertName(NAME_STEP_ONE);
        logger.info("Click on continue button");
        stepOnePage.clickOnButtonContinue();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of last name error in step one Case");
    }

    @Test
    public void checkOutStepOnePostalCodeError(){
        logger = extent.createTest("Checkout-Step-One postal code error");
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorPostalCode");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        LAST_NAME_STEP_ONE = props.getProperty("stepOne.lastName");
        String headerStepOne;

        Log.info("Start of postal code error in step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Insert name in input");
        stepOnePage.insertName(NAME_STEP_ONE);
        logger.info("Insert lastname in input");
        stepOnePage.insertLastName(LAST_NAME_STEP_ONE);
        logger.info("Click on continue button");
        stepOnePage.clickOnButtonContinue();
        logger.info("Validate error message: "+ ERROR_MESSAGE);
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of error last name in step one Case");
    }

    @Test
    public void checkOutStepOneContinue(){
        logger = extent.createTest("Checkout-Step-One continue purchase");
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        HEADER_STEP_TWO = props.getProperty("stepTwo.title");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        LAST_NAME_STEP_ONE = props.getProperty("stepOne.lastName");
        POSTAL_CODE_STEP_ONE = props.getProperty("stepOne.postalCode");
        String headerStepOne;
        String headerStepTwo;

        Log.info("Start of continue step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Insert name in input");
        stepOnePage.insertName(NAME_STEP_ONE);
        logger.info("Insert lastname in input");
        stepOnePage.insertLastName(LAST_NAME_STEP_ONE);
        logger.info("Insert postal code in input");
        stepOnePage.insertPostalCode(POSTAL_CODE_STEP_ONE);
        logger.info("Click on continue button");
        stepOnePage.clickOnButtonContinue();
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        headerStepTwo = stepTwoPage.StepTwoHeader();
        Assert.assertEquals(HEADER_STEP_TWO.toUpperCase(),headerStepTwo.toUpperCase());
        Log.info("End of continue step one Case");
    }

    //This method is to shows correctly TestExecutionReport.html and is used by other test methods.
    public void checkOutStepOneContinue(boolean isRequired){
        productIntoCart(true);
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        HEADER_STEP_TWO = props.getProperty("stepTwo.title");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        LAST_NAME_STEP_ONE = props.getProperty("stepOne.lastName");
        POSTAL_CODE_STEP_ONE = props.getProperty("stepOne.postalCode");
        String headerStepOne;
        String headerStepTwo;

        Log.info("Start of continue step one Case");
        MenuList menuList = new MenuList(driver);
        logger.info("Click on cart icon");
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        logger.info("Click on checkout button");
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        logger.info("Getting step-one page header");
        headerStepOne = stepOnePage.StepOneHeader();
        logger.info("Validate step-one page header");
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        logger.info("Insert name in input");
        stepOnePage.insertName(NAME_STEP_ONE);
        logger.info("Insert lastname in input");
        stepOnePage.insertLastName(LAST_NAME_STEP_ONE);
        logger.info("Insert postal code in input");
        stepOnePage.insertPostalCode(POSTAL_CODE_STEP_ONE);
        logger.info("Click on continue button");
        stepOnePage.clickOnButtonContinue();
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        logger.info("Getting step-two page header");
        headerStepTwo = stepTwoPage.StepTwoHeader();
        logger.info("Validate step-two page header");
        Assert.assertEquals(HEADER_STEP_TWO.toUpperCase(),headerStepTwo.toUpperCase());
        Log.info("End of continue step one Case");
    }

    @Test
    public void checkOutStepTwoCancel(){
        logger = extent.createTest("Checkout-Step-Two cancel purchase");
        checkOutStepOneContinue(true);

        PAYMENT_INFO = props.getProperty("stepTwo.paymentInfo");
        SHIPPING_INFO = props.getProperty("stepTwo.shippingInfo");

        Log.info("Start of cancel step two Case");
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        logger.info("Validate if product that was added to cart is still into purchase");
        stepTwoPage.verifyProductIntoPurchase(arrayProducts.getJSONObject(i));
        logger.info("Validate if payment info is correct");
        stepTwoPage.validatePaymentInfo(PAYMENT_INFO);
        logger.info("Validate if shipping info is correct");
        stepTwoPage.validateShippingInfo(SHIPPING_INFO);
        logger.info("Get and validate if values prices are correct");
        stepTwoPage.validateCorrectValuesPrice(arrayProducts.getJSONObject(i));
        logger.info("Click on cancel button");
        stepTwoPage.clickOnButtonCancel();
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Validate Inventory Container");
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of cancel step two Case");
    }

    @Test
    public void checkoutComplete(){
        logger = extent.createTest("Checkout-Step-Two continue purchase");
        checkOutStepOneContinue(true);

        PAYMENT_INFO = props.getProperty("stepTwo.paymentInfo");
        SHIPPING_INFO = props.getProperty("stepTwo.shippingInfo");
        HEADER_CHECKOUT_COMPLETE = props.getProperty("checkoutComplete.title");
        EMPTY_NOTIFICATIONS = props.getProperty("menu.emptyNotificationsCart");
        String headerCheckoutComplete;

        Log.info("Start of checkout complete Case");
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        logger.info("Validate if product that was added to cart is still into purchase");
        stepTwoPage.verifyProductIntoPurchase(arrayProducts.getJSONObject(i));
        logger.info("Validate if payment info is correct");
        stepTwoPage.validatePaymentInfo(PAYMENT_INFO);
        logger.info("Validate if shipping info is correct");
        stepTwoPage.validateShippingInfo(SHIPPING_INFO);
        logger.info("Get and validate if values prices are correct");
        stepTwoPage.validateCorrectValuesPrice(arrayProducts.getJSONObject(i));
        logger.info("Click on finish button");
        stepTwoPage.clickOnButtonFinish();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        logger.info("Getting checkout-complete page header");
        headerCheckoutComplete = checkoutCompletePage.checkoutComHeader();
        logger.info("Validate checkout-complete page header");
        Assert.assertEquals(HEADER_CHECKOUT_COMPLETE.toUpperCase(),headerCheckoutComplete.toUpperCase());
        logger.info("Validate if information and buttons are displayed");
        Assert.assertTrue(checkoutCompletePage.infoAndButtonIsDisplayed());
        logger.info("Click on back home button");
        checkoutCompletePage.clickBackHomeButton();
        InventoryPage inventoryPage = new InventoryPage(driver);
        logger.info("Validate Inventory Container");
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        MenuList menuList = new MenuList(driver);
        logger.info("Validate if notifications aren't displayed");
        menuList.checkNotificationsCart(EMPTY_NOTIFICATIONS);
        Log.info("End of checkout complete Case");
    }

    @Test
    public void socialMediaLinks(){
        logger = extent.createTest("Social Media Links");
        successLogin(true);

        URL_TWITTER = props.getProperty("footer.urlTwitter");
        URL_FACEBOOK = props.getProperty("footer.urlFace");
        URL_LINKEDIN = props.getProperty("footer.urlLinkedin");

        Footer footer = new Footer(driver);
        logger.info("Validate if twitter url is correct");
        footer.checkURLTwitter(URL_TWITTER);
        logger.info("Validate if facebook url is correct");
        footer.checkURLFacebook(URL_FACEBOOK);
        logger.info("Validate if linkedin url is correct");
        footer.checkURLLinkedin(URL_LINKEDIN);
    }

}

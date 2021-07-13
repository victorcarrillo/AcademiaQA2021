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

    /**Auxiliary Constants**/
    int i;
    JSONArray arrayProducts;

    @Test
    public void successLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.correct");

        Log.info("Start of success login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertUser(USER);
        loginPage.insertPassword(PASSWORD);
        loginPage.clickLoginButton();

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of success login Case");
    }

    @Test
    public void failedUserLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.incorrect");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        Log.info("Start of failed user login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertUser(USER);
        loginPage.insertPassword(PASSWORD);
        loginPage.clickLoginButton();

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed user login Case");
    }

    @Test
    public void failedPasswordLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.incorrect");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        Log.info("Start of failed password login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertUser(USER);
        loginPage.insertPassword(PASSWORD);
        loginPage.clickLoginButton();

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed password login Case");
    }

    @Test
    public void lockedLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.locked");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.lockedError");

        Log.info("Start of locked login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertUser(USER);
        loginPage.insertPassword(PASSWORD);
        loginPage.clickLoginButton();

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of locked login Case");
    }

    @Test
    public void emptyUserLogin(){
        WEBAPP = props.getProperty("webapp");
        ERROR_MESSAGE = props.getProperty("login.emptyUserError");

        Log.info("Start of empty user login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.clickLoginButton();

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of empty user login Case");
    }

    @Test
    public void emptyPasswordLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        ERROR_MESSAGE = props.getProperty("login.emptyPasswordError");

        Log.info("Start of empty password login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertUser(USER);
        loginPage.clickLoginButton();

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of empty password login Case");
    }

    @Test
    public void sortedAtoZ(){
        successLogin();
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueAZ");

        Log.info("Start of sorted A to Z Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted A to Z Case");
    }

    @Test
    public void sortedZtoA(){
        successLogin();
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueZA");

        Log.info("Start of sorted Z to A Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted Z to A Case");
    }

    @Test
    public void sortedPriceLoToHi(){
        successLogin();
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueLoHi");

        Log.info("Start of sorted price low to high Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price low to high Case");
    }

    @Test
    public void sortedPriceHiToLo(){
        successLogin();
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueHiLo");

        Log.info("Start of sorted price high  to low Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price high to low Case");
    }

    @Test
    public void visualizeMenu(){
        successLogin();
        URL_ABOUT = props.getProperty("menu.urlAbout");

        Log.info("Start of visualize Menu Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickBurgerButton();
        Assert.assertTrue(menuList.menuIsDisplayed());
        menuList.clickAllItems();
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        menuList.checkURLAbout(URL_ABOUT);
        menuList.clickLogout();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        Log.info("End of visualize Menu Case");
    }

    @Test
    public void resetApp(){
        successLogin();
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonRemove");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        INDEX_PRODUCT2 = props.getProperty("inventory.indexProduct2");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedResetAppCase");
        EMPTY_NOTIFICATIONS = props.getProperty("menu.emptyNotificationsCart");

        Log.info("Start of reset app Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickBurgerButton();
        Assert.assertTrue(menuList.menuIsDisplayed());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        menuList.clickBurgerCrossButton();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCarElement(INDEX_PRODUCT, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.addToCarElement(INDEX_PRODUCT2, MESSAGE_INVENTORY_BUTTON);
        Assert.assertFalse(menuList.menuIsDisplayed());
        menuList.clickBurgerButton();
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        menuList.clickResetApp();
        menuList.checkNotificationsCart(EMPTY_NOTIFICATIONS);
        Log.info("End of reset app Case");
    }

    @Test
    public void productIntoCart(){
        successLogin();
        String SwitchButtonMessage = props.getProperty("inventory.messageButtonRemove");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        PRODUCTS = props.getProperty("inventory.productsList");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedAddCar");
        HEADER_CART = props.getProperty("cart.title");
        String headerCart;

        Log.info("Start of product into cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        String product = inventoryPage.addToCarElement(INDEX_PRODUCT, SwitchButtonMessage);
        i = inventoryPage.checkProductInformation(arrayProducts, product);
        MenuList menuList = new MenuList(driver);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        headerCart = cartPage.CartHeader();
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        Assert.assertTrue(cartPage.cartButtonsDisplayed());
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),SwitchButtonMessage);
        cartPage.clickButtonContinueShopping();
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        Log.info("End of product into cart Case");
    }

    @Test
    public void productOutCart(){
        productIntoCart();
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        EMPTY_NOTIFICATIONS = props.getProperty("menu.emptyNotificationsCart");

        Log.info("Start of product out cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        String product = inventoryPage.removeCarElement(INDEX_PRODUCT, MESSAGE_INVENTORY_BUTTON);
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        menuList.checkNotificationsCart(EMPTY_NOTIFICATIONS);
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.verifyProductOutCar(product));
        Log.info("End of product out cart Case");
    }

    @Test
    public void checkOutStepOneCancelPurchase(){
        productIntoCart();
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        HEADER_CART = props.getProperty("cart.title");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonRemove");
        String headerStepOne;
        String headerCart;

        Log.info("Start of cancel purchase in step one Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        headerStepOne = stepOnePage.StepOneHeader();
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        stepOnePage.clickOnButtonCancel();
        headerCart = cartPage.CartHeader();
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),MESSAGE_INVENTORY_BUTTON);
        Log.info("End of cancel purchase in step one Case");
    }

    @Test
    public void checkOutStepOneNameError(){
        productIntoCart();
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorName");
        String headerStepOne;

        Log.info("Start of name error in step one Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        headerStepOne = stepOnePage.StepOneHeader();
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        stepOnePage.clickOnButtonContinue();
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of name error in step one Case");
    }

    @Test
    public void checkOutStepOneLastNameError(){
        productIntoCart();
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorLastName");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        String headerStepOne;

        Log.info("Start of last name error in step one Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        headerStepOne = stepOnePage.StepOneHeader();
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        stepOnePage.insertName(NAME_STEP_ONE);
        stepOnePage.clickOnButtonContinue();
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of last name error in step one Case");
    }

    @Test
    public void checkOutStepOnePostalCodeError(){
        productIntoCart();
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        ERROR_MESSAGE = props.getProperty("stepOne.messageErrorPostalCode");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        LAST_NAME_STEP_ONE = props.getProperty("stepOne.lastName");
        String headerStepOne;

        Log.info("Start of postal code error in step one Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        headerStepOne = stepOnePage.StepOneHeader();
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        stepOnePage.insertName(NAME_STEP_ONE);
        stepOnePage.insertLastName(LAST_NAME_STEP_ONE);
        stepOnePage.clickOnButtonContinue();
        stepOnePage.validateErrorMessage(ERROR_MESSAGE);
        Log.info("End of error last name in step one Case");
    }

    @Test
    public void checkOutStepOneContinue(){
        productIntoCart();
        HEADER_STEP_ONE = props.getProperty("stepOne.title");
        HEADER_STEP_TWO = props.getProperty("stepTwo.title");
        NAME_STEP_ONE = props.getProperty("stepOne.name");
        LAST_NAME_STEP_ONE = props.getProperty("stepOne.lastName");
        POSTAL_CODE_STEP_ONE = props.getProperty("stepOne.postalCode");
        String headerStepOne;
        String headerStepTwo;

        Log.info("Start of continue step one Case");
        MenuList menuList = new MenuList(driver);
        menuList.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickButtonCheckout();
        StepOnePage stepOnePage = new StepOnePage(driver);
        headerStepOne = stepOnePage.StepOneHeader();
        Assert.assertEquals(HEADER_STEP_ONE.toUpperCase(),headerStepOne.toUpperCase());
        stepOnePage.insertName(NAME_STEP_ONE);
        stepOnePage.insertLastName(LAST_NAME_STEP_ONE);
        stepOnePage.insertPostalCode(POSTAL_CODE_STEP_ONE);
        stepOnePage.clickOnButtonContinue();
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        headerStepTwo = stepTwoPage.StepTwoHeader();
        Assert.assertEquals(HEADER_STEP_TWO.toUpperCase(),headerStepTwo.toUpperCase());
        Log.info("End of continue step one Case");
    }

    @Test
    public void checkOutStepTwoCancel(){
        checkOutStepOneContinue();

        PAYMENT_INFO = props.getProperty("stepTwo.paymentInfo");
        SHIPPING_INFO = props.getProperty("stepTwo.shippingInfo");

        Log.info("Start of cancel step two Case");
        StepTwoPage stepTwoPage = new StepTwoPage(driver);
        stepTwoPage.verifyProductIntoPurchase(arrayProducts.getJSONObject(i));
        stepTwoPage.validatePaymentInfo(PAYMENT_INFO);
        stepTwoPage.validateShippingInfo(SHIPPING_INFO);
        stepTwoPage.validateCorrectValuesPrice(arrayProducts.getJSONObject(i));
        stepTwoPage.clickOnButtonCancel();
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of cancel step two Case");
    }


}

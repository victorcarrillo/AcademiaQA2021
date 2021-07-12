package Tests.Frontend;

import PageObjects.*;
import org.json.JSONArray;
import org.json.JSONObject;
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
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueAZ");

        successLogin();
        Log.info("Start of sorted A to Z Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted A to Z Case");
    }

    @Test
    public void sortedZtoA(){
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueZA");

        successLogin();
        Log.info("Start of sorted Z to A Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of sorted Z to A Case");
    }

    @Test
    public void sortedPriceLoToHi(){
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueLoHi");

        successLogin();
        Log.info("Start of sorted price low to high Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price low to high Case");
    }

    @Test
    public void sortedPriceHiToLo(){
        PRODUCTS = props.getProperty("inventory.productsList");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        SORTED_BY = props.getProperty("inventory.sortValueHiLo");

        successLogin();
        Log.info("Start of sorted price high  to low Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        inventoryPage.sortProducts(SORTED_BY, arrayProducts);
        Log.info("End of price high to low Case");
    }

    @Test
    public void visualizeMenu(){
        URL_ABOUT = props.getProperty("menu.urlAbout");

        successLogin();
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
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonRemove");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        INDEX_PRODUCT2 = props.getProperty("inventory.indexProduct2");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedResetAppCase");

        successLogin();
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
        menuList.checkNotificationsCart("");
        Log.info("End of reset app Case");
    }

    @Test
    public void productIntoCart(){
        String SwitchButtonMessage = props.getProperty("inventory.messageButtonRemove");
        MESSAGE_INVENTORY_BUTTON = props.getProperty("inventory.messageButtonAdd");
        PRODUCTS = props.getProperty("inventory.productsList");
        INDEX_PRODUCT = props.getProperty("inventory.indexProduct");
        NUM_NOTIFICATIONS_EXPECTED = props.getProperty("menu.numProductsAddedAddCar");
        HEADER_CART = props.getProperty("cart.title");
        int i;
        String headerCart;

        successLogin();
        Log.info("Start of product into cart Case");
        InventoryPage inventoryPage = new InventoryPage(driver);
        JSONArray arrayProducts = inventoryPage.checkProductsContainerInInventory(PRODUCTS, MESSAGE_INVENTORY_BUTTON);
        String product = inventoryPage.addToCarElement(INDEX_PRODUCT, SwitchButtonMessage);
        i = inventoryPage.checkProductInformation(arrayProducts, product);
        MenuList menuList = new MenuList(driver);
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        CartPage cartPage = new CartPage(driver);
        menuList.clickCartIcon();
        headerCart = cartPage.CartHeader();
        Assert.assertEquals(HEADER_CART.toUpperCase(),headerCart.toUpperCase());
        Assert.assertTrue(cartPage.cartButtonsDisplayed());
        cartPage.verifyProductIntoCar(arrayProducts.getJSONObject(i),SwitchButtonMessage);
        cartPage.clickButtonContinueShopping();
        menuList.checkNotificationsCart(NUM_NOTIFICATIONS_EXPECTED);
        Log.info("End of product into cart Case");
    }


}

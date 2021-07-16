package Tests;

import PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

import java.util.List;

public class PurchaseFlowTests extends BaseTest{
    String URL;
    String username;
    String password;
    List<WebElement> products;
    List<WebElement> prices;

    @Test
    public void checkoutInfoTest(){
        Log.startLog("Caso de prueba 21: verificacion de formulario de informacion de envio");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.goToCheckout());

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        Assert.assertTrue(purchaseFlowPages.verifyOverview());
    }

    @Test
    public void verifyTotalTest(){
        Log.startLog("Caso de prueba 22: verificacion de precio total de compra");

        List<Float> pricesFloat;
        float expectedPrice;

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        prices = inventoryPage.retrieveProductsPrices();

        pricesFloat = convertToFloat(prices);

        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        inventoryPage.selectProduct(products, 5);

        expectedPrice = pricesFloat.get(0) + pricesFloat.get(4);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");


        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        shoppingCartPage.goToCheckout();

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        Assert.assertTrue(purchaseFlowPages.verifyTotalPrice(expectedPrice));
    }

    @Test
    public void verifySuccesfulPurchaseTest(){
        Log.startLog("Caso de prueba 23: verificacion de compra exitosa");

        List<Float> pricesFloat;
        float expectedPrice;

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        prices = inventoryPage.retrieveProductsPrices();

        pricesFloat = convertToFloat(prices);

        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        inventoryPage.selectProduct(products, 5);

        expectedPrice = pricesFloat.get(0) + pricesFloat.get(4);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");


        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        shoppingCartPage.goToCheckout();

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        Assert.assertTrue(purchaseFlowPages.verifyTotalPrice(expectedPrice));
        Assert.assertTrue(purchaseFlowPages.goToFinish());
        Assert.assertTrue(purchaseFlowPages.verifyPurchase());
    }
}

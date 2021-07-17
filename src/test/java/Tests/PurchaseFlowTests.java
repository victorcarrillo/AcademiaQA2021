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
        logger = extent.createTest("Caso de prueba 21", "Verificacion de formulario de informacion de envio");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToShoppingCart();
        logger.info("Carrito de compra desplegado correctamente");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.goToCheckout());
        logger.info("Checkout desplegado de manera correcta");

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        logger.info("Información de envío insertada");
        Assert.assertTrue(purchaseFlowPages.verifyOverview());
        logger.info("Resumen de compra verificado correctamente");
    }

    @Test
    public void verifyTotalTest(){
        Log.startLog("Caso de prueba 22: verificacion de precio total de compra");
        logger = extent.createTest("Caso de prueba 22", "Verificacion de precio total de compra");

        List<Float> pricesFloat;
        float expectedPrice;

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        prices = inventoryPage.retrieveProductsPrices();

        pricesFloat = convertToFloat(prices);

        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        logger.info("Producto agregado al carrito exitosamente");
        inventoryPage.selectProduct(products, 5);

        expectedPrice = pricesFloat.get(0) + pricesFloat.get(4);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");
        logger.info("Producto agregado al carrito exitosamente");


        inventoryPage.goToShoppingCart();
        logger.info("Carrito de compra desplegado correctamente");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        logger.info("Contenido del carrito verificado, 2 productos");
        shoppingCartPage.goToCheckout();

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        logger.info("Información de envío insertada");
        Assert.assertTrue(purchaseFlowPages.verifyTotalPrice(expectedPrice));
        logger.info("Subtotal en resumen de compra verificado correctamente");
    }

    @Test
    public void verifySuccesfulPurchaseTest(){
        Log.startLog("Caso de prueba 23: verificacion de compra exitosa");
        logger = extent.createTest("Caso de prueba 23", "Verificacion de compra exitosa");

        List<Float> pricesFloat;
        float expectedPrice;

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        prices = inventoryPage.retrieveProductsPrices();

        pricesFloat = convertToFloat(prices);

        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        logger.info("Producto agregado al carrito exitosamente");
        inventoryPage.selectProduct(products, 5);

        expectedPrice = pricesFloat.get(0) + pricesFloat.get(4);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");
        logger.info("Producto agregado al carrito exitosamente");


        inventoryPage.goToShoppingCart();
        logger.info("Carrito de compra desplegado correctamente");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        logger.info("Contenido del carrito verificado, 2 productos");
        shoppingCartPage.goToCheckout();

        PurchaseFlowPages purchaseFlowPages = new PurchaseFlowPages(driver);
        Assert.assertTrue(purchaseFlowPages.insertCheckoutInfo());
        logger.info("Información de envío insertada");
        Assert.assertTrue(purchaseFlowPages.verifyTotalPrice(expectedPrice));
        logger.info("Subtotal en resumen de compra verificado correctamente");
        Assert.assertTrue(purchaseFlowPages.goToFinish());
        Assert.assertTrue(purchaseFlowPages.verifyPurchase());
        logger.info("Verificación de compra exitosa");
    }
}

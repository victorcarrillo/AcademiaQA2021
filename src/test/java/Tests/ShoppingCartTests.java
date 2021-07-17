package Tests;

import PageObjects.InventoryPage;
import PageObjects.LoginPage;
import PageObjects.ProductDetailPage;
import PageObjects.ShoppingCartPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

import java.util.List;

public class ShoppingCartTests extends BaseTest{

    String URL;
    String username;
    String password;
    List<WebElement> products;

    @Test
    public void emptyCartTest(){
        Log.startLog("Caso de prueba 18: verificacion de carrito vacio");
        logger = extent.createTest("Caso de prueba 18", "Verificacion de carrito vacio");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Login exitoso");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToShoppingCart();
        logger.info("Carrito desplegado correctamente");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.verifyCartContent(0);
        logger.info("Verificacion de carrito, 0 productos");
    }

    @Test
    public void addOneProductFromInventoryTest(){
        Log.startLog("Caso de prueba 19: verificacion de carrito no vacio");
        logger = extent.createTest("Caso de prueba 19", "Verificacion de carrito no vacio");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Login exitoso");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        logger.info("Producto agregado correctamente");
        inventoryPage.selectProduct(products, 5);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");
        logger.info("Producto agregado correctamente");


        inventoryPage.goToShoppingCart();
        logger.info("Carrito desplegado correctamente");

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        logger.info("Verificacion de carrito, 2 productos");
    }

    @Test
    public void removeProductsFromCartTest(){
        Log.startLog("Caso de prueba 20: eliminacion de productos en carrito");
        logger = extent.createTest("Caso de prueba 20", "Eliminacion de productos en carrito");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Login exitoso");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        logger.info("Producto agregado correctamente");
        inventoryPage.selectProduct(products, 5);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");
        logger.info("Producto agregado correctamente");


        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        shoppingCartPage.removeProduct(1);
        logger.info("Producto eliminado correctamente");
        shoppingCartPage.removeProduct(1);
        logger.info("Producto eliminado correctamente");
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
        logger.info("Verificacion de carrito, 0 productos");
    }

}

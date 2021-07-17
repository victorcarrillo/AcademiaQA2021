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

public class ProductDetailTests extends BaseTest{

    String URL;
    String username;
    String password;
    List<WebElement> products;

    @Test
    public void productDetailTest(){
        Log.startLog("Caso de prueba 15: verificación detalle producto");
        logger = extent.createTest("Caso de prueba 15","Verificación detalle producto");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        logger.info("Productos mostrados correctamente en catálogo");
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.verifyPage());
        logger.info("Detalle de producto desplegado correctamente");
    }

    @Test
    public void addProductFromDetailTest(){
        Log.startLog("Caso de prueba 16: agregar producto a carrito desde detalle");
        logger = extent.createTest("Caso de prueba 16","Agregar producto a carrito desde detalle");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        logger.info("Productos mostrados correctamente en catálogo");
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.actionButton("ADD"));
        logger.info("Detalle de producto desplegado correctamente");
        logger.info("Producto agregado al carrito desde detalle de producto");

        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(1));
        logger.info("Contenido de carrito verificado, 1 produto");
    }

    @Test
    public void addAndRemoveProductFromDetailTest(){
        Log.startLog("Caso de prueba 17: agregar producto a carrito y despues eliminar desde detalle");
        logger = extent.createTest("Caso de prueba 17", "Agregar producto a carrito y despues eliminar desde detalle");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Sesión iniciada exitosamente");

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        logger.info("Productos mostrados correctamente en catálogo");
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.actionButton("ADD"));
        logger.info("Detalle de producto desplegado correctamente");
        logger.info("Producto agregado al carrito desde detalle de producto");
        Assert.assertTrue(productDetailPage.actionButton("REMOVE"));
        logger.info("Producto eliminado del carrito desde detalle de producto");

        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
        logger.info("Contenido de carrito verificado, 0 produtos");
    }

}

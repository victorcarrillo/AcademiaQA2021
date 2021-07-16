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

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.verifyPage());
    }

    @Test
    public void addProductFromDetailTest(){
        Log.startLog("Caso de prueba 16: agregar producto a carrito desde detalle");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.actionButton("ADD"));

        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(1));
    }

    @Test
    public void addAndRemoveProductFromDetailTest(){
        Log.startLog("Caso de prueba 17: agregar producto a carrito y despues eliminar desde detalle");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        inventoryPage.selectProduct(products, 1);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.actionButton("ADD"));
        Assert.assertTrue(productDetailPage.actionButton("REMOVE"));

        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
    }

}

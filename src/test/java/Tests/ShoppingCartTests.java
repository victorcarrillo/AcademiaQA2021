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

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.verifyCartContent(0);
    }

    @Test
    public void addOneProductFromInventoryTest(){
        Log.startLog("Caso de prueba 19: verificacion de carrito no vacio");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        inventoryPage.selectProduct(products, 5);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");


        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
    }

    @Test
    public void removeProductsFromCartTest(){
        Log.startLog("Caso de prueba 20: eliminacion de productos en carrito");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        inventoryPage.selectProduct(products, 5);

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.actionButton("ADD");


        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(2));
        shoppingCartPage.removeProduct(1);
        shoppingCartPage.removeProduct(1);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
    }

}

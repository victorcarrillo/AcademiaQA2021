package Tests;

import PageObjects.InventoryPage;
import PageObjects.LoginPage;
import PageObjects.ShoppingCartPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

import java.util.*;

public class InventoryTests extends BaseTest{

    String URL;
    String username;
    String password;
    List<WebElement> products;
    List<WebElement> orderedProducts;

    @Test
    public void inventoryTest(){
        Log.startLog("Caso de prueba 7: verificacion de inventario");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveProducts();
        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void sortByAlphabeticDescendantTest(){
        Log.startLog("Caso de prueba 8: filtro alfabético descendente");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Log.info("Buscando productos en orden alfabetico");
        products = inventoryPage.retrieveProducts();
        inventoryPage.sortByAlphabeticDescendant();
        Log.info("Buscando productos en orden alfabetico descendente");
        orderedProducts = inventoryPage.retrieveProducts();
        Assert.assertTrue(compareOrder("za"));
    }

    @Test
    public void sortByPriceAscendantTest(){
        Log.startLog("Caso de prueba 9: filtro precio ascendente");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Log.info("Buscando productos en orden regular");
        products = inventoryPage.retrieveProductsPrices();
        inventoryPage.sortByPriceAscendant();
        Log.info("Buscando productos en orden de precio ascendente");
        orderedProducts = inventoryPage.retrieveProductsPrices();
        Assert.assertTrue(compareOrder("lohi"));
    }

    @Test
    public void sortByPriceDescendantTest(){
        Log.startLog("Caso de prueba 10: filtro precio descendente");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Log.info("Buscando productos en orden regular");
        products = inventoryPage.retrieveProductsPrices();
        inventoryPage.sortByPriceDescendant();
        Log.info("Buscando productos en orden de precio descendente");
        orderedProducts = inventoryPage.retrieveProductsPrices();
        Assert.assertTrue(compareOrder("hilo"));
    }

    @Test
    public void addOneProductFromInventoryTest(){
        Log.startLog("Caso de prueba 11: agregar un producto a carrito desde inventario");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(1));
    }

    @Test
    public void addMultipleProductsFromInventoryTest(){
        Log.startLog("Caso de prueba 12: agregar varios producto a carrito desde inventario");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 2, "ADD"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 3, "ADD"));
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(3));
    }

    @Test
    public void addAndRemoveOneProductFromInventoryTest(){
        Log.startLog("Caso de prueba 13: agregar un producto a carrito desde inventario y eliminarlo");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "REMOVE"));
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
    }

    @Test
    public void addAndRemoveMultipleProductFromInventoryTest(){
        Log.startLog("Caso de prueba 14: agregar multiples productos a carrito desde inventario y luego eliminarlos");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "ADD"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 2, "ADD"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 3, "ADD"));
        products = inventoryPage.retrieveAddButtons();
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 1, "REMOVE"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 2, "REMOVE"));
        Assert.assertTrue(inventoryPage.buttonCartAction(products, 3, "REMOVE"));
        inventoryPage.goToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPage.verifyCartContent(0));
    }

    private void printProducts(){
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).getText());
        }
        System.out.println("");
        for(int i = 0; i < orderedProducts.size(); i++){
            System.out.println(orderedProducts.get(i).getText());
        }
    }

    private boolean compareOrder(String comparer) {
        List<Float> prices;
        List<Float> orderedPrices;

        switch (comparer){
            case "za":
                products.sort(Comparator.comparing( WebElement::getText ).reversed());
                if(orderedProducts.equals(products)) {
                    Log.info("Productos en orden correcto");
                    return true;
                }
                else {
                    Log.fatal("Productos en orden incorrecto");
                    return false;
                }

            case "lohi":
                prices = convertToFloat(products);
                orderedPrices = convertToFloat(orderedProducts);
                Collections.sort(prices);
                if(orderedPrices.equals(prices)) {
                    Log.info("Precios en orden correcto");
                    return true;
                }
                else {
                    Log.fatal("Precios en orden incorrecto");
                    return false;
                }

            case "hilo":
                prices = convertToFloat(products);
                orderedPrices = convertToFloat(orderedProducts);
                Collections.sort(prices, Collections.reverseOrder());
                if(orderedPrices.equals(prices)) {
                    Log.info("Precios en orden correcto");
                    return true;
                }
                else {
                    Log.fatal("Precios en orden incorrecto");
                    return false;
                }
        }
        return false;
    }
}

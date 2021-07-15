package Tests;

import PageObjects.InventoryPage;
import PageObjects.LoginPage;
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
        Log.startLog("Caso de prueba 3: verificacion de inventario");

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
        Log.startLog("Caso de prueba 4: filtro alfabético ascendente");

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
        Log.startLog("Caso de prueba 5: filtro precio ascendente");

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
        Log.startLog("Caso de prueba 6: filtro precio descendente");

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

    private void printProducts(){
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).getText());
        }
        System.out.println("");
        for(int i = 0; i < orderedProducts.size(); i++){
            System.out.println(orderedProducts.get(i).getText());
        }
    }

    private List<Float> convertToFloat(List<WebElement> elements){
        List<Float> prices = new ArrayList<>();
        String tempPrice;

        for(int i = 0; i < elements.size(); i++){
            tempPrice = elements.get(i).getText();
            tempPrice= tempPrice.replace("$", "");
            prices.add(Float.parseFloat(tempPrice));
        }
        return prices;
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

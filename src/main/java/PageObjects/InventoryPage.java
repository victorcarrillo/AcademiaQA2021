package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

import java.util.List;

public class InventoryPage extends BasePage{

    /** Locators **/
    By byInventoryProducts;
    By byInventoryProductsPrice;
    By byFilterAlphabeticDescendant;
    By byFilterPriceAscendant;
    By byFilterPriceDescendant;

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public List<WebElement> retrieveProducts() {
        byInventoryProducts = By.cssSelector(prop.getProperty("inventoryProducts"));

        try{
            List<WebElement> products = getListElements(byInventoryProducts, 5);
            Log.info("Productos encontrados");
            return products;
        }
        catch(Exception e){
            Log.fatal("Productos no encontrados");
            return null;
        }
    }

    public List<WebElement> retrieveProductsPrices() {
        byInventoryProductsPrice = By.cssSelector(prop.getProperty("inventoryProductsPrice"));

        try{
            List<WebElement> products = getListElements(byInventoryProductsPrice, 5);
            Log.info("Precios encontrados");
            return products;
        }
        catch(Exception e){
            Log.fatal("Precios no encontrados");
            return null;
        }
    }

    public void sortByAlphabeticDescendant() {
        byFilterAlphabeticDescendant = By.cssSelector(prop.getProperty("filterAlphabeticDescendant"));

        Log.info("Filtrando objetos en orden alfabético descendente");
        WebElement filter = driver.findElement(byFilterAlphabeticDescendant);
        filter.click();
    }

    public void sortByPriceAscendant() {
        byFilterPriceAscendant = By.cssSelector(prop.getProperty("filterPriceAscendant"));

        Log.info("Filtrando objetos en orden de precio ascendente");
        WebElement filter = driver.findElement(byFilterPriceAscendant);
        filter.click();
    }

    public void sortByPriceDescendant() {
        byFilterPriceDescendant = By.cssSelector(prop.getProperty("filterPriceDescendant"));

        Log.info("Filtrando objetos en orden de precio descendente");
        WebElement filter = driver.findElement(byFilterPriceDescendant);
        filter.click();
    }
}

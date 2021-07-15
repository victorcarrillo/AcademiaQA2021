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
    By byAddButtons;
    By byShoppingCartButton;

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
        WebElement filter = getElementOfPresenceOfElementLocated(byFilterAlphabeticDescendant, 5);
        filter.click();
    }

    public void sortByPriceAscendant() {
        byFilterPriceAscendant = By.cssSelector(prop.getProperty("filterPriceAscendant"));

        Log.info("Filtrando objetos en orden de precio ascendente");
        WebElement filter = getElementOfPresenceOfElementLocated(byFilterPriceAscendant, 5);
        filter.click();
    }

    public void sortByPriceDescendant() {
        byFilterPriceDescendant = By.cssSelector(prop.getProperty("filterPriceDescendant"));

        Log.info("Filtrando objetos en orden de precio descendente");
        WebElement filter = getElementOfPresenceOfElementLocated(byFilterPriceDescendant, 5);
        filter.click();
    }

    public void goToShoppingCart() {
        byShoppingCartButton = By.cssSelector(prop.getProperty("shoppingCartButton"));

        Log.info("Accesando carrito de compra");
        WebElement shoppingCartButton = getElementOfPresenceOfElementLocated(byShoppingCartButton, 5);
        shoppingCartButton.click();
    }

    public List<WebElement> retrieveAddButtons() {
        byAddButtons = By.cssSelector(prop.getProperty("addToCartButton"));

        Log.info("Agregando producto al carrito de compra");
        List<WebElement> addButtons = getListElements(byAddButtons, 5);
        return addButtons;
    }

    public boolean addProductToCart(List<WebElement> products, int productNumber, String action) {
        if(productNumber <= products.size()) {
            Log.info("Encontrando producto para accion: " + action);
            WebElement addButton = products.get(productNumber - 1);
            String buttonMessage = addButton.getText();
            if(buttonMessage.contains(action)){
                addButton.click();
                Log.info("Acción, " + action + ", exitosa");
                return true;
            }
            else{
                Log.fatal("Acción, " + action + ", fallida");
                return false;
            }
        }
        else {
            Log.fatal("Producto inexistente");
            return false;
        }
    }
}

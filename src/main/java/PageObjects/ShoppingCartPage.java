package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

import java.util.List;

public class ShoppingCartPage extends BasePage{

    /** Locators **/
    By byProductsOnCart;

    public ShoppingCartPage(WebDriver driver){
        super(driver);
    }

    public boolean verifyCartContent(int cantidadProductos) {
        byProductsOnCart = By.cssSelector(prop.getProperty("productsOnCart"));

        try {
            Log.info("Recuperando productos en el carrito");
            List<WebElement> products = getListElements(byProductsOnCart, 5);
            Log.info("Verificando cantidad de productos");
            if(products.size() == cantidadProductos) {
                Log.info("Número de productos correcto");
                return true;
            }
            else {
                Log.fatal("Número de productos incorrecto");
                return false;
            }
        }
        catch (Exception e){
            if(cantidadProductos == 0){
                Log.info("El carrito está vacío");
                return true;
            }
            else {
                Log.fatal("Productos no encontrados");
                return false;
            }
        }
    }
}

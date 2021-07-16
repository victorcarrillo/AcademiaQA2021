package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

public class ProductDetailPage extends BasePage{

    /** Locators **/
    By byBackToProductsButton;
    By byActionButton;

    public ProductDetailPage(WebDriver driver){
        super(driver);
    }


    public boolean verifyPage() {
        byBackToProductsButton = By.cssSelector(prop.getProperty("backToProducts"));

        try{
            getElementOfPresenceOfElementLocated(byBackToProductsButton, 5);
            Log.info("Detalle de producto verificado");
            return true;
        }
        catch (Exception e){
            Log.fatal("Verificacion fallida");
            return false;
        }
    }

    public boolean actionButton(String action) {
        byActionButton = By.cssSelector(prop.getProperty("addToCartButton"));

        try {
            Log.info("Encontrando producto para accion: " + action);
            WebElement addButton = getElementOfPresenceOfElementLocated(byActionButton, 5);
            String buttonMessage = addButton.getText();
            if (buttonMessage.contains(action)) {
                addButton.click();
                Log.info("Acción, " + action + ", exitosa");
                return true;
            } else {
                Log.fatal("Acción, " + action + ", fallida");
                return false;
            }
        }
        catch (Exception e){
            Log.fatal("Boton no encontrado");
            return false;
        }
    }
}

package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

public class PurchaseFlowPages extends BasePage{

    /** Locators **/
    By byFirstNameInput;
    By byLastNameInput;
    By byZipCodeInput;
    By byContinueButton;
    By byPageTitle;
    By bySubTotal;
    By byFinishButton;

    public PurchaseFlowPages(WebDriver driver){
        super(driver);
    }

    public boolean insertCheckoutInfo() {
        String firstName = prop.getProperty("firstName");
        String lastName = prop.getProperty("lastName");
        String zipCode = prop.getProperty("zipCode");
        byFirstNameInput = By.cssSelector(prop.getProperty("firstNameInput"));
        byLastNameInput = By.cssSelector(prop.getProperty("lastNameInput"));
        byZipCodeInput = By.cssSelector(prop.getProperty("zipCodeInput"));
        byContinueButton = By.cssSelector(prop.getProperty("continueButton"));

        try{
            Log.info("Obteniendo elementos de formulario");
            WebElement firstNameInput = getElementOfPresenceOfElementLocated(byFirstNameInput, 5);
            WebElement lastNameInput = getElementOfPresenceOfElementLocated(byLastNameInput, 5);
            WebElement zipCodeInput = getElementOfPresenceOfElementLocated(byZipCodeInput, 5);
            WebElement continueButton = getElementOfPresenceOfElementLocated(byContinueButton, 5);

            Log.info("Rellenando formulario");
            firstNameInput.sendKeys(firstName);
            lastNameInput.sendKeys(lastName);
            zipCodeInput.sendKeys(zipCode);
            continueButton.click();

            return true;
        }
        catch (Exception e){
            Log.fatal("Error en la insercion de informacion a formulario");
            return false;
        }
    }

    public boolean verifyOverview() {
        byPageTitle = By.cssSelector(prop.getProperty("pageTitle"));

        try{
            WebElement pageTitle = getElementOfPresenceOfElementLocated(byPageTitle, 5);
            if(pageTitle.getText().contains("OVERVIEW")) {
                Log.info("Verificacion de overview correcta");
                return true;
            }
            else{
                Log.info("Verificacion de overview fallida");
                return false;
            }
        }
        catch (Exception e){
            Log.fatal("No se encuentra titulo de pagina");
            return false;
        }
    }

    public boolean verifyTotalPrice(float expectedPrice) {
        bySubTotal = By.cssSelector(prop.getProperty("subTotal"));

        Log.info("Comparing expected price and shown total");
        try{
            WebElement priceDiv = getElementOfPresenceOfElementLocated(bySubTotal, 5);
            String totalTemp = priceDiv.getText();
            totalTemp = totalTemp.replace("Item total: $", "");
            float subTotal = Float.parseFloat(totalTemp);

            if(subTotal == expectedPrice){
                Log.info("El precio subtotal es correct");
                return true;
            }
            else {
                Log.fatal("El precio subtottal es incorrecto");
                return false;
            }
        }
        catch (Exception e){
            Log.fatal("No se pudo encontrar el precio subtotal");
            return false;
        }
    }

    public boolean goToFinish() {
        byFinishButton = By.cssSelector(prop.getProperty("finishButton"));

        Log.info("Procediendo a finalizar la compra");

        try{
            WebElement finishButton = getElementOfPresenceOfElementLocated(byFinishButton, 5);
            finishButton.click();

            return true;
        }
        catch (Exception e){
            Log.fatal("Boton de finalizar no encontrado");
            return false;
        }
    }

    public boolean verifyPurchase() {
        byPageTitle = By.cssSelector(prop.getProperty("pageTitle"));

        try{
            WebElement pageTitle = getElementOfPresenceOfElementLocated(byPageTitle, 5);
            if(pageTitle.getText().contains("COMPLETE")) {
                Log.info("Verificacion de compra correcta");
                return true;
            }
            else{
                Log.info("Verificacion de compra fallida");
                return false;
            }
        }
        catch (Exception e){
            Log.fatal("No se encuentra titulo de pagina");
            return false;
        }
    }
}

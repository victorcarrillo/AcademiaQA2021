package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

import java.security.Key;

public class LoginPage extends BasePage{

    /** Locators **/
    By byInputUsername;
    By byInputPassword;
    By byMenuButton;
    By byErrorMessage;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void inputCredentials(String username, String password) {
        byInputUsername = By.cssSelector(prop.getProperty("inputUser"));
        byInputPassword = By.cssSelector(prop.getProperty("inputPass"));

        WebElement usernameField = getElementOfPresenceOfElementLocated(byInputUsername, 5);
        Log.info("Ingresando usuario");
        usernameField.sendKeys(username);

        WebElement passwordField = getElementOfPresenceOfElementLocated(byInputPassword, 5);
        Log.info("Ingresando contraseña");
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
    }

    public boolean validateLogin() {
        byMenuButton = By.cssSelector(prop.getProperty("menuButton"));
        Log.info("Validando Login exitoso");
        try{
            Assert.assertTrue(getElementOfPresenceOfElementLocated(byMenuButton, 5).isDisplayed());
            Log.info("Login exitoso");
            return true;
        }
        catch(Exception e) {
            Log.fatal("Login fallido");
            return false;
        }
    }

    public boolean validateBlockedLogin() {
        byErrorMessage = By.cssSelector(prop.getProperty("errorMessage"));
        Log.info("Validando Login bloqueado");
        try{
            Assert.assertTrue(getElementOfPresenceOfElementLocated(byErrorMessage, 5).isDisplayed());
            Log.info("Bloqueo exitoso");
            return true;
        }
        catch(Exception e) {
            Log.fatal("Bloqueo fallido");
            return false;
        }
    }
}

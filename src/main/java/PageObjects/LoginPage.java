package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

public class LoginPage extends BasePage{

    /**Locators**/
    By bySauceLogo = By.cssSelector("div.login_logo");
    By byUserInput = By.cssSelector("input#user-name");
    By byPasswordInput = By.cssSelector("input#password");
    By byMessageEpicSad = By.cssSelector("div.error-message-container.error >h3");


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public boolean validateLoginLogo(){
        try {;
            Log.info("Login logo validation");
            WebElement logo = getElementOfPresenceOfElementLocated(bySauceLogo, 5);
            return true;
        }
        catch(Exception e){
            Log.fatal("Element logo in login page is not found");
            return false;
        }
    }

    public void insertCredentials(String user, String password){
        Log.info("Trying access with user: "+user);
        WebElement userInput =  getElementOfPresenceOfElementLocated(byUserInput, 5);
        userInput.sendKeys(user);
        Log.info("Trying access with password: "+password);
        WebElement userPassword =  getElementOfPresenceOfElementLocated(byPasswordInput, 5);
        userPassword.sendKeys(password);
        userPassword.sendKeys(Keys.ENTER);
    }

    public void checkMessageEpicSadFace(String message){
        Log.info("Comparing Epic sad face message");
        WebElement messageEpicSad = getElementOfPresenceOfElementLocated(byMessageEpicSad,5);
        Assert.assertEquals(messageEpicSad.getText(), message);
    }
}

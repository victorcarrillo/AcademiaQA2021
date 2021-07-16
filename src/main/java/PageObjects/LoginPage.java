package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage{

    /**Locators**/
    By usernameFieldLocator = By.id("user-name");
    By passwordFieldLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");
    By errorMessageLocator = By.cssSelector("*[data-test='error']");

    /**Behavior**/

    public LoginPage(WebDriver driver){

        super(driver);

    }

    public void login(String username, String password){

        Log.info("Logging in with username/password credentials -> "+username+":"+password );
        typeOnElement(username, usernameFieldLocator);
        typeOnElement(password, passwordFieldLocator);
        clickOnElement(loginButtonLocator);
        Log.info("Login attempt completed");

    }

    public void login(){

        Log.info("Logging in with empty username field" );
        clickOnElement(loginButtonLocator);
        Log.info("Login attempt completed");

    }

    public void login(String username){

        Log.info("Logging in with empty password field");
        typeOnElement(username, usernameFieldLocator);
        clickOnElement(loginButtonLocator);
        Log.info("Login attempt completed");

    }


    public void validateErrorMessage(String expected){

        Log.info("Validating error message display and match");
            String actual = getElementText(errorMessageLocator);
            boolean match = expected.equals(actual);
        if (match){
            Log.info("Found error message matches expected error message.");
        }
            if (!match){
                Log.info("Expected message: \""+ expected + "\"");
                Log.info("Actual message: \""+ actual + "\"");
                Log.fatal("Error message did not match witch expected result");
            }
        Assert.assertEquals(actual, expected, "Error message comparison");
    }


    public void validateLoginPage(){
        Log.info("Validating login page elements");
        if(elementExists(usernameFieldLocator) && elementExists(passwordFieldLocator) && elementExists(loginButtonLocator)){
            Log.info("Login page elements found");
        }
    }

}

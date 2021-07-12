package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

        type(username, usernameFieldLocator);
        type(password, passwordFieldLocator);
        click(loginButtonLocator);

    }

    public void login(){

        click(loginButtonLocator);

    }

    public void login(String username){

        type(username, usernameFieldLocator);
        click(loginButtonLocator);

    }

    public boolean validateErrorMessage(String expected){

            String actual = getElement(errorMessageLocator, 5).getText();
            boolean match = expected.equals(actual);
            if (!match){
                Log.info("Expected message: \""+ expected + "\"");
                Log.info("Actual message: \""+ actual + "\"");
                Log.fatal("Error message did not match witch expected result");
            }
            return match;

    }


    public boolean validateLoginPage(){

            toLocate = getElement(usernameFieldLocator,5);
            return toLocate.isDisplayed();

    }

}

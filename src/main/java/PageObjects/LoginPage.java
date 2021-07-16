package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By userNameSelector = By.cssSelector("[id=\"user-name\"]");
    public WebElement userNameInput (){ return getElementOfPresenceOfElementLocated(userNameSelector); }

    private By passwordSelector = By.cssSelector("[id=\"password\"]");
    public WebElement passwordInput (){
        return getElementOfPresenceOfElementLocated(passwordSelector);
    }

    private By loginButtonSelector = By.cssSelector("[id=\"login-button\"]");
    public WebElement loginButton (){
        return getElementOfPresenceOfElementLocated(loginButtonSelector);
    }

    private By loginErrorMessageSelector = By.cssSelector("[data-test=\"error\"]");
    public WebElement loginErrorMessage (){
        return getElementOfPresenceOfElementLocated(loginErrorMessageSelector);
    }
}

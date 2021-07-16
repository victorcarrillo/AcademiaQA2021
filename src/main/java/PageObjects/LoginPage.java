package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    /* LOCATORS */
    By locUserField = By.xpath(props.getProperty("field_user"));
    By locPassField = By.xpath(props.getProperty("field_pass"));
    By locLoginButton = By.xpath(props.getProperty("button_login"));
    By locPageTitle = By.xpath(props.getProperty("page_title"));
    By locLoginLogo = By.xpath(props.getProperty("logo_login"));
    By locErrorMessage = By.xpath(props.getProperty("error_message"));
    By locCloseError = By.xpath(props.getProperty("close_error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void isUserField() {
        try{
            getElement(locUserField,5);
            Log.info("Field 'username' found");
        } catch(Exception e) {
            Log.fatal("Field 'username' not found");
        }
    }

    public void isPassField() {
        try{
            getElement(locPassField,5);
            Log.info("Field 'password' found");
        } catch(Exception e) {
            Log.fatal("Field 'password' not found");
        }
    }

    public void isLoginButton() {
        try{
            getElement(locLoginButton,5);
            Log.info("Button 'login' found");
        } catch(Exception e) {
            Log.fatal("Button 'login' not found");
        }
    }

    public void fillLoginForm(String user, String pass) {
        Log.info("Filling up field 'username' with: "+user);
        getElement(locUserField,5).sendKeys(user);

        Log.info("Filling up field 'password' with: "+pass);
        getElement(locPassField,5).sendKeys(pass);

        Log.info("Clicking button 'login'");
        getButton(locLoginButton,5).click();
    }

    public String currentPage() {
        String page = getElement(locPageTitle,5).getText();
        Log.info("Current page is: "+page);
        return page;
    }

    public boolean currentPageIsLogin() {
        try {
            getElement(locLoginLogo,5);
            Log.info("Current page is: LOGIN FORM");
            return true;
        } catch(Exception e) {
            Log.fatal("Not in LOGIN FORM page");
            return false;
        }
    }

    public String errorMessage() {
        String error = getElement(locErrorMessage,5).getText();
        Log.info("Error message showed: "+error);
        return error;
    }

    public void closeErrorMessage() {
        Log.info("Clicking message's button 'close'");
        getButton(locCloseError,5).click();
    }
}

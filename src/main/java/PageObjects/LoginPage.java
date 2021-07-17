package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public boolean isUserField() {
        try{
            getElement(locUserField,5);
            Log.info("username field found");
            return true;
        } catch(Exception e) {
            Log.fatal("username field not found");
            return false;
        }
    }

    public boolean isPassField() {
        try{
            getElement(locPassField,5);
            Log.info("password field found");
            return true;
        } catch(Exception e) {
            Log.fatal("password field not found");
            return false;
        }
    }

    public boolean isLoginButton() {
        try{
            getElement(locLoginButton,5);
            Log.info("login button found");
            return true;
        } catch(Exception e) {
            Log.fatal("login button not found");
            return false;
        }
    }

    public void fillLoginForm(String user, String pass) {
        try {
            Log.info("filling up username field with: "+user);
            getElement(locUserField,5).sendKeys(user);
        } catch (Exception e) {
            Log.fatal("username field not found");
        }

        try {
            Log.info("filling up password field with: "+pass);
            getElement(locPassField,5).sendKeys(pass);
        } catch (Exception e) {
            Log.fatal("password field not found");
        }

        try {
            Log.info("clicking login button");
            getButton(locLoginButton,5).click();
        } catch (Exception e) {
            Log.fatal("login button not found");
        }
    }

    public String currentPage() {
        String page;
        try {
            Log.info("getting current's page title");
            page = getElement(locPageTitle,5).getText();
            Log.info("current page is: "+page);
            return page;
        } catch (Exception e) {
            Log.fatal("page title not found");
        }
        return null;
    }

    public boolean currentPageIsLogin() {
        try {
            getElement(locLoginLogo,5);
            Log.info("current page is: LOGIN FORM");
            return true;
        } catch(Exception e) {
            Log.fatal("Not in LOGIN FORM page");
            return false;
        }
    }

    public String errorMessage() {
        String error;
        try {
            Log.info("getting error message");
            error = getElement(locErrorMessage,5).getText();
            Log.info("error message showed: "+error);
            return error;
        } catch (Exception e) {
            Log.fatal("error message not found");
        }
        return null;
    }

    public boolean closeErrorMessage() {
        try {
            Log.info("clicking error's close button");
            getButton(locCloseError,5).click();
            Log.info("error message closed");
            return true;
        } catch (Exception e) {
            Log.fatal("error's close button not found");
            return false;
        }
    }
}

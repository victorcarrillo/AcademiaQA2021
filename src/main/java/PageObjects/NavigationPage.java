package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage{
    /* LOCATORS */
    By locBurgerMenu = By.xpath(props.getProperty("burger_menu"));
    By locAppLogo = By.xpath(props.getProperty("app_logo"));
    By locCartButton = By.xpath(props.getProperty("cart_button"));

    By locTwitterLink = By.xpath(props.getProperty("twitter_link"));
    By locFacebookLink = By.xpath(props.getProperty("facebook_link"));
    By locLinkedInLink = By.xpath(props.getProperty("linkedin_link"));
    By locCopyright = By.xpath(props.getProperty("copyright"));

    By locBurgerMenuContainer = By.xpath(props.getProperty("menu_container"));
    By locProductsOption = By.xpath(props.getProperty("option_products"));
    By locAboutOption = By.xpath(props.getProperty("option_about"));
    By locLogoutOption = By.xpath(props.getProperty("option_logout"));
    By locResetOption = By.xpath(props.getProperty("option_reset"));
    By locCloseBurger = By.xpath(props.getProperty("option_close"));

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void isBurgerMenu() {
        try{
            getElement(locBurgerMenu,5);
            Log.info("burger menu found");
        } catch(Exception e) {
            Log.fatal("burger menu not found");
        }
    }

    public void isAppLogo() {
        try {
            getElement(locAppLogo,5);
            Log.info("app logo found");
        } catch(Exception e) {
            Log.fatal("app logo not found");
        }
    }

    public void isCartButton() {
        try {
            getElement(locCartButton,5);
            Log.info("cart button found");
        } catch(Exception e) {
            Log.fatal("cart button not found");
        }
    }

    public void isTwitterLink() {
        try {
            getElement(locTwitterLink,5);
            Log.info("twitter link found");
        } catch(Exception e) {
            Log.fatal("twitter link not found");
        }
    }

    public void isFacebookLink() {
        try {
            getElement(locFacebookLink,5);
            Log.info("facebook link found");
        } catch(Exception e) {
            Log.fatal("facebook link not found");
        }
    }

    public void isLinkedinLink() {
        try {
            getElement(locLinkedInLink,5);
            Log.info("linkedIn link found");
        } catch(Exception e) {
            Log.fatal("linkedIn link not found");
        }
    }

    public void isCopyright() {
        try {
            getElement(locCopyright,5);
            Log.info("copyright message found");
        } catch(Exception e) {
            Log.fatal("copyright message not found");
        }
    }

    public void clickOnBurgerMenu() {
        try {
            Log.info("clicking burger menu button");
            getButton(locBurgerMenu,5).click();
            Log.info("burger menu opened");
        } catch (Exception e) {
            Log.fatal("burger menu not found");
        }
    }

    public void clickOnProductsOption() {
        Log.info("waiting for burger menu to open");
        burgerVisible();

        try {
            Log.info("clicking all products option");
            getButton(locProductsOption,5).click();
            Log.info("going to inventory page");
        } catch (Exception e) {
            Log.fatal("all products option not found");
        }
    }

    public void clickOnAboutOption() {
        Log.info("waiting for burger menu to open");
        burgerVisible();

        try {
            Log.info("clicking about option");
            getButton(locAboutOption,5).click();
            Log.info("going to about page");
        } catch (Exception e) {
            Log.fatal("about option not found");
        }
    }

    public void clickOnLogout() {
        Log.info("waiting for burger menu to open");
        burgerVisible();

        try {
            Log.info("clicking logout option");
            getButton(locLogoutOption,5).click();
            Log.info("logging out");
        } catch (Exception e) {
            Log.fatal("logout option not found");
        }
    }

    public void clickOnResetOption() {
        Log.info("waiting for burger menu to open");
        burgerVisible();

        try {
            Log.info("clicking reset option");
            getButton(locResetOption,5).click();
            Log.info("resetting app state");
        } catch (Exception e) {
            Log.fatal("reset option not found");
        }


        try {
            Log.info("clicking close button");
            getButton(locCloseBurger,5).click();
            Log.info("burger menu closed");
        } catch (Exception e) {
            Log.fatal("close button not found");
        }

        Log.info("refreshing page");
        driver.navigate().refresh();
    }

    public void burgerVisible() {
        try {
            getHiddenElement(locBurgerMenuContainer,5);
            Log.info("burger menu is visible");
        } catch (Exception e) {
            Log.fatal("burger menu is not visible");
        }
    }
}

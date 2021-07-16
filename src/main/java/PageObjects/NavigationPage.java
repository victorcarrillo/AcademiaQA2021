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
            Log.info("Burger menu found");
        } catch(Exception e) {
            Log.fatal("Burger menu not found");
        }
    }

    public void isAppLogo() {
        try {
            getElement(locAppLogo,5);
            Log.info("App logo found");
        } catch(Exception e) {
            Log.fatal("App logo not found");
        }
    }

    public void isCartButton() {
        try {
            getElement(locCartButton,5);
            Log.info("Cart button found");
        } catch(Exception e) {
            Log.fatal("Cart button not found");
        }
    }

    public void isTwitterLink() {
        try {
            getElement(locTwitterLink,5);
            Log.info("Twitter link found");
        } catch(Exception e) {
            Log.fatal("Twitter link not found");
        }
    }

    public void isFacebookLink() {
        try {
            getElement(locFacebookLink,5);
            Log.info("Facebook link found");
        } catch(Exception e) {
            Log.fatal("Facebook link not found");
        }
    }

    public void isLinkedinLink() {
        try {
            getElement(locLinkedInLink,5);
            Log.info("LinkedIn link found");
        } catch(Exception e) {
            Log.fatal("LinkedIn link not found");
        }
    }

    public void isCopyright() {
        try {
            getElement(locCopyright,5);
            Log.info("Copyright message found");
        } catch(Exception e) {
            Log.fatal("Copyright message not found");
        }
    }

    public void clickOnBurgerMenu() {
        getButton(locBurgerMenu,5).click();
        Log.info("opening burger menu");
    }

    public void clickOnProductsOption() {
        burgerVisible();
        getButton(locProductsOption,5).click();
        Log.info("going to inventory page");
    }

    public void clickOnAboutOption() {
        burgerVisible();
        getButton(locAboutOption,5).click();
        Log.info("going to about page");
    }

    public void clickOnLogout() {
        burgerVisible();
        getButton(locLogoutOption,5).click();
        Log.info("log out of system");
    }

    public void clickOnResetOption() {
        burgerVisible();
        getButton(locResetOption,5).click();
        Log.info("resetting app data");
        getButton(locCloseBurger,5).click();
        Log.info("closing burger menu");
        driver.navigate().refresh();
        Log.info("refreshing page");
    }

    public void burgerVisible() {
        getHiddenElement(locBurgerMenuContainer,5);
        Log.info("burger menu opened");
    }
}

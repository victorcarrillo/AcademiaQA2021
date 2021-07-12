package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

public class MenuList extends BasePage{

    By byBurgerButton = By.cssSelector("button#react-burger-menu-btn");
    By byMenuList = By.cssSelector("div#menu_button_container>div>div.bm-menu-wrap");
    By byLinkInventory = By.cssSelector("a#inventory_sidebar_link");
    By byLinkAbout = By.cssSelector("a#about_sidebar_link");
    By byLinkLogout = By.cssSelector("a#logout_sidebar_link");
    By byLinkResetApp = By.cssSelector("a#reset_sidebar_link");
    By byCartIcon = By.cssSelector("div#shopping_cart_container>a");
    By byBurgerCross = By.cssSelector("button#react-burger-cross-btn");
    By byNotificationsCardIcon = By.cssSelector("div.shopping_cart_container");


    public MenuList(WebDriver driver){
        super(driver);
    }

    public void clickBurgerButton(){
        WebElement burger = getElementOfPresenceOfElementLocated(byBurgerButton, 5);
        burger.click();
        Log.info("Button burger clicked");

    }

    public void clickBurgerCrossButton(){
        WebElement burgerCross = getElementOfPresenceOfElementLocated(byBurgerCross, 5);
        burgerCross.click();
        Log.info("Button burger cross clicked");

    }

    public boolean menuIsDisplayed(){
        WebElement menuList = getElementOfPresenceOfElementLocated(byMenuList, 5);
        if(menuList.isDisplayed()){
            Log.info("Menu is present");
            return true;
        }
        else {
            Log.info("Menu isn't present");
            return false;
        }
    }

    public void clickAllItems(){
        WebElement linkInventory = getElementOfPresenceOfElementLocated(byLinkInventory, 5);
        linkInventory.click();
        Log.info("Link inventory clicked");
    }

    public void checkURLAbout(String about){
        WebElement linkAbout = getElementOfPresenceOfElementLocated(byLinkAbout, 5);
        Log.info("Check about url "+about +" in menu ");
        Assert.assertEquals(linkAbout.getAttribute("href"), about);
    }

    public void clickLogout(){
        WebElement linkLogout = getElementOfPresenceOfElementLocated(byLinkLogout, 5);
        linkLogout.click();
        Log.info("Link logout clicked");
    }

    public void checkNotificationsCart(String notificationsExpected){
        WebElement cardIcon = getElementOfPresenceOfElementLocated(byNotificationsCardIcon, 5);
        String numberNotification = cardIcon.getText();
        Assert.assertEquals(numberNotification, notificationsExpected);
    }

    public void clickResetApp(){
        WebElement linkReset = getElementOfPresenceOfElementLocated(byLinkResetApp, 5);
        linkReset.click();
        Log.info("Link resetApp clicked");
    }

    public void clickCartIcon(){
        WebElement carIcon = getElementOfPresenceOfElementLocated(byCartIcon, 5);
        carIcon.click();
        Log.info("Cart icon clicked");
    }


}

package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NavbarPage extends BasePage{

    //Locators
    By cartLinkLocator = By.cssSelector(".shopping_cart_link");
    By notEmptyCartLocator = By.cssSelector(".shopping_cart_badge");

    public NavbarPage(WebDriver driver){

        super(driver);

    }

    public void validateCartAdded(){
        Log.info("Starting added product validation");
        toLocate = getElement(notEmptyCartLocator);
        Log.info("Added product validation completed");
    }

    public void validateCartEmpty(){
        Log.info("Starting empty cart validation");
        toLocate = null;
        try{
            toLocate = driver.findElement(notEmptyCartLocator);
            Log.fatal("Cart could not be emptied.");
            Assert.assertNull(toLocate, "Empty cart validation");
        }catch(org.openqa.selenium.NoSuchElementException e){
            Log.info("Empty cart validation completed");
        }
    }

    public boolean getCartEmpty(){
        toLocate = null;
        try{
            toLocate = driver.findElement(notEmptyCartLocator);
            return false;
        }catch(org.openqa.selenium.NoSuchElementException e){
            return true;
        }
    }

    public void goToCart(){
        clickOnElement(cartLinkLocator);
    }

}

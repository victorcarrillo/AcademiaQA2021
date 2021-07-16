package PageObjects;


import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends BasePage{

    //locators
    By checkoutButtonLocator = By.cssSelector("*[data-test='checkout']");


    public CartPage(WebDriver driver){

        super(driver);

    }

    public void checkout(){
        clickOnElement(checkoutButtonLocator);
    }


    }

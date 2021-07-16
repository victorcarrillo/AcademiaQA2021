package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

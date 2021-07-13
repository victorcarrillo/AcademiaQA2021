package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

public class CheckoutCompletePage extends BasePage{

    By byCompleteText = By.cssSelector("div#checkout_complete_container>div.complete-text");
    By byCompleteImage = By.cssSelector("div#checkout_complete_container>img.pony_express");
    By byBackHomeButton = By.cssSelector("button#back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    By byHeader = By.cssSelector("span.title");

    public String checkoutComHeader(){
        try {;
            Log.info("Getting checkout complete Header");
            WebElement header = getElementOfPresenceOfElementLocated(byHeader, 5);
            String textHeader = header.getText();
            return textHeader;
        }
        catch(Exception e){
            Log.fatal("Element checkout complete header is not found");
            return "";
        }
    }

    public boolean infoAndButtonIsDisplayed(){
        WebElement completeText = getElementOfPresenceOfElementLocated(byCompleteText, 5);
        WebElement completeImage = getElementOfPresenceOfElementLocated(byCompleteImage, 5);
        WebElement backHomeButton = getElementOfPresenceOfElementLocated(byBackHomeButton, 5);
        if(completeText.isDisplayed() && completeImage.isDisplayed() && backHomeButton.isDisplayed()){
            Log.info("Objects in container are present");
            return true;
        }
        else {
            Log.info("Objects in container aren't present");
            return false;
        }
    }

    public void clickBackHomeButton(){
        Log.info("Click on back home button");
        WebElement backHomeButton =  getElementOfPresenceOfElementLocated(byBackHomeButton, 5);
        backHomeButton.click();
    }
}

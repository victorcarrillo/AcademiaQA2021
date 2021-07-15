package PageObjects;

import Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    public WebDriver driver;
    WebElement toLocate;

    public int elementsTimeoutSeconds = 5;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public WebDriverWait wait (int secs){
        return new WebDriverWait(driver, secs);
    }


    public WebElement getElement(By by){
        boolean found = false;
        try{
            toLocate = wait(elementsTimeoutSeconds)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            found = true;
            return toLocate;

        }catch(TimeoutException e){
            Log.fatal("Element not found during execution. Test aborted.");
            Assert.assertTrue(found, "Element display verification");
        }
        return toLocate;
    }

    public boolean elementExists(By by){


        return getElement(by).isDisplayed();

    }


    public String getElementText(By by){

        if(elementExists(by)){
            return getElement(by).getText();
        }
        return "";
    }

    public void typeOnElement(String data, By locator){
        getElement(locator).sendKeys(data);
    }

    public void clickOnElement(By locator){
        getElement(locator).click();
    }

    public boolean compareText(By by, String expected){

        return getElementText(by).equals(expected);

    }


}

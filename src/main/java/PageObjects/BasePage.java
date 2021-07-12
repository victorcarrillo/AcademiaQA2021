package PageObjects;

import Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    WebElement toLocate;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void type(String data, By locator){
        toLocate = driver.findElement(locator);
        toLocate.sendKeys(data);
    }

    public void click(By locator){
        toLocate = driver.findElement(locator);
        toLocate.click();
    }

    public boolean exists(By locator){
        try{
            return  driver.findElement(locator).isDisplayed();
        }catch (org.openqa.selenium.NoSuchElementException e){
            return false;
        }

    }

    public WebDriverWait wait (int secs){
        return new WebDriverWait(driver, secs);
    }

    public WebElement getElement(By by, int secs){
        try{

            toLocate = wait(secs)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return toLocate;

        }catch(TimeoutException e){
            Log.fatal("Element not found during execution");
        }
        return null;
    }

}

package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class BasePage {
    WebDriver driver ;
    private WebElement element;
    List<WebElement> listWebElement;

    public BasePage(WebDriver driver){
        this.driver = driver ;
    }

    private int secs = 5;
    public WebDriverWait wait(int secs){
        return new WebDriverWait(driver,secs);
    }

    public WebElement getElementOfPresenceOfElementLocated(By by){
        try{
            element = wait(secs)
                    .ignoring(TimeoutException.class, NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        }catch (NoSuchElementException e){
            Log.fatal(" Element is not found during execution " +e);
        }
        return null;
    }

    public WebElement getElementOfElementToBeClickable(By by){
        try{
            element = wait(secs)
                    .ignoring(TimeoutException.class, NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
            return element;
        }catch (NoSuchElementException e){
            Log.fatal(" Element is not found during execution " +e);
        }
        return null;
    }

    public List<WebElement> getElements(By by){
        try{
            listWebElement = wait(secs)
                    .ignoring(TimeoutException.class,NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            return listWebElement;
        } catch(NoSuchElementException e){
            Log.fatal(" Elements are not found during execution " +e);
        }
        return null;
    }
}

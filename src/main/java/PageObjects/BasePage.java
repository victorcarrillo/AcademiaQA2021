package PageObjects;

import Utilities.CommonUtilities;
import Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class BasePage {
    String projectPath = System.getProperty("user.dir");

    public WebDriver driver;
    public Properties props = CommonUtilities
            .loadProperties(projectPath+"src/main/resources/testData.properties");

    WebElement element;
    List<WebElement> listWebElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait wait(int s) {
        return new WebDriverWait(driver,s);
    }

    public WebElement getElement(By by, int s) {
        try {
            element = wait(s)
                    .ignoring(TimeoutException.class, NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch(TimeoutException | NoSuchElementException e) {
            Log.fatal("Element is not found during execution "+e);
        }
        return null;
    }

    public List<WebElement> getListWebElement(By by, int s) {
        try {
            listWebElement = wait(s)
                    .ignoring(TimeoutException.class,NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            return listWebElement;
        } catch(TimeoutException | NoSuchElementException e) {
            Log.fatal("Elements are not found during execution "+e);
        }
        return null;
    }
}

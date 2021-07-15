package PageObjects;

import Utilities.Log;
import com.sun.media.jfxmedia.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InventoryPage extends BasePage{

    /**Locators**/
    By inventoryPageTitleLocator = By.cssSelector(".title");
    By inventoryPageImageLocator = By.cssSelector(".peek");

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public void validateLogin() {

        Log.info("Starting login attempt validation by image.");
        toLocate = getElement(inventoryPageImageLocator);
        Log.info("Products page image found");

    }

    public void validateLogin(String expected){

        Log.info("Starting login attempt validation");

        String actual = getElementText(inventoryPageTitleLocator);
        boolean match = expected.equals(actual);

        if (match){
            Log.info("Found title matches expected title. Login succesful");
        }
        if (!match)
        {
            Log.info("Expected title: \""+ expected + "\"");
            Log.info("Actual title: \""+ getElementText(inventoryPageTitleLocator) + "\"");
            Log.fatal("Page title did not match witch expected result");
        }

        Assert.assertEquals(expected, actual, "Inventory page title comparison");

    }

}

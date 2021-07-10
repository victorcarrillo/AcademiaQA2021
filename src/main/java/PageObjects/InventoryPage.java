package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

public class InventoryPage extends BasePage {

    By byInventoryContainer = By.cssSelector("div.inventory_container");


    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public boolean validateInventoryContainer(){
        try {;
            Log.info("Inventory container validation");
            WebElement logo = getElementOfPresenceOfElementLocated(byInventoryContainer, 5);
            return true;
        }
        catch(Exception e){
            Log.fatal("Element container in Inventory page is not found");
            return false;
        }
    }

}

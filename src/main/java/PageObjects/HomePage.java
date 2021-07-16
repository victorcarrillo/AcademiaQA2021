package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.FilterOptions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By inventoryItemSelector = By.cssSelector(".inventory_item");
    public List<WebElement> inventoryItemList(){ return getElements(inventoryItemSelector); }

    private By inventoryContainerSelector = By.cssSelector("[id=\"inventory_container\"]");
    public WebElement inventoryContainer(){ return getElementOfPresenceOfElementLocated(inventoryContainerSelector); }

    public boolean existInventoryContainer(){
        return inventoryContainer().isDisplayed();
    }

    public void selectLowHighFilter(FilterOptions option) {
        getElementOfElementToBeClickable(By.cssSelector(".product_sort_container option[value='" + option + "']")).click();
    }

    public List<Float> inventoryItemPriceList(){
        List<WebElement> priceItemList = getElements(By.cssSelector(".inventory_item .inventory_item_price"));
        List<Float> priceList = new ArrayList<Float>();

        for (WebElement priceItem : priceItemList){
            priceList.add(Float.parseFloat(priceItem.getText().replace("$", "")));
        }
        return priceList;
    }

    public boolean lohiValidation(){
        List<Float> priceList = inventoryItemPriceList();
        for(int i = 1; i < priceList.size(); i++){
            if(priceList.get(i) < priceList.get(i-1)){
                return false;
            }
        }
        return true;
    }
}

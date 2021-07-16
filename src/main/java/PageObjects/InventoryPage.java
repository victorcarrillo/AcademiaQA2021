package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class InventoryPage extends BasePage{

    /**Locators**/
    By inventoryPageTitleLocator = By.cssSelector(".title");
    By inventoryPageImageLocator = By.cssSelector(".peek");

    By listFiltersSelector = By.cssSelector(".product_sort_container");
    By itemsNameLocator = By.cssSelector(".inventory_item_name");
    By itemsPriceLocator = By.cssSelector(".inventory_item_price");

    By addItemButtonLocator = By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
    By deleteItemButtonLocator = By.cssSelector("*[data-test=\"remove-sauce-labs-bike-light\"]");


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

    public void validateSorting( String criteria ){
        Log.info("Starting filter functionality verification");
        List<WebElement> elementsList;

        List<String> actualNameSorting;
        List<String> expectedNameSorting;

        Integer[] actualPriceSorting;
        Integer[] expectedPriceSorting;

        if( criteria.equals("az") || criteria.equals("za") ){
            Log.info("Name filter type detected");
            //sorted list in expected order
            elementsList = getElements(itemsNameLocator);
            expectedNameSorting = getSortedTextList(elementsList,criteria);

            //applying filter
            Log.info("Applying filter");
            toggleSelect(listFiltersSelector, criteria);

            //found list in actual order
            elementsList = getElements(itemsNameLocator);
            actualNameSorting = getTextList(elementsList);

            //expected vs found comparison
            Assert.assertTrue(expectedNameSorting.equals(actualNameSorting), "Expected filter results verification");
            Log.info("Verification complete. Sorting results match expected order.");

        }else{

            Log.info("Price filter detected");
            //sorted list in expected order
            elementsList = getElements(itemsPriceLocator);
            expectedPriceSorting = getSortedNumbersList(elementsList, criteria);

            //applying filter
            Log.info("Applying filter");
            toggleSelect(listFiltersSelector, criteria);

            //found list in actual order
            elementsList = getElements(itemsPriceLocator);
            actualPriceSorting = getNumbersList(elementsList);

            //expected vs found comparison
            Assert.assertTrue(Arrays.equals(expectedPriceSorting, actualPriceSorting), "Expected filter result verification");
            Log.info("Verification complete. Sorting results match expected order.");
        }

    }

    public void addTestItem(){
        clickOnElement(addItemButtonLocator);
    }

    public void deleteTestItem(){
        clickOnElement(deleteItemButtonLocator);
    }


}

package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPage extends BasePage{
    /* LOCATORS */
    By locInventory = By.xpath(props.getProperty("inventory_items"));
    By locSortOption = By.xpath(props.getProperty("sort_option"));
    By locCardName = By.xpath(props.getProperty("card_name"));
    By locCardImage = By.xpath(props.getProperty("card_image"));
    By locCardDescription = By.xpath(props.getProperty("card_description"));
    By locCardPrice = By.xpath(props.getProperty("card_price"));
    By locCardButton = By.xpath(props.getProperty("card_button"));
    By locNameInCard = By.xpath(props.getProperty("link_to_page_1") + props.getProperty("product_to_test") + props.getProperty("link_to_page_2"));

    By locNameInPage = By.xpath(props.getProperty("page_name"));
    By locImageInPage = By.xpath(props.getProperty("page_image"));
    By locDescriptionInPage = By.xpath(props.getProperty("page_description"));
    By locPriceInPage = By.xpath(props.getProperty("page_price"));
    By locButtonInPage = By.xpath(props.getProperty("page_button"));
    By locBackInPage = By.xpath(props.getProperty("page_back"));

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public void productsInInventory() {
        try{
            List<WebElement> products = getListWebElement(locInventory,5);
            Log.info("Products in inventory: "+products.size());
        } catch(Exception e) {
            Log.fatal("Inventory is empty");
        }
    }

    public void productsCardsHaveName() {
        List<WebElement> products = getListWebElement(locInventory,5);
        List<WebElement> name = getListWebElement(locCardName,5);
        if(products.size() == name.size()) {
            Log.info("All products' cards in inventory have a name");
        } else if(products.size() > name.size()) {
            Log.fatal("Not all products' cards in inventory have a name");
        } else {
            Log.fatal("More names were found");
        }
    }

    public void productsCardsHaveImage() {
        List<WebElement> products = getListWebElement(locInventory,5);
        List<WebElement> image = getListWebElement(locCardImage,5);
        if(products.size() == image.size()) {
            Log.info("All products' cards in inventory have an image");
        } else if(products.size() > image.size()) {
            Log.fatal("Not all products' cards in inventory have an image");
        } else {
            Log.fatal("More images were found");
        }
    }

    public void productsCardsHaveDescription() {
        List<WebElement> products = getListWebElement(locInventory,5);
        List<WebElement> description = getListWebElement(locCardDescription,5);
        if(products.size() == description.size()) {
            Log.info("All products' cards in inventory have a description");
        } else if(products.size() > description.size()) {
            Log.fatal("Not all products' cards in inventory have a description");
        } else {
            Log.fatal("More descriptions were found");
        }
    }

    public void productsCardsHavePrice() {
        List<WebElement> products = getListWebElement(locInventory,5);
        List<WebElement> price = getListWebElement(locCardPrice,5);
        if(products.size() == price.size()) {
            Log.info("All products' cards in inventory have a price");
        } else if(products.size() > price.size()) {
            Log.fatal("Not all products' cards in inventory have a price");
        } else {
            Log.fatal("More prices were found");
        }
    }

    public void productsCardsHaveButton() {
        List<WebElement> products = getListWebElement(locInventory,5);
        List<WebElement> button = getListWebElement(locCardButton,5);
        if(products.size() == button.size()) {
            Log.info("All products' cards in inventory have a cart button");
        } else if(products.size() > button.size()) {
            Log.fatal("Not all products' cards in inventory have a cart button");
        } else {
            Log.fatal("More cart buttons were found");
        }
    }

    public void isSortOption() {
        try{
            WebElement sortOption = getElement(locSortOption,5);
            Log.info("Sort option found");
        } catch(Exception e) {
            Log.fatal("Sort option not found");
        }
    }

    public String toProductPage(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Product id: "+ p +" - "+product);
        getElement(locNameInCard,5).click();
        return product;
    }

    public void toInventoryFromProduct() {
        Log.info("Clicking in 'back to products' button");
        getElement(locBackInPage,5).click();
    }

    public String currentPage() {
        String page = getElement(locNameInPage,5).getText();
        Log.info("Current product's page is: "+page);
        return page;
    }

    public void isNameInPage() {
        try{
            WebElement name = getElement(locNameInPage,5);
            Log.info("Product's name found");
        } catch(Exception e) {
            Log.fatal("Product's name not found");
        }
    }

    public void isImageInPage() {
        try{
            WebElement image = getElement(locImageInPage,5);
            Log.info("Product's image found");
        } catch(Exception e) {
            Log.fatal("Product's image not found");
        }
    }

    public void isDescriptionInPage() {
        try{
            WebElement description = getElement(locDescriptionInPage,5);
            Log.info("Product's description found");
        } catch(Exception e) {
            Log.fatal("Product's description not found");
        }
    }

    public void isPriceInPage() {
        try{
            WebElement price = getElement(locPriceInPage,5);
            Log.info("Product's price found");
        } catch(Exception e) {
            Log.fatal("Product's price not found");
        }
    }

    public void isButtonInPage() {
        try{
            WebElement button = getElement(locButtonInPage,5);
            Log.info("Product's 'add to cart' button found");
        } catch(Exception e) {
            Log.fatal("Product's 'add to cart' button not found");
        }
    }

    public void isBackInPage() {
        try{
            WebElement back = getElement(locBackInPage,5);
            Log.info("Product's 'back to products' button found");
        } catch(Exception e) {
            Log.fatal("Product's 'back to products' button not found");
        }
    }

    public void showMeTheList(List<String> list) {
        for(int i = 0; i < list.size(); i++) {
            Log.info("Product "+ (i+1) +" is "+ list.get(i));
        }
    }
}

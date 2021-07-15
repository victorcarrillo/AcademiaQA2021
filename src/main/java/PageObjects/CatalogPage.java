package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends BasePage{
    /* LOCATORS */
    By locInventory = By.xpath(props.getProperty("inventory_items"));
    By locSortOption = By.xpath(props.getProperty("sort_option"));
    By locSortAZ = By.xpath(props.getProperty("option_sort_az"));
    By locSortZA = By.xpath(props.getProperty("option_sort_za"));
    By locSortLoHi = By.xpath(props.getProperty("option_sort_lohi"));
    By locSortHiLo = By.xpath(props.getProperty("option_sort_hilo"));

    By locCartContainer = By.xpath(props.getProperty("container_cart"));

    By locCardName = By.xpath(props.getProperty("card_name"));
    By locCardImage = By.xpath(props.getProperty("card_image"));
    By locCardDescription = By.xpath(props.getProperty("card_description"));
    By locCardPrice = By.xpath(props.getProperty("card_price"));
    By locCardButton = By.xpath(props.getProperty("card_button"));
    By locNameInCard = By.xpath(props.getProperty("link_to_page_1") + props.getProperty("product_one_id") + props.getProperty("link_to_page_2"));

    By locAddToCart = By.xpath(props.getProperty("add_to_cart_1")+props.getProperty("product_one_name")+props.getProperty("add_to_cart_2"));
    By locRemoveFromCart = By.xpath(props.getProperty("remove_from_cart_1")+props.getProperty("product_one_name")+props.getProperty("remove_from_cart_2"));

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

    public List<String> getNamesInInventory() {
        try {
            List<WebElement> names = getListWebElement(locCardName,5);
            Log.info("Products' names in inventory: "+names.size());
            return getListString(names);
        } catch(Exception e) {
            Log.fatal("Product's names not found");
        }
        return null;
    }

    public List<Float> getPricesInInventory() {
        try {
            List<WebElement> prices = getListWebElement(locCardPrice,5);
            Log.info("Products' prices in inventory: "+prices.size());
            List<String> pricesString = getListString(prices);
            return cleanPriceList(pricesString);
        } catch(Exception e) {
            Log.fatal("Product's prices not found");
        }
        return null;
    }

    public List<Float> cleanPriceList(List<String> pList) {
        List<Float> floatPrice = new ArrayList<Float>();
        String dirtyPrice;
        String cleanPrice;
        String currentPrice;
        try {
            Log.info("Cleaning '$' from price lists");
            for(int i = 0; i < pList.size(); i++) {
                dirtyPrice = pList.get(i);
                cleanPrice = "";
                for(int j = 1; j < dirtyPrice.length(); j++) {
                    cleanPrice = cleanPrice + dirtyPrice.charAt(j);
                }
                pList.set(i,cleanPrice);
            }
            Log.info("Converting String List to Float List");
            for(int i = 0; i < pList.size(); i++) {
                currentPrice = pList.get(i);
                floatPrice.add(Float.parseFloat(currentPrice));
            }
            return floatPrice;
        } catch(Exception e) {
            Log.fatal("Error getting prices");
        }
        return null;
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

    public void isAddTo(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Looking for 'add to cart' button in product id: "+ p +" - "+product);
        try {
            getElement(locAddToCart,5);
            Log.info("Button 'add to cart' found");
        } catch(Exception e) {
            Log.fatal("Button 'add to cart' not found");
        }
    }

    public void isRemove(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Looking for 'remove' button in product id: "+ p +" - "+product);
        try {
            getElement(locRemoveFromCart,5);
            Log.info("Button 'remove' found");
        } catch(Exception e) {
            Log.fatal("Button 'remove' not found");
        }
    }

    public void isAddToPage(String p) {
        String product = getElement(locNameInPage,5).getText();
        Log.info("Looking for 'add to cart' button in product id: "+ p +" - "+product);
        try {
            getElement(locAddToCart,5);
            Log.info("Button 'add to cart' found");
        } catch(Exception e) {
            Log.fatal("Button 'add to cart' not found");
        }
    }

    public void isRemovePage(String p) {
        String product = getElement(locNameInPage,5).getText();
        Log.info("Looking for 'remove' button in product id: "+ p +" - "+product);
        try {
            getElement(locRemoveFromCart,5);
            Log.info("Button 'remove' found");
        } catch(Exception e) {
            Log.fatal("Button 'remove' not found");
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

    public void sortProducts(String op) {
        try {
            Log.info("Opening sort options");
            getElement(locSortOption,5).click();
            switch(op) {
                case "az":
                    Log.info("Selecting 'a' to 'z' option");
                    getElement(locSortAZ,5).click();
                    break;
                case "za":
                    Log.info("Selecting 'z' to 'a' option");
                    getElement(locSortZA,5).click();
                    break;
                case "lohi":
                    Log.info("Selecting 'low' to 'high' option");
                    getElement(locSortLoHi,5).click();
                    break;
                case "hilo":
                    Log.info("Selecting 'high' to 'low' option");
                    getElement(locSortHiLo,5).click();
                    break;
            }
        } catch(Exception e) {
            Log.fatal("Not options in sort button found");
        }
    }

    public String addToCart(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Product id: "+ p +" - "+product +" added to cart");
        getElement(locAddToCart,5).click();
        return product;
    }

    public String removeFromCart(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Product id: "+ p +" - "+product +" removed from cart");
        getElement(locRemoveFromCart,5).click();
        return product;
    }

    public String addToCartPage(String p) {
        String product = getElement(locNameInPage,5).getText();
        Log.info("Product id: "+ p +" - "+product +" added to cart");
        getElement(locAddToCart,5).click();
        return product;
    }

    public String removeFromCartPage(String p) {
        String product = getElement(locNameInPage,5).getText();
        Log.info("Product id: "+ p +" - "+product +" removed from cart");
        getElement(locRemoveFromCart,5).click();
        return product;
    }

    public int productsInButtonCart() {
        String inCart = getElement(locCartContainer,5).getText();
        int n;
        if(inCart.isEmpty()) {
            Log.info("No products in cart");
            return 0;
        } else {
            n = Integer.parseInt(inCart);
            Log.info("Currently "+ n +" product(s) in cart");
            return n;
        }
    }

    public String toProductPage(String p) {
        String product = getElement(locNameInCard,5).getText();
        Log.info("Product id: "+ p +" - "+product);
        getElement(locNameInCard,5).click();
        return product;
    }

    public void toInventoryFromProduct() {
        getElement(locBackInPage,5).click();
        Log.info("'back' button found");
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

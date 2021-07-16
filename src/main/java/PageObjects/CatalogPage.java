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
            Log.info("products in inventory: "+products.size());
        } catch(Exception e) {
            Log.fatal("inventory is empty");
        }
    }

    public List<String> getNamesInInventory() {
        try {
            List<WebElement> names = getListWebElement(locCardName,5);
            Log.info("products' names in inventory: "+names.size());
            return getListString(names);
        } catch(Exception e) {
            Log.fatal("product's names not found");
        }
        return null;
    }

    public List<Float> getPricesInInventory() {
        try {
            List<WebElement> prices = getListWebElement(locCardPrice,5);
            Log.info("products' prices in inventory: "+prices.size());
            List<String> pricesString = getListString(prices);
            return cleanPriceList(pricesString);
        } catch(Exception e) {
            Log.fatal("product's prices not found");
        }
        return null;
    }

    public List<Float> cleanPriceList(List<String> pList) {
        List<Float> floatPrice = new ArrayList<>();
        String dirtyPrice;
        String cleanPrice;
        String currentPrice;
        try {
            Log.info("cleaning '$' from price lists");
            for(int i = 0; i < pList.size(); i++) {
                dirtyPrice = pList.get(i);
                cleanPrice = "";
                for(int j = 1; j < dirtyPrice.length(); j++) {
                    cleanPrice = cleanPrice + dirtyPrice.charAt(j);
                }
                pList.set(i,cleanPrice);
            }
            Log.info("converting String List to Float List");
            for(int i = 0; i < pList.size(); i++) {
                currentPrice = pList.get(i);
                floatPrice.add(Float.parseFloat(currentPrice));
            }
            return floatPrice;
        } catch(Exception e) {
            Log.fatal("error getting prices");
        }
        return null;
    }

    public void productsCardsHaveName() {
        List<WebElement> products = new ArrayList<>();
        List<WebElement> name = new ArrayList<>();

        try {
            Log.info("getting products in inventory");
            products = getListWebElement(locInventory,5);
        } catch (Exception e) {
            Log.fatal("products in inventory not found");
        }

        try {
            Log.info("getting names of products in inventory");
            name = getListWebElement(locCardName,5);
        } catch (Exception e) {
            Log.fatal("names in inventory not found");
        }

        Log.info("comparing products against names");
        if(products.size() == name.size()) {
            Log.info("all products' cards in inventory have a name");
        } else if(products.size() > name.size()) {
            Log.fatal("not all products' cards in inventory have a name");
        } else {
            Log.fatal("more names were found");
        }
    }

    public void productsCardsHaveImage() {
        List<WebElement> products = new ArrayList<>();
        List<WebElement> image = new ArrayList<>();

        try {
            Log.info("getting products in inventory");
            products = getListWebElement(locInventory,5);
        } catch (Exception e) {
            Log.fatal("products in inventory not found");
        }

        try {
            Log.info("getting images of products in inventory");
            image = getListWebElement(locCardImage,5);
        } catch (Exception e) {
            Log.fatal("images in inventory not found");
        }

        Log.info("comparing products against images");
        if(products.size() == image.size()) {
            Log.info("all products' cards in inventory have an image");
        } else if(products.size() > image.size()) {
            Log.fatal("not all products' cards in inventory have an image");
        } else {
            Log.fatal("more images were found");
        }
    }

    public void productsCardsHaveDescription() {
        List<WebElement> products = new ArrayList<>();
        List<WebElement> description = new ArrayList<>();

        try {
            Log.info("getting products in inventory");
            products = getListWebElement(locInventory,5);
        } catch (Exception e) {
            Log.fatal("products in inventory not found");
        }

        try {
            Log.info("getting descriptions of products in inventory");
            description = getListWebElement(locCardDescription,5);
        } catch (Exception e) {
            Log.fatal("descriptions not found");
        }

        Log.info("comparing products against descriptions");
        if(products.size() == description.size()) {
            Log.info("all products' cards in inventory have a description");
        } else if(products.size() > description.size()) {
            Log.fatal("not all products' cards in inventory have a description");
        } else {
            Log.fatal("more descriptions were found");
        }
    }

    public void productsCardsHavePrice() {
        List<WebElement> products = new ArrayList<>();
        List<WebElement> price = new ArrayList<>();

        try {
            Log.info("getting products in inventory");
            products = getListWebElement(locInventory,5);
        } catch (Exception e) {
            Log.fatal("products in inventory not found");
        }

        try {
            Log.info("getting prices of products in inventory");
            price = getListWebElement(locCardPrice,5);
        } catch (Exception e) {
            Log.fatal("prices in inventory not found");
        }

        Log.info("comparing products against prices");
        if(products.size() == price.size()) {
            Log.info("all products' cards in inventory have a price");
        } else if(products.size() > price.size()) {
            Log.fatal("not all products' cards in inventory have a price");
        } else {
            Log.fatal("more prices were found");
        }
    }

    public void productsCardsHaveButton() {
        List<WebElement> products = new ArrayList<>();
        List<WebElement> button = new ArrayList<>();

        try {
            Log.info("getting products in inventory");
            products = getListWebElement(locInventory,5);
        } catch (Exception e) {
            Log.fatal("products in inventory not found");
        }

        try {
            Log.info("getting buttons of products in inventory");
            button = getListWebElement(locCardButton,5);
        } catch (Exception e) {
            Log.fatal("buttons in inventory not found");
        }

        Log.info("comparing products against buttons");
        if(products.size() == button.size()) {
            Log.info("all products' cards in inventory have a cart button");
        } else if(products.size() > button.size()) {
            Log.fatal("not all products' cards in inventory have a cart button");
        } else {
            Log.fatal("more cart buttons were found");
        }
    }

    public String getProductName(By l) {
        String name;
        try {
            name = getElement(l,5).getText();
            Log.info("product name found");
            return name;
        } catch (Exception e) {
            Log.fatal("product name not found");
        }
        return null;
    }

    public void isAddTo(String p) {
        Log.info("getting product name");
        String product = getProductName(locNameInCard);

        Log.info("looking for add to cart button in product id: "+ p +" - "+product);
        try {
            getElement(locAddToCart,5);
            Log.info("button add to cart found");
        } catch(Exception e) {
            Log.fatal("button add to cart not found");
        }
    }

    public void isRemove(String p) {
        Log.info("getting product name");
        String product = getProductName(locCardName);

        Log.info("looking for remove button in product id: "+ p +" - "+product);
        try {
            getElement(locRemoveFromCart,5);
            Log.info("button remove found");
        } catch(Exception e) {
            Log.fatal("button remove not found");
        }
    }

    public void isAddToPage(String p) {
        Log.info("getting product name");
        String product = getProductName(locCardName);

        Log.info("looking for add to cart button in product id: "+ p +" - "+product);
        try {
            getElement(locAddToCart,5);
            Log.info("button add to cart found");
        } catch(Exception e) {
            Log.fatal("button add to cart not found");
        }
    }

    public void isRemovePage(String p) {
        Log.info("getting product name");
        String product = getProductName(locCardName);

        Log.info("looking for remove button in product id: "+ p +" - "+product);
        try {
            getElement(locRemoveFromCart,5);
            Log.info("button remove found");
        } catch(Exception e) {
            Log.fatal("button remove not found");
        }
    }

    public void isSortOption() {
        try{
            getElement(locSortOption,5);
            Log.info("sort option found");
        } catch(Exception e) {
            Log.fatal("sort option not found");
        }
    }

    public void sortProducts(String op) {
        try {
            Log.info("opening sort menu");
            getButton(locSortOption,5).click();
        } catch (Exception e) {
            Log.fatal("sort menu not found");
        }

        try {
            switch(op) {
                case "az":
                    Log.info("selecting a to z option");
                    getButton(locSortAZ,5).click();
                    break;
                case "za":
                    Log.info("selecting z to a option");
                    getButton(locSortZA,5).click();
                    break;
                case "lohi":
                    Log.info("selecting low to high option");
                    getButton(locSortLoHi,5).click();
                    break;
                case "hilo":
                    Log.info("selecting high to low option");
                    getButton(locSortHiLo,5).click();
                    break;
            }
        } catch(Exception e) {
            Log.fatal("not options in sort button found");
        }
    }

    public String addToCart(String p) {
        Log.info("getting product name");
        String product = getProductName(locNameInCard);

        try {
            Log.info("clicking add to car button");
            getButton(locAddToCart,5).click();
            Log.info("product id: "+ p +" - "+product +" added to cart");
            return product;
        } catch (Exception e) {
            Log.fatal("add to cart button not found");
        }
        return null;
    }

    public String removeFromCart(String p) {
        Log.info("getting product name");
        String product = getProductName(locNameInCard);

        try {
            Log.info("clicking remove button");
            getButton(locRemoveFromCart,5).click();
            Log.info("product id: "+ p +" - "+product +" removed from cart");
            return product;
        } catch (Exception e) {
            Log.fatal("remove button not found");
        }
        return null;
    }

    public String addToCartPage(String p) {
        Log.info("getting product name");
        String product = getProductName(locNameInPage);

        try {
            Log.info("clicking add to car button");
            getButton(locAddToCart,5).click();
            Log.info("Product id: "+ p +" - "+product +" added to cart");
            return product;
        } catch (Exception e) {
            Log.fatal("add to cart button not found");
        }
        return null;
    }

    public String removeFromCartPage(String p) {
        Log.info("getting product name");
        String product = getElement(locNameInPage,5).getText();

        try {
            Log.info("clicking remove button");
            getButton(locRemoveFromCart,5).click();
            Log.info("product id: "+ p +" - "+product +" removed from cart");
            return product;
        } catch (Exception e) {
            Log.fatal("remove button not found");
        }
        return null;
    }

    public int productsInButtonCart() {
        String inCart = "";
        int n;

        try {
            Log.info("getting number of products in cart");
            inCart = getElement(locCartContainer,5).getText();
        } catch (Exception e) {
            Log.fatal("products in cart container not found");
        }

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
        Log.info("getting product name");
        String product = getProductName(locNameInCard);

        Log.info("Product id: "+ p +" - "+product);
        try {
            Log.info("clicking product name");
            getButton(locNameInCard,5).click();
            return product;
        } catch (Exception e) {
            Log.fatal("product name not found");
        }
        return null;
    }

    public void toInventoryFromProduct() {
        try {
            Log.info("clicking back button");
            getButton(locBackInPage,5).click();
        } catch (Exception e) {
            Log.fatal("back button not found");
        }
    }

    public String currentPage() {
        String page;
        try {
            Log.info("getting name of current page");
            page = getElement(locNameInPage,5).getText();
            Log.info("current page is: "+page);
            return page;
        } catch (Exception e) {
            Log.fatal("page name not found");
        }
        return null;
    }

    public void isNameInPage() {
        try{
            getElement(locNameInPage,5);
            Log.info("product name found");
        } catch(Exception e) {
            Log.fatal("product name not found");
        }
    }

    public void isImageInPage() {
        try{
            getElement(locImageInPage,5);
            Log.info("product image found");
        } catch(Exception e) {
            Log.fatal("product image not found");
        }
    }

    public void isDescriptionInPage() {
        try{
            getElement(locDescriptionInPage,5);
            Log.info("product description found");
        } catch(Exception e) {
            Log.fatal("product description not found");
        }
    }

    public void isPriceInPage() {
        try{
            getElement(locPriceInPage,5);
            Log.info("product's price found");
        } catch(Exception e) {
            Log.fatal("product price not found");
        }
    }

    public void isButtonInPage() {
        try{
            getElement(locButtonInPage,5);
            Log.info("add to cart button found");
        } catch(Exception e) {
            Log.fatal("add to cart button not found");
        }
    }

    public void isBackInPage() {
        try{
            getElement(locBackInPage,5);
            Log.info("back to products button found");
        } catch(Exception e) {
            Log.fatal("back to products button not found");
        }
    }
}

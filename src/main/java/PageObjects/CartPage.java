package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage{
    /* LOCATORS */
    By locCartButton = By.xpath(props.getProperty("container_cart"));

    By locCartList = By.xpath(props.getProperty("cart_list"));
    By locBackButton = By.xpath(props.getProperty("back_button"));
    By locCheckoutButton = By.xpath(props.getProperty("checkout_button"));

    By locQuantitiesInCart = By.xpath(props.getProperty("quantity_in_cart"));
    By locNamesInCart = By.xpath(props.getProperty("name_in_cart"));
    By locDescriptionsInCart = By.xpath(props.getProperty("description_in_cart"));
    By locPricesInCart = By.xpath(props.getProperty("price_in_cart"));
    By locRemovesInCart = By.xpath(props.getProperty("remove_in_cart"));

    By locNameInCart = By.xpath(props.getProperty("link_to_page_1") + props.getProperty("product_one_id") + props.getProperty("link_to_page_2"));
    By locRemoveInCart = By.xpath(props.getProperty("remove_from_cart_1")+props.getProperty("product_one_name")+props.getProperty("remove_from_cart_2"));

    By locCheckoutFirstName = By.xpath(props.getProperty("checkout_first_name"));
    By locCheckoutLastName = By.xpath(props.getProperty("checkout_last_name"));
    By locCheckoutZip = By.xpath(props.getProperty("checkout_zip"));
    By locCheckoutCancel = By.xpath(props.getProperty("checkout_cancel"));
    By locCheckoutContinue = By.xpath(props.getProperty("checkout_confirm"));

    By locOverviewCartList = By.xpath(props.getProperty("overview_cart_list"));
    By locOverviewSummaryInfo = By.xpath(props.getProperty("overview_summary_info"));
    By locOverviewCancel = By.xpath(props.getProperty("overview_cancel"));
    By locOverviewFinish = By.xpath(props.getProperty("overview_finish"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void toCart() {
        getElement(locCartButton,5).click();
        Log.info("going to cart page");
    }

    public void continueShopping() {
        getElement(locBackButton,5).click();
        Log.info("going to catalog page");
    }

    public void checkoutBuy() {
        getElement(locCheckoutButton,5).click();
        Log.info("going to checkout page");
    }

    public void isCartList() {
        try{
            WebElement cartList = getElement(locCartList,5);
            Log.info("'cart list' found");
        } catch(Exception e) {
            Log.fatal("'cart list' not found");
        }
    }

    public void isBackButton() {
        try{
            WebElement back = getElement(locBackButton,5);
            Log.info("'back' button not found");
        } catch(Exception e) {
            Log.fatal("'back' button not found");
        }
    }

    public void isCheckoutButton() {
        try{
            WebElement checkout = getElement(locCheckoutButton,5);
            Log.info("'checkout' button found");
        } catch(Exception e) {
            Log.fatal("'checkout' button not found");
        }
    }

    public void isCheckoutFirstName() {
        try {
            WebElement firstName = getElement(locCheckoutFirstName,5);
            Log.info("'first name' field found");
        } catch(Exception e){
            Log.fatal("'first name' field not found");
        }
    }

    public void isCheckoutLastName() {
        try {
            WebElement lastName = getElement(locCheckoutLastName,5);
            Log.info("'last name' field found");
        } catch(Exception e){
            Log.fatal("'last name' field not found");
        }
    }

    public void isCheckoutZip() {
        try {
            WebElement zip = getElement(locCheckoutZip,5);
            Log.info("'zip/postal code' field found");
        } catch(Exception e){
            Log.fatal("'zip/postal code' field not found");
        }
    }

    public void isCheckoutCancel() {
        try {
            WebElement cancel = getElement(locCheckoutCancel,5);
            Log.info("'cancel checkout' button found");
        } catch(Exception e){
            Log.fatal("'cancel checkout' button not found");
        }
    }

    public void isCheckoutContinue() {
        try {
            WebElement continueCheckout = getElement(locCheckoutContinue,5);
            Log.info("'continue checkout' button found");
        } catch(Exception e){
            Log.fatal("'continue checkout' button not found");
        }
    }

    public int howManyQuantityInCart() {
        try {
            List<WebElement> quantity = getListWebElement(locQuantitiesInCart,5);
            int n = quantity.size();
            Log.info(n+" 'quantity' elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements 'quantity' were not found");
        }
        return 0;
    }

    public int howManyNamesInCart() {
        try {
            List<WebElement> names = getListWebElement(locNamesInCart,5);
            int n = names.size();
            Log.info(n+" 'name' elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements 'name' were not found");
        }
        return 0;
    }

    public int howManyDescriptionsInCart() {
        try {
            List<WebElement> desc = getListWebElement(locDescriptionsInCart,5);
            int n = desc.size();
            Log.info(n+" 'description' elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements 'description' were not found");
        }
        return 0;
    }

    public int howManyPricesInCart() {
        try {
            List<WebElement> prices = getListWebElement(locPricesInCart,5);
            int n = prices.size();
            Log.info(n+" 'price' elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements 'price' were not found");
        }
        return 0;
    }

    public int howManyRemovesInCart() {
        try {
            List<WebElement> removes = getListWebElement(locRemovesInCart,5);
            int n = removes.size();
            Log.info(n+" 'remove' buttons found");
            return n;
        } catch(Exception e) {
            Log.fatal("button 'remove' were not found");
        }
        return 0;
    }

    public String removeInCart(String p) {
        String product = getElement(locNameInCart,5).getText();
        Log.info("Product id: "+ p +" - "+product +" removed from cart");
        getElement(locRemoveInCart,5).click();
        return product;
    }

    public void fillCheckoutForm(String first, String last, String zip) {
        Log.info("Filling up field 'first name' with: "+first);
        getElement(locCheckoutFirstName,5).sendKeys(first);

        Log.info("Filling up field 'last name' with: "+last);
        getElement(locCheckoutLastName,5).sendKeys(last);

        Log.info("Filling up field 'zip' with: "+zip);
        getElement(locCheckoutZip,5).sendKeys(zip);

        Log.info("Clicking button 'continue'");
        getElement(locCheckoutContinue,5).click();
    }

    public void cancelCheckout() {
        getElement(locCheckoutCancel,5).click();
        Log.info("going to cart page");
    }

    public void cancelOverview() {
        getElement(locOverviewCancel,5).click();
        Log.info("going to catalog page");
    }

    public void finishOverview() {
        getElement(locOverviewFinish,5).click();
        Log.info("going to checkout complete page");
    }

    public void isOverviewCartList() {
        try{
            WebElement cartList = getElement(locOverviewCartList,5);
            Log.info("'cart list' found");
        } catch(Exception e) {
            Log.fatal("'cart list' not found");
        }
    }

    public void isOverviewSummaryInfo() {
        try{
            WebElement summaryInfo = getElement(locOverviewSummaryInfo,5);
            Log.info("'summary info' found");
        } catch(Exception e) {
            Log.fatal("'summary info' not found");
        }
    }

    public void isOverviewCancel() {
        try{
            WebElement cancel = getElement(locOverviewCancel,5);
            Log.info("'cancel' button found");
        } catch(Exception e) {
            Log.fatal("'cancel' button not found");
        }
    }

    public void isOverviewFinish() {
        try{
            WebElement finish = getElement(locOverviewFinish,5);
            Log.info("'finish' button found");
        } catch(Exception e) {
            Log.fatal("'finish' button not found");
        }
    }
}

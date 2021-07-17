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
        try {
            getButton(locCartButton,5).click();
            Log.info("going to cart page");
        } catch (Exception e) {
            Log.fatal("cart button not found");
        }
    }

    public void continueShopping() {
        try {
            getButton(locBackButton,5).click();
            Log.info("going to catalog page");
        } catch (Exception e) {
            Log.fatal("continue shopping button not found");
        }
    }

    public void checkoutBuy() {
        try {
            getButton(locCheckoutButton,5).click();
            Log.info("going to checkout page");
        } catch (Exception e) {
            Log.fatal("checkout button not found");
        }
    }

    public boolean isCartList() {
        try{
            getElement(locCartList,5);
            Log.info("cart list found");
            return true;
        } catch(Exception e) {
            Log.fatal("cart list not found");
            return false;
        }
    }

    public boolean isBackButton() {
        try{
            getElement(locBackButton,5);
            Log.info("back button not found");
            return true;
        } catch(Exception e) {
            Log.fatal("back button not found");
            return false;
        }
    }

    public boolean isCheckoutButton() {
        try{
            getElement(locCheckoutButton,5);
            Log.info("checkout button found");
            return true;
        } catch(Exception e) {
            Log.fatal("checkout button not found");
            return false;
        }
    }

    public boolean isCheckoutFirstName() {
        try {
            getElement(locCheckoutFirstName,5);
            Log.info("first name field found");
            return true;
        } catch(Exception e){
            Log.fatal("first name field not found");
            return false;
        }
    }

    public boolean isCheckoutLastName() {
        try {
            getElement(locCheckoutLastName,5);
            Log.info("last name field found");
            return true;
        } catch(Exception e){
            Log.fatal("last name field not found");
            return false;
        }
    }

    public boolean isCheckoutZip() {
        try {
            getElement(locCheckoutZip,5);
            Log.info("zip/postal code field found");
            return true;
        } catch(Exception e){
            Log.fatal("zip/postal code field not found");
            return false;
        }
    }

    public boolean isCheckoutCancel() {
        try {
            getElement(locCheckoutCancel,5);
            Log.info("cancel checkout button found");
            return true;
        } catch(Exception e){
            Log.fatal("cancel checkout button not found");
            return false;
        }
    }

    public boolean isCheckoutContinue() {
        try {
            getElement(locCheckoutContinue,5);
            Log.info("continue checkout button found");
            return true;
        } catch(Exception e){
            Log.fatal("continue checkout button not found");
            return false;
        }
    }

    public int howManyQuantityInCart() {
        try {
            List<WebElement> quantity = getListWebElement(locQuantitiesInCart,5);
            int n = quantity.size();
            Log.info(n+" quantity elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements quantity were not found");
        }
        return 0;
    }

    public int howManyNamesInCart() {
        try {
            List<WebElement> names = getListWebElement(locNamesInCart,5);
            int n = names.size();
            Log.info(n+" name elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements name were not found");
        }
        return 0;
    }

    public int howManyDescriptionsInCart() {
        try {
            List<WebElement> desc = getListWebElement(locDescriptionsInCart,5);
            int n = desc.size();
            Log.info(n+" description elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements description were not found");
        }
        return 0;
    }

    public int howManyPricesInCart() {
        try {
            List<WebElement> prices = getListWebElement(locPricesInCart,5);
            int n = prices.size();
            Log.info(n+" price elements found");
            return n;
        } catch(Exception e) {
            Log.fatal("elements price were not found");
        }
        return 0;
    }

    public int howManyRemovesInCart() {
        try {
            List<WebElement> removes = getListWebElement(locRemovesInCart,5);
            int n = removes.size();
            Log.info(n+" remove buttons found");
            return n;
        } catch(Exception e) {
            Log.fatal("button remove were not found");
        }
        return 0;
    }

    public String removeInCart(String p) {
        String product;
        try {
            product = getElement(locNameInCart, 5).getText();
            Log.info("product id: " + p + " - " + product + " removed from cart");
        } catch (Exception e) {
            Log.fatal("product name not found");
            return null;
        }

        try {
            getButton(locRemoveInCart, 5).click();
            return product;
        } catch (Exception e) {
            Log.fatal("remove option not found");
        }
        return null;
    }

    public void fillCheckoutForm(String first, String last, String zip) {
        try {
            Log.info("filling up field first name with: "+first);
            getElement(locCheckoutFirstName,5).sendKeys(first);
        } catch (Exception e) {
            Log.fatal("first name field not found");
        }

        try {
            Log.info("filling up field last name with: "+last);
            getElement(locCheckoutLastName,5).sendKeys(last);
        } catch (Exception e) {
            Log.fatal("last name field not found");
        }

        try {
            Log.info("filling up field zip with: "+zip);
            getElement(locCheckoutZip,5).sendKeys(zip);
        } catch (Exception e) {
            Log.fatal("zip field not found");
        }

        try {
            Log.info("clicking button continue");
            getButton(locCheckoutContinue,5).click();
        } catch (Exception e) {
            Log.fatal("continue button not found");
        }
    }

    public void cancelCheckout() {
        try {
            getButton(locCheckoutCancel,5).click();
            Log.info("going to cart page");
        } catch (Exception e) {
            Log.fatal("cancel button not found");
        }
    }

    public void cancelOverview() {
        try {
            getButton(locOverviewCancel,5).click();
            Log.info("going to catalog page");
        } catch (Exception e) {
            Log.fatal("cancel button not found");
        }
    }

    public void finishOverview() {
        try {
            getButton(locOverviewFinish,5).click();
            Log.info("going to checkout complete page");
        } catch (Exception e) {
            Log.fatal("finish button not found");
        }
    }

    public boolean isOverviewCartList() {
        try{
            getElement(locOverviewCartList,5);
            Log.info("cart list found");
            return true;
        } catch(Exception e) {
            Log.fatal("cart list not found");
            return false;
        }
    }

    public boolean isOverviewSummaryInfo() {
        try{
            getElement(locOverviewSummaryInfo,5);
            Log.info("summary info found");
            return true;
        } catch(Exception e) {
            Log.fatal("summary info not found");
            return false;
        }
    }

    public boolean isOverviewCancel() {
        try{
            getElement(locOverviewCancel,5);
            Log.info("cancel button found");
            return true;
        } catch(Exception e) {
            Log.fatal("cancel button not found");
            return false;
        }
    }

    public boolean isOverviewFinish() {
        try{
            getElement(locOverviewFinish,5);
            Log.info("finish button found");
            return true;
        } catch(Exception e) {
            Log.fatal("finish button not found");
            return false;
        }
    }
}

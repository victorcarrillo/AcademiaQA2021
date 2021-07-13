package PageObjects;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

import java.util.List;

public class CartPage extends BasePage{

    By byHeaderCart = By.cssSelector("span.title");
    By byCheckoutButton = By.cssSelector("button#checkout");
    By byContinueButton = By.cssSelector("button#continue-shopping");
    By byProductsNameInCart = By.cssSelector("div.inventory_item_name");
    By byProductsDescriptionInCart = By.cssSelector("div.inventory_item_desc");
    By byProductsPriceInCart = By.cssSelector("div.inventory_item_price");
    By byButtonRemoveCart = By.cssSelector("div.item_pricebar>button");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public String CartHeader(){
        try {;
            Log.info("Getting Cart Header");
            WebElement header = getElementOfPresenceOfElementLocated(byHeaderCart, 5);
            String textHeader = header.getText();
            return textHeader;
        }
        catch(Exception e){
            Log.fatal("Element cart header is not found");
            return "";
        }
    }

    public boolean cartButtonsDisplayed(){
        WebElement checkoutButton = getElementOfPresenceOfElementLocated(byCheckoutButton, 5);
        WebElement continueButton = getElementOfPresenceOfElementLocated(byContinueButton, 5);
        if(checkoutButton.isDisplayed() && continueButton.isDisplayed()){
            Log.info("Buttons are present");
            return true;
        }
        else {
            Log.info("Buttons aren't present");
            return false;
        }
    }

    public void verifyProductIntoCar(JSONObject objectProduct, String messageButton){
        String nameProduct = objectProduct.getString("Product");
        String descriptionProduct = objectProduct.getString("Description");
        String priceProduct = objectProduct.getString("Price");

        List<WebElement> productsName = getListElements(byProductsNameInCart, 5);
        List<WebElement> productsDescription = getListElements(byProductsDescriptionInCart, 5);
        List<WebElement> productsPrice = getListElements(byProductsPriceInCart, 5);
        List<WebElement> buttonRemove = getListElements(byButtonRemoveCart, 5);

        String nameLastProductAdded = productsName.get(productsName.size()-1).getText();
        String descriptionLastProductAdded = productsDescription.get(productsDescription.size()-1).getText();
        String priceLastProductAdded = productsPrice.get(productsPrice.size()-1).getText();
        String buttonRemoveProduct = buttonRemove.get(buttonRemove.size()-1).getText();

        Log.info("Comparing product last product added: "+nameProduct +" vs "+nameLastProductAdded );
        Assert.assertEquals(nameProduct, nameLastProductAdded);
        Assert.assertEquals(descriptionProduct, descriptionLastProductAdded);
        Assert.assertEquals(priceProduct, priceLastProductAdded);
        Assert.assertEquals(messageButton.toUpperCase(), buttonRemoveProduct.toUpperCase());
    }

    public boolean verifyProductOutCar(String productName){
        Log.info("Validate if product "+ productName + " isn't present into car");
        if(!driver.findElements(byProductsNameInCart).isEmpty()){
            List<WebElement> productsName = getListElements(byProductsNameInCart, 5);
            for(int i=0; i < productsName.size(); i++)
            {
                String productN = productsName.get(i).getText();
                String[] element = productN.split("\\\n");

                if(element[0].equals(productName)){
                    Log.info("Product "+ productName + " is still present into car");
                    return false;
                }
            }
        }
        Log.info("Product "+ productName + " isn't present into car");
        return true;
    }

    public void clickButtonContinueShopping(){
        WebElement continueButton = getElementOfPresenceOfElementLocated(byContinueButton, 5);
        continueButton.click();
        Log.info("Continue shopping button clicked");
    }

    public void clickButtonCheckout(){
        WebElement checkoutButton = getElementOfPresenceOfElementLocated(byCheckoutButton, 5);
        checkoutButton.click();
        Log.info("Checkout button clicked");
    }
}

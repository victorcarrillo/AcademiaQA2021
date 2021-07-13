package PageObjects;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

import java.util.List;

public class StepTwoPage extends BasePage{

    By byHeader = By.cssSelector("span.title");
    By byProductsNameInCart = By.cssSelector("div.inventory_item_name");
    By byProductsDescriptionInCart = By.cssSelector("div.inventory_item_desc");
    By byProductsPriceInCart = By.cssSelector("div.inventory_item_price");
    By byPaymentInfo = By.xpath("//div[@class=\"summary_info\"]/div[2]");
    By byShippingInfo = By.xpath("//div[@class=\"summary_info\"]/div[4]");
    By byItemTotalSub = By.cssSelector("div.summary_subtotal_label");
    By byTax = By.cssSelector("div.summary_tax_label");
    By byTotal = By.cssSelector("div.summary_total_label");
    By byCancelButton = By.cssSelector("button#cancel");


    public StepTwoPage(WebDriver driver){
        super(driver);
    }

    public String StepTwoHeader(){
        try {;
            Log.info("Getting Step Two Header");
            WebElement header = getElementOfPresenceOfElementLocated(byHeader, 5);
            String textHeader = header.getText();
            return textHeader;
        }
        catch(Exception e){
            Log.fatal("Element Step One Header is not found");
            return "";
        }
    }

    public void verifyProductIntoPurchase(JSONObject objectProduct){
        String nameProduct = objectProduct.getString("Product");
        String descriptionProduct = objectProduct.getString("Description");
        String priceProduct = objectProduct.getString("Price");

        List<WebElement> productsName = getListElements(byProductsNameInCart, 5);
        List<WebElement> productsDescription = getListElements(byProductsDescriptionInCart, 5);
        List<WebElement> productsPrice = getListElements(byProductsPriceInCart, 5);

        String nameLastProductAdded = productsName.get(productsName.size()-1).getText();
        String descriptionLastProductAdded = productsDescription.get(productsDescription.size()-1).getText();
        String priceLastProductAdded = productsPrice.get(productsPrice.size()-1).getText();

        Log.info("Comparing product last product added: "+nameProduct +" vs "+nameLastProductAdded );
        Assert.assertEquals(nameProduct, nameLastProductAdded);
        Assert.assertEquals(descriptionProduct, descriptionLastProductAdded);
        Assert.assertEquals(priceProduct, priceLastProductAdded);
    }

    public void validatePaymentInfo(String paymentInfo){
        WebElement payment = getElementOfPresenceOfElementLocated(byPaymentInfo, 5);
        String info = payment.getText();
        Log.info("Comparing expected payment information "+paymentInfo);
        Assert.assertEquals(info, paymentInfo);
    }

    public void validateShippingInfo(String shippingInfo){
        WebElement shipping = getElementOfPresenceOfElementLocated(byShippingInfo, 5);
        String info = shipping.getText();
        Log.info("Comparing expected shipping information "+shippingInfo);
        Assert.assertEquals(info, shippingInfo);
    }

    public void validateCorrectValuesPrice(JSONObject objectProduct){
        String priceProduct = objectProduct.getString("Price");
        WebElement itemSubTotal = getElementOfPresenceOfElementLocated(byItemTotalSub, 5);
        String itSubTotal = itemSubTotal.getText();

        String[] priceArray = priceProduct.split(java.util.regex.Pattern.quote("$"));
        String[] itTotalArray = itSubTotal.split(java.util.regex.Pattern.quote("$"));
        Log.info("Comparing expected item total "+priceProduct +" vs " +itSubTotal);
        Assert.assertEquals(priceArray[1], itTotalArray[1]);

        Log.info("Getting tax");
        double totItem = Double.parseDouble(priceArray[1]);
        double taxPurchase = (totItem * .08);
        double roundTaxDbl = Math.round(taxPurchase*100.0)/100.0;

        WebElement itemTax= getElementOfPresenceOfElementLocated(byTax, 5);
        String itTax = itemTax.getText();
        String[] taxArray = itTax.split(java.util.regex.Pattern.quote("$"));
        double taxDouble = Double.parseDouble(taxArray[1]);
        Log.info("Comparing expected tax "+roundTaxDbl + " vs "+taxDouble);
        Assert.assertEquals(roundTaxDbl, taxDouble);


        Log.info("Getting Total");
        double totalPurchase = (roundTaxDbl + totItem);
        WebElement itemTotal = getElementOfPresenceOfElementLocated(byTotal, 5);
        String itTotal = itemTotal.getText();
        String[] totalArray = itTotal.split(java.util.regex.Pattern.quote("$"));
        double totalDouble = Double.parseDouble(totalArray[1]);
        Log.info("Comparing expected total "+totalPurchase + " vs "+totalDouble);
        Assert.assertEquals(totalPurchase, totalDouble);
    }

    public void clickOnButtonCancel(){
        Log.info("Click on cancel button");
        WebElement cancelButton =  getElementOfPresenceOfElementLocated(byCancelButton, 5);
        cancelButton.click();
    }
}

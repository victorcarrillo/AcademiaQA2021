package PageObjects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryPage extends BasePage {

    By byInventoryContainer = By.cssSelector("div.inventory_container");
    By byInventoryItem = By.cssSelector("div.inventory_item");
    By bySelectFilterAZ = By.cssSelector("select.product_sort_container>option[value=\"az\"]");
    By bySelectFilterZA = By.cssSelector("select.product_sort_container>option[value=\"za\"]");
    By bySelectFilterLoHi = By.cssSelector("select.product_sort_container>option[value=\"lohi\"]");
    By bySelectFilterHiLo = By.cssSelector("select.product_sort_container>option[value=\"hilo\"]");
    By bySelectImageProductLink;
    String partIPL = "a#item_";
    String partIPL2 = "_img_link";
    By byAddToCar = By.cssSelector("div.inventory_item_container button");
    By byRemoveCar = By.cssSelector("div.inventory_item_container button");
    By byBackToProducts = By.cssSelector("button#back-to-products");
    By byNameProductInCar = By.cssSelector("div.inventory_details_name.large_size");


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

    public JSONArray checkProductsContainerInInventory(String productsData, String buttonText){
        Log.info("Validation of products in container");
        List<WebElement> products = getListElements(byInventoryItem, 5);

        JSONArray array = new JSONArray(productsData);
        for(int i=0; i < array.length(); i++)
        {
            String product = products.get(i).getText();
            String[] element = product.split("\\\n");

            JSONObject object = array.getJSONObject(i);
            Log.info("Comparing product: "+element[0] +" vs " +object.getString("Product"));
            Assert.assertEquals(object.getString("Product"), element[0]);
            Assert.assertEquals(object.getString("Description"), element[1]);
            Assert.assertEquals(object.getString("Price"), element[2]);
            Assert.assertEquals(buttonText.toUpperCase(), element[3].toUpperCase());
        }
        return array;
    }

    public void sortProducts(String sortedBy,JSONArray arrayProducts){
        ArrayList<String> auxList;
        List<WebElement> products;
        switch (sortedBy){
            case "az":
                Log.info("Product sort: A TO Z");
                WebElement AZFilter = getElementOfPresenceOfElementLocated(bySelectFilterAZ, 5);
                AZFilter.click();
                auxList = new ArrayList<String>();

                for(int i=0; i < arrayProducts.length(); i++)
                {
                    JSONObject object = arrayProducts.getJSONObject(i);
                    auxList.add(object.getString("Product"));
                }
                Collections.sort(auxList);
                products = getListElements(byInventoryItem, 5);
                Log.info("Validation sorted products");
                for(int i=0; i < products.size(); i++)
                {
                    String product = products.get(i).getText();
                    String[] element = product.split("\\\n");
                    Log.info("Sort product: "+element[0]);
                    Assert.assertEquals(auxList.get(i), element[0]);
                }
                break;
            case "za":
                Log.info("Product sort: Z TO A");
                WebElement ZAFilter = getElementOfPresenceOfElementLocated(bySelectFilterZA, 5);
                ZAFilter.click();
                auxList  = new ArrayList<String>();

                for(int i=0; i < arrayProducts.length(); i++)
                {
                    JSONObject object = arrayProducts.getJSONObject(i);
                    auxList.add(object.getString("Product"));
                }
                Collections.reverse(auxList);
                products = getListElements(byInventoryItem, 5);
                Log.info("Validation sorted products");
                for(int i=0; i < products.size(); i++)
                {
                    String product = products.get(i).getText();
                    String[] element = product.split("\\\n");
                    Log.info("Sort product: "+element[0]);
                    Assert.assertEquals(auxList.get(i), element[0]);
                }
                break;
            case "lohi":
                Log.info("Product sort: Low price to High price");
                WebElement LoHiFilter = getElementOfPresenceOfElementLocated(bySelectFilterLoHi, 5);
                LoHiFilter.click();
                auxList  = new ArrayList<String>();

                for(int i=0; i < arrayProducts.length(); i++)
                {
                    JSONObject object = arrayProducts.getJSONObject(i);
                    auxList.add(object.getString("Price"));
                }
                Collections.sort(auxList, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return extractInt(o1) - extractInt(o2);
                    }

                    int extractInt(String s) {
                        String num = s.replaceAll("\\D", "");
                        // return 0 if no digits found
                        return num.isEmpty() ? 0 : Integer.parseInt(num);
                    }
                });
                products = getListElements(byInventoryItem, 5);
                Log.info("Validation sorted products");
                for(int i=0; i < products.size(); i++)
                {
                    String product = products.get(i).getText();
                    String[] element = product.split("\\\n");
                    Log.info("Sort product by price: "+element[2]);
                    Assert.assertEquals(auxList.get(i), element[2]);
                }
                break;
            case "hilo":
                Log.info("Product sort:  price High to Low price");
                WebElement HiLoFilter = getElementOfPresenceOfElementLocated(bySelectFilterHiLo, 5);
                HiLoFilter.click();
                auxList  = new ArrayList<String>();

                for(int i=0; i < arrayProducts.length(); i++)
                {
                    JSONObject object = arrayProducts.getJSONObject(i);
                    auxList.add(object.getString("Price"));
                }
                Collections.sort(auxList, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return  extractInt(o2) - extractInt(o1);
                    }

                    int extractInt(String s) {
                        String num = s.replaceAll("\\D", "");
                        // return 0 if no digits found
                        return num.isEmpty() ? 0 : Integer.parseInt(num);
                    }
                });
                products = getListElements(byInventoryItem, 5);
                Log.info("Validation sorted products");
                for(int i=0; i < products.size(); i++)
                {
                    String product = products.get(i).getText();
                    String[] element = product.split("\\\n");
                    Log.info("Sort product by price: "+element[2]);
                    Assert.assertEquals(auxList.get(i), element[2]);
                }
                break;
        }
    }

    public String addToCarElement(String indexProduct, String messageCarButton){
        Log.info("Click on product with index: "+indexProduct);
        bySelectImageProductLink = By.cssSelector(partIPL+indexProduct+partIPL2);
        WebElement productImage = getElementOfPresenceOfElementLocated(bySelectImageProductLink, 5);
        productImage.click();
        WebElement productName = getElementOfPresenceOfElementLocated(byNameProductInCar, 5);
        String nameProduct = productName.getText();
        Log.info("Adding to car product " +nameProduct );
        WebElement addToCar = getElementOfPresenceOfElementLocated(byAddToCar, 5);
        addToCar.click();
        addToCar = getElementOfPresenceOfElementLocated(byAddToCar, 5);
        Log.info("Checking change in the button message");
        Assert.assertEquals(addToCar.getText().toUpperCase(),messageCarButton.toUpperCase());
        WebElement backProducts = getElementOfPresenceOfElementLocated(byBackToProducts, 5);
        Log.info("Going back to the products view");
        backProducts.click();
        return nameProduct;
    }

    public int checkProductInformation(JSONArray objectProducts, String productAdded){
        int index = -1;
        for(int i=0; i < objectProducts.length(); i++) {
            JSONObject object = objectProducts.getJSONObject(i);
            if(object.getString("Product").equals(productAdded)){
                index = i;
                break;
            }
        }
        if(index == -1){
            Log.fatal("Element added into car is not found");
            throw new IllegalArgumentException("Element added into car is not found");

        }
        return index;
    }

    public String removeCarElement(String indexProduct, String messageCarButton){
        Log.info("Click on product with index: "+indexProduct);
        bySelectImageProductLink = By.cssSelector(partIPL+indexProduct+partIPL2);
        WebElement productImage = getElementOfPresenceOfElementLocated(bySelectImageProductLink, 5);
        productImage.click();
        WebElement productName = getElementOfPresenceOfElementLocated(byNameProductInCar, 5);
        String nameProduct = productName.getText();
        Log.info("Remove to car product " +nameProduct );
        WebElement removeToCar = getElementOfPresenceOfElementLocated(byRemoveCar, 5);
        removeToCar.click();
        removeToCar = getElementOfPresenceOfElementLocated(byRemoveCar, 5);
        Log.info("Checking change in the button message");
        Assert.assertEquals(removeToCar.getText().toUpperCase(),messageCarButton.toUpperCase());
        WebElement backProducts = getElementOfPresenceOfElementLocated(byBackToProducts, 5);
        Log.info("Going back to the products view");
        backProducts.click();
        return nameProduct;
    }

}

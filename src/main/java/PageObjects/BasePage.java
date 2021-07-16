package PageObjects;

import Utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    WebElement toLocate;
    List<WebElement> toLocateMultiple;

    public int elementsTimeoutSeconds = 5;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public WebDriverWait wait (int secs){
        return new WebDriverWait(driver, secs);
    }


    public WebElement getElement(By by){
        boolean found = false;
        try{
            toLocate = wait(elementsTimeoutSeconds)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            found = true;
            return toLocate;

        }catch(TimeoutException e){
            Log.fatal("Element not found during execution. Test aborted.");
            Assert.assertTrue(found, "Element display verification");
        }
        return toLocate;
    }


    public List<WebElement> getElements(By by){
        boolean found = false;
        try{
            toLocateMultiple = wait(elementsTimeoutSeconds)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            found = true;
            return toLocateMultiple;

        }catch(TimeoutException e){
            Log.fatal("Elements not found during execution. Test aborted.");
            Assert.assertTrue(found, "Elements display verification");
        }
        return toLocateMultiple;
    }


    public boolean elementExists(By by){

        return getElement(by).isDisplayed();

    }


    public String getElementText(By by){

        if(elementExists(by)){
            return getElement(by).getText();
        }
        return "";
    }

    public void typeOnElement(String data, By locator){
        getElement(locator).sendKeys(data);
    }

    public void clickOnElement(By locator){
        getElement(locator).click();
    }


    public void toggleSelect(By locator, String criteria){
        toLocate = getElement(locator);
        Select filterSelect = new Select(toLocate);
        filterSelect.selectByValue(criteria);
    }


    public List<String> getTextList(List<WebElement> toExtract) {
        ArrayList<String> temporary = new ArrayList<String>();
        for(WebElement element: toExtract){
            temporary.add(element.getText());
        }
        return temporary;
    }

    public List<String> getSortedTextList(List<WebElement> toSort, String criteria){

        ArrayList<String> temporary = new ArrayList<String>();
        for(WebElement element:toSort){
            temporary.add(element.getText());
        }
        Collections.sort(temporary);
        if(criteria.equals("za")){
            Collections.reverse(temporary);
        }
        return temporary;

    }

    public Integer[] getNumbersList(List<WebElement> toExtract) {

        Integer[] temporary = new Integer[toExtract.size()];
        for(int i=0; i<toExtract.size(); i++){
            int toAdd = Integer.parseInt(cleanPriceTag(toExtract.get(i).getText()));
            temporary[i] = toAdd;
        }
        return temporary;

    }

    public Integer[] getSortedNumbersList(List<WebElement> toSort, String criteria){

        Integer[] temporary = new Integer[toSort.size()];
        for(int i=0; i<toSort.size(); i++){
            int toAdd = Integer.parseInt(cleanPriceTag(toSort.get(i).getText()));
            temporary[i] = toAdd;
        }
       Arrays.sort(temporary);
        if(criteria.equals("hilo")){
            //Collections.reverse(Arrays.asList(temporary));
        }
        return temporary;

    }


    public String cleanPriceTag(String toClean){
        String temp = toClean.replace("$","");
        return temp.replace(".99","");
    }



}

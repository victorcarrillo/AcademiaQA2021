package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{

    /**Locators**/
    By productsPageTitleLocator = By.cssSelector(".title");
    By productsPageImageLocator = By.cssSelector(".peek");

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public boolean validateLogin() {

        toLocate = getElement(productsPageImageLocator, 5);
        return toLocate.isDisplayed();

    }

    public boolean validateLogin(String expected){

            String actual = getElement(productsPageTitleLocator, 5).getText();
            boolean match = expected.equals(actual);
            if (!match){
                Log.info("Expected title: \""+ expected + "\"");
                Log.info("Actual title: \""+ actual + "\"");
                Log.fatal("Page title did not match witch expected result");
            }
            return match;

    }

}

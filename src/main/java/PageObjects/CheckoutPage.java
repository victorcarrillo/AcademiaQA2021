package PageObjects;

import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    //Locators
    By firstNameFieldLocator = By.cssSelector("*[data-test='firstName']");
    By lastNameFieldLocator = By.cssSelector("*[data-test='lastName']");
    By postalCodeFieldLocator = By.cssSelector("*[data-test='postalCode']");
    By continueButtLocator = By.cssSelector("*[data-test='continue']");
    By summaryLocator = By.cssSelector(".summary_total_label");
    By finishButtLocator = By.cssSelector("*[data-test='finish']");
    By completeHeaderLocator = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {

        super(driver);

    }

    public void validateStepOne() {
        Log.info("Validating step one form elements");
        toLocate = getElement(firstNameFieldLocator);
        Assert.assertTrue(toLocate.isDisplayed(), "Checkout step one validation");
        Log.info("Step one elements located");
    }

    public void stepOneFormSubmit(String firstName, String lastName, String zipCode) {
        typeOnElement(firstName, firstNameFieldLocator);
        typeOnElement(lastName, lastNameFieldLocator);
        typeOnElement(zipCode, postalCodeFieldLocator);
        clickOnElement(continueButtLocator);
    }

    public void validateSummary(){
        Log.info("Validating summary elements");
        toLocate = getElement(summaryLocator);
        Assert.assertTrue(toLocate.isDisplayed(), "Checkout summary validation");
        Log.info("Summary elements located");
    }

    public void finishCheckout(){
        clickOnElement(finishButtLocator);
    }

    public void validateCheckout(){
        Log.info("Validating finished checkout elements");
        toLocate = getElement(completeHeaderLocator);
        Assert.assertTrue(toLocate.isDisplayed(), "Checkout finish validation");
        Log.info("Finished checkout located");
    }
}
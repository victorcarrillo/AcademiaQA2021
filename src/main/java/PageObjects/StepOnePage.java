package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

public class StepOnePage extends BasePage{

    By byHeader = By.cssSelector("span.title");
    By byContinueButton = By.cssSelector("input#continue");
    By byCancelButton = By.cssSelector("button#cancel");
    By byMessageErr = By.cssSelector("div.error-message-container.error >h3");
    By byNameInput = By.cssSelector("input#first-name");
    By byLastNameInput = By.cssSelector("input#last-name");
    By byPostalCodeInput = By.cssSelector("input#postal-code");


    public StepOnePage(WebDriver driver){
        super(driver);
    }

    public String StepOneHeader(){
        try {;
            Log.info("Getting Step One Header");
            WebElement header = getElementOfPresenceOfElementLocated(byHeader, 5);
            String textHeader = header.getText();
            return textHeader;
        }
        catch(Exception e){
            Log.fatal("Element Step One Header is not found");
            return "";
        }
    }

    public void clickOnButtonCancel(){
        Log.info("Click on cancel button");
        WebElement cancelButton =  getElementOfPresenceOfElementLocated(byCancelButton, 5);
        cancelButton.click();
    }

    public void clickOnButtonContinue(){
        Log.info("Click on continue button");
        WebElement continueButton =  getElementOfPresenceOfElementLocated(byContinueButton, 5);
        continueButton.click();
    }

    public void validateErrorMessage(String message){
        Log.info("Comparing error message: "+ message);
        WebElement messageError = getElementOfPresenceOfElementLocated(byMessageErr,5);
        Assert.assertEquals(messageError.getText(), message);
    }

    public void insertName(String name){
        Log.info("Insert "+ name+" name");
        WebElement nameInput =  getElementOfPresenceOfElementLocated(byNameInput, 5);
        nameInput.sendKeys(name);
    }

    public void insertLastName(String lastName){
        Log.info("Insert "+ lastName+" last name");
        WebElement lastNameInput =  getElementOfPresenceOfElementLocated(byLastNameInput, 5);
        lastNameInput.sendKeys(lastName);
    }

    public void insertPostalCode(String postalCode){
        Log.info("Insert "+ postalCode+" postal code");
        WebElement postalCodeInput =  getElementOfPresenceOfElementLocated(byPostalCodeInput, 5);
        postalCodeInput.sendKeys(postalCode);
    }
}

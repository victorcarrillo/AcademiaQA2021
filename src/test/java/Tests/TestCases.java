package Tests;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.FilterOptions;
import utilities.Log;

import java.util.List;

public class TestCases extends BaseTest{
    @Test(testName = "Success Login")
    public void successLogin(){
        driver.get(props.getProperty("test_url"));

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        Log.info( "Executing Test Case Success Login... ");
        loginPage.userNameInput().sendKeys(props.getProperty("username"));
        loginPage.passwordInput().sendKeys(props.getProperty("password"));
        loginPage.loginButton().click();

        Assert.assertTrue(homePage.existInventoryContainer());
    }

    @Test(testName = "Unsuccessful Login")
    public void unsuccessfulLogin(){
        driver.get(props.getProperty("test_url"));

        LoginPage loginPage = new LoginPage(driver);

        Log.info( "Executing Test Case Unsuccessful Login... ");
        loginPage.userNameInput().sendKeys(props.getProperty("username_invalid"));
        loginPage.passwordInput().sendKeys(props.getProperty("password_invalid"));
        loginPage.loginButton().click();

        String error = loginPage.loginErrorMessage().getText();

        Assert.assertEquals(error,"Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "Missing username")
    public void missingUsername(){
        driver.get(props.getProperty("test_url"));

        LoginPage loginPage = new LoginPage(driver);

        Log.info( "Executing Test Case Missing username... ");
        loginPage.userNameInput().sendKeys("");
        loginPage.passwordInput().sendKeys(props.getProperty("password"));
        loginPage.loginButton().click();

        String error = loginPage.loginErrorMessage().getText();

        Assert.assertEquals(error,"Epic sadface: Username is required");
    }

    @Test(testName = "Missing password")
    public void missingPassword(){
        driver.get(props.getProperty("test_url"));

        LoginPage loginPage = new LoginPage(driver);

        Log.info( "Executing Test Case Missing password... ");
        loginPage.userNameInput().sendKeys(props.getProperty("username"));
        loginPage.passwordInput().sendKeys("");
        loginPage.loginButton().click();

        String error = loginPage.loginErrorMessage().getText();

        Assert.assertEquals(error,"Epic sadface: Password is required");
    }

    @Test(testName = "Order list low to High")
    public void orderListLowHigh(){
        HomePage homePage = new HomePage(driver);

        successLogin();

        homePage.selectLowHighFilter(FilterOptions.lohi);
        Assert.assertTrue(homePage.lohiValidation());
    }
}
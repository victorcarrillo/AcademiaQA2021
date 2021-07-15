package Tests;

import PageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

public class LoginTests extends BaseTest{
    String URL;
    String username;
    String password;


    @Test
    public void validLoginTest(){
        Log.startLog("Caso de prueba 1: login estandar");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        loginPage.validateLogin();
    }

    @Test
    public void invalidLoginTest(){
        Log.startLog("Caso de prueba 2: login bloqueado");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("invalidUsername");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        Assert.assertTrue(loginPage.validateBlockedLogin());
    }

}

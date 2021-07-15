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
        Assert.assertTrue(loginPage.validateLogin());
    }

    @Test
    public void invalidLoginTest(){
        Log.startLog("Caso de prueba 6: login bloqueado");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("invalidUsername");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        Assert.assertTrue(loginPage.validateErrorMessage("locked out"));
    }

    @Test
    public void emptyPasswordTest(){
        Log.startLog("Caso de prueba 3: login con contraseña vacía");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, "");
        Assert.assertTrue(loginPage.validateErrorMessage("Password is required"));
    }

    @Test
    public void emptyUsernameTest(){
        Log.startLog("Caso de prueba 2: login con nombre de usuario vacío");

        URL = prop.getProperty("webApp");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials("", password);
        Assert.assertTrue(loginPage.validateErrorMessage("Username is required"));
    }

    @Test
    public void wrongPasswordTest(){
        Log.startLog("Caso de prueba 4: login con contraseña incorrecta");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, "falsePassword");
        Assert.assertTrue(loginPage.validateErrorMessage("do not match"));
    }

    @Test
    public void wrongUsernameTest(){
        Log.startLog("Caso de prueba 5: login con nombre de usuario incorrecto");

        URL = prop.getProperty("webApp");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials("falseUsername", password);
        Assert.assertTrue(loginPage.validateErrorMessage("do not match"));
    }

}

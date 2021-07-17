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
        logger = extent.createTest("Caso de prueba 1", "Login estandar");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateLogin());
        logger.info("Login exitoso");
    }

    @Test
    public void invalidLoginTest(){
        Log.startLog("Caso de prueba 6: login bloqueado");
        logger = extent.createTest("Caso de prueba 6", "Login estandar");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("invalidUsername");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, password);
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateErrorMessage("locked out"));
        logger.info("Login bloqueado exitosamente");
    }

    @Test
    public void emptyPasswordTest(){
        Log.startLog("Caso de prueba 3: login con contraseña vacía");
        logger = extent.createTest("Caso de prueba 3", "Login con contraseña vacía");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, "");
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateErrorMessage("Password is required"));
        logger.info("Mensaje de error mostrado correctamente");
    }

    @Test
    public void emptyUsernameTest(){
        Log.startLog("Caso de prueba 2: login con nombre de usuario vacío");
        logger = extent.createTest("Caso de prueba 2","Login con nombre de usuario vacío");

        URL = prop.getProperty("webApp");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials("", password);
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateErrorMessage("Username is required"));
        logger.info("Mensaje de error mostrado correctamente");
    }

    @Test
    public void wrongPasswordTest(){
        Log.startLog("Caso de prueba 4: login con contraseña incorrecta");
        logger = extent.createTest("Caso de prueba 4", "login con contraseña incorrecta");

        URL = prop.getProperty("webApp");
        username = prop.getProperty("username");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials(username, "falsePassword");
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateErrorMessage("do not match"));
        logger.info("Mensaje de error mostrado correctamente");
    }

    @Test
    public void wrongUsernameTest(){
        Log.startLog("Caso de prueba 5: login con nombre de usuario incorrecto");
        logger = extent.createTest("Caso de prueba 5", "Login con nombre de usuario incorrecto");

        URL = prop.getProperty("webApp");
        password = prop.getProperty("password");

        driver.get(URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputCredentials("falseUsername", password);
        logger.info("Inserción de datos correcta");
        Assert.assertTrue(loginPage.validateErrorMessage("do not match"));
        logger.info("Mensaje de error mostrado correctamente");
    }

}

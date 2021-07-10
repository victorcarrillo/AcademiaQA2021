package Tests.Frontend;

import PageObjects.BaseTest;
import PageObjects.InventoryPage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

public class LoginTests extends BaseTest {

    /** Test Data **/
    String WEBAPP;
    String USER;
    String PASSWORD;
    String ERROR_MESSAGE;

    @Test
    public void successLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.correct");

        Log.info("Start of success login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertCredentials(USER, PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.validateInventoryContainer());
        Log.info("End of success login Case");
    }

    @Test
    public void failedUserLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.incorrect");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        Log.info("Start of failed user login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertCredentials(USER, PASSWORD);

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed user login Case");
    }

    @Test
    public void failedPasswordLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.correct");
        PASSWORD = props.getProperty("password.incorrect");
        ERROR_MESSAGE = props.getProperty("login.messageError");

        Log.info("Start of failed password login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertCredentials(USER, PASSWORD);

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of failed password login Case");
    }

    @Test
    public void lockedLogin(){
        WEBAPP = props.getProperty("webapp");
        USER = props.getProperty("user.locked");
        PASSWORD = props.getProperty("password.correct");
        ERROR_MESSAGE = props.getProperty("login.lockedError");

        Log.info("Start of locked login Case");
        Log.info("Opening " + WEBAPP);
        driver.get(WEBAPP);

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.validateLoginLogo());
        loginPage.insertCredentials(USER, PASSWORD);

        loginPage.checkMessageEpicSadFace(ERROR_MESSAGE);
        Log.info("End of locked login Case");
    }
}

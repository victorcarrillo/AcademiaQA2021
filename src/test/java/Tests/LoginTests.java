package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.Log;

public class LoginTests extends BaseTest{
    String URL;
    String username;
    String password;


    @Test
    public void TestCase1(){
        Log.startLog("Caso de prueba 1: login estandar");

        URL = prop.getProperty("webApp");
        driver.get(URL);
    }

}

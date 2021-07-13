package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Log;

public class Footer extends BasePage {

    By byLinkTwitter = By.cssSelector("footer.footer li.social_twitter a");
    By byLinkFacebook = By.cssSelector("footer.footer li.social_facebook a");
    By byLinkLinkedin = By.cssSelector("footer.footer li.social_linkedin a");

    public Footer(WebDriver driver){
        super(driver);
    }

    public void checkURLTwitter(String twitter){
        WebElement linkTwitter = getElementOfPresenceOfElementLocated(byLinkTwitter, 5);
        Log.info("Check twitter url "+twitter +" in footer ");
        Assert.assertEquals(linkTwitter.getAttribute("href"), twitter);
    }

    public void checkURLFacebook(String face){
        WebElement linkFacebook = getElementOfPresenceOfElementLocated(byLinkFacebook, 5);
        Log.info("Check facebook url "+face +" in footer ");
        Assert.assertEquals(linkFacebook.getAttribute("href"), face);
    }

    public void checkURLLinkedin(String linkedin){
        WebElement linkLinkedin = getElementOfPresenceOfElementLocated(byLinkLinkedin, 5);
        Log.info("Check linkedin url "+linkedin +" in footer ");
        Assert.assertEquals(linkLinkedin.getAttribute("href"), linkedin);
    }
}

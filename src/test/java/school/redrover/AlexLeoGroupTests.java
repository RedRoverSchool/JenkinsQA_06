package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AlexLeoGroupTests extends BaseTest {

    @Test
    public void testVerifyLogoJenkinsIsPresent() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsPresent() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
       Assert.assertTrue(logoWord.isDisplayed());
    }

    @Test
    public void testVerifySearchFieldPresent() {
        WebElement searchField = getDriver().findElement(By.id("search-box"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testVerifyLogoutTextPresent() {
        String textLogout = getDriver().findElement(By.xpath("//span[contains(text(),'log out')]")).getText();
        Assert.assertEquals(textLogout, "log out");
    }
}

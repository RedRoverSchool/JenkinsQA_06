package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AlexLeoGroupTests extends BaseTest {

    @Test
    public void testVerifyLogoJenkinsIsPresentTC_001_33() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

   @Ignore
    @Test
    public void testVerifyWordIconJenkinsPresentTC_002_33() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
        Assert.assertEquals(logoWord.getAttribute("src"), "http://localhost:8081/static/bc90ee8a/images/title.svg");
    }
}

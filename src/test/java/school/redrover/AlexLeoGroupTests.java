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

    @Test
    public void testVerifyWordIconJenkinsPresentTC_002_33() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
       Assert.assertTrue(logoWord.isDisplayed());
    }
}

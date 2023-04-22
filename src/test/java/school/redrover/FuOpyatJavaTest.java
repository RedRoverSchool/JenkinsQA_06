package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FuOpyatJavaTest extends BaseTest {
    @Test
    public void testTitle() {

        WebElement title = getDriver().findElement(By.id("jenkins-name-icon"));

        Assert.assertEquals(title.getText(), "Jenkins");
    }

    @Test
    public void tesLogoIsDisplayed() {

        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(logo.isDisplayed());
    }
}

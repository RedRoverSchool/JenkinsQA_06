package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class QaAutomationJavaTest extends BaseTest {
    @Test
    public void testOne() {
        WebElement logo = getDriver().findElement(By.id("jenkins-name-icon"));

        Assert.assertTrue(logo.isDisplayed());
    }
}

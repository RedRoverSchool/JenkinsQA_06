package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipilineCreationTest extends BaseTest {

    @Test
    public void testPipelineCreation() throws InterruptedException {

        getDriver().findElement(By.cssSelector(".task:first-child")).click();

        getDriver().findElement(By.id("name")).sendKeys("Test Pipeline");
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getDriver().findElement(By.cssSelector("[name='Submit']")).click();

        getDriver().findElement(By.cssSelector(".jenkins-breadcrumbs__list-item:first-child .model-link")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Test Pipeline']")).isDisplayed());
    }
}

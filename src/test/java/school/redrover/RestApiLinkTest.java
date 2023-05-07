package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RestApiLinkTest extends BaseTest {

    @Test
    public void testFindRestApiInstruction() {

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'REST API')]"))).click();

        WebElement restApiInstructionTitle = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1[contains(text(),'REST API')]"));
        Assert.assertTrue(restApiInstructionTitle.isDisplayed(), "Element not found");

    }
}
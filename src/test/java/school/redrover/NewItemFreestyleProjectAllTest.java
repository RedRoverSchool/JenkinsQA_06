package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItemFreestyleProjectAllTest extends BaseTest {
    @Test
    public void testCreateFreestyleProjectWithValidData() {
        final String projectName = "My Freestyle Project";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys(projectName);
        getDriver().findElement(By.cssSelector(".icon-freestyle-project")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        getDriver().findElement(By.linkText("Dashboard")).click();

        String actualProjectNameOnDashboard = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a/span")))
                .getText();

        Assert.assertEquals(actualProjectNameOnDashboard, projectName);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateOrganizationFolderTest extends BaseTest {

    @Test
    public void testCreateOrganizationFolder() {
        final String expectedNewFolderName = "Project1";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='name']"))).sendKeys(expectedNewFolderName);
        getDriver().findElement(By.xpath("//li[@class='jenkins_branch_OrganizationFolder']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]")).click();

        String actualNewFolderName = getDriver().findElement(
                By.xpath("//a[@class='jenkins-table__link model-link inside']")).getText();

        Assert.assertEquals(actualNewFolderName, expectedNewFolderName);
    }
}

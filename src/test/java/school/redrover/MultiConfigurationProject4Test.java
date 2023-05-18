package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProject4Test extends BaseTest {
    @Test
    public void testDisableMultiConfigurationProject() {
        String expectedResult = "This project is currently disabled";

        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        getDriver().findElement(By.xpath("//a[@href='/job/MyProject/configure']")).click();

        WebElement enableToggle = getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[@id='toggle-switch-enable-disable-project']")));
        enableToggle.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement projectIsDisabledMessage = getWait2()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//form[@id='enable-project']")));

        Assert.assertEquals(projectIsDisabledMessage.getText().substring(0,34), expectedResult);
    }

    @Test(dependsOnMethods = {"testDisableMultiConfigurationProject"})
    public void testEnableMultiConfigurationProject() {
        getDriver().findElement(By.xpath("//a[@href='job/MyProject/']/span")).click();

        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();

        WebElement disableButton = getDriver().findElement(By.xpath("//button[text() = 'Disable Project']"));

        Assert.assertTrue(disableButton.isDisplayed());
    }
}

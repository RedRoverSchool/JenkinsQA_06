package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProject4Test extends BaseTest {
    private static final By SAVE_BUTTON_XPATH = By.xpath("//button[@name='Submit']");
    private static final By ENABLE_TOGGLE_XPATH = By.xpath("//span[@id='toggle-switch-enable-disable-project']");


    @Test
    public void testDisableMultiConfigurationProject() {
        String expectedResult = "This project is currently disabled";

        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        getDriver().findElement(By.xpath("//a[@href='/job/MyProject/configure']")).click();

        WebElement enableToggle = getWait5().until(ExpectedConditions.elementToBeClickable(ENABLE_TOGGLE_XPATH));
        enableToggle.click();

        WebElement saveButton = getDriver().findElement(SAVE_BUTTON_XPATH);
        saveButton.click();

        WebElement projectIsDisabledMessage = getWait2()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//form[@id='enable-project']")));

        Assert.assertEquals(projectIsDisabledMessage.getText().substring(0,34), expectedResult);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject16Test extends BaseTest {

    @Test
    public void testDisableFreestyleProject() {
        TestUtils.createFreestyleProject(this, "New Freestyle Project", true);
        getDriver().findElement(By.xpath("//a[@href ='job/New%20Freestyle%20Project/']/span")).click();

        WebElement disableProject = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(
                By.xpath("//button[@name='Submit']"))));
        disableProject.click();

        WebElement errorMessageWarning = getDriver().findElement(By.id("enable-project"));
        Assert.assertEquals(errorMessageWarning.getText().substring(0, 34), "This project is currently disabled");

        getDriver().findElement(By.xpath("//*[@id='jenkins-head-icon']")).click();

        WebElement jobIconStatusDisabled = getDriver().findElement(By.xpath("//*[@tooltip='Disabled']"));

        Assert.assertTrue(jobIconStatusDisabled.isDisplayed());
    }

    @Test(dependsOnMethods = "testDisableFreestyleProject")
    public void testEnableFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@href ='job/New%20Freestyle%20Project/']/span")).click();

        WebElement enableProject = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(
                By.xpath("//button[@name='Submit']"))));
        enableProject.click();

        WebElement projectDisabled = getDriver().findElement(
                By.xpath("//*[@id='disable-project']/button"));
        Assert.assertTrue(projectDisabled.isDisplayed());

        getDriver().findElement(By.xpath("//*[@id='jenkins-head-icon']")).click();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']"))
                .getAttribute("tooltip"), "Not built");
    }
}

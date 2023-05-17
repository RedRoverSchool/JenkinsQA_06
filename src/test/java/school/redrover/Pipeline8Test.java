package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Pipeline8Test extends BaseTest {

    @Test
    public void testChapterChangesOfPipelineSeeTheStatusOfLastBuild() {
        String changesBuild = "No changes in any of the builds";
        TestUtils.createPipeline(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'changes')]")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText().contains(changesBuild),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");

    }

    @Test
    public void testCreateBuildNowVisibilityTheTimeStatusBuild() {
        TestUtils.createPipeline(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'buildTimeTrend')]")).click();

        WebElement successIcon = getDriver().
                findElement(By.xpath("//a[@tooltip=normalize-space('Success > Console Output')]"));
        WebElement timeAndDateLine = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'indent-multiline')]")));

        Assert.assertTrue(successIcon.isDisplayed(), "successIcon not displayed");
        Assert.assertTrue(timeAndDateLine.isDisplayed(), "timeAndDateLine not displayed");
    }


}

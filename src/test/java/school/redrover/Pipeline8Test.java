package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        WebElement successIcon = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@tooltip=normalize-space('Success > Console Output')]")));
        WebElement timeAndDateLine = getDriver().findElement(By.xpath("//div[contains(@class, 'indent-multiline')]"));

        Assert.assertTrue(successIcon.isDisplayed(), "successIcon not displayed");
        Assert.assertTrue(timeAndDateLine.isDisplayed(), "timeAndDateLine not displayed");
    }

    @Test
    public void testMakeSeveralBuilds() {
        TestUtils.createPipeline(this, "Engineer", true);
        List<String>buildNumberExpected = Arrays.asList("#1", "#2", "#3");
        List<String>buildNumber = new ArrayList<>();

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        WebElement newBuild = getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]"));
        newBuild.click();
        newBuild.click();
        newBuild.click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'buildTimeTrend')]")).click();

        buildNumber.add(getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[href='1/']"))).getText());
        buildNumber.add(getDriver().findElement(By.cssSelector("[href='2/']")).getText());
        buildNumber.add(getDriver().findElement(By.cssSelector("[href='3/']")).getText());

        Assert.assertEquals(buildNumber, buildNumberExpected);
    }


}

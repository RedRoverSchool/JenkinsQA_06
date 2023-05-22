package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Pipeline7Test extends BaseTest {

    @Test
    public void testCreatePipelineProject() {
        final String namePipeline = "First Pipeline";

        TestUtils.createPipeline(this, namePipeline, true);
        String actualRes = getDriver().findElement(By.cssSelector(".jenkins-table>:nth-child(2)>tr>:nth-child(3)>a")).getText();

        Assert.assertEquals(actualRes, namePipeline);
    }
    @Test(dependsOnMethods = {"testCreatePipelineProject"})
    public void testBuildPipeline() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'First Pipeline')]"))).click().perform();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getDriver().findElement(By.cssSelector("#buildHistory>div>div>span>div>:nth-child(2)")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href$='console']"))).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("(//*[name()='svg'][@title='Success'])[1]")).isDisplayed(), "Build failed");
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent")).isDisplayed(), "Not found build");
    }
}

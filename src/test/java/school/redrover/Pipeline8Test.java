package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Pipeline8Test extends BaseTest {

    @Test
    public void testChapterChangesOfPipelineSeeTheStatusOfLastBuild() {
        String changesBuild = "No changes in any of the builds, or multiple SCMs in use.";
        TestUtils.createPipeline(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'changes')]")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText().contains(changesBuild),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");

    }


}

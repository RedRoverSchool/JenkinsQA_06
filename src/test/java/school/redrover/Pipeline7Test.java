package school.redrover;

import org.openqa.selenium.By;
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
}

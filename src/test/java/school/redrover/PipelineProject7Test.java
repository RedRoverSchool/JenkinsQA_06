package school.redrover;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject7Test extends BaseTest {
        private String name1 = "Project1";
    @Test
    public void testCreatePipelineProject() {
        TestUtils.createPipeline(this, name1, true);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//tr[@id='job_" + name1 + "']//a//span['" + name1 + "']"))
                .getText(), name1);
    }
}


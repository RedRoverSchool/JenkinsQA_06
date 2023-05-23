package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class Pipeline26Test extends BaseTest{
    private static final String NAME_PIPELINE_PROJECT = "pipeline";
    @Test
    public void testCreate() {

        WebElement pipelineElement = new MainPage(getDriver())
            .clickNewItem()
            .enterItemName(NAME_PIPELINE_PROJECT)
            .selectPipelineAndOk()
            .clickSaveButton()
            .clickDashboard()
            .getProjectName();

        Assert.assertEquals(pipelineElement.getText(), NAME_PIPELINE_PROJECT);
    }
}

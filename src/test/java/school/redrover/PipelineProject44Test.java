package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class PipelineProject44Test extends BaseTest {

    private static final String FIRST_PROJECT = "FirstProject";

    @Test
    public void testCreatePipelineProject() {

        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FIRST_PROJECT).selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), FIRST_PROJECT);
    }
}

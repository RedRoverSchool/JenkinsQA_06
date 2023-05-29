package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class PipelineProject10Test extends BaseTest {

    @Test
    public void testCreatePipelineProject() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("newProject")
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard();

        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(),"newProject");
    }

    @Test(dependsOnMethods = {"testCreatePipelineProject"})
    public void testRenamePipeline() {
        new MainPage(getDriver())
                .clickPipelineProject("newProject")
                .clickRename()
                .clearNameField()
                .enterNewName("newProject1")
                .clickRenameButton()
                .clickDashboard();

        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(),"newProject1");
    }
}

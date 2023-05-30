package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.BuildPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class BuildDescriptionTest extends BaseTest {

    public MainPage createNewPipelineProjectAndReturnToMainPage() {

        return new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("NewProject")
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo();
    }

    @Test
    public void testAddBuildDescription() throws InterruptedException {
        String projectName = "NewProject";
        String descriptionText = "NewDescription";

        MainPage mainPage = createNewPipelineProjectAndReturnToMainPage();
        BuildPage projectBuildPage = mainPage.clickSchedulerBuildForPipeline(projectName)
                .clickBuildsHistoryButton()
                .clickPipelineProjectBuildNumber(projectName)
                .clickEditBuildInformationButton(projectName)
                .editBuildDescription(descriptionText)
                .clickSaveButton();

        Assert.assertEquals(projectBuildPage.getProjectDescription(), descriptionText);
    }
}

package school.redrover.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.BuildPage;
import school.redrover.model.MainPage;
import school.redrover.model.ViewFolderPage;
import school.redrover.runner.BaseTest;

public class BuildTest extends BaseTest {

    @Test
    public void verifyStatusBroken() {

        final String namePipeline = "New Builds";
        final String textToDescriptionField = "What's up";
        final String textToPipelineScript = "Test";
        final String expectedStatusMessageText = "broken since this build";

        BuildPage buildsPage = new MainPage(getDriver())
                .clickNewItemButton()
                .inputAnItemName(namePipeline)
                .clickPipelineProject()
                .clickSaveButton()
                .sendAreDescriptionInputString(textToDescriptionField)
                .scrollToBuildtriggersByJavaScript()
                .clickBuildTriggerCheckBox()
                .scrollToPipelineSection()
                .sendAreContentInputString(textToPipelineScript)
                .clickSaveButton()
                .clickDashBoardButton()
                .clickPlayBuildForATestButton()
                .clickBuildsHistoryButton()
                .scrollToIconElement();

        String actualStatusMessageText = new BuildPage(getDriver())
                .getStatusMessageText();

        Assert.assertEquals(actualStatusMessageText,expectedStatusMessageText);
    }
}

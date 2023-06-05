package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.MultibranchPipelineConfigPage;
import school.redrover.model.MultibranchPipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MultibranchPipelineTest extends BaseTest {

    private static final String multibranchPipelineName = "MultibranchPipeline";
    private static final String multibranchPipelineRenamed = "MultibranchPipelineRenamed";

    @Test
    public void testCreateMultibranchPipelineWithDisplayName() {
        final String multibranchPipelineDisplayName = "MultibranchDisplayName";

        String actualDisplayedName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multibranchPipelineName)
                .selectMultibranchPipelineAndOk()
                .enterDisplayName(multibranchPipelineDisplayName)
                .clickSaveButton()
                .getDisplayedName();

        Assert.assertEquals(actualDisplayedName, multibranchPipelineDisplayName);
    }

    @Test
    public void testCreateMultibranchPipelineWithDescription() {
        String MultibranchPipeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multibranchPipelineName)
                .selectMultibranchPipelineAndOk()
                .addDescription("DESCRIPTION")
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickMultibranchPipelineName(multibranchPipelineName)
                .getDescription();

        Assert.assertEquals(MultibranchPipeline, "DESCRIPTION");
    }

    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        MultibranchPipelinePage pageWithOutDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multibranchPipelineName)
                .selectMultibranchPipelineAndOk()
                .clickSaveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())).viewDescription().getText().isEmpty());
    }

    @Test(dependsOnMethods = "testCreateMultibranchPipelineWithoutDescription")
    public void testRenameMultibranchPipeline() {
        String actualDisplayedName = new MainPage(getDriver())
                .clickMultibranchPipelineName(multibranchPipelineName)
                .renameMultibranchPipelinePage()
                .enterNewName(multibranchPipelineRenamed)
                .submitNewName()
                .getDisplayedName();

        Assert.assertEquals(actualDisplayedName, multibranchPipelineRenamed);
    }

    @Test(dependsOnMethods = "testRenameMultibranchPipeline")
    public void testDisableMultibranchPipeline() {
        String actualDisableMessage = new MainPage(getDriver())
                .clickMultibranchPipelineName(multibranchPipelineRenamed)
                .clickConfigureSideMenu()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();
        Assert.assertTrue(actualDisableMessage.contains("This Multibranch Pipeline is currently disabled"));
    }

    @Test(dependsOnMethods = "testDisableMultibranchPipeline")
    public void testDeleteMultibranchPipeline() {
        String WelcomeJenkinsPage = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(multibranchPipelineRenamed)
                .clickYes()
                .getWelcomeWebElement()
                .getText();

        Assert.assertEquals(WelcomeJenkinsPage, "Welcome to Jenkins!");
    }

    @Test
    public void testMoveMultibranchPipelineToFolder(){

        TestUtils.createFolder(this, "Folder", true);
        TestUtils.createMultibranchPipeline(this, "MultibranchPipeline", true);

        WebElement nameMultibranchPipeline = new MainPage(getDriver())
                .dropDownMenuClickMove("MultibranchPipeline",new FolderPage(getDriver()))
                .selectDestinationFolder("Folder")
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName("Folder")
                .getMultibranchPipelineName();

        Assert.assertTrue(nameMultibranchPipeline.isDisplayed());
    }

    @Test
    public void createMultiPipeline(){
        for (int i = 0 ;i < 4; i++){
            String jobName = "M0"+i;
            TestUtils.createMultibranchPipeline(this,jobName,true);
        }
        MainPage mainPage = new MainPage(getDriver());
        List<String> jobs = mainPage.getJobList();
        Assert.assertTrue(jobs.size()==4);
    }
    @Test(dependsOnMethods = "createMultiPipeline")
    public void testFindCreatedMultibranchPipelineOnDashboard(){
        MainPage mainPage = new MainPage(getDriver());
        boolean status = mainPage.verifyJobIsPresent("M00");
        Assert.assertTrue(status);
    }
}

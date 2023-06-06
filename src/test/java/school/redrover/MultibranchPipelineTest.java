package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultibranchPipelineConfigPage;
import school.redrover.model.MultibranchPipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MultibranchPipelineTest extends BaseTest {

    private static final String NAME = "MultibranchPipeline";
    private static final String RENAMED = "MultibranchPipelineRenamed";

    @Test
    public void testCreateMultibranchPipelineWithDisplayName() {
        final String multibranchPipelineDisplayName = "MultibranchDisplayName";

        MultibranchPipelinePage multibranchPipelinePage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectMultibranchPipelineAndOk()
                .enterDisplayName(multibranchPipelineDisplayName)
                .clickSaveButton();

        Assert.assertEquals(multibranchPipelinePage.getDisplayedName(), multibranchPipelineDisplayName);
        Assert.assertTrue(multibranchPipelinePage.metadataFolderIconIsDisplayed(), "error was not shown Metadata Folder icon");
    }

    @Test
    public void testCreateMultibranchPipelineWithDescription() {
        String MultibranchPipeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectMultibranchPipelineAndOk()
                .addDescription("DESCRIPTION")
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickMultibranchPipelineName(NAME)
                .getDescription();

        Assert.assertEquals(MultibranchPipeline, "DESCRIPTION");
    }

    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        MultibranchPipelinePage pageWithOutDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectMultibranchPipelineAndOk()
                .clickSaveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())).viewDescription().getText().isEmpty());
    }

    @Test(dependsOnMethods = "testCreateMultibranchPipelineWithoutDescription")
    public void testRenameMultibranchPipeline() {
        String actualDisplayedName = new MainPage(getDriver())
                .clickMultibranchPipelineName(NAME)
                .renameMultibranchPipelinePage()
                .enterNewName(RENAMED)
                .submitNewName()
                .getDisplayedName();

        Assert.assertEquals(actualDisplayedName, RENAMED);
    }

    @Test(dependsOnMethods = "testRenameMultibranchPipeline")
    public void testDisableMultibranchPipeline() {
        String actualDisableMessage = new MainPage(getDriver())
                .clickMultibranchPipelineName(RENAMED)
                .clickConfigureSideMenu()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();
        Assert.assertTrue(actualDisableMessage.contains("This Multibranch Pipeline is currently disabled"));
    }

    @Test(dependsOnMethods = "testDisableMultibranchPipeline")
    public void testDeleteMultibranchPipeline() {
        String WelcomeJenkinsPage = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(RENAMED)
                .clickYes()
                .getWelcomeWebElement()
                .getText();

        Assert.assertEquals(WelcomeJenkinsPage, "Welcome to Jenkins!");
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithDisplayName")
    public void testChooseDefaultIcon() {
        MultibranchPipelinePage multibranchPipelinePage = new MainPage(getDriver())
                .clickMultibranchPipelineName(NAME)
                .clickConfigureSideMenu()
                .clickAppearance()
                .selectDefaultIcon()
                .clickSaveButton();
        Assert.assertTrue(multibranchPipelinePage.defaultIconIsDisplayed(), "error was not shown default icon");
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

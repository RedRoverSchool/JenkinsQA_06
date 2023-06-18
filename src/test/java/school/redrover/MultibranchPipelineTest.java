package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.jobsconfig.MultibranchPipelineConfigPage;
import school.redrover.model.jobs.MultibranchPipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultibranchPipelineTest extends BaseTest {

    private static final String NAME = "MultibranchPipeline";
    private static final String RENAMED = "MultibranchPipelineRenamed";

    @Test
    public void testCreateMultibranchPipelineWithDisplayName() {
        final String multibranchPipelineDisplayName = "MultibranchDisplayName";

        MultibranchPipelinePage multibranchPipelinePage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultibranchPipeline)
                .clickOkButton(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())))
                .enterDisplayName(multibranchPipelineDisplayName)
                .clickSaveButton();

        Assert.assertEquals(multibranchPipelinePage.getJobName(), multibranchPipelineDisplayName);
        Assert.assertTrue(multibranchPipelinePage.metadataFolderIconIsDisplayed(), "error was not shown Metadata Folder icon");
    }

    @Test
    public void testCreateMultibranchPipelineWithDescription() {
        String MultibranchPipeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultibranchPipeline)
                .clickOkButton(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())))
                .addDescription("DESCRIPTION")
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickJobName(NAME, new MultibranchPipelinePage(getDriver()))
                .getDescription();

        Assert.assertEquals(MultibranchPipeline, "DESCRIPTION");
    }

    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        boolean isDescriptionEmpty = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultibranchPipeline)
                .clickOkButton(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())))
                .clickSaveButton()
                .isDescriptionEmpty();

        Assert.assertTrue(isDescriptionEmpty);
    }

    @Test(dependsOnMethods = "testCreateMultibranchPipelineWithoutDescription")
    public void testRenameMultibranchPipeline() {
        String actualDisplayedName = new MainPage(getDriver())
                .clickJobName(NAME, new MultibranchPipelinePage(getDriver()))
                .clickRename()
                .enterNewName(RENAMED)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualDisplayedName, RENAMED);
    }

    @Test(dependsOnMethods = "testRenameMultibranchPipeline")
    public void testDisableMultibranchPipeline() {
        String actualDisableMessage = new MainPage(getDriver())
                .clickJobName(RENAMED, new MultibranchPipelinePage(getDriver()))
                .clickConfigure()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();
        Assert.assertTrue(actualDisableMessage.contains("This Multibranch Pipeline is currently disabled"));
    }

    @Test(dependsOnMethods = "testDisableMultibranchPipeline")
    public void testDeleteMultibranchPipeline() {
        String WelcomeJenkinsPage = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(RENAMED)
                .clickYesButton()
                .getWelcomeText();

        Assert.assertEquals(WelcomeJenkinsPage, "Welcome to Jenkins!");
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithDisplayName")
    public void testChooseDefaultIcon() {
        boolean defaultIconDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new MultibranchPipelinePage(getDriver()))
                .clickConfigure()
                .clickAppearance()
                .selectDefaultIcon()
                .clickSaveButton()
                .defaultIconIsDisplayed();

        Assert.assertTrue(defaultIconDisplayed, "error was not shown default icon");
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithDisplayName")
    public void testAddHealthMetrics() {
        boolean healthMetricIsVisible = new MainPage(getDriver())
                .clickJobName(NAME, new MultibranchPipelinePage(getDriver()))
                .clickConfigure()
                .addHealthMetrics()
                .clickSaveButton()
                .clickConfigure()
                .clickHealthMetrics()
                .healthMetricIsVisible();

        Assert.assertTrue(healthMetricIsVisible, "error was not shown Health Metrics");
    }

    @Test(dependsOnMethods = "testCreateMultibranchPipelineWithDescription")
    public void testFindCreatedMultibranchPipelineOnMainPage(){
        boolean jobIsPresent = new MainPage(getDriver())
                .verifyJobIsPresent(NAME);

        Assert.assertTrue(jobIsPresent);
    }
}

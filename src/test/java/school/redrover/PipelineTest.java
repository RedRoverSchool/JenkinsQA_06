package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.PipelinePage;
import school.redrover.model.jobsconfig.PipelineConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class PipelineTest extends BaseTest {

    private static final String NAME = "PIPELINE_NAME";
    private static final String NEW_NAME = "Pipeline Project";
    private static final String DESCRIPTION = "This is a test description";

    @Test
    public void testCreatePipeline() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobName(NAME);

        Assert.assertEquals(projectName, NAME);
    }

    @Test
    public void testCreatePipelineWithDescription() {
        String jobDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .addDescription(DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(jobDescription, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddingDescriptionToPipeline")
    public void testEditPipelineDescription() {
        final String newDescription = "Edited description text";

        String jobDescription = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickEditDescription()
                .clearDescriptionField()
                .enterDescription(newDescription)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(jobDescription, newDescription);
    }

    @Test
    public void testPipelineBuildNow() {
        String stageName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .getStage();

        Assert.assertEquals(stageName, "Hello");
    }

    @Test
    public void testPipelineConsoleOutputSuccess() {
        String text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .clickIconBuildOpenConsoleOutput(1)
                .getConsoleOutputText();

        Assert.assertTrue(text.contains("Finished: SUCCESS"), "Job does not finished success");
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testAddingDescriptionToPipeline() {
        String resultDescriptionText = new PipelinePage(getDriver())
                .clickEditDescription()
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(resultDescriptionText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testDiscardOldBuildsIsCheckedWithValidParams")
    public void testRenamePipeline() {
        String projectName = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickRename()
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getJobName(NEW_NAME);

        Assert.assertEquals(projectName, NEW_NAME);
    }

    @Test(dependsOnMethods = "testRenamePipeline")
    public void testDeleteLeftMenu() {
        String welcomeText =  new MainPage(getDriver())
                .clickJobName(NEW_NAME, new PipelinePage(getDriver()))
                .clickDeleteAndAccept()
                .getWelcomeText();

        Assert.assertEquals(welcomeText, "Welcome to Jenkins!");
    }

    @Test
    public void testCreatingBasicPipelineProjectThroughJenkinsUI() {
        String resultOptionDefinitionFieldText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .scrollToPipelineSection()
                .getOptionTextInDefinitionField();

        Assert.assertEquals(resultOptionDefinitionFieldText, "Pipeline script");
    }

    @Test(dependsOnMethods = "testCancelDeletion")
    public void testDeleteDropDownMenu() {
        String welcomeText = new MainPage(getDriver())
                .dropDownMenuClickDelete(NEW_NAME)
                .acceptAlert()
                .getWelcomeText();

        Assert.assertEquals(welcomeText, "Welcome to Jenkins!");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreatingBasicPipelineProjectThroughJenkinsUI")
    public void testPipelineBuildingAfterChangesInCode() {
        BuildPage buildPage = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickConfigure()
                .clickPipelineLeftMenu()
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .clickIconBuildOpenConsoleOutput(1)
                .clickNumberBuild(1);

        Assert.assertTrue(buildPage.isDisplayedBuildTitle(), "Build #1 failed");
        Assert.assertTrue(buildPage.isDisplayedGreenIconV(), "Build #1 failed");
    }

    @Test
    public void testSetDescriptionPipeline() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        String jobDescription = new PipelinePage(getDriver())
                .clickConfigure()
                .addDescription("Pipeline text")
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(jobDescription, "Pipeline text");
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        String jobName = new PipelinePage(getDriver())
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds("2")
                .enterMaxOfBuildsToKeep("30")
                .clickSaveButton()
                .getJobName();

        Assert.assertEquals(jobName, "Pipeline " + NAME);
    }

    @Test
    public void testBuildPipeline() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, true);

        ConsoleOutputPage consoleOutputPage = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickBuildNowFromSideMenu()
                .clickTrend()
                .clickBuildIcon();

        Assert.assertTrue(consoleOutputPage.isDisplayedGreenIconV(), "Build failed");
        Assert.assertTrue(consoleOutputPage.isDisplayedBuildTitle(), "Not found build");
    }

    @Test
    public void testChangesStatusOfLastBuild() {

        TestUtils.createJob(this, "Engineer", TestUtils.JobType.Pipeline, true);

        String text = new MainPage(getDriver())
                .clickJobName("Engineer", new PipelinePage(getDriver()))
                .clickBuildNowFromSideMenu()
                .clickChangeOnLeftSideMenu()
                .getTextOfPage();

        Assert.assertTrue(text.contains("No changes in any of the builds"),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");
    }

    @Test(dependsOnMethods = "testBuildPipeline")
    public void testMakeSeveralBuilds() {
        List<String> buildNumberExpected = Arrays.asList("#1", "#2", "#3", "#4");

        List buildNumber = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickBuildNowFromSideMenu()
                .clickBuildNowFromSideMenu()
                .clickBuildNowFromSideMenu()
                .clickTrend()
                .getBuildNumbers(4);

        Assert.assertEquals(buildNumber, buildNumberExpected);
    }

    @Test
    public void testCreateNewPipelineWithScript() {
        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .selectScriptedPipelineAndSubmit()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NAME);

        Assert.assertTrue(projectIsPresent);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testDisablePipeline() {
        String jobStatus = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickDisable()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(NAME);

        Assert.assertEquals(jobStatus, "Disabled");
    }

    @Test(dependsOnMethods = "testDisablePipeline")
    public void testEnablePipeline() {
        String jobStatus = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickEnable()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(NAME);

        Assert.assertEquals(jobStatus, "Not built");
    }

    @Test(dependsOnMethods = "testEnablePipeline")
    public void testCreateDuplicatePipelineProject() {

        NewJobPage newJobPage =
                TestUtils.createJobWithExistingNameWithoutClickOk(this, NAME, TestUtils.JobType.Pipeline);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» A job already exists with the name " + "‘" + NAME + "’");
        Assert.assertTrue(newJobPage.isOkButtonEnabled(), "error OK button is disabled");
    }

    @Test
    public void testSortingPipelineProjectAplhabetically() {

        List<String> namesOfJobs = Arrays.asList("UProject", "SProject", "AProject");

        TestUtils.createJob(this, namesOfJobs.get(1), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, namesOfJobs.get(2), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, namesOfJobs.get(0), TestUtils.JobType.Pipeline, true);

        List<String> listNamesOfJobs = new MainPage(getDriver())
                .clickSortByName()
                .getJobList();

        Assert.assertEquals(listNamesOfJobs, namesOfJobs);
    }

    @Test(dependsOnMethods = "testSetDescriptionPipeline")
    public void testRenamePipelineDropDownMenu() {
        String renamedPipeline = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME.replaceAll(" ", "%20"), new PipelinePage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getJobName(NEW_NAME);

        Assert.assertEquals(renamedPipeline, NEW_NAME);
    }

    @Test
    public void testPipelineNameAllowedChar() {
        final String allowedChar = "_-+=”{},";

        String projectNameDashboard = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(allowedChar)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobName(allowedChar);

        Assert.assertEquals(projectNameDashboard, allowedChar);
    }

    @DataProvider(name = "wrong-characters")
    public Object[][] providerWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-characters")
    public void testPipelineNameUnsafeChar(String wrongCharacters) {
        NewJobPage newJobPage =
                TestUtils.createFolderUsingInvalidData(this, wrongCharacters, TestUtils.JobType.Pipeline);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacters + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled(), "error OK button is enabled");
    }

    @Test
    public void testDotBeforeNameProject() {
        String  getMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(".")
                .getItemInvalidMessage();

        Assert.assertEquals(getMessage, "» “.” is not an allowed name");
    }

    @Test
    public void testCreatePipelineWithSpaceInsteadOfName() {
        CreateItemErrorPage createItemErrorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.Pipeline);

        Assert.assertEquals(createItemErrorPage.getHeaderText(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "No name is specified");
    }

    @Test
    public void testDiscardOldBuildsIsChecked() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        boolean discardOldBuildsCheckbox = new PipelinePage(getDriver())
                .clickConfigure()
                .selectDiscardOldBuildsandSave()
                .clickConfigure()
                .checkboxDiscardOldBuildsIsSelected();

        Assert.assertTrue(discardOldBuildsCheckbox);
    }

    @Test
    public void testDiscardOldBuildsParams() {
        final String days = "7";
        final String builds = "5";

        PipelineConfigPage pipelineConfigPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("test-pipeline")
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds(days)
                .enterMaxOfBuildsToKeep(builds)
                .clickSaveButton()
                .clickConfigure();

        Assert.assertEquals(pipelineConfigPage.getDaysToKeepBuilds(), days);
        Assert.assertEquals(pipelineConfigPage.getMaxNumbersOfBuildsToKeep(), builds);
    }

    @Test
    public void testDiscardOldBuilds0Days() {
        String actualErrorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("test-pipeline")
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds("0")
                .enterMaxOfBuildsToKeep("")
                .getErrorMessageStrategyDays();

        Assert.assertEquals(actualErrorMessage, "Not a positive integer");
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Builds() {
        TestUtils.createJob(this, "test-pipeline", TestUtils.JobType.Pipeline, false);

        boolean notPositiveInteger = new PipelinePage(getDriver())
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds("0")
                .clickOutsideOfInputField()
                .isErrorMessageDisplayed();

        Assert.assertTrue(notPositiveInteger);
    }

    @Test
    public void testDisableDuringCreation() {
        final String PIPELINE_NAME = "My_pipeline";

        boolean projectDisable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .toggleDisableProject()
                .clickSaveButton()
                .clickConfigure()
                .isProjectDisable();

        Assert.assertFalse(projectDisable, "Pipeline is enabled");
    }

    @Test
    public void testCreatePipelineGoingFromManageJenkinsPage() {
        List<String> jobList = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobList();

        Assert.assertTrue(jobList.contains(NAME));
    }

    @Test
    public void testSetPipelineDisplayName() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        PipelinePage pipelinePage = new PipelinePage(getDriver())
                .clickConfigure()
                .scrollAndClickAdvancedButton()
                .setDisplayName(NEW_NAME)
                .clickSaveButton();

        Assert.assertEquals(pipelinePage.getJobName(), "Pipeline " + NEW_NAME);
        Assert.assertEquals(pipelinePage.getProjectNameSubtitleWithDisplayName(), NAME);
        Assert.assertEquals(pipelinePage.getHeader().clickLogo().getJobName(NAME), NEW_NAME);
    }

    @Test
    public void testAddDescriptionAfterRewrite() {
        String description = "description";
        String newDescription = "new description";

        String textPreview = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .addDescription(description)
                .clickPreview()
                .getPreviewText();
        Assert.assertEquals(textPreview, description);

        PipelinePage pipelinePage = new PipelineConfigPage(new PipelinePage(getDriver()))
                .clearDescriptionArea()
                .addDescription(newDescription)
                .clickSaveButton();
        String actualDescription = pipelinePage.getDescription();
        Assert.assertTrue(actualDescription.contains(newDescription), "description not displayed");
    }

    @Test
    public void testAddBooleanParameterWithDescription() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        final String name = "Pipeline Boolean Parameter";
        final String description = "Some boolean parameters here";
        final String parameterName = "Boolean Parameter";

        BuildWithParametersPage buildParametersPagePage = new PipelinePage(getDriver())
                .clickConfigure()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .setDefaultBooleanParameter()
                .setBooleanParameterDescription(description)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton(new PipelinePage(getDriver()));

        Assert.assertEquals(buildParametersPagePage.getBooleanParameterName(), name);
        Assert.assertEquals(buildParametersPagePage.getBooleanParameterCheckbox(), "true");
        Assert.assertEquals(buildParametersPagePage.getParameterDescription(), description);
    }

    @Test
    public void testAddBooleanParameter() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, false);

        final String name = "Pipeline Boolean Parameter";
        final String parameterName = "Boolean Parameter";

        BuildWithParametersPage buildParametersPage = new PipelinePage(getDriver())
                .clickConfigure()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton(new PipelinePage(getDriver()));

        Assert.assertEquals(buildParametersPage.getBooleanParameterName(), name);
        Assert.assertNull(buildParametersPage.getBooleanParameterCheckbox());
    }

    @Test(dependsOnMethods = "testRenamePipelineDropDownMenu")
    public void testCancelDeletion() {
        boolean projectIsPresent = new MainPage(getDriver())
                .dropDownMenuClickDelete(NEW_NAME)
                .dismissAlert()
                .jobIsDisplayed(NEW_NAME);

        Assert.assertTrue(projectIsPresent);
    }

    @Test
    public void testAddingAProjectOnGithubToThePipelineProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        TestUtils.createJob(this, NAME, TestUtils.JobType.Pipeline, true);

        String actualNameRepo = new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickConfigure()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .selectFromJobDropdownMenuTheGitHub(NAME);

        Assert.assertEquals(actualNameRepo, expectedNameRepo);
    }

    @Test(dependsOnMethods = "testCreatePipelineWithDescription")
    public void testDiscardOldBuildsIsCheckedWithValidParams() {
        final String days = "7";
        final String builds = "5";

        new MainPage(getDriver())
                .clickJobName(NAME, new PipelinePage(getDriver()))
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds(days)
                .enterMaxOfBuildsToKeep(builds)
                .clickSaveButton()
                .clickConfigure();

        PipelineConfigPage pipelineConfigPage = new PipelineConfigPage(new PipelinePage(getDriver()));

        Assert.assertTrue(pipelineConfigPage.checkboxDiscardOldBuildsIsSelected());
        Assert.assertEquals(pipelineConfigPage.getDaysToKeepBuilds(), days);
        Assert.assertEquals(pipelineConfigPage.getMaxNumbersOfBuildsToKeep(), builds);
    }
}

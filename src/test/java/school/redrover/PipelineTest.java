package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = "PIPELINE_NAME";
    private static final String RENAME = "Pipeline Project";
    private static final String TEXT_DESCRIPTION = "This is a test description";

    @Test
    public void testCreatePipeline() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName, PIPELINE_NAME);
    }

    @Test
    public void testCreatePipelineWithDescription() {
        final String textDescription = "description text";

        String jobDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .addDescription(textDescription)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(jobDescription, textDescription);
    }

    @Test
    public void testEditPipelineDescription() {
        final String description = "description text";
        final String newDescription = "Edited description text";

        String jobDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .addDescription(description)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
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
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .getStage();

        Assert.assertEquals(stageName, "Hello");
    }

    @Test
    public void testPipelineConsoleOutputSuccess() {
        String text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .clickBuildIcon()
                .getConsoleOutputField();

        Assert.assertTrue(text.contains("Finished: SUCCESS"), "Job does not finished success");
    }

    @Test
    public void testAddingDescriptionToPipeline() {
        final String pipelineName = "test_pipeline";
        final String descriptionText = "description text";
        String resultDescriptionText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(pipelineName)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .clickEditDescription()
                .enterDescription(descriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(resultDescriptionText, descriptionText);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testRenamePipeline() {
        final String newPipelineName = PIPELINE_NAME + "new";

        String projectName = new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickRename()
                .enterNewName(newPipelineName)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName, newPipelineName);
    }

    @Test()
    public void testDeleteLeftMenu() {
        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickDeletePipeline()
                .acceptAlert()
                .verifyJobIsPresent(PIPELINE_NAME);

        Assert.assertFalse(projectIsPresent);
    }

    @Test
    public void testCreatingBasicPipelineProjectThroughJenkinsUI() {
        String resultOptionDefinitionFieldText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .scrollToPipelineSection()
                .getOptionTextInDefinitionField();

        Assert.assertEquals(resultOptionDefinitionFieldText, "Pipeline script");
    }

    @Test
    public void testDeleteDropDownMenu() {
        final String name = PIPELINE_NAME + "1";

        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(name)
                .acceptAlert()
                .verifyJobIsPresent(name);

        Assert.assertFalse(projectIsPresent);
    }

    @Test(dependsOnMethods = "testCreatingBasicPipelineProjectThroughJenkinsUI")
    public void testPipelineBuildingAfterChangesInCode() {
        BuildPage buildPage = new MainPage(getDriver())
                .getHeader()
                .clickLogo()
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickConfigure()
                .clickPipelineLeftMenu()
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .clickBuildIcon()
                .click1BuildHistory();

        Assert.assertTrue(buildPage.isDisplayedBuildTitle(), "Build #1 failed");
        Assert.assertTrue(buildPage.isDisplayedGreenIconV(), "Build #1 failed");
    }

    @Test
    public void testSetDescriptionPipeline() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

        String jobDescription = new PipelinePage(getDriver())
                .clickConfigure()
                .addDescription("Pipeline text")
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(jobDescription, "Pipeline text");
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

        String jobName = new PipelinePage(getDriver())
                .clickConfigure()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds("2")
                .enterMaxOfBuildsToKeep("30")
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(jobName, "Pipeline " + PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testBuildPipeline() {
        final String namePipeline = "FirstPipeline";

        TestUtils.createJob(this, namePipeline, TestUtils.JobType.Pipeline, true);

        ConsoleOutputPage consoleOutputPage = new MainPage(getDriver())
                .clickJobName(namePipeline, new PipelinePage(getDriver()))
                .clickBuildNow()
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
                .clickBuildNow()
                .clickChangeOnLeftSideMenu()
                .getTextOfPage();

        Assert.assertTrue(text.contains("No changes in any of the builds"),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");
    }

    @Test
    public void testMakeSeveralBuilds() {
        final String jobName = "Engineer";
        List<String> buildNumberExpected = Arrays.asList("#1", "#2", "#3");

        List buildNumber = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(jobName)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(jobName, new PipelinePage(getDriver()))
                .clickBuildNow()
                .clickBuildNow()
                .clickBuildNow()
                .clickTrend()
                .getBuildNumbers(3);

        Assert.assertEquals(buildNumber, buildNumberExpected);
    }

    @Test
    public void testCreateNewPipelineWithScript() {
        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .selectScriptedPipelineAndSubmit()
                .getHeader()
                .clickLogo()
                .verifyJobIsPresent(PIPELINE_NAME);

        Assert.assertTrue(projectIsPresent);
    }

    @Test
    public void testDisablePipeline() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, true);

        String jobStatus = new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickDisable()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(PIPELINE_NAME);

        Assert.assertEquals(jobStatus, "Disabled");
    }

    @Test
    public void testEnablePipeline() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, true);

        String jobStatus = new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickDisable()
                .clickEnable()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(PIPELINE_NAME);

        Assert.assertEquals(jobStatus, "Not built");
    }

    @Test
    public void testCreateDuplicatePipelineProject() {

        String jobExists = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .getItemInvalidMessage();

        Assert.assertEquals(jobExists, "» A job already exists with the name " + "‘" + PIPELINE_NAME + "’");
    }

    @Test
    public void testSortingPipelineProjectAplhabetically() {

        List<String> namesOfJobs = Arrays.asList("UProject", "SProject", "AProject");

        TestUtils.createJob(this, namesOfJobs.get(1), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, namesOfJobs.get(2), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, namesOfJobs.get(0), TestUtils.JobType.Pipeline, true);

        List<String> listNamesOfJobs = new MainPage(getDriver())
                .clickSortByName()
                .getListNamesOfJobs();

        Assert.assertEquals(listNamesOfJobs, namesOfJobs);
    }

    @Test
    public void testRenamePipelineDropDownMenu() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, true);

        String renamedPipeline = new MainPage(getDriver())
                .dropDownMenuClickRename(PIPELINE_NAME.replaceAll(" ", "%20"), new PipelinePage(getDriver()))
                .enterNewName(RENAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(renamedPipeline, RENAME);
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
                .getProjectNameMainPage(allowedChar);

        Assert.assertEquals(projectNameDashboard, allowedChar);
    }

    @DataProvider(name = "wrong-characters")
    public Object[][] providerWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-characters")
    public void testPipelineNameUnsafeChar(String wrongCharacters) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacters);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacters + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
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
        CreateItemErrorPage createItemErrorPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("  ")
                .selectJobAndOkAndGoError(TestUtils.JobType.Pipeline);

        Assert.assertEquals(createItemErrorPage.getHeaderText(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "No name is specified");
    }

    @Test
    public void testSetDescription() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, true);

        String addDescription = new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickEditDescription()
                .enterDescription(TEXT_DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(addDescription, TEXT_DESCRIPTION);
    }

    @Test
    public void testDiscardOldBuildsIsChecked() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

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
                .checkWarningMessage()
                .clickConfigure()
                .isProjectDisable();

        Assert.assertFalse(projectDisable, "Pipeline is enabled");
    }

    @Test
    public void testCreatePipelineWithTheSameName() {
        String actualErrorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobAndOkAndGoError(TestUtils.JobType.Pipeline)
                .getErrorMessage();

        Assert.assertEquals(actualErrorMessage, "A job already exists with the name ‘" + PIPELINE_NAME + "’");
    }

    @Test
    public void testCreatePipelineGoingFromManageJenkinsPage() {
        List<String> jobList = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobList();

        Assert.assertTrue(jobList.contains(PIPELINE_NAME));
    }

    @Test
    public void testSetPipelineDisplayName() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

        PipelinePage pipelinePage = new PipelinePage(getDriver())
                .clickConfigure()
                .scrollAndClickAdvancedButton()
                .setDisplayName(RENAME)
                .clickSaveButton();

        Assert.assertEquals(pipelinePage.getProjectName(), "Pipeline " + RENAME);
        Assert.assertEquals(pipelinePage.getProjectNameSubtitle(), PIPELINE_NAME);
        Assert.assertEquals(pipelinePage.getHeader().clickLogo().getProjectName(), RENAME);
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
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

        final String name = "Pipeline Boolean Parameter";
        final String description = "Some boolean parameters here";
        final String parameterName = "Boolean Parameter";

        BuildPage buildPage = new PipelinePage(getDriver())
                .clickConfigure()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .setDefaultBooleanParameter()
                .setBooleanParameterDescription(description)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton();

        Assert.assertEquals(buildPage.getBooleanParameterName(), name);
        Assert.assertEquals(buildPage.getBooleanParameterCheckbox(), "true");
        Assert.assertEquals(buildPage.getParameterDescription(), description);
    }

    @Test
    public void testAddBooleanParameter() {
        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, false);

        final String name = "Pipeline Boolean Parameter";
        final String parameterName = "Boolean Parameter";

        BuildPage buildPage = new PipelinePage(getDriver())
                .clickConfigure()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton();

        Assert.assertEquals(buildPage.getBooleanParameterName(), name);
        Assert.assertNull(buildPage.getBooleanParameterCheckbox());
    }

    @Test
    public void testCancelDeletion() {
        final String jobName = "P1";

        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(jobName)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(jobName)
                .dismissAlert()
                .verifyJobIsPresent(jobName);

        Assert.assertTrue(projectIsPresent);
    }

    @Test
    public void testAddingAProjectOnGithubToThePipelineProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        TestUtils.createJob(this, PIPELINE_NAME, TestUtils.JobType.Pipeline, true);

        String actualNameRepo = new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
                .clickConfigure()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .openJobDropDownMenu(PIPELINE_NAME)
                .selectFromJobDropdownMenuTheGitHub();

        Assert.assertEquals(actualNameRepo, expectedNameRepo);
    }

    @Test(dependsOnMethods = "testCreatePipelineWithDescription")
    public void testDiscardOldBuildsIsCheckedWithValidParams() {
        final String days = "7";
        final String builds = "5";

        new MainPage(getDriver())
                .clickJobName(PIPELINE_NAME, new PipelinePage(getDriver()))
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

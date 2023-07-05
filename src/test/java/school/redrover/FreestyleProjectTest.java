package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static school.redrover.runner.TestUtils.createJob;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = "FREESTYLE_NAME";
    private static final String NEW_FREESTYLE_NAME = "NEW_FREESTYLE_NAME";
    private static final String DESCRIPTION_TEXT = "DESCRIPTION_TEXT";
    private static final String NEW_DESCRIPTION_TEXT = "NEW_DESCRIPTION_TEXT";

    @Test
    public void testCreateFreestyleProject() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobName(FREESTYLE_NAME);

        Assert.assertEquals(projectName, FREESTYLE_NAME);
    }

    @Test
    public void testCreateFSProjectWithDefaultConfigurations() {
        final String PROJECT_NAME = UUID.randomUUID().toString();

        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .enterItemName(PROJECT_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.projectStatusTableIsDisplayed());
        Assert.assertEquals(mainPage.getProjectsList().size(), 1);
        Assert.assertEquals(mainPage.getJobName(PROJECT_NAME), PROJECT_NAME);
    }

    @Test
    public void testCreateWithExistingName() {
        createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        CreateItemErrorPage errorPage =
                TestUtils.createJobWithExistingName(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(),
                String.format("A job already exists with the name ‘%s’", FREESTYLE_NAME));
    }

    @Test
    public void testEmptyNameError() {
        final String expectedError = "» This field cannot be empty, please enter a valid name";

        String actualError = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .getItemNameRequiredErrorText();

        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void testOKButtonIsDisabledWhenEmptyName() {
        boolean okButton = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .isOkButtonDisabled();

        Assert.assertTrue(okButton);
    }

    @Test
    public void testNavigateToChangePage() {
        createJob(this, "Engineer", TestUtils.JobType.FreestyleProject, true);

        String text = new MainPage(getDriver())
                .clickJobName("Engineer", new FreestyleProjectPage(getDriver()))
                .clickChangeOnLeftSideMenu()
                .getTextOfPage();

        Assert.assertTrue(text.contains("No builds."),
                "In the Freestyle project Changes chapter, not displayed status of the latest build.");
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testAccessConfigurationPageFromDashboard() {
        final String breadcrumb = "Dashboard > " + FREESTYLE_NAME + " > Configuration";

        FreestyleProjectConfigPage freestyleConfigPage = new MainPage(getDriver())
                .clickConfigureDropDown(
                        FREESTYLE_NAME, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver()))
                );

        Assert.assertEquals(freestyleConfigPage.getBreadcrumb().getFullBreadcrumbText(), breadcrumb);
        Assert.assertTrue(freestyleConfigPage.isConfigurationDisplayed(), "error: 'Configure' was not displayed");
    }

    @Test(dependsOnMethods = "testAccessConfigurationPageFromDashboard")
    public void testDisableProject() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickDisable();

        Assert.assertEquals(projectName.getDisabledMessageText(), "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {
        MainPage projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickEnable()
                .getHeader()
                .clickLogo();

        Assert.assertEquals(projectName.getJobBuildStatusIcon(FREESTYLE_NAME), "Not built");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testAddDescription() {
        String actualDescription = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .addDescription("Freestyle project")
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescription, "Freestyle project");
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testRenameFreestyleProject() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickRename()
                .enterNewName(FREESTYLE_NAME + " New")
                .clickRenameButton();

        Assert.assertEquals(projectName.getJobName(), "Project " + FREESTYLE_NAME + " New");
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testRenameFreestyleProjectUsingDropDownMenu() {
        String actualFreestyleProjectName = new MainPage(getDriver())
                .dropDownMenuClickRename(FREESTYLE_NAME + " New", new FreestyleProjectPage(getDriver()))
                .enterNewName(NEW_FREESTYLE_NAME)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualFreestyleProjectName, "Project " + NEW_FREESTYLE_NAME);
    }

    @Test
    public void testCreateFreestyleProjectWithDescription() {

        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addDescription("Description")
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getJobName(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), "Description");
    }

    @Test
    public void testEditDescription() {
        String editDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .clickEditDescription()
                .removeOldDescriptionAndAddNew(NEW_DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescription();

        Assert.assertEquals(editDescription, NEW_DESCRIPTION_TEXT);
    }

    @Test
    public void testPreviewDescription() {
        String previewDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickPreviewButton()
                .getPreviewDescription();

        Assert.assertEquals(previewDescription, "DESCRIPTION_TEXT");
    }

    @Test
    public void testVisibleProjectNameAndDescriptionOnViewPage() {
        FreestyleProjectPage projectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getHeader()
                .clickLogo()
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()));

        String projectNameFromViewPage = projectPage.getJobName();
        String projectDescriptionFromViewPage = projectPage.getDescription();

        Assert.assertEquals(projectNameFromViewPage, "Project " + FREESTYLE_NAME);
        Assert.assertEquals(projectDescriptionFromViewPage, DESCRIPTION_TEXT);
    }

    @Test
    public void testBuildFreestyleProject() {
        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MyFreestyleProject")
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addExecuteShellBuildStep("echo Hello")
                .clickSaveButton()
                .clickBuildNow()
                .clickIconBuildOpenConsoleOutput(1)
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("echo Hello"), "Command wasn't run OR test was run on the Windows");
        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build wasn't finished successfully");
    }

    @Test
    public void testCreatedNewBuild() {
        boolean buildHeaderIsDisplayed = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName("Engineer", new FreestyleProjectPage(getDriver()))
                .clickBuildNow()
                .clickIconBuildOpenConsoleOutput(1)
                .isDisplayedBuildTitle();

        Assert.assertTrue(buildHeaderIsDisplayed, "build not created");
    }

    @Test(dependsOnMethods = "testAddBooleanParameterTheFreestyleProject")
    public void testPresenceOfBuildLinksAfterBuild() {
        MainPage mainPage = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickBuildWithParameters()
                .clickBuild()
                .getBreadcrumb()
                .clickDashboardButton();

        Assert.assertEquals(mainPage.getJobBuildStatusIcon(NEW_FREESTYLE_NAME), "Success");

        int sizeOfPermalinksList = mainPage
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .getSizeOfPermalinksList();

        Assert.assertTrue(sizeOfPermalinksList == 4);
    }

    @Test
    public void testFreestyleProjectJob() {
        String nameProject = "Hello world";
        String steps = "javac ".concat(nameProject.concat(".java\njava ".concat(nameProject)));

        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameProject)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addBuildStepsExecuteShell(steps)
                .clickSaveButton()
                .clickBuildNow()
                .clickIconBuildOpenConsoleOutput(1)
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build Finished: FAILURE");
    }

    @Test
    public void testAddDescriptionFromConfigureDropDownAndPreview() {
        final String descriptionText = "In publishing and graphic design, Lorem ipsum is a placeholder " +
                                       "text commonly used to demonstrate the visual form of a document or a typeface without relying .";

        String previewText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickConfigureDropDown(FREESTYLE_NAME, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addDescription(descriptionText)
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, descriptionText);

        String actualDescriptionText = new FreestyleProjectPage(getDriver())
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescriptionText, descriptionText);
    }


    @Test(dependsOnMethods = "testEnableJenkinsToBlockBuildsWhenUpstreamProjectIsBuilding")
    public void testDeleteFreestyleProject() {
        final String projName = NEW_FREESTYLE_NAME;

        boolean isProjectPresent = new MainPage(getDriver())
                .clickJobName(projName, new FreestyleProjectPage(getDriver()))
                .clickDeleteAndAccept()
                .verifyJobIsPresent(projName);

        Assert.assertFalse(isProjectPresent);
    }

    @Test
    public void testDeleteProjectFromDropdown() {
        final String projectName = "Name";

        String h2text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(projectName)
                .acceptAlert()
                .clickMyViewsSideMenuLink()
                .getStatusMessageText();

        Assert.assertEquals(h2text, "This folder is empty");
    }

    @Test
    public void testDeleteProjectWithoutConfirmation() {
        final String name = "projectToDeleteWithoutConfirmation";

        boolean projectIsPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickDeleteProjectOnDropDown()
                .dismissAlert()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(name);

        Assert.assertTrue(projectIsPresent);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProjectUsingDropDownMenu")
    public void testAddingAProjectOnGitHubToTheFreestyleProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        final String actualNameRepo = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .selectFromJobDropdownMenuTheGitHub(NEW_FREESTYLE_NAME);

        Assert.assertEquals(actualNameRepo, expectedNameRepo);
    }

    @Test(dependsOnMethods = "testAddingAProjectOnGitHubToTheFreestyleProject")
    public void testSetParametersToDiscardOldBuilds() {
        final int daysToKeepBuilds = 3;
        final int maxOfBuildsToKeep = 5;

        FreestyleProjectConfigPage freestyleProjectConfigPage = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickOldBuildCheckBox()
                .enterDaysToKeepBuilds(daysToKeepBuilds)
                .enterMaxNumOfBuildsToKeep(maxOfBuildsToKeep)
                .clickSaveButton()
                .clickConfigure();

        Assert.assertEquals(Integer
                .parseInt(freestyleProjectConfigPage.getDaysToKeepBuilds("value")), daysToKeepBuilds);
        Assert.assertEquals(Integer
                .parseInt(freestyleProjectConfigPage.getMaxNumOfBuildsToKeep("value")), maxOfBuildsToKeep);
    }

    @Test
    public void testAddChoiceParameter() {
        final String parameterType = "Choice Parameter";
        final String parameterName = "Choice parameter name test";
        final String parameterDesc = "Choice parameter desc test";
        final List<String> parameterChoicesList = new ArrayList<>() {{
            add("choice one");
            add("choice two");
            add("choice three");
        }};

        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, false);

        BuildWithParametersPage buildPage = new FreestyleProjectPage(getDriver())
                .clickConfigure()
                .checkProjectIsParametrized()
                .openAddParameterDropDown()
                .selectParameterInDropDownByType(parameterType)
                .inputParameterName(parameterName)
                .inputParameterChoices(parameterChoicesList)
                .inputParameterDesc(parameterDesc)
                .clickSaveButton()
                .clickBuildWithParameters();

        Assert.assertTrue(buildPage.isParameterNameDisplayed(parameterName));
        Assert.assertEquals(buildPage.getParameterDescription(), parameterDesc);
        Assert.assertEquals(buildPage.getChoiceParametersValuesList(), parameterChoicesList);
    }

    @Test(dependsOnMethods = "testSetParametersToDiscardOldBuilds")
    public void testAddBooleanParameterTheFreestyleProject() {
        final String booleanParameter = "Boolean Parameter";
        final String booleanParameterName = "Boolean";

        final boolean checkedSetByDefault = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .checkProjectIsParametrized()
                .openAddParameterDropDown()
                .selectParameterInDropDownByType(booleanParameter)
                .inputParameterName(booleanParameterName)
                .selectCheckboxSetByDefault()
                .clickSaveButton()
                .clickBuildWithParameters()
                .checkedTrue();

        Assert.assertTrue(checkedSetByDefault);
    }

    @Test
    public void testBuildStepsOptions() {
        List<String> expectedOptionsInBuildStepsSection = List.of("Execute Windows batch command", "Execute shell",
                "Invoke Ant", "Invoke Gradle script", "Invoke top-level Maven targets", "Run with timeout",
                "Set build status to \"pending\" on GitHub commit");

        List<String> actualOptionsInBuildStepsSection = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .openBuildStepOptionsDropdown()
                .getOptionsInBuildStepDropdown();

        Assert.assertEquals(actualOptionsInBuildStepsSection, expectedOptionsInBuildStepsSection);
    }


    @Test(dependsOnMethods = "testPresenceOfBuildLinksAfterBuild")
    public void testSetRateLimitForBuilds() {
        final String timePeriod = "Week";

        final String actualTimePeriod = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .checkThrottleBuilds()
                .selectTimePeriod(timePeriod)
                .clickSaveButton()
                .clickConfigure()
                .getTimePeriodText();

        Assert.assertEquals(actualTimePeriod, timePeriod);
    }


    @Test(dependsOnMethods = "testSetRateLimitForBuilds")
    public void testAllowParallelBuilds() {
        final String checkExecuteConcurrentBuilds = "rowvg-start tr";

        final String statusExecuteConcurrentBuilds = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickCheckBoxExecuteConcurrentBuilds()
                .clickSaveButton()
                .clickConfigure()
                .getTrueExecuteConcurrentBuilds()
                .getAttribute("class");

        Assert.assertEquals(statusExecuteConcurrentBuilds, checkExecuteConcurrentBuilds);
    }

    @Ignore
    @Test(dependsOnMethods = "testAllowParallelBuilds")
    public void testSetPeriodForJenkinsToWaitBeforeActuallyStartingTriggeredBuild() {
        final String expectedQuietPeriod = "10";

        final String actualQuietPeriod = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .clickQuietPeriod()
                .inputQuietPeriod(expectedQuietPeriod)
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .getQuietPeriod();

        Assert.assertEquals(actualQuietPeriod, expectedQuietPeriod);
    }


    @Test(dependsOnMethods = "testAllowParallelBuilds")
    public void testSetNumberOfCountForJenkinsToCheckOutFromTheSCMUntilItSucceeds() {
        final String retryCount = "5";

        final String actualRetryCount = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .clickRetryCount()
                .inputSCMCheckoutRetryCount(retryCount)
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .getCheckoutRetryCountSCM();

        Assert.assertEquals(actualRetryCount, retryCount);
    }

    @Test(dependsOnMethods = "testSetNumberOfCountForJenkinsToCheckOutFromTheSCMUntilItSucceeds")
    public void testEnableJenkinsToBlockBuildsWhenUpstreamProjectIsBuilding() {
        final String checkBlockBuildWhenUpstreamProjectIsBuilding = "rowvg-start tr";

        final boolean statusBlockBuildWhenUpstreamProjectIsBuilding = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .clickBlockBuildWhenUpstreamProjectIsBuilding()
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedDropdownMenu()
                .getTrueBlockBuildWhenUpstreamProjectIsBuilding();

        Assert.assertTrue(statusBlockBuildWhenUpstreamProjectIsBuilding, "error input is not selected");
    }
}

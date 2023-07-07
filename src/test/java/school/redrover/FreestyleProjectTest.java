package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.model.*;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static school.redrover.runner.TestUtils.createJob;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = "FREESTYLE_NAME";
    private static final String NEW_FREESTYLE_NAME = "NEW_FREESTYLE_NAME";
    private static final String DESCRIPTION_TEXT = "DESCRIPTION_TEXT";
    private static final String NEW_DESCRIPTION_TEXT = "NEW_DESCRIPTION_TEXT";

    @Test
    public void testCreateFromNewItem() {
        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo();

        Assert.assertTrue(projectName.jobIsDisplayed(FREESTYLE_NAME));
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
    public void testCreateWithEmptyName() {
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
                .isOkButtonEnabled();

        Assert.assertFalse(okButton);
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

    @Test(dependsOnMethods = "testCreateFromNewItem")
    public void testAccessConfigurationPageFromDashboard() {
        final String breadcrumb = "Dashboard > " + FREESTYLE_NAME + " > Configuration";

        FreestyleProjectConfigPage freestyleConfigPage = new MainPage(getDriver())
                .clickConfigureDropDown(
                        FREESTYLE_NAME, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver()))
                );

        Assert.assertEquals(freestyleConfigPage.getBreadcrumb().getFullBreadcrumbText(), breadcrumb);
        Assert.assertEquals(freestyleConfigPage.getTitle(), "Configure");
    }

    @Test(dependsOnMethods = "testAccessConfigurationPageFromDashboard")
    public void testDisableFromProjectPage() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickDisable();

        List<String> DropDownMenu = new MainPage(getDriver())
                .getListOfProjectMenuItems(FREESTYLE_NAME);

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(DropDownMenu.contains("Build Now"), "'Build Now' option is present in drop-down menu");
        soft.assertEquals(projectName.getDisabledMessageText(), "This project is currently disabled");
        soft.assertEquals(projectName.getEnableButtonText(), "Enable");
        soft.assertAll();
    }

    @Test(dependsOnMethods = "testDisableFromProjectPage")
    public void testEnableFromProjectPage() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickEnable();

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(projectName.getDisableButtonText(),"Disable Project");
        soft.assertEquals(projectName.clickConfigure().getTextEnabled(), "Enabled");
        soft.assertEquals(projectName.getHeader().clickLogo().getJobBuildStatusIcon(FREESTYLE_NAME), "Not built");
        soft.assertAll();
    }

    @Test
    public void testDisableFromConfigurationPage() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        FreestyleProjectConfigPage configPage = new MainPage(getDriver())
                .clickConfigureDropDown(FREESTYLE_NAME, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .switchCheckboxDisable();

        String availableMode = configPage
                .getTextEnabled();

        MainPage mainPage = configPage
                .clickSaveButton()
                .getHeader()
                .clickLogo();

        Assert.assertEquals(availableMode, "Enabled");
        Assert.assertEquals(mainPage.getJobBuildStatusIcon(FREESTYLE_NAME), "Disabled");
        Assert.assertFalse(mainPage.isScheduleBuildOnDashboardAvailable(FREESTYLE_NAME), "Error: disabled project cannot be built");
    }

    @Test(dependsOnMethods = "testPreviewDescriptionFromProjectPage")
    public void testAddDescription() {
        String actualDescription = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .addDescription("Freestyle project")
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescription, "Freestyle project");
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]{{"!","!"}, {"@","@"}, {"#","#"}, {"$","$"}, {"%","%"}, {"^","^"}, {"&","&amp;"}, {"*","*"},
                {"?","?"}, {"|","|"}, {">","&gt;"}, {"<","&lt;"}, {"[","["}, {"]","]"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testRenameWithInvalidData(String invalidData, String expectedResult) {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String actualErrorMessage = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickRename()
                .enterNewName(invalidData)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(actualErrorMessage,"‘" + expectedResult + "’ is an unsafe character");
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testRenameToTheCurrentNameAndGetError() {
        String errorMessage = new MainPage(getDriver())
                .dropDownMenuClickRename(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .enterNewName(FREESTYLE_NAME)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameToTheCurrentNameAndGetError")
    public void testRenameFromSideMenu() {
        String projectName = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickRename()
                .enterNewName(FREESTYLE_NAME + " New")
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(projectName, "Project " + FREESTYLE_NAME + " New");
    }

    @Test(dependsOnMethods = "testRenameFromSideMenu")
    public void testRenameFromDropDownMenu() {
        String actualFreestyleProjectName = new MainPage(getDriver())
                .dropDownMenuClickRename(FREESTYLE_NAME + " New", new FreestyleProjectPage(getDriver()))
                .enterNewName(NEW_FREESTYLE_NAME)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualFreestyleProjectName, "Project " + NEW_FREESTYLE_NAME);
    }

    @Test
    public void testAddDescriptionFromConfigurationPage() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, false);

        String description = new FreestyleProjectPage(getDriver())
                .clickConfigure()
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescriptionFromConfigurationPage")
    public void testEditDescription() {
        String editDescription = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickEditDescription()
                .removeOldDescriptionAndAddNew(NEW_DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescription();
        Assert.assertEquals(editDescription, NEW_DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddEmailNotificationToPostBuildActions")
    public void testPreviewDescriptionFromProjectPage() {
        String previewDescription = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickPreviewButton()
                .getPreviewDescription();

        Assert.assertEquals(previewDescription, "DESCRIPTION_TEXT");
    }

    @Test
    public void testVisibleProjectNameOnProjectPage() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String projectNameOnProjectPage = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .getJobName();

        Assert.assertEquals(projectNameOnProjectPage, "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testBuildStepsExecuteShell() {
        final String commandFieldText = "echo Hello";

        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addExecuteShellBuildStep(commandFieldText)
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .clickIconBuildOpenConsoleOutput(1)
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains(commandFieldText), "Command wasn't run OR test was run on the Windows");
        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build wasn't finished successfully");
    }

    @Test
    public void testCreateBuildNowFromSideMenu() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        boolean buildHeaderIsDisplayed = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickBuildNowFromSideMenu()
                .clickIconBuildOpenConsoleOutput(1)
                .isDisplayedBuildTitle();

        Assert.assertTrue(buildHeaderIsDisplayed, "build not created");
    }

    @Test
    public void testPresenceOfBuildLinksAfterBuild() {
        TestUtils.createJob(this, NEW_FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String statusIcon = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickBuildNowFromSideMenu()
                .getBreadcrumb()
                .clickDashboardButton()
                .getJobBuildStatusIcon(NEW_FREESTYLE_NAME);

        int sizeOfPermalinksList = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .getSizeOfPermalinksList();

        Assert.assertEquals(statusIcon, "Success");
        Assert.assertEquals(sizeOfPermalinksList, 4);
    }

   // @Ignore
    @Test
    public void testCreateBuildInvokeMavenTargets() {
        String nameProject = "Hello world";
        String steps1 = "javac ".concat(nameProject.concat(".java\njava ".concat(nameProject)));
        String step="-version";
        TestUtils.createJob(this, FREESTYLE_NAME,TestUtils.JobType.FreestyleProject,true);

        String consoleOutput = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .openBuildStepOptionsDropdown()
                .addBuildInvokeMavenTargets(step)  //addBuildInvokeMavenTargets(steps)
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .clickIconBuildOpenConsoleOutput(1)
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build wasn't finished successfully");
       // Assert.assertTrue(consoleOutput.isDisplayedBuildTitle(), "build not created");
    }

    @Test
    public void testPreviewDescriptionFromConfigurationPage() {
        final String descriptionText = "In publishing and graphic design, Lorem ipsum is a placeholder " +
                "text commonly used to demonstrate the visual form of a document or a typeface without relying .";
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String previewText = new MainPage(getDriver())
                .clickConfigureDropDown(FREESTYLE_NAME, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .addDescription(descriptionText)
                .clickPreview()
                .getPreviewText();

        String actualDescriptionText = new FreestyleProjectPage(getDriver())
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(previewText, descriptionText);
        Assert.assertEquals(actualDescriptionText, descriptionText);
    }

    @Test(dependsOnMethods = "testEnableJenkinsToBlockBuildsWhenUpstreamProjectIsBuilding")
    public void testCancelDeletingFromSideMenu() {
        boolean isProjectPresent = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickDeleteAndCancel()
                .getHeader()
                .clickLogo()
                .verifyJobIsPresent(NEW_FREESTYLE_NAME);

        Assert.assertTrue(isProjectPresent, "error! project is not displayed!");
    }

    @Test(dependsOnMethods = "testCancelDeletingFromSideMenu")
    public void testDeleteItemFromSideMenu() {
        boolean isProjectPresent = new MainPage(getDriver())
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickDeleteAndAccept()
                .WelcomeIsDisplayed();

        Assert.assertTrue(isProjectPresent, "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testDeleteItemFromDropDown() {
        final String projectName = "Name";

        MyViewsPage h2text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(projectName)
                .acceptAlert()
                .clickMyViewsSideMenuLink();

        Assert.assertEquals(h2text.getStatusMessageText(), "This folder is empty");
        Assert.assertTrue(h2text.getHeader().clickLogo().WelcomeIsDisplayed());
    }

    @Test
    public void testCancelDeletingFromDropDownMenu() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        boolean projectIsPresent = new MainPage(getDriver())
                .dropDownMenuClickDelete(FREESTYLE_NAME)
                .dismissAlert()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(FREESTYLE_NAME);

        Assert.assertTrue(projectIsPresent, "Error: the name of the Freestyle project is not shown");
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
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

    @Test
    public void testCreateWithLongName() {
        String longName = RandomStringUtils.randomAlphanumeric(256);

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(longName)
                .selectJobAndOkAndGoToBugPage(TestUtils.JobType.FreestyleProject)
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "A problem occurred while processing the request.");
    }

    @Test
    public void testCreateWithSpaceInsteadName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.FreestyleProject);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testEnableFromProjectPage")
    public void testAddEmailNotificationToPostBuildActions() {
        final String email = "email@email.com";

        String currentEmail = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickPostBuildActionsButton()
                .clickAddPostBuildActionDropDown()
                .clickEmailNotification()
                .setEmailNotification(email)
                .clickSaveButton()
                .clickConfigure()
                .clickPostBuildActionsButton()
                .getEmailNotificationFieldText();

        Assert.assertEquals(currentEmail, email);
    }

    @DataProvider(name = "invalid-characters")
    public Object[][] getInvalidCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "invalid-characters")
    public void testCreateUsingInvalidData(String character) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(character)
                .selectJobType(TestUtils.JobType.FreestyleProject);

        Assert.assertFalse(newJobPage.isOkButtonEnabled(), "The button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + character + "’ is an unsafe character");
    }

    @Test
    public void testConfigurePostBuildActionBuildOtherProjects() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);
        TestUtils.createJob(this, NEW_FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String lastBuildInfo = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickPostBuildActionsButton()
                .clickAddPostBuildActionDropDown()
                .clickBuildOtherProjects()
                .setBuildOtherProjects(NEW_FREESTYLE_NAME)
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .getHeader()
                .clickLogo()
                .clickJobName(NEW_FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickLastBuildLink()
                .getBuildInfo();

        Assert.assertEquals(lastBuildInfo, "Started by upstream project " + FREESTYLE_NAME);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testDeleteBuildNowFromSideMenu() {
        boolean noBuildsMessage = new MainPage(getDriver())
                .clickPlayBuildForATestButton(FREESTYLE_NAME)
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickDeleteBuildFromDropDownMenu()
                .clickYesButton()
                .isNoBuildsDisplayed();

        Assert.assertTrue(noBuildsMessage, "error! No builds message is not display");
    }

    @Test(dependsOnMethods = "testDeleteBuildNowFromSideMenu")
    public void testDeleteBuildNowFromBuildPage() {
        boolean noBuildsMessage = new MainPage(getDriver())
                .clickPlayBuildForATestButton(FREESTYLE_NAME)
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickLastBuildLink()
                .clickDeleteBuild(new FreestyleProjectPage(getDriver()))
                .clickYesButton()
                .isNoBuildsDisplayed();

        Assert.assertTrue(noBuildsMessage, "error! No builds message is not display");
    }

    @Test
    public void testCreateFromMyViewsNewItem() {
        MainPage projectName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(projectName.jobIsDisplayed(FREESTYLE_NAME), "Error: the folder name is not displayed");
    }

    @Test
    public void testCreateFromMyViewsCreateAJob() {
        MainPage projectName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickCreateAJob()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(projectName.jobIsDisplayed(FREESTYLE_NAME), "Error: the Freestyle Project's name is not displayed on Dashboard from Home page");
        Assert.assertTrue(projectName.clickMyViewsSideMenuLink()
                .jobIsDisplayed(FREESTYLE_NAME), "Error: the Freestyle Project's name is not displayed on Dashboard from MyViews page");
    }

    @Test
    public void testConfigurePostBuildActionArchiveArtifacts() {
        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);
        String archiveTheArtifacts = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickPostBuildActionsButton()
                .clickAddPostBuildActionDropDown()
                .clickArchiveTheArtifacts()
                .clickSaveButton()
                .clickConfigure()
                .clickPostBuildActionsButton()
                .getTextArchiveArtifacts();

        Assert.assertEquals(archiveTheArtifacts, "Archive the artifacts\n" +
                "?\n" +
                "Files to archive\n" +
                "?\n" +
                "Advanced");
    }

    @Test
    public void testCreateFromManageJenkinsPage() {
        boolean jobIsDisplayed = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(FREESTYLE_NAME);

        Assert.assertTrue(jobIsDisplayed, "Error: the Freestyle Project's name is not displayed on Dashboard");
    }
}

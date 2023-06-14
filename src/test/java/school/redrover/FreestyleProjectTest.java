package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
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
                .getProjectName();

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
        Assert.assertEquals(mainPage.getOnlyProjectName(), PROJECT_NAME);
    }

    @Test
    public void testCreateWithExistingName() {
        createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String itemAlreadyExistsMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobAndOkAndGoError(TestUtils.JobType.FreestyleProject)
                .getErrorMessage();

        Assert.assertEquals(itemAlreadyExistsMessage,
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
                .okButtonDisabled();

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

    @Test
    public void testDisableProject() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickTheDisableProjectButton();

        Assert.assertEquals(projectName.getWarningMessage(), "This project is currently disabled");
    }

    @Test
    public void testEnableProject() {
        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickTheDisableProjectButton()
                .clickTheEnableProjectButton()
                .getHeader()
                .clickLogo();

        Assert.assertEquals(projectName.getJobBuildStatusIcon(FREESTYLE_NAME), "Not built");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testAddDescription() {
        String description = "Freestyle project";

        String actualDescription = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigureButton()
                .addDescription(description)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescription, description);
    }

    @Test
    public void testRenameFreestyleProject() {
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickRenameProject(FREESTYLE_NAME)
                .enterNewName(FREESTYLE_NAME + " New")
                .clickRenameButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME + " New");
    }

    @Ignore
    @Test(dependsOnMethods = "testPresenceOfBuildLinksAfterBuild")
    public void testRenameFreestyleProjectUsingDropDownMenu() {
        String actualFreestyleProjectName = new MainPage(getDriver())
                .dropDownMenuClickRename(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .enterNewName(NEW_FREESTYLE_NAME)
                .clickRenameButton()
                .getProjectName();

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

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME);
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

        String projectNameFromViewPage = projectPage.getProjectName();
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
                .selectBuildNow()
                .openConsoleOutputForBuild()
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("echo Hello"), "Command wasn't run");
        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build wasn't finished successfully");
    }

    @Test
    public void testCreatedNewBuild() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName("Engineer", new FreestyleProjectPage(getDriver()))
                .selectBuildNow()
                .selectBuildItemTheHistoryOnBuildPage();

        Assert.assertTrue(new BuildPage(getDriver()).buildHeaderIsDisplayed(), "build not created");
    }
@Ignore
    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testPresenceOfBuildLinksAfterBuild() {

        MainPage mainPage = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .selectBuildNow()
                .getHeader()
                .clickDashboardButton();

        Assert.assertEquals(mainPage.getTitleValueOfBuildStatusIconElement(), "Success");

        int sizeOfPermalinksList = mainPage
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .getSizeOfPermalinksList();

        Assert.assertTrue(sizeOfPermalinksList == 4);
    }

    @Ignore
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
                .selectBuildNow()
                .openConsoleOutputForBuild()
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
                .clickConfigureDropDown(FREESTYLE_NAME)
                .addDescription(descriptionText)
                .clickPreviewButton()
                .getPreviewDescription();

        Assert.assertEquals(previewText, descriptionText);

        String actualDescriptionText = new FreestyleProjectPage(getDriver())
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescriptionText, descriptionText);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testDeleteFreestyleProject() {
        final String projName = FREESTYLE_NAME + " New";

        boolean isProjectPresent = new MainPage(getDriver())
                .clickJobName(projName, new FreestyleProjectPage(getDriver()))
                .clickDeleteProject()
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
                .verifyJobIsPresent(name);

        Assert.assertTrue(projectIsPresent);
    }

    @Test
    public void testAddingAProjectOnGitHubToTheFreestyleProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        TestUtils.createJob(this, FREESTYLE_NAME, TestUtils.JobType.FreestyleProject, true);

        String actualNameRepo = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigureButton()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .openJobDropDownMenu(FREESTYLE_NAME)
                .selectFromJobDropdownMenuTheGitHub();

        Assert.assertEquals(actualNameRepo, expectedNameRepo);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testSetParametersToDiscardOldBuilds() {
        final int daysToKeepBuilds = 3;
        final int maxOfBuildsToKeep = 5;

        FreestyleProjectConfigPage freestyleProjectConfigPage = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigureButton()
                .clickOldBuildCheckBox()
                .enterDaysToKeepBuilds(daysToKeepBuilds)
                .enterMaxNumOfBuildsToKeep(maxOfBuildsToKeep)
                .clickSaveButton()
                .clickConfigureButton();

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

        BuildPage buildPage = new FreestyleProjectPage(getDriver())
                .clickConfigureButton()
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

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testAddBooleanParameterTheFreestyleProject() {
        final String booleanParameter = "Boolean Parameter";
        final String booleanParameterName = "Boolean";

        boolean checkedSetByDefault = new MainPage(getDriver())
                .clickJobName(FREESTYLE_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigureButton()
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
}

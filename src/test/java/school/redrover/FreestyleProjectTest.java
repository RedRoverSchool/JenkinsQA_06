package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.UUID;
import static school.redrover.runner.TestUtils.createFreestyleProject;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = "FREESTYLE_NAME";
    private static final String NEW_FREESTYLE_NAME = "NEW_FREESTYLE_NAME";
    private static final String DESCRIPTION_TEXT = "DESCRIPTION_TEXT";
    private static final String NEW_DESCRIPTION_TEXT = "NEW_DESCRIPTION_TEXT";

    @Test
    public void testCreateFreestyleProject() {
        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), FREESTYLE_NAME);
    }

    @Test
    public void testCreateFSProjectWithDefaultConfigurations() {
        final String PROJECT_NAME = UUID.randomUUID().toString();

        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .enterItemName(PROJECT_NAME)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.getProjectStatusTable().isDisplayed());
        Assert.assertEquals(mainPage.getProjectsList().size(), 1);
        Assert.assertEquals(mainPage.getOnlyProjectName(), PROJECT_NAME);
    }

    @Test
    public void testCreateWithExistingName() {
        createFreestyleProject(this, FREESTYLE_NAME, true);

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
                .selectTypeJob(TestUtils.JobType.FreestyleProject)
                .getItemNameRequiredErrorText();

        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void testOKButtonIsDisabledWhenEmptyName() {
        boolean okButton = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .selectTypeJob(TestUtils.JobType.FreestyleProject)
                .okButtonDisabled();

        Assert.assertFalse(okButton);
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]
                {{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateFreestyleProjectWithInvalidName(String wrongCharacter) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testNavigateToChangePage() {
        createFreestyleProject(this, "Engineer", true);

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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickTheDisableProjectButton();

        Assert.assertEquals(projectName.getWarningMessage(), "This project is currently disabled");
    }

    @Test
    public void testEnableProject() {
        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickRenameProject(FREESTYLE_NAME)
                .enterNewName(FREESTYLE_NAME + " New")
                .clickRenameButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME + " New");
    }

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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName("Engineer", new FreestyleProjectPage(getDriver()))
                .selectBuildNow()
                .selectBuildItemTheHistoryOnBuildPage();

        Assert.assertTrue(new BuildPage(getDriver()).getBuildHeader().isDisplayed(), "build not created");
    }

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

    @Test
    public void testFreestyleProjectJob() {
        String nameProject = "Hello world";
        String steps = "javac ".concat(nameProject.concat(".java\njava ".concat(nameProject)));

        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameProject)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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

        MyViewsPage h2text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(projectName)
                .acceptAlert()
                .clickMyViewsSideMenuLink();

        Assert.assertEquals(h2text.getStatusMessageText(), "This folder is empty");
    }

    @Test
    public void testDeleteProjectWithoutConfirmation() {
        final String name = "projectToDeleteWithoutConfirmation";
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectTypeJobAndOk(TestUtils.JobType.FreestyleProject, new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickDeleteProjectOnDropDown()
                .dismissAlert()
                .clickDashboard()
                .getProjectNameMainPage(name);

        Assert.assertEquals(projectName, name);
    }

    @Test
    public void testAddingAProjectOnGitHubToTheFreestyleProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, true);

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

}

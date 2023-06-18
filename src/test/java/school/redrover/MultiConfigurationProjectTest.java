package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.MultiConfigurationProjectPage;
import school.redrover.model.jobsconfig.MultiConfigurationProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MultiConfigurationProjectTest extends BaseTest {

    private static final String NAME = "MULTI_CONFIGURATION_NAME";
    private static final String NEW_NAME = "MULTI_CONFIGURATION_NEW_NAME";

    @Test
    public void testCreateProject() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, false);

        Assert.assertEquals(new MultiConfigurationProjectPage(getDriver()).getJobName().substring(8, 32), NAME);

        new MainPage(getDriver())
                .getHeader()
                .clickLogo();

        Assert.assertEquals(new MainPage(getDriver()).getJobName(), NAME);
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testCreateMultiConfigurationProjectWithEqualName() {
        final String errorMessageName = "A job already exists with the name " + "‘" + NAME + "’";

        String error = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .getErrorPage()
                .getErrorMessage();

        Assert.assertEquals(error, errorMessageName);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProjectWithEqualName")
    public void testRenameFromDropDownMenu() {
        String newNameProject = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getJobName();

        Assert.assertEquals(newNameProject, NEW_NAME);
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
    public void testRename() {
        String newName = new MainPage(getDriver())
                .clickJobName(NEW_NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickRename()
                .enterNewName(NAME)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(newName, "Project " + NAME);
    }

    @DataProvider(name = "unsafeCharacter")
    public static Object[][] provideUnsafeCharacters() {
        return new Object[][]{{'!'}, {'@'}, {'#'}, {'$'}, {'%'}, {'^'}, {'&'},
                {'*'}, {'['}, {']'}, {'\\'}, {'|'}, {';'}, {':'},
                {'<'}, {'>'}, {'/'}, {'?'}};
    }

    @Test(dataProvider = "unsafeCharacter")
    public void testVerifyAnErrorIfCreatingMultiConfigurationProjectWithUnsafeCharacterInName(char unsafeSymbol) {
        String invalidMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(unsafeSymbol + "MyProject")
                .getItemInvalidMessage();

        Assert.assertEquals(invalidMessage, "» ‘" + unsafeSymbol + "’" + " is an unsafe character");
    }

    @Test(dependsOnMethods = "testRename")
    public void testDisable() {

        MultiConfigurationProjectPage disabled = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDisable();

        Assert.assertEquals(disabled.getDisabledMessageText(), "This project is currently disabled");
        Assert.assertEquals(disabled.getEnableButtonText(), "Enable");
    }

    @Test(dependsOnMethods = "testDisable")
    public void testCheckDisableSwitchButtonOnConfigurePage() {
        String statusSwitchButton = new MainPage(getDriver())
                .clickConfigureDropDown(NAME, new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .getTextDisable();

        Assert.assertEquals(statusSwitchButton, "Disabled");
    }

    @Test(dependsOnMethods = "testCheckDisableSwitchButtonOnConfigurePage")
    public void testCheckDisableIconOnDashboard() {
        String statusIcon = new MainPage(getDriver())
                .getJobBuildStatusIcon(NAME);

        Assert.assertEquals(statusIcon, "Disabled");
    }

    @Test(dependsOnMethods = "testCheckDisableIconOnDashboard")
    public void testBuildNowOptionNotPresentInDisabledProject() {
        List<String> dropDownMenuItems = new MainPage(getDriver())
                .getListOfProjectMenuItems(NAME);

        Assert.assertFalse(dropDownMenuItems.contains("Build Now"), "'Build Now' option is present in drop-down menu");
    }

    @Test(dependsOnMethods = "testBuildNowOptionNotPresentInDisabledProject")
    public void testConfigurePageEnable() {
        String projectPage = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickConfigure()
                .switchCheckboxEnabled()
                .clickSaveButton()
                .getDisableButtonText();

        Assert.assertEquals(projectPage, "Disable Project");
    }

    @Test(dependsOnMethods = "testConfigurePageEnable")
    public void testEnabled() {
        String enabledButtonText = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickConfigure()
                .getTextEnabled();

        Assert.assertEquals(enabledButtonText, "Enabled");
    }

    @Test(dependsOnMethods = "testEnabled")
    public void testJobDropdownDelete() {
        String helloMessage = new MainPage((getDriver()))
                .dropDownMenuClickDelete(NAME)
                .acceptAlert()
                .getWelcomeText();

        Assert.assertEquals(helloMessage, "Welcome to Jenkins!");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testProjectPageDelete() {
        MainPage deletedProjPage = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDelete();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getWelcomeText(), "Welcome to Jenkins!");
    }

    @Test
    public void testCheckGeneralParametersDisplayedAndClickable() {
        MultiConfigurationProjectConfigPage config = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())));

        boolean checkboxesVisibleClickable = true;
        for (int i = 4; i <= 8; i++) {
            WebElement checkbox = config.getCheckboxById(i);
            if (!checkbox.isDisplayed() || !checkbox.isEnabled()) {
                checkboxesVisibleClickable = false;
                break;
            }
        }
        Assert.assertTrue(checkboxesVisibleClickable);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testMultiConfigurationProjectAddDescription1() {
        final String text = "text";

        String addDescriptionText = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .changeDescriptionWithoutSaving(text)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(addDescriptionText, text);
    }

    @Ignore
    @Test
    public void testBuildNowDropDownMenuMultiConfigurationProject() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, true);

        Assert.assertEquals(new MainPage(getDriver()).getJobBuildStatus(NAME), "Not built");

        MultiConfigurationProjectPage multiConfigurationProjectPage = new MainPage(getDriver())
                .clickJobDropdownMenuBuildNow(NAME)
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()));

        Assert.assertEquals(multiConfigurationProjectPage.getJobBuildStatus(NAME), "Success");
    }

    @DataProvider(name = "wrong character")
    public Object[][] wrongCharacters() {
        return new Object[][]{
                {"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}
        };
    }

    @Test(dataProvider = "wrong character")
    public void testCreateProjectWithWrongName(String wrongCharacter) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @DataProvider(name = "unsafe-character")
    public Object[][] putUnsafeCharacterInputField() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}};
    }

    @Test(dataProvider = "unsafe-character")
    public void testCreateMultiConfigurationProjectWithSpecialSymbols(String unsafeCharacter) {
        final String expectedResult = "» ‘" + unsafeCharacter + "’ is an unsafe character";
        String messageUnderInputField = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(unsafeCharacter)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .getItemInvalidMessage();

        Assert.assertEquals(messageUnderInputField, expectedResult);
    }

    @Test
    public void testCheckExceptionOfNameToMultiConfiguration() {
        String exceptionMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .getItemNameRequiredMessage();

        Assert.assertEquals(exceptionMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testDeleteProjectFromDropDownMenu() {
        List<String> deleteProject = new MainPage(getDriver())
                .dropDownMenuClickDelete(NAME)
                .acceptAlert()
                .getJobList();

        Assert.assertEquals(deleteProject.size(), 0);
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testAddDescriptionInMultiConfigurationProject() {
        final String textDescription = "Text Description Test";

        String getDescription = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving(textDescription)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(getDescription, textDescription);
    }

    @DataProvider(name = "unsafeCharacters")
    public static Object[][] unsafeCharacterArray() {
        return new Object[][]{
                {'!', "!"}, {'@', "@"}, {'#', "#"}, {'$', "$"}, {'%', "%"}, {'^', "^"}, {'&', "&amp;"},
                {'*', "*"}, {'[', "["}, {']', "]"}, {'\\', "\\"}, {'|', "|"}, {';', ";"}, {':', ":"},
                {'<', "&lt;"}, {'>', "&gt;"}, {'/', "/"}, {'?', "?"}};
    }

    @Test(dataProvider = "unsafeCharacters")
    public void testVerifyProjectNameRenameWithUnsafeSymbols(char unsafeSymbol, String htmlUnsafeSymbol) {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, true);

        String errorNotification = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(NAME + unsafeSymbol)
                .getErrorMessage();

        Assert.assertEquals(errorNotification, String.format("‘%s’ is an unsafe character", unsafeSymbol));

        CreateItemErrorPage createItemErrorPage = new RenamePage<>(new MultiConfigurationProjectPage(getDriver()))
                .clickRenameButtonAndGoError();

        Assert.assertEquals(createItemErrorPage.getHeaderText(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), String.format("‘%s’ is an unsafe character", htmlUnsafeSymbol));
    }

    @Test
    public void testCreateProjectWithDescription() {
        final String multiConfigurationProjectName = "New project";
        final String description = "Description text";

        String descriptionOnProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfigurationProjectName)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .clickSaveButton()
                .changeDescriptionWithoutSaving(description)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(descriptionOnProjectPage, description);
    }

    @Test
    public void testConfigureOldBuildForProject() {
        final String multiConfProjectName = "New project";
        final int displayedDaysToKeepBuilds = 5;
        final int displayedMaxNumOfBuildsToKeep = 7;

        MultiConfigurationProjectConfigPage multiConfigurationProjectConfigPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfProjectName)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .clickSaveButton()
                .clickConfigure()
                .clickOldBuildCheckBox()
                .enterDaysToKeepBuilds(displayedDaysToKeepBuilds)
                .enterMaxNumOfBuildsToKeep(displayedMaxNumOfBuildsToKeep)
                .clickSaveButton()
                .clickConfigure();

        Assert.assertEquals(Integer.parseInt(
                multiConfigurationProjectConfigPage.getDaysToKeepBuilds("value")), displayedDaysToKeepBuilds);
        Assert.assertEquals(Integer.parseInt(
                multiConfigurationProjectConfigPage.getMaxNumOfBuildsToKeep("value")), displayedMaxNumOfBuildsToKeep);
    }

    @Test
    public void testCreateProjectWithSpaceInsteadName() {
        final String expectedResult = "No name is specified";

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(" ")
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .getErrorPage()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expectedResult);
    }

    @Test
    public void testAddingAProjectOnGithubToTheMultiConfigurationProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, true);

        String actualNameRepo = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickConfigure()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .selectFromJobDropdownMenuTheGitHub(NAME);

        Assert.assertEquals(actualNameRepo, expectedNameRepo);
    }
}

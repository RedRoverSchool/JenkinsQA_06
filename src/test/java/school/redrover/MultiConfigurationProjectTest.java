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
    private static final String DESCRIPTION = "Description";

    @Test
    public void testCreateProject() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, false);

        Assert.assertEquals(new MultiConfigurationProjectPage(getDriver()).getJobName().substring(8, 32), NAME);

        new MainPage(getDriver())
                .getHeader()
                .clickLogo();

        Assert.assertEquals(new MainPage(getDriver()).getJobName(NAME), NAME);
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testCreateMultiConfigurationProjectWithEqualName() {
        final String errorMessageName = "A job already exists with the name " + "‘" + NAME + "’";

        CreateItemErrorPage errorPage =
                TestUtils.createJobWithExistingName(this, NAME, TestUtils.JobType.MultiConfigurationProject);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), errorMessageName);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProjectWithEqualName")
    public void testCreateProjectWithSpaceInsteadName() {

        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.MultiConfigurationProject);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testCreateProjectWithSpaceInsteadName")
    public void testCheckExceptionOfNameToMultiConfiguration() {
        String exceptionMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .getItemNameRequiredMessage();

        Assert.assertEquals(exceptionMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test(dependsOnMethods = "testCheckExceptionOfNameToMultiConfiguration")
    public void testRenameFromDropDownMenu() {
        String newNameProject = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getJobName(NEW_NAME);

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
        NewJobPage newJobPage = TestUtils.createFolderUsingInvalidData
                (this, unsafeSymbol + "MyProject", TestUtils.JobType.MultiConfigurationProject);

        Assert.assertFalse(newJobPage.isOkButtonEnabled(), "error OK button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + unsafeSymbol + "’" + " is an unsafe character");
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

    @Ignore
    @Test
    public void testCheckGeneralParametersDisplayedAndClickable() {
        MultiConfigurationProjectConfigPage parameter = new MainPage(getDriver())
                .clickConfigureDropDown(NAME, new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())));

        boolean checkboxesVisibleClickable = true;
        for (int i = 4; i <= 8; i++) {
            WebElement checkbox = parameter.getCheckboxById(i);
            if (!checkbox.isDisplayed() || !checkbox.isEnabled()) {
                checkboxesVisibleClickable = false;
                break;
            }
        }
        Assert.assertTrue(checkboxesVisibleClickable);
    }

    @Test(dependsOnMethods = "testEnabled")
    public void testBuildNowDropDownMenu() {
        Assert.assertEquals(new MainPage(getDriver()).getJobBuildStatusByWeatherIcon(NAME), "Not built");

        String jobBuildStatus = new MainPage(getDriver())
                .clickJobDropdownMenuBuildNow(NAME)
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .getJobBuildStatus();

        Assert.assertEquals(jobBuildStatus, "Success");
    }

    @Test(dependsOnMethods = "testBuildNowDropDownMenu")
    public void testAddDescription() {

        String getDescription = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving(DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(getDescription, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testDeleteProjectFromDropDownMenu() {
        List<String> deleteProject = new MainPage(getDriver())
                .dropDownMenuClickDelete(NAME)
                .acceptAlert()
                .getJobList();

        Assert.assertEquals(deleteProject.size(), 0);
    }
    @Test
    public void testCreateFromCreateAJob() {

        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.jobIsDisplayed(NAME), "error was not show name project");
    }

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testConfigureOldBuildForProject() {
        final int displayedDaysToKeepBuilds = 5;
        final int displayedMaxNumOfBuildsToKeep = 7;

        MultiConfigurationProjectConfigPage multiConfigurationProjectConfigPage = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
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

    @Test(dependsOnMethods = "testConfigureOldBuildForProject")
    public void testAddingAProjectOnGithubToTheMultiConfigurationProject() {
        final String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        final String expectedNameRepo = "Sign in";

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

    @Test(dependsOnMethods = "testAddingAProjectOnGithubToTheMultiConfigurationProject")
    public void testProjectPageDelete() {
        MainPage deletedProjPage = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDeleteAndAlert();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");
        Assert.assertEquals(deletedProjPage.getWelcomeText(), "Welcome to Jenkins!");
    }
}

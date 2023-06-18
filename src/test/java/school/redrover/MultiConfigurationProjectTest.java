package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.Jobs.MultiConfigurationProjectPage;
import school.redrover.model.JobsConfig.MultiConfigurationProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MultiConfigurationProjectTest extends BaseTest {

    private static final String NAME = "MULTI_CONFIGURATION_NAME";
    private static final String NEW_NAME = "MULTI_CONFIGURATION_NEW_NAME";

    @Test
    public void testCreateProject() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, false);
        Assert.assertEquals(new MultiConfigurationProjectPage(getDriver()).getProjectName().substring(8, 32), NAME);
        new MainPage(getDriver())
                .getHeader()
                .clickLogo();
        Assert.assertEquals(new MainPage(getDriver()).getJobName(), NAME);
    }
    @Test(dependsOnMethods = "testCreateProject")
    public void testCreateProjectWithEqualName() {
        final String ERROR_MESSAGE_EQUAL_NAME = "A job already exists with the name " + "‘" + NAME + "’";

        String error = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .getErrorPage()
                .getErrorMessage();

        Assert.assertEquals(error, ERROR_MESSAGE_EQUAL_NAME);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProjectOnProjectPage")
    public void testRenameFromDropDownMenu() {
        String NewNameProject = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getJobName();

        Assert.assertEquals(NewNameProject, NEW_NAME);
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
    public void testRename() {
        String newName = new MainPage(getDriver())
                .clickJobName(NEW_NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickRename()
                .enterNewName(NAME)
                .clickRenameButton()
                .getProjectName();

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

    @Test()
    public void testMultiConfigurationProjectConfigurePageDisabled() {
        String configPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("My Multi configuration project")
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .clickSaveButton()
                .clickConfigure()
                .switchCheckboxDisable()
                .getTextDisable();

        Assert.assertEquals(configPage, "Disabled");
    }

    @Ignore
    @Test(dependsOnMethods = "testMultiConfigurationProjectConfigurePageDisabled")
    public void testMultiConfigurationProjectConfigurePageEnable() {
        String configPage = new MainPage(getDriver())
                .clickJobName("My Multi configuration project", new MultiConfigurationProjectPage(getDriver()))
                .clickConfigure()
                .switchCheckboxEnabled()
                .getTextEnabled();

        Assert.assertEquals(configPage, "Enabled");
    }

    @Test(dependsOnMethods = "testDisable")
    public void testEnabledMultiConfigurationProject() {
        String disableButtonText = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickEnable()
                .getDisableButtonText();

        Assert.assertEquals(disableButtonText, "Disable Project");
    }

    @Test(dependsOnMethods = "testCreateProjectWithEqualName")
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
    public void testAddDescription() {
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
    public void testBuildNowDropDownMenuProject() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.MultiConfigurationProject, true);

        Assert.assertEquals(new MainPage(getDriver()).getJobBuildStatus(NAME), "Not built");

        MultiConfigurationProjectPage multiConfigurationProjectPage = new MainPage(getDriver())
                .clickJobDropdownMenuBuildNow(NAME)
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()));

        Assert.assertEquals(multiConfigurationProjectPage.getJobBuildStatus(NAME), "Success");
    }

    @Test
    public void testCheckExceptionOfNameToMultiConfiguration() {
        String exceptionMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .getItemNameRequiredMessage();

        Assert.assertEquals(exceptionMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testDisableEnableProject() {
        TestUtils.createJob(this, "DisableTestName", TestUtils.JobType.MultiConfigurationProject, false);

        String checkStatusIsDisabled = new MultiConfigurationProjectPage(getDriver())
                .clickDisable()
                .getDisabledMessageText();
        Assert.assertTrue(checkStatusIsDisabled.contains("This project is currently disabled"));

        boolean checkStatusIsEnabled = new MultiConfigurationProjectPage(getDriver())
                .clickEnable()
                .isDisableButtonDisplayed();
        Assert.assertTrue(checkStatusIsEnabled);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testCheckDisableIconOnDashboard() {
        String statusIcon = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDisable()
                .getBreadcrumb()
                .clickDashboardButton()
                .getJobBuildStatusIcon(NAME);

        Assert.assertEquals(statusIcon, "Disabled");
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
    public void testAddDescriptionInProject() {
        final String textDescription = "Text Description Test";

        String getDescription = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving(textDescription)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(getDescription, textDescription);
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

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testBuildNowOptionNotPresentInDisabledProject() {
        List<String> dropDownMenuItems = new MainPage(getDriver())
                .clickJobName(NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDisable()
                .getHeader()
                .clickLogo()
                .getListOfProjectMenuItems(NAME);

        Assert.assertFalse(dropDownMenuItems.contains("Build Now"), "'Build Now' option is present in drop-down menu");
    }

    @Test
    public void testAddingAProjectOnGithubToTheProject() {
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

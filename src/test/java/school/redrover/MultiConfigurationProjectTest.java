package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.util.List;

public class MultiConfigurationProjectTest extends BaseTest {

    private static final String MULTI_CONFIGURATION_NAME = "MULTI_CONFIGURATION_NAME";
    private static final String MULTI_CONFIGURATION_NEW_NAME = "MULTI_CONFIGURATION_NEW_NAME";

    @Test
    public void testCreateMultiConfiguration() {
        final String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName().getText();

        Assert.assertEquals(projectName, MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testCreate() {

        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testCreateMultiConfigurationProject() {
        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testCreateMultiConfigurationProjectOnProjectPage() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getName();

        Assert.assertEquals(projectName.substring(8, 32), MULTI_CONFIGURATION_NAME);
    }
    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testCreateMultiConfigurationProjectWithEqualName() {
        final String ERROR_MESSAGE_EQUAL_NAME = "A job already exists with the name " + "‘" + MULTI_CONFIGURATION_NAME + "’";

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk();

        String error = new ErrorNodePage(getDriver())
                .getErrorEqualName();

        Assert.assertEquals(error, ERROR_MESSAGE_EQUAL_NAME);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testRenameFromDropDownMenu() {
        String NewNameProject = new MainPage(getDriver())
                .dropDownMenuClickRename(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getProjectName().getText();

        Assert.assertEquals(NewNameProject, MULTI_CONFIGURATION_NEW_NAME);
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

    @Test
    public void testDisabledMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);
        MultiConfigurationProjectPage disabled = new MultiConfigurationProjectPage(getDriver())
                .clickDisable();


        Assert.assertEquals(getDriver().findElement(By.cssSelector("form#enable-project"))
                .getText().trim().substring(0, 34), "This project is currently disabled");
    }

    @Test
    public void testDisableMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        String enable = new MultiConfigurationProjectPage(getDriver())
                .clickDisable()
                .getEnableButtonText();

        Assert.assertEquals(enable, "Enable");
    }

    @Test()
    public void testMultiConfigurationProjectConfigurePageDisabled() {
        String configPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("My Multi configuration project")
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .clickConfigure()
                .switchCheckboxDisable()
                .getTextDisable()
                .getText();

        Assert.assertEquals(configPage, "Disabled");
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
    }

    @Test(dependsOnMethods = "testMultiConfigurationProjectConfigurePageDisabled")
    public void testMultiConfigurationProjectConfigurePageEnable() {
        String configPage = new MainPage(getDriver())
                .clickJobMultiConfigurationProject("My Multi configuration project")
                .clickConfigure()
                .switchCheckboxEnabled()
                .getTextEnabled().getText();

        Assert.assertEquals(configPage, "Enabled");
    }

    @Test(dependsOnMethods = "testDisabledMultiConfigurationProject")
    public void testEnabledMultiConfigurationProject() {
        String disableButtonText = new MainPage(getDriver())
                .clickJobMultiConfigurationProject(MULTI_CONFIGURATION_NAME)
                .clickEnable()
                .getDisableButtonText();

        Assert.assertEquals(disableButtonText, "Disable Project");
    }

    @Ignore
    @Test(dependsOnMethods = "testDisableMultiConfigurationProject")
    public void testMultiConfigurationProjectDisabled() {
        String enable = new MainPage(getDriver())
                .clickJobName("MyProject", new MultiConfigurationProjectPage(getDriver()))
                .clickEnable()
                .getDisableButtonText();

        Assert.assertEquals(enable, "Disable Project");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testRenameFromDashboard() {
        String renamedProject = new MainPage(getDriver())
                .dropDownMenuClickRename(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getProjectName()
                .getText();

        Assert.assertEquals(renamedProject, MULTI_CONFIGURATION_NEW_NAME);

    }

    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testJobDropdownDelete() {
        MainPage deletedProjPage = new MainPage((getDriver()))
                .dropDownMenuClickDelete(MULTI_CONFIGURATION_NEW_NAME);

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getNoJobsMainPageHeader().getText(), "Welcome to Jenkins!");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testProjectPageDelete() {
        MainPage deletedProjPage = new MainPage(getDriver())
                .clickJobMultiConfigurationProject(MULTI_CONFIGURATION_NAME)
                .clickDelete();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getNoJobsMainPageHeader().getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testCheckGeneralParametersDisplayedAndClickable() {
        MultiConfigurationProjectConfigPage config = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk();

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
    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testMultiConfigurationProjectAddDescription1() {
        final String text = "text";

        String addDescriptionText = new MainPage(getDriver())
                .clickJobName(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .changeDescriptionWithoutSaving(text)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(addDescriptionText, text);
    }

    //    Test does not pass at my computer because a Wait is needed (Actual result was "In progress")
    @Ignore
    @Test
    public void testBuildNowDropDownMenuMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        Assert.assertEquals(new MainPage(getDriver()).getJobBuildStatus(MULTI_CONFIGURATION_NAME), "Not built");

        MultiConfigurationProjectPage multiConfigurationProjectPage = new MainPage(getDriver())
                .clickJobDropdownMenuBuildNow(MULTI_CONFIGURATION_NAME)
                .clickJobMultiConfigurationProject(MULTI_CONFIGURATION_NAME);

        Assert.assertEquals(multiConfigurationProjectPage.getJobBuildStatus(MULTI_CONFIGURATION_NAME), "Success");
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
                .selectMultiConfigurationProject()
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testCreateProjectWithDescription() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);
        String nameDescription = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving("Description")
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(nameDescription, "Description");
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
                .selectMultiConfigurationProject()
                .getItemInvalidMessage();

        Assert.assertEquals(messageUnderInputField, expectedResult);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProjectOnProjectPage")
    public void testRenameMultiConfigurationProject() {
        String newName = new MainPage(getDriver())
                .clickJobMultiConfigurationProject(MULTI_CONFIGURATION_NAME)
                .clickRename()
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .clickRenameButton()
                .getName();

        Assert.assertEquals(newName, "Project " + MULTI_CONFIGURATION_NEW_NAME);
    }

    @Test
    public void testCheckExceptionOfNameToMultiConfiguration() {
        String exceptionMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectMultiConfigurationProject()
                .getItemNameRequiredMessage();

        Assert.assertEquals(exceptionMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testDisableEnableProject() {
        TestUtils.createMultiConfigurationProject(this, "DisableTestName", false);

        String checkStatusIsDisabled = new MultiConfigurationProjectPage(getDriver())
                .clickDisable()
                .getDisabledMessageText();
        Assert.assertTrue(checkStatusIsDisabled.contains("This project is currently disabled"));

        boolean checkStatusIsEnabled = new MultiConfigurationProjectPage(getDriver())
                .clickEnable()
                .isDisableButtonDisplayed();
        Assert.assertTrue(checkStatusIsEnabled);
    }

    @Test
    public void testCheckDisableIconOnDashboard() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        getDriver().findElement(By.xpath("//*[@id='disable-project']/button")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();

        WebElement iconDisabled = getDriver().findElement(By.xpath("//*[@tooltip='Disabled']"));

        Assert.assertTrue(iconDisabled.isDisplayed());
    }

    @Test
    public void testDisableProjectFromConfigurationPage() {
        final String disableResult = "This project is currently disabled";

        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);
        String disableMessage = new MultiConfigurationProjectPage(getDriver())
                .clickDisable()
                .getDisabledMessageText();

        Assert.assertTrue(disableMessage.contains(disableResult), "Not found such message");
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
    public void testDeleteProjectFromDropDownMenu() {
        List<String> deleteProject = new MainPage(getDriver())
                .dropDownMenuClickDelete(MULTI_CONFIGURATION_NEW_NAME)
                .acceptAlert()
                .getJobList();

        Assert.assertEquals(deleteProject.size(), 0);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionInMultiConfigurationProject() {
        final String textDescription = "Text Description Test";

        String getDescription = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving(textDescription)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(getDescription, textDescription);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionToMultiConfigurationProject() {
        final String descriptionText = "Web-application project";

        String description = new MultiConfigurationProjectPage(getDriver())
                .changeDescriptionWithoutSaving(descriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(description, descriptionText);
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
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        String errorNotification = new MainPage(getDriver())
                .dropDownMenuClickRename(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(MULTI_CONFIGURATION_NAME + unsafeSymbol)
                .getErrorMessage();

        Assert.assertEquals(errorNotification, String.format("‘%s’ is an unsafe character", unsafeSymbol));

        CreateItemErrorPage createItemErrorPage = new RenamePage<>(new MultiConfigurationProjectPage(getDriver()))
                .clickRenameButtonAndGoError();

        Assert.assertEquals(createItemErrorPage.getHeaderText(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), String.format("‘%s’ is an unsafe character", htmlUnsafeSymbol));
    }

    @Test
    public void testCreateMultiConfigurationProjectWithDescription() {
        final String multiConfigurationProjectName = "New project";
        final String description = "Description text";

        String descriptionOnProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfigurationProjectName)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .changeDescriptionWithoutSaving(description)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(descriptionOnProjectPage, description);
    }

    @Test
    public void testConfigureOldBuildForMultiConfigurationProject() {
        final String multiConfProjectName = "New project";
        final int displayedDaysToKeepBuilds = 5;
        final int displayedMaxNumOfBuildsToKeep = 7;

        MultiConfigurationProjectConfigPage multiConfigurationProjectConfigPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfProjectName)
                .selectMultiConfigurationProjectAndOk()
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
    public void testCreateMultiConfigurationProjectWithSpaceInsteadName() {
        final String expectedResult = "Error";

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(" ")
                .selectMultiConfigurationProjectAndOk();

        String errorMessage = new ErrorNodePage(getDriver()).getErrorMessage();

        Assert.assertEquals(errorMessage, expectedResult);
    }

    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testBuildNowOptionNotPresentInDisabledProject() {
        List<String> dropDownMenuItems = new MainPage(getDriver())
                .clickJobName(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickDisable()
                .getHeader()
                .clickLogo()
                .openJobDropDownMenu(MULTI_CONFIGURATION_NAME)
                .getListOfProjectMenuItems(MULTI_CONFIGURATION_NAME);

        Assert.assertFalse(dropDownMenuItems.contains("Build Now"), "'Build Now' option is present in drop-down menu");
    }

    @Test
    public void testAddingAProjectOnGithubToTheMultiConfigurationProject() {
        String nameProject = "Engineer";
        String gitHubUrl = "https://github.com/ArtyomDulya/TestRepo";
        String nameRepo = "Sign in";

        TestUtils.createMultiConfigurationProject(this, nameProject, true);
        new MainPage(getDriver())
                .clickJobName(nameProject, new MultiConfigurationProjectPage(getDriver()))
                .clickConfigure()
                .clickGitHubProjectCheckbox()
                .inputTextTheInputAreaProjectUrlInGitHubProject(gitHubUrl)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .openJobDropDownMenu(nameProject)
                .selectFromJobDropdownMenuTheGitHub();

        GitHubPage gitHubPage = new GitHubPage(getDriver());
        Assert.assertEquals(gitHubPage.githubSignInText(), nameRepo);
    }


}

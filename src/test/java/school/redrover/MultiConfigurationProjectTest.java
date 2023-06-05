package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import school.redrover.model.*;

public class MultiConfigurationProjectTest extends BaseTest {
    private static final String MULTI_CONFIGURATION_NAME = "MULTI_CONFIGURATION_NAME";
    private static final String MULTI_CONFIGURATION_NEW_NAME = "MULTI_CONFIGURATION_NEW_NAME";

    @Test
    public void testCreateMultiConfiguration() {
        MainPage mainPage = new MainPage(getDriver());
        final String projectName = mainPage.clickNewItem()
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

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testRenameFromDropDownMenu() {
        // TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        String NewNameProject = new MainPage(getDriver())
                .dropDownMenuClickRename(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
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
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem();

        String invalidMessage = new NewJobPage(getDriver())
                .enterItemName(unsafeSymbol + "MyProject")
                .getItemInvalidMessage();

        Assert.assertEquals(invalidMessage, "» ‘" + unsafeSymbol + "’" + " is an unsafe character");
    }

    @Test
    public void testDisabledMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);
        MultiConfigurationProjectPage disabled = new MultiConfigurationProjectPage(getDriver())
                .getDisableClick();


        Assert.assertEquals(getDriver().findElement(By.cssSelector("form#enable-project"))
                .getText().trim().substring(0, 34), "This project is currently disabled");
    }

    @Test
    public void testDisableMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        String enable = new MultiConfigurationProjectPage(getDriver())
                .getDisableClick()
                .getEnableSwitch()
                .getText();

        Assert.assertEquals(enable, "Enable");
    }

    @Test()
    public void testMultiConfigurationProjectConfigurePageDisabled() {
        String configPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("My Multi configuration project")
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getConfigPage()
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
                .getConfigPage()
                .switchCheckboxEnabled()
                .getTextEnabled().getText();

        Assert.assertEquals(configPage, "Enabled");
    }

    @Test(dependsOnMethods = "testDisabledMultiConfigurationProject")
    public void testEnabledMultiConfigurationProject() {
        MultiConfigurationProjectPage enabledProjPage = new MainPage(getDriver())
                .clickJobMultiConfigurationProject(MULTI_CONFIGURATION_NAME)
                .getEnableClick();

        Assert.assertEquals(enabledProjPage.getDisableSwitch().getText(), "Disable Project");
    }

    @Ignore
    @Test(dependsOnMethods = {"testDisableMultiConfigurationProject"})
    public void testEnableMultiConfigurationProject() {
        new MainPage(getDriver())
                .clickMultiConfigurationProjectName("MyProject");

        WebElement disableProject = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick()
                .getDisableSwitch();

        Assert.assertTrue(disableProject.isDisplayed());
    }

    @Ignore
    @Test(dependsOnMethods = "testDisableMultiConfigurationProject")
    public void testMultiConfigurationProjectDisabled() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.clickMultiConfigurationProjectName("MyProject");

        WebElement enable = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick().getDisableElem();

        Assert.assertEquals(enable.getText(), "Disable Project");
    }

    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testRenameFromDashboard() {

        String renamedProject = new MainPage(getDriver())
                .dropDownMenuClickRename(MULTI_CONFIGURATION_NAME, new MultiConfigurationProjectPage(getDriver()))
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
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
                .deleteProject();

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

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testMultiConfigurationProjectAddDescription1() {
        final String text = "text";
        MainPage mainPage = new MainPage(getDriver());
        mainPage.getProjectName()
                .click();
        WebElement addDescriptionText = new MultiConfigurationProjectPage(getDriver())
                .getAddDescription(text)
                .getSaveButton()
                .getInputAdd();
        Assert.assertEquals(addDescriptionText.getText(), text);
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
                .getAddDescription("Description")
                .getSaveButton()
                .getInputAdd().getText();

        Assert.assertEquals(nameDescription, "Description");
    }

    @DataProvider(name = "unsafe-character")
    public Object[][] putUnsafeCharacterInputField() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}};
    }

    @Test(dataProvider = "unsafe-character")
    public void testCreateMultiConfigurationProjectWithSpecialSymbols(String unsafeCharacter) {
        final String expectedResult = "is an unsafe character";

        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();

        getDriver().findElement(By.name("name")).sendKeys(unsafeCharacter);

        WebElement errorMessage = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals((errorMessage.getText()).substring(6, 28), expectedResult);

        getDriver().findElement(By.name("name")).clear();
    }

    @Test
    public void testRenameMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        String newName = new MultiConfigurationProjectPage(getDriver())
                .clickRename()
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
                .getMultiProjectName();

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
                .getDisableClick()
                .getDisableText();
        Assert.assertTrue(checkStatusIsDisabled.contains("This project is currently disabled"));

        boolean checkStatusIsEnabled = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick()
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
                .getDisableClick()
                .getDisableText();

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
        MultiConfigurationProjectPage multiConfPage =
                new MultiConfigurationProjectPage(getDriver())
                        .getAddDescription(textDescription)
                        .getSaveButton();

        String getDescription = multiConfPage
                .getInputAdd()
                .getText();
        Assert.assertEquals(getDescription, textDescription);
    }

    @Test
    public void addDescriptionInMultiConfigurationProjectTest() {
        final String textDescription = "Text Description Test";

        TestUtils.createMultiConfigurationProject(this, "Test1", false);
        String actualDescription = new MultiConfigurationProjectPage(getDriver())
                .getAddDescription(textDescription)
                .getSaveButton()
                .getInputAdd().getText();

        Assert.assertEquals(actualDescription, textDescription);
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionToMultiConfigurationProject() {
        final String descriptionText = "Web-application project";
        String description = new MainPage(getDriver())
                .clickMultiConfigurationProjectName(MULTI_CONFIGURATION_NAME)
                .getAddDescription(descriptionText)
                .getSaveButton()
                .getInputAdd()
                .getText();

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
                .clickRenameButton();

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
                .getAddDescription(description)
                .getSaveButton()
                .getInputAdd().getText();

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
                .clickConfigureSideMenu()
                .clickOldBuildCheckBox()
                .enterDaysToKeepBuilds(displayedDaysToKeepBuilds)
                .enterMaxNumOfBuildsToKeep(displayedMaxNumOfBuildsToKeep)
                .clickSaveButton()
                .clickConfigureSideMenu();

        Assert.assertEquals(Integer.parseInt(
                multiConfigurationProjectConfigPage.getDaysToKeepBuilds("value")), displayedDaysToKeepBuilds);
        Assert.assertEquals(Integer.parseInt(
                multiConfigurationProjectConfigPage.getMaxNumOfBuildsToKeep("value")), displayedMaxNumOfBuildsToKeep);
    }

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
}

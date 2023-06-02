package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.model.base.BasePage;
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

    private static final String DESCRIPTION = "Description";
    private static final By DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final By INPUT_FIELD = By.name("name");
    private static final By INPUT_NEW_ITEM_FIELD = By.xpath("//input[@name='newName']");
    private static final String MULTI_CONFIGURATION_NAME = "MULTI_CONFIGURATION_NAME";
    private static final String MULTI_CONFIGURATION_NEW_NAME = "MULTI_CONFIGURATION_NEW_NAME";
    private static final By SAVE_BUTTON = By.name("Submit");

    private void createMultiConfigurationProject(String name, Boolean goToMainPage) {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']"))).sendKeys(name);
        getDriver().findElement(By.xpath("//label/span[contains(text(), 'Multi-configuration proj')]")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        if (goToMainPage) {
            getDriver().findElement(By.linkText("Dashboard")).click();
        }
    }

    @Test
    public void testCreateMultiConfiguration() {
        MainPage mainPage = new MainPage(getDriver());
        final String projectName = mainPage.clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
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
                .saveConfigurePageAndGoToProjectPage()
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
                .saveConfigurePageAndGoToProjectPage()
                .getHeader()
                .clickLogo()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), MULTI_CONFIGURATION_NAME);
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
                .saveConfigurePageAndGoToProjectPage()
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
                .getMultiConfigPage()
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
                .clickJobWebElement("MyProject");

        WebElement disableProject = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick()
                .getDisableSwitch();

        Assert.assertTrue(disableProject.isDisplayed());
    }

    @Ignore
    @Test(dependsOnMethods = "testDisableMultiConfigurationProject")
    public void testMultiConfigurationProjectDisabled() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement enable = new MultiConfigurationProjectPage(getDriver())
                .getEnableClick().getDisableElem();

        Assert.assertEquals(enable.getText(), "Disable Project");
    }


    @Test
    public void testRenameFromDashboard() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

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

        MainPage mainPage = new MainPage(getDriver())
                .clickJobDropDownMenu(MULTI_CONFIGURATION_NAME);

        Assert.assertEquals(mainPage.getJobBuildStatus(MULTI_CONFIGURATION_NAME), "Not built");

        MultiConfigurationProjectPage multiConfigurationProjectPage = new MainPage(getDriver())
                .clickJobDropdownMenuBuildNow()
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
                .getAddDescription(DESCRIPTION)
                .getSaveButton()
                .getInputAdd().getText();

        Assert.assertEquals(nameDescription, DESCRIPTION);
    }

    @DataProvider(name = "unsafe-character")
    public Object[][] putUnsafeCharacterInputField() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}};
    }

    @Test(dataProvider = "unsafe-character")
    public void testCreateMultiConfigurationProjectWithSpecialSymbols(String unsafeCharacter) {
        final String expectedResult = "is an unsafe character";

        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();

        getDriver().findElement(INPUT_FIELD).sendKeys(unsafeCharacter);

        WebElement errorMessage = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals((errorMessage.getText()).substring(6, 28), expectedResult);

        getDriver().findElement(By.name("name")).clear();
    }

    @Test
    public void testRenameMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        WebElement newName = new JobPage(getDriver())
                .clickRename()
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
                .getNameProject();

        Assert.assertEquals(newName.getText(), "Project " + MULTI_CONFIGURATION_NEW_NAME);
    }

    @Ignore
    @Test
    public void testCheckExceptionOfNameToMultiConfiguration() {
        String exceptionMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectMultiConfigurationProject()
                .clickButtonOk()
                .getItemNameRequiredMessage();

        Assert.assertEquals(exceptionMessage,"» This field cannot be empty, please enter a valid name");
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
        getDriver().findElement(DASHBOARD_BUTTON).click();

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

    @Test
    public void testRenameFromDropDownMenu() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By
                        .xpath("//td//a[@class='jenkins-table__link model-link inside']")))
                .pause(1000)
                .perform();

        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a//button")));
        chevron.sendKeys(Keys.RETURN);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//*[text()='Rename']")))
                .click()
                .perform();

        getDriver().findElement(By.xpath("//div//input[@checkdependson='newName']"))
                .sendKeys(MULTI_CONFIGURATION_NEW_NAME);
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']//button")).click();

        getDriver().findElement(DASHBOARD_BUTTON).click();

        WebElement newNameMultiCofigurationProject = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(newNameMultiCofigurationProject.getText(),
                MULTI_CONFIGURATION_NAME + MULTI_CONFIGURATION_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
    public void testDeleteProjectFromDropDownMenu() {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By
                        .xpath("//td//a[@class='jenkins-table__link model-link inside']")))
                .pause(1000)
                .perform();

        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//td//a//button")));
        chevron.sendKeys(Keys.RETURN);

        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//*[text()='Delete Multi-configuration project']")))
                .click()
                .perform();

        getDriver().switchTo().alert().accept();

        List<WebElement> projects = getDriver().findElements(By
                .xpath("//a[@href='job/" + MULTI_CONFIGURATION_NAME + MULTI_CONFIGURATION_NEW_NAME + "/']"));

        Assert.assertEquals(projects.size(), 0);
    }

    @Test (dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionInMultiConfigurationProject(){
        final String textDescription = "Text Description Test";
        MultiConfigurationProjectPage multiConfPage =
                new MultiConfigurationProjectPage(getDriver())
                        .getAddDescription(textDescription)
                        .getSaveButton();

        String getDescription = multiConfPage
                .getInputAdd()
                .getText();
        Assert.assertEquals(getDescription,textDescription);

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

    @Test
    public void testAddDescriptionToMultiConfigurationProject() {
        final String expectedDescription = "Web-application project";

        WebElement selectNewItem = getDriver().findElement(By.xpath("//*[@id='tasks']//span/a"));
        selectNewItem.click();

        WebElement setNewItemName = getDriver().findElement(INPUT_FIELD);
        setNewItemName.sendKeys("Project_MultiConfigJob");

        WebElement selectMultiConfigProject = getWait5().
                until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//span[text()='Multi-configuration project']")));
        selectMultiConfigProject.click();

        WebElement okButton = getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[@id='ok-button']")));
        okButton.click();

        WebElement scrollBySubmitButton = getDriver().findElement(SAVE_BUTTON);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollBySubmitButton);
        scrollBySubmitButton.click();

        WebElement addDescription = getDriver().findElement(By.xpath("//a[@href='editDescription']"));
        addDescription.click();

        WebElement textAreaDescription = getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//textarea[@name='description']")));
        textAreaDescription.clear();
        textAreaDescription.sendKeys("Web-application project");

        WebElement saveButton = getDriver().findElement(SAVE_BUTTON);
        saveButton.click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(actualDescription.getText(), expectedDescription);
    }

    @DataProvider(name = "unsafeCharacters")
    public static Object[][] unsafeCharacterArray() {
        return new Object[][]{
                {'!', "!"}, {'@', "@"}, {'#', "#"}, {'$', "$"}, {'%', "%"}, {'^', "^"}, {'&', "&amp;"},
                {'*', "*"}, {'[', "["}, {']', "]"}, {'\\', "\\"}, {'|', "|"}, {';', ";"}, {':', ":"},
                {'<', "&lt;"}, {'>', "&gt;"}, {'/', "/"}, {'?', "?"}};
    }

    @Ignore
    @Test(dataProvider = "unsafeCharacters")
    public void verifyProjectNameRenameWithUnsafeSymbolsTest(char unsafeSymbol, String htmlUnsafeSymbol) {

        createMultiConfigurationProject(MULTI_CONFIGURATION_NAME, true);

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
        final String multiConfigurationProjectName= "New project";
        final String description ="Description text";

        String descriptionOnProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfigurationProjectName)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .getAddDescription(description)
                .getSaveButton()
                .getInputAdd().getText();

        Assert.assertEquals(descriptionOnProjectPage, description);
    }

    @Ignore
    @Test
    public void testConfigureOldBuildForMultiConfigurationProject() {
        final String multiConfProjectName = "New project";
        final int displayedDaysToKeepBuilds = 5;
        final int displayedMaxNumOfBuildsToKeep = 7;

        MultiConfigurationProjectConfigPage multiConfigurationProjectConfigPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfProjectName)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .clickConfigureSideMenu()
                .clickOldBuildCheckBox()
                .enterDaysToKeepBuilds(displayedDaysToKeepBuilds)
                .enterMaxNumOfBuildsToKeep(displayedMaxNumOfBuildsToKeep)
                .saveConfigurePageAndGoToProjectPage()
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

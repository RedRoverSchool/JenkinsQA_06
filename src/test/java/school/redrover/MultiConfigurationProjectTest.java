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

import org.apache.commons.lang3.RandomStringUtils;
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
    private static final By NEW_ITEM_BUTTON = By.xpath("//*[@id='tasks']//span/a");
    private static final By INPUT_FIELD = By.name("name");
    private static final By DISABLE_BUTTON_CONFIG_PAGE = By.xpath("//*[@id='disable-project']/button");
    private static final By INPUT_NEW_ITEM_FIELD = By.xpath("//input[@name='newName']");
    private static final String MULTI_CONFIGURATION_NAME = RandomStringUtils.randomAlphanumeric(5);
    private static final String MULTI_CONFIGURATION_NEW_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final By SAVE_BUTTON = By.name("Submit");

    @Test
    public void testCreateMultiConfiguration() {
        MainPage mainPage = new MainPage(getDriver());
        final String projectName = mainPage.clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .navigateToHomePageUsingJenkinsIcon()
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
                .navigateToHomePageUsingJenkinsIcon()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testCreateMultiConfigurationProject()  {

        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .navigateToHomePageUsingJenkinsIcon()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), MULTI_CONFIGURATION_NAME);
    }

    @Ignore
    @Test
    public void testCreateMultiConfigurationProjectWithDescriptionTest1() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        MultiConfigurationProjectPage multiConfigProjectWithDescription = new MultiConfigurationProjectPage(getDriver())
                .getAddDescription(DESCRIPTION)
                .getSaveButton();

        Assert.assertEquals(multiConfigProjectWithDescription.getInputAdd().getText(),DESCRIPTION);
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
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .toggleDisable()
                .saveConfigurePageAndGoToProjectPage();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("form#enable-project"))
                .getText().trim().substring(0, 34), "This project is currently disabled");
    }

    @Test
    public void testDisableMultiConfigurationProject() {
        TestUtils.createMultiConfigurationProject(this, "MyProject", false);

        WebElement enable = new MultiConfigurationProjectPage(getDriver())
                .getDisableClick()
                .getEnableSwitch();

        Assert.assertEquals(enable.getText(),"Enable");
    }

    @Ignore
    @Test (dependsOnMethods = "testCreateMultiConfiguration")
    public void testMultiConfigurationProjectConfigureDisabled() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement configPage = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxDisable()
                .getTextDisable();

        Assert.assertEquals(configPage.getText(),"Disabled");
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
    }

    @Test(dependsOnMethods = "testDisabledMultiConfigurationProject")
    public void testEnabledMultiConfigurationProject() {
        ProjectPage enabledProjPage = new MainPage(getDriver())
                .navigateToProjectPage()
                .enableProject();

        Assert.assertEquals(enabledProjPage.getDisableButton().getText(), "Disable Project");
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
    @Test (dependsOnMethods = "testDisableMultiConfigurationProject")
    public void testMultiConfigurationProjectDisabled() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement enable = new MultiConfigurationProjectPage (getDriver())
                .getEnableClick().getDisableElem();

        Assert.assertEquals(enable.getText(),"Disable Project");
    }

   @Test (dependsOnMethods = "testDisableMultiConfigurationProject")
    public void testMultiConfigurationProjectConfigureEnable() {
        MainPage mainPageName = new MainPage(getDriver());
        mainPageName.getMultiConfigPage();

        WebElement configPage = new MultiConfigurationProjectPage(getDriver())
                .getConfigPage()
                .switchCheckboxEnabled()
                .getTextEnabled();

        Assert.assertEquals(configPage.getText(),"Enabled");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testRenameMultiConfigurationProjectFromDashboard() {

        WebElement newName = new MainPage(getDriver())
                .navigateToProjectPage()
                .clickRename()
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
                .getNameProject();

        Assert.assertEquals(newName.getText(), ("Project " + MULTI_CONFIGURATION_NEW_NAME));
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testJobDropdownDelete() {
        MainPage deletedProjPage = new MainPage((getDriver()))
                .clickJobDropdownMenu(MULTI_CONFIGURATION_NEW_NAME)
                .selectJobDropdownMenuDelete();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getNoJobsMainPageHeader().getText(), "Welcome to Jenkins!");
    }

    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testProjectPageDelete() {
        MainPage deletedProjPage = new MainPage(getDriver())
                .navigateToProjectPage()
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

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']"))).sendKeys(wrongCharacter);

        String errorName = getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='itemname-invalid']"))).getText();

        Assert.assertEquals(errorName, "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.xpath("//button[@id='ok-button']")).isEnabled());

        getDriver().findElement(By.xpath("//*[@id='jenkins-name-icon']")).click();
    }

    @Test
    public void testCreateMultiConfigurationProjectWithDescriptionTest() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//*[@id='description']//textarea")).sendKeys(DESCRIPTION);
        getDriver().findElement(By.name("Submit")).click();

        WebElement nameDescription = getDriver().findElement(By.xpath("//div[@id ='description']//div"));

        Assert.assertEquals(nameDescription.getText(),DESCRIPTION);
    }

    @Test
    public void testCreateMultiConfigurationProjectWithSpaceInsteadName() {
        final String expectedResult = "Error";

        getDriver().findElement(NEW_ITEM_BUTTON).click();

        getDriver().findElement(INPUT_FIELD).sendKeys(" ");
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();

        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();

        WebElement errorMessage  = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);
    }

    @DataProvider(name="unsafe-character")
    public Object [][]putUnsafeCharacterInputField() {
        return new Object[][] {{"!"},{"@"},{"#"},{"$"},{"%"},{"^"},{"&"},{"*"},{"?"}};
    }

    @Test(dataProvider = "unsafe-character")
    public void testCreateMultiConfigurationProjectWithSpecialSymbols(String unsafeCharacter)  {
        final String expectedResult = "is an unsafe character";

        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();

        getDriver().findElement(INPUT_FIELD).sendKeys(unsafeCharacter);

        WebElement errorMessage = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals((errorMessage.getText()).substring(6, 28), expectedResult);

        getDriver().findElement(By.name("name")).clear();
    }

    @Test
    public void testCreateMultiConfigurationProjectWithEqualName() {
        final String ERROR_MESSAGE_EQUAL_NAME = "A job already exists with the name " + "‘" + MULTI_CONFIGURATION_NAME + "’";

        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getDriver().findElement(INPUT_FIELD).sendKeys(MULTI_CONFIGURATION_NAME);
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();
        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();

        WebElement errorMessage  = getDriver().findElement(By.xpath("//*[@id='main-panel']/p"));

        Assert.assertEquals(errorMessage.getText(),ERROR_MESSAGE_EQUAL_NAME);
    }

    @Test
    public void testRenameProject() {

        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, true);

        String link = getDriver().findElement(By
                .xpath("//*[@id='job_" + MULTI_CONFIGURATION_NAME + "']/td[3]/a")).getAttribute("href");
        getDriver().get(link);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='/job/" + MULTI_CONFIGURATION_NAME + "/confirm-rename']"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NEW_ITEM_FIELD)).clear();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NEW_ITEM_FIELD))
                .sendKeys(MULTI_CONFIGURATION_NEW_NAME);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h1[@class='matrix-project-headline page-headline']"))).getText(),
                "Project " + MULTI_CONFIGURATION_NEW_NAME);
    }

    @Test
    public void testCheckExceptionToMultiConfigurationPage() {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement multiconfigButton = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        multiconfigButton.click();
        String exceptionText = getDriver().findElement(By
                .xpath("//div[text() ='» This field cannot be empty, please enter a valid name']")).getText();

        Assert.assertEquals(exceptionText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testDisableEnableProject(){
        TestUtils.createMultiConfigurationProject(this, "DisableTestName", false);
        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        String expectedString = getDriver().findElement(By.id("enable-project")).getText();
        Assert.assertTrue(expectedString.contains("This project is currently disabled"));

        WebElement enableButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        enableButton.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//form[@id='disable-project']/button"))
                .isDisplayed());
    }

    @Test
    public void testCheckDisableIconOnDashboard() {
        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        getDriver().findElement(DISABLE_BUTTON_CONFIG_PAGE).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();

        WebElement iconDisabled = getDriver().findElement(By.xpath("//*[@tooltip='Disabled']"));

        Assert.assertTrue(iconDisabled.isDisplayed());
    }

    @Test
    public void testDisableMultiConfigurationProjectFromConfigurationPage() {
        final String expectedResult = "This project is currently disabled";

        TestUtils.createMultiConfigurationProject(this, MULTI_CONFIGURATION_NAME, false);

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(DISABLE_BUTTON_CONFIG_PAGE).click();

        WebElement disableMessage = getDriver().findElement(By.xpath("//*[@id='enable-project']"));

        Assert.assertEquals(disableMessage.getText().substring(0,34),expectedResult);
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

    @Ignore
    @Test (dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testAddDescriptionInMultiConfigurationProject(){
        final String textDescription = "Text Description Test";

        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='description']")))
                .sendKeys(textDescription);
        getDriver().findElement(By.xpath("//div[@id='description']//button[@name=\"Submit\"]")).click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualDescription.getText(),textDescription);
    }

    @Test
    public void addDescriptionInMultiConfigurationProjectTest(){
        final String textDescription = "Text Description Test";

        getDriver().findElement(By.xpath("(//section[@class='empty-state-section'] )[1]//li")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Test1");
        getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name,'Submit')]"))).click();

        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='description']"))).sendKeys(textDescription);
        getDriver().findElement(By.xpath("//div[@id='description']//button[@name=\"Submit\"]")).click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualDescription.getText(),textDescription);
    }
    @Test
    public void testAddDescriptionToMultiConfigurationProject() {
        final String expectedDescription = "Web-application project";

        WebElement selectNewItem = getDriver().findElement(NEW_ITEM_BUTTON);
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
}

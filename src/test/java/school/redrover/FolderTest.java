package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";

    private void createNewFolder(String name, boolean clickDashboard) {
        new NewJobPage(getDriver())
                .enterItemName(name)
                .selectFolderAndOk();
        if (clickDashboard) {
            new FolderPage(getDriver()).clickDashboard();
        }
    }

    private void createFolderNewItem(String name, boolean clickDashboard) {
        new MainPage(getDriver())
                .clickNewItem();
        createNewFolder(name, clickDashboard);
    }

    private void createFolderCreateAJob(String name, boolean clickDashboard) {
        new MainPage(getDriver())
                .clickCreateAJob();
        createNewFolder(name, clickDashboard);
    }

    private void createFolderDashboard(String name, boolean clickDashboard) {
        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.linkText("Dashboard"))));

        WebElement dashboard = getDriver().findElement(By.linkText("Dashboard"));
        WebElement pointer = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']//button[@class=\"jenkins-menu-dropdown-chevron\"]"));
        new Actions(getDriver())
                .moveToElement(dashboard)
                .perform();
        pointer.sendKeys(Keys.RETURN);

        new Actions (getDriver())
                .click(getDriver().findElement(By.xpath("//ul[@class='first-of-type']//span[text()='New Item']")))
                .perform();

        createNewFolder(name, clickDashboard);
    }

    @Test
    public void testCreateFolderNewItem() {
        createFolderNewItem(NAME, true);

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test
    public void testCreateFolderCreateAJob() {
        createFolderCreateAJob(NAME, true);

        MainPage mainPage = new MainPage(getDriver());
        String actualResult = mainPage.getFolderName().getText();

        WebElement webElement = mainPage.navigateToProjectPage().getNameProject();

        Assert.assertEquals(actualResult, NAME);
        Assert.assertEquals(webElement.getText(), NAME);
    }

    @Test
    public void testCreateFolderDashboard() {
        createFolderDashboard(NAME, true);

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

     @Test
    public void testErrorWhenCreateFolderWithExistingName() {
        String errorMessage = "Error";

        createFolderCreateAJob(NAME, false);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href= '/']")).click();

         createFolderNewItem(NAME, false);

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), errorMessage);
    }

    @DataProvider(name = "invalid-data")
    public Object[][] provideInvalidData() {
        return new Object[][]{{"!"}, {"#"}, {"$"}, {"%"}, {"&"}, {"*"}, {"/"}, {":"},
                {";"}, {"<"}, {">"}, {"?"}, {"@"}, {"["}, {"]"}, {"|"}, {"\\"}, {"^"}};
    }

    @Test(dataProvider = "invalid-data")
    public void testCreateFolderUsingInvalidData(String invalidData) {
        String errorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        WebElement createItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        createItemButton.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.clear();
        fieldInputName.sendKeys(invalidData);

        WebElement resultMessage = getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"))));
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, errorMessage);
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        final String displayName = "NewFolder";
        final String description = "Created new folder";

        TestUtils.createFolder(this, NAME, false);
        FolderPage folderPage = new FolderPage(getDriver());
        folderPage.clickConfigureSideMenu()
                .enterDisplayName(displayName)
                .enterDescription(description)
                .clickSaveButton();

        Assert.assertEquals(folderPage.getFolderDisplayName(), displayName);
        Assert.assertTrue(folderPage.getFolderName().contains("Folder name: " + NAME));
        Assert.assertEquals(folderPage.getFolderDescription(), description);
    }

    @Ignore
    @Test
    public void testAddHealthMetric(){
        TestUtils.createFolder(this, NAME, false);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + NAME + "/configure']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@class='jenkins-button advanced-button advancedButton']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id='yui-gen1-button']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='yuimenuitemlabel']"))).click();

        assertTrue(getDriver().findElement(By.xpath("//div[@name='healthMetrics']")).isDisplayed());

        getDriver().findElement(By.xpath("//button [@name='Submit']")).click();
    }

    @Test
    public void testRename() {
        final String newName = "newTestName";

        createFolderNewItem(NAME, true);
        new MainPage(getDriver())
                .selectRenameJobDropDownMenu(NAME)
                .enterNewName(newName)
                .SubmitNewNameFolder()
                .navigateToMainPageByBreadcrumbs();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(newName).isDisplayed(),
                "error was not show new name folder");
    }

    @Test
    public void testRenameFolderNegative (){
        createFolderCreateAJob(NAME, false);
        new FolderConfigPage(getDriver()).clickSaveButton().rename().setNewName(NAME).clickRenameButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(By.cssSelector("div[id='main-panel'] p")).getText(), "The new name is the same as the current name.");
    }

    @Test
    public void testDeleteFolder() {
        createFolderDashboard(NAME, true);
        new MainPage(getDriver())
                .selectDeleteFolderDropDownMenu(NAME)
                .clickYes();

        Assert.assertTrue(new MainPage(getDriver()).getWelcomeWebElement().isDisplayed(),
                "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testCancelDeleting() {
        createFolderCreateAJob(NAME, true);
        new MainPage(getDriver())
                .clickFolderName(NAME)
                .delete()
                .clickDashboard();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
    }

    @Test
    public void testCreateNewViewInFolder() {
        final String viewName = "Test View";

        createFolderCreateAJob(NAME, true);
        new MainPage(getDriver())
                .clickFolderName(NAME)
                .clickNewView()
                .enterViewName(viewName)
                .selectMyViewAndClickCreate()
                .clickAll();

        WebElement newView = getDriver().findElement(By.linkText(viewName));
        Assert.assertTrue(newView.isDisplayed(), "error was not shown created view");
    }

    @Test
    public void testMoveFreestyleProjectToFolder() {

        String projectName = "Project_1";

        TestUtils.createFolder(this, NAME, true);
        TestUtils.createFreestyleProject(this, projectName, true);

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']",projectName)))).click();
        getDriver().findElement(By.xpath(String.format("//a[@href='/job/%s/move']", projectName))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select setting-input']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//option[@value='/%s']",NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getDriver().findElement(By.xpath("//ol/li/a[@href='/']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']", NAME)))).click();

        WebElement movedProject = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']",projectName))));

        Assert.assertEquals(movedProject.getText(),projectName);
    }

    @Test
    public void testMoveFolderToFolder(){
        final String folderOne = RandomStringUtils.randomAlphanumeric(9);
        final String folderTwo = folderOne + "Two";

        TestUtils.createFolder(this, folderOne, true);
        TestUtils.createFolder(this, folderTwo, true);

        WebElement folderName = new MainPage(getDriver())
                .clickJobDropDownMenu(folderTwo)
                .selectMoveFromDropDownMenu()
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderOne)
                .getNestedFolder(folderTwo);

        Assert.assertTrue(folderName.isDisplayed());
    }

    @Test
    public void testCreateFreestyleProjectInFolder() {
        createFolderDashboard("folder", true);
        getDriver().findElement(By.xpath("//span[normalize-space()='folder']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/folder/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("new project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
        getDriver().findElement(By.xpath("//a[normalize-space()='folder']")).sendKeys(Keys.RETURN);
        WebElement projectName = getDriver().findElement(By.xpath("//span[normalize-space()='new project']"));

        Assert.assertEquals(projectName.getText(), "new project");
    }

    @Test
    public void testCreateMultibranchPipelineInFolder() {
        createFolderDashboard(NAME, true);
        FolderPage folderPage  = new MainPage(getDriver())
                .clickFolderName(NAME)
                .newItem()
                .enterItemName("My Multibranch Pipeline")
                .selectMultibranchPipelineAndOk()
                .saveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickFolderName(NAME);

        String actualResult = folderPage.getMultibranchPipelineName().getText();

        Assert.assertEquals(actualResult, "My Multibranch Pipeline");
    }
    @Test
    public void testCreatePipelineProjectWithoutDescriptionInFolder() {
        final String folderName = RandomStringUtils.randomAlphanumeric(8);
        final String pipelineName = RandomStringUtils.randomAlphanumeric(8);
        boolean isPipelinePresent = false;

        TestUtils.createFolder(this, folderName, false);

        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        String actualPipelineName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
        getDriver().findElement(By.linkText("Dashboard")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='projectstatus']"))).findElement(By.linkText(folderName)).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='projectstatus']")));
        List<WebElement> jobs = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));
        for (WebElement job : jobs) {
            String jobName = job.getText();
            if ( jobName.contains(pipelineName) ) {
                isPipelinePresent = true;
                break;
            }
        }

        Assert.assertEquals(actualPipelineName, "Pipeline " + pipelineName);
        Assert.assertTrue(isPipelinePresent);
    }
    @Test
    public void testCreateMulticonfigurationProjectInFolder(){

        ProjectPage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("TC 00.04 New item Create Folder")
                .selectFolderAndOk()
                .clickSaveButton()
                .clickCreateAJob()
                .enterItemName("Mine Project")
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage();

        Assert.assertTrue(new ProjectPage(getDriver()).projectsHeadline().getText().contains("Mine Project"));
    }
}

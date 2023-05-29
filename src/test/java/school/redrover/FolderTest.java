package school.redrover;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";

    @Test
    public void testCreateFolderNewItem() {
        TestUtils.createFolder(this, NAME, true);

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test
    public void testCreateFolderCreateAJob() {

        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(NAME)
                .selectFolderAndOk()
                .clickDashboard();

        String actualResult = mainPage.getFolderName().getText();

        WebElement webElement = mainPage.navigateToProjectPage().getNameProject();

        Assert.assertEquals(actualResult, NAME);
        Assert.assertEquals(webElement.getText(), NAME);
    }

    @Test
    public void testCreateFolderDashboard() {
        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.linkText("Dashboard"))));

        WebElement dashboard = getDriver().findElement(By.linkText("Dashboard"));
        WebElement pointer = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']//button[@class=\"jenkins-menu-dropdown-chevron\"]"));
        new Actions(getDriver())
                .moveToElement(dashboard)
                .perform();
        pointer.sendKeys(Keys.RETURN);

        new Actions(getDriver())
                .click(getDriver().findElement(By.xpath("//ul[@class='first-of-type']//span[text()='New Item']")))
                .perform();

        new NewJobPage(getDriver())
                .enterItemName(NAME)
                .selectFolderAndOk()
                .clickDashboard();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test
    public void testErrorWhenCreateFolderWithExistingName() {
        String errorMessage = "Error";

        TestUtils.createFolder(this, NAME, false);

        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href= '/']")).click();

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectFolderAndOk();

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
    public void testCreateNewViewInFolder() {
        final String viewName = "Test View";

        TestUtils.createFolder(this, NAME, true);
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
    public void testRename() {
        final String newName = "newTestName";
        TestUtils.createFolder(this, NAME, true);

        new MainPage(getDriver())
                .selectRenameJobDropDownMenu(NAME)
                .enterNewName(newName)
                .SubmitNewNameFolder()
                .navigateToMainPageByBreadcrumbs();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(newName).isDisplayed(),
                "error was not show new name folder");
    }

    @Test
    public void testRenameFolderNegative() {
        TestUtils.createFolder(this, NAME, false);
        new FolderPage(getDriver()).rename().setNewName(NAME).clickRenameButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(By.cssSelector("div[id='main-panel'] p")).getText(), "The new name is the same as the current name.");
    }

    @Test
    public void testMoveFolderToFolder() {
        final String folderOne = "folderOne";
        final String folderTwo = folderOne + "Two";

        TestUtils.createFolder(this, folderOne, true);
        TestUtils.createFolder(this, folderTwo, true);

        WebElement folderName = new MainPage(getDriver())
                .selectMoveJobDropDownMenu(folderTwo, new FolderPage(getDriver()))
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderOne)
                .getNestedFolder(folderTwo);

        Assert.assertTrue(folderName.isDisplayed());
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
    public void testAddHealthMetric() {
        TestUtils.createFolder(this, NAME, false);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + NAME + "/configure']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@class='jenkins-button advanced-button advancedButton']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id='yui-gen1-button']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='yuimenuitemlabel']"))).click();

        assertTrue(getDriver().findElement(By.xpath("//div[@name='healthMetrics']")).isDisplayed());

        getDriver().findElement(By.xpath("//button [@name='Submit']")).click();
    }

    @Test
    public void testDeleteFolder() {
        TestUtils.createFolder(this, NAME, true);
        new MainPage(getDriver())
                .selectDeleteFolderDropDownMenu(NAME)
                .clickYes();

        Assert.assertTrue(new MainPage(getDriver()).getWelcomeWebElement().isDisplayed(),
                "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testCancelDeleting() {
        TestUtils.createFolder(this, NAME, true);
        new MainPage(getDriver())
                .clickFolderName(NAME)
                .delete()
                .clickDashboard();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
    }

    @Test
    public void testCreateFreestyleProjectInFolder() {
        TestUtils.createFolder(this, "folder", true);

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
    public void testCreatePipelineProjectWithoutDescriptionInFolder() {
        final String folderName = "folderName";
        final String pipelineName = "pipelineName";
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
            if (jobName.contains(pipelineName)) {
                isPipelinePresent = true;
                break;
            }
        }

        Assert.assertEquals(actualPipelineName, "Pipeline " + pipelineName);
        Assert.assertTrue(isPipelinePresent);
    }

    @Test
    public void testCreateMultibranchPipelineInFolder() {
        TestUtils.createFolder(this, NAME, true);
        FolderPage folderPage = new MainPage(getDriver())
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
    public void testCreateMulticonfigurationProjectInFolder() {

        ProjectPage mainPage = new MainPage(getDriver())
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

    @Test
    public void testTwoFoldersCreation() {
        final String FOLDER1_NAME = "My_folder";
        final String FOLDER2_NAME = "MyFolder2";
        List<String> expectedFoldersList = Arrays.asList(FOLDER1_NAME, FOLDER2_NAME);

        TestUtils.createFolder(this, FOLDER1_NAME, true);
        TestUtils.createFolder(this, FOLDER2_NAME, true);

        List<WebElement> foldersList = getDriver().findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));

        List<String> actualFoldersList = new ArrayList<>();
        for (WebElement webElement : foldersList) {
            actualFoldersList.add(webElement.getText());
        }

        Collections.sort(expectedFoldersList);
        Collections.sort(actualFoldersList);

        Assert.assertEquals(actualFoldersList, expectedFoldersList);
    }

    @DataProvider(name = "create-folder")
    public Object[][] provideFoldersNames() {
        return new Object[][]
                {{"My_folder"}, {"MyFolder2"}, {"FOLDER"}};
    }

    @Test(dataProvider = "create-folder")
    public void testFoldersCreationWithProvider(String provideNames) {
        TestUtils.createFolder(this, provideNames, true);

        getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']")).click();

        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span")).getText(), provideNames);
    }

    @Test
    public void testCreateFolderFromExistingFolder() {
        final String FOLDER1_NAME = "My_folder";
        final String COPY_FOLDER = "Copy_folder";
        final String DESCRIPTION = "This is a test folder";

        TestUtils.createFolder(this, FOLDER1_NAME, true);

        getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']"))
                .sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Configure"))).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("_.description"))).sendKeys(DESCRIPTION);
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']")).click();

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated((By.id("name")))).sendKeys(COPY_FOLDER);
        getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        getDriver().findElement(By.id("from")).sendKeys(FOLDER1_NAME);
        getDriver().findElement(By.id("ok-button")).click();

        String copiedFolderDescription = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("_.description"))).getText();

        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("view-message"));

        Assert.assertTrue(getDriver().findElement(By.id("view-message")).getText().contains(DESCRIPTION));
        Assert.assertEquals(copiedFolderDescription, DESCRIPTION);
    }
    @Test
    public void testMoveFreestyleProjectToFolder() {

        String projectName = "Project_1";

        TestUtils.createFolder(this, NAME, true);
        TestUtils.createFreestyleProject(this, projectName, true);

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']", projectName)))).click();
        getDriver().findElement(By.xpath(String.format("//a[@href='/job/%s/move']", projectName))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select setting-input']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//option[@value='/%s']", NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getDriver().findElement(By.xpath("//ol/li/a[@href='/']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']", NAME)))).click();

        WebElement movedProject = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']", projectName))));

        Assert.assertEquals(movedProject.getText(), projectName);
    }

    @Test
    public void testCreateOrganizationFolderInFolder() {
        final String nameFolder = "nameFolder";
        final String nameOrganizationFolder = nameFolder + "Organization";

        WebElement createdOrganizationFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameFolder)
                .selectFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickFolderName(nameFolder)
                .clickNewItem()
                .enterItemName(nameOrganizationFolder)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickFolderName(nameFolder)
                .getNestedFolder(nameOrganizationFolder);

        Assert.assertTrue(createdOrganizationFolder.isDisplayed());
    }

    @Test
    public void testMoveMultibranchPipelineToFolderByDrop(){
        final String nameMultibranchPipeline = "MultibranchPipeline1";
        final String nameFolder = "Folder1";

        TestUtils.createFolder(this, nameFolder, true);
        TestUtils.createMultibranchPipeline(this, nameMultibranchPipeline, true);

        WebElement projectNameDisplays = new MainPage(getDriver())
                .clickJobDropDownMenu(nameMultibranchPipeline)
                .selectMoveJobDropDownMenu(nameMultibranchPipeline,new FolderPage(getDriver()))
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(nameFolder)
                .getNestedFolder(nameMultibranchPipeline);

        Assert.assertEquals(projectNameDisplays.getText(),nameMultibranchPipeline);
    }
  
    @Test
    public void testMoveFolderToFolderFromSideMenu() {
        String folder1 = "Folder1";
        String folder2 = "Folder2";

        TestUtils.createFolder(this, folder1, true);
        TestUtils.createFolder(this, folder2, true);

        WebElement nestedFolder = new MainPage(getDriver())
                .clickToOpenFolder(folder2)
                .clickMoveOnSideMenu(folder2)
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folder1)
                .getNestedFolder(folder2);

        Assert.assertEquals(nestedFolder.getText(), folder2);
    }

    @Test
    public void testConfigureFolderNameDescriptionHealthMetrics(){
        final String NEW_FOLDER_NAME = "TestFolder0404";
        final String DESCRIPTION_VALUE = "Test Description of the folder";

        TestUtils.createFolder(this, NAME, false);

        FolderPage folderPage =
                new FolderPage(getDriver())
                        .clickConfigureSideMenu()
                        .enterDisplayName(NEW_FOLDER_NAME)
                        .enterDescription(DESCRIPTION_VALUE)
                        .setHealthMetricsType()
                        .clickSaveButton();
        Assert.assertEquals(folderPage.getFolderDisplayName(), NEW_FOLDER_NAME);
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION_VALUE);
        Assert.assertTrue(folderPage.clickConfigureSideMenu().clickOnHealthMetricsType().isRecursive());
    }
}

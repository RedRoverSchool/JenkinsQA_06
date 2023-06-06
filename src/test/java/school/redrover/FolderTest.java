package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";
    private static final String DESCRIPTION = "Created new folder";
    private static final String  DISPLAY_NAME = "NewFolder";

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
                .getHeader()
                .clickLogo();

        String actualResult = mainPage.getFolderName().getText();

        String folderName = mainPage
                .clickFolderName(NAME)
                .getFolderName();

        Assert.assertEquals(actualResult, NAME);
        Assert.assertEquals(folderName, NAME);
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
                .getHeader()
                .clickLogo();

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
        final String expectedErrorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        String actualErrorMessage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(invalidData)
                .getItemInvalidMessage();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
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
                .dropDownMenuClickRename(NAME, new FolderPage(getDriver()))
                .enterNewName(newName)
                .submitNewName()
                .navigateToMainPageByBreadcrumbs();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(newName).isDisplayed(),
                "error was not show new name folder");
    }

    @Test
    public void testRenameFolderNegative() {
        TestUtils.createFolder(this, NAME, false);
        new FolderPage(getDriver()).rename().enterNewName(NAME).submitNewName();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(By.cssSelector("div[id='main-panel'] p")).getText(), "The new name is the same as the current name.");
    }

    @Test
    public void testMoveFolderToFolder() {
        final String folderOne = "folderOne";
        final String folderTwo = folderOne + "Two";

        TestUtils.createFolder(this, folderOne, true);
        TestUtils.createFolder(this, folderTwo, true);

        String folderName = new MainPage(getDriver())
                .dropDownMenuClickMove(folderTwo, new FolderPage(getDriver()))
                .selectDestinationFolder(folderOne)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(folderOne)
                .getNestedFolder(folderTwo);

        Assert.assertEquals(folderName, folderTwo);
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        TestUtils.createFolder(this, NAME, false);

        FolderPage folderPage = new FolderPage(getDriver())
                .clickConfigureSideMenu()
                .enterDisplayName(DISPLAY_NAME)
                .addDescription(DESCRIPTION)
                .clickSaveButton();

        Assert.assertEquals(folderPage.getFolderName(), DISPLAY_NAME);
        Assert.assertTrue(folderPage.getOriginalFolderNameIfDisplayNameSet().contains("Folder name: " + NAME));
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION);
    }

    @Test
    public void testAddHealthMetric() {
        TestUtils.createFolder(this, NAME, false);
        boolean healthMetric = new FolderPage(getDriver())
                .clickConfigureSideMenu()
                .clickHealthMetrics()
                .clickAddMetric()
                .clickChildWithWorstHealth()
                .healthMetricIsVisible();

        assertTrue(healthMetric);
    }

    @Test
    public void testDeleteFolder() {
        TestUtils.createFolder(this, NAME, true);
        new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(NAME)
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
                .getHeader()
                .clickLogo();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(NAME).isDisplayed(),
                "error was not show name folder");
    }

    @Test
    public void testCreateFreestyleProjectInFolder() {
        final String folderName = "folder";
        final String newProjectName = "new project";

        TestUtils.createFolder(this, folderName, true);

        String itemName = new MainPage(getDriver())
                .clickFolderName(folderName)
                .newItem()
                .enterItemName(newProjectName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(folderName)
                .getLastCreatedItemName();

        Assert.assertEquals(itemName, newProjectName);
    }

    @Test
    public void testCreateMultibranchPipelineInFolder() {
        TestUtils.createFolder(this, NAME, true);
        FolderPage folderPage = new MainPage(getDriver())
                .clickFolderName(NAME)
                .newItem()
                .enterItemName("My Multibranch Pipeline")
                .selectMultibranchPipelineAndOk()
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickFolderName(NAME);

        String actualResult = folderPage.getMultibranchPipelineName().getText();

        Assert.assertEquals(actualResult, "My Multibranch Pipeline");
    }

    @Test
    public void testCreateMulticonfigurationProjectInFolder() {

       MultiConfigurationProjectPage multiPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("TC 00.04 New item Create Folder")
                .selectFolderAndOk()
                .clickSaveButton()
                .clickCreateAJob()
                .enterItemName("Mine Project")
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton();

        Assert.assertTrue(multiPage.getMultiProjectName().contains("Mine Project"));
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

    @Test(dependsOnMethods = "testCreateNewFolderWithDescription")
    public void testCreateFolderFromExistingFolder() {
        final String secondFolderName = "SecondFolder";

        String copiedFolderDescription = new FolderPage(getDriver())
                .getHeader()
                .clickLogo()
                .clickFolderName(NAME)
                .clickNewItem()
                .enterItemName(secondFolderName)
                .copyFromFolder(NAME)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(NAME)
                .clickInnerFolder(DISPLAY_NAME)
                .clickInnerFolder(DISPLAY_NAME)
                .getFolderDescription();

        Assert.assertEquals(copiedFolderDescription, DESCRIPTION);
    }

    @Test
    public void testMoveFreestyleProjectToFolder() {
        final String projectName = "FreestyleProject";

        String movedFreestyleProjectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()

                .clickNewItem()
                .enterItemName(projectName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()

                .clickMoveOnSideMenu()
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(NAME)
                .getNestedFreestyleProjectName(projectName);

        Assert.assertEquals(movedFreestyleProjectName, projectName);
    }

    @Test
    public void testCreateOrganizationFolderInFolder() {
        final String nameFolder = "nameFolder";
        final String nameOrganizationFolder = nameFolder + "Organization";

        String createdOrganizationFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameFolder)
                .selectFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(nameFolder)
                .clickNewItem()
                .enterItemName(nameOrganizationFolder)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(nameFolder)
                .getNestedFolder(nameOrganizationFolder);

        Assert.assertEquals(createdOrganizationFolder, nameOrganizationFolder);
    }

    @Test
    public void testMoveMultibranchPipelineToFolderByDrop(){
        final String nameMultibranchPipeline = "MultibranchPipeline1";
        final String nameFolder = "Folder1";

        TestUtils.createFolder(this, nameFolder, true);
        TestUtils.createMultibranchPipeline(this, nameMultibranchPipeline, true);

        String projectNameDisplays = new MainPage(getDriver())
                .dropDownMenuClickMove(nameMultibranchPipeline,new FolderPage(getDriver()))
                .selectDestinationFolder(nameFolder)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(nameFolder)
                .getNestedFolder(nameMultibranchPipeline);

        Assert.assertEquals(projectNameDisplays,nameMultibranchPipeline);
    }
  
    @Test
    public void testMoveFolderToFolderFromSideMenu() {
        String folder1 = "Folder1";
        String folder2 = "Folder2";

        TestUtils.createFolder(this, folder1, true);
        TestUtils.createFolder(this, folder2, true);

        String nestedFolder = new MainPage(getDriver())
                .clickFolderName(folder2)
                .clickMoveOnSideMenu(folder2)
                .selectDestinationFolder(folder1)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(folder1)
                .getNestedFolder(folder2);

        Assert.assertEquals(nestedFolder, folder2);
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
                        .setHealthMetricsType()
                        .addDescription(DESCRIPTION_VALUE)
                        .clickSaveButton();
        Assert.assertEquals(folderPage.getFolderName(), NEW_FOLDER_NAME);
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION_VALUE);
        Assert.assertTrue(folderPage.clickConfigureSideMenu().clickOnHealthMetricsType().isRecursive());
    }
    @Test
    public void testCreateOrganizationFolder() {

        final String nameFolder = "OrganizationFolder";

        WebElement createdOrganizationFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameFolder)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(nameFolder)
                .getNestedOrganizationFolder(nameFolder);

        Assert.assertTrue(createdOrganizationFolder.isDisplayed());
    }

    @Test
    public void testMoveMultibranchPipelineToFolderFromSideMenu() {
        final String nameMultibranchPipeline = "MultibranchPipeline1";
        final String nameFolder = "Folder1";

        TestUtils.createFolder(this, nameFolder, true);
        TestUtils.createMultibranchPipeline(this, nameMultibranchPipeline, true);

        String nameMultibranchPipelineDisplays = new MainPage(getDriver())
                .dropDownMenuClickMove(nameMultibranchPipeline, new MultibranchPipelinePage(getDriver()))
                .selectDestinationFolder(nameFolder)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(nameFolder)
                .getMultibranchPipelineName().getText();

        Assert.assertEquals(nameMultibranchPipelineDisplays,nameMultibranchPipeline);
    }

    @Test (dependsOnMethods = "testCreateFolderCreateAJob")
    public void testMoveMultiConfigurationProjectToFolderFromSideMenu() {

        final String multiConfigurationProjectName= "MyMultiConfigurationProject";

        String createdMultiConfigurationProjectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfigurationProjectName)
                .selectMultiConfigurationProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()

                .clickMultiConfigurationProjectName(multiConfigurationProjectName)
                .clickMoveOnSideMenu()
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(NAME)

                .getNestedMultiConfigurationProjectName(multiConfigurationProjectName);

        Assert.assertEquals(createdMultiConfigurationProjectName,multiConfigurationProjectName);
    }

    @Test
    public void testCreatePipelineProjectWithoutDescriptionInFolder() {
        final String folderName = "folderName";
        final String pipelineName = "pipelineName";

        TestUtils.createFolder(this, folderName, false);

        String projectName = new FolderPage(getDriver())
                .clickNewItem()
                .enterItemName(pipelineName)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getProjectName();

        FolderPage folderPage = new FolderPage(getDriver())
                .getHeader()
                .clickLogo()
                .clickFolderName(folderName);

        Assert.assertTrue(folderPage.getNestedPipelineProject(pipelineName).getText().contains(pipelineName));
        Assert.assertEquals(projectName, "Pipeline " + pipelineName);
    }

    @Test
    public void testMovePipelineToFolder() {

        TestUtils.createFolder(this, "testFolder",true);
        TestUtils.createPipeline(this, "testPipeline",true);

        String actualBreadcrumbText =
            new MainPage(getDriver())
            .dropDownMenuClickMove("testPipeline", new FolderPage(getDriver()))
            .selectDestinationFolder("testFolder")
            .clickMoveButton().
            getBreadcrumbText();

        assertEquals(actualBreadcrumbText, "Dashboard > testFolder > testPipeline");

    }

}

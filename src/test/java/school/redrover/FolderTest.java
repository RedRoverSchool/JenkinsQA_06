package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FolderTest extends BaseTest {

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[@class='label'][text()='Folder']")).click();
        getDriver().findElement(By.xpath("//div[@class='btn-decorator']")).click();
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        final String folderName = "f1";
        final String displayName = "NewFolder";
        final String description = "Created new folder";

        TestUtils.createFolder(this, folderName, false);
        FolderPage folderPage = new FolderPage(getDriver());
        folderPage.clickConfigureSideMenu()
                .enterDisplayName(displayName)
                .enterDescription(description)
                .clickSaveButton();

        Assert.assertEquals(folderPage.getFolderDisplayName(), displayName);
        Assert.assertTrue(folderPage.getFolderName().contains("Folder name: " + folderName));
        Assert.assertEquals(folderPage.getFolderDescription(), description);
    }

    @Test()
    public void testEditFolderName() {
        final String name = "AnotherFolder";
        final String editedName = "NewFolderName";

        TestUtils.createFolder(this, name, true);
        FolderPage folderPage = new MainPage(getDriver())
                .clickJobDropdownMenu(name)
                .clickRenameInDropDownMenu()
                .setNewName(editedName)
                .clickRenameButton();

        Assert.assertEquals(folderPage.getFolderDisplayName(), editedName);
    }

    @Test
    public void testMoveFreestyleProjectToFolder() {

        String folderName = "Folder_1";
        String projectName = "Project_1";

        TestUtils.createFolder(this, folderName, true);
        TestUtils.createFreestyleProject(this, projectName, true);

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']",projectName)))).click();
        getDriver().findElement(By.xpath(String.format("//a[@href='/job/%s/move']", projectName))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select setting-input']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//option[@value='/%s']",folderName)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getDriver().findElement(By.xpath("//ol/li/a[@href='/']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']", folderName)))).click();

        WebElement movedProject = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='job/%s/']",projectName))));

        Assert.assertEquals(movedProject.getText(),projectName);
    }

    @Test
    public void testErrorWhenCreateFolderWithExistingName() {
        String folderName = "TestFolders";
        String errorMessage = "Error";

        createFolder(folderName);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href= '/']")).click();

        createFolder(folderName);

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

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Folder']"))).click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.clear();
        fieldInputName.sendKeys(invalidData);

        WebElement resultMessage = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, errorMessage);
    }

    @Test
    public void testMoveFolderToFolder(){
        final String folderOne = RandomStringUtils.randomAlphanumeric(9);
        final String folderTwo = folderOne + "Two";

        TestUtils.createFolder(this, folderOne, true);
        TestUtils.createFolder(this, folderTwo, true);

        WebElement folderName = new MainPage(getDriver())
                .clickOnProjectDropDownMenu(folderTwo)
                .selectMoveFromDropDownMenu()
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderOne)
                .getNestedFolder(folderTwo);

        Assert.assertTrue(folderName.isDisplayed());
    }

    @Test
    public void testCreateFolder3() {
        String nameItem = "Test Folder";

        MainPage mainPage  = new MainPage(getDriver())
                .clickNewItem().enterItemName(nameItem)
                .selectFolderAndOk()
                .saveProjectAndGoToFolderPage()
                .navigateToMainPageByBreadcrumbs();

        String actualResult = mainPage.getFolderName().getText();

        WebElement webElement = mainPage.navigateToProjectPage().getNameProject();

        Assert.assertEquals(actualResult, nameItem);
        Assert.assertEquals(webElement.getText(), nameItem);
    }

    @Test(dependsOnMethods = "testCreateFolder3")
    public void testCreateMultibranchPipelineInFolder() {

        FolderPage folderPage  = new MainPage(getDriver())
                .clickFolderName("Test Folder")
                .newItem()
                .enterItemName("My Multibranch Pipeline")
                .selectMultibranchPipelineAndOk()
                .saveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickFolderName("Test Folder");

        String actualResult = folderPage.getMultibranchPipelineName().getText();

        Assert.assertEquals(actualResult, "My Multibranch Pipeline");
    }
}

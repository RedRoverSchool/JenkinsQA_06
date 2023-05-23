package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FolderTest extends BaseTest {

    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By DASHBOARD_LINK = By.xpath("//div[@id='breadcrumbBar']//a");
    private static final By DISPLAY_NAME_FIELD = By.name("_.displayNameOrNull");
    private static final String FOLDER_NAME_1 = "f1";

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[@class='label'][text()='Folder']")).click();
        getDriver().findElement(By.xpath("//div[@class='btn-decorator']")).click();
    }

    private void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        String name = "NewFolder";
        String description = "Created new folder";

        createFolder(FOLDER_NAME_1);
        getWait2().until(ExpectedConditions.elementToBeClickable(DISPLAY_NAME_FIELD)).click();
        getDriver().findElement(DISPLAY_NAME_FIELD).sendKeys(name);
        WebElement descriptionField = getDriver().findElement(By.name("_.description"));
        descriptionField.sendKeys(description);
        getDriver().findElement(SAVE_BUTTON).click();

    Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), name);
    Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel'][contains(text(), 'Folder name:')]")).getText().contains("Folder name: " + FOLDER_NAME_1));
    Assert.assertEquals(getDriver().findElement(By.id("view-message")).getText(), description);
    }

    @Test()
    public void testEditFolderName() {
        String name = "AnotherFolder";
        String editedName = "NewFolderName";

        createFolder(name);
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD_LINK).click();

        WebElement folderDropdown = getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']/button", name)));
        jsClick(folderDropdown);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Rename"))).click();
        WebElement newNameField = getDriver().findElement(By.name("newName"));
        getWait5().until(ExpectedConditions.elementToBeClickable(newNameField)).click();
        newNameField.clear();
        newNameField.sendKeys(editedName);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), editedName);
        Assert.assertNotEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), name);
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

    @Test
    public void testCreateOrganizationFolderInFolder() {
        final String name = RandomStringUtils.randomAlphanumeric(8);

        TestUtils.createFolder(this, name, true);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        TestUtils.createOrganizationFolder(this, name + "Organization", true);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("projectstatus"))).getText().contains(name + "Organization"));
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
                .clickDropDownMenuFolderName(folderTwo)
                .selectMoveFromDropDownMenu()
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderOne)
                .getNestedFolderName(folderTwo);

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

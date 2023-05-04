package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

public class MykolaTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String folderName = faker.artist().name() + " folder";
    private final String newFolderName = faker.funnyName().name() + " folder";

    private void createFolder(String nameFolder) {
        WebElement textField = getDriver().findElement(By.xpath("//*[@id='name']"));
        textField.sendKeys(nameFolder);

        WebElement folderItem = getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folderItem.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement folderDescription = getDriver().findElement(By.xpath("//*[@name='_.description']"));
        folderDescription.sendKeys("Test description");

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveButton.click();
    }

    private void folderDropDownMenu(String folderName) throws InterruptedException {
        WebElement dashboardPageLink = getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]"));
        dashboardPageLink.click();

        WebElement folderNameButton = getDriver().findElement(By.xpath("//*[contains(@href,'job')]/*[contains(text(),'" + folderName + "')]"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(folderNameButton).perform();

        WebElement dropDownButton = getDriver().findElement(By.xpath("//a[contains(@href,'job')]/button[@class='jenkins-menu-dropdown-chevron']"));
        dropDownButton.click();
        Thread.sleep(500);
    }

    private void renameFolder() {
        WebElement newNameField = getDriver().findElement(By.name("newName"));
        newNameField.sendKeys(Keys.CONTROL + "a");
        newNameField.sendKeys(newFolderName);
        WebElement blueRenameButton = getDriver().findElement(By.name("Submit"));
        blueRenameButton.click();
    }

    @Test
    public void testNewItemCreateFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + folderName + "')]"));
        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(folderName));
    }

    @Test
    public void testDeleteCreatedFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        WebElement deleteFolderButton = getDriver().findElement(By.xpath("//*[contains(@href,'delete')]"));
        deleteFolderButton.click();
        WebElement yesButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        yesButton.click();

        Assert.assertTrue(getDriver().findElement(By.tagName("h1")).isDisplayed());
    }

    @Test
    public void testRenameCreatedFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        WebElement renameButton = getDriver().findElement(By.xpath("//*[contains(@href,'confirm-rename')]"));
        renameButton.click();
        renameFolder();

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + newFolderName + "')]"));
        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(newFolderName));
    }

    @Test
    public void testDeleteSecondFolderFromDashboardPage() throws InterruptedException {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        getDriver().findElement(newItemButtonLocator).click();
        createFolder(newFolderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        List<WebElement> folderList = getDriver().findElements(By.xpath("//tr[contains(@id,'job')]"));
        String deletedFolderName = folderList.get(0).getAttribute("id").split("_")[1];
        folderDropDownMenu(deletedFolderName);

        By deleteFolderButtonLocator = By.xpath("//*[@index='2']");
        getDriver().findElement(deleteFolderButtonLocator).click();
        WebElement yesButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        yesButton.click();

        Assert.assertNull(null, deletedFolderName);
    }

    @Test
    public void testRenameFolderFromDashboardPage() throws InterruptedException {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        folderDropDownMenu(folderName);

        By renameButtonLocator = By.xpath("//*[contains(text(),'Rename')]");
        getDriver().findElement(renameButtonLocator).click();
        renameFolder();

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + newFolderName + "')]"));
        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(newFolderName));
    }

    @Test
    public void testAddFolderInExistingFolderThroughDropDownMenu() throws InterruptedException {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        folderDropDownMenu(folderName);

        By dropDownNewItemButtonLocator =By.xpath("//*[@index='1']");
        getDriver().findElement(dropDownNewItemButtonLocator).click();

        String addedFolderName = faker.name().firstName();
        createFolder(addedFolderName);

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + addedFolderName + "')]"));
        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(addedFolderName));
    }

    @Test
    public void testAddNewViewThroughAllIconOnCreatedFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        WebElement allPlusButton = getDriver().findElement(By.xpath("//*[@title='New View']"));
        allPlusButton.click();
        WebElement nameField = getDriver().findElement(By.xpath("//*[@id='name']"));
        String viewName = faker.funnyName().name();
        nameField.sendKeys(viewName);
        WebElement listViewSelectedSymbol = getDriver().findElement(By.xpath("//*[@for='hudson.model.ListView']"));
        listViewSelectedSymbol.click();
        WebElement createButton = getDriver().findElement(By.xpath("//button[@id='ok']"));
        createButton.click();
        WebElement okButton = getDriver().findElement(By.name("Submit"));
        okButton.click();

        WebElement viewNameButton = getDriver().findElement(By.xpath("//div/*[contains(@href,'view') and contains(text(),'" + viewName + "')]"));
        Assert.assertTrue(viewNameButton.isDisplayed());
    }

    @Test
    public void testDeleteViewThroughAllIconOnDashboardPage() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();
        createFolder(folderName);

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        WebElement allPlusButton = getDriver().findElement(By.xpath("//*[@title='New View']"));
        allPlusButton.click();
        WebElement nameField = getDriver().findElement(By.xpath("//*[@id='name']"));
        String viewName = faker.funnyName().name();
        nameField.sendKeys(viewName);
        WebElement listViewSelectedSymbol = getDriver().findElement(By.xpath("//*[@for='hudson.model.ListView']"));
        listViewSelectedSymbol.click();
        WebElement createButton = getDriver().findElement(By.xpath("//button[@id='ok']"));
        createButton.click();
        WebElement okButton = getDriver().findElement(By.name("Submit"));
        okButton.click();

        getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).click();

        List<WebElement> viewButtons = getDriver().findElements(By.xpath("//*[@class='tab']"));

        WebElement viewNameButton = getDriver().findElement(By.xpath("//div/*[contains(@href,'view') and contains(text(),'" + viewName + "')]"));
        viewNameButton.click();
        WebElement deleteViewButton = getDriver().findElement(By.xpath("//*[@href='delete']"));
        deleteViewButton.click();
        WebElement yesButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        yesButton.click();

        List<WebElement> viewButtonsAfterDelete = getDriver().findElements(By.xpath("//*[@class='tab']"));
        int x = viewButtons.size() - 1;
        Assert.assertEquals(viewButtonsAfterDelete.size(), x);
    }
}

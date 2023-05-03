package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MykolaTests extends BaseTest {

    @Test
    public void testNewItemCreateFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();

        String folderName = "My folder";
        WebElement textField = getDriver().findElement(By.xpath("//*[@id='name']"));
        textField.sendKeys(folderName);

        WebElement folderItem = getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folderItem.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement folderDescription = getDriver().findElement(By.xpath("//*[@name='_.description']"));
        folderDescription.sendKeys("Test description");

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveButton.click();

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + folderName + "')]"));

        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(folderName));
    }

    @Test
    public void testDeleteCreatedFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();

        String folderName = "My folder";
        WebElement textField = getDriver().findElement(By.xpath("//*[@id='name']"));
        textField.sendKeys(folderName);

        WebElement folderItem = getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folderItem.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement folderDescription = getDriver().findElement(By.xpath("//*[@name='_.description']"));
        folderDescription.sendKeys("Test description");

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveButton.click();

        WebElement deleteFolderButton = getDriver().findElement(By.xpath("//*[contains(@href,'delete')]"));
        deleteFolderButton.click();

        WebElement yesButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        yesButton.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).isDisplayed());
    }

    @Test
    public void testRenameCreatedFolder() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();

        String folderName = "My folder";
        WebElement textField = getDriver().findElement(By.xpath("//*[@id='name']"));
        textField.sendKeys(folderName);

        WebElement folderItem = getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folderItem.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement folderDescription = getDriver().findElement(By.xpath("//*[@name='_.description']"));
        folderDescription.sendKeys("Test description");

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveButton.click();

        WebElement renameButton = getDriver().findElement(By.xpath("//*[contains(@href,'confirm-rename')]"));
        renameButton.click();

        String newFolderName = "Team folder";
        WebElement newNameField = getDriver().findElement(By.name("newName"));
        newNameField.sendKeys(Keys.CONTROL + "a");
        newNameField.sendKeys(newFolderName);

        WebElement blueRenameButton = getDriver().findElement(By.name("Submit"));
        blueRenameButton.click();

        WebElement folderPageName = getDriver().findElement(By.xpath("//h1[contains(text(),'" + newFolderName + "')]"));
        Assert.assertTrue(folderPageName.isDisplayed() && folderPageName.getText().equals(newFolderName));
    }

    @Test
    public void testDeleteFolderFromDashboardPage() {
        By newItemButtonLocator = By.xpath("//*[@href='/view/all/newJob']");
        getDriver().findElement(newItemButtonLocator).click();

        String folderName = "My folder";
        WebElement textField = getDriver().findElement(By.xpath("//*[@id='name']"));
        textField.sendKeys(folderName);

        WebElement folderItem = getDriver().findElement(By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folderItem.click();

        WebElement okButton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        okButton.click();

        WebElement folderDescription = getDriver().findElement(By.xpath("//*[@name='_.description']"));
        folderDescription.sendKeys("Test description");

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveButton.click();

        WebElement dashboardPageLink = getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]"));
        dashboardPageLink.click();

        WebElement folderNameButton = getDriver().findElement(By.xpath("//*[contains(text(),'" + folderName + "')]"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(folderNameButton).perform();

        WebElement dropDownButton = getDriver().findElement(By.xpath("(//*[@class='jenkins-menu-dropdown-chevron'])[3]"));
        dropDownButton.click();

        WebElement deleteFolderButton = getDriver().findElement(By.xpath("//*[contains(text(),'Delete Folder')]"));
        deleteFolderButton.click();

        WebElement yesButton = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        yesButton.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@href='/' and contains(text(),'Dashboard')]")).isDisplayed());
    }
}

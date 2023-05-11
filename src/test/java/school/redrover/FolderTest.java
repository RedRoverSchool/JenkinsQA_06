package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;


public class FolderTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By FOLDER = By.xpath("//span[@class='label'][text()='Folder']");
    private static final By ENTER_ITEM_NAME = By.name("name");
    private static final By OK_BUTTON = By.xpath("//div[@class='btn-decorator']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By DASHBOARD_LINK = By.xpath("//div[@id='breadcrumbBar']//a");
    private static final By DISPLAY_NAME_FIELD = By.name("_.displayNameOrNull");

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

    private void createFolder(String name) {
        getDriver().findElement(NEW_ITEM).click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(ENTER_ITEM_NAME)).sendKeys(name);
        getDriver().findElement(FOLDER).click();
        getDriver().findElement(OK_BUTTON).click();
    }

    private void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        String folderName = "Folder1";
        String name = "NewFolder";
        String description = "Created new folder";

        createFolder(folderName);
        getWait(2).until(ExpectedConditions.elementToBeClickable(DISPLAY_NAME_FIELD)).click();
        getDriver().findElement(DISPLAY_NAME_FIELD).sendKeys(name);
        WebElement descriptionField = getDriver().findElement(By.name("_.description"));
        descriptionField.sendKeys(description);
        getDriver().findElement(SAVE_BUTTON).click();

    Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), name);
    Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel'][contains(text(), 'Folder name:')]")).getText().contains("Folder name: " + folderName));
    Assert.assertEquals(getDriver().findElement(By.id("view-message")).getText(), description);
    }
@Ignore
    @Test()
    public void testEditFolderName() {
        String name = "AnotherFolder";
        String editedName = "NewFolderName";

        createFolder(name);
        getDriver().findElement(DASHBOARD_LINK).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(("//tbody//tr[1]//a"))).getText(), name);

        WebElement folderDropdown = getDriver().findElement(By.xpath("//tbody//button"));
        jsClick(folderDropdown);
        getWait(5).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Rename"))).click();
        WebElement newNameField = getDriver().findElement(By.name("newName"));
        getWait(2).until(ExpectedConditions.elementToBeClickable(newNameField)).click();
        newNameField.clear();
        newNameField.sendKeys(editedName);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), editedName);
        Assert.assertNotEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), name);
    }
}

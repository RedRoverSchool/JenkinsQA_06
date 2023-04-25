package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;


public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Folder1";
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By FOLDER = By.xpath("//span[@class='label'][text()='Folder']");
    private static final By ENTER_ITEM_NAME = By.name("name");
    private static final By OK_BUTTON = By.xpath("//div[@class='btn-decorator']");
    private static final By SAVE_BUTTON = By.name("Submit");

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

    @Test
    public void testCreateNewFolderWithDescription() {
        String expectedDisplayName = "New Folder";
        String expectedDescription = "Created new folder";

        getDriver().findElement(NEW_ITEM).click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(ENTER_ITEM_NAME)).sendKeys(FOLDER_NAME);
        getDriver().findElement(FOLDER).click();
        getDriver().findElement(OK_BUTTON).click();
        WebElement displayName = getDriver().findElement(By.name("_.displayNameOrNull"));
        getWait(2).until(ExpectedConditions.elementToBeClickable(displayName)).click();
        displayName.sendKeys(expectedDisplayName);
        WebElement description = getDriver().findElement(By.name("_.description"));
        description.sendKeys(expectedDescription);
        getDriver().findElement(SAVE_BUTTON).click();

    Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), expectedDisplayName);
    Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel'][text()='Folder name: Folder1']")).getText().contains("Folder name: " + FOLDER_NAME));
    Assert.assertEquals(getDriver().findElement(By.id("view-message")).getText(), expectedDescription);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateNewFolder1Test extends BaseTest {

    private static final By LINK_NEW_ITEM = By.linkText("New Item");
    private static final By REQUIRED_FIELD= By.id("name");
    private static final By FOLDER = By.xpath("//span[text()='Folder']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[text()='Save']");
    private static final By NEW_FOLDER_NAME = By.xpath("//h1");
    private static final By NAME_IS_DISPLAYED = By.id("main-panel");

    private String folderName = "New Folder";

    @Test
    public void testCreateNewFolder() {

        getDriver().findElement(LINK_NEW_ITEM).click();
        getDriver().findElement(REQUIRED_FIELD).sendKeys(folderName);
        getDriver().findElement(FOLDER).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(NEW_FOLDER_NAME).getText(), folderName);
        Assert.assertTrue(getDriver().findElement(NAME_IS_DISPLAYED).getText().contains(folderName));
    }
}
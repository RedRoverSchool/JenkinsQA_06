package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class KirillKTest extends BaseTest {

    public static final By NEW_JOB_LINK = By.cssSelector("a[href='newJob']");
    public static final By ITEM_NAME_INPUT = By.cssSelector("div[class='add-item-name'] > input:nth-of-type(1)");
    public static final By SELECT_ITEM_TYPE_FOLDER = By.cssSelector("li.com_cloudbees_hudson_plugins_folder_Folder");
    public static final By OK_BUTTON = By.cssSelector("div.footer button#ok-button");
    public static final By FINAL_SAVE_BUTTON = By.cssSelector("div#bottom-sticker button[name='Submit']");

    @Test
    public void testFolderCreation() {
        WebElement newJobLink = getDriver().findElement(NEW_JOB_LINK);
            newJobLink.click();
        WebElement itemNameInput = getDriver().findElement(ITEM_NAME_INPUT);
            try {
                Assert.assertTrue(itemNameInput.isEnabled());
            } catch (Exception e) {
                System.out.println(e);
            }
            itemNameInput.click();
            itemNameInput.sendKeys("auto-test");
        WebElement selectItemTypeFolder = getDriver().findElement(SELECT_ITEM_TYPE_FOLDER);
            selectItemTypeFolder.click();
        WebElement okButton = getDriver().findElement(OK_BUTTON);
            Assert.assertEquals(okButton.getText(), "OK");
            okButton.click();
        WebElement finalSaveButton = getDriver().findElement(FINAL_SAVE_BUTTON);
            finalSaveButton.click();
    }
}

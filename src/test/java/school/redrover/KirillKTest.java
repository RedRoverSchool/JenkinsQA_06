package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class KirillKTest extends BaseTest {

    public static final By NEW_JOB_LINK = By.xpath("// a[@href='newJob']");
    public static final By ITEM_NAME_INPUT = By.xpath("// div[@class='add-item-name'] / input[1]");
    public static final By SELECT_ITEM_TYPE_FOLDER = By.xpath("// li[@class='com_cloudbees_hudson_plugins_folder_Folder']");
    public static final By OK_BUTTON = By.xpath("// div[@class='footer'] // button[@id='ok-button']");
    public static final By FINAL_SAVE_BUTTON = By.xpath("// div[@id='bottom-sticker'] // button[@name='Submit']");

    @Test
    public void testFolderCreation(){

        getWait5();

        WebElement newJobLink = getDriver().findElement(NEW_JOB_LINK);
        newJobLink.click();

        WebElement itemNameInput = getDriver().findElement(ITEM_NAME_INPUT);
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

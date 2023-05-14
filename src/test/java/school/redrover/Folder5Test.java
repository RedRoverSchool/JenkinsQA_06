package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder5Test extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By DASHBOARD = By.xpath("//img[@id='jenkins-head-icon']");
    private static final By SET_ITEM_NAME = By.id("name");
    private static final String NAME_FOLDER = "New folder";

    @Test
    public void testCreateNewFolder() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(SET_ITEM_NAME).sendKeys("New folder");
        getDriver().findElement(By.xpath("//span[text() = 'Folder']/..")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(DASHBOARD).click();
        WebElement folderName = getDriver().findElement(By.xpath("//span[text() = 'New folder']"));

        Assert.assertEquals(folderName.getText(), NAME_FOLDER);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder5Test extends BaseTest {

    private static final By DASHBOARD = By.xpath("//img[@id='jenkins-head-icon']");

    @Test
    public void testCreateNewFolder() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New folder");
        getDriver().findElement(By.xpath("//span[text() = 'Folder']/..")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(DASHBOARD).click();
        WebElement folderName = getDriver().findElement(By.xpath("//span[text() = 'New folder']"));

        Assert.assertEquals(folderName.getText(), "New folder");
    }
}

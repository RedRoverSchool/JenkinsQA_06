package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder8Test extends BaseTest {
    @Test
    public void creatFolderTest() {
        String name = "MyBalcon";

        WebElement creatajob = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a"));
        creatajob.click();

        WebElement folder = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]"));
        folder.click();

        WebElement input = getDriver().findElement(By.xpath("//*[@id='name']"));
        input.sendKeys(name);

        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]"));
        saveButton.click();

        WebElement dashButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashButton.click();

        WebElement actualresult = getDriver().findElement(By.xpath("//*[@id='job_" + name + "']/td[3]/a/span"));
        Assert.assertEquals(actualresult.getText(), name);
    }
}

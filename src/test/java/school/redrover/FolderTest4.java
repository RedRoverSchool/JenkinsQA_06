package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderTest4 extends BaseTest {

    private static final String NAME = "Nw project";
    @Test
    public void testCreateFolder() {
        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys(NAME);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']//div[@class='desc']")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']//button[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='breadcrumbs']//a")).click();

        WebElement nameFolder = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(nameFolder.getText(),NAME);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolder2Test extends BaseTest {
    private final String nameItem = "Test Folder";

    @Test
    public void testCreateFolder() {
        getDriver().findElement(By.cssSelector(".task-link")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(nameItem);
        getDriver().findElement(By.xpath("(//span[@class='label'])[4]")).click();
        getDriver().findElement(By.cssSelector(".btn-decorator")).click();

        getDriver().findElement(By.cssSelector("[name = 'Submit']")).click();

        getDriver().findElement(By.xpath("(//a[@class = 'model-link'])[2]")).click();

        WebElement nameOfFolder = getDriver().findElement(By.xpath("//a[@href = 'job/Test%20Folder/']"));
        String actualResult = nameOfFolder.getText();
        nameOfFolder.click();

        Assert.assertEquals(actualResult, nameItem);
        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), nameItem);
    }
}

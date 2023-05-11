package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolderTest_2 extends BaseTest {
    String nameItem = "Test Folder";

    @Test
    public void testCreateFolder() {
        WebElement newItem = getDriver().findElement(By.cssSelector(".task-link"));
        newItem.click();

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys(nameItem);
        WebElement categoryFolder = getDriver().findElement(By.xpath("(//span[@class='label'])[4]"));
        categoryFolder.click();
        WebElement okButton = getDriver().findElement(By.cssSelector(".btn-decorator"));
        okButton.click();

        getDriver().findElement(By.cssSelector("[name = 'Submit']")).click();

        WebElement breadcrumbsDashboard = getDriver().findElement(
                By.xpath("(//a[@class = 'model-link'])[2]"));
        breadcrumbsDashboard.click();

        WebElement nameOfFolder = getDriver().findElement(By.xpath("//a[@href = 'job/Test%20Folder/']"));
        String actualResult = nameOfFolder.getText();

        nameOfFolder.click();

        WebElement title = getDriver().findElement(By.cssSelector("#main-panel>h1"));

        Assert.assertEquals(actualResult, nameItem);
        Assert.assertEquals(title.getText(), nameItem);
    }
}

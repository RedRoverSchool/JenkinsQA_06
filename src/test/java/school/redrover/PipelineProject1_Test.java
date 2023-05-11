package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject1_Test extends BaseTest {
    @Test
    public void testCreatePipelineProject() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);

        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("project Alex");

        WebElement pipeline = getDriver().findElement(By.xpath("//span[normalize-space()='Pipeline']"));
        pipeline.click();

        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.sendKeys(Keys.RETURN);

        WebElement tabSave = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
        tabSave.sendKeys(Keys.RETURN);

        WebElement tabDashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        tabDashboard.sendKeys(Keys.RETURN);

        WebElement textPipeline = getDriver().findElement(By.xpath("//span[normalize-space()='project Alex']"));

        Assert.assertEquals(textPipeline.getText(), "project Alex");

    }
}

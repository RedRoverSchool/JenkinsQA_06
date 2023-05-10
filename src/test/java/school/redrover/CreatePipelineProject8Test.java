package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class CreatePipelineProject8Test extends BaseTest {
    @Test
    public void testNewItemSubmit() throws InterruptedException {
        WebElement newItemBtn = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='task-link-text' and .='New Item']")));
        newItemBtn.click();

        getDriver().findElement(By.cssSelector("#name")).sendKeys("NewName");
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        WebElement newNodeFileAdded = getDriver().findElement(By.xpath("//h1[contains(text(),'Project NewName')]"));
        Assert.assertEquals(newNodeFileAdded.getText(),"Project NewName");

    }
}

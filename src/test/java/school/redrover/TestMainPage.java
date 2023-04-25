package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class TestMainPage extends BaseTest {
    @Test
    public void testMainPage() {

        WebElement startElement = getDriver().findElement(By.xpath("//div/section[1]/h2[@class = 'h4']"));
        Assert.assertEquals(startElement.getText(), "Start building your software project");

        WebElement setElement = getDriver().findElement(By.xpath("//div/section[2]/h2[@class = 'h4']"));
        Assert.assertEquals(setElement.getText(), "Set up a distributed build");
    }
    @Test
    public void testInputNewDescription() {
        WebElement fieldDescription = getDriver().findElement(By.xpath("//*[@id=\"description-link\"]"));
        fieldDescription.click();
        WebElement textarea = new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea")));
        textarea.clear();
        textarea.sendKeys("RRSDevgeni Jenkins_06");
        WebElement buttonSave = getDriver().findElement(By.xpath("//*[@id='description']//button"));
        buttonSave.click();
        WebElement newDescription = new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#description > div:nth-child(1)")));
        Assert.assertEquals(newDescription.getText(), "RRSDevgeni Jenkins_06");
    }
}

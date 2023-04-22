package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class NewItemFreestyleProjectTest extends BaseTest {


    @Test
    public void createFreestyleTest(){

        String text = "FirstJob";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable
                (By.cssSelector(".jenkins-input"))).sendKeys(text);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("description")).sendKeys("PovytchikovForever");
        getDriver().findElement((By.name("Submit"))).click();

        getDriver().findElement(By.id("jenkins-name-icon")).click();
        String result =  getDriver().findElement(By.xpath("//td/a/span")).getText();

        Assert.assertEquals(text, result);
    }
}

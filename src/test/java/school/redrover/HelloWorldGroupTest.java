package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class HelloWorldGroupTest extends BaseTest{
    @Test
    public void testJenkinsVersion() {
        WebElement version = getDriver().findElement(By.xpath("//a[@rel='noopener noreferrer']"));
        Assert.assertEquals(version.getText(),"Jenkins 2.387.2");
    }

    @Test
    public void testDescriptionEdit(){
        WebElement descr = getDriver().findElement(By.xpath("//*[@id='description-link']"));
        descr.click();
        WebElement descrArea = getDriver().findElement(By.xpath("//div[@id='description']//textarea"));
        descrArea.sendKeys("hello");
        WebElement saveBtn = getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveBtn.click();
        WebElement descrText = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));
        Assert.assertEquals(descrText.getText(), "hello");
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProject3Test  extends BaseTest {
    @Test
    public void testCreateFreestyleProject(){
        final String testData = "Test";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(testData);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement projectPage = getDriver().findElement(By.xpath("//h1[normalize-space()='Project Test']"));
        Assert.assertEquals(projectPage.getText(), "Project " + testData);
    }

}

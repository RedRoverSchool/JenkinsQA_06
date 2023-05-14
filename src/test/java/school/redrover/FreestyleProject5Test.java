package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject5Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']")));
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("First Freestyle Project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@class='model-link']")).click();
        WebElement projectName =
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span"));

        Assert.assertEquals(projectName.getText(), "First Freestyle Project");
    }
}

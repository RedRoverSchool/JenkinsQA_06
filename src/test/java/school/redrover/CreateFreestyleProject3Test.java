package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProject3Test extends BaseTest {

    @Test
    public void createFreestyleProjectTest(){
        getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[1]/span[1]/a[1]")).click();

        WebElement nameField = getDriver().findElement(By.id("name"));
        nameField.click();
        nameField.sendKeys("FreestyleProject");

        getDriver().findElement(By.xpath("//span[contains(text(),'Freestyle project')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[contains(text(),'Project')]")).getText(), "Project FreestyleProject");
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipProjectTest extends BaseTest {

    @Test
    public void testCreatePipProject() {
        String expectedPipeline = "Pipeline Engineer";
        String expectedResult = "Engineer";

        getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='headline']")).getText(), expectedPipeline);

        getDriver().findElement(By.cssSelector("#breadcrumbBar > ol > li")).click();
        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer/']")).getText();

        Assert.assertEquals(actualResult, expectedResult);


    }
}

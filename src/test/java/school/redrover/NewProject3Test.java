package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewProject3Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String expectedResult = "Engineer2";

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer2");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();

        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer2/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreatePipProject() {
        String expectedPipeline = "Pipeline Engineer";
        String expectedResult = "Engineer";

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='headline']")).getText(), expectedPipeline);

        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer/']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateMultiConfigurationProject() {

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.cssSelector("input[id='name']")).sendKeys("Engineer3");
        getDriver().findElement(By.cssSelector("[value$='MatrixProject'] + span")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("li:nth-child(1) > a")).click();

        WebElement result = getDriver().findElement(By.cssSelector("#projectstatus"));
        Assert.assertTrue(result.isDisplayed(), "project no display");
    }


}

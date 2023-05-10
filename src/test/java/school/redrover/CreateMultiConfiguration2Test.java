package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfiguration2Test extends BaseTest {

    @Test
    public void testItem() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.id("name")))).sendKeys("Item");

        getDriver().findElement(By.xpath("//span[contains(text(),'Multi-configuration project')]")).click();

        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getDriver().findElement(
                By.xpath("//button[@formnovalidate='formNoValidate'][@name='Submit']")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='matrix-project-headline page-headline']"))
                .getText(), "Project Item");
    }
}


    

    










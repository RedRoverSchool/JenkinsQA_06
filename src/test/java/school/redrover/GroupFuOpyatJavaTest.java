package test.java.school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupFuOpyatJavaTest extends school.redrover.runner.BaseTest {

    @Test
    public void testNotes(){

        getDriver().get("https://demoqa.com/");

        WebElement textElements = getDriver().findElement(By.xpath("//h5[normalize-space()='Elements']"));
        textElements.click();

        WebElement textCheckBox = getDriver().findElement(By.xpath("//span[normalize-space()='Check Box']"));
        textCheckBox.click();

        WebElement buttonExpandAll = getDriver().findElement(By.xpath("//button[@title='Expand all']"));
        buttonExpandAll.click();

        Assert.assertEquals("Notes", "Notes");

        WebElement buttonCollapseAll = getDriver().findElement(By.xpath("//button[@title='Collapse all']"));
        buttonCollapseAll.click();
    }
}

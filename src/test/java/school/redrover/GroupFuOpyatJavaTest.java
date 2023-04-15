package test.java.school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupFuOpyatJavaTest extends school.redrover.runner.BaseTest {

    @Test
    public void testNotes(){

        getGetdriver().get("https://demoqa.com/");

        WebElement textElements = getGetdriver().findElement(By.xpath("//h5[normalize-space()='Elements']"));
        textElements.click();

        WebElement textCheckBox = getGetdriver().findElement(By.xpath("//span[normalize-space()='Check Box']"));
        textCheckBox.click();

        WebElement buttonExpandAll = getGetdriver().findElement(By.xpath("//button[@title='Expand all']"));
        buttonExpandAll.click();

        Assert.assertEquals("Notes", "Notes");

        WebElement buttonCollapseAll = getGetdriver().findElement(By.xpath("//button[@title='Collapse all']"));
        buttonCollapseAll.click();
    }
}

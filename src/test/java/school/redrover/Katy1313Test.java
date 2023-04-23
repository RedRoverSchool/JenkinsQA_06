package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Katy1313Test extends BaseTest {

    @Test
    public void testAddDescription() {

        WebElement adminDropDown = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        adminDropDown.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));

        Assert.assertTrue(textArea.isDisplayed());
    }
}

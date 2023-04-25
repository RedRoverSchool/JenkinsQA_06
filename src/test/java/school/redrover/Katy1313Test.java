package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Katy1313Test extends BaseTest {

    @Ignore
    @Test
    public void testAddDescription() {

        WebElement adminDropDown = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        adminDropDown.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));

        Assert.assertTrue(textArea.isDisplayed());
    }

    @Test
    public void testSaveDescription() {

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        addDescriptionButton.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        textArea.clear();
        textArea.sendKeys("Some text is here");
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        submitButton.click();
        WebElement description = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(description.getText(),"Some text is here");
    }
}

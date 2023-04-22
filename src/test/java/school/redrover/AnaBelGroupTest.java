package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AnaBelGroupTest extends BaseTest {

    @Test
    public void testLogo() {
        WebElement logo = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testAddDescription() {
        WebElement button = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        button.click();

        WebElement textField = getDriver().findElement(By.cssSelector(".jenkins-input"));
        textField.clear();
        textField.sendKeys("testDesctiprion1");

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement verify = getDriver().findElement(By.cssSelector("#description>div"));

        Assert.assertEquals(verify.getText(), "testDesctiprion1");
    }

}

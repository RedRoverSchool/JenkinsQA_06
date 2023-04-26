package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AnaBelGroupTest extends BaseTest {

    @Test
    public void testLogo() {
        WebElement logo = getDriver().findElement(By.id("jenkins-name-icon"));
        Assert.assertTrue(logo.isDisplayed());
    }
    @Ignore
    @Test
    public void testBuildHistory() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href ='/view/all/builds']"));

        Assert.assertEquals(buildHistory.getText(), "Build History");
    }

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

    @Test
    public void testItem() throws InterruptedException {
        WebElement button = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        button.click();

        WebElement textBox = getDriver().findElement(By.xpath("//input[@name='name']"));
        textBox.sendKeys("Prodjekt-Item");

        WebElement lablebutton = getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']"));
        lablebutton.click();

        WebElement Okbutton = getDriver().findElement(By.xpath("//*[@id='ok-button']"));
        Okbutton.click();

    }
}

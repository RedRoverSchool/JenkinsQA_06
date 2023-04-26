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
    public void testBuildHistory() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href ='/view/all/builds']"));

        Assert.assertEquals(buildHistory.getText(), "Build History");
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

    @Test
    public  void testCreateaJob() throws InterruptedException {
        WebElement button = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        button.click();

        Thread.sleep(1000);

        WebElement buttonProject =getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        buttonProject.click();

        WebElement errormessage = getDriver().findElement(By.xpath("//div[@class='input-validation-message']"));

        Assert.assertEquals(errormessage.getText(), "» This field cannot be empty, please enter a valid name");
    }
}

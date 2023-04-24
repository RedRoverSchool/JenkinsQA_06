package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class DreamTeamTest extends BaseTest {

    @Test
    public void testWelcomeToJenkinsPresent() {
        WebElement welcome = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/h1"));
        Assert.assertEquals(welcome.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testJenkinsMainPageLilia() {
        WebElement headerWelcome = getDriver().findElement(By.tagName("h1"));
        Assert.assertEquals(headerWelcome.getText(),"Welcome to Jenkins!");

        WebElement addDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescription.click();
        WebElement textBox = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textBox.sendKeys("Hello Jenkins!");
        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
    }
}


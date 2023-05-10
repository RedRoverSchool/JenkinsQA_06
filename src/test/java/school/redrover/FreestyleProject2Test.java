package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject2Test extends BaseTest {

    @Test
    public void testNewProject() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);

        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("First project");

        WebElement freestyleProject = getDriver().findElement(By.xpath
                ("//li[@class='hudson_model_FreeStyleProject']"));
        freestyleProject.sendKeys(Keys.RETURN);

        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.sendKeys(Keys.RETURN);

        WebElement tabSave = getDriver().findElement(By.xpath("//button[normalize-space()='Save']"));
        tabSave.sendKeys(Keys.RETURN);

        WebElement tabDashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        tabDashboard.sendKeys(Keys.RETURN);

        WebElement textProject = getDriver().findElement(By.xpath
                ("//span[normalize-space()='First project']"));

        Assert.assertEquals(textProject.getText(), "First project");

    }
}

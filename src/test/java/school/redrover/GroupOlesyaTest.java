package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupOlesyaTest extends BaseTest {


    @Test
    public void testInputHelpMsg() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();

        String expectedResultMsg = getDriver()
                .findElement(By.xpath("//div[@class = 'input-help']"))
                .getText();

        Assert.assertEquals(expectedResultMsg, "» Required field");
    }

    @Test
    public void createProjectTest() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.xpath("//span[text() ='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text()='Project Freestyle']")).getText(), "Project Freestyle");
    }
}

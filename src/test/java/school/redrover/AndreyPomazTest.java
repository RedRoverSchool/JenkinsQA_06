package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
public class AndreyPomazTest extends BaseTest {
    @Test
    public void testPeople() {
        WebElement people = getDriver().findElement(By.xpath("(//a[@href='/asynchPeople/'])[1]"));
        people.click();

        WebElement table = getDriver().findElement(By.xpath("(//table[@id='people'])[1]"));
        table.findElement(By.linkText("admin")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/user/admin/");
    }
    @Test
    public void testFreeStyleProject() {

        WebElement buttonNewJob = getDriver().findElement(By.xpath("(//a[@href='/view/all/newJob'])[1]"));
        buttonNewJob.click();

        WebElement input = getDriver().findElement(By.xpath("(//input[@id='name'])[1]"));
        input.sendKeys("Test");

        WebElement buttonFreeStyle = getDriver().findElement(By.xpath("(//span[normalize-space()='Freestyle project'])[1]"));
        buttonFreeStyle.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        Assert.assertEquals(getDriver().getCurrentUrl(),"http://localhost:8080/job/Test/configure");
    }
}

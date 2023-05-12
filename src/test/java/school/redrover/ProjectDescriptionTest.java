package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class ProjectDescriptionTest extends BaseTest {

    public void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer2");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs > li ")).click();
    }

    @Test
    public void testProjectDescription() {
        String expectedResult = "Test";
        createFreestyleProject();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement description = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        description.click();
        String string = "w";

        for(int i = 0; i < 10; i++) {
            string += string;
        }

        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys(string);
        WebElement submit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submit.click();

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).clear();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), expectedResult);
    }
}

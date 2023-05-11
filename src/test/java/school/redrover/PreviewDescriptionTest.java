package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class PreviewDescriptionTest extends BaseTest {

    public void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer2");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs > li ")).click();
    }

    @Test
    public void testPreviewDescription() {
        String expectedResult = "wwwww";
        createFreestyleProject();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).clear();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//a[text()='Preview']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).getText(), expectedResult);
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).isDisplayed());
    }
}

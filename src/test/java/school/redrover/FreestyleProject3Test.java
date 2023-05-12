package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class FreestyleProject3Test extends BaseTest {

    public void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
    }

    @Test
    public void testCreatedNewBuild() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        createFreestyleProject();

        getDriver().findElement(By.cssSelector("[href$='Engineer/']")).click();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getDriver().findElement(By.cssSelector("[href$='console']")).click();

        WebElement result = getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent"));

        Assert.assertTrue(result.isDisplayed());
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

    @Test
    public void testDeleteProjectFromTheDashboardList()  {
        String expectedResult = "Start building your software project";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        createFreestyleProject();

        Actions actions = new Actions(getDriver());

        WebElement projectButton = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        actions.moveToElement(projectButton).perform();
        WebElement dropdown = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a/button"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", dropdown);
        WebElement deleteProject = getDriver().findElement(By.xpath("//ul[@class='first-of-type']/li[5]"));
        js.executeScript("arguments[0].click();", deleteProject);
        getDriver().switchTo().alert().accept();

        String actualResult = getDriver().findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}

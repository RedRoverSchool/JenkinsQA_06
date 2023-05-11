package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject3Test extends BaseTest {

    public void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer2");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs > li ")).click();
    }

    @Test
    public void testDeleteProjectFromTheDashboardList() throws InterruptedException {
        String expectedResult = "Start building your software project";
        createFreestyleProject();
        Actions actions = new Actions(getDriver());

        WebElement projectButton = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        actions.moveToElement(projectButton).perform();
        getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a/button")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();
        getDriver().switchTo().alert().accept();

        String actualResult = getDriver().findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}

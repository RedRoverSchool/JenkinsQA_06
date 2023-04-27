package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class AlexTest extends BaseTest {
    @Test
    public void testAddMultiConfProject() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions action = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor)getDriver();

        WebElement dashBoard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        action.moveToElement(dashBoard).perform();
        WebElement dashBoardDropDown = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']/button"));
        action.click(dashBoardDropDown).perform();

        js.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//div[@id='breadcrumb-menu-target']//a[@href='/view/all/newJob']")));

        WebElement itemNameField = getDriver().findElement(By.xpath("//input[@id='name']"));
        itemNameField.sendKeys("Sample project");

        WebElement multiConfProject = getDriver().findElement(By.xpath("//span[normalize-space()='Multi-configuration project']"));
        multiConfProject.click();

        WebElement ok = getDriver().findElement(By.cssSelector("#ok-button"));
        ok.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='description']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@class='jenkins-toggle-switch__label ']")).isEnabled());
    }
}

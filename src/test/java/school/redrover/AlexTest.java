package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class AlexTest extends BaseTest {

    @Test
    public void testAddMultiConfProject() {
        Actions action = new Actions(getDriver());
        WebElement dashBoard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        action.moveToElement(dashBoard).build().perform();
        WebElement dashBoardDropDown = new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Dashboard']/button")));
        action.click(dashBoardDropDown).perform();


        WebElement addNewItem = new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='yui-gen1'] span")));
        addNewItem.click();

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

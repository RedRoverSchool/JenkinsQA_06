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
public class JavaJitsuGroupTest extends BaseTest {
    @Test
    public void testCheckingConfiguration() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropDownElement = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownElement).perform();

        WebElement configure = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Configure']//parent::a")));
        Actions actions = new Actions(getDriver());
        actions.click(configure).perform();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='_.description']")).isDisplayed());
    }
}

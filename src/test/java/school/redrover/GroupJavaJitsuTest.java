package school.redrover;

import school.redrover.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
public class GroupJavaJitsuTest extends BaseTest {
    @Test
    public void testCheckingConfiguration() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropDownEl = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownEl).perform();

        WebElement configure = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Configure']")));
        configure.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='_.description']")).isDisplayed());
    }
}

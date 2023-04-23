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

public class JavaNiSyGroupTest extends BaseTest {

    @Test
    public void testFullNameVerification(){
        Actions actions = new Actions(getDriver());

        WebElement dropBox = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@href='/user/admin']//button"))));
        actions.moveToElement(dropBox).click().build().perform();

        WebElement btnConfigure = getDriver().findElement(
                By.xpath("//span[text()='Configure']//parent::a"));
        actions.moveToElement(btnConfigure).click().build().perform();

        String inputFullName = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='_.fullName']"))).getAttribute("value"));
        getDriver().findElement(By.xpath("//input[@name='_.fullName']")).getAttribute("value");

        Assert.assertEquals(inputFullName, "admin");
    }
}


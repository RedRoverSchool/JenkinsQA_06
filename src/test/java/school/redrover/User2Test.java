package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class User2Test extends BaseTest {

    @Test
    public void testUserCreated() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='securityRealm/']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='addUser']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("anna");
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys("11");
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys("11");
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys("anna 1");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("anna@gmail.com");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='jenkins-table sortable']")));
        List<WebElement> listUsers = getDriver().findElements(By.xpath("//tr"));

        Assert.assertEquals(listUsers.size(), 3);
    }
}

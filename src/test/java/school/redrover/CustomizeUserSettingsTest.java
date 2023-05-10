package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CustomizeUserSettingsTest extends BaseTest  {

    @Test
    public void testUpdateUserName() throws InterruptedException {
        String testUser = "TestUser";
        String testUserName = "Test User";
        String newUser = "New User";
        String password = "password";
        String email = "testuser@test.com";

        getDriver().findElement(By.cssSelector("[href='/manage']")).click();
        getDriver().findElement(By.cssSelector("[href='securityRealm/']")).click();
        getDriver().findElement(By.cssSelector("[href='addUser']")).click();

        getDriver().findElement(By.cssSelector("#username")).sendKeys(testUser);
        getDriver().findElement(By.cssSelector("[name='password1']")).sendKeys(password);
        getDriver().findElement(By.cssSelector("[name='password2']")).sendKeys(password);
        getDriver().findElement(By.cssSelector("[name='fullname']")).sendKeys(testUserName);
        getDriver().findElement(By.cssSelector("[name='email']")).sendKeys(email);
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();

        getDriver().findElement(By.cssSelector("[href='/']")).click();
        getDriver().findElement(By.cssSelector("[href='/asynchPeople/']")).click();
        String oldactualresult = getDriver().findElement(By.cssSelector("#people>tbody>tr:nth-child(2)>td:nth-child(3)")).getText();
        Assert.assertEquals(oldactualresult, testUserName);

        getDriver().findElement(By.cssSelector("[href='/user/testuser/']")).click();
        getDriver().findElement(By.cssSelector("[href='/user/testuser/configure']")).click();

        WebElement newUserName = getDriver().findElement(By.cssSelector("[name='_.fullName']"));
        newUserName.clear();
        newUserName.sendKeys(newUser);

        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("[href='/asynchPeople/']")).click();

        String newactualresult = getDriver().findElement(By.cssSelector("#people>tbody>tr:nth-child(2)>td:nth-child(3)")).getText();
        Assert.assertEquals(newactualresult, newUser);
    }
}

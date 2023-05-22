package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ManageUsers3Test extends BaseTest {
    private final String USER_NAME = "Yulia";
    private final String PASSWORD = "Qwerty12345";
    private final String EMAIL = "Yulia@gmail.com";
    @Test
    public void testCreateNewUsers(){
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//*[@href='securityRealm/']/dl/dt")).click();
        getDriver().findElement(By.xpath("//*[@href='addUser']")).click();

        getDriver().findElement(By.id("username")).sendKeys(USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys(PASSWORD);
        getDriver().findElement(By.name("password2")).sendKeys(PASSWORD);
        getDriver().findElement(By.name("email")).sendKeys(EMAIL);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath
                ("//*[@href='user/" + USER_NAME.toLowerCase() + "/']")).getText(),USER_NAME);
    }
}

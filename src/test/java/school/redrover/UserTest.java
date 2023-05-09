package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class UserTest extends BaseTest {

        @Test
        public void testCreateUser() {
            WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
            manageJenkins.click();
            WebElement manageUser = getDriver().findElement(By.xpath("//dd[contains(text(),'Create')]"));
            manageUser.click();
            WebElement createUser = getDriver().findElement(By.xpath("//a[contains(text(),'Create')]"));
            createUser.click();
            WebElement userName = getDriver().findElement(By.id("username"));
            userName.sendKeys("1");

            WebElement password = getDriver().findElement(By.name("password1"));
            password.sendKeys("1");

            WebElement confirmPassword = getDriver().findElement(By.name("password2"));
            confirmPassword.sendKeys("1");

            WebElement fullName = getDriver().findElement(By.name("fullname"));
            fullName.sendKeys("1");

            WebElement email = getDriver().findElement(By.name("email"));
            email.sendKeys("1@gmail.com");

            WebElement createUserButton = getDriver().findElement(By.name("Submit"));
            createUserButton.click();

            Assert.assertEquals(getDriver().findElement(
                    By.xpath("//tbody/tr[2]/td[2]/a[1]")).getText(), "1");


        }
}

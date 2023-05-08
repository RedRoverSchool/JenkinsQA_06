package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class UserTest extends BaseTest {
    private static final String USERDATA = "admin";
        @Test
        public void testCreatingUser() throws InterruptedException {

            WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
            manageJenkins.click();

            WebElement manageUsers = getDriver().findElement(By.xpath("//dt[normalize-space()='Manage Users']"));
            manageUsers.click();

            WebElement createUser = getDriver().findElement(By.xpath("//a[normalize-space()='Create User']"));
            createUser.click();

            WebElement userName = getDriver().findElement(By.xpath("//input[@id='username']"));
            userName.sendKeys(USERDATA);

            WebElement password = getDriver().findElement(By.xpath("//input[@name='password1']"));
            password.sendKeys(USERDATA);

            WebElement confirmPassword = getDriver().findElement(By.xpath(" //input[@name='password2']"));
            confirmPassword.sendKeys(USERDATA);

            WebElement fullName = getDriver().findElement(By.xpath("//input[@name='fullname']"));
            fullName.sendKeys(USERDATA);

            WebElement email = getDriver().findElement(By.xpath("//input[@name='email']"));
            email.sendKeys("admin@gmail.com");


            WebElement createUserButton = getDriver().findElement(By.xpath("//button[normalize-space()='Create User']"));
            createUserButton.click();

            WebElement createdUser = getDriver().findElement(By.xpath(" //a[normalize-space()='admin']"));
            Assert.assertEquals(createdUser.getText(), USERDATA);
        }
    }



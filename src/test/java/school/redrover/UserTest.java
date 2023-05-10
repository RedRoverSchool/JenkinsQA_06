package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    private static final String USERDATA = "user1";
    private WebElement createdUserLink;

    @Test
    public void testCreatingUser() {

        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkins.click();

        WebElement manageUsers = getDriver().findElement(By.xpath("(//dt[normalize-space()='Manage Users'])[1]"));
        manageUsers.click();

        WebElement createUser = getDriver().findElement(By.xpath("//a[@href='addUser']"));
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
        email.sendKeys("user1@gmail.com");

        WebElement createUserButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        createUserButton.click();

        createdUserLink = getWait2().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[normalize-space()='" + USERDATA + "']")));
        Assert.assertTrue(createdUserLink.isDisplayed());

        // Delete the user via Bin

        WebElement binButton = getWait2().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'user/user1/delete') and contains(@class,'jenkins-table__button jenkins-!-destructive-color')]")));
        binButton.click();

        WebElement confirmYesButton = getDriver().findElement(By.xpath("//button[normalize-space()='Yes']"));
        confirmYesButton.click();

        try {
            createdUserLink.isDisplayed();
            Assert.fail("User link is still displayed");
        } catch (StaleElementReferenceException e) {
             }
        }
    }


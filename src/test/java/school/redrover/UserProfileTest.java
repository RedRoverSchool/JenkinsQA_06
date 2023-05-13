package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class UserProfileTest extends BaseTest {

    protected static final String username = "testuser";
    protected static final String password = "p@ssword123";
    protected static final String email = "test@test.com";
    protected static final String fullName = "Test User";

    public void createUser(String username, String password, String fullName, String email)  {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys(fullName);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testAddDescriptionToUser() {
        final String displayedDescriptionText = "Test User Description";

        createUser(username, password, fullName, email);

        getDriver().findElement(By.xpath("//a[contains(@href, '" + username + "')]")).click();
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();

        WebElement descriptionInputField = getDriver()
                .findElement(By.xpath("//textarea[@name='description']"));
        descriptionInputField.click();
        descriptionInputField.clear();
        descriptionInputField.sendKeys("Test User Description");
        getDriver().findElement(By.name("Submit")).click();

        String actualDisplayedDescriptionText = getDriver()
                .findElement(By.xpath("//div[@id='description']/div[1]")).getText();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
    }

    @Test(dependsOnMethods = {"testAddDescriptionToUser"})
    public void testUpdateDescriptionToUser() {
        final String displayedDescriptionText = "User Description Updated";
        final String existingDescriptionText = "Test User Description";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + username + "')]")).click();

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();

        WebElement descriptionInputField = getDriver()
                .findElement(By.xpath("//textarea[@name='description']"));
        descriptionInputField.click();
        descriptionInputField.clear();
        descriptionInputField.sendKeys(displayedDescriptionText);
        getDriver().findElement(By.name("Submit")).click();

        String actualDisplayedDescriptionText = getDriver()
                .findElement(By.xpath("//div[@id='description']/div[1]")).getText();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
        Assert.assertNotEquals(actualDisplayedDescriptionText, existingDescriptionText);
    }
}

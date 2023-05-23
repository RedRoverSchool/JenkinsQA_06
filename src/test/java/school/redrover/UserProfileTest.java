package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.CreateUserPage;
import school.redrover.model.StatusUserPage;
import school.redrover.runner.BaseTest;


public class UserProfileTest extends BaseTest {

    protected static final String USER_NAME = "testuser";
    protected static final String PASSWORD = "p@ssword123";
    protected static final String EMAIL = "test@test.com";
    protected static final String USER_FULL_NAME = "Test User";
    protected static final String USER_LINK = "//a[@href='user/" + USER_NAME + "/']";

    @Test
    public void testAddDescriptionToUser() {
        final String displayedDescriptionText = "Test User Description";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        getDriver().findElement(By.xpath(USER_LINK)).click();
        String actualDisplayedDescriptionText = new StatusUserPage(getDriver())
                .clickAddDescriptionLink()
                .clearDescriptionInputField()
                .setDescription(displayedDescriptionText)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
    }

    @Test(dependsOnMethods = {"testAddDescriptionToUser"})
    public void testEditDescriptionToUser() {
        final String displayedDescriptionText = "User Description Updated";
        final String existingDescriptionText = "Test User Description";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath(USER_LINK)).click();

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

    @Test
    public void testEditEmailByDropDown() {
        final String displayedEmail = "testedited@test.com";

        new CreateUserPage(getDriver()).createUser(USER_NAME, PASSWORD, USER_FULL_NAME, EMAIL);

        getDriver().findElement(
                By.xpath(USER_LINK + "/button[@class='jenkins-menu-dropdown-chevron']"))
                .sendKeys(Keys.ENTER);
        getDriver().findElement(By.xpath("//li[@class='yuimenuitem']/a")).click();

        WebElement emailInputField = getDriver()
                .findElement(By.xpath("//input[@name='email.address']"));
        String oldEmail = emailInputField.getAttribute("value");

        emailInputField.clear();
        emailInputField.sendKeys(displayedEmail);
        emailInputField.sendKeys(Keys.ENTER);

        getDriver().findElement(By.xpath("//a[@href='/user/" + USER_NAME + "/configure']")).click();

        String actualEmail = getDriver()
                .findElement(By.xpath("//input[@name='email.address']"))
                .getAttribute("value");

        Assert.assertNotEquals(actualEmail, oldEmail);
        Assert.assertEquals(actualEmail, displayedEmail);
    }
}

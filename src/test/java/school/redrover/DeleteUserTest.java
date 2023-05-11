package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;
import java.time.Duration;

public class DeleteUserTest extends BaseTest {

    private String USERNAME = RandomStringUtils.randomAlphabetic(10);
    private String PASSWORD = RandomStringUtils.randomAlphanumeric(10);
    private String FULL_NAME = RandomStringUtils.randomAlphabetic(10);
    private String EMAIL = USERNAME + "@gmail.com";
    private String username = "";
    private String password = "";
    private String email = "";

    public void createUser1() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/manage']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='securityRealm/']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='addUser']"))).click();

        getDriver().findElement(By.xpath("//input[@id='username']")).sendKeys(USERNAME);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys(FULL_NAME);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(EMAIL);
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button")).click();
    }

    @Test
    public void testDeleteUser() {
        createUser1();

        List<WebElement> listOfUsers = getDriver().findElements(By.xpath("//*[@class='jenkins-table__link model-link inside']"));

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='jenkins-table__button jenkins-!-destructive-color']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='Submit']"))).click();

        List<WebElement> listOfUsersAfterDelete = getDriver().findElements(By.xpath("//*[@class='jenkins-table__link model-link inside']"));
        Assert.assertNotEquals(listOfUsers, listOfUsersAfterDelete);
    }

    @Test
    public void testDeleteUserViaPeopleMenu() {
        createUser();

        getDriver().findElement(By.xpath("//*[@href='/asynchPeople/']")).click();

        WebElement userToDelete = getDriver().findElement(
                By.xpath("//a[@href='/user/" + username + "/']"));
        userToDelete.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOf(getDriver().findElement(By.id("main-panel"))));

        getDriver().findElement(By.xpath("//a[@href='/user/" + username + "/delete']")).click();

        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//*[@href='/asynchPeople/']")).click();

        Boolean isNotPresent = ExpectedConditions.not(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/user/" + username + "/']")))
                .apply(getDriver());
        Assert.assertTrue(isNotPresent);
    }

    @Test
    public void testDeleteUserViaManageUsersMenu() {
        createUser();

        getDriver().findElement(By.xpath("//*[@href='/manage']")).click();

        getDriver().findElement(By.xpath("//*[@href='securityRealm/']")).click();

        WebElement basketButton = getDriver().findElement(
                By.xpath("//a[@href='user/" + username + "/delete']"));
        basketButton.click();

        getDriver().findElement(By.name("Submit")).click();
        Boolean isNotPresent = ExpectedConditions.not(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/user/" + username + "/']")))
                .apply(getDriver());
        Assert.assertTrue(isNotPresent);
    }

    private void createUser() {
        username = "user" + Math.round((Math.random() * 1000));
        password = "" + Math.round(Math.random() * 10000);
        email = username + "@gmail.com";
        getDriver().findElement(By.xpath("//*[@href='/manage']")).click();

        getDriver().findElement(By.xpath("//*[@href='securityRealm/']")).click();

        getDriver().findElement(By.xpath("//*[@href='addUser']")).click();

        WebElement usernameInputField = getDriver().findElement(By.id("username"));
        usernameInputField.clear();
        usernameInputField.sendKeys(username);

        WebElement passwordInputField = getDriver().findElement(By.name("password1"));
        passwordInputField.clear();
        passwordInputField.sendKeys(password);

        WebElement passwordConfirmationField = getDriver().findElement(By.name("password2"));
        passwordConfirmationField.clear();
        passwordConfirmationField.sendKeys(password);

        WebElement emailInputField = getDriver().findElement(By.xpath("//*[@name='email']"));
        emailInputField.clear();
        emailInputField.sendKeys(email);

        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOf(getDriver().findElement(By.id("people"))));
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }
}


package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class DeleteUserTest extends BaseTest {
    private String username = "";
    private String password = "";
    private String email = "";

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

    private void createUser() {
        username = "user" + Math.round((Math.random() * 1000));
        password = "" + Math.round(Math.random()*10000);
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

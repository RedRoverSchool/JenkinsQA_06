package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class DeleteUserTest extends BaseTest {

    private String USERNAME = RandomStringUtils.randomAlphabetic(10);
    private String PASSWORD = RandomStringUtils.randomAlphanumeric(10);
    private String FULL_NAME = RandomStringUtils.randomAlphabetic(10);
    private String EMAIL = USERNAME + "@gmail.com";

    public void createUser() {
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
        createUser();

        List<WebElement> listOfUsers = getDriver().findElements(By.xpath("//*[@class='jenkins-table__link model-link inside']"));

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='jenkins-table__button jenkins-!-destructive-color']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='Submit']"))).click();

        List<WebElement> listOfUsersAfterDelete = getDriver().findElements(By.xpath("//*[@class='jenkins-table__link model-link inside']"));

        Assert.assertNotEquals(listOfUsers, listOfUsersAfterDelete);
    }
}

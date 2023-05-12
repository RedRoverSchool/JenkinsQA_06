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
        getDriver().findElement(By.xpath("//div[@id='main-panel']/section[3]/div/div[4]/a/dl")).click();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/div/div[2]/a")).click();

        getDriver().findElement(By.xpath("//input[@id='username']")).sendKeys(testUser);
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[2]/div[2]/input")).sendKeys(password);
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[3]/div[2]/input")).sendKeys(password);
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[4]/div[2]/input")).sendKeys(testUserName);
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[5]/div[2]/input")).sendKeys(email);
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']/div/button")).click();

        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]/a")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[2]/span/a")).click();
        String oldactualresult = getDriver().findElement(By.xpath("//tr[@id='person-TestUser']/td[3]")).getText();
        Assert.assertEquals(oldactualresult, testUserName);

        getDriver().findElement(By.xpath("//tr[@id='person-TestUser']/td[2]/a")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a")).click();

        WebElement newUserName = getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input"));
        newUserName.clear();
        newUserName.sendKeys(newUser);

        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']/div/button[1]")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();

        String newactualresult = getDriver().findElement(By.xpath("//tr[@id='person-TestUser']/td[3]")).getText();
        Assert.assertEquals(newactualresult, newUser);
    }
}
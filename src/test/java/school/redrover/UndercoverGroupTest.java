package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class UndercoverGroupTest extends BaseTest {

    @Test
    public void testCheckMainPage() {
        WebElement mainPageTitle = getDriver().findElement(By.xpath("//div[@class='empty-state-block'] /h1"));
        Assert.assertEquals(mainPageTitle.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testCheckLogOut() {
        WebElement logOutButton = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        logOutButton.click();

        WebElement mainPageTitle = getDriver().findElement(By.xpath("//*[@id='loginIntroDefault']/h1"));
        Assert.assertEquals(mainPageTitle.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testLogInWithNoData() {
        WebElement logOutButton = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        logOutButton.click();

        WebElement signInButton = getDriver().findElement(By.name("Submit"));
        signInButton.click();

        WebElement signInErrorMsg = getDriver().findElement(By.xpath("/html/body/div/div/form/div[1]"));
        Assert.assertEquals(signInErrorMsg.getText(), "Invalid username or password");
    }
}
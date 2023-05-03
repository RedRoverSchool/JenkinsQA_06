package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;


public class UndercoverGroupTest extends BaseTest {

    @Test
    public void testCheckMainPage() {
        WebElement mainPageTitle = getDriver().findElement(By.xpath("//div[@class='empty-state-block'] /h1"));
        Assert.assertEquals(mainPageTitle.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testLogOut() {
        WebElement logOutButton = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        logOutButton.click();

        getDriver().navigate().refresh();

        WebElement mainPageTitle = getDriver().findElement(By.xpath("//*[@id='loginIntroDefault']/h1"));
        Assert.assertEquals(mainPageTitle.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testLogInWithNoData() {
        WebElement logOutButton = getDriver().findElement(By.xpath("//a[@href='/logout']/*[@class='icon-md']"));
        logOutButton.click();

        WebElement usernameField = getDriver().findElement(By.xpath("//input[@placeholder='Username']"));
        Assert.assertEquals(usernameField.getText(), "");

        WebElement passwordField = getDriver().findElement(By.name("j_password"));
        Assert.assertEquals(passwordField.getText(), "");

        WebElement signInButton = getDriver().findElement(By.name("Submit"));
        signInButton.click();

        WebElement signInErrorMsg = getDriver().findElement(By.xpath("/html/body/div/div/form/div[1]"));
        Assert.assertEquals(signInErrorMsg.getText(), "Invalid username or password");
    }

    @Test
    public void testSearchBox() {
        String query = "test";

        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.RETURN);

        WebElement searchResultsTitle = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
        Assert.assertEquals(searchResultsTitle.getText(), String.format("Search for '%s'", query));
    }

    @Test
    public void testCreateNewItemWithUnsafeChar() {
        String[] unsafeChars = new String[]{"!", "#", "$", "%", "&", "\\", "*", "/", ":", ";", "<", ">", "?", "@"};

        for (String unsafeChar : unsafeChars) {
            getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]")).click();

            WebElement itemNameBox = getDriver().findElement(By.xpath("//*[@id='name']"));
            itemNameBox.sendKeys("a"); //test is unstable if both characters are sent at the same time
            itemNameBox.sendKeys(String.format("%s", unsafeChar));

            WebElement errorMsg = getDriver().findElement(By.xpath("//*[@id='itemname-invalid']"));
            Assert.assertEquals(errorMsg.getText(), String.format("» ‘%s’ is an unsafe character", unsafeChar));

            getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();

            getDriver().findElement(By.id("ok-button")).click();

            errorMsg = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
            Assert.assertEquals(errorMsg.getText(), "Error");

            errorMsg = getDriver().findElement(By.xpath("//*[@id='main-panel']/p"));
            Assert.assertEquals(errorMsg.getText(), String.format("‘%s’ is an unsafe character", unsafeChar));

            getDriver().findElement(By.xpath("//*[@id='jenkins-home-link']")).click();
        }
    }

    @Test
    public void testMessageOfRequiredItemName() {
        getDriver().findElement(By.xpath("//*[@href='newJob']")).click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        getDriver().findElement(By.xpath("//*[contains(text(), 'Freestyle')]")).click();

        WebElement message = getDriver().findElement(By.id("itemname-required"));
        Assert.assertTrue(message.isDisplayed());
    }
}
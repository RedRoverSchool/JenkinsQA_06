package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    public void testSearchForm() {
        String query = "test";

        WebElement searchForm = getDriver().findElement(By.id("search-box"));
        searchForm.sendKeys(query);
        searchForm.sendKeys(Keys.RETURN);

        WebElement searchResultsTitle = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
        Assert.assertEquals(searchResultsTitle.getText(), String.format("Search for '%s'", query));
    }
}
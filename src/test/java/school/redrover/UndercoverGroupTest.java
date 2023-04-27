package school.redrover;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
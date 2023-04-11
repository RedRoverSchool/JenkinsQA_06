package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupILoveBugsTest {
    @Test
    public void ADFirstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://guitar.com/");

        Thread.sleep(2000);

        WebElement latestNews = driver.findElement(By.xpath("//h2[@class='posts-block-title']"));

        Assert.assertEquals(latestNews.getText(), "LATEST NEWS");

        driver.quit();
    }

    @Test
    public void SteamTest(){
        final String MAIN_PAGE = "https://store.steampowered.com/";
        final By LOGIN_BUTTON = By.xpath("//a[@class='global_action_link']");
        final By SIGN_IN_BUTTON = By.cssSelector(".newlogindialog_SubmitButton_2QgFE");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        driver.get(MAIN_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), MAIN_PAGE, "The page opened is not 'store.steampowered.com' or the URL is invalid");

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();

        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        Assert.assertTrue(driver.findElement(SIGN_IN_BUTTON).isDisplayed(), "The bottom 'Sign In' is not displayed.");

        driver.quit();
    }
}

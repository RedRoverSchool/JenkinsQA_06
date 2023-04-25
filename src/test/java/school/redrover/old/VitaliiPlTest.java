package school.redrover.old;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class VitaliiPlTest {

    @Test
    public void steamHomePageTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*","--lang=eng",
                "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.get("https://store.steampowered.com/");


        WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.className("global_action_link")));
        signIn.click();


        WebElement loginText = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(@class, 'newlogindialog_PrimaryHeader_39uMK') and contains(text(), 'Sign in')]")));

        Assert.assertEquals(loginText.getText(), "SIGN IN");


        WebElement inputLogin = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[text()='Sign in with account name']/following-sibling::input")));
        inputLogin.sendKeys("login");
        WebElement inputPassword = driver.findElement(By.xpath("//div[text()='Password']/following-sibling::input"));
        inputPassword.sendKeys("password");
        WebElement subbmit = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='newlogindialog_SignInButtonContainer_14fsn']/child::button")));
        subbmit.click();


        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(@class,'newlogindialog_FormError_1Mcy9') and contains(text(), 'Please check your password and account name and try again.')]")));

        Assert.assertEquals(error.getText(),
                "Please check your password and account name and try again.");

        driver.quit();

    }
}
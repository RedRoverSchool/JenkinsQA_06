package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VitaliiPlTest {

    @Test
    public void steamHomePageTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*",
                "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://store.steampowered.com/");

        WebElement signIn = driver.findElement(By.className("global_action_link"));
        signIn.click();

        Thread.sleep(5000);

        WebElement loginText = driver.findElement(By.linkText("Войти"));

        Assert.assertEquals(loginText.getText(), "Войти");

        WebElement inputLogin = driver.findElement(By.xpath("//div[text()='Войти, используя имя аккаунта']/following-sibling::input"));
        inputLogin.sendKeys("login");
        WebElement inputPassword = driver.findElement(By.xpath("//div[text()='Пароль']/following-sibling::input"));
        inputPassword.sendKeys("password");
        WebElement subbmit = driver.findElement(By.xpath("//div[@class='newlogindialog_SignInButtonContainer_14fsn']/child::button"));
        subbmit.click();

        Thread.sleep(3000);

        WebElement error = driver.findElement(By.xpath("//div[@class='newlogindialog_FormError_1Mcy9']"));

        Assert.assertEquals(error.getText(), "Пожалуйста, проверьте свой пароль и имя аккаунта и попробуйте снова.");

        driver.quit();

    }





}

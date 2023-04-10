package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VidimTest {


    @Test
    public void checkWrongEmail() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://redrover.school");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15000));
        WebElement but = driver.findElement(By.xpath("//*[@id=\"rec544122398\"]/div/div/div[3]/a"));
        but.click();
        WebElement email = driver.findElement(By.xpath("//*[@id=\"form544122415\"]/div[2]/div[1]/div/input"));
        email.sendKeys("vidim@gmail.");
        WebElement name = driver.findElement(By.xpath("//*[@id=\"form544122415\"]/div[2]/div[2]/div/input"));
        name.sendKeys("Vitalii");
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"form544122415\"]/div[2]/div[3]/div/label/div"));
        checkbox.click();

        WebElement button = driver.findElement(By.cssSelector("#form544122415 > div.t-form__inputsbox > div.tn-form__submit > button"));
        button.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"tilda-popup-for-error\"]/div[1]/p"));

        Assert.assertEquals(errorMessage.getText(), "Please enter a valid email address");
        //Assert.assertEquals(errorMessage.getText(), "Укажите, пожалуйста, корректный email");
        driver.quit();
    }
}

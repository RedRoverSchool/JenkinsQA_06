package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class IolantaFTest {

    @Test
    public void SeleniumPageCheckedTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("selenium\n");

        WebElement text = driver.findElement(By.xpath("//h3[text()  = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");

        driver.quit();
    }


    @Test
    public void FirstProjectTest(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://101hotels.com/");

        WebElement checkBox = driver.findElement(By.id("destination"));

        checkBox.sendKeys("москва\n");

        WebElement textBox = driver.findElement(By.xpath("//h1[text()  = 'Результаты поиска']"));

        Assert.assertEquals(textBox.getText(), "Результаты поиска");

        driver.quit();
    }

    @Test
    public void SecondProjectTest(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://101hotels.com/");

        WebElement checkBox = driver.findElement(By.id("destination"));

        checkBox.sendKeys("moscow\n");

        WebElement textBox = driver.findElement(By.xpath("//h1[text()  = 'Результаты поиска']"));

        Assert.assertEquals(textBox.getText(), "Результаты поиска");

        driver.quit();
    }
}

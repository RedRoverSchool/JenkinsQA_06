package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IrinaETest {

    @Test
       public void testMyFirst(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://www.clouds-travel.ru/");

        WebElement textBox = driver.findElement(By.xpath("//h4[text() = 'Экскурсии Ярославль']"));

        textBox.click();

        WebElement textBox1 = driver.findElement(By.xpath("//h2[text() = 'Прием групп и индивидуальных туристов в Ярославле']"));

        Assert.assertEquals(textBox1.getText(), "Прием групп и индивидуальных туристов в Ярославле");

        driver.quit();

    }
}

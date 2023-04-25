package school.redrover.old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
    public class TestSalary {
        @Ignore
        @Test
        public void testSalary() throws InterruptedException {

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

            WebDriver driver = new ChromeDriver(chromeOptions);
            //driver.manage().window().maximize();

            driver.get("https://mybuh.kz/useful/calc/");

            String title = driver.getTitle();
            Assert.assertEquals(title, "Онлайн калькулятор расчета зарплаты и налогов по заработной плате для ТОО на общеустановленном режиме - ОПВ, СО, СН, ИПН, ОСМС");

            WebElement closeBtn = driver.findElement(By.className("webinar-modal__close")); //can not find the element
            Thread.sleep(1000);
            closeBtn.click();
            Thread.sleep(1000);

            WebElement textBox = driver.findElement(By.className("input-xs-calc"));


            textBox.sendKeys("173500");
            Thread.sleep(1000);
            WebElement submitButton = driver.findElement(By.className("btn-anchor"));
            submitButton.click();
            Thread.sleep(1000);
            WebElement message = driver.findElement(By.className("calculator-total__title"));
            String value = message.getText();
            Assert.assertEquals(value, "Итого");

            driver.quit();
        }
    }


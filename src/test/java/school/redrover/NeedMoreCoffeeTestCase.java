package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class NeedMoreCoffeeTestCase {

        @Test
        public void test_006() throws InterruptedException {
            ChromeOptions chromeOptions = new ChromeOptions(); // это опции для запуска на сервеере
            chromeOptions.addArguments("--headless", "--window-size=1920,1080"); //чтобы не открывалось окно браузера и с одним размером окна. Потому что на сервере не установлен этот браузер. Тест упадет, если оставить открытие браузера

            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.get("https://askomdch.com/");
            assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
            Thread.sleep(1000);
            String textBox = driver.findElement(By.xpath("//h2[@class='has-text-align-center']")).getText();
            Thread.sleep(500);
            assertEquals(textBox, "Featured Products");
            List<WebElement> products = driver.findElements(By.className("type-product"));
            assertEquals(products.size(), 5);
            //driver.findElement(By.xpath("//li[@class='ast-article-single product type-product post-1215 status-publish first instock product_cat-men product_cat-womens-shoes has-post-thumbnail featured taxable shipping-taxable purchasable product-type-simple']"));
            driver.quit();
        }
    @Test
    public void testBozhok() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions(); // это опции для запуска на сервеере
        chromeOptions.addArguments("--headless", "--window-size=1920,1080"); //чтобы не открывалось окно браузера и с одним размером окна. Потому что на сервере не установлен этот браузер. Тест упадет, если оставить открытие браузера

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();

    }
    }


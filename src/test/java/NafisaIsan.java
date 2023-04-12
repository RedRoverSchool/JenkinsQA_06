import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NafisaIsan {

        @Test
        public void test_006() throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://askomdch.com/");
            Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
            Thread.sleep(1000);
            String textBox = driver.findElement(By.xpath("//h2[@class='has-text-align-center']")).getText();
            Thread.sleep(500);
            Assert.assertEquals(textBox, "Featured Products");
            List<WebElement> products = driver.findElements(By.className("type-product"));
            Assert.assertEquals(products.size(), 5);
            //driver.findElement(By.xpath("//li[@class='ast-article-single product type-product post-1215 status-publish first instock product_cat-men product_cat-womens-shoes has-post-thumbnail featured taxable shipping-taxable purchasable product-type-simple']"));
            driver.quit();
        }
    }


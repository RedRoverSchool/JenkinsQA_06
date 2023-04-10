import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    @Test
    public void testSalary() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        //driver.manage().window().maximize();

        driver.get("https://mybuh.kz/useful/calc/");

        String title = driver.getTitle();
        Assert.assertEquals(title, "Онлайн калькулятор расчета зарплаты и налогов по заработной плате для ТОО на общеустановленном режиме - ОПВ, СО, СН, ИПН, ОСМС");

        WebElement closeBtn = driver.findElement(By.className("webinar-modal__close"));
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


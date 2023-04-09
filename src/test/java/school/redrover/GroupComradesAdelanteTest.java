package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupComradesAdelanteTest {

    @Test

    public void MariaLuchnikovaTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement login = driver.findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Thread.sleep(3000);

        WebElement Backpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Backpack.click();

        WebElement tShirt = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        tShirt.click();

        WebElement onesie = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        onesie.click();

        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        cart.click();

        List<WebElement> items = driver.findElements(By.className("cart_item"));

        int countItems = items.size();
        Assert.assertEquals(countItems, 3);

        driver.quit();

    }

}

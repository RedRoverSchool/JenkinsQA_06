package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RockstarTest {
    @Test
    public void addToCartTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement passField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));

        loginField.sendKeys("standard_user");
        Thread.sleep(1000);
        passField.sendKeys("secret_sauce");
        Thread.sleep(1000);
        loginButton.click();

        WebElement backPackAddToCart = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        WebElement cartButton = driver.findElement(By.xpath("//*[@class=\"shopping_cart_link\"]"));
        Thread.sleep(1000);
        backPackAddToCart.click();
        Thread.sleep(1000);
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"checkout\"]"));
        checkoutButton.click();

        WebElement firstName = driver.findElement(By.xpath("//input[@data-test=\"firstName\"]"));
        WebElement lastName = driver.findElement(By.xpath("//input[@data-test=\"lastName\"]"));
        WebElement code = driver.findElement(By.xpath("//input[@data-test=\"postalCode\"]"));
        WebElement continueButton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));

        Thread.sleep(2000);
        firstName.sendKeys("test");
        Thread.sleep(1000);
        lastName.sendKeys("test");
        Thread.sleep(1000);
        code.sendKeys("test");
        Thread.sleep(1000);
        continueButton.click();

        WebElement finish = driver.findElement(By.xpath("//button[@class=\"btn btn_action btn_medium cart_button\"]"));
        Thread.sleep(1000);
        finish.click();

        WebElement complete = driver.findElement(By.xpath("//h2[@class=\"complete-header\"]"));
        String completeText = complete.getText();
        String message = "Thank you for your order!";
        Assert.assertEquals(completeText,message);

        driver.quit();
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupOlesyaTests {
    @Test
    public void nsergeevaTest (){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

         WebDriver driver = new ChromeDriver(chromeOptions);
         driver.get("https://www.saucedemo.com/inventory.html");

         WebElement username = driver.findElement(By.name("user-name"));
         WebElement password = driver.findElement(By.name("password"));
         WebElement loginButton = driver.findElement(By.name("login-button"));

         username.sendKeys("standard_user");
         password.sendKeys("secret_sauce");
         loginButton.click();

         WebElement addtocartButton = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
         addtocartButton.click();

         WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
         Assert.assertEquals(removeButton.getText(),"Remove");

         WebElement cartbutton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
         cartbutton.click();
         Assert.assertEquals(driver.getTitle(), "Swag Labs");

         WebElement cartQuantity = driver.findElement(By.xpath("//*[@class=\"cart_quantity\"]"));

            //removing backpack from the cart:
         WebElement cartremovebutton = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
         cartremovebutton.click();
         Assert.assertEquals(driver.findElements(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).isEmpty(), true);

         driver.quit();
    }
}

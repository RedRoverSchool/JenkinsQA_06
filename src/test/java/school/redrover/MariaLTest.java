package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class MariaLTest extends BaseTest {
    @Test
    public void testCountItemsOfCart() throws InterruptedException {

        getDriver().get("https://www.saucedemo.com");

        WebElement login = getDriver().findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement password = getDriver().findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        Thread.sleep(3000);

        WebElement Backpack = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Backpack.click();

        WebElement tShirt = getDriver().findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        tShirt.click();

        WebElement onesie = getDriver().findElement(By.id("add-to-cart-sauce-labs-onesie"));
        onesie.click();

        WebElement cart = getDriver().findElement(By.className("shopping_cart_link"));
        cart.click();

        List<WebElement> items = getDriver().findElements(By.className("cart_item"));

        int countItems = items.size();
        Assert.assertEquals(countItems, 3);
    }
}

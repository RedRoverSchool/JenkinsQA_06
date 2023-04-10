package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void checkPriceLowToHigh() { //Stoyana's Test

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driver.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driver.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText("Price (low to high)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);

        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        driver.quit();
    }

    @Test
    public void checkPriceHighToLow() { //Stoyana's Test

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driver.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driver.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText("Price (high to low)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);
        Collections.reverse(beforeFilterPriceList); //reverse the sorted list

        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        driver.quit();
    }
}

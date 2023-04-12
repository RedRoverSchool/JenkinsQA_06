package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SchoolWebTest {
    String[] links = {"Courses", "Training", "About school", "Teachers"};

    @Test
    public void testCopyright() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://redrover.school");

        for (String link : links) {
            driver.findElement(new By.ByLinkText(link)).click();
            Thread.sleep(3000);
            String copyright = driver.findElement(By.xpath("//*[contains(text(), 'Copyright ©')]")).getText();
            Assert.assertEquals(copyright, "Copyright © 2022 RedRover School. All rights reserved");
        }

        driver.quit();
    }

    @Test
    public void testBlingNoExtra() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://blingrus.azurewebsites.net/");

        Thread.sleep(1000);

        //Empty cart if something is there
        WebElement cartState = driver.findElement(By.xpath("//span[@id = 'cart-counter']"));
        if (!cartState.getText().equals("0")) {
            driver.findElement(By.linkText("Empty cart")).click();
        }

        driver.findElement(By.linkText("Store")).click();

        Thread.sleep(1000);

        String item = "The Bling Ring";

        WebElement parent = driver.findElement(By.xpath("//*[contains(text(), '"+item+"')]/ancestor::li[1]"));
        WebElement form = parent.findElement(By.xpath("//input[@placeholder = 'Add your personal message to be engraved']"));
        form.sendKeys("1234567890");

        Thread.sleep(1000);

        parent.findElement(By.xpath("//button[@data-action = 'AddToCart']")).click();

        Thread.sleep(1000);

        WebElement modal = driver.findElement(By.xpath("//div[@class = 'modal-dialog']"));
        modal.findElement(By.xpath("//button[@class = 'close']")).click();

        Thread.sleep(1000);

        cartState = driver.findElement(By.xpath("//span[@id = 'cart-counter']"));
        System.out.println(cartState.getText());

        driver.quit();
    }
}

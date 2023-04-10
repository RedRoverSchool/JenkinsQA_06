package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AnaBelTest {
    String url = "https://openweathermap.org";
    @Ignore
    @Test
    public void testTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url + "/guide");
        String title = driver.getTitle();
        Assert.assertEquals(title,"OpenWeatherMap API guide - OpenWeatherMap");

        driver.quit();
    }
    @Ignore
    @Test
    public void testFaringeights() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url + "/city/2643743");
        WebElement switcher = driver.findElement(By.xpath("//div[@class='option'][2]"));
        Thread.sleep(6000);
        switcher.click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='heading']")).getText().contains("F"));

        driver.quit();
    }
    @Ignore
    @Test
    public void testCookies() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);
        String footer = driver.findElement(By.xpath("//p[contains(text(),'We use cookies which are "
                + "essential for the site to work. We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.')]"))
                .getText();
        Assert.assertEquals(footer, "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.");

        driver.quit();
    }
}

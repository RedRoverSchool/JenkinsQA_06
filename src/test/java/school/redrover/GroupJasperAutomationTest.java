package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
 public class GroupJasperAutomationTest {
    @Test
    public void footballua () throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://football.ua/");
        Thread.sleep(3000);

        WebElement textBox = driver.findElement(By.id("searchInput"));
        Thread.sleep(2000);
        textBox.sendKeys("Реал Мадрид");
        Thread.sleep(2000);


        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);



        WebElement text = driver.findElement(By.linkText("Реал Мадрид"));
        Assert.assertEquals(text.getText(), "Реал Мадрид");

        driver.quit();
    }
    @Test
    public void testTitle() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://football.ua/");
        Thread.sleep(3000);

        String title = driver.getTitle();
        Assert.assertEquals(title, "Football.ua - Новости футбола - Футбол онлайн - Результаты матчей, трансляции — football.ua");

        driver.quit();
    }
     @Test
     public void testBiletskayaA() throws InterruptedException{

         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--headless", "--window-size=1920,1080");

         WebDriver driver = new ChromeDriver(chromeOptions);
         driver.get("https://new.uschess.org/");

         WebElement textLink = driver.findElement(By.className("clo-image"));
         textLink.click();

         Thread.sleep(2000);

         WebElement element = driver.findElement(By.xpath("//*[text()='Search Articles']"));
         Assert.assertEquals(element.getText(), "Search Articles");

         driver.quit();
     }

     @Test
     public void testBUshakov() throws InterruptedException{

         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--headless", "--window-size=1920,1080");

         WebDriver driver = new ChromeDriver(chromeOptions);
         driver.get("https://privatbank.ua/");

         WebElement textLink = driver.findElement(By.xpath("//a[contains(text(), 'Відділення')]"));
         textLink.click();

         Assert.assertEquals(driver.getCurrentUrl(), "https://privatbank.ua/map");

         driver.quit();
     }

}

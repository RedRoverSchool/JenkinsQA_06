package school.redrover.old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class KirilldkoTest {

    @Test

    public void testSecond () throws InterruptedException {
        // опции для chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        // --headless - команда запуска браузера в операционной системе, без запуска ока
        // --window-size=1920,1080 размер онка
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        String url = "https://openweathermap.org/";
        // передаем опции
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);
        //Thread.sleep(5000);
        driver.close();


    }

}

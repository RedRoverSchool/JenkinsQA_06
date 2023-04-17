package school.redrover.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private WebDriver driver;
    private Integer resolutionX;
    private Integer resolutionY;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();

        if (resolutionX == null || resolutionY == null){
            chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        } else {
            chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=" + resolutionX + "," + resolutionY);
        }

        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    protected void afterMethod() {
        driver.quit();
    }

    protected void setResolution(Integer resolutionX,Integer resolutionY) {
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
    }
    protected WebDriver getDriver() {
        return driver;
    }
}

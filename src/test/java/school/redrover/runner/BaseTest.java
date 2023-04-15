package school.redrover.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private WebDriver getdriver;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        getdriver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    protected void afterMethod() {
        getdriver.quit();
    }

    protected WebDriver getGetdriver() {
        return getdriver;
    }
}

package school.redrover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class TestFindTitle extends BaseTest {

    @Test
    public  void testYandex() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        getDriver().get("https://yandex.ru");

        Assert.assertEquals(getDriver().getTitle(),"Дзен");
        getDriver().quit();
    }
}

package school.redrover.old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
@Ignore
public class NumTest extends BaseTest {

    @Test
    public  void wikiTest() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        getDriver().get("https://wikipedia.org");

        Assert.assertEquals(getDriver().getTitle(),"Wikipedia");
        getDriver().quit();
    }
}

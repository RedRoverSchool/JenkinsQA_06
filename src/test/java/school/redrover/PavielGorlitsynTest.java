package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;


public class PavielGorlitsynTest extends BaseTest {
    String expectedURL= "https://www.youtube.com/";
    String actualURL;

    @Test
    public void testYoutubeSearch() throws InterruptedException {

        getDriver().get("https://www.google.com/");

        WebElement textBox= getDriver().findElement(By.xpath("//*[@name= 'q']"));
        textBox.sendKeys("Youtube");

        WebElement search= getDriver().findElement(By.xpath("//*[@name= 'btnK']"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        search.click();

        WebElement header =getDriver().findElement(By.xpath("//h3[@class=\"LC20lb MBeuO DKV0Md\"]"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        header.click();
        Thread.sleep(3000);

        actualURL= getDriver().getCurrentUrl();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        Assert.assertEquals(actualURL,expectedURL);
    }
}

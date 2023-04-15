package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AnaBelTest extends BaseTest {
    String url = "https://openweathermap.org";

    @Ignore
    @Test
    public void testTitle() {

        getGetdriver().get(url + "/guide");
        String title = getGetdriver().getTitle();
        Assert.assertEquals(title,"OpenWeatherMap API guide - OpenWeatherMap");
    }
    @Ignore
    @Test
    public void testFaringeights() throws InterruptedException {

        getGetdriver().get(url + "/city/2643743");
        WebElement switcher = getGetdriver().findElement(By.xpath("//div[@class='option'][2]"));
        Thread.sleep(6000);
        switcher.click();
        Assert.assertTrue(getGetdriver().findElement(By.xpath("//span[@class='heading']")).getText().contains("F"));
    }
    @Ignore
    @Test
    public void testCookies() {

        getGetdriver().get(url);
        String footer = getGetdriver().findElement(By.xpath("//p[contains(text(),'We use cookies which are "
                + "essential for the site to work. We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.')]"))
                .getText();
        Assert.assertEquals(footer, "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.");
    }
}

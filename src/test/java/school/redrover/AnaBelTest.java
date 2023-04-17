package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import org.openqa.selenium.support.ui.Select;

public class AnaBelTest extends BaseTest {
    String url = "https://openweathermap.org";

    @Ignore
    @Test
    public void testTitle() {

        getDriver().get(url + "/guide");
        String title = getDriver().getTitle();
        Assert.assertEquals(title,"OpenWeatherMap API guide - OpenWeatherMap");
    }
    @Ignore
    @Test
    public void testFaringeights() throws InterruptedException {

        getDriver().get(url + "/city/2643743");
        WebElement switcher = getDriver().findElement(By.xpath("//div[@class='option'][2]"));
        Thread.sleep(6000);
        switcher.click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[@class='heading']")).getText().contains("F"));
    }
    @Ignore
    @Test
    public void testCookies() {

        getDriver().get(url);
        String footer = getDriver().findElement(By.xpath("//p[contains(text(),'We use cookies which are "
                + "essential for the site to work. We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.')]"))
                .getText();
        Assert.assertEquals(footer, "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.");
    }

    @Test
    public void testSimpleCalculator() {
        getDriver().get("https://testpages.herokuapp.com/styled/calculator");

        WebElement num1 = getDriver().findElement(By.id("number1"));
        num1.sendKeys("5");
        WebElement num2 = getDriver().findElement(By.id("number2"));
        num2.sendKeys("5");
        Select function = new Select(getDriver().findElement(By.id("function")));
        function.selectByVisibleText("plus");
        getDriver().findElement(By.id("calculate")).click();
        String answer = getDriver().findElement(By.id("answer")).getText();

        Assert.assertEquals(answer, "10");
    }
}

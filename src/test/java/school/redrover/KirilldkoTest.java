package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class KirilldkoTest extends BaseTest {

    static String url = "https://openweathermap.org/";
    @Test
    public void testTitle () throws InterruptedException {
        String expectedUrl = "https://openweathermap.org/guide";
        String expectTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(5000);
        WebElement GuideMenuItem = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text() = 'Guide']")
        );
        //2. Нажимаем на кнопку Guide
        GuideMenuItem.click();
        Thread.sleep(3000);

        //3. Подтвердить, что мы перешли на нужную страницу
        String currenUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currenUrl, expectedUrl);

        //4. Подтвердить схожеть Title
        String currentTitle = getDriver().getTitle();
        Assert.assertEquals(currentTitle, expectTitle);

    }

    @Test
    public void testFahrenheitButton() throws InterruptedException {
        String Fahrenheit = "F";
        Boolean expectR = true;

        getDriver().get(url);
        Thread.sleep(5000);
        WebElement ImperialF = getDriver().findElement(
                By.xpath("//div[@class = 'switch-container']/div[text() = 'Imperial: °F, mph']")
        );
        ImperialF.click();
        Thread.sleep(3000);
        WebElement TestWeatherInFahrenheit = getDriver().findElement(
                By.xpath("//span [@class = 'heading']")
        );
        Boolean currentR = TestWeatherInFahrenheit.getText().contains(Fahrenheit);
        Assert.assertEquals(currentR, expectR);

    }
    @Test
    public void testCookiesPopUpWindow () throws InterruptedException {
        String downPanelText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectButtonAllowAll = "Allow all";
        String expectButtonManageCookies = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(5000);
        WebElement testDownPanelText = getDriver().findElement(
                By.xpath("//p[@class ='stick-footer-panel__description']")
        );

        String corrTextDownPanel = testDownPanelText.getText();
        Assert.assertEquals(downPanelText, corrTextDownPanel);

        // button Allow all
        WebElement testButtonAllowAll = getDriver().findElement(
                By.xpath("//button[text() = 'Allow all'] ")
        );
        String currTestButtonAllowAll = testButtonAllowAll.getText();
        Assert.assertEquals(currTestButtonAllowAll, expectButtonAllowAll);

        //expectButtonManageCookies
        WebElement testButtonManageCookies = getDriver().findElement(
                By.xpath("//a[text() = ' Manage cookies ']")
        );
        String currTestButtonManageCookies = testButtonManageCookies.getText();
        Assert.assertEquals(currTestButtonManageCookies, expectButtonManageCookies);

    }

}

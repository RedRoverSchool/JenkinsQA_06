package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import static org.testng.Assert.assertEquals;

public class GroupAndreyTest extends BaseTest {
    @Test
    public void testAndreyTitle() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement button = getDriver().findElement(By.xpath("//*[@id=\"desktop-menu\"]/ul/li[1]/a"));
        button.click();

        assertEquals(getDriver().getTitle(), "OpenWeatherMap API guide - OpenWeatherMap");
    }

    @Test
    public void testArtemTextOnSuggestsButton() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement suggestButton = getDriver().findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        WebElement suggestButton2 = getDriver().findElement(By.xpath("//*[@id=\"forecast_list_ul\"]/table/tbody/tr[2]/td[2]/b[1]/a"));

        assertEquals(suggestButton.getText(), "Omsk, RU");
        assertEquals(suggestButton2.getText(), "Omskaya Oblast’, RU");
    }

    @Test
    public void testArtemTextOnCityPage() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement button = getDriver().findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        assertEquals(button.getText(), "Omsk, RU");
        button.click();

        Thread.sleep(5000);

        WebElement text = getDriver().findElement(By.xpath("//*[@class=\"grid-container grid-4-5\"]/div/div/h2"));

        assertEquals(text.getText(), "Omsk, RU");
    }
    @Ignore
    @Test
    public void testArtemLocalDateOnCityPage() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement button = getDriver().findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        assertEquals(button.getText(), "Omsk, RU");
        button.click();

        Thread.sleep(5000);

        WebElement text = getDriver().findElement(By.xpath("//*[@class=\"grid-container grid-4-5\"]/div/div/span"));

        Date currentDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM d, hh:mma", Locale.US);
        formatDate.setTimeZone(TimeZone.getTimeZone("GMT+06"));
        String expectedDate = formatDate.format(currentDate).replace("AM", "am").replace("PM", "pm");

        assertEquals(text.getText(), expectedDate);
    }

    @Test
    public void testArtemSliderMove() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement slider = getDriver().findElement(By.xpath("/html/body/main/div/form/div/div[3]/label[3]/input"));

        Actions move = new Actions(getDriver());
        move.moveToElement(slider).clickAndHold().moveByOffset(25, 0).release().perform();

        Thread.sleep(3500);

        WebElement button = getDriver().findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
        button.click();

        Thread.sleep(3500);

        String url = getDriver().getCurrentUrl();
        int expected;
        if (url.contains("my-range=6")) {
            expected = 1;
        } else {
            expected = 2;
        }

        assertEquals(expected, 1);
    }

    @Ignore
    @Test
    public void testAndreyGuideTitle() throws InterruptedException {
        getDriver().get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement button = getDriver().findElement(By.xpath("//*[@id=\"desktop-menu\"]/ul/li[1]/a"));
        button.click();

        WebElement title = getDriver().findElement(
                By.xpath("/html/body/main/div[1]/div/div/div[1]/h1"));
        int expected;
        if (title.getText().contains("Guide")) {
            expected = 1;
        } else {
            expected = 2;
        }

        assertEquals(expected, 1);
    }

  
}

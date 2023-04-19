package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class DreamTeamTest extends BaseTest {

    @Test
    public void testSecond() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testSendKeysPavelBal() throws InterruptedException {

        final String START_LINK = "http://uitestingplayground.com/";
        getDriver().get(START_LINK);
        WebElement textInput = getDriver().findElement(By.cssSelector("[href='/textinput']"));
        textInput.click();
        String title = getDriver().getTitle();
        Thread.sleep(2000);
        Assert.assertEquals("Text Input", title);

        String myButtonName = "Push me";
        WebElement button = getDriver().findElement(By.id("updatingButton"));
        Assert.assertNotEquals(button.getText(), myButtonName);

        WebElement field = getDriver().findElement(By.id("newButtonName"));
        field.click();
        field.sendKeys(myButtonName);
        button.click();
        Assert.assertEquals(button.getText(), myButtonName);

    }

    @Test
    public void testZoom(){
        getDriver().get("https://zoom.us");

        String title = getDriver().getTitle();
        Assert.assertEquals("One platform to connect | Zoom", title);

        WebElement solutionButton = getDriver().findElement(By.id("btnNewSolutions"));
        solutionButton.click();

        WebElement discoverButton = getDriver().findElement(By.id("discoverZoom"));
        String link = discoverButton.getAttribute("href");
        Assert.assertEquals(link, "https://explore.zoom.us/en/industry/");
    }

    @Test
    public void testMainPageFirstLineText() {
        getDriver().get("https://openweathermap.org/");

        WebElement text = getDriver().findElement(By.xpath("//div[1]/div/h1/span"));
        Assert.assertEquals(text.getText(), "OpenWeather");
    }

    @Test
    public void testWorkWithWaits() {
        getDriver().get("https://google.com/ncr");
        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("jenkins" + Keys.ENTER);

        WebElement text = new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath
                        ("//a[@href='https://www.jenkins.io/']/div/div/span")));
        Assert.assertEquals(text.getText(), "Jenkins");
    }
}



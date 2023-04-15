package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DreamTeamTest extends BaseTest {
    //First test
    @Test
    public void testSecond() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);

        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();
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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://zoom.us");

        String title = driver.getTitle();
        Assert.assertEquals("One platform to connect | Zoom", title);

        WebElement solutionButton = driver.findElement(By.id("btnNewSolutions"));
        solutionButton.click();

        WebElement discoverButton = driver.findElement(By.id("discoverZoom"));
        String link = discoverButton.getAttribute("href");
        Assert.assertEquals(link, "https://explore.zoom.us/en/industry/");
        driver.quit();

    }

}

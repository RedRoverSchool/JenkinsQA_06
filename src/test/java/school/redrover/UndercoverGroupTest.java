package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UndercoverGroupTest {

    private WebDriver driverFHD;
    private WebDriver driverHD;

    // Browser setup params
    private ChromeOptions resolution(int resolutionX, int resolutionY) {
        // Метод создает новые options для браузера с параметрами(разрешением окна) указанными в аргументах.
        ChromeOptions options = new ChromeOptions();
        return options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=" + resolutionX + "," + resolutionY);
    }

    @BeforeTest
    private void openChromeBrowser() {
        driverFHD = new ChromeDriver(resolution(1920, 1080));
        driverHD = new ChromeDriver(resolution(1280, 720));
    }
    @AfterTest
    private void closeChromeBrowser() {
        driverFHD.quit();
        driverHD.quit();
    }

    // Tests
    @Test
    public void googleSearchTest() {
        driverFHD.get("https://google.com");

        WebElement searchField = driverFHD.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        WebElement actual = driverFHD.findElement(By.xpath("//h3[text() = \"Selenium\"]"));

        Assert.assertEquals(actual.getText(), "Selenium");
    }

    @Test
    public void gogleSearchtest() {
        driverFHD.get("https://www.google.com/");

        WebElement searchbox = driverFHD.findElement(By.name("q"));
        searchbox.sendKeys("selenium\n");

        WebElement text = driverFHD.findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }

    @Test
    public void checkboxClicktest() {
        driverFHD.get("https://crossbrowsertesting.github.io/todo-app.html");

        WebElement checkboxOne = driverFHD.findElement(By.name("todo-1"));
        checkboxOne.click();

        WebElement check = driverFHD.findElement(By.cssSelector("ul.list-unstyled span.done-true"));
        check.isDisplayed();
    }

    @Test
    public void dragAndDropFHDTest() {
        driverFHD.get("https://crossbrowsertesting.github.io/drag-and-drop.html");

        WebElement element1 = driverFHD.findElement(By.id("draggable"));
        WebElement element2 = driverFHD.findElement(By.id("droppable"));

        Actions action = new Actions(driverFHD);

        action.dragAndDrop(element1, element2).build().perform();
    }
    @Test
    public void dragAndDropHDTest() {
        driverHD.get("https://crossbrowsertesting.github.io/drag-and-drop.html");

        WebElement element1 = driverHD.findElement(By.id("draggable"));
        WebElement element2 = driverHD.findElement(By.id("droppable"));

        Actions action = new Actions(driverHD);

        action.dragAndDrop(element1, element2).build().perform();
    }
}
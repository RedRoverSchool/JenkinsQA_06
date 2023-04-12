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
import org.testng.annotations.Test;

public class UndercoverGroupTest {
    // Browser setup params
    private ChromeOptions resolution(int resolutionX, int resolutionY) {
        ChromeOptions options = new ChromeOptions();
        return options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=" + resolutionX + "," + resolutionY);
    }

    WebDriver driverFHD = new ChromeDriver(resolution(1920, 1080));

    @AfterTest
    private void closeChromeBrowser() {
        driverFHD.quit();
    }

    // Tests
    @Test(timeOut = 20000)
    public void firstTest() throws InterruptedException{
        driverFHD.get("https://google.com");
        Thread.sleep(2000);

        WebElement searchField = driverFHD.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        WebElement actual = driverFHD.findElement(By.xpath("//h3[text() = \"Selenium\"]"));

        Assert.assertEquals(actual.getText(), "Selenium");
    }

    @Test(timeOut = 20000)
    public void testGoogleSearch() throws InterruptedException{
        driverFHD.get("https://www.google.com/");
        Thread.sleep(2000);

        WebElement searchbox = driverFHD.findElement(By.name("q"));
        searchbox.sendKeys("selenium\n");

        WebElement text = driverFHD.findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }

    @Test(timeOut = 20000)
    public void testFirstTry() throws InterruptedException{
        driverFHD.get("https://crossbrowsertesting.github.io/todo-app.html");
        Thread.sleep(2000);

        WebElement checkboxOne = driverFHD.findElement(By.name("todo-1"));
        checkboxOne.click();

        WebElement check = driverFHD.findElement(By.cssSelector("ul.list-unstyled span.done-true"));
        if (check != null) {
            System.out.println("First checkbox checked!");
        } else {
            System.out.println("Checkbox not found");
        }
    }

    @Test(timeOut = 20000)
    public void dragAndDropTest() throws InterruptedException {
        driverFHD.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        Thread.sleep(2000);

        WebElement element1 = driverFHD.findElement(By.id("draggable"));
        WebElement element2 = driverFHD.findElement(By.id("droppable"));

        Actions action = new Actions(driverFHD);

        action.dragAndDrop(element1, element2).build().perform();
    }
}
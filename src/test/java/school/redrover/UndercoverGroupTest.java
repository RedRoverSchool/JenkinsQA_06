package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class UndercoverGroupTest {

    @Test
    public void firstTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://google.com");

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        WebElement actual = driver.findElement(By.xpath("//h3[text() = \"Selenium\"]"));

        Assert.assertEquals(actual.getText(), "Selenium");

        driver.quit();
    }
    @Test
    public void testSearch(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=800,600");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.google.com/");
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.sendKeys("selenium\n");

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");

        driver.quit();
    }

    @Test
    public void testFirstTry() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=800,600");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://crossbrowsertesting.github.io/todo-app.html");

        WebElement checkboxOne = driver.findElement(By.name("todo-1"));
        checkboxOne.click();

        WebElement check = driver.findElement(By.cssSelector("ul.list-unstyled span.done-true"));
        if (check != null) {
            System.out.println("First checkbox checked!");
        } else {
            System.out.println("Checkbox not found");
        }
    }

    @Test
    public void seleniumWebFormTest() throws InterruptedException {
        ChromeOptions chrOpts = new ChromeOptions();
        chrOpts.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920, 1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        assertEquals("Web form", driver.getTitle());

        Thread.sleep(2000);

        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.click();
        textInput.sendKeys("Selenium");

        WebElement password = driver.findElement(By.name("my-password"));
        password.click();
        password.sendKeys("123456");

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.click();
        textArea.sendKeys("lorem ipsum ...");

        Select dropdown = new Select(driver.findElement(By.className("form-select")));
        dropdown.selectByVisibleText("Two");

        WebElement checkbox1 = driver.findElement(By.id("my-check-1"));
        if(checkbox1.isDisplayed()){
            checkbox1.click();
        }
        WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
        if(checkbox2.isDisplayed()){
            checkbox2.click();
        }
        assertFalse(driver.findElement(By.id("my-check-1")).isSelected());
        assertTrue(driver.findElement(By.id("my-check-2")).isSelected());

        driver.findElement(By.cssSelector("button")).click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();
    }
}

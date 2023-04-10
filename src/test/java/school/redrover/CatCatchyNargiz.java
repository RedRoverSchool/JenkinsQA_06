package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CatCatchyNargiz {
    @Test
    public void textVerification() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement mouseHoverOver = driver.findElement(By.id("mousehover"));

        String actualText = mouseHoverOver.getText();
        String expectedText = "Mouse Hover";

        Assert.assertEquals(actualText, expectedText, "Text verification is FAILED");

        driver.quit();
    }

    @Test
    public void dragAndDrop() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://formy-project.herokuapp.com/dragdrop");

        WebElement image = driver.findElement(By.id("image"));

        WebElement box = driver.findElement(By.id("box"));

        Actions actions = new Actions(driver);

        actions.dragAndDrop(image, box).perform();

        driver.quit();

    }
}

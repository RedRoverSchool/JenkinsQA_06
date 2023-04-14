package school.redrover;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.interactions.Actions;
// Allows to hover over language dropdown to reveal options

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;
// Executes JS code. In this case, window.alert() for the pop-up message

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KirillKTest {

    @Test
    public void redRoverLangTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(
                    "--remote-allow-origins=*",
                    "--window-size=1300,700",
                    "--headless",
                    "--start-maximized"
            );
        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions actions = new Actions(driver);

        driver.get("https://redrover.school/");

        Thread.sleep(1000);

        WebElement langDropdownBase = driver.findElement(By.cssSelector("a[data-tooltip-menu-id= '546712221']"));
        actions.moveToElement(langDropdownBase).perform(); // hovers over element

        // explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class= 't794__typo t794__link t-name t-name_xs']")));

        WebElement langOptionRu = driver.findElement(By.cssSelector("a[class= 't794__typo t794__link t-name t-name_xs']"));
        actions.moveToElement(langOptionRu); // hovers over element
        actions.click(langOptionRu).perform();
        Thread.sleep(500);

        WebElement boldHeading = driver.findElement(By.cssSelector("h2[field= 'tn_text_1674775278869']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < boldHeading.getLocation().getY(); i += 10) {
            js.executeScript("window.scrollBy(0," +  10 + ")", "");
            Thread.sleep(5); // change millis for custom scroll speed
        }

        Assert.assertEquals(boldHeading.getText(), "Обучение");
        // If the page is still in English, boldHeading.getText would instead return "Learning process"
        Thread.sleep(5);


        js.executeScript("window.alert(\"Site language option functions properly. Closing window...\")");

        Thread.sleep(3500);

        driver.quit();
    }
}

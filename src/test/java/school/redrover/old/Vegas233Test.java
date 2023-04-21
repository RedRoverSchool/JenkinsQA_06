package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;
@Ignore
public class Vegas233Test extends BaseTest {

    @Test
    public void testFirst() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        Thread.sleep(2000);


        String title = getDriver().getTitle();
        assertEquals(title, "Web form");

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals(value, "Received!");
    }
}

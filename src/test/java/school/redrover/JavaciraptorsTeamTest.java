package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JavaciraptorsTeamTest extends BaseTest {
 
    @Test
    public void testReadOnlyInput() {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement readOnlyInput = getDriver().findElement(By.name("my-readonly"));
        String initial = readOnlyInput.getAttribute("value");

        readOnlyInput.click();
        readOnlyInput.sendKeys("Hello World!");

        Assert.assertEquals(readOnlyInput.getAttribute("value"), initial);
    }

    @Test
    public void testSavichev() {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = getDriver().findElement(By.name("my-text"));
        textInput.click();
        textInput.sendKeys("Hello World!");
        String expText = textInput.getAttribute("value");

        Assert.assertEquals(expText, "Hello World!");

        WebElement passInput = getDriver().findElement(By.name("my-password"));
        passInput.click();
        passInput.sendKeys("222222");
        String expPass = passInput.getAttribute("value");

        Assert.assertEquals(expPass, "222222");

        WebElement checkBox = getDriver().findElement(By.id("my-check-2"));
        checkBox.click();

        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
}

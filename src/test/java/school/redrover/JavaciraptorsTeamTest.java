package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class JavaciraptorsTeamTest extends BaseTest {

    @Test
    public void testReadOnlyInput() {

        getGetDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement readOnlyInput = getGetDriver().findElement(By.name("my-readonly"));
        String initial = readOnlyInput.getAttribute("value");

        readOnlyInput.click();
        readOnlyInput.sendKeys("Hello World!");

        Assert.assertEquals(readOnlyInput.getAttribute("value"), initial);
    }

    @Test
    public void testSavichev() {

        getGetDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = getGetDriver().findElement(By.name("my-text"));
        textInput.click();
        textInput.sendKeys("Hello World!");
        String expText = textInput.getAttribute("value");

        Assert.assertEquals(expText, "Hello World!");

        WebElement passInput = getGetDriver().findElement(By.name("my-password"));
        passInput.click();
        passInput.sendKeys("222222");
        String expPass = passInput.getAttribute("value");

        Assert.assertEquals(expPass, "222222");

        WebElement checkBox = getGetDriver().findElement(By.id("my-check-2"));
        checkBox.click();

        WebElement submitButton = getGetDriver().findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement message = getGetDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testBinoeder() {

        getGetDriver().get("https://www.selenium.dev/selenium/web/web-form.html");


        String title = getGetDriver().getTitle();
        assertEquals("Web form", title);

        WebElement textBox = getGetDriver().findElement(By.name("my-textarea"));
        WebElement submitButton = getGetDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");

        submitButton.click();

        WebElement message = getGetDriver().findElement(By.id("message"));
        String value = message.getText();

        assertEquals("Received!", value);
    }
}

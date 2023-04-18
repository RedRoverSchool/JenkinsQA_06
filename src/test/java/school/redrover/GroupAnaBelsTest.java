package school.redrover;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import school.redrover.runner.BaseTest;
public class GroupAnaBelsTest extends BaseTest {

    @Test
    public void testStasM() {
        getDriver().get("https://www.yahoo.com/");

        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id='ybarAccountProfile']/a"));
        submitButton.click();

        String title = getDriver().getTitle();
        Assert.assertEquals(title, "Yahoo");
    }
}
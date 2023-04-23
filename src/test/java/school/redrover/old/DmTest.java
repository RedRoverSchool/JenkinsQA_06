package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Ignore
public class DmTest {

    @Test
    public void testCheckResultTask() extends BaseTest {
        getDriver().get("https://www.w3schools.com/");

        WebElement search;
        search = getDriver().findElement(By.xpath("//form/input[@id = \"search2\"]"));
        search.sendKeys("HTML Tutorial");
        search.sendKeys(Keys.RETURN);

        WebElement textBox;
        textBox = getDriver().findElement(By.xpath("//input[@name = \"ex1\"]"));
        textBox.sendKeys("title");

        WebElement submitConfirm;
        submitConfirm = getDriver().findElement(By.xpath("//*[@id=\"w3-exerciseform\"]/div/button"));
        submitConfirm.click();

        ArrayList<String> words = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(words.get(1));

        WebElement submit = getDriver().findElement(By.xpath("//button[@id='answerbutton']"));
        submit.click();

        WebElement result;
        result = getDriver().findElement(By.xpath("//*[@id=\"assignmentCorrect\"]/h2"));

        Assert.assertEquals(result.getText(), "Correct!");

        getDriver().quit();
    }
}

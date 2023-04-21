package school.redrover;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupJavaExplorersTest extends BaseTest {

    @Test
    public void testTrelloTitle() {
        getDriver().get("https://trello.com/");

        Assert.assertEquals(getDriver().getTitle(), "Manage Your Team’s Projects From Anywhere | Trello");
    }

    @Test
    public void testEquilateralTriangle() {
        int triangleSize = 5;
        final String expectedResult = "Equilateral";

        getDriver().get("https://testpages.herokuapp.com/styled/apps/triangle/triangle001.html");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement inputSize1 =  getDriver().findElement(By.id("side1"));
        inputSize1.click();
        inputSize1.sendKeys(String.valueOf(triangleSize));
        WebElement inputSize2 =  getDriver().findElement(By.id("side2"));
        inputSize2.click();
        inputSize2.sendKeys(String.valueOf(triangleSize));
        WebElement inputSize3 =  getDriver().findElement(By.id("side3"));
        inputSize3.click();
        inputSize3.sendKeys(String.valueOf(triangleSize));
        WebElement button =  getDriver().findElement(By.id("identify-triangle-action"));
        button.sendKeys(Keys.ENTER);

        WebElement actualResult =  getDriver().findElement(By.id("triangle-type"));
        Assert.assertEquals(actualResult.getText(), expectedResult);
    }

    @Test
    public void testElementWithDynamicId() {
        getDriver().get("http://uitestingplayground.com/");
        WebElement buttonClassAttribute = getDriver().findElement(By.cssSelector("a[href='/classattr']"));
        buttonClassAttribute.click();
        WebElement buttonPrimary = getDriver().findElement(By.cssSelector("button[class*=btn-primary]"));
        buttonPrimary.click();
        Alert alert = getDriver().switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText, "Primary button pressed");
    }

    @Test
    public void testClickOnButton() {
        getDriver().get("http://uitestingplayground.com/");
        WebElement buttonClick = getDriver().findElement(By.xpath("//a[@href='/click']"));
        buttonClick.click();
        WebElement buttonPrimary = getDriver().findElement(By.xpath("//button[@id='badButton']"));
        buttonPrimary.click();
        String buttonClass = buttonPrimary.getAttribute("class");

        Assert.assertTrue(buttonClass.contains("btn-success"));
    }
    @Ignore
    @Test
    public void testIsoscelesTriangle() throws InterruptedException {
        getDriver().get("https://playground.learnqa.ru/puzzle/triangle");

        WebElement sideATextBox = getDriver().findElement(By.className("js_a"));
        sideATextBox.sendKeys("5");
        WebElement sideBTextBox = getDriver().findElement(By.className("js_b"));
        sideBTextBox.sendKeys("5");
        WebElement sideCTextBox = getDriver().findElement(By.className("js_c"));
        sideCTextBox.sendKeys("6");
        WebElement showResultButton = getDriver().findElement(By.xpath("//button[@class='btn btn-submit']"));
        Thread.sleep(1000);
        showResultButton.click();
        WebElement resultMessage = getDriver().findElement(By.xpath("//div[@class='info logg']"));
        Thread.sleep(1000);
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, "Это равнобедренный треугольник.\nВы ввели:\nA: 5; B: 5; C: 6");
    }
}

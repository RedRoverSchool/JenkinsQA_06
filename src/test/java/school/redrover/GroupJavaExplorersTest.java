package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupJavaExplorersTest extends BaseTest {

    @Test
    public void testTrelloTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        String url = "https://trello.com/";
        String expTitle = "Manage Your Team’s Projects From Anywhere | Trello";

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);

        Assert.assertEquals(driver.getTitle(), expTitle);

        driver.quit();
    }

    @Test
    public void textTitleOnAuthFormTest() {
        getDriver().get("https://www.21vek.by/");

        WebElement buttonCookie = getDriver().findElement(By.cssSelector("#modal-cookie button[class$='primary']"));
        buttonCookie.click();
        WebElement buttonAccount = getDriver().findElement(By.cssSelector("div[class*=userTools] button"));
        buttonAccount.click();
        WebElement buttonSignIn = getDriver().findElement(By.cssSelector("button[data-testid='loginButton']"));
        buttonSignIn.click();

        WebElement authFormTitleText = getDriver().findElement(By.cssSelector("form h5"));
        Assert.assertEquals(authFormTitleText.getText(), "Вход");
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
    public void testLocalityBtnOnHeader() {
        getDriver().get("https://www.21vek.by/");

        WebElement buttonCookie = getDriver().findElement(By.cssSelector("#modal-cookie button[class$='primary']"));
        buttonCookie.click();
        WebElement buttonLocality = getDriver().findElement(By.cssSelector("header button[class*='localityBtn']"));
        buttonLocality.click();
        WebElement buttonInputCity = getDriver().findElement(By.cssSelector("input[aria-label='city']"));
        buttonInputCity.click();
        WebElement firstCityOnDropdown = getDriver().findElement(By.cssSelector("div[id='dropdown-0-city'] li:nth-child(1)"));
        firstCityOnDropdown.click();
        WebElement buttonSubmit = getDriver().findElement(By.cssSelector("button[class*=baseAction] > div[class*=Button-module]"));
        buttonSubmit.click();

        Assert.assertEquals(buttonLocality.getText(), "г. Минск");
    }
}

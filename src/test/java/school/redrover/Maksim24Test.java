package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;



public class Maksim24Test {


    @FindBy(xpath = "//a[@class='btn btn-secondary m-1']")
    public  WebElement buttonDocumentation;

    public final static String BASE_URL = "https://www.jenkins.io/";

    public WebDriverWait webDriverWait10;

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public  final ChromeOptions chromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");

        return chromeOptions;
    }

    public final WebDriver createDriver() {
        WebDriver driver = new ChromeDriver(chromeOptions());

        WebDriverManager.chromedriver().setup();
        return driver;
    }

    public final  void getBaseUrl(){
        driver = createDriver();
        driver.get(BASE_URL);

        PageFactory.initElements(driver, this);
    }

    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(driver,Duration.ofSeconds(5));
        }
        return webDriverWait10;
    }

    public final void verifyElementVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementIsClickable (WebElement element) {
      return   getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public final void clickButtonDocumentation() {
        verifyElementVisible(buttonDocumentation);
        verifyElementIsClickable(buttonDocumentation).click();

    }

    @Test
    public void testClickDocumentationButton()  {

        final String expectedTitle = "Jenkins User Documentation";

        getBaseUrl();

        clickButtonDocumentation();

        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);

        driver.quit();
    }
}

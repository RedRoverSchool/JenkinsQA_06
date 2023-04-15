package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupCATTest extends BaseTest {

    @FindBy(xpath = "//a[@class='btn btn-secondary m-1']")
    public WebElement buttonDocumentation;
    @FindBy(xpath = "//a[@class='btn btn-primary m-1']")
    public WebElement buttonDownload;
    @FindBy(xpath = "//div[@class='supporters']//li")
    public List<WebElement> supporters;
    @FindBy(xpath = "//div[@class='supporters']")
    public WebElement containerOfSupporters;
    @FindBy(xpath = "//div[@class='row chunks features uniform-height']")
    public WebElement containerOfNamesOfFeatureListSegment;
    @FindBy(xpath = "//div[@class='row chunks features uniform-height']//h5")
    public List<WebElement> featureListSegment;

    public final static String BASE_URL = "https://www.jenkins.io/";

    public WebDriverWait webDriverWait10;

    public final void getBaseUrl() {
        getDriver().get(BASE_URL);

        PageFactory.initElements(getDriver(), this);
    }

    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return webDriverWait10;
    }

    public final void verifyElementVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementIsClickable(WebElement element) {
        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public final void clickButtonDocumentation() {
        verifyElementVisible(buttonDocumentation);
        verifyElementIsClickable(buttonDocumentation).click();
    }

    public final void clickButtonDownload() {
        verifyElementVisible(buttonDownload);
        verifyElementIsClickable(buttonDownload).click();
    }

    public void scrollByElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public int getListSize(List<WebElement> elements){
        return elements.size();
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public List<String> getNamesOfLists(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element));
        }

        return texts;
    }

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

    @Test
    public void testVerifyRedirectFromJetLabel() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jetbrains.com/");

        Thread.sleep(3000);

        WebElement label = driver.findElement(By.xpath("//a[@aria-label='Navigate to main page']"));
        label.click();

        WebElement title = driver.findElement(By.xpath("//h1[@class='rs-h1 rs-h1_theme_dark home-page__title']"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.jetbrains.com/");
        Assert.assertEquals(title.getText(), "Essential tools for software developers and teams");

        driver.quit();
    }

    @Test
    public void testVerifyLink() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://smartbear.com/");

        Thread.sleep(3000);

        WebElement carrer = driver.findElement(By.cssSelector("li:nth-child(4) > a"));
        carrer.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://smartbear.com/company/careers/");

        driver.quit();
    }

    @Test
    public void testClickDocumentationButton() {

        final String expectedTitle = "Jenkins User Documentation";

        getBaseUrl();

        clickButtonDocumentation();

        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        getDriver().quit();
    }
    @Test
    public void testSizeSupporters(){
        final int expectedSizeOfSupporters = 12;

        getBaseUrl();

        scrollByElement(containerOfSupporters);
        getWait10();

        int actualSizeOfSupporters = getListSize(supporters);
        getWait10();

        Assert.assertEquals(actualSizeOfSupporters, expectedSizeOfSupporters);
        getDriver().quit();
    }

    @Test
    public void testNamesOfSupporters(){
        final List<String> expectedNamesOfSupporters = Arrays.asList("Atlassian", "Datadog", "DigitalOcean", "Discourse",
                "Fastly", "IBM", "Netlify", "PagerDuty", "Sentry", "SpinUp", "Tsinghua University", "XMission");

        getBaseUrl();

        scrollByElement(containerOfSupporters);
        getWait10();

        List<String> actualNamesOfSupporters = getNamesOfLists(supporters);

        Assert.assertEquals(actualNamesOfSupporters, expectedNamesOfSupporters);

        getDriver().quit();
    }

    @Test
    public void testClickDownloadButton() {
        final String expectedTitle = "Jenkins download and deployment";

        getBaseUrl();
        clickButtonDownload();
        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
        getDriver().quit();
    }

    @Test
    public void testNamesOfFeatureListSegment(){
        final List<String> expectedNamesOfFeatureListSegment = Arrays.asList("Continuous Integration and Continuous Delivery",
                "Easy installation", "Easy configuration", "Plugins", "Extensible", "Distributed");

        getBaseUrl();
        scrollByElement(containerOfNamesOfFeatureListSegment);
        getWait10();
        List<String> actualNamesOfNamesOfFeatureListSegment = getNamesOfLists(featureListSegment);

        Assert.assertEquals(actualNamesOfNamesOfFeatureListSegment, expectedNamesOfFeatureListSegment);
        getDriver().quit();
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Ignore;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group99BottlesTest extends BaseTest {

    @Test
    public void testRemoveProductFromCart() {

        getDriver().get("https://petstore.octoperf.com/actions/Catalog.action");

        getDriver().findElement(
                By.xpath("//div[@id='SidebarContent']/a[contains(@href, 'FISH')]")).click();
        getDriver().findElement(
                By.xpath("//div[@id='Catalog']//a[contains(@href, 'FI-SW-01')]")).click();
        getDriver().findElement(
                By.xpath("//div[@id='Catalog']//a[contains(@href, 'EST-2') and text()='EST-2']")).click();
        getDriver().findElement(
                By.xpath("//div[@id='Catalog']//a[contains(@href, 'EST-2') and @class='Button' and text()='Add to Cart']")).click();
        getDriver().findElement(
                By.xpath("//div[@id='Cart']//a[contains(@href, 'EST-2') and @class='Button' and text()='Remove']")).click();
        String actual = getDriver().findElement(
                By.xpath("//div[@id='Cart']//td[text()='Sub Total: $0.00 ']")).getText();

        Assert.assertEquals(actual, "Sub Total: $0.00");
    }

    @Test
    public void testCheckboxesHeadHerokuApp() throws InterruptedException {

        getDriver().get("https://formy-project.herokuapp.com/");

        getDriver().findElement(By.xpath("//li/a[@href='/checkbox']")).click();

        Thread.sleep(3000);

        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Checkboxes");
    }

    @Test
    public void testHeaderH1TextOnWomenPage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");

        driver.findElement(By.id("menu-item-1229")).click();

        WebElement headerText = driver.findElement(
                By.xpath("//header[@class = 'woocommerce-products-header']/h1"));

        Assert.assertEquals(headerText.getText(), "Women");

        driver.quit();
    }

    @Test
    public void testWorkshopHeaderText() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://corporatetrainingmaterials.com/");

        driver.findElement(By.xpath("//ul[@id='AccessibleNav']/li[2]/a")).click();

        WebElement headerH1Text = driver.findElement(
                By.xpath("//div[@id='ProductSection']//div[1]/h1"));

        Assert.assertEquals(headerH1Text.getText(), "WORKSHOP LIBRARY");

        driver.quit();

    }

    @Test
    public void testTelerikHeaderText() {
        getDriver().get("https://www.telerik.com/");

        WebElement headerH1Text = getDriver().findElement(
                By.xpath("//div[@id='ContentPlaceholder1_C339_Col00']/h1"));

        Assert.assertEquals(headerH1Text.getText(), "Modern UI Made Easy");
    }

    @Test
    public void testTelerikTitleURLDemosPage() {
        getDriver().get("https://www.telerik.com/");
        getDriver().manage().window().maximize();

        getDriver().findElement(By.xpath("//nav[@id='js-tlrk-nav']//ul[@class='TK-Context-Menu TK-Menu']/li[1]/a")).click();

        Assert.assertEquals(getDriver().getTitle(), "Telerik Product Demos, Examples and Tutorials for all Telerik products");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.telerik.com/support/demos");
    }

    @Test
    public void testTelerikNavigateMenuDemosPageArray() {
        String[] expectedResult = {"Web", "Desktop", "Mobile", "Reporting & QA", "Conversational UI", "Sitefinity CMS"};
        getDriver().get("https://www.telerik.com/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        getDriver().findElement(By.xpath("//nav[@id='js-tlrk-nav']//ul[@class='TK-Context-Menu TK-Menu']/li[1]/a")).click();

        String[] actualResult = new String[6];
        actualResult[0] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][1]")).getText();
        actualResult[1] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][2]")).getText();
        actualResult[2] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][3]")).getText();
        actualResult[3] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][4]")).getText();
        actualResult[4] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][5]")).getText();
        actualResult[5] = getDriver().findElement(By.xpath("//div[@data-tlrk-plugin='navspy']/a[@href][6]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTelerikNavigateMenuDemosPageList() {
        List<String> expectedResult = new ArrayList<>(
                Arrays.asList("Web", "Desktop", "Mobile", "Reporting & QA", "Conversational UI", "Sitefinity CMS"));
        getDriver().get("https://www.telerik.com/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        getDriver().findElement(By.xpath("//nav[@id='js-tlrk-nav']//ul[@class='TK-Context-Menu TK-Menu']/li[1]/a")).click();

        List<WebElement> elementList = getDriver().findElements(By.xpath("//div[@data-tlrk-plugin='navspy']/a"));
        List<String> actualResult = WebElementToString(elementList);

        Assert.assertEquals(actualResult, expectedResult);
    }

    public static List<String> WebElementToString(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    @Test
    public void testH1Text_WhenChooseLevelLanguage() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.w3schools.com/");

        driver.findElement(By.xpath("//a[@href = 'where_to_start.asp']")).click();

        Thread.sleep(3000);

        WebElement text = driver.findElement(By.xpath("//h1[text() = 'Where To Start']"));

        Assert.assertEquals(text.getText(), "Where To Start");

        driver.quit();
    }
    @Ignore
    @Test
    public void testDemoblazeAddToCart() {

        String productName = "Iphone 6 32gb";
        getDriver().get("https://www.demoblaze.com/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> products = getDriver().findElements(By.cssSelector(".hrefch"));
        for (WebElement prod : products) {
            if (prod.getText().equals(productName)) {
                prod.click();
                break;
            }
        }
        getDriver().findElement(By.cssSelector(".btn-success")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.cssSelector("#cartur")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr/td[2]")).getText(), productName);
    }

    @Test
    public void testDemoblazeProdAddToCart() {

        String productName = "Sony vaio i5";
        getDriver().get("https://www.demoblaze.com/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> products = getDriver().findElements(By.cssSelector(".hrefch"));
        products.stream().filter(product -> product.getText()
                .equals(productName)).findFirst().ifPresent(WebElement::click);
        getDriver().findElement(By.cssSelector(".btn-success")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.cssSelector("#cartur")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr/td[2]")).getText(), productName);
    }

    @Test
    public void testJPetStoreAddDogToCart() {
        getDriver().get("https://petstore.octoperf.com/actions/Catalog.action");

        getDriver().findElement(By.xpath("//div[@id='SidebarContent']/a[2]")).click();
        getDriver().findElement(By.xpath("//div[@id='Catalog']/table/tbody/tr[3]/td/a")).click();
        getDriver().findElement(By.xpath("//a[@class='Button']")).click();

        List<String> textCartItems = new ArrayList<>();
        List<WebElement> cartItems = getDriver().findElements(By.xpath("//td"));
        for (WebElement element : cartItems) {
            textCartItems.add(element.getText());
        }
        Assert.assertTrue(textCartItems.contains("K9-PO-02"));
    }

    @Test
    public void testCheckActionMoreButton() throws InterruptedException {

        getDriver().get("https://magento.softwaretestingboard.com/");

        getDriver().findElement(By.xpath("//span[@class='action more button']")).click();

        Thread.sleep(3000);

        WebElement text = getDriver().findElement(By.xpath("//span[@class='base']"));

        Assert.assertEquals(text.getText(), "New Luma Yoga Collection");
    }

    @Ignore
    @Test
    public void testLogoNavigateToBaseUrl() {
        String expectedResult = "https://www.thestar.com/";

        getDriver().get("https://www.thestar.com/");
        getDriver().findElement(By.xpath("//*[@class='c-main-logo']")).click();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testElementContentTitle() {

        getDriver().get("https://magento.softwaretestingboard.com/");
        WebElement discountSign = getDriver().findElement(By.xpath("//a[@class='block-promo home-pants']/span/strong[@class='title']"));

        Assert.assertEquals(discountSign.getText(), "20% OFF");
    }
}

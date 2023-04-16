package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class GroupLargusTest {

    @Test
    public void testSearchResult() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://burgerkingrus.ru/");

        driver.findElement(By.xpath("//input[@class='bk-input__element']"))
                .sendKeys("Воппер");
        List<WebElement> listOfSearch = driver.findElements(By.xpath
                ("//div[@class='bk-search__list']//div[@class='bk-dish-card__title']"));

        for (WebElement elementOfList: listOfSearch) {
            Assert.assertTrue(elementOfList.getText().contains("Воппер"));
        }

        driver.quit();
    }

    @Test
    public void testCheckedLinkSiderBar() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.w3schools.com/");
        driver.manage().window().maximize();

        WebElement buttonLearnJava = driver.findElement
                (By.xpath("//div[@class='w3-col l6 w3-center']//a[text()='Learn Java']"));
        js.executeScript("arguments[0].scrollIntoView(true);",buttonLearnJava);
        buttonLearnJava.click();

        WebElement siderBar = driver.findElement
                (By.id("leftmenuinnerinner"));

        List<WebElement> listSiderBar = driver.findElements
                (By.xpath("//div[@id='leftmenuinnerinner']//a[@target='_top']"));

        WebElement itemListSiderBar = null;
        for (WebElement element : listSiderBar) {
            if(element.getText().contains("HashMap")){
                itemListSiderBar = element;
                break;
            }
        }

        actions
                .moveToElement(siderBar)
                .scrollToElement(itemListSiderBar)
                .moveToElement(itemListSiderBar)
                .click()
                .build()
                .perform();

        WebElement titleOnPage = driver.findElement
                (By.xpath("//span[@class='color_h1']"));

        Assert.assertEquals("HashMap", titleOnPage.getText());
        driver.quit();
    }

    @Ignore
    @Test
    public void testCheckSearchUsedCar() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", /*"--headless", */"--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions actions = new Actions(driver);

        driver.get("https://www.kia.ru/");
        Thread.sleep(3000);

        List<WebElement> buttonsChoiseAndBuy  = driver.findElements
                    (By.xpath("//span[text()='Авто с пробегом']"));
        actions
                .sendKeys(Keys.ARROW_UP)
                .moveToElement(buttonsChoiseAndBuy.get(0))
                .click()
                .build()
                .perform();

        Thread.sleep(1000);
        List<WebElement> listOfSearch = driver.findElements
                (By.xpath("//div[@class='catalog-element__descr']"));
        System.out.println("include- " + listOfSearch.size());
        boolean findResult = true;
        for (WebElement elementOfList: listOfSearch) {
            if (!elementOfList.getText().contains("км") && elementOfList.getText().equals("0 км")){
                findResult = false;
            }
        }

        Assert.assertTrue(findResult);
        driver.quit();
    }
}

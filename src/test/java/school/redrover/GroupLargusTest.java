package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class GroupLargusTest extends BaseTest {

    @Test
    public void testSearchResult() {
        getDriver().get("https://burgerkingrus.ru/");

        getDriver().findElement(By.xpath("//input[@class='bk-input__element']"))
                .sendKeys("Воппер");
        List<WebElement> listOfSearch = getDriver().findElements(By.xpath
                ("//div[@class='bk-search__list']//div[@class='bk-dish-card__title']"));

        for (WebElement elementOfList: listOfSearch) {
            Assert.assertTrue(elementOfList.getText().contains("Воппер"));
        }
    }

    @Test
    public void testCheckedLinkSiderBar() {
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        getDriver().get("https://www.w3schools.com/");
        getDriver().manage().window().maximize();

        WebElement buttonLearnJava = getDriver().findElement
                (By.xpath("//div[@class='w3-col l6 w3-center']//a[text()='Learn Java']"));
        js.executeScript("arguments[0].scrollIntoView(true);",buttonLearnJava);
        buttonLearnJava.click();

        WebElement siderBar = getDriver().findElement
                (By.id("leftmenuinnerinner"));

        List<WebElement> listSiderBar = getDriver().findElements
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

        WebElement titleOnPage = getDriver().findElement
                (By.xpath("//span[@class='color_h1']"));

        Assert.assertEquals("HashMap", titleOnPage.getText());
    }
    @Test
    public void testFirst() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(),"Selenium");

        driver.quit();
    }
}

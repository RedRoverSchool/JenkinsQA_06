package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
@Ignore
public class AliaksandraAnufryievaTest extends BaseTest {

    @Test
    public void testWikipediaSearch() {
        getDriver().get("https://www.wikipedia.org/");

        WebElement search = getDriver().findElement(By.name("search"));
        search.sendKeys("Jenkins");
        search.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.id("firstHeading"));

        Assert.assertEquals(text.getText(), "Jenkins");
    }

    @Test
    public void testWikipediaLanguage() {
        getDriver().get("https://www.wikipedia.org/");

        WebElement languageButton = getDriver().findElement(By.className("lang-list-button"));
        languageButton.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        WebElement chooseBelLanguage = getDriver().findElement(By.xpath("//a[@title='Belaruskaya']"));
        chooseBelLanguage.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        Assert.assertEquals(getDriver().getTitle(), "Вікіпедыя");
    }

    @Test
    public void testFreeFormatterXPath() {
        getDriver().get("https://freeformatter.com/");

        WebElement xpathTesterSearch = getDriver().findElement(By.xpath("//a[@title='XPath Tester']"));
        xpathTesterSearch.click();

        WebElement textXML = getDriver().findElement(By.xpath("//textarea[@placeholder='Copy-paste your XML here']"));
        textXML.sendKeys("<title lang='en'>Harry Potter</title>");

        WebElement textXPath = getDriver().findElement(By.xpath("//input[@placeholder='Copy-paste your XPath expression here']"));
        textXPath.sendKeys("//text()");

        WebElement evaluateXPath = getDriver().findElement(By.id("formatBtn"));
        evaluateXPath.click();

        WebElement resultXPath = getDriver().findElement(By.xpath("//code[text()]"));

        Assert.assertEquals(resultXPath.getText(), "Text='Harry Potter'");
    }

    @Test
    public void testEtsyItemsOnSale() {
        getDriver().get("https://www.etsy.com/");

        WebElement search = getDriver().findElement(By.name("search_query"));
        search.sendKeys("easter gifts");
        search.sendKeys(Keys.RETURN);

        WebElement filterButton = getDriver().findElement(By.id("search-filter-button"));
        filterButton.click();

        WebElement onSaleCheckbox = getDriver().findElement(By.xpath("//label[@for='special-offers-on-sale']"));
        onSaleCheckbox.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        WebElement applyButton = getDriver().findElement(By.xpath("//button[@aria-label='Apply']"));
        applyButton.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        WebElement text = getDriver().findElement(By.xpath("//a[@aria-label='Remove On sale Filter']"));

        Assert.assertEquals(text.getText(), "On sale");
    }
}

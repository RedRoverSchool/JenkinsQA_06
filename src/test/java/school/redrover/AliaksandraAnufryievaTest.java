package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class AliaksandraAnufryievaTest extends BaseTest {

    @Ignore
    @Test
    public void testEtsy() {
        getDriver().get("https://www.etsy.com/");

        WebElement search = getDriver().findElement(By.name("search_query"));
        search.sendKeys("easter gifts");
        search.sendKeys(Keys.RETURN);

        WebElement filter = getDriver().findElement(By.id("search-filter-button"));
        filter.click();

        WebElement onSale = getDriver().findElement(By.xpath("//label[@for='special-offers-on-sale']"));
        onSale.click();

        WebElement apply = getDriver().findElement(By.xpath("//button[@aria-label='Apply']"));
        apply.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WebElement text = getDriver().findElement(By.xpath("//a[@aria-label='Remove On sale Filter']"));

        Assert.assertEquals(text.getText(), "On sale");
    }

    @Test
    public void testJenkinsTitle() {
        getDriver().get("https://www.jenkins.io/");

        Assert.assertEquals(getDriver().getTitle(),"Jenkins");
    }
}

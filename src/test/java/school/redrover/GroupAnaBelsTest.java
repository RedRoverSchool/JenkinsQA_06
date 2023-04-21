package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupAnaBelsTest extends BaseTest {
    @Ignore
    @Test
    public void testStasM() {
        getDriver().get("https://www.yahoo.com/");

        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id='ybarAccountProfile']/a"));
        submitButton.click();

        String title = getDriver().getTitle();
        Assert.assertEquals(title, "Yahoo");
    }

    @Test
    public void testGetItem() throws InterruptedException {
        getDriver().get("https://www.demoblaze.com/index.html");
        getDriver().findElement(By.xpath("//a[@onclick=\"byCat('notebook')\"]")).click();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        getDriver().findElement(By.cssSelector("a[href='prod.html?idp_=11']")).click();
        getDriver().findElement(By.cssSelector("a[onclick='addToCart(11)']")).click();
        Thread.sleep(1000);
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.cssSelector("#cartur")).click();
        WebElement item = getDriver().findElement(By.cssSelector("td:nth-child(2)"));

        Assert.assertEquals(item.getText(), "MacBook air");

    }
}


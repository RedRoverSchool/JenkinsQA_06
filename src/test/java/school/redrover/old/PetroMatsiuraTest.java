package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class PetroMatsiuraTest extends BaseTest {

    @BeforeMethod
    public void BeforeMethod() {

        getDriver().get("https://askomdch.com/");
    }

    @Test
    public void testSale() {

        for(WebElement element : getDriver().findElements(By.cssSelector("span[class='onsale']"))) {

            Assert.assertEquals(element.getText(), "Sale!");
        }
    }

    @Test
    public void testCurrency() {

        for (WebElement element : getDriver().findElements(By.cssSelector("span[class*='currencySymbol']"))) {

            Assert.assertEquals(element.getText(), "$");
        }
    }

    @Test
    public void testDiscount() {

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h3[class*='medium-font-size']")).
                getText(), "25% OFF On all products");
    }

    @Test
    public void testAccount() {

        getDriver().findElement(By.cssSelector("li#menu-item-1237")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askomdch.com/account/");
    }

    @Test
    public void testShop() {

        getDriver().findElement(By.cssSelector("a.wp-block-button__link")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askomdch.com/store");
    }

    @Test
    public void testProductsNumber() {

        Assert.assertEquals(getDriver().findElement(By.cssSelector("ul.products.columns-5")).
                findElements(By.tagName("li")).size(), 5);
    }

    @Test
    public void testColour() {

        getDriver().findElement(By.cssSelector("a[href*='men']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("button[value='Search']")).
                getCssValue("background-color"), "rgba(49, 151, 214, 1)");
    }

    @Test
    public void testFindMoreButton() {

        getDriver().findElement(By.cssSelector("div.wp-block-button.is-style-fill")).click();

        Assert.assertEquals(getDriver().getTitle(), "Contact Us – AskOmDch");
    }
}

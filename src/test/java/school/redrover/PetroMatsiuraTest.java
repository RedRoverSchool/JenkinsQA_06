package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PetroMatsiuraTest extends BaseTest {

    @BeforeMethod
    public void BeforeMethod() {

        getGetdriver().get("https://askomdch.com/");
    }

    @Test
    public void testSale() {

        for(WebElement element : getGetdriver().findElements(By.cssSelector("span[class='onsale']"))) {

            Assert.assertEquals(element.getText(), "Sale!");
        }
    }

    @Test
    public void testCurrency() {

        for (WebElement element : getGetdriver().findElements(By.cssSelector("span[class*='currencySymbol']"))) {

            Assert.assertEquals(element.getText(), "$");
        }
    }

    @Test
    public void testDiscount() {

        Assert.assertEquals(getGetdriver().findElement(By.cssSelector("h3[class*='medium-font-size']")).
                getText(), "25% OFF On all products");
    }

    @Test
    public void testAccount() {

        getGetdriver().findElement(By.cssSelector("li#menu-item-1237")).click();

        Assert.assertEquals(getGetdriver().getCurrentUrl(), "https://askomdch.com/account/");
    }

    @Test
    public void testShop() {

        getGetdriver().findElement(By.cssSelector("a.wp-block-button__link")).click();

        Assert.assertEquals(getGetdriver().getCurrentUrl(), "https://askomdch.com/store");
    }

    @Test
    public void testProductsNumber() {

        Assert.assertEquals(getGetdriver().findElement(By.cssSelector("ul.products.columns-5")).
                findElements(By.tagName("li")).size(), 5);
    }

    @Test
    public void testColour() {

        getGetdriver().findElement(By.cssSelector("a[href*='men']")).click();

        Assert.assertEquals(getGetdriver().findElement(By.cssSelector("button[value='Search']")).
                getCssValue("background-color"), "rgba(49, 151, 214, 1)");
    }

    @Test
    public void testFindMoreButton() {

        getGetdriver().findElement(By.cssSelector("div.wp-block-button.is-style-fill")).click();

        Assert.assertEquals(getGetdriver().getTitle(), "Contact Us – AskOmDch");
    }
}

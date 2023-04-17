package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ElenaTsTest extends BaseTest {

    @Test
    public void testTitle() {
        getDriver().get("https://www.ebay.com/");
        Assert.assertEquals(getDriver().getTitle(), "Electronics, Cars, Fashion, Collectibles & More | eBay");
    }

    @Test
    public void testFindProductByBrandName() throws InterruptedException {
        getDriver().get("https://www.ebay.com/");
        WebElement searchField = getDriver().findElement(By.xpath("//input [@class='gh-tb ui-autocomplete-input']"));
        searchField.sendKeys("Samsung");
        searchField.sendKeys(Keys.RETURN);
        Thread.sleep(2000);

        WebElement result = getDriver().findElement(By.xpath("(//span[@role='heading'])[2]"));

        Assert.assertEquals(result.getText().substring(0, 7), "Samsung");
    }

    @Test
    public void testReturnToMainPage() throws InterruptedException {
        getDriver().get("https://www.ebay.com/");
        WebElement menuButton = getDriver().findElement(By.xpath("//li[@data-currenttabindex ='0']"));
        menuButton.click();
        Thread.sleep(2000);

        WebElement siteIcon = getDriver().findElement(By.xpath("//a[@id='gh-la']"));
        siteIcon.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.ebay.com/");
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        getDriver().get("https://www.bergfreunde.eu/");

        WebElement searchIcon = getDriver().findElement(By.xpath("//input[@title='Start your search!']"));
        searchIcon.click();

        WebElement searchField = getDriver().findElement(By.xpath("//input[@class='searchfield show-for-small-only']"));
        searchField.sendKeys("Bottle");
        searchField.sendKeys(Keys.RETURN);

        WebElement productImg = getDriver().findElement(By.xpath("(//img[@class='product-image'])[1]"));
        productImg.click();

        WebElement addToCurtButton = getDriver().findElement(By.xpath("//button[contains(@id,'addToCartButton')]"));
        addToCurtButton.click();

        Thread.sleep(2000);
        WebElement quantityOfProducts = getDriver().findElement(By.xpath("//td[text()>=1]"));

        Assert.assertEquals(quantityOfProducts.getText(), "1");
    }

    @Test
    public void testRegistrationWithInvalidEmail() throws InterruptedException {
        getDriver().get("https://www.bergfreunde.eu/");

        WebElement loginIcon = getDriver().findElement(By.xpath("//i[@class='icon login']"));
        loginIcon.click();
        Thread.sleep(1000);

        try {
            WebElement cookiesButton = getDriver().findElement(By.id("onetrust-accept-btn-handler"));
            if (cookiesButton.isDisplayed()) {
                cookiesButton.click();
            }
        } catch (NoSuchElementException ignored) {
        }

        Thread.sleep(1000);
        WebElement registrationButton = getDriver().findElement(By.id("login_create_form_show"));
        registrationButton.click();

        WebElement registrationEmailField = getDriver().findElement(By.id("lgn-usr"));
        registrationEmailField.sendKeys("qw@");

        WebElement registrationPasswordField = getDriver().findElement(By.id("lgn-pwd"));
        registrationPasswordField.click();

        WebElement warningMessage = getDriver().findElement(By.xpath("//p[@id='err-lgn-usr']"));

        Assert.assertEquals(warningMessage.getText(), "Your email address is incorrect. Please recheck your input");
    }
}

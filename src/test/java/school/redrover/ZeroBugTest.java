package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ZeroBugTest extends BaseTest {
    @Test (enabled = false)
    public void testSearchSelenium() {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);
        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }
    @Test (enabled = false)
    public void testGetWebFormTitle() {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);
        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        textBox.sendKeys("Selenium");
        submitButton.click();
        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
    @Test
    public void testGetTitle_1() {

        getDriver().get("https://askomdch.com/");

        String expectedHPResult = "AskOmDch";
        String actualHPResultHP = getDriver().findElement(By.xpath("//h1[@class='site-title']/.")).getText();
        Assert.assertEquals(actualHPResultHP,expectedHPResult,"Wrong text from header!");

        String expectedTextBtnMen = "Men";
        String actualTextBtnMen = "";
        List<WebElement> list = getDriver().findElements(By.xpath("//li[@id='menu-item-1226']/..//li"));
        for (WebElement element: list) {
            if (element.getText().equals(expectedTextBtnMen)) {
                actualTextBtnMen = element.getText();
                break;
            }
        }
        Assert.assertEquals(actualTextBtnMen, expectedTextBtnMen, "Element " + expectedTextBtnMen + " is not found" );
        WebElement men = getDriver().findElement(By.xpath("//*[@id='menu-item-1228']"));
        men.click();
        String textFromMenHeader = "Men";
        String actualTextFromMenPage = getDriver().findElement(By.xpath("//h1[.='Men']")).getText();
        Assert.assertEquals(actualTextFromMenPage,textFromMenHeader,"Wrong text from header");
    }

    @Test
    public void testGetTitle_2() {

        getDriver().get("https://askomdch.com/");

        String expectedHeaderHP = "Featured Products";
        String actualHeaderHP = getDriver().findElement(By.cssSelector(".wp-block-group__inner-container>h2")).getText();
        Assert.assertEquals(actualHeaderHP,expectedHeaderHP,"Header from Home Page do not match");

        String expectedMen = "Men";
        String actualHeaderMen = "";
        List <WebElement> listTabsHP = getDriver().findElements(By.xpath("//*[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//li"));
        for (WebElement tab:listTabsHP){
            if(tab.getText().equals(expectedMen)){
                tab.click();
                actualHeaderMen = getDriver().findElement(By.cssSelector(".woocommerce-products-header>h1")).getText();
                break;
            }
        }
        Assert.assertEquals(actualHeaderMen,expectedMen,"Text from header of Men Page do not match");
    }
    @Test
    public void testSearchProduct() {

        getDriver().get("https://askomdch.com/");

        String expectedResult = "jeans";
        getDriver().findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        getDriver().findElement(By.id("woocommerce-product-search-field-0")).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//button[@value='Search']")).click();
        String actualResult = getDriver().findElement(By.xpath("//h1[contains(.,'jeans')]")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult),"Search product is not working");
    }

    @Test
    public void testPublixOrderWrap() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        //built instance javascript executor
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        //open Chore and go to the test link
        getDriver().manage().window().maximize();
        getDriver().get("https://www.publix.com/pd/boars-head-italian-wrap/BMO-DSB-600561/builder?origin=collections5");

        //Add Cookie to get the Location available in headless mode
        Set<Cookie> cookies = getDriver().manage().getCookies();
        for(Cookie temp : cookies)    {
            System.out.println("Set-Cookie: " + temp.getName()+"="+temp.getValue() + "; " + "path=" + temp.getPath() + ";");
        }
        Cookie storeCookie1 = new Cookie("Store", "{%22CreationDate%22:%222023-04-20T19:02:09.690Z%22%2C%22ForceRefreshed%22:false%2C%22Option%22:%22ACFJLNORTY%22%2C%22ShortStoreName%22:%22Grandover%20Village%22%2C%22StoreName%22:%22Grandover%20Village%22%2C%22StoreNumber%22:1658}");
        Cookie storeCookie2 = new Cookie( "store_rpt","1");
        getDriver().manage().addCookie(storeCookie1);
        getDriver().manage().addCookie(storeCookie2);


        //Add ingredients in the wrap
        getDriver().navigate().refresh();
        WebElement elementSpinach = getDriver().findElement(By.xpath("//*[@data-id = 'Wrap']//p[.='Spinach']"));
        elementSpinach.click();

        WebElement elementCheddar = getDriver().findElement(By.xpath("//*[@data-id = 'Cheese']//p[.='Cheddar']"));
        jse.executeScript("window.scrollTo(0, 100)"); //scroll page
        elementCheddar.click();

        WebElement elementBacon = getDriver().findElement(By.xpath("//*[@data-id = 'Extras']//p[.='Bacon']"));
        jse.executeScript("window.scrollTo(100, 1200)");
        elementBacon.click();

        //verify ingredients
        String expectedArrIngredients = "Includes Genoa Salami, Tavern Ham, Cappacolla, Wrap, Spinach, Cheddar, Bacon, Lettuce, Tomato";
        String actualArrIngredients = getDriver().findElement(By.cssSelector("p[class='p-text paragraph-sm normal context--default color--null line-clamp']")).getText();
        Assert.assertEquals(actualArrIngredients,expectedArrIngredients,"Ingredients are incorrect");

        // Please Help!
        //Comment because this part of code method testPublixOrderWrap() does not work in headless mode chromeDriver.

        //click add order
/*
        jse.executeScript("window.scrollTo(0, 10)");
        WebElement addOrder = getDriver().findElement(By.cssSelector("button[id= 'builder-add-to-order-btn']"));
        wait.until(ExpectedConditions.elementToBeClickable(addOrder));
        addOrder.click();
        Thread.sleep(2000);
        String actualText = getDriver().findElement(By.cssSelector("button[id= 'builder-add-to-order-btn']>span")).getText();

        //verify order in the cart
        String expectedOrder = "Boar's Head® Italian Wrap";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-expanded='true']/span[@class='button__icon']")));
        String actualOrder = getDriver().findElement(By.xpath("//div[@class = 'title-wrapper']/a")).getText();
        Assert.assertEquals(actualOrder,expectedOrder,"The wrap is not in the cart");
*/
    }
}

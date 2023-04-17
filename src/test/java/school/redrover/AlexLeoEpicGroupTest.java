package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class AlexLeoEpicGroupTest extends BaseTest {

    @Test
    public void titleOfTheHomePageCheckedTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement icon = driver.findElement(By.xpath("//span[@class='onsale']"));
        Assert.assertEquals(icon.getText(), "Sale!");
        driver.quit();
    }

    @Test
    public void verifySaleSTas_TC_001_04() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        WebElement saleSign = driver.findElement(By.className("onsale"));
        Assert.assertEquals(saleSign.getText(), "Sale!");

        driver.quit();
    }

    @Test
    public void buttonOpened() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");
        String url = "https://askomdch.com/store";

        driver.findElement(By.cssSelector("div.wp-block-button>a[href=\"/store\"]")).click();
        Assert.assertEquals(url, "https://askomdch.com/store");
        driver.quit();
    }

    @Test
    public void testColourOfButton() {

        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.cssSelector("a[href*='men']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("button[value='Search']")).
                getCssValue("background-color"), "rgba(49, 151, 214, 1)");
    }

    @Test
    public void testAssertDiscountInfo() {

        final String DISCOUNT_INFO = "25% OFF On all products";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        WebElement textDiscount = driver.findElement(By.xpath("//h3[text()='25% OFF On all products']"));

        Assert.assertEquals(textDiscount.getText(), DISCOUNT_INFO);

        driver.quit();
    }

    @Test
    public void testCheckDiscount() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://askomdch.com/");
        WebElement discount = getDriver().findElement(By.xpath("//h3[contains(text(), \"25% OFF \")]"));

        Assert.assertEquals(discount.getText(), "25% OFF On all products");
    }

    @Test
    public void testClickAccountButton() {

        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//li[@id='menu-item-1237']/a")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askomdch.com/account/");
    }

    @Test
    public void testProductsNumber() {

        getDriver().get("https://askomdch.com/");

        Assert.assertEquals(getDriver().findElement(By.cssSelector("ul.products.columns-5")).
                findElements(By.tagName("li")).size(), 5);
    }

    @Test
    public void testCurrency() {

        getDriver().get("https://askomdch.com/");

        for (WebElement element : getDriver().findElements(By.cssSelector("span[class*='currencySymbol']"))) {

            Assert.assertEquals(element.getText(), "$");
        }
    }

    @Test
    public void VerifyTheLoginPage_TC() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        driver.findElement(By.xpath("//span[@class='onsale']")).isDisplayed();
        WebElement shopNowButton = driver.findElement(By.xpath("//a[@class='wp-block-button__link']"));
        shopNowButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store");
        driver.quit();

    }

    @Test
    public void MikeBTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));


        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/");

        driver.findElement(By.xpath("//a[@class=\"wp-block-button__link\"]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store");


    }

    @Test
    public void testFindGitHubEl() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://www.jenkins.io/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.jenkins.io/");

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"sponsorsblock\"]/div[1]/ul/li[6]/a/img"))
                .isDisplayed());
    }

    @Test
    public void test_006() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");

        String textBox = driver.findElement(By.xpath("//h2[@class='has-text-align-center']")).getText();

        Assert.assertEquals(textBox, "Featured Products");
        List<WebElement> products = driver.findElements(By.className("type-product"));
        Assert.assertEquals(products.size(), 5);
        driver.quit();

    }

    @Test
    public void verifySaleIconIsDisplayedArtemTTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        try {
            getDriver().get("https://askomdch.com/");
            WebElement featuredProducts = getDriver()
                    .findElement(By.xpath("//*[contains(text(),'Featured Products')]"));
            List<WebElement> saleProducts = featuredProducts
                    .findElements(By.xpath("//li[contains(@class, 'ast-article-single')]" +
                            "[count(.//span[contains(@class, 'woocommerce-Price-currencySymbol')]) > 1]"));
            for (WebElement saleProduct : saleProducts) {
                assertTrue(saleProduct.findElement(By.cssSelector("span.onsale"))
                        .isDisplayed(), "Sale icon not found on a sale product: " + saleProduct.getText());
            }
        } finally {
            getDriver().quit();
        }
    }


    @Test
    public void verifyShopNowLinkArtemTTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

            getDriver().get("https://askomdch.com/");
            getDriver().findElement(By.xpath("//a[@class='wp-block-button__link' and text()='Shop Now']"))
                    .click();
            String URL = getDriver().getCurrentUrl();

            Assert.assertEquals(URL, "https://askomdch.com/store");
    }

    @Test
    public void verifyFindMoreLinkArtemTTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        try {
            getDriver().get("https://askomdch.com/");
            getDriver().findElements(By.cssSelector("a.wp-block-button__link")).get(1).click();
            String URL = getDriver().getCurrentUrl();
            Assert.assertEquals(URL, "https://askomdch.com/contact-us/");
        } finally {
            getDriver().quit();
        }
    }

    @Test
    public void verifyTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("https://askomdch.com");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        WebElement saleSign = driver.findElement(By.className("onsale"));
        Assert.assertEquals(saleSign.getText(), "Sale!");

        driver.quit();
    }


    @Test
    public void testLoginPageIsOpenedTC_004() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@id=\"menu-item-1237\"]")).click();
        String expectedUrl = "https://askomdch.com/account/";
        driver.get(expectedUrl);
        try {
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
            System.out.println("Navigated to the correct login page");
        } catch (Throwable pageNavigationError) {
            System.out.println("Did not navigate to correct page");
        }
        Thread.sleep(2000);
        driver.quit();

    }

    @Test
    public void testDollarSignDisplayed() {
        List<WebElement> listFeaturedProductsText = getDriver().findElements(By.className("astra-shop-summary-wrap"));

        for (WebElement webElement : listFeaturedProductsText) {
            String[] arrProductText = webElement.getText().split("\n");

            Assert.assertEquals(arrProductText[2].charAt(0), '$');
        }
    }

    @Test
    public void dollarSignTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price']"));
        for (WebElement price : prices) {
            Assert.assertTrue(price.getText().contains("$"));
        }
        driver.quit();
    }

    @Test
    public void testVerifyDiscountMessagePresented() {
        getDriver().get("https://askomdch.com");

        WebElement text = getDriver().findElement(By.xpath("//h3[contains(text(), '25%')]"));

        Assert.assertEquals(text.getText(), "25% OFF On all products");
    }

    @Test
    public void testSaleIconVerification() {
        getDriver().get("https://askomdch.com/");
        WebElement featuredProductsElement = getDriver().findElement(By.xpath("//h2[@class='has-text-align-center']"));
        List<WebElement> featuredProducts = featuredProductsElement.findElements(By.xpath(
                "//div[@class='astra-shop-thumbnail-wrap']/parent::li"));
        for (WebElement featuredProduct : featuredProducts) {
            try {
                String value = featuredProduct.findElement(By.xpath("//del[@aria-hidden='true']"))
                        .getAttribute("aria-hidden");
                if (value.equals("true")) {
                    String sale = featuredProduct.findElement(By.xpath("//span[@class='onsale']")).getText();
                    Assert.assertEquals(sale, "Sale!");
                }
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    public void testDollarSignVerification() {
        getDriver().get("https://askomdch.com/");
        WebElement featuredProductsElement = getDriver().findElement(By.xpath("//h2[@class='has-text-align-center']"));
        List<WebElement> featuredProducts = featuredProductsElement.findElements(By.xpath(
                "//span[@class='woocommerce-Price-currencySymbol']"));
        for (WebElement featuredProduct : featuredProducts) {
            String sign = featuredProduct.getText();
            Assert.assertEquals(sign, "$");
        }
    }

    @Test
    public void testDiscountMessageVerification() {
        getDriver().get("https://askomdch.com/");
        String actualText = getDriver().findElement(By.xpath("//h3[@class='has-text-align-center has-white-color " +
                "has-text-color has-medium-font-size']")).getText();

        String expectedText = "25% OFF On all products";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testLoginPageVerification() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//a[@href='https://askomdch.com/account/' and text()='Account']")).click();

        String url = "https://askomdch.com/account/";
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

    @Test
    public void testStorePageVerification() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//a[@href='https://askomdch.com/store/' and text()='Store']")).click();

        String url = "https://askomdch.com/store/";
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

    @Test
    public void testNumberOfProductsVerification() {
        getDriver().get("https://askomdch.com/");
        WebElement featuredProductsElement = getDriver().findElement(By.xpath("//h2[@class='has-text-align-center']"));
        List<WebElement> featuredProducts = featuredProductsElement.findElements(By.xpath(
                "//div[@class='astra-shop-thumbnail-wrap']/parent::li"));

        Assert.assertEquals(featuredProducts.size(), 5);
    }

    @Test
    public void testBackgroundColorVerification() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//a[@href='https://askomdch.com/product-category/men/']")).click();
        WebElement button = getDriver().findElement(By.xpath("//button[@type='submit' and @value='Search']"));

        String expectedColor = "rgba(49, 151, 214, 1)";
        Assert.assertEquals(button.getCssValue("background-color"), expectedColor);
    }

    @Test
    public void testFindMorePageVerification() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//a[@href='https://askomdch.com/contact-us/' and text()='Find More']")).click();

        String actaulPage = getDriver().getTitle().substring(0, 10);
        String expectedPage = "Contact Us";
        String actualLink = getDriver().getCurrentUrl();
        String expectedLink = "https://askomdch.com/contact-us/";

        Assert.assertEquals(actaulPage, expectedPage);
        Assert.assertEquals(actualLink, expectedLink);
    }

    @Test
    public void testMenuBar() {
        getDriver().get("https://askomdch.com/");
        WebElement menu = getDriver().findElement(By.xpath("//ul[@id='ast-hf-menu-1']"));
        List<WebElement> products = menu.findElements(By.tagName("li"));

        Assert.assertEquals(products.size(), 8);

    }

    @Test
    public void testSale() {

        getDriver().get("https://askomdch.com/");

        for (WebElement element : getDriver().findElements(By.cssSelector("span[class='onsale']"))) {

            Assert.assertEquals(element.getText(), "Sale!");
        }
    }

    @Test
    public void testDiscount() {

        getDriver().get("https://askomdch.com/");

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h3[class*='medium-font-size']")).
                getText(), "25% OFF On all products");
    }


    @Test
    public void testVerifyStorePageOpened() {
        getDriver().get("https://askomdch.com");

        getDriver().findElement(By.cssSelector("a[href='/store']")).click();
        WebElement store = getDriver().findElement(By.xpath("//header[contains(@class, 'woocommerce-products-header')]//following::h1"));

        Assert.assertEquals(store.getText(), "Store");
    }

    @Test
    public void testVerifyCardOnWomenPage() {

        getDriver().get("https://askomdch.com");
        getDriver().findElement(By.xpath("//li[@id='menu-item-1229']/a")).click();
        List<WebElement> womenProducts = getDriver().findElements(By.xpath("//div[@class='astra-shop-thumbnail-wrap']"));

        Assert.assertEquals(womenProducts.size(), 7);
    }

    @Test
    public void testVerifyContactUsPageDisplayed() {

        getDriver().get("https://askomdch.com");
        getDriver().findElement(By.xpath("//*[contains(text(),'Find More')]")).click();
        WebElement textOnPageContactUs = getDriver().findElement(
                By.xpath("//*[@id='post-60']/div/div[1]/div/h1"));

        Assert.assertEquals(textOnPageContactUs.getText(), "Contact Us");
    }

    @Test
    public void testVerifySearchBackgroundButtonColorOnMenPage() {

        getDriver().get("https://askomdch.com");
        getDriver().findElement(By.xpath("//*[@id='menu-item-1228']/a")).click();
        String backgroundButtonColor = getDriver().findElement(
                        By.xpath("//*[@id='woocommerce_product_search-1']/form/button"))
                .getCssValue("background-color");

        Assert.assertEquals(backgroundButtonColor, "rgba(49, 151, 214, 1)");
    }

    @Test
    public void testVerifyLoginPageOpened() {
        getDriver().get("https://askomdch.com");

        getDriver().findElement(By.linkText("Account")).click();

        Assert.assertEquals(getDriver().findElement(By.className("has-text-align-center")).getText(), "Account");
    }

    @Test
    public void testLoginPageOpened() {

        getDriver().get("https://askomdch.com/");

        WebElement accountButton = getDriver().findElement(By.xpath(
                "// li[@id='menu-item-1237']//a[contains(normalize-space(),'Account')]"));
        accountButton.click();
        String openedURL = getDriver().getCurrentUrl();

        Assert.assertEquals(openedURL, "https://askomdch.com/account/");
    }

    @Test
    public void testTheStorePageOpened() {
        getDriver().get("https://askomdch.com/");

        WebElement shopNowButton = getDriver().findElement(By.xpath("//a[contains(text(),'Shop Now')]"));
        shopNowButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://askomdch.com/store");
    }

    @Test
    public void testNumberOfFeaturedProducts() {
        getDriver().get("https://askomdch.com/");

        WebElement featuredProductsBlock = getDriver().findElement(By.xpath(
                "//ul[@class='products columns-5']"));
        List<WebElement> items = featuredProductsBlock.findElements(By.tagName("li"));

        Assert.assertEquals(items.size(), 5);
    }

    @Test
    public void testBackgroundColor() {
        getDriver().get("https://askomdch.com/");

        getDriver().findElement(By.cssSelector("a[href='https://askomdch.com/product-category/men/']")).click();
        WebElement buttonSearch = getDriver().findElement(By.cssSelector("button[value='Search']"));

        Assert.assertEquals(buttonSearch.getCssValue("background-color"), "rgba(49, 151, 214, 1)");
    }

    @Test
    public void testVerifyPromoText() {
        getDriver().get("https://askomdch.com");
        WebElement promoText = getDriver().findElement(By.xpath(".//h3[@class = 'has-text-align-center has-white-color has-text-color has-medium-font-size']"));

        Assert.assertEquals(promoText.getText(), "25% OFF On all products");
    }

    @Test
    public void testAccessoriesDropDownMenuSectionsNumber() {

        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.xpath("//a[text()='Accessories']")).click();
        WebElement dropDownMenu = getDriver().findElement(By.cssSelector("select.orderby[name='orderby']"));
        dropDownMenu.click();
        List<WebElement> dropDownMenuSections = dropDownMenu.findElements(By.tagName("option"));
        dropDownMenuSections.get(4).click();
        Assert.assertEquals(dropDownMenuSections.size(), 6);

    }

    @Test
    public void testProductsDisplayed() {
        getDriver().get("https://askomdch.com/");
        List<WebElement> list = getDriver().findElements(By.className("astra-shop-thumbnail-wrap"));

        Assert.assertEquals(list.size(), 5);
    }
    @Test
    public void testAddProductToCartInscription() {
            getDriver().get("https://askomdch.com/");

            getDriver().findElement(By.xpath("//a[@class='menu-link'][text()='Store']")).click();
            getDriver().findElement(By.xpath("//h2[text()='Blue Denim Shorts']")).click();
            getDriver().findElement(By.xpath("//button[@name='add-to-cart'][text()='Add to cart']")).click();
            WebElement addInscription = getDriver().findElement(By.xpath("//div[@role='alert']"));

            Assert.assertTrue(addInscription.getText().contains("Blue Denim Shorts"));
    }
    @Test
    public void testAddedProductInTheCart() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://askomdch.com/");

        getDriver().findElement(By.xpath("//a[@class='wp-block-button__link'][text()='Shop Now']"))
                .click();

        WebElement firstProductInList = getDriver()
                .findElement(By.cssSelector("div[class='ast-woocommerce-container'] > ul > li"));
        String productNameInList = firstProductInList
                .findElement(By.cssSelector("div[class='astra-shop-summary-wrap'] > a:first-child")).getText();
        firstProductInList.findElement(By.cssSelector("div[class='astra-shop-summary-wrap'] > a:last-child")).click();
        firstProductInList.findElement(By.cssSelector("a[title='View cart']")).click();
        WebElement productNameEl = getDriver().findElement(By.cssSelector("td[data-title='Product'] > a"));

        Assert.assertEquals(productNameEl.getText(), productNameInList);
    }

    @Test
    public void testCountInTheShoppingCartIcon() {
        getDriver().get("https://askomdch.com/");

        getDriver().findElement(By.xpath("//div/h2[text()='Featured Products']/../div/ul/li/div[2]/a[2]"))
                        .click();
        String valueInCart = getDriver()
                .findElement(By.xpath("//div[@class='ast-cart-menu-wrap']/span[@class='count']"))
                .getText();

        Assert.assertEquals(valueInCart, "0");
    }

    @Test
    public void testVerifySymbol() {
        getDriver().get("https://askomdch.com/");
        String text = getDriver().findElement(By.cssSelector("h2.has-text-align-center")).getText();

        Assert.assertEquals(text,"Featured Products");

        String symbol = getDriver().findElement(By.cssSelector("span.woocommerce-Price-currencySymbol")).getText();

        Assert.assertEquals(symbol,"$");
    }


    }


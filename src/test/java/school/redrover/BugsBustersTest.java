package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BugsBustersTest extends BaseTest {

    @Ignore
    @Test
    public void testAngelinaGiftCardSelection() {
        getDriver().get("https://www.broadway.com/");

        WebElement giftCardTab = getDriver().findElement(By.linkText("Gift Cards"));
        giftCardTab.click();

        WebElement buyNowButton = getDriver().findElement(By.linkText("Buy Now"));
        buyNowButton.click();

        WebElement continueToDeliveryButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        continueToDeliveryButton.click();

        WebElement text = getDriver().findElement(By.xpath("//div[@data-qa='subtotal-amount']//strong"));

        Assert.assertEquals(text.getText(), "$100.00");
    }

    @Test
    public void testSergeyConvert() {
        getDriver().get("https://www.unitconverters.net/weight-and-mass/lbs-to-kg.htm");

        WebElement textBox = getDriver().findElement(By.cssSelector("input#ucfrom.ucdcinput"));
        textBox.sendKeys("1");

        WebElement convert = getDriver().findElement(By.cssSelector("input.ucdcsubmit"));
        convert.click();

        WebElement text = getDriver().findElement(By.cssSelector("div#ucresult"));

        Assert.assertEquals(text.getText(), "Result: 1 pound = 0.45359237 kilogram");
    }

    @Ignore
    @Test
    public void testAngelinaSearchResults() {
        getDriver().get("https://www.broadway.com/");

        WebElement searchBox = getDriver().findElement(By.id("nav-typeahead-js"));
        searchBox.sendKeys("the phantom of the opera\n");

        WebElement text = getDriver().findElement(By.xpath("//a[@href='/shows/the-phantom-of-the-opera/']/h3"));

        Assert.assertEquals(text.getText(), "The Phantom of the Opera");
    }

    @Test
    public void testGetSeleniumWebDriverPageTitle() {
        getDriver().get("https://www.selenium.dev/");

        WebElement readMoreButton = getDriver().findElement(By.xpath("//a[@href='/documentation/webdriver/']"));
        readMoreButton.click();

        WebElement getStatedButton = getDriver().findElement(By.xpath("//a[@href='/documentation/webdriver/getting_started/']"));
        getStatedButton.click();

        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Getting started");
    }

    @Ignore
    @Test
    public void testMarynaSTitle() {
        getDriver().get("https://www.funartcolorado.com/");

        Assert.assertEquals(getDriver().getTitle(), "Home | Funart");
    }

    @Test
    public void testMarynaSArtTab() {
        getDriver().get("https://www.funartcolorado.com/");

        WebElement artTab = getDriver().findElement(By.id("comp-jnlr6x4r1label"));
        artTab.click();

        WebElement mosaicArtHeader = getDriver().findElement(By.cssSelector("div[id='comp-jbrwxjbr'] span"));

        Assert.assertEquals(mosaicArtHeader.getText(), "About my art:");
    }

    @Ignore
    @Test
    public void testMarynaSHeader() throws InterruptedException {
        getDriver().get("https://www.funartcolorado.com/");

        WebElement artTab = getDriver().findElement(By.id("comp-jnlr6x4r1label"));
        artTab.click();

        WebElement classesTab = getDriver().findElement(By.id("comp-jnlr6x4r2label"));
        classesTab.click();
        Thread.sleep(2000);

        WebElement classesHeader = getDriver().findElement(By.cssSelector("h1[class='font_0'] span"));

        Assert.assertEquals(classesHeader.getText(), "ONLINE course \"Fun Art with Tempered glass\"");
    }

    @Ignore
    @Test
    public void testContactPageHeader() throws InterruptedException {
        String expectedURL = "https://www.ohiofamilypractice.com/connect";
        String expectedHeader = "Contact Us".toLowerCase();

        getDriver().get("https://www.ohiofamilypractice.com/");
        Thread.sleep(1000);
        WebElement contactUsReference = getDriver().findElement(By.xpath("//div[@class='Header-nav-inner']/a[@href='/connect']"));
        contactUsReference.click();
        Thread.sleep(1000);
        WebElement h1 = getDriver().findElement(By.xpath("//h1"));
        String h1Text = h1.getText().toLowerCase();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
        Assert.assertEquals(h1Text, expectedHeader);
    }

    @Ignore
    @Test
    public void testMarynaSArtMosaicVideoHeader() {
        getDriver().get("https://www.funartcolorado.com/");

        WebElement mosaicArtHeader = getDriver().findElement(By.xpath("//p[@id='comp-jnlr6x4r2label']"));

        Assert.assertEquals(mosaicArtHeader.getText(), "Classes");
    }

    @Ignore
    @Test
    public void testMarynaSArtMosaicVideo() throws InterruptedException {
        getDriver().get("https://www.funartcolorado.com/");

        WebElement classesTab = getDriver().findElement(By.id("comp-jnlr6x4r2label"));
        classesTab.click();
        Thread.sleep(2000);

        WebElement playButton = getDriver().findElement(By.xpath("//div[@class='QzC0oQ E1QW47']//*[name()='svg']"));
        playButton.click();

        WebElement video = getDriver().findElement(By.tagName("video"));
        Thread.sleep(3000);

        Assert.assertTrue(Float.parseFloat(video.getAttribute("currentTime")) > 0);
    }

    @Test
    public void testMarynaLFirstTest() {
        getDriver().get("https://invoice-generator.com/");

        WebElement signInButton = getDriver().findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[3]/a"));
        signInButton.click();

        WebElement emailAddress = getDriver().findElement(By.xpath("//*[@id='inputEmail']"));
        emailAddress.sendKeys("victoria.bilanko@gmail.com");

        WebElement password = getDriver().findElement(By.xpath("//*[@id='inputPassword']"));
        password.sendKeys("Java12345@");

        WebElement signInBtn = getDriver().findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        signInBtn.click();

        WebElement userName = getDriver().findElement(By.xpath("//a[contains(text(), 'JavaTest')]"));

        Assert.assertEquals(userName.getText(), "JavaTest");
    }

    @Test
    public void testAnastasiyaFirstTest() {
        getDriver().get("https://demoqa.com/text-box");

        WebElement firstNameTextBox = getDriver().findElement(By.id("userName"));
        firstNameTextBox.sendKeys("First Last");

        WebElement emailTextBox = getDriver().findElement(By.xpath("//*[@id='userEmail']"));
        emailTextBox.sendKeys("blablabla@gmail.com");

        WebElement currentAddress = getDriver().findElement(By.xpath("//*[@id='currentAddress']"));
        currentAddress.sendKeys("2023 New Year St, NY");

        WebElement permanentAddress = getDriver().findElement(By.xpath("//*[@id='permanentAddress']"));
        permanentAddress.sendKeys("2024 New Year St, NY");

        WebElement submitButton = getDriver().findElement(By.id("submit"));
        submitButton.click();

        WebElement nameText = getDriver().findElement(By.xpath("//*[@id='name']"));

        Assert.assertEquals(nameText.getText(), "Name:First Last");
    }

    @Ignore
    @Test
    public void testOurServicesHeader() throws InterruptedException {
        String expectedURL = "https://www.ohiofamilypractice.com/services";
        String expectedHeader = "Our Services".toLowerCase();

        getDriver().get("https://www.ohiofamilypractice.com/");
        Thread.sleep(1000);
        WebElement servicesReference = getDriver().findElement(By.xpath("//a[@href='/services'][@class='Header-nav-item']"));
        servicesReference.click();
        Thread.sleep(1000);
        WebElement h1 = getDriver().findElement(By.xpath("//h1"));
        String h1Text = h1.getText().toLowerCase();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
        Assert.assertEquals(h1Text, expectedHeader);
    }

    @Ignore
    @Test
    public void testMariaAmazonSearch() {
        getDriver().get("https://www.amazon.com/");

        WebElement searchBox = getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("the marvelous mrs. maisel");
        searchBox.sendKeys(Keys.RETURN);

        WebElement searchResult = getDriver().findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));

        Assert.assertEquals(searchResult.getText(), "The Marvelous Mrs. Maisel – Season 5");
    }

    @Test
    public void testMariaLogin() {
        getDriver().get("https://www.saucedemo.com/");

        WebElement userName = getDriver().findElement(By.cssSelector("input[placeholder='Username']"));
        userName.sendKeys("standard_user");

        WebElement password = getDriver().findElement(By.cssSelector("input[placeholder='Password']"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        WebElement header = getDriver().findElement(By.cssSelector("div[class='app_logo']"));

        Assert.assertEquals(header.getText(), "Swag Labs");
    }

    @Test
    public void testAngelinaReservationButtonTitle() {
        getDriver().get("https://nailspaas.com/");

        String title = getDriver().getTitle();

        Assert.assertEquals(title, "Nail Spa A&S");
    }

    @Test
    public void testAngelinaReservationButton() {
        getDriver().get("https://nailspaas.com/");

        WebElement reservationButton = getDriver().findElement(By.xpath("//a[contains(text(),'Reservation')]"));

        Assert.assertEquals(reservationButton.getText(), "Reservation");
    }

    @Test
    public void testMariaAddItemsToCart() {
        getDriver().get("https://www.saucedemo.com/");

        getDriver().findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("standard_user");

        getDriver().findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("secret_sauce");

        getDriver().findElement(By.id("login-button")).click();

        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        getDriver().findElement(By.cssSelector(".shopping_cart_link")).click();

        WebElement item = getDriver().findElement(By.cssSelector(".inventory_item_name"));

        Assert.assertEquals(item.getText(), "Sauce Labs Backpack");
    }
}
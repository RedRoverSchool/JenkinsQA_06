package school.redrover.old;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Date;
@Ignore
public class JavaNiSyGroupTest extends BaseTest {
    @Ignore
    @Test
    public void testNewsPageMS() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://www.livelib.ru/news/post/118924-derzhim-v-kurse-tekuschij-status-rabot-i-problemy-na-sajte");

        WebElement btnNews = getDriver().findElement(By.cssSelector("[class=\"header-card-user__class\"] b"));
        btnNews.click();

        WebElement textNews = getDriver().findElement(By.cssSelector(".lists h1:nth-child(2"));

        Assert.assertEquals(textNews.getText(), "Новости");
    }

    @Ignore
    @Test
    public void testButtonsMS() {

        getDriver().get("https://demoqa.com/radio-button");
        WebElement radioBtn = getDriver().findElement(By.cssSelector("[for='yesRadio']"));
        radioBtn.click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector(".mt-3")).getText(), "You have selected Yes");
    }

    @Test
    public void testUscis() {

        getDriver().get("https://www.uscis.gov/");

        WebElement textBox = getDriver().findElement(By.id("gsc-i-id1"));
        textBox.sendKeys("citizenship and naturalization");
        textBox.sendKeys(Keys.RETURN);

        WebElement button = getDriver().findElement(By.name("Search"));
        button.click();

        WebElement text = getDriver().findElement(By.className("gsc-selected-option"));

        Assert.assertEquals(text.getText(), "Relevance");
    }

    @Test
    public void testGoogleTransl() {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("гугл переводчик");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']"));

        Assert.assertEquals(text.getText(), "Google Переводчик");
    }

    @Test
    public void testSignUpPlotnikov() throws InterruptedException {

        getDriver().get("http://shop.bugred.ru/user/register/index");

        WebElement inputName = getDriver().findElement(By.id("exampleInputName"));
        inputName.sendKeys("Ivan");

        WebElement inputEmail = getDriver().findElement(By.id("exampleInputEmail1"));
        inputEmail.sendKeys("test10@mail.ru");                                       // При каждом запуске необходимо изменить почту

        WebElement inputPassword1 = getDriver().findElement(By.id("exampleInputPassword1"));
        inputPassword1.sendKeys("123@@#aA");

        WebElement inputPassword2 = getDriver().findElement(By.id("exampleInputPassword2"));
        inputPassword2.sendKeys("123@@#aA");

        WebElement button = getDriver().findElement(By.name("_csrf"));
        button.click();

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//p[text() = 'Пользователь с таким email уже зарегистрирован!']"));
        Thread.sleep(2000);
        Assert.assertEquals(text.getText(), "Пользователь с таким email уже зарегистрирован!");
    }

    @Test
    public void testGetMessageDifferentPasswordPlotnikov() throws InterruptedException {

        getDriver().get("http://shop.bugred.ru/user/register/index");

        WebElement inputName = getDriver().findElement(By.id("exampleInputName"));
        inputName.sendKeys("Ivan");

        WebElement inputEmail = getDriver().findElement(By.id("exampleInputEmail1"));
        inputEmail.sendKeys("test12@mail.ru");                                       // При каждом запуске необходимо изменить почту

        WebElement inputPassword1 = getDriver().findElement(By.id("exampleInputPassword1"));
        inputPassword1.sendKeys("123@@#aA");

        WebElement inputPassword2 = getDriver().findElement(By.id("exampleInputPassword2"));
        inputPassword2.sendKeys("321@@#aA");

        WebElement button = getDriver().findElement(By.name("_csrf"));
        button.click();

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//p[text() = 'Пароль и повтор пароля не равны!']"));
        Thread.sleep(2000);
        Assert.assertEquals(text.getText(), "Пароль и повтор пароля не равны!");
    }

    @Test
    public void testPandaCareersLink() {

        getDriver().get("https://www.foodpanda.com/");

        WebElement careersButton = getDriver().findElement(By.cssSelector("#menu-item-1753 a"));
        String careersButtonText = careersButton.getText();

        Assert.assertEquals(careersButtonText, "CAREERS");
        careersButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://careers.foodpanda.com/");

    }

    @Test
    public void testJava() {
        getDriver().get("https://www.google.com");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("java\n");

        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Java | Oracle']"));

        Assert.assertEquals(text.getText(), "Java | Oracle");

    }

    @Test
    public void testEnglishHome() throws InterruptedException {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        getDriver().get("https://www.englishhome.bg/");
        Thread.sleep(1000);

        if (!getDriver().findElements(By.id("img_lightbox_close")).isEmpty()) {
            WebElement hotAttantion = getDriver().findElement(By.id("img_lightbox_close"));
            hotAttantion.click();
        }

        WebElement news = getDriver().findElement(By.id("web_push_hayir"));
        news.click();

        WebElement argeeCookies = getDriver().findElement(By.xpath("//div[@class = 'cookie']/a"));
        argeeCookies.click();

        WebElement textSearch = getDriver().findElement(By.name("search_text"));
        textSearch.clear();
        textSearch.sendKeys("чаши");

        WebElement buttonSearch = getDriver().findElement(By.xpath("//button[@class = 'search__button hero-button gray js-search-button']"));
        buttonSearch.click();

        Thread.sleep(1000);
        WebElement textFilter = getDriver().findElement(By.xpath("//label[@class = 'list__info__filter']/span"));
        Assert.assertEquals(textFilter.getText(), "чаши");
    }

    @Test
    public void testYogaShopBtn() {

        getDriver().get("https://magento.softwaretestingboard.com/");

        WebElement yogaShopBtn = getDriver().findElement(By.xpath("//span[@class='action more button']"));
        yogaShopBtn.click();

        WebElement text = getDriver().findElement(By.xpath("//span[@class='base']"));

        Assert.assertEquals(text.getText(), "New Luma Yoga Collection");
    }

    @Test
    public void testPandaLocation() {

        getDriver().get("https://www.foodpanda.com/");

        WebElement locationImage = getDriver().findElement(By.xpath("//button[@class='btn btn-white']"));
        locationImage.click();

        WebElement btnSubmit = getDriver().findElement(By.id("gform_submit_button_2"));
        btnSubmit.click();

        WebElement errorSummary = getDriver().
                findElement(By.xpath("//*[@id='gform_2_validation_container']/h2"));

        Assert.assertEquals(errorSummary.getText(),
                "THERE WAS A PROBLEM WITH YOUR SUBMISSION. PLEASE REVIEW THE FIELDS BELOW.");
    }
    @Ignore
    @Test
    public void testDrive2Audi() {

        getDriver().get("https://www.drive2.ru/");

        WebElement textBox = getDriver().findElement(By.name("text"));
        textBox.sendKeys("Audi Q5");

        WebElement button = getDriver().findElement(By.xpath("/html/body/main/div[1]/form/button"));
        button.click();

        WebElement text = getDriver().findElement(By.xpath("/html/body/main/div/div[2]/div[1]/div/header/h1"));
        Assert.assertEquals(text.getText(), "Результаты поиска");
    }
}

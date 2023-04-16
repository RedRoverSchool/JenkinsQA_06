package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

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
    public void testUscis() throws InterruptedException {

        getDriver().get("https://www.uscis.gov/");
        WebElement textBox = getDriver().findElement(By.id("gsc-i-id1"));

        textBox.sendKeys("citizenship and naturalization");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement button = getDriver().findElement(By.name("Search"));
        button.click();

        WebElement text = getDriver().findElement(By.className("gsc-selected-option"));

        Assert.assertEquals(text.getText(), "Relevance");
    }

    @Test
    public void testGoogleTransl() throws InterruptedException {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("гугл переводчик");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

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
    public void testJava(){

        getDriver().get("https://www.google.com");

        WebElement textBox =  getDriver().findElement(By.name("q"));
        textBox.sendKeys("java\n");

        WebElement text =  getDriver().findElement(By.xpath("//h3[text() = 'Java | Oracle']"));

        Assert.assertEquals(text.getText(), "Java | Oracle");
    }

}


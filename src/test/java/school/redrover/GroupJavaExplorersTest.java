package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class GroupJavaExplorersTest {

    @Test
    public void testTrelloTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        String url = "https://trello.com/";
        String expTitle = "Manage Your Team’s Projects From Anywhere | Trello";

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);

        Assert.assertEquals(driver.getTitle(), expTitle);

        driver.quit();
    }

    @Test
    public void vhodTextOnAuthPageTest() throws InterruptedException {

        //проверка того, что на окошке со входом над полями Логина и пасса есть слово Вход
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        //"--remote-allow-origins=*", "--headless" --- params for server

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.21vek.by/");             //проходим на сайт

        Thread.sleep(1000);

        WebElement buttonCookie = driver.findElement(    //прошли выплывашку с куки
                By.xpath("//button[@class='Button-module__button Button-module__blue-primary']"));
        buttonCookie.click();

        Thread.sleep(1000);

        WebElement buttonAccount = driver.findElement(   // нажали на Аккаунт
                By.xpath("//button[@class='styles_userToolsToggler__imcSl']"));
        buttonAccount.click();

        Thread.sleep(1000);

        WebElement buttonVoiti = driver.findElement(   // нажали на Войти
                By.xpath("//button[@data-testid='loginButton']"));
        buttonVoiti.click();

        Thread.sleep(1000);

        WebElement textVhod = driver.findElement(   // нашли текст вверху, в центре, где должно быть ВХОД
                By.xpath("//h5[@class='style_formTitle__hRNRz']"));
        Assert.assertEquals(textVhod.getText(), "Вход");

        driver.quit();
    }


    @Test
    public void TestS() {
    }

    @Ignore
    @Test
    public void testOracleSearch() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://docs.oracle.com/en/");

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("Java");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement text = driver.findElement(By.cssSelector("span[class*='search-title']"));
        Assert.assertEquals(text.getText(), "Java Management");

        driver.quit();
    }

    public void TestAfterSet ()
    {
        System.out.println("Тест после перенастроек всего и вся");
    }


}

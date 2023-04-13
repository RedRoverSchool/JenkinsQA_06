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


public class HelloWorldTest {

    @Test
    public void getUrlTest (){
   ChromeOptions chromeOptions = new ChromeOptions();
     chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        for (int i=0;i<3;i++){
            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.get("http:/google.com");
            driver.quit();
        }
    }

@Ignore
@Test

    public void firstTestOK() throws InterruptedException {
//      ChromeOptions chromeOptions = new ChromeOptions();
//      chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver= new ChromeDriver();
        driver.get("https://ya.ru/");
        WebElement news= driver.findElement(By.xpath("//*[@id=\"text\"]"));
        news.sendKeys("Moscow");
        Thread.sleep(2000);
      //  WebElement but = driver.findElement(By.xpath("/html/body/main/div[3]/form/div[2]/button"));
      //  but.click();
        Thread.sleep(2000);

      //  WebElement text  = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[1]/div/div[1]/a/h2/span"));
        //Assert.assertEquals(text.getText(),  "Moscow - Wikipedia");
        driver.quit();
    }

    @Test
    public void printTest (){
        System.out.println("Hi");
    }

    @Test
    public void wikiTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
       chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver= new ChromeDriver(chromeOptions);
        driver.get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        WebElement but = driver.findElement(By.xpath("//*[@id=\"Добро_пожаловать_в_Википедию,\"]/a"));
        but.click();
        Thread.sleep(2000);
        WebElement txt = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));
        Assert.assertEquals(txt.getText(), "Википедия");

        driver.quit();

    }

    @Test
    public void wikiSeleniumTest(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://ru.wikipedia.org/");
        WebElement search = driver.findElement(By.xpath("//*[@id='searchInput']"));
        search.sendKeys("selenium");

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id='searchButton']"));
        searchBtn.click();
        WebElement selPage = driver.findElement(By.xpath("//*[@id='firstHeading']/span"));

        Assert.assertEquals(selPage.getText(), "Selenium");
        driver.quit();
    }

    @Test

    public void wikTest(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.wikipedia.org/");
        WebElement textBox = driver.findElement(By.name("search"));
        textBox.sendKeys("Xpath");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));
        Assert.assertEquals(text.getText(),"XPath");

        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        //chromeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class=\"wp-block-button__link\"]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store");

        Thread.sleep(3000);
        driver.quit();
    }



    @Test
    public void bazhTestZoolegenda() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://zoolegenda.ru/");

        WebElement button = driver.findElement(By.cssSelector("[href='/catalog/koshki']"));
        Thread.sleep(3000);
        button.click();

        WebElement text = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(text.getText(), "КОШКИ");

        driver.quit();
    }

    @Test
    public void SimpleTest() {
        System.out.println("It's work");
    }

    @Test
    public void seleniumTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.ru/");
        WebElement inputField = driver.findElement(By.name("q"));
        inputField.sendKeys("Selenium");
        inputField.sendKeys(Keys.ENTER);
        driver.quit();
    }

    @Test
    public void bazhTestYandexSignInError() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://passport.yandex.ru/registration?retpath=https%3A%2F%2Fsso.passport.yandex.ru%2Fprepare%3Fuuid%3Db258193d-7833-453a-882c-4c071ee1a3a9%26goal%3Dhttps%253A%252F%252Fya.ru%252F%26finish%3Dhttps%253A%252F%252Fpassport.yandex.ru%252F&process_uuid=6e0d6f36-3d71-4915-bbc6-18cf579d4abe");
        Thread.sleep(3000);

        // allow cookies
//        WebElement cookies = driver.findElement(By.cssSelector("[data-id=\"button-all\"]"));
//        cookies.click();

        // test name
        WebElement firstname = driver.findElement(By.cssSelector("[data-t=\"field:input-firstname\"]"));
        firstname.sendKeys(" " + "\n");

        WebElement error = driver.findElement(By.cssSelector("[class=\"error-message\"]"));
        Thread.sleep(3000);
        Assert.assertEquals(error.getText(), "Пожалуйста, укажите имя");

        // test lastname
        WebElement lastname = driver.findElement(By.cssSelector("[data-t=\"field:input-lastname\"]"));
        lastname.sendKeys(" ");
        lastname.click();

        WebElement error2 = driver.findElement(By.cssSelector("[class=\"error-message\"]"));
        Thread.sleep(3000);
        Assert.assertEquals(error2.getText(), "Пожалуйста, укажите фамилию");

        // test login
        WebElement login = driver.findElement(By.cssSelector("[data-t=\"field:input-login\"]"));
        login.sendKeys(" ");
        login.click();
        Thread.sleep(3000);

        WebElement error3 = driver.findElement(By.cssSelector("[data-t=\"login-error\"]"));
        Assert.assertEquals(error3.getText(), "Необходимо выбрать логин");

        driver.quit();
    }

}

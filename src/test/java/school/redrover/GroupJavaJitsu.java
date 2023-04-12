package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class GroupJavaJitsu {

    @Test
    public void testCarServiceOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.homesteadhyundai.net/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement serviceMenu = driver.findElement(By.xpath("//span[text()='Service/Parts']"));
        serviceMenu.click();

        List<WebElement> options = serviceMenu.findElements(By.tagName("li"));
        Assert.assertEquals(options.size(), 14);

        driver.quit();
    }

    @Test
    public void testAriumFindACommunity() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://ariumliving.com/");

        WebElement inputElement = driver.findElement(By.name("search"));
        inputElement.sendKeys("Arium Seaglass");
        inputElement.sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(), "https://ariumliving.com/find-a-community/?search=Arium+Seaglass");

        driver.quit();

    }

    @Test
    public void tema_openCartRegistrationTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");
        driver.findElement(By.id("input-firstname")).sendKeys("Tema");
        driver.findElement(By.id("input-lastname")).sendKeys("Shvets");
        driver.findElement(By.id("input-email")).sendKeys("temaemail123@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123xyz");
        driver.quit();
    }

    @Test
    public void testFirst_RedRover() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = driver.findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = driver.findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = driver.findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = driver.findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = driver.findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = driver.findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
        driver.quit();
    }

    @Test
    public void testJenkins() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jenkins.io/");

        WebElement But = driver.findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = driver.findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = driver.findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = driver.findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(), "Step 1: Setup wizard");
        driver.quit();
    }
    @Test
    public void testForm() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com");
        WebElement ButtonForms = driver.findElement(By.xpath("//div[@class='home-body']//div[2]//div[1]//div[2]//*[name()='svg']"));
        ButtonForms.click();

        WebElement ButtonPracticeForm = driver.findElement(By.xpath("(//li[@id='item-0'])[2]"));
        ButtonPracticeForm.click();

        WebElement formNameFirst = driver.findElement(By.id("firstName"));
        formNameFirst.sendKeys("Andrey");
        WebElement formNameLast = driver.findElement(By.id("lastName"));
        formNameLast.sendKeys("Pomaz");

        WebElement formEmail = driver.findElement(By.id("userEmail"));
        formEmail.sendKeys("Test@mail.com");

        WebElement genderMale = driver.findElement(By.className("custom-control-label"));
        genderMale.click();

        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("5555555555");

        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        dateOfBirth.findElement(By.xpath("(//select[@class='react-datepicker__month-select'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='8'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='1989'])[1]")).click();
        dateOfBirth.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[5]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[4]/div[7]")).click();

        WebElement checkboxHobbies = driver.findElement(By.xpath("(//label[normalize-space()='Sports'])[1]"));
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Sports'])[1]")).click();
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Music'])[1]")).click();

        WebElement address = driver.findElement(By.id("currentAddress"));
        address.sendKeys("Test Address");

        WebElement ButSubmit = driver.findElement(By.id("submit"));
        ButSubmit.click();

        WebElement got = driver.findElement(By.xpath("(//div[@id='example-modal-sizes-title-lg'])[1]"));
        Thread.sleep(2000);
        Assert.assertEquals(got.getText(), "Thanks for submitting the form");
        driver.quit();
    }



    @Test
    public void testAboutDoctorsFind() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://prodoctorov.ru/");
        String title = driver.getTitle();
        Assert.assertEquals("ПроДокторов – сайт отзывов пациентов о врачах №1 в России", title);
        WebElement urlElement = driver.findElement(By.className("b-choose-town-btn-v2"));
        urlElement.click();
        WebElement inputElementSearch = driver.findElement(By.className("b-choose-town-popup__search-input"));
        inputElementSearch.sendKeys("Краснодар");
        WebElement SearchboxElement = driver.findElement(By.className("tt-dataset"));
        Thread.sleep(2000);
        SearchboxElement.click();
        Thread.sleep(2000);
        WebElement inputElement = driver.findElement(By.className("text-field__input"));
        Thread.sleep(2000);
        inputElement.sendKeys("Ницакова Марина Петровна");
        WebElement submitButton = driver.findElement(By.className("base-search__button"));
        submitButton.click();
        WebElement link = driver.findElement(By.className("b-card__name-link"));
        link.click();
        WebElement text = driver.findElement(By.className("ui-text"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://prodoctorov.ru/krasnodar/vrach/177664-nicakova/");
        driver.quit();
    }
    @Test
    public void testAlex() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/");
        WebElement element= driver.findElement(By.xpath("//a[@href=\"https://www.globalsqa.com/training/appium-online-training/\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
        WebElement schedule=driver.findElement(By.xpath("//li[@id='Batch Schedule']"));
        schedule.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", schedule);
        WebElement enroll=driver.findElement(By.xpath("(//a[@href=\"https://www.instamojo.com/globalsqa/appium-training/\"])[2]"));
        enroll.click();
        driver.quit();
    }

    @Test
    public void testLoginAnton () throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement userElement = driver.findElement(By.id("user-name"));
        userElement.sendKeys("standard_user");

        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }


    public static void main(String[] args) {
        System.out.println("testLoginNatasha");
        System.out.println("testLogin2");
    }

    @Test
    public void testArtem() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        WebElement buttonElements = driver.findElement(By.xpath("//div[@class='card mt-4 top-card'][1]//*[name()='svg']"));
        buttonElements.click();

        WebElement textBox = driver.findElement(By.xpath("//span[text()='Text Box']"));
        textBox.click();

        WebElement fullNameField = driver.findElement(By.id("userName"));
        fullNameField.sendKeys("Artem De");

        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.sendKeys("test@gmail.com");

        WebElement currentAddressField = driver.findElement(By.id("currentAddress"));
        currentAddressField.sendKeys("123 Main St, Anytown USA");

        WebElement permanentAddressField = driver.findElement(By.id("permanentAddress"));
        permanentAddressField.sendKeys("456 Oak St, Anytown USA");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement successfullySubmitted = driver.findElement(By.id("name"));
        Assert.assertEquals(successfullySubmitted.getText(), "Name:Artem De");
        driver.quit();
    }
}


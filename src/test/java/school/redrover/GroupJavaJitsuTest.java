package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupJavaJitsuTest extends BaseTest {

   @Ignore
   @Test
    public void testCarServiceOptions() {
        getGetdriver().get("https://www.homesteadhyundai.net/");
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement serviceMenu = getGetdriver().findElement(By.xpath("//span[text()='Service/Parts']"));
        serviceMenu.click();

        List<WebElement> options = serviceMenu.findElements(By.tagName("li"));
        Assert.assertEquals(options.size(), 14);
    }

    @Test
    public void testAriumFindACommunity() {
        getGetdriver().get("https://ariumliving.com/");

        WebElement inputElement = getGetdriver().findElement(By.name("search"));
        inputElement.sendKeys("Arium Seaglass");
        inputElement.sendKeys(Keys.ENTER);

        Assert.assertEquals(getGetdriver().getCurrentUrl(), "https://ariumliving.com/find-a-community/?search=Arium+Seaglass");

    }

    @Test
    public void tema_openCartRegistrationTest() {
        getGetdriver().manage().window().maximize();
        getGetdriver().manage().deleteAllCookies();
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        getGetdriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        getGetdriver().findElement(By.id("input-firstname")).sendKeys("Tema");
        getGetdriver().findElement(By.id("input-lastname")).sendKeys("Shvets");
        getGetdriver().findElement(By.id("input-email")).sendKeys("temaemail123@gmail.com");
        getGetdriver().findElement(By.id("input-password")).sendKeys("123xyz");
    }

    @Test
    public void testFirst_RedRover() throws InterruptedException {
        getGetdriver().get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = getGetdriver().findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = getGetdriver().findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = getGetdriver().findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = getGetdriver().findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = getGetdriver().findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = getGetdriver().findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
    }

    @Test
    public void testJenkins() {
        getGetdriver().get("https://www.jenkins.io/");

        WebElement But = getGetdriver().findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = getGetdriver().findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = getGetdriver().findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = getGetdriver().findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(), "Step 1: Setup wizard");
    }

    @Test
    public void testForm() throws InterruptedException {
        getGetdriver().get("https://demoqa.com");
        WebElement ButtonForms = getGetdriver().findElement(By.xpath("//div[@class='home-body']//div[2]//div[1]//div[2]//*[name()='svg']"));
        ButtonForms.click();

        WebElement ButtonPracticeForm = getGetdriver().findElement(By.xpath("(//li[@id='item-0'])[2]"));
        ButtonPracticeForm.click();

        WebElement formNameFirst = getGetdriver().findElement(By.id("firstName"));
        formNameFirst.sendKeys("Andrey");
        WebElement formNameLast = getGetdriver().findElement(By.id("lastName"));
        formNameLast.sendKeys("Pomaz");

        WebElement formEmail = getGetdriver().findElement(By.id("userEmail"));
        formEmail.sendKeys("Test@mail.com");

        WebElement genderMale = getGetdriver().findElement(By.className("custom-control-label"));
        genderMale.click();

        WebElement mobileNumber = getGetdriver().findElement(By.id("userNumber"));
        mobileNumber.sendKeys("5555555555");

        WebElement dateOfBirth = getGetdriver().findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        dateOfBirth.findElement(By.xpath("(//select[@class='react-datepicker__month-select'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='8'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='1989'])[1]")).click();
        dateOfBirth.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[5]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[4]/div[7]")).click();

        WebElement checkboxHobbies = getGetdriver().findElement(By.xpath("(//label[normalize-space()='Sports'])[1]"));
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Sports'])[1]")).click();
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Music'])[1]")).click();

        WebElement address = getGetdriver().findElement(By.id("currentAddress"));
        address.sendKeys("Test Address");

        WebElement ButSubmit = getGetdriver().findElement(By.id("submit"));
        ButSubmit.click();

        WebElement got = getGetdriver().findElement(By.xpath("(//div[@id='example-modal-sizes-title-lg'])[1]"));
        Thread.sleep(2000);
        Assert.assertEquals(got.getText(), "Thanks for submitting the form");
    }

    @Ignore
    @Test
    public void testAboutDoctorsFind() throws InterruptedException {
        getGetdriver().get("https://prodoctorov.ru/");
        String title = getGetdriver().getTitle();

        Assert.assertEquals("ПроДокторов – сайт отзывов пациентов о врачах №1 в России", title);

        WebElement urlElement = getGetdriver().findElement(By.className("b-choose-town-btn-v2"));
        urlElement.click();
        WebElement inputElementSearch = getGetdriver().findElement(By.className("b-choose-town-popup__search-input"));
        inputElementSearch.sendKeys("Краснодар");
        Thread.sleep(2000);
        WebElement SearchboxElement = getGetdriver().findElement(By.className("tt-dataset"));
        SearchboxElement.click();
        Thread.sleep(2000);

//      WebElement inputElement = driver.findElement(By.className("text-field__input"));
        getGetdriver().findElement(By.xpath("//div[@class = 'text-field base-search__input'")).click();
        getGetdriver().findElement(By.xpath("//div[@class = 'text-field base-search__input']/div[1]/div[1]/input[1]")).sendKeys("Ницакова Марина Петровна");
        WebElement submitButton = getGetdriver().findElement(By.className("base-search__button"));
        submitButton.click();
        WebElement link = getGetdriver().findElement(By.className("b-card__name-link"));
        link.click();
        WebElement text = getGetdriver().findElement(By.className("ui-text"));
        Assert.assertEquals(getGetdriver().getCurrentUrl(), "https://prodoctorov.ru/krasnodar/vrach/177664-nicakova/");
    }

    @Test
    public void testAlex() throws InterruptedException {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getGetdriver().manage().window().maximize();

        getGetdriver().get("https://www.globalsqa.com/");

        WebElement element = getGetdriver().findElement(By.xpath("//a[@href=\"https://www.globalsqa.com/training/appium-online-training/\"]"));
        ((JavascriptExecutor) getGetdriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
        WebElement schedule = getGetdriver().findElement(By.xpath("//li[@id='Batch Schedule']"));
        schedule.click();
        ((JavascriptExecutor) getGetdriver()).executeScript("arguments[0].scrollIntoView(true);", schedule);
        WebElement enroll = getGetdriver().findElement(By.xpath("(//a[@href=\"https://www.instamojo.com/globalsqa/appium-training/\"])[2]"));
        enroll.click();
    }

    @Test
    public void testLoginAnton() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getGetdriver().manage().window().maximize();
        getGetdriver().get("https://www.saucedemo.com/");

        WebElement userElement = getGetdriver().findElement(By.id("user-name"));
        userElement.sendKeys("standard_user");

        WebElement userPassword = getGetdriver().findElement(By.id("password"));
        userPassword.sendKeys("secret_sauce");

        WebElement loginButton = getGetdriver().findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals(getGetdriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testArtem() {
        getGetdriver().get("https://demoqa.com/");
        WebElement buttonElements = getGetdriver().findElement(By.xpath("//div[@class='card mt-4 top-card'][1]//*[name()='svg']"));
        buttonElements.click();

        WebElement textBox = getGetdriver().findElement(By.xpath("//span[text()='Text Box']"));
        textBox.click();

        WebElement fullNameField = getGetdriver().findElement(By.id("userName"));
        fullNameField.sendKeys("Artem De");

        WebElement emailField = getGetdriver().findElement(By.id("userEmail"));
        emailField.sendKeys("test@gmail.com");

        WebElement currentAddressField = getGetdriver().findElement(By.id("currentAddress"));
        currentAddressField.sendKeys("123 Main St, Anytown USA");

        WebElement permanentAddressField = getGetdriver().findElement(By.id("permanentAddress"));
        permanentAddressField.sendKeys("456 Oak St, Anytown USA");

        WebElement submitButton = getGetdriver().findElement(By.id("submit"));
        submitButton.click();

        WebElement successfullySubmitted = getGetdriver().findElement(By.id("name"));
        Assert.assertEquals(successfullySubmitted.getText(), "Name:Artem De");
    }

    @Test
    public void homePageSoccer() {
        getGetdriver().get("https://soccerzone.com/");

        Assert.assertEquals(getGetdriver().getTitle(), "Soccer Zone");
    }

    @Test
    public void testHomePageSoccer() {
        // Navigate to the Soccer Zone website
        getGetdriver().get("https://soccerzone.com/");

        // Verify the page title
        Assert.assertEquals("Soccer Zone", getGetdriver().getTitle());
    }

        @Test
        public void testKatya2 () {
            getGetdriver().get("https://www.selenium.dev/selenium/web/web-form.html");

            String title = getGetdriver().getTitle();
            assertEquals("Web form", title);

            getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = getGetdriver().findElement(By.name("my-text"));
            WebElement submitButton = getGetdriver().findElement(By.cssSelector("button"));

            textBox.sendKeys("Selenium");
            submitButton.click();

            WebElement message = getGetdriver().findElement(By.id("message"));
            String value = message.getText();
            assertEquals("Received!", value);
        }
    @Test
    public void formFillOut() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        getGetdriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        getGetdriver().findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Egor");
        getGetdriver().findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Zaitsev");
        getGetdriver().findElement(By.xpath(" //input[@id='input-email']")).sendKeys("zadfsdg@mail.ua");
        getGetdriver().findElement(By.xpath("//input[@id='input-password']")).sendKeys("1234567890");
        getGetdriver().findElement(By.id("input-newsletter-yes")).click();
        getGetdriver().findElement(By.name("agree")).click();
        getGetdriver().findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
    }


    @Test
    public void changeCurrencyTest() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://demo.opencart.com/index.php?route=common/home&language=en-gb");

        getGetdriver().findElement(By.xpath("//span[contains(text(), 'Currency')]")).click();
        getGetdriver().findElement(By.xpath("//a[contains(text(), '€ Euro')]")).click();
        getGetdriver().findElement(By.xpath("//body/main[1]/div[2]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/a[1]/img[1]")).click();
        String currency = getGetdriver().findElement(By.xpath("//span[contains(text(),'112.65€')]")).getText();

        Assert.assertEquals(currency, "112.65€");
    }

    @Test
    public void buttonsRedirectionTest() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://demo.opencart.com/index.php?route=common/home&language=en-gb");

        getGetdriver().findElement(By.xpath("//body/main[1]/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]")).click();
        getGetdriver().findElement(By.xpath("//a[contains(text(),'Mac (1)')]")).click();
        String totalDesktop = getGetdriver().findElement(By.xpath("//div[contains(text(),'Showing 1 to 1 of 1 (1 Pages)')]")).getText();

        Assert.assertEquals(totalDesktop, "Showing 1 to 1 of 1 (1 Pages)");
    }

    @Test
    public void valuePlaceHolderTest() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        String firstName = getGetdriver().findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("placeholder");
        Assert.assertEquals(firstName, "First Name");

        String lastName = getGetdriver().findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("placeholder");
        Assert.assertEquals(lastName, "Last Name");

        String email = getGetdriver().findElement(By.xpath("//input[@placeholder='E-Mail']")).getAttribute("placeholder");
        Assert.assertEquals(email, "E-Mail");

        String password = getGetdriver().findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("placeholder");
        Assert.assertEquals(password, "Password");
    }

    @Test
    public void multiSelectionBoxTest() {
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("http://omayo.blogspot.com/");

        getGetdriver().findElement(By.xpath("//option[contains(text(),'Hyundai')]")).click();
        WebElement checkMultiBox = getGetdriver().findElement(By.xpath("//option[contains(text(),'Hyundai')]"));

        Assert.assertEquals(checkMultiBox.getText(), "Hyundai");
    }

        @Test
        public void Liliia_loginTestErrorAppears() {
            getGetdriver().get("https://automationteststore.com/");

            WebElement loginOrRegister = getGetdriver().findElement(By.xpath("//ul[@id='customer_menu_top']/li"));
            loginOrRegister.click();

            WebElement loginNameInput = getGetdriver().findElement(By.xpath("//input[@id='loginFrm_loginname']"));
            loginNameInput.sendKeys("avadakedavra");

            WebElement passwordInput = getGetdriver().findElement(By.xpath("//input[@id='loginFrm_password']"));
            passwordInput.sendKeys("12345");

            WebElement loginButton = getGetdriver().findElement(By.xpath("//button[@title='Login']"));
            loginButton.click();

            WebElement alert = getGetdriver().findElement(By.xpath("//div[@class='alert alert-error alert-danger']"));
            WebElement closeButton = alert.findElement(By.tagName("button"));
            String alertText = alert.getText().replace(closeButton.getText(), "").trim();

            Assert.assertEquals(alertText, "Error: Incorrect login or password provided.");
        }

    }





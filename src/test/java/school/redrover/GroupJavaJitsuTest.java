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
        getDriver().get("https://www.homesteadhyundai.net/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement serviceMenu = getDriver().findElement(By.xpath("//span[text()='Service/Parts']"));
        serviceMenu.click();

        List<WebElement> options = serviceMenu.findElements(By.tagName("li"));
        Assert.assertEquals(options.size(), 14);
    }

    @Test
    public void testAriumFindACommunity() {
        getDriver().get("https://ariumliving.com/");

        WebElement inputElement = getDriver().findElement(By.name("search"));
        inputElement.sendKeys("Arium Seaglass");
        inputElement.sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://ariumliving.com/find-a-community/?search=Arium+Seaglass");

    }

    @Test
    public void tema_openCartRegistrationTest() {
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        getDriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        getDriver().findElement(By.id("input-firstname")).sendKeys("Tema");
        getDriver().findElement(By.id("input-lastname")).sendKeys("Shvets");
        getDriver().findElement(By.id("input-email")).sendKeys("temaemail123@gmail.com");
        getDriver().findElement(By.id("input-password")).sendKeys("123xyz");
    }

    @Test
    public void testFirst_RedRover() throws InterruptedException {
        getDriver().get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = getDriver().findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = getDriver().findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = getDriver().findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = getDriver().findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = getDriver().findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = getDriver().findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
    }

    @Test
    public void testJenkins() {
        getDriver().get("https://www.jenkins.io/");

        WebElement But = getDriver().findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = getDriver().findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = getDriver().findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = getDriver().findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(), "Step 1: Setup wizard");
    }
    @Test
    public void testForm() throws InterruptedException {
        getDriver().get("https://demoqa.com");
        WebElement ButtonForms = getDriver().findElement(By.xpath("(//div[@class='card-body'])[2]"));
        ButtonForms.click();

        WebElement ButtonPracticeForm = getDriver().findElement(By.xpath("(//li[@id='item-0'])[2]"));
        ButtonPracticeForm.click();

        WebElement formNameFirst = getDriver().findElement(By.id("firstName"));
        formNameFirst.sendKeys("Andrey");
        WebElement formNameLast = getDriver().findElement(By.id("lastName"));
        formNameLast.sendKeys("Pomaz");

        WebElement formEmail = getDriver().findElement(By.id("userEmail"));
        formEmail.sendKeys("Test@mail.com");

        WebElement genderMale = getDriver().findElement(By.className("custom-control-label"));
        genderMale.click();

        WebElement mobileNumber = getDriver().findElement(By.id("userNumber"));
        mobileNumber.sendKeys("5555555555");

        WebElement dateOfBirth = getDriver().findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        dateOfBirth.findElement(By.xpath("(//select[@class='react-datepicker__month-select'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='8'])[1]")).click();
        dateOfBirth.findElement(By.xpath("(//option[@value='1989'])[1]")).click();
        dateOfBirth.findElement(By.xpath("//div[contains(text(),'23')]")).click();

        WebElement checkboxHobbies = getDriver().findElement(By.xpath("(//label[normalize-space()='Sports'])[1]"));
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Sports'])[1]")).click();
        checkboxHobbies.findElement(By.xpath("(//label[normalize-space()='Music'])[1]")).click();

        WebElement address = getDriver().findElement(By.id("currentAddress"));
        address.sendKeys("Test Address");

        WebElement ButSubmit = getDriver().findElement(By.id("submit"));
        ButSubmit.click();

        WebElement got = getDriver().findElement(By.xpath("(//div[@id='example-modal-sizes-title-lg'])[1]"));
        Thread.sleep(2000);
        Assert.assertEquals(got.getText(), "Thanks for submitting the form");
    }


    @Test
    public void testAboutDoctorsFind() throws InterruptedException {
        getDriver().get("https://prodoctorov.ru/");
        String title = getDriver().getTitle();

        Assert.assertEquals("ПроДокторов – сайт отзывов пациентов о врачах №1 в России", title);

        WebElement urlTownElement = getDriver().findElement(By.className("b-choose-town-btn-v2"));
        urlTownElement.click();
        WebElement inputElementSearch = getDriver().findElement(By.className("b-choose-town-popup__search-input"));
        Thread.sleep(2000);
        inputElementSearch.sendKeys("Краснодар");
        inputElementSearch.click();
        Thread.sleep(2000);
        WebElement SearchBoxElement = getDriver().findElement(By.xpath("//strong[@class='b-town-search__highlight']"));
        SearchBoxElement.click();
        Thread.sleep(2000);
        WebElement inputPlaceholderElement = getDriver().findElement(By.xpath("//input[@placeholder = 'Врачи, клиники, услуги']"));
        Thread.sleep(2000);
        inputPlaceholderElement.sendKeys("Ницакова Марина Петровна");
        WebElement submitButton = getDriver().findElement(By.className("base-search__button"));
        submitButton.click();
        WebElement linkName = getDriver().findElement(By.className("b-card__name-link"));
        linkName.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://prodoctorov.ru/krasnodar/vrach/177664-nicakova/");
    }

    @Ignore
    @Test
    public void testAlex() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();

        getDriver().get("https://www.globalsqa.com/");

        WebElement element = getDriver().findElement(By.xpath("//a[@href=\"https://www.globalsqa.com/training/appium-online-training/\"]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
        WebElement schedule = getDriver().findElement(By.xpath("//li[@id='Batch Schedule']"));
        schedule.click();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", schedule);
        WebElement enroll = getDriver().findElement(By.xpath("(//a[@href=\"https://www.instamojo.com/globalsqa/appium-training/\"])[2]"));
        enroll.click();
    }

    @Test
    public void testLoginAnton() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
        getDriver().get("https://www.saucedemo.com/");

        WebElement userElement = getDriver().findElement(By.id("user-name"));
        userElement.sendKeys("standard_user");

        WebElement userPassword = getDriver().findElement(By.id("password"));
        userPassword.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testArtem() {
        getDriver().get("https://demoqa.com/");
        WebElement buttonElements = getDriver().findElement(By.xpath("//div[@class='card mt-4 top-card'][1]//*[name()='svg']"));
        buttonElements.click();

        WebElement textBox = getDriver().findElement(By.xpath("//span[text()='Text Box']"));
        textBox.click();

        WebElement fullNameField = getDriver().findElement(By.id("userName"));
        fullNameField.sendKeys("Artem De");

        WebElement emailField = getDriver().findElement(By.id("userEmail"));
        emailField.sendKeys("test@gmail.com");

        WebElement currentAddressField = getDriver().findElement(By.id("currentAddress"));
        currentAddressField.sendKeys("123 Main St, Anytown USA");

        WebElement permanentAddressField = getDriver().findElement(By.id("permanentAddress"));
        permanentAddressField.sendKeys("456 Oak St, Anytown USA");

        WebElement submitButton = getDriver().findElement(By.id("submit"));
        submitButton.click();

        WebElement successfullySubmitted = getDriver().findElement(By.id("name"));
        Assert.assertEquals(successfullySubmitted.getText(), "Name:Artem De");
    }

    @Test
    public void homePageSoccer() {
        getDriver().get("https://soccerzone.com/");

        Assert.assertEquals(getDriver().getTitle(), "Soccer Zone");
    }

    @Test
    public void testHomePageSoccer() {
        // Navigate to the Soccer Zone website
        getDriver().get("https://soccerzone.com/");

        // Verify the page title
        Assert.assertEquals("Soccer Zone", getDriver().getTitle());
    }

    @Test
    public void testKatya2() {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        
        String title = getDriver().getTitle();
        assertEquals("Web form", title);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        
        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
    }

    @Test
    public void formFillOut() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        getDriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        getDriver().findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Egor");
        getDriver().findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Zaitsev");
        getDriver().findElement(By.xpath(" //input[@id='input-email']")).sendKeys("zadfsdg@mail.ua");
        getDriver().findElement(By.xpath("//input[@id='input-password']")).sendKeys("1234567890");
        getDriver().findElement(By.id("input-newsletter-yes")).click();
        getDriver().findElement(By.name("agree")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
    }


    @Test
    public void changeCurrencyTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://demo.opencart.com/index.php?route=common/home&language=en-gb");

        getDriver().findElement(By.xpath("//span[contains(text(), 'Currency')]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(), '€ Euro')]")).click();
        getDriver().findElement(By.xpath("//body/main[1]/div[2]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/a[1]/img[1]")).click();
        String currency = getDriver().findElement(By.xpath("//span[contains(text(),'112.65€')]")).getText();

        Assert.assertEquals(currency, "112.65€");
    }

    @Test
    public void buttonsRedirectionTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://demo.opencart.com/index.php?route=common/home&language=en-gb");

        getDriver().findElement(By.xpath("//body/main[1]/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Mac (1)')]")).click();
        String totalDesktop = getDriver().findElement(By.xpath("//div[contains(text(),'Showing 1 to 1 of 1 (1 Pages)')]")).getText();

        Assert.assertEquals(totalDesktop, "Showing 1 to 1 of 1 (1 Pages)");
    }

    @Test
    public void valuePlaceHolderTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");

        String firstName = getDriver().findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("placeholder");
        Assert.assertEquals(firstName, "First Name");

        String lastName = getDriver().findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("placeholder");
        Assert.assertEquals(lastName, "Last Name");

        String email = getDriver().findElement(By.xpath("//input[@placeholder='E-Mail']")).getAttribute("placeholder");
        Assert.assertEquals(email, "E-Mail");

        String password = getDriver().findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("placeholder");
        Assert.assertEquals(password, "Password");
    }

    @Test
    public void multiSelectionBoxTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("http://omayo.blogspot.com/");

        getDriver().findElement(By.xpath("//option[contains(text(),'Hyundai')]")).click();
        WebElement checkMultiBox = getDriver().findElement(By.xpath("//option[contains(text(),'Hyundai')]"));

        Assert.assertEquals(checkMultiBox.getText(), "Hyundai");
    }

    @Test
    public void Liliia_loginTestErrorAppears() {
        getDriver().get("https://automationteststore.com/");

        WebElement loginOrRegister = getDriver().findElement(By.xpath("//ul[@id='customer_menu_top']/li"));
        loginOrRegister.click();

        WebElement loginNameInput = getDriver().findElement(By.xpath("//input[@id='loginFrm_loginname']"));
        loginNameInput.sendKeys("avadakedavra");

        WebElement passwordInput = getDriver().findElement(By.xpath("//input[@id='loginFrm_password']"));
        passwordInput.sendKeys("12345");

        WebElement loginButton = getDriver().findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();

        WebElement alert = getDriver().findElement(By.xpath("//div[@class='alert alert-error alert-danger']"));
        WebElement closeButton = alert.findElement(By.tagName("button"));
        String alertText = alert.getText().replace(closeButton.getText(), "").trim();

        Assert.assertEquals(alertText, "Error: Incorrect login or password provided.");
    }

    @Test
    public void testfindBookAndreyP() throws InterruptedException {
        getDriver().get("https://demoqa.com/");
        WebElement buttonAlertMain = getDriver().findElement(By.xpath("(//div[@class='card mt-4 top-card'])[6]"));
        buttonAlertMain.click();
        Thread.sleep(2000);

        WebElement searchBox = getDriver().findElement(By.xpath("//input[@id='searchBox']"));
        searchBox.sendKeys("Speaking JavaScript");

        WebElement findBook = getDriver().findElement(By.linkText("Speaking JavaScript"));
        findBook.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/books?book=9781449365035");
    }
    @Test
    public void testAlert() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        getDriver().get("https://demoqa.com/");
        Thread.sleep(1000);
        WebElement buttonMainAlert = getDriver().findElement(By.xpath("//h5[normalize-space()='Alerts, Frame & Windows']"));
        buttonMainAlert.click();

        WebElement buttonAlert = getDriver().findElement(By.xpath("//span[normalize-space()='Alerts']"));
        buttonAlert.click();

        WebElement buttonClickMe1 = getDriver().findElement(By.id("alertButton"));
        buttonClickMe1.click();
        getDriver().switchTo().alert().accept();

        WebElement buttonClickMe2 = getDriver().findElement(By.id("timerAlertButton"));
        buttonClickMe2.click();
        Thread.sleep(7000);
        getDriver().switchTo().alert().accept();

        WebElement buttonClickMe3Ok = getDriver().findElement(By.id("confirmButton"));
        buttonClickMe3Ok.click();
        getDriver().switchTo().alert().accept();
        WebElement selectOk = getDriver().findElement(By.xpath("(//span[@id='confirmResult'])[1]"));
        Thread.sleep(1000);
        Assert.assertEquals(selectOk.getText(), "You selected Ok");

        WebElement buttonClickMe3No = getDriver().findElement(By.id("confirmButton"));
        buttonClickMe3No.click();
        getDriver().switchTo().alert().dismiss();
        WebElement selectNo = getDriver().findElement(By.xpath("(//span[@id='confirmResult'])[1]"));
        Assert.assertEquals(selectNo.getText(), "You selected Cancel");

        WebElement buttonClickMe4 = getDriver().findElement(By.id("promtButton"));
        buttonClickMe4.click();
        Thread.sleep(1000);
        getDriver().switchTo().alert().sendKeys("RedRover06");
        getDriver().switchTo().alert().accept();
        WebElement enterTextResult = getDriver().findElement(By.xpath("//span[@id='promptResult']"));
        Assert.assertEquals(enterTextResult.getText(), "You entered RedRover06");
    }
    @Ignore
    @Test
    public void testZakazUa() {
        getDriver().get("https://winetime.zakaz.ua/uk/");

        WebElement buttonEntry = getDriver().findElement(By.xpath("//span[contains(text(),'Увійти')]"));
        buttonEntry.click();

        WebElement textBoxPhone = getDriver().findElement(By.tagName("input"));
        textBoxPhone.sendKeys("+380505055050");

        WebElement textBoxPassword = getDriver().findElement(By.xpath("//input[@id='login-password']"));
        textBoxPassword.sendKeys("1234567890");

        WebElement buttonReg = getDriver().findElement(By.xpath("//button[contains(text(),'Зареєструватись')]"));
        buttonReg.click();

        WebElement text=getDriver().findElement(By.xpath("//span[contains(text(),'У мене вже є акаунт')]"));
        Assert.assertEquals(text.getText(), "У мене вже є акаунт");
    }


    @Test
    public void testFindInSearchBox(){

        getDriver().get("https://soccerzone.com/");
        WebElement searchBox = getDriver().findElement(By.name("search_query_adv"));
        searchBox.click();
        searchBox.sendKeys("ball");

        WebElement button = getDriver().findElement(By.xpath(" //button[@name='go']"));
        button.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://soccerzone.com/search.php?search_query_adv=ball&go=Search");
    }

    @Test
    public void testWrongSignIn(){
        getDriver().get("https://soccerzone.com/login.php");
        WebElement username = getDriver().findElement(By.name("login_email"));
        username.sendKeys("1234@gmail.com");

        WebElement password = getDriver().findElement(By.id("login_pass"));
        password.sendKeys("12345678");

        WebElement button = getDriver().findElement(By.cssSelector("input[value='Sign in']"));
        button.click();

        WebElement text = getDriver().findElement(By.cssSelector(".alertBox-column.alertBox-message"));
        Assert.assertTrue(text.isDisplayed());}

    @Ignore
    @Test
    public void testChoiceClothes() throws InterruptedException {
        getDriver().get("https://soccerzone.com/");
        WebElement bootRoom = getDriver().findElement(By.cssSelector("img[src='images/stencil/original/image-manager/boots.png']"));
        bootRoom.click();

        WebElement newBoots = getDriver().findElement(By.cssSelector("img[title='NEW']"));
        newBoots.click();

        WebElement nikePremier = getDriver().findElement(By.cssSelector("img[title='The Nike Premier III FG - WHITE/ROYAL/RED']"));
        nikePremier.click();
        Thread.sleep(1000);

        WebElement choiceSize = getDriver().findElement(By.cssSelector("label[for='attribute_rectangle__3283_11248']"));
        choiceSize.click();

        WebElement choiceColor = getDriver().findElement(By.cssSelector("label[for='attribute_rectangle__3284_11254']"));
        choiceColor.click();

        WebElement addToCart = getDriver().findElement(By.id("form-action-addToCart"));
        addToCart.click();
        Thread.sleep(1000);

        WebElement text = getDriver().findElement(By.cssSelector("h1[class='modal-header-title']"));
        Assert.assertTrue(text.isDisplayed());
    }
}





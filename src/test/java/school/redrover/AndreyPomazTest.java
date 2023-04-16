package school.redrover;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import school.redrover.runner.BaseTest;

public class AndreyPomazTest extends BaseTest {

    @Test
    public void testRedRover() throws InterruptedException {
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

        WebElement buttonSubmit = getDriver().findElement(By.className("t-submit"));
        buttonSubmit.click();
        Thread.sleep(2000);

        WebElement error = getDriver().findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
    }

    @Test
    public void testSelenium() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);
        Thread.sleep(2000);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testJenkins() {
        getDriver().get("https://www.jenkins.io/");

        WebElement butDoc = getDriver().findElement(By.linkText("Documentation"));
        butDoc.click();

        WebElement butInstall = getDriver().findElement(By.linkText("Installing Jenkins"));
        butInstall.click();

        WebElement butWindows = getDriver().findElement(By.linkText("Windows"));
        butWindows.click();

        WebElement text = getDriver().findElement(By.className("hdlist1"));
        Assert.assertEquals(text.getText(),"Step 1: Setup wizard");
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
    public void findBook() throws InterruptedException {
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
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupCodeProTest extends BaseTest {

    @Test
    public void testKravisRegistration() throws InterruptedException {

        getDriver().get("https://www.kravis.org//");

        WebElement buttonLogin = getDriver()
                .findElement(By.xpath("//a[@id='calendar']"));
        buttonLogin.click();
        Thread.sleep(2000);

        WebElement email = getDriver().findElement(By.id("EMAIL"));
        email.sendKeys("mail1@gmail.com");
        WebElement password = getDriver().findElement(By.id("PASSWORD"));
        password.sendKeys("mail123");
        WebElement firstName = getDriver().findElement(By.id("FIRST"));
        firstName.sendKeys("Anna");
        WebElement lastName = getDriver().findElement(By.id("LAST"));
        lastName.sendKeys("Smith");
        WebElement address = getDriver().findElement(By.id("ADDRESS1"));
        address.sendKeys("West 123");
        WebElement city = getDriver().findElement(By.id("CITY"));
        city.sendKeys("West Palm Beach");
        WebElement state = getDriver().findElement(By.id("STATE"));
        state.sendKeys("FL");
        WebElement zip = getDriver().findElement(By.id("ZIP"));
        zip.sendKeys("33124");
        WebElement phone = getDriver().findElement(By.id("DAY_PHONE"));
        phone.sendKeys("2482676867");
        WebElement buttonSubmit = getDriver()
                .findElement(By.xpath("//input[@id='GUEST_INFO']"));
        buttonSubmit.click();
        Thread.sleep(2000);

        WebElement pageEvents = getDriver()
                .findElement(By.xpath("//button[@id='tab-event-list']"));

        Assert.assertEquals(pageEvents.getText(), "EVENT LIST");
    }

    @Test
    public void testSSregistration() throws InterruptedException {

        getDriver().get("https://www.hbomax.com/");

        WebElement buttonSignUpNow = getDriver()
                .findElement(By.xpath("//*[@id=\"page23957-band406944-Header406945\"]//a[text()='Sign Up Now']"));
        buttonSignUpNow.click();
        Thread.sleep(2000);

        WebElement pageSignUp = getDriver()
                .findElement(By.xpath("//div[@class='exp-2688-plans']//div[text()='$9.99']"));
        pageSignUp.click();
        Thread.sleep(2000);

        WebElement firstName = getDriver().findElement(By.id("firstName"));
        firstName.sendKeys("Ann");
        WebElement lastName = getDriver().findElement(By.id("lastName"));
        lastName.sendKeys("Mann");
        WebElement email = getDriver().findElement(By.id("email"));
        email.sendKeys("mail2467845@gmail.com");
        WebElement password = getDriver().findElement(By.id("password"));
        password.sendKeys("bumbar123!");
        WebElement buttonCreateAccount = getDriver()
                .findElement(By.xpath("//button[@id='createAccount']"));

        Assert.assertEquals(buttonCreateAccount.getText(), "CREATE ACCOUNT");
    }
}

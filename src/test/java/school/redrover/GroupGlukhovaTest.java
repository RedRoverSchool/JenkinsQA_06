package school.redrover;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupGlukhovaTest extends BaseTest {
    @Test
    public void testLogIn(){
        getDriver().get("https://www.demoblaze.com/");

        WebElement logIn = getDriver().findElement(By.xpath("//a[@id='login2']"));
        logIn.click();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement enterUserName = getDriver().findElement(By.xpath("//input[@id='loginusername']"));
        enterUserName.sendKeys("Alex33");

        WebElement enterPassword = getDriver().findElement(By.xpath("//input[@id='loginpassword']"));
        enterPassword.sendKeys("i#iw\"iZn2JR6BeM>");

        WebElement clickLogIn = getDriver().findElement(By.xpath("//button[@onclick='logIn()']"));
        clickLogIn.click();

        Assert.assertEquals(clickLogIn.getText(), "Log in");
    }

    @Test
    public void testVideoAvailabilityAboutUS() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement clickAboutUs = getDriver().findElement(By.xpath("//a[text() ='About us']"));
        clickAboutUs.click();

        WebElement clickPlayVideo = getDriver().findElement(By.cssSelector(".video-js .vjs-big-play-button"));
        clickPlayVideo.click();

        Assert.assertEquals(clickPlayVideo.getText(), "");
    }

    @Test
    public void testCloseSectionAboutUsByButton() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement clickAboutUs = getDriver().findElement(By.xpath("//a[text() ='About us']"));
        clickAboutUs.click();

        WebElement closeButton = getDriver().findElement(By.xpath
                ("//div[@id ='videoModal']//div[@class='modal-footer']/button[text()='Close']"));
        closeButton.click();

        Assert.assertTrue((closeButton.getText().equals("Close") || closeButton.getText().equals("")));
    }

    @Test
    public void testCloseSectionAboutUsByIcon() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement clickAboutUs = getDriver().findElement(By.xpath("//a[text() ='About us']"));
        clickAboutUs.click();

        WebElement closeIcon = getDriver().findElement(By.xpath
                ("//div[@id ='videoModal']//button[@class='close']/span"));
        closeIcon.click();

        Assert.assertTrue((closeIcon.getText().equals("×") || closeIcon.getText().equals("")));
    }

    @Test
    public void testNextIconCarousel() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement clickNextIcon = getDriver().findElement(By.cssSelector(".carousel-control-next-icon"));
        clickNextIcon.click();
        clickNextIcon.click();
        clickNextIcon.click();

        Assert.assertEquals(clickNextIcon.getText(), "");
    }

    @Test
    public void testSendMessageUsingContact() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement clickContact = getDriver().findElement(By.xpath("//a[text() ='Contact']"));
        clickContact.click();

        WebElement enterEmail = getDriver().findElement(By.xpath("//input[@id='recipient-email']"));
        enterEmail.sendKeys("test@mail.test");

        WebElement enterName = getDriver().findElement(By.xpath("//input[@id='recipient-name']"));
        enterName.sendKeys("Ann");

        WebElement enterMessage = getDriver().findElement(By.xpath("//textarea[@id='message-text']"));
        enterMessage.sendKeys("I cannot pay for goods with a credit card");

        WebElement sendMessageButton = getDriver().findElement(By.xpath("//button[@onclick='send()']"));
        sendMessageButton.click();

        Alert alert = getDriver().switchTo().alert();

        Assert.assertEquals(alert.getText(), "Thanks for the message!!");

        alert.accept();
    }
}

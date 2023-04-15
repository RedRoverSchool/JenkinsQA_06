package school.redrover;

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

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(5000));

        WebElement enterUserName = getDriver().findElement(By.xpath("//input[@id='loginusername']"));
        enterUserName.sendKeys("Alex33");

        WebElement enterPassword = getDriver().findElement(By.xpath("//input[@id='loginpassword']"));
        enterPassword.sendKeys("i#iw\"iZn2JR6BeM>");

        WebElement clickLogIn = getDriver().findElement(By.xpath("//button[@onclick='logIn()']"));
        clickLogIn.click();

        Assert.assertEquals(clickLogIn.getText(), "Log in");
    }

    @Test
    public void  testVideoAvailabilityAboutUS() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(5000));

        WebElement clickAboutUs = getDriver().findElement(By.xpath("//a[text() = 'About us']"));
        clickAboutUs.click();

        WebElement clickPlayVideo = getDriver().findElement(By.cssSelector(".video-js .vjs-big-play-button"));
        clickPlayVideo.click();

        Assert.assertEquals(clickPlayVideo.getText(), "");
    }

    @Test
    public void  testNextIconCarousel() {
        getDriver().get("https://www.demoblaze.com/");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(5000));

        WebElement clickNextIcon = getDriver().findElement(By.cssSelector(".carousel-control-next-icon"));
        clickNextIcon.click();
        clickNextIcon.click();
        clickNextIcon.click();

        Assert.assertEquals(clickNextIcon.getText(), "");
    }
}

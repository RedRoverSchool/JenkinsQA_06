package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class LyudaTest extends BaseTest {

    @Test
    public void testLoginForm(){
        getDriver().navigate().to("https://demo.applitools.com/");

        WebElement username = getDriver().findElement(By.xpath("//input[@id = 'username' ]"));
        username.sendKeys("milaqa1@gmail.com");

        WebElement password = getDriver().findElement(By.xpath("//input[@id = 'password' ]"));
        password.sendKeys("123456");

        WebElement checkBox = getDriver().findElement(By.xpath("//input[@type = 'checkbox' ]"));
        checkBox.click();

        WebElement signInButton = getDriver().findElement(By.xpath("//a[@id= 'log-in' ]"));
        signInButton.click();

        WebElement totalBalance = getDriver().findElement(By.xpath("//div[contains(text(),'Balance')]"));
        Assert.assertEquals(totalBalance.getText(),"Total Balance");
    }
}

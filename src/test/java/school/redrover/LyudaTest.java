package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class LyudaTest extends BaseTest {

    @Test
    public void testLoginForm(){
        getGetdriver().navigate().to("https://demo.applitools.com/");

        WebElement username = getGetdriver().findElement(By.xpath("//input[@id = 'username' ]"));
        username.sendKeys("milaqa1@gmail.com");

        WebElement password = getGetdriver().findElement(By.xpath("//input[@id = 'password' ]"));
        password.sendKeys("123456");

        WebElement checkBox = getGetdriver().findElement(By.xpath("//input[@type = 'checkbox' ]"));
        checkBox.click();

        WebElement signInButton = getGetdriver().findElement(By.xpath("//a[@id= 'log-in' ]"));
        signInButton.click();

        WebElement totalBalance = getGetdriver().findElement(By.xpath("//div[contains(text(),'Balance')]"));
        Assert.assertEquals(totalBalance.getText(),"Total Balance");
    }
}

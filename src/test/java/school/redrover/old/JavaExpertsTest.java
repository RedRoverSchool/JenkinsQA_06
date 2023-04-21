package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.*;
@Ignore
public class JavaExpertsTest extends BaseTest {

    private static final String email = "test" + Math.random()*1000 + "@mail.com";

    @Test
    public void testPageTitle(){
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();

        assertEquals("Web form", title, "Test page title is fail");
    }

    @Test
    public void testFormSubmit(){
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();

        assertEquals("Received!", value, "Form submit is fail");
    }

    @Ignore
    @Test
    public void testRegisterUser(){
        getDriver().get("http://selenium1py.pythonanywhere.com/en-gb/accounts/login/");

        WebElement registration_email = getDriver().findElement(By.cssSelector("input[name='registration-email']"));
        registration_email.sendKeys(email);

        WebElement password1 = getDriver().findElement(By.cssSelector("input[name='registration-password1']"));
        password1.sendKeys(email);

        WebElement password2 = getDriver().findElement(By.cssSelector("input[name='registration-password2']"));
        password2.sendKeys(email);

        getDriver().findElement(By.cssSelector("button[name='registration_submit']")).click();

        assertFalse(getDriver().findElements(By.cssSelector(".alertinner.wicon")).isEmpty(),
                "User registration is fail");
    }

    @Ignore
    @Test(dependsOnMethods = "testRegisterUser")
    public void testLoginUser(){
        getDriver().get("http://selenium1py.pythonanywhere.com/en-gb/accounts/login/");

        WebElement login_email = getDriver().findElement(By.cssSelector("input[name='login-username']"));
        login_email.sendKeys(email);

        WebElement password = getDriver().findElement(By.cssSelector("input[name='login-password']"));
        password.sendKeys(email);

        getDriver().findElement(By.cssSelector("button[name='login_submit']")).click();

        assertFalse(getDriver().findElements(By.cssSelector(".alertinner.wicon")).isEmpty(),
                "User login is fail");
    }
}

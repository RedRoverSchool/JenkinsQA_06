package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class TatianaTodosevaTest extends BaseTest {

    private String email;
    @Test
    public void firstTest() {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();

        assertEquals("Received!", value);

    }

    /*@Test

    public void secondTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        Thread.sleep(2000);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();
    }
    */
    @Test
    public void testRegisterUser() throws InterruptedException {

         getDriver().get("http://selenium1py.pythonanywhere.com/ru/accounts/login/");

         WebElement registr_email = getDriver().findElement(By.cssSelector("input[name='registration-email']"));
         registr_email.sendKeys(email);

         WebElement password1 = getDriver().findElement(By.cssSelector("input[name='registration-password1']"));
         password1.sendKeys(email);

         WebElement password2 = getDriver().findElement(By.cssSelector("input[name='registration-password2']"));
         password2.sendKeys(email);

         getDriver().findElement(By.cssSelector("button[name='registration_submit']")).click();

         Thread.sleep(4000);

        /*Thread.sleep(6000);

        try {
            WebElement success_registration = getDriver().findElements(By.cssSelector("alertinner.wicon"));
        } catch (Exception ex) {
            WebElement success_registration = null;
        } */
        boolean flag = false;
        if (!getDriver().findElements(By.cssSelector("alertinner.wicon")).isEmpty()) {
            flag = true;
        }

        assertEquals(flag, true);
    }

    @Test(priority = 2)
    public void testLoginUser() throws InterruptedException {

        getDriver().get("http://selenium1py.pythonanywhere.com/ru/accounts/login/");

        WebElement login_email = getDriver().findElement(By.cssSelector("input[name='login-username']"));
        login_email.sendKeys(email);

        WebElement password = getDriver().findElement(By.cssSelector("input[name='login-password']"));
        password.sendKeys(email);

        getDriver().findElement(By.cssSelector("button[name='login_submit']")).click();

        Thread.sleep(6000);

        boolean flag = false;
        if (!getDriver().findElements(By.cssSelector("alertinner.wicon")).isEmpty()) {
            flag = true;
        }

        assertEquals(flag, true);
    }
}



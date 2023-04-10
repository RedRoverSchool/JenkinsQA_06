package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupHighwayToAqaTest {

    @Test
    public void openContactUsPageTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.xpath("//footer/div/ul[@class='footer links']//a[text()='Contact Us']")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://magento.softwaretestingboard.com/contact/");
        Assert.assertEquals(pageTitle.getText(), "Contact Us");

        driver.quit();
    }

    @Test
    public void testErrorMessage() {

        String expectedErrorMessage = "This is a required field.";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://magento.softwaretestingboard.com");

        WebElement scrollByVisibleElement = driver.findElement(By.xpath("//div[@class='footer content']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollByVisibleElement);

        WebElement contactNavItem = driver.findElement(
                By.xpath("//a[@href='https://magento.softwaretestingboard.com/contact/']"));
        contactNavItem.click();

        driver.findElement(By.xpath("//input[@id='name']"))
                .sendKeys("Anna");
        driver.findElement(By.xpath("//input[@id='telephone']"))
                .sendKeys("8995552557");
        driver.findElement(By.xpath("//textarea[@id='comment']"))
                .sendKeys("Thank you for providing such great products and service!");
        driver.findElement(By.xpath("//span[text()='Submit']")).click();

        String actualErrorMessage = driver.findElement(
                By.xpath("//div[@id='email-error']")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.quit();
    }

    @Test
    public void MLFirstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");

        WebElement textBox = driver.findElement(
                By.xpath("//header//a[normalize-space(text())=\"Create an Account\"]"));
        textBox.click();

        WebElement text = driver.findElement(By.xpath("//span[@data-ui-id = \"page-title-wrapper\"]"));

        Assert.assertEquals(text.getText(), "Create New Customer Account");


        WebElement firstName = driver.findElement(By.xpath("//input[@id = \"firstname\"]"));
        firstName.sendKeys("Marina");
        WebElement lastName = driver.findElement(By.xpath("//input[@id = \"lastname\"]"));
        lastName.sendKeys("Los");
        WebElement email = driver.findElement(By.xpath("//input[@id = \"email_address\"]"));
        email.sendKeys("test@google.com");
        WebElement password1 = driver.findElement(By.xpath("//input[@id = \"password\"]"));
        password1.sendKeys("123Qwerty+");
        WebElement password2 = driver.findElement(By.xpath("//input[@id = \"password-confirmation\"]"));
        password2.sendKeys("123Qwerty+");
        WebElement submitbutton = driver.findElement(
                By.xpath("//button/span[normalize-space(text())=\"Create an Account\"]")
        );
        submitbutton.click();

        Thread.sleep(2000);

        WebElement clickHereLink=driver.findElement(
                By.xpath("//div/a[normalize-space(text())=\"click here\"]")
        );
        clickHereLink.click();

        WebElement forgotPassword = driver.findElement(
                By.xpath("//h1/span[normalize-space(text())=\"Forgot Your Password?\"]")
        );

        Assert.assertEquals(forgotPassword.getText(), "Forgot Your Password?");

        driver.quit();
    }
}

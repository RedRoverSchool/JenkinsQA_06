package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import school.redrover.runner.BaseTest;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class GroupSomeGroupTest extends BaseTest{

    private WebDriverWait wait;

    @BeforeMethod
    public void init() {
        wait=new WebDriverWait(getDriver(), Duration.ofSeconds(2));
    }


    @Test
    public void testEmailContactPage() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.cssSelector("#ast-desktop-header  a[href$='/contact-us/']")).click();

        WebElement email = getDriver().findElement(
                By.xpath("//p[contains(text(), 'Email')]/strong"));

        Assert.assertEquals(email.getText(), "askomdch@gmail.com");
    }

    @Test
    public void testVerifyTitle() {
        getDriver().get("http://w3schools.com");

        WebElement textBox = getDriver().findElement(By.id("search2"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("JS");
        submitButton.click();

        getDriver().manage().window().maximize();
        String title = getDriver().getTitle();

        Assert.assertEquals(title, "JavaScript Tutorial");
    }

    @Ignore
    @Test
    public void testCartCounter() {
        getDriver().get("https://askomdch.com/");
        getDriver().findElement(By.cssSelector("#ast-desktop-header  a[href$='/store/']")).click();

        getDriver().findElement(
                By.xpath("//a[@href='?add-to-cart=1198']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ast-site-header-cart-data  ul")));
        WebElement counter = getDriver().findElement(By.xpath("//div[@id='ast-desktop-header']//span[@class='count']"));
        Assert.assertEquals(counter.getText().trim(), "1");
    }
    @Ignore
    @Test
    public void testButtonPrevious() {
        getDriver().get("https://www.w3schools.com/js/js_object_prototypes.asp");
        getDriver().findElement(By.xpath("//a[contains(text(), '❮ Previous')]")).click();

        getDriver().manage().window().maximize();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.w3schools.com/js/js_object_constructors.asp");
    }
}
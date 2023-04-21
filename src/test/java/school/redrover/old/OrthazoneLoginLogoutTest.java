package school.redrover.old;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class OrthazoneLoginLogoutTest extends BaseTest{

    @Test
    public void testLogin() throws InterruptedException{
        getDriver().get("https://www.orthazone.com/");

        getDriver().findElement(By.className("y-header__user")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//a[2]")).click();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("autotest-old@orthazone.com");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//button[text()='Login']")).click();

        WebElement textForAssert = getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[2]"));
        Assert.assertEquals(textForAssert.getText(),"Auto");

        getDriver().findElement(By.className("y-header__user")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//a[2]")).click();

        WebElement textOnLogoutPage = getDriver().findElement(By.xpath("//div[@class='asteps__head']"));
        Assert.assertEquals(textOnLogoutPage.getText(),"ACCOUNT LOGOUT");
    }

    @Test
    public void testLoginDev () throws InterruptedException {

        getDriver().get("https://devortha.dentazone.com/");

        WebElement account = getDriver().findElement(By.xpath("//button[@title='Your account menu']//span[@class='y-btn__txt']"));
        account.click();
        Thread.sleep(2000);

        getDriver().findElement(By.xpath("//div[@class=\"y-modal__main-inner\"]//span[@class='y-btn__txt' and text()='Login']")).click();
        getDriver().findElement(By.name("email")).sendKeys("ntest@orthazone.com");
        getDriver().findElement(By.name("password")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//button[@type='submit'][@class='r-btn abtn' and text() = 'Login']")).click();

        WebElement accountName = getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[3]"));
        Assert.assertEquals(accountName.getText(), "Ntest");
    }
}

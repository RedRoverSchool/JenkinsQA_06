package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BarbaraTest {
    @Test
    public void firstTest(){
        String test = "Github First Simple Test";
        Assert.assertEquals("Github First Simple Test", test);
    }
    @Test
    public void testFindElement() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/");


        WebElement textBox = driver.findElement(By.id("search2"));
        textBox.sendKeys("java ");
        WebElement Button = driver.findElement(By.id("learntocode_searchbtn"));
        Button.click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"leftmenuinnerinner\"]/a[1]"));

        Assert.assertEquals(text.getText(), "Java HOME");
        driver.quit();
    }
}



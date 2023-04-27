package school.redrover.old;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class TestTitansGroupTest extends BaseTest {

    @Test
    public void testGoogleSearchSelenium() throws InterruptedException {
        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");

    }

    @Test
    public void testFormDemoqaDotCom() throws InterruptedException {
        getDriver().get("https://demoqa.com");


        WebElement buttonCategoryCards = getDriver().findElement(By.xpath("//div[@class='card mt-4 top-card'][2]"));
        buttonCategoryCards.click();

        Thread.sleep(2000);

        WebElement buttonPracticeForm = getDriver().findElement(By.xpath("//span[@class='text'][text()=\"Practice Form\"]"));
        buttonPracticeForm.click();

        Thread.sleep(2000);

        WebElement textBox = getDriver().findElement(By.xpath("//div[@class='main-header']"));

        Assert.assertEquals(textBox.getText(), "Practice Form");

    }

}

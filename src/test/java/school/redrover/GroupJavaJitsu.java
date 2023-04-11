package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class GroupJavaJitsu {

    @Test
    public void testCarServiceOptions(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.homesteadhyundai.net/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement serviceMenu = driver.findElement(By.xpath("//span[text()='Service/Parts']"));
        serviceMenu.click();

        List<WebElement> options = serviceMenu.findElements(By.tagName("li"));
        Assert.assertEquals(options.size(), 14);

        driver.quit();
    }

    @Test
    public void testAriumFindACommunity() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://ariumliving.com/");

        WebElement inputElement = driver.findElement(By.name("search"));
        inputElement.sendKeys("Arium Seaglass");
        inputElement.sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(), "https://ariumliving.com/find-a-community/?search=Arium+Seaglass");

        driver.quit();

    }

    @Test
    public void tema_flightFinderTest() throws Throwable {
//        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String url = "https://demo.guru99.com/test/newtours/reservation.php";
        driver.get(url);

        WebElement radioBtn = driver.findElement(By.xpath("//input[@value='oneway']"));
        radioBtn.click();

        //Passenger Selection
        WebElement passengerAmount = driver.findElement(By.name("passCount"));
        Select pAmount = new Select(passengerAmount);
        pAmount.selectByIndex(2);           //3 passengers selected
        Thread.sleep(2000);
        pAmount.selectByVisibleText("2");   //changed my mind, selected 2 passengers
        Thread.sleep(2000);
        pAmount.selectByValue("4");         //changed my mind, selected 4 passengers
        Thread.sleep(2000);

        //Departing Airport Selection
        WebElement dAirport = driver.findElement(By.name("fromPort"));
        Select dFrom = new Select(dAirport);
        List<WebElement> dAirports = dFrom.getOptions();
        for(WebElement ele : dAirports){
            System.out.println("Departing city: "+ele.getText());
        }
        Thread.sleep(2000);
        dFrom.selectByValue("Paris");

        //Departing Month Selection
        WebElement dMonths = driver.findElement(By.name("fromMonth"));
        Select dMonth = new Select(dMonths);
        Thread.sleep(2000);
        dMonth.selectByVisibleText("April");

        //Departing Day Selection
        WebElement dDays = driver.findElement(By.name("fromDay"));
        Select dDay = new Select(dDays);
        Thread.sleep(2000);
        dDay.selectByVisibleText("5");

        //Arriving Airport Selection
        WebElement aAirport = driver.findElement(By.name("toPort"));
        Select aTo = new Select(aAirport);
        Thread.sleep(2000);
        aTo.selectByValue("Zurich");

        //Arriving Month Selection
        WebElement aMonths = driver.findElement(By.name("toMonth"));
        Select aMonth = new Select(aMonths);
        Thread.sleep(2000);
        aMonth.selectByVisibleText("May");

        //Arriving Day Selection
        WebElement aDays = driver.findElement(By.name("toDay"));
        Select aDay = new Select(aDays);
        Thread.sleep(2000);
        aDay.selectByVisibleText("14");

        //Service Class
        WebElement sClass = driver.findElement(By.xpath("//input[@value='Business']"));
        sClass.click();

        //Airline
        WebElement airline = driver.findElement(By.name("airline"));
        Select selectAirline = new Select(airline);
        Thread.sleep(2000);
        airline.click();
        Thread.sleep(2000);

//        selectAirline.selectByIndex(3);
        selectAirline.selectByVisibleText("Unified Airlines");

        //checkout
        driver.findElement(By.name("findFlights")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.guru99.com/test/newtours/reservation2.php");
        driver.quit();
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupLargusTest {

    @Test
    public void testSearchResult() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        driver.get("https://burgerkingrus.ru/");

        WebElement outPoint = driver.findElement(By.xpath("//input[@class='bk-input__element']"));
        outPoint.sendKeys("Воппер");
        List<WebElement> listOfSearch = driver.findElements(By.xpath
                ("//div[@class='bk-search__list']//div[@class='bk-dish-card__title']"));

        for (WebElement elementOfList: listOfSearch) {
            Assert.assertTrue(elementOfList.getText().contains("Воппер"));
        }

        driver.quit();
    }

    @Test
    public void testCheckSearchUsedCar() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.kia.ru/");
        Thread.sleep(1000);

        List<WebElement> buttonsHamburgerBar = driver.findElements(By.xpath("//span[@class='hamburger-box']"));
        buttonsHamburgerBar.get(1).click();
        Thread.sleep(1000);

        WebElement buttonChoiseAndBuy = driver.findElement(By.partialLinkText("Выбор и покупка"));
        buttonChoiseAndBuy.click();

        WebElement buttonSearchCar = driver.findElement(By.partialLinkText("Поиск авто с пробегом"));
        buttonSearchCar.click();

        List<WebElement> listOfSearch = driver.findElements(By.xpath
                ("//div[@class='catalog-element__descr']"));

        boolean findResult = true;
        for (WebElement elementOfList: listOfSearch) {
            if (!elementOfList.getText().contains("км") && elementOfList.getText().equals("0 км")){
                findResult = false;
            }
        }

        Assert.assertTrue(findResult);
        driver.quit();
    }
}

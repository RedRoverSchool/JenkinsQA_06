package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class OlegfTest extends BaseTest {
    @Test
    public void dishesTest() throws InterruptedException {

        getDriver().get("https://www.canvashomestore.com/");

        getDriver().findElement(By.name("q")).sendKeys("plate\n");
        List<WebElement> platesList = getDriver().findElements(By.xpath("//main//div[@class='grid__item']"));

        String finalPlate;
        for (int i = 0; i < platesList.size(); i++) {
            finalPlate = platesList.get(i).getText();
            assert finalPlate.contains("Blue");
        }
    }
    @Test
    public void bankTest() throws Exception{

        getDriver().get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        Thread.sleep(600);
        getDriver().findElement(By.xpath("//button[@class='btn btn-primary btn-lg']")).click();

        Thread.sleep(600);
        WebElement nameDropDown = getDriver().findElement(By.id("userSelect"));
        Select optionsName = new Select(nameDropDown);
        optionsName.selectByVisibleText("Albus Dumbledore");

        getDriver().findElement(By.xpath("//button[text()='Login']")).click();

        Thread.sleep(600);
        String successfulLogin = getDriver().findElement(By.xpath("//strong[text()=' Welcome ']")).getText();
        Assert.assertEquals(successfulLogin, "Welcome Albus Dumbledore !!");

        getDriver().findElement(By.xpath("//select[contains(@name,'accountSelect')]")).click();
        getDriver().findElement(By.xpath("//option[contains(@value,'number:1012')]")).click();

        Boolean valueOption = getDriver().findElement(By.xpath("//option[contains(@value,'number:1012')]")).isSelected();
        Assert.assertEquals(valueOption, true);

        getDriver().findElement(By.xpath("//button[@ng-class='btnClass2']")).click();
        Thread.sleep(600);
        getDriver().findElement(By.xpath("//input[@type='number']")).sendKeys("26000\n");
        Thread.sleep(600);
        String depositMessage = getDriver().findElement(By.xpath("//span[@class='error ng-binding']")).getText();
        Assert.assertEquals(depositMessage, "Deposit Successful");

    }
}

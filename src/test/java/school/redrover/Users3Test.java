package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class Users3Test extends BaseTest {

    @Test
    public void testCreateUser(){

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Users')]")).click();
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();

        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("User");
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys("User");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("user@gmail.com");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//div[@id= 'breadcrumbBar']/ol/li/a[@href='/']")).click();
        getWait2()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/asynchPeople/']"))).click();

        List<WebElement> listOfPeople = getDriver()
                .findElements(By.xpath("//table[@id='people']//tr//td/a"));
        List<String> userIDs = new ArrayList<>();
        for(WebElement element : listOfPeople){
            userIDs.add(element.getText());
        }

        Assert.assertTrue(userIDs.contains("User"));
    }
}

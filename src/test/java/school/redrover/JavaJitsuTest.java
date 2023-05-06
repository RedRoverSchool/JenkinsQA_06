package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JavaJitsuTest extends BaseTest {
    @Test
    public void testSimple() {
        //getDriver().findElement(By.cssSelector(".empty-state-block > h1"))
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }
    @Test
    public void testAddNewItem() throws InterruptedException {

        WebElement newItemBtn = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemBtn.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input[@id='name']"));
        searchField.sendKeys("Tema10");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']//label"));
        freestyleProject.click();
        WebElement okBtn = getDriver().findElement(By.id("ok-button"));
        okBtn.click();
        WebElement myDropDown = getDriver().findElement(By.xpath("//a[@href='/user/tema']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.moveToElement(myDropDown).click().build().perform();
        WebElement configure = getDriver().findElement(By.xpath("//span[.='Configure']"));
        configure.click();
//        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/user/tema']//button[@class='jenkins-menu-dropdown-chevron']"))).click();
    }

}

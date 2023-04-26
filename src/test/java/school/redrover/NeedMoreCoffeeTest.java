package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class NeedMoreCoffeeTest extends BaseTest {

    @Test
    public void testIdUser() {
        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a"));
        buttonPeople.sendKeys(Keys.RETURN);

        WebElement element = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"person-admin\"]/td[2]/a"))));

        WebElement buttonAdmin = getDriver().findElement(By.xpath("//*[@id=\"person-admin\"]/td[2]/a"));
        buttonAdmin.sendKeys(Keys.RETURN);
        WebElement textUserId = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]"));

        Assert.assertEquals(textUserId.getText(), "Jenkins User ID: admin");

    }

    @Test
    public void testNewProject() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        field.sendKeys("project");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        freestyleProject.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement tabSave = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        tabSave.sendKeys(Keys.RETURN);
        WebElement textProject = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));

        Assert.assertEquals(textProject.getText(), "Project project");

    }

    @Test
    public void testNewFolder() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        field.sendKeys("folder");
        WebElement folder = getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]/ul/li[1]"));
        folder.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement tabSave = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        tabSave.sendKeys(Keys.RETURN);
        WebElement textFolder = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));

        Assert.assertEquals(textFolder.getText(), "folder");
    }

    @Test
    public void testNegativeNewItemSpecialSymbolDollar() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);
        WebElement newField = getDriver().findElement(By.xpath("//input[@id='name']"));
        newField.sendKeys("$",Keys.ENTER);

        WebElement element = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='itemname-invalid']"))));

        WebElement textError = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));

        Assert.assertEquals(textError.getText(), "» ‘$’ is an unsafe character");

    }
}

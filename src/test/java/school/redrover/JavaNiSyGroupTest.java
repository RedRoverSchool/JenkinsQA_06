package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JavaNiSyGroupTest extends BaseTest {

    @Ignore
    @Test
    public void testFullNameVerification(){
        Actions actions = new Actions(getDriver());

        WebElement dropBox = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@href='/user/admin']//button"))));
        actions.moveToElement(dropBox).click().build().perform();

        WebElement btnConfigure = getDriver().findElement(
                By.xpath("//span[text()='Configure']//parent::a"));
        actions.moveToElement(btnConfigure).click().build().perform();

        String inputFullName = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='_.fullName']"))).getAttribute("value"));
        getDriver().findElement(By.xpath("//input[@name='_.fullName']")).getAttribute("value");

        Assert.assertEquals(inputFullName, "admin");
    }

    @Ignore
    @Test
    public void testCreateNewItem() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement newItemBtn = getDriver().findElement(By.xpath("//span[text() = 'New Item']//ancestor::a"));
        newItemBtn.click();

        WebElement textOnOpenedPage = getDriver().findElement(By.xpath("//div[@class='header']//label"));

        Assert.assertEquals(textOnOpenedPage.getText(), "Enter an item name");

        WebElement fieldNewFolder = getDriver().findElement(By.xpath("//div[@class='header']//input"));
        fieldNewFolder.sendKeys("ThisIsMyFolder");

        WebElement FolderBtn = getDriver().findElement(By.xpath("//input[contains(@value, '.Folder')]//ancestor::li"));
        FolderBtn.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okBtn.click();

        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='Submit']")));
        saveBtn.click();

        WebElement confirmation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='description']/following-sibling::div//h2")));

        Assert.assertEquals(confirmation.getText(), "This folder is empty");
    }
}


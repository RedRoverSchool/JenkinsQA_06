package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class NewItemTest extends BaseTest {

    @Test
    public void testNewItemHeader() {
        getDriver().findElement(By.linkText("New Item")).click();

        WebElement h3Header = new WebDriverWait(getDriver(), Duration.ofMillis(3000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class = 'h3']")));
        String actualResult = h3Header.getText();

        Assert.assertEquals(actualResult, "Enter an item name");
    }

    @Ignore
    @Test
    public void testVerifyNewItemsList() {
        List<String> listOfNewItemsExpect = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > span")));
        List<WebElement> listOfNewItems = getDriver().findElements(By.cssSelector("label > span"));

        for (int i = 0; i < listOfNewItemsExpect.size(); i++) {
            Assert.assertEquals(listOfNewItems.get(i).getText(), listOfNewItemsExpect.get(i));
        }
    }

    @Ignore
    @Test
    public void testVerifyButtonIsDisabled() {
        getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']")).click();

        WebElement button = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button")));

        Assert.assertFalse(button.isEnabled());
    }

    @Test
    public void testErrorWhenCreateNewItemWithSpecialCharacterName() {
        String expectedErrorMessage = "» ‘@’ is an unsafe character";

        getDriver()
                .findElement(By.xpath("//a[@href='/view/all/newJob']"))
                .click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.id("name")))
                .sendKeys("@");

        String actualErrorMessage = getDriver()
                .findElement(By.xpath("//div[@id='itemname-invalid']"))
                .getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}

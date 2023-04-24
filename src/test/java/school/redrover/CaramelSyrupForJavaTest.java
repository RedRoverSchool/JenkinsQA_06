package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
public class CaramelSyrupForJavaTest extends BaseTest {
    @Test
    public void testAbramovaHotKeys() {

        WebElement body = getDriver().findElement(By.tagName("body"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        body.sendKeys(Keys.chord(Keys.CONTROL, "k"));
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@role]"));
        WebElement currentElement = getDriver().switchTo().activeElement();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertEquals(searchBox, currentElement);
    }

    @Test
    public void testKhudovaEditDescriptionButtonChanges() {

        WebElement editDescriptionButton = getDriver().findElement(By.id("description-link"));
        editDescriptionButton.click();

        WebElement inputWindow = getDriver().findElement(By.xpath("//textarea[@class = 'jenkins-input   ']"));
        inputWindow.sendKeys("New Description");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name= 'Submit']"));
        saveButton.click();

        WebElement changedDescriptionButton = getDriver().findElement(By.id("description-link"));
        String newButtonText = changedDescriptionButton.getText();

        Assert.assertEquals(newButtonText, "Edit description");
    }

}



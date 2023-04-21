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

public class TestProjectWindow extends BaseTest {

    @Test
    public void testAddProjectDescription() {
        String textDescription = "This is test description for Jenkins project";

        WebElement editDescriptionLink = getDriver().findElement(By.xpath("//a[@id = 'description-link']"));
        editDescriptionLink.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name = 'description']")));
        WebElement jobDescriptionText = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        jobDescriptionText.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        jobDescriptionText.sendKeys(textDescription);
        WebElement descriptionSaveButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        descriptionSaveButton.click();

        WebElement textDescriptionSaved = getDriver().findElement(By.xpath("//div[@id = 'description']/descendant::div"));
        String actual = textDescriptionSaved.getText();

        Assert.assertEquals(actual, textDescription);
    }
}
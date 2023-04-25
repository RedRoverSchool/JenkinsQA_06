package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupGlukhovaTest extends BaseTest {

    @Test
    public void testAddDescriptionOnMainScreen() {
        String testText = "Start building a software project.";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(6000));

        WebElement addDescrButton = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescrButton.click();

        WebElement descriptionText = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        descriptionText.clear();
        descriptionText.sendKeys(testText);

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        saveButton.click();

        WebElement description = getDriver().findElement(By.xpath("//div[@id='description']/div"));

        Assert.assertEquals(description.getText(), testText);
    }
    @Test
    public void testAviaSales() {
        getDriver().get("https://www.aviasales.ru/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(5000));

        WebElement destinationButton = getDriver().findElement(By.xpath("//input[@data-test-id='destination-autocomplete-field']"));
        destinationButton.sendKeys("Коломбо");

        Assert.assertEquals(destinationButton.getText(), "");
    }
}

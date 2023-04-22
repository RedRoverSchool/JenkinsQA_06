package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupHighwayToAqaTest extends BaseTest {
    @Test
    public void testAddBoardDescription() {
        String description = "Some text about dashboard";
        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();
        WebElement inputForm = getDriver().findElement(By.xpath("//div[@class = 'setting-main help-sibling']/textarea"));
        inputForm.sendKeys(description);
        WebElement saveButton = getDriver().findElement(By.xpath("//div/button[@name = 'Submit']"));
        saveButton.click();
        WebElement descriptionText = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));
        Assert.assertEquals(descriptionText.getText(), description);
        addDescriptionButton = getDriver().findElement(By.xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();
        getDriver().findElement(By.xpath("//div[@class = 'setting-main help-sibling']/textarea")).clear();
        saveButton = getDriver().findElement(By.xpath("//div/button[@name = 'Submit']"));
        saveButton.click();
    }
}



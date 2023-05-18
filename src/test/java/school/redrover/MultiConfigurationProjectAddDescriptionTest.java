package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectAddDescriptionTest extends BaseTest {
    @Test
    public void testMultiConfigurationProjectAddDescription () {
        final String text = "text";

        TestUtils.createMultiConfigurationProject(this,"My Multi configuration project",false);

        WebElement addDescription = getDriver().findElement(By.cssSelector("#description-link"));
        addDescription.click();

        WebElement textInput = getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("textarea[name='description']"))));
        textInput.clear();
        textInput.sendKeys(text);

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        saveButton.click();

        WebElement inputAdd = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(inputAdd.getText(), text);
    }

}

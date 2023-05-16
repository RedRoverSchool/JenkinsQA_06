package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProjectAddDescriptionTest extends BaseTest {

    @Ignore
    @Test
    public void testMultiConfigurationProjectAddDescription () {
        final String text = "text";

        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));
        newItem.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//input[@id='name']"))))
                .sendKeys("My Multi configuration project");

        WebElement MultiProject = getDriver().findElement(By.xpath("//span[text() = 'Multi-configuration project']"));
        MultiProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement buttonSave = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        buttonSave.click();

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

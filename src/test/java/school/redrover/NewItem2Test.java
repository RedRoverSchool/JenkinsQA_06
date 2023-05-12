package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem2Test extends BaseTest {

    private static final By NEW_ITEM_BUTTON = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME_INPUT_FIELD = By.xpath("//input[@id='name']");
    private static final By MULTIBRANCH_PIPELINE_TYPE = By.xpath("//span[text() = 'Multibranch Pipeline']");
    private static final By OK_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        final String expectedNameOfMultibranchPipeline = "MyMultibranchPipeline";

        WebElement buttonCreateItem = getDriver().findElement(NEW_ITEM_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(NAME_INPUT_FIELD);
        getWait5().until(ExpectedConditions.elementToBeClickable(fieldInputName));
        fieldInputName.click();
        fieldInputName.sendKeys(expectedNameOfMultibranchPipeline);

        WebElement buttonMultibranchPipeline = getDriver().findElement(MULTIBRANCH_PIPELINE_TYPE);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()", buttonMultibranchPipeline);

        WebElement buttonOk = getDriver().findElement(OK_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(SAVE_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        String actualNameOfMultibranchPipeline = titleName.getText();
        Assert.assertEquals(actualNameOfMultibranchPipeline, expectedNameOfMultibranchPipeline);
    }

    @Test
    public void testCreateNewItemWithNullName() {
        final String expectedErrorMessage = "» This field cannot be empty, please enter a valid name";

        WebElement buttonCreateItem = getDriver().findElement(NEW_ITEM_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(NAME_INPUT_FIELD);
        getWait5().until(ExpectedConditions.elementToBeClickable(fieldInputName));
        fieldInputName.click();

        WebElement buttonMultibranchPipeline = getDriver().findElement(MULTIBRANCH_PIPELINE_TYPE);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()", buttonMultibranchPipeline);

        getWait5().until(ExpectedConditions.elementToBeClickable(buttonMultibranchPipeline));
        buttonMultibranchPipeline.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id = 'itemname-required']"));

        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }

    @Test
    public void testCreateFolder() {
        final String expectedFolderName = "First folder";

        WebElement buttonCreateItem = getDriver().findElement(NEW_ITEM_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(NAME_INPUT_FIELD);
        getWait5().until(ExpectedConditions.visibilityOf(fieldInputName));
        fieldInputName.sendKeys(expectedFolderName);

        WebElement buttonFolder = getDriver().findElement(By.xpath("//span[text()='Folder']"));
        buttonFolder.click();

        WebElement buttonOk = getDriver().findElement(OK_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(SAVE_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        getWait5().until(ExpectedConditions.visibilityOf(titleName));

        String actualFolderName = titleName.getText();
        Assert.assertEquals(actualFolderName, expectedFolderName);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupJavaExplorersTest extends BaseTest {

    @Test
    public void testCreatingNewFolder() {
        final String testFolderName = "First folder";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.click();
        fieldInputName.sendKeys(testFolderName);

        WebElement buttonFolder = getDriver().findElement(By.xpath("//span[text()='Folder']"));
        buttonFolder.click();

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@type='submit']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        String actualFolderName = titleName.getText();
        Assert.assertEquals(actualFolderName, testFolderName);
    }

    @Test
    public void testCreateMultibranchPipeline() {
        final String expectedNameOfMultibranchPipeline = "MyPipeline";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.click();
        fieldInputName.sendKeys(expectedNameOfMultibranchPipeline);

        WebElement buttonMultibranchPipeline = getDriver().findElement(By.xpath("//span[text() = 'Multibranch Pipeline']"));
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click()", buttonMultibranchPipeline);

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@type='submit']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        String actualNameOfMultibranchPipeline = titleName.getText();
        Assert.assertEquals(actualNameOfMultibranchPipeline, expectedNameOfMultibranchPipeline);
    }

   @Ignore
    @Test
    public void testAddDescription() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a[@href='editDescription']"));
        addDescriptionButton.click();

        WebElement descriptionInput = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        descriptionInput.clear();
        descriptionInput.sendKeys("Hello Jenkins!");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        saveButton.click();

        WebElement resultMessage = getDriver().findElement(By.xpath("//div[@id='description']/div"));
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, "Hello Jenkins!");
    }

    @Test
    public void testCreateNewItemWithSpecialCharactersInName() {
        final String incorrectNewItemName = "<>";
        final String expectedErrorMessage = "is an unsafe character";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.click();
        fieldInputName.sendKeys(incorrectNewItemName);

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id = 'itemname-invalid']"));

        Assert.assertTrue(errorMessage.getText().contains(expectedErrorMessage));
    }

    @Test
    public void testCreateNewItemWithNullName() {
        final String expectedErrorMessage = "» This field cannot be empty, please enter a valid name";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement buttonMultibranchPipeline = getDriver().findElement(By.xpath("//span[text() = 'Multibranch Pipeline']"));
        buttonMultibranchPipeline.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id = 'itemname-required']"));

        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }
}
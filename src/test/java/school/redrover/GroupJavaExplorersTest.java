package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.List;

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
    public void testAddDescription() {
        final String newDescription = "Hello Jenkins!";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a[@href='editDescription']"));
        addDescriptionButton.click();

        WebElement descriptionInput = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        descriptionInput.clear();
        descriptionInput.sendKeys(newDescription);

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        saveButton.click();

        WebElement resultMessage = getDriver().findElement(By.xpath("//div[@id='description']/div"));
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, newDescription);
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
    public void testInputSpecialCharacters() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<String> listOfSpecialCharacters =
                List.of("!", "#", "$", "%", "&", "*", "/", ":", ";", "<", ">", "?", "@", "[", "]", "|", "\\", "^");

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        for (String listOfSpecialCharacter : listOfSpecialCharacters) {
            String errorMessage = "» ‘" + listOfSpecialCharacter + "’ is an unsafe character";

            WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
            fieldInputName.clear();
            fieldInputName.sendKeys(listOfSpecialCharacter);

            WebElement resultMessage = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
            String messageValue = resultMessage.getText();

            Assert.assertEquals(messageValue, errorMessage);
        }

    }

    @Test
    public void testCreateNewPipelineAsCopy() {
        final String newName = "1st job";
        final String secondName = "2nd job";
        final String expectedNameOfCopyJob = "Pipeline 2nd job";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.click();
        fieldInputName.sendKeys(newName);

        WebElement buttonPipeline = getDriver().findElement(By.xpath("//span[text() = 'Pipeline']"));
        buttonPipeline.click();

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@type='submit']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement homeLink = getDriver().findElement(By.xpath("//a[@href = '/']"));
        homeLink.click();

        WebElement buttonCreateItem2 = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem2.click();

        WebElement fieldInputName2 = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName2.click();
        fieldInputName2.sendKeys(secondName);

        WebElement buttonPipeline2 = getDriver().findElement(By.xpath("//span[text() = 'Pipeline']"));
        buttonPipeline2.click();

        WebElement inputFrom = getDriver().findElement(By.xpath("//input[@id = 'from']"));
        inputFrom.sendKeys(newName);

        WebElement buttonOk2 = getDriver().findElement(By.xpath("//button[@type='submit']"));
        buttonOk2.click();

        WebElement buttonSave2 = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave2.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        String actualNameOfCopyJob = titleName.getText();
        Assert.assertEquals(actualNameOfCopyJob, expectedNameOfCopyJob);
    }

}

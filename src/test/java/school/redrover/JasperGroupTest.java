package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasperGroupTest extends BaseTest {

    @Test
    public void testFindElement() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement searchField = getDriver().findElement(By.xpath("//input[@name = 'q']"));
        searchField.sendKeys("admin");
        searchField.sendKeys(Keys.RETURN);

        WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-panel\"]/div[2]")));
        Assert.assertEquals(actualResult.getText(), "Jenkins User ID: admin");
    }

    @Test
    public void testCreateNewItem() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement createItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        createItem.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"name\"]")));
        nameField.sendKeys("New Item");

        WebElement typeOfItems = getDriver().findElement(By.xpath("//*[@class=\"hudson_model_FreeStyleProject\"]"));
        typeOfItems.click();

        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ok-button\"]")));
        okButton.click();

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"description\"]")));
        descriptionField.sendKeys("New Item");

        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button apply-button\"]")));
        applyButton.click();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button jenkins-button--primary \"]")));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//*[@class=\"job-index-headline page-headline\"]"));
        Assert.assertEquals(actualResult.getText(), "Project New Item");
    }

    @Test
    public void testChangeName() {
        WebElement settingsMenuButton = getDriver().findElement(By.xpath("//div[@class = 'login page-header__hyperlinks']/a[@class = 'model-link']"));
        settingsMenuButton.click();

        WebElement configureButton = getDriver().findElement(By.xpath("//span[text() = 'Configure']/.."));
        configureButton.click();

        WebElement fullNameTextBox = getDriver().findElement(By.xpath("//input[@name = '_.fullName']"));
        fullNameTextBox.clear();
        fullNameTextBox.sendKeys("User");
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        submitButton.click();

        WebElement h1Name = getDriver().findElement(By.xpath("//div[@id = 'main-panel']/h1"));
        WebElement headerMenuName = getDriver().findElement(By.xpath("//div[@class = 'login page-header__hyperlinks']/a[@class = 'model-link']/span"));
        List<WebElement> names = new ArrayList<>(Arrays.asList(h1Name, headerMenuName));

        for (WebElement name : names) {
            Assert.assertEquals(name.getText(), "User");
        }
    }

    @Ignore
    @Test
    public void testFolderEmptyNameChange() {
        WebElement newItemButton = getDriver().findElement(By.xpath("//span[text()='New Item']/.."));
        newItemButton.click();

        WebElement nameBox = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        nameBox.sendKeys("Folder");

        WebElement modeFolder = getDriver().findElement(By.xpath("//li[@class = 'com_cloudbees_hudson_plugins_folder_Folder']"));
        modeFolder.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        saveButton.click();

        WebElement renameButton = getDriver().findElement(By.xpath("//span[text()='Rename']/.."));
        renameButton.click();

        WebElement newNameBox = getDriver().findElement(By.xpath("//input[@name = 'newName']"));
        newNameBox.clear();

        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        submitButton.click();

        WebElement headerError = getDriver().findElement(By.xpath("//h1"));
        WebElement messageError = getDriver().findElement(By.xpath("//p"));

        Assert.assertEquals(headerError.getText(), "Error");
        Assert.assertEquals(messageError.getText(), "No name is specified");
    }

    @Test
    public void testCreatingNewProject() throws InterruptedException {
        WebElement newJobButton = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        newJobButton.click();

        Thread.sleep(2000);

        WebElement name = getDriver().findElement(By.xpath("//input[@id='name']"));
        name.sendKeys("TestProject");

        WebElement projectButton = getDriver().findElement(By.xpath("(//label)[2]"));
        projectButton.click();

        WebElement okButton = getDriver().findElement(By.xpath(" //button[@id='ok-button']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("(//button[normalize-space()='Save'])[1]"));
        saveButton.click();

        WebElement mainImage = getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']"));
        mainImage.click();

        WebElement textElement = getDriver().findElement(By.xpath("//tr[@id='job_TestProject']//td[3]"));
        Assert.assertEquals(textElement.getText(), "TestProject");
    }

    @Test
    public void testCountUsers() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement users = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a")));
        users.click();

        List<WebElement> usersList = getDriver().findElements(By.xpath("//*[@id=\"people\"]"));
        Assert.assertTrue(usersList.size() > 0, "List of users are empty");
    }

    @Test
    public void testFindPeopleJenkins() {
        WebElement users = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a/span[2]"));

        Assert.assertEquals(users.getText(), "People");
    }

    @Test
    public void testSearchResultNothingSeemsToMatch() {

        WebElement newItemField = getDriver().findElement(By.xpath("//input[@id = 'search-box' ]"));
        newItemField.sendKeys("jenk");
        newItemField.sendKeys(Keys.RETURN);

        WebElement searchResult1 = getDriver().findElement(By.xpath("//div[text() = 'Nothing seems to match.']"));
        Assert.assertEquals(searchResult1.getText(), "Nothing seems to match.");
    }

    @Test
    public void testValidationMessage() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("createItem")).click();
        WebElement message = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(message.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testFindAllElements() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//*[@class=\"task-link-text\"]"));
        List<String> expectedElements = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        for (int i = 0; i < elements.size(); i++) {
            Assert.assertEquals(elements.get(i).getText(), (expectedElements.get(i)));
        }
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JavaNiSyGroupTest extends BaseTest {

    @Ignore
    @Test
    public void testFullNameVerification(){
        Actions actions = new Actions(getDriver());

        WebElement dropBox = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@href='/user/admin']//button"))));
        actions.moveToElement(dropBox).click().build().perform();

        WebElement btnConfigure = getDriver().findElement(
                By.xpath("//span[text()='Configure']//parent::a"));
        actions.moveToElement(btnConfigure).click().build().perform();

        String inputFullName = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@name='_.fullName']"))).getAttribute("value"));
        getDriver().findElement(By.xpath("//input[@name='_.fullName']")).getAttribute("value");

        Assert.assertEquals(inputFullName, "admin");
    }


    @Test
    public void testCreateNewItem() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement newItemBtn = getDriver().findElement(By.xpath("//span[text() = 'New Item']//ancestor::a"));
        newItemBtn.click();

        WebElement fieldNewFolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header']//input")));
        fieldNewFolder.sendKeys("ThisIsMyFolder");

        WebElement folderBtn = getDriver().findElement(By.xpath("//input[contains(@value, '.Folder')]//ancestor::li"));
        folderBtn.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okBtn.click();

        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='Submit']")));
        saveBtn.click();

        WebElement confirmation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='description']/following-sibling::div//h2")));

        Assert.assertEquals(confirmation.getText(), "This folder is empty");
    }

    @Test
    public void testDeleteFolder()  {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        WebElement newItemBtn = getDriver().findElement(By.xpath("//span[text() = 'New Item']//ancestor::a"));
        newItemBtn.click();

        WebElement fieldNewFolder = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header']//input")));
        fieldNewFolder.sendKeys("ThisIsMyFolder");

        WebElement folderBtn = getDriver().findElement(By.xpath("//input[contains(@value, '.Folder')]//ancestor::li"));
        folderBtn.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okBtn.click();
        Actions action = new Actions(getDriver());
        WebElement toolBarFolder =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/job/ThisIsMyFolder/']")));
        WebElement toolBarArrow = getDriver().findElement(By.xpath("//a[@href='/job/ThisIsMyFolder/']/button"));
        action.moveToElement(toolBarFolder, 45, 0).click().build().perform();
        action.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/ThisIsMyFolder/delete']"))))
                .click().build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        WebElement textAfterDeletion = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(textAfterDeletion.getText(), "Welcome to Jenkins!");
    }
    @Test
    public void testCreateDescription() {
        WebElement buttonAddDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        buttonAddDescription.click();
        WebElement textInputArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        String text = "Hello!";
        textInputArea.clear();
        textInputArea.sendKeys(text);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        WebElement description = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));

        Assert.assertEquals(description.getText(), text);

        buttonAddDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        buttonAddDescription.click();
        textInputArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textInputArea.clear();
    }
    @Test
    public void testAddDescription() {
        WebElement btnAddDescr = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        btnAddDescr.click();

        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textArea.clear();
        textArea.sendKeys("You are welcome!");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement descriptionResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));

        Assert.assertEquals(descriptionResult.getText(), "You are welcome!");
    }
    @Test
    public void testCreateItem() {
        WebElement btnCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        btnCreateItem.click();

        WebElement inputItemName = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        inputItemName.sendKeys("First Folder");

        WebElement createFreeStyleProject = getDriver()
                .findElement(By.xpath("//span[text()='Freestyle project']"));
        createFreeStyleProject.click();

        WebElement btnOk = getDriver().findElement(By.xpath("//div[@class='btn-decorator']"));
        btnOk.click();

        WebElement btnSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        btnSave.click();

        WebElement projectName = getDriver()
                .findElement(By.xpath("//h1[text()='Project First Folder']"));

        Assert.assertEquals(projectName.getText(), "Project First Folder");
    }
}




package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.List;

public class ComradesAdelanteGroupTest extends BaseTest {

    @Test
    public void testCreateJobJenkins() {
        WebElement createJobLink = getDriver().findElement(By.xpath("//span[contains(text(), 'Create a job')]"));
        createJobLink.click();

        WebElement nameJobField = getDriver().findElement(By.xpath("//*[@class = 'jenkins-input']"));
        nameJobField.sendKeys("First jenkins job");
        nameJobField.sendKeys(Keys.RETURN);

        WebElement selectTypeOfJob = getDriver().findElement(By.xpath("//*[@class = 'hudson_model_FreeStyleProject']"));
        selectTypeOfJob.click();

        WebElement buttonConfirmation = getDriver().findElement(By.xpath("//*[@class = 'jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']"));
        buttonConfirmation.click();

        WebElement generalInfoField = getDriver().findElement(By.xpath("//*[@class = 'jenkins-app-bar__content']/child::h2"));
        Assert.assertEquals(generalInfoField.getText(), "General");
    }
    @Test
    public void testVerifyNameOfFreestyleProject() {
        String nameOfJob = "Simple test";
        String expectedHeader = "Project ".concat(nameOfJob);

        WebElement newItemLink = getDriver().findElement(
                By.xpath("//div/span/a[@href='/view/all/newJob']")
        );
        newItemLink.click();

        WebElement inputName = getDriver().findElement(
                By.xpath("//input[@class='jenkins-input']")
        );
        inputName.sendKeys(nameOfJob);

        WebElement chooseCategory = getDriver().findElement(
                By.xpath("//span[text()='Freestyle project']")
        );
        chooseCategory.click();

        WebElement btnOk = getDriver().findElement(
                By.xpath("//div/button")
        );
        btnOk.click();

        WebElement btnSave = getDriver().findElement(
                By.xpath("//button[@formnovalidate='formNoValidate']")
        );
        btnSave.click();

        WebElement nameOfHeader = getDriver().findElement(
                By.xpath("//div/h1")
        );
        String actualHeader = nameOfHeader.getText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }
    @Test
    public void testCreateNewBuild() {
        WebElement taskLinkText = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));

        taskLinkText.click();
        WebElement nameItem = getDriver().findElement(By.xpath("//*[@name='name']"));

        nameItem.sendKeys("Hello world");
        WebElement buttonFreestyleProj = getDriver().findElement(By.xpath("//*[@class='label']"));

        buttonFreestyleProj.click();
        WebElement buttonOk = getDriver().findElement(By.xpath("//*[@id='ok-button']"));

        buttonOk.click();
        WebElement buttonSave = getDriver().findElement(By.xpath("//*[@formnovalidate='formNoValidate']"));

        buttonSave.click();

        Assert.assertEquals( getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),"Project Hello world");
    }

    @Test
    public void testCreateFirstJobb() {
        WebElement createItem = getDriver().findElement(
                By.xpath("//*[text()='Create a job']"));
        createItem.click();

        WebElement textBoxOfNewName = getDriver().findElement(
                By.xpath("//*[@class='jenkins-input']"));
        textBoxOfNewName.sendKeys("First job");

        WebElement createTaskOfFreeConfiguration = getDriver().findElement(
                By.xpath("//*[@class='hudson_model_FreeStyleProject']"));
        createTaskOfFreeConfiguration.click();

        WebElement submitButton = getDriver().findElement(
                By.xpath("//*[@id='ok-button']"));
        submitButton.click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[text()='First job']")).getText(), "First job");
    }

    @Test
    public void testUserPage() {
        WebElement userIcon = getDriver().findElement(By.xpath("//a[@href=\"/user/admin\"]"));
        String expectedUserPageHeader = userIcon.getText();
        userIcon.click();

        WebElement userPageHeader = getDriver().findElement(By.xpath("//h1"));
        String actualUserPageHeader = userPageHeader.getText();

        Assert.assertEquals(actualUserPageHeader, expectedUserPageHeader);
    }

    @Test
    public void testCreateNewBuildJob() throws InterruptedException {
        WebElement taskLinkText = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));

        taskLinkText.click();
        WebElement nameItem = getDriver().findElement(By.xpath("//*[@name='name']"));

        nameItem.sendKeys("Hello world");
        WebElement buttonFreestyleProj = getDriver().findElement(By.xpath("//*[@class='label']"));

        buttonFreestyleProj.click();
        WebElement buttonOk = getDriver().findElement(By.xpath("//*[@id='ok-button']"));

        buttonOk.click();
        WebElement description = getDriver().findElement(By.xpath("//*[@name='description']"));

        description.sendKeys("Hello world java test program");
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport();
        new Actions(getDriver())
                .scrollFromOrigin(scrollOrigin, 0, 600)
                .perform();

        WebElement clickable = getDriver().findElement(By.xpath("//*[@id='radio-block-1']"));
        new Actions(getDriver())
                .click(clickable)
                .perform();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement textRepository = getDriver().findElement(By.xpath("//*[@checkdependson='credentialsId']"));

        textRepository.sendKeys("https://github.com/kriru/firstJava.git");

        scrollOrigin = WheelInput.ScrollOrigin.fromViewport();
        new Actions(getDriver())
                .scrollFromOrigin(scrollOrigin, 0, 2000)
                .perform();

        clickable = getDriver().findElement(By.xpath("//*[@id='yui-gen9-button']"));
        new Actions(getDriver())
                .click(clickable)
                .perform();

        clickable = getDriver().findElement(By.xpath("//*[@id='yui-gen24']"));
        new Actions(getDriver())
                .click(clickable)
                .perform();
        WebElement executeWinCommand = getDriver().findElement(By.xpath("//*[@name='description']"));

        executeWinCommand.sendKeys("javac HelloWorld.java\njava HelloWorld");
        WebElement buttonApply = getDriver().findElement(By.xpath("//*[@name='Apply']"));

        buttonApply.click();
        WebElement buttonSave = getDriver().findElement(By.xpath("//*[@name='Submit']"));

        buttonSave.click();
        WebElement buildNow = getDriver().findElement(By.xpath("//*[@href='/job/Hello%20world/build?delay=0sec']"));

        buildNow.click();
        WebElement buildName = getDriver().findElement(By.xpath("//*[@class='model-link inside build-link display-name']"));

        buildName.click();
        WebElement consoleOut = getDriver().findElement(By.xpath("//*[@class='icon-terminal icon-xlg']"));

        consoleOut.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@class='console-output']")).getText().contains("Finished: SUCCESS"));
    }

    @Test
    public void testCountTask() {
        List<WebElement> sidePanelCountTasksList = getDriver().findElements(
                By.xpath("//*[@class='task ']")
        );
        Assert.assertEquals(sidePanelCountTasksList.size(),5);
    }
}
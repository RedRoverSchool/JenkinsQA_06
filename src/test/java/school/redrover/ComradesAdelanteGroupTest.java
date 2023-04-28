package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

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

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Project Hello world");
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
}
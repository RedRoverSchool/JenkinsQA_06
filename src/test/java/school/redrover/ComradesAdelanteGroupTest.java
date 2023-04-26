package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

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
}
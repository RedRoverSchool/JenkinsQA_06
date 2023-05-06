package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class RenamePipelineTest extends BaseTest {

    @Test
    public void testRenamePipeline() throws InterruptedException {

        WebElement newItemLink = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemLink.click();

        WebElement textField = getDriver().findElement(By.xpath("//input[@id='name']"));
        textField.sendKeys("TestPipelineJava");

        WebElement pipelineProject = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Pipeline']")));
        pipelineProject.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okBtn.click();

        WebElement saveBtn = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
        JavascriptExecutor js= (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)",saveBtn);

        saveBtn.click();

        WebElement projectName = getDriver().findElement(By.xpath("//h1[normalize-space()='Pipeline TestPipelineJava']"));
        Assert.assertTrue(projectName.isDisplayed());

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();

        WebElement projectNameOnDashboard = getDriver().findElement(By.xpath("//span[.='TestPipelineJava']"));
        Assert.assertTrue(projectNameOnDashboard.isDisplayed());

       projectNameOnDashboard.click();

       WebElement renameBtn = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/TestPipelineJava/confirm-rename']")));
       renameBtn.click();

        WebElement renameTextField = getDriver().findElement(By.xpath("//input[@class='jenkins-input validated  ']"));
        renameTextField.clear();
        renameTextField.sendKeys("TestPipelineJavaJitsu");


       WebElement rename = getDriver().findElement(By.xpath("//button[normalize-space()='Rename']"));
       rename.click();

       WebElement projectNameUpdate = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));
       Assert.assertTrue(projectNameUpdate.getText().contains("TestPipelineJavaJitsu"));
    }
}

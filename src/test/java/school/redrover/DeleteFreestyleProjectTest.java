package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeleteFreestyleProjectTest extends BaseTest {
    private final String PROJECT_NAME = "Project 2";
    @Ignore
    @Test
    private void createFreestyleProject() {
        WebElement newItemMenu = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/view/all/newJob']")));
        newItemMenu.click();

        WebElement nameInputField = getDriver().findElement(By.id("name"));
        nameInputField.sendKeys(PROJECT_NAME);

        WebElement freestyleProjectButton = getDriver().findElement(By.xpath("//li//span[contains(text(), 'Freestyle')]"));
        freestyleProjectButton.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();
    }
    @Ignore
    @Test
    public void testDeleteFreestyleProject() {
        final String expectedH2 = "This folder is empty";
        createFreestyleProject();

        WebElement dashboardBreadCrumb = getDriver().findElement(By.xpath("//li/a[contains(text(),'Dashboard')]"));
        dashboardBreadCrumb.click();

        WebElement project = getDriver().findElement(By.xpath("//span[contains(text(), '" + PROJECT_NAME + "')]"));
        project.click();

        WebElement deleteProjectLink = getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Project')]"));
        deleteProjectLink.click();
        getDriver().switchTo().alert().accept();

        WebElement myViews = getDriver().findElement(By.xpath("//a[@href = '/me/my-views']"));
        myViews.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h2")).getText(), expectedH2);
    }
    @Ignore
    @Test
    public void testDeleteFreestyleProject2(){
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        WebElement inputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        getWait2().until(ExpectedConditions.visibilityOf(inputName));
        inputName.sendKeys("One");

        getDriver().findElement(By.xpath("//li[@class= 'hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath(("//button[@id= 'ok-button']"))).click();
        getDriver().findElement(By.xpath("//button[@name= 'Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project One");

        getDriver().findElement(By.xpath("//span[contains(text(),'Delete')]")).click();
        getDriver().switchTo().alert().accept();

        WebElement myViews = getDriver().findElement(By.xpath("//a[@href = '/me/my-views']"));
        myViews.click();

        Assert.assertEquals( (getDriver().findElement(By.xpath("//h2")).getText()),  "This folder is empty");
    }
}

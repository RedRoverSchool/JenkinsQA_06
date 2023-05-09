package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeleteFreestyleProjectTest extends BaseTest {

    private final String PROJECT_NAME = "Project 2";

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
}

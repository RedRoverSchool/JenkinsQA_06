package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineTest2 extends BaseTest {


    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By PIPELINE = By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']");
    private static final By BUTTON = By.cssSelector("#ok-button");
    private static final By DESCRIPTION = By.name("description");
    private static final By SUBMIT = By.name("Submit");
    private static final By TEXT_PIPELINE = By.cssSelector(".job-index-headline.page-headline");
    private static final By RENAME = By.xpath("//a[@href='/job/FirstPipeline/confirm-rename']");
    private static final By RENAME_LINE = By.xpath("//input[@name='newName']");

    String name = "FirstPipelin";
    String newName = "PipeLine New";
    String descriptionText = "Pipeline text";
    String renameText = "Pipeline1";

    @Test
    public void testCreatePipeline() {

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NAME).sendKeys(name);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(BUTTON).click();
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Pipeline " + name, getDriver().findElement(TEXT_PIPELINE).getText());
    }

    @Test
    public void testRenamePipeline() {
        createPipeline();

        getDriver().findElement(RENAME).click();
        getDriver().findElement(RENAME_LINE).clear();
        getDriver().findElement(RENAME_LINE).sendKeys(renameText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Pipeline " + renameText, getDriver().findElement(TEXT_PIPELINE).getText());
    }

    public void createPipeline() {

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement itemNameInput = getDriver().findElement(By.id("name"));
        itemNameInput.sendKeys(name);

        WebElement itemTypeRadio = getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"));
        itemTypeRadio.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();
    }

    @Test
    public void pipelineRenameTest() {
        createPipeline();

        WebElement renameButton = getDriver().findElement(By.xpath("//span[text()='Rename']/.."));
        renameButton.click();

        WebElement newNameInput = getDriver().findElement(By.name("newName"));
        newNameInput.clear();
        newNameInput.sendKeys(newName);

        WebElement submitRenameButton = getDriver().findElement(By.name("Submit"));
        submitRenameButton.click();

        WebElement pipelineName = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));
        Assert.assertEquals("Pipeline " + newName, pipelineName.getText());
    }
}

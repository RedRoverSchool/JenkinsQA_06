package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class PipelineConfigureTest extends BaseTest {
    final String EXPECTED_RESULT = "New pipeline project";

    public void createPipeline() {
        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@href = 'newJob']"));
        createJobButton.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("test-pipeline");

        WebElement newPipeline = getDriver()
                .findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']//li[2]"));
        newPipeline.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
    }

    public WebElement findElement(By by){
        return getDriver().findElement(by);
    }

    public void clickLinkButton(String menuButton){
        findElement(By.xpath(String.format("//*[text()='%s']", menuButton))).click();
    }

    public void createPipelineProject(String nameOfProject, String typeOfProject){
        findElement(By.className("task-icon-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(nameOfProject);

        List<WebElement> listItemOptions = getDriver().findElements(By.id("j-add-item-type-standalone-projects"));
        for(WebElement element:listItemOptions){
            if (element.getText().contains(typeOfProject)){
                element.click();
            }
        }
        findElement(By.id("ok-button")).click();
    }


    @Test
    public void testSetDescription() {
        String descriptionText = "This is a test description";

        createPipeline();

        WebElement descriptionField = getDriver().findElement(By.name("description"));
        descriptionField.sendKeys(descriptionText);

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//*[@id='description']/div"));

        Assert.assertEquals(actualDescription.getText(), descriptionText);
    }

    @Test
    public void testDiscardOldBuildsIsCheckedEmptyDaysAndBuildsField() {
        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement configureMenu = getDriver()
                .findElement(By.xpath("//*[@href='/job/test-pipeline/configure']"));
        configureMenu.click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
    }

    @Test
    public void testDiscardOldBuildsIsChecked7DaysAnd5Builds() {
        final String days = "7";
        final String builds = "5";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement daysToKeepField = getDriver().findElement(By.name("_.daysToKeepStr"));
        daysToKeepField.sendKeys(days);

        WebElement buildsToKeepField = getDriver().findElement(By.name("_.numToKeepStr"));
        buildsToKeepField.sendKeys(builds);

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement configureMenu = getDriver()
                .findElement(By.xpath("//*[@href='/job/test-pipeline/configure']"));
        configureMenu.click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));
        WebElement daysToKeep = getDriver().findElement(By.name("_.daysToKeepStr"));
        WebElement buildsToKeep = getDriver().findElement(By.name("_.numToKeepStr"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(daysToKeep.getAttribute("value"), days);
        Assert.assertEquals(buildsToKeep.getAttribute("value"), builds);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Days() {
        final String days = "0";
        final String errorMessage = "Not a positive integer";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement daysToKeepField = getDriver().findElement(By.name("_.daysToKeepStr"));
        daysToKeepField.sendKeys(days);

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        WebElement clickOutsideOfInputField = getDriver()
                .findElement(By.xpath("//*[@name='strategy']/div/div"));
        clickOutsideOfInputField.click();

        WebElement actualErrorMessage = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[@name='strategy']//div[@class='error']")));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(actualErrorMessage.getText(), errorMessage);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Builds() {
        final String builds = "0";
        final String errorMessage = "Not a positive integer";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement buildsToKeepField = getDriver().findElement(By.name("_.numToKeepStr"));
        buildsToKeepField.sendKeys(builds);

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        WebElement clickOutsideOfInputField = getDriver()
                .findElement(By.xpath("//*[@name='strategy']/div/div"));
        clickOutsideOfInputField.click();

        WebElement actualErrorMessage = getWait5().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//*[@name='strategy']//div[@class='error']")));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(actualErrorMessage.getText(), errorMessage);
    }

    @Test
    public void testPipelineCreation() {
        final String PIPELINE_NAME = "My_pipeline";

        WebElement newItem = getDriver().findElement(By.xpath(
                "//a[@href='/view/all/newJob']"));
        newItem.click();

        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("name"))));

        WebElement pipelineType = getDriver().findElement(By.cssSelector(".org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        pipelineType.click();

        WebElement nameField = getDriver().findElement(By.id("name"));
        nameField.sendKeys(PIPELINE_NAME);

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit")));

        WebElement enableToggles = getDriver().findElement(By.id("toggle-switch-enable-disable-project"));
        boolean isPipelineEnabled = Boolean.parseBoolean(getDriver().findElement(By.xpath("//input[@name='enable']"))
                .getAttribute("value"));
        if (isPipelineEnabled){
            enableToggles.click();
        }

        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.textToBe(By.tagName("h1"), "Pipeline " + PIPELINE_NAME));

        String disabledWarning = getDriver().findElement(By.id("enable-project")).getText();

        WebElement configurePipeline= getDriver().findElement(By.linkText("Configure"));
        configurePipeline.click();

        getWait5().until(ExpectedConditions.textToBe(By.tagName("h2"), "General"));

        boolean isPipelineEnabledAfterDisable = Boolean.parseBoolean(getDriver().findElement(
                By.xpath("//input[@name='enable']")).getAttribute("value"));

        Assert.assertTrue(disabledWarning.contains("This project is currently disabled"));
        Assert.assertFalse(isPipelineEnabledAfterDisable,"false");
    }

    @Test
    public void addDescriptionPipelineProjectTest(){
        String description = "This is a project for school test";
        createPipelineProject(EXPECTED_RESULT, "Pipeline");
        findElement(By.name("description")).sendKeys(description);
        findElement(By.name("Apply")).click();
        WebElement messageSaved = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("notification-bar")));

        Assert.assertTrue(messageSaved.isDisplayed());

        clickLinkButton("Dashboard");
        clickLinkButton(EXPECTED_RESULT);

        WebElement fieldDescription = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));

        Assert.assertTrue(fieldDescription.getText().contains(description));
    }
}

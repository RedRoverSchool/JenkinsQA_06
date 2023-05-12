package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.*;

public class PipelineProjectTest extends BaseTest {
    final String EXPECTED_RESULT = "New pipeline project";

    private void createWithoutDescription(String name) {
        getDriver().findElement(By.xpath("//a[@href = 'newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']//li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();
    }

    public WebElement findElement(By by){
        return getDriver().findElement(by);
    }

    public void clickPageButton(String menuButton){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[text()='%s']", menuButton)))).click();
    }

    public void clickTaskButton(String nameOfTask){
        List<WebElement> taskIconLinks = getDriver().findElements(By.className("task-icon-link"));
        List<WebElement> taskLinksText = getDriver().findElements(By.xpath("//*[@id='tasks']/*//span[2]"));
        List<String> textOfTaskLink = new ArrayList<>();
        for (WebElement taskLinkText : taskLinksText){
            textOfTaskLink.add(taskLinkText.getText());
        }

        Map<WebElement, String> buttonAndTask = new HashMap<>();
        int i = 0;
        for (WebElement taskIcon: taskIconLinks) {
            buttonAndTask.put(taskIconLinks.get(i), textOfTaskLink.get(i));
            i++;
        }

        for (Map.Entry<WebElement, String> entry : buttonAndTask.entrySet()) {
            if (entry.getValue().equals(nameOfTask)) {
                entry.getKey().click();
            } else {
                System.out.println("Wrong item");
            }
        }
    }

    public void createPipelineProject(String nameOfProject, String typeOfProject){
        WebElement fieldName = findElement(By.id("name"));
        getWait2().until(ExpectedConditions.visibilityOf(fieldName)).sendKeys(nameOfProject);

        List<WebElement> listItemOptions = getDriver().findElements(By.id("j-add-item-type-standalone-projects"));
        for(WebElement element:listItemOptions){
            if (element.getText().contains(typeOfProject)){
                element.click();
            }
        }
        findElement(By.id("ok-button")).click();
    }

    public void clickButtonApply(){
        findElement(By.name("Apply")).click();
    }

    public String statusOfProject(){
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("svg-icon"))).getAttribute("title");
    }

    @Test
    public void testSetDescription() {
        String descriptionText = "This is a test description";

        createWithoutDescription("test-pipeline");
        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        getDriver().findElement(By.name("description")).sendKeys(descriptionText);
        getDriver().findElement(By.name("Submit")).click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//*[@id='description']/div"));

        Assert.assertEquals(actualDescription.getText(), descriptionText);
    }

    @Test
    public void testDiscardOldBuildsIsChecked() {
        createWithoutDescription("test-pipeline");
        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
    }

    @Test
    public void testDiscardOldBuildsIsCheckedWithValidParams() {
        final String days = "7";
        final String builds = "5";

        createWithoutDescription("test-pipeline");
        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys(days);
        getDriver().findElement(By.name("_.numToKeepStr")).sendKeys(builds);
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(getDriver().findElement(By.name("_.daysToKeepStr")).getAttribute("value"), days);
        Assert.assertEquals(getDriver().findElement(By.name("_.numToKeepStr")).getAttribute("value"), builds);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Days() {
        final String days = "0";
        final String errorMessage = "Not a positive integer";

        createWithoutDescription("test-pipeline");
        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys(days);

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        WebElement daysToKeepLabel = getDriver()
                .findElement(By.xpath("//*[@name='strategy']/div/div"));
        daysToKeepLabel.click();

        WebElement actualErrorMessage = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[@name='strategy']//div[@class='error']")));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(actualErrorMessage.getText(), errorMessage);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Builds() {
        final String builds = "0";
        final String errorMessage = "Not a positive integer";

        createWithoutDescription("test-pipeline");
        getDriver().findElement(By.xpath("//*[@href='/job/test-pipeline/configure']")).click();

        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("_.numToKeepStr")).sendKeys(builds);

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

        getWait2().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.tagName("h1")),"Pipeline"));

        String disabledWarning = getDriver().findElement(By.id("enable-project")).getText();

        WebElement configurePipeline= getDriver().findElement(By.xpath("//a[contains(@href,'configure')]"));
        configurePipeline.click();

        getWait5().until(ExpectedConditions.textToBe(By.tagName("h2"), "General"));

        boolean isPipelineEnabledAfterDisable = Boolean.parseBoolean(getDriver().findElement(
                By.xpath("//input[@name='enable']")).getAttribute("value"));

        Assert.assertTrue(disabledWarning.contains("This project is currently disabled"));
        Assert.assertFalse(isPipelineEnabledAfterDisable);
    }
@Ignore
    @Test
    public void addDescriptionPipelineProjectTest(){
        String description = "This is a project for school test";
        clickTaskButton("New Item");
        createPipelineProject(EXPECTED_RESULT, "Pipeline");
        findElement(By.name("description")).sendKeys(description);
        clickButtonApply();
        WebElement messageSaved = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("notification-bar")));

        Assert.assertTrue(messageSaved.isDisplayed());

        clickPageButton("Dashboard");
        clickPageButton(EXPECTED_RESULT);

        WebElement fieldDescription = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));

        Assert.assertTrue(fieldDescription.getText().contains(description));
    }

    @Ignore
    @Test
    public void disablePipelineProjectTest(){
        clickTaskButton("New Item");
        createPipelineProject(EXPECTED_RESULT, "Pipeline");

        clickPageButton("Dashboard");
        String statusBeforeDisable = statusOfProject();

        clickPageButton(EXPECTED_RESULT);
        clickTaskButton("Configure");

        findElement(By.id("toggle-switch-enable-disable-project")).click();
        clickButtonApply();

        clickPageButton("Dashboard");

        String statusAfterDisable = statusOfProject();

        Assert.assertNotEquals(statusBeforeDisable, statusAfterDisable);
    }
}

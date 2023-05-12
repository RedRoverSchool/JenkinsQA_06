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
    private static final String NAME = "test-pipeline";
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By DISCARD_OLD_BUILDS_CHECKBOX = By.id("cb2");
    private static final By DISCARD_OLD_BUILDS_LABEL = By.xpath("//label[contains(text(),'Discard old builds')]");
    private static final By DAYS_TO_KEEP_LABEL = By.xpath("//*[@name='strategy']/div/div");
    private static final By DAYS_TO_KEEP_FIELD = By.name("_.daysToKeepStr");
    private static final By BUILDS_TO_KEEP_FIELD = By.name("_.numToKeepStr");
    private static final String ERROR_MESSAGE = "Not a positive integer";
    private static final By ACTUAL_ERROR_MESSAGE = By.xpath("//*[@name='strategy']//div[@class='error']");
    private static final By CONFIGURE_MENU = By.xpath("//*[@href='/job/test-pipeline/configure']");
    private static final By JOB_PIPELINE = By.xpath("//*[@id='j-add-item-type-standalone-projects']//li[2]");

    private void createPipelineWithoutDescription(String name) {
       getDriver().findElement(By.xpath("//a[@href = 'newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(JOB_PIPELINE).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(SAVE_BUTTON).click();
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
        final String descriptionText = "This is a test description";

        createPipelineWithoutDescription(NAME);
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(By.name("description")).sendKeys(descriptionText);
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//*[@id='description']/div")).getText(), descriptionText);
    }

    @Test
    public void testDiscardOldBuildsIsCheckedEmptyDaysAndBuildsField() {
        createPipelineWithoutDescription(NAME);
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(DISCARD_OLD_BUILDS_LABEL).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(DISCARD_OLD_BUILDS_CHECKBOX);

        Assert.assertTrue(getDriver().findElement(DISCARD_OLD_BUILDS_CHECKBOX).isSelected());
    }

    @Test
    public void testDiscardOldBuildsIsChecked7DaysAnd5Builds() {
        final String days = "7";
        final String builds = "5";

        createPipelineWithoutDescription(NAME);
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(DISCARD_OLD_BUILDS_LABEL).click();
        getDriver().findElement(DAYS_TO_KEEP_FIELD).sendKeys(days);
        getDriver().findElement(BUILDS_TO_KEEP_FIELD).sendKeys(builds);
        getDriver().findElement(SAVE_BUTTON).click();

        getDriver().findElement(CONFIGURE_MENU).click();

        Assert.assertTrue(getDriver().findElement(DISCARD_OLD_BUILDS_CHECKBOX).isSelected());
        Assert.assertEquals(getDriver().findElement(DAYS_TO_KEEP_FIELD).getAttribute("value"), days);
        Assert.assertEquals(getDriver().findElement(BUILDS_TO_KEEP_FIELD).getAttribute("value"), builds);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Days() {
        final String days = "0";

        createPipelineWithoutDescription(NAME);
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(DISCARD_OLD_BUILDS_LABEL).click();
        getDriver().findElement(DAYS_TO_KEEP_FIELD).sendKeys(days);
        getDriver().findElement(DAYS_TO_KEEP_LABEL).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(ACTUAL_ERROR_MESSAGE));

        Assert.assertTrue(getDriver().findElement(DISCARD_OLD_BUILDS_CHECKBOX).isSelected());
        Assert.assertEquals(getDriver().findElement(ACTUAL_ERROR_MESSAGE).getText(), ERROR_MESSAGE);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Builds() {
        final String builds = "0";

        createPipelineWithoutDescription(NAME);
        getDriver().findElement(CONFIGURE_MENU).click();

        getDriver().findElement(DISCARD_OLD_BUILDS_LABEL).click();
        getDriver().findElement(BUILDS_TO_KEEP_FIELD).sendKeys(builds);
        getDriver().findElement(DAYS_TO_KEEP_LABEL).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(ACTUAL_ERROR_MESSAGE));

        Assert.assertTrue(getDriver().findElement(DISCARD_OLD_BUILDS_CHECKBOX).isSelected());
        Assert.assertEquals(getDriver().findElement(ACTUAL_ERROR_MESSAGE).getText(), ERROR_MESSAGE);
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

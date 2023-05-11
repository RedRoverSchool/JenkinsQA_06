package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderTest5 extends BaseTest {
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By PIPELINE = By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']");
    private static final By BUTTON = By.cssSelector("#ok-button");
    private static final By DESCRIPTION = By.name("description");
    private static final By SUBMIT = By.name("Submit");
    private static final By TEXT_PIPELINE = By.cssSelector(".job-index-headline.page-headline");
    private static final By RENAME = By.xpath("//a[@href='/job/FirstPipeline/confirm-rename']");
    private static final By RENAME_LINE = By.xpath("//input[@name='newName']");
    private static final By CONFIGURE = By.xpath("//a[@href='/job/FirstPipeline/configure']");
    private static final By ENABLE_DISABLE_BUTTON = By.cssSelector("label[for='enable-disable-project']");
    private static final By DISABLE_WARNING_TEXT = By.xpath("//div[@class='warning']");
    private static final By DISCARD_OLD_BUILD = By.xpath("//label[normalize-space()='Discard old builds']");
    private static final By DAYS_TO_KEEP_BUILDS = By.name("_.daysToKeepStr");
    private static final By MAX_DAYS = By.xpath("//input[@name='_.numToKeepStr']");

    String name = "FirstPipeline";
    String descriptionText = "Pipeline text";
    String renameText = "Pipeline1";

    public void createPipeline() {

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NAME).sendKeys(name);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(BUTTON).click();
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();
    }

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

    @Test
    public void testSetDescriptionEnableDisablePipeline() {
        String descriptionText = "Good job!";
        String enableButton = "Enable";
        String text = "Disable Project";

        createPipeline();

        getDriver().findElement(CONFIGURE).click();
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(ENABLE_DISABLE_BUTTON).click();
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals(enableButton, getDriver().findElement(SUBMIT).getText());

        getDriver().findElement(SUBMIT).click();
        Assert.assertEquals(text, getDriver().findElement(SUBMIT).getText());
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        String daysMin = "2";
        String daysMax = "30";

        createPipeline();

        getDriver().findElement(CONFIGURE).click();
        getDriver().findElement(DISCARD_OLD_BUILD).click();
        getDriver().findElement(DAYS_TO_KEEP_BUILDS).sendKeys(daysMin);
        getDriver().findElement(MAX_DAYS).sendKeys(daysMax);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Pipeline " + name, getDriver().findElement(TEXT_PIPELINE).getText());
    }
}



//    private static final By MY_VIEWS_ICON = By.xpath("//a[@href='/me/my-views']");
//    private static final By PLUS_ICON = By.xpath("a[title='New View']");
//    private static final By NAME = By.name("name");
//    private static final By LIST_VIEW_ICON = By.xpath("label[for='hudson.model.ListView']");
//    private static final By CREATE = By.id("ok");
//    private static final By DESCRIPTION = By.name("description");
//    public static final By SUBMIT = By.name("submit");
//    public static final By RESULT = By.xpath("//div[@id='description'][1]");
//
//    String nameCreate = "First";
//    String descriptionText = "Good job!";
//
////    public void createPipeline() {
////
////        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
////        newItemButton.click();
////
////        WebElement itemNameInput = getDriver().findElement(By.id("name"));
////        itemNameInput.sendKeys(name);
////
////        WebElement itemTypeRadio = getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"));
////        itemTypeRadio.click();
////
////        WebElement okButton = getDriver().findElement(By.id("ok-button"));
////        okButton.click();
////
////        WebElement saveButton = getDriver().findElement(By.name("Submit"));
////        saveButton.click();
////    }
//
////    createPipeline();
//
//    @Test
//    public void testUserCreateViewNameWithListName() {
//        getDriver().findElement(MY_VIEWS_ICON).click();
//        getDriver().findElement(PLUS_ICON).click();
//        getDriver().findElement(NAME).sendKeys(nameCreate);
//        getDriver().findElement(LIST_VIEW_ICON).click();
//        getDriver().findElement(CREATE).click();
//        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
//        getDriver().findElement(SUBMIT).click();
//
//        Assert.assertEquals(descriptionText, getDriver().findElement(RESULT).getText());





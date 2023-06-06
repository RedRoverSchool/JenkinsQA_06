package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = "PIPELINE_NAME";
    private static final String RENAME = "Pipeline Project";
    private static final String TEXT_DESCRIPTION = "This is a test description";

    @Test
    public void testCreatePipeline() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectName()
                .getText();

        Assert.assertEquals(projectName, PIPELINE_NAME);
    }

    @Test
    public void testCreatePipelineWithDescription() {
        final String textDescription = "description text";

        String jobDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .addDescription(textDescription)
                .clickSaveButton()
                .getDescription()
                .getText();

        Assert.assertEquals(jobDescription, textDescription);
    }

    @Test
    public void testEditPipelineDescription() {
        final String description = "description text";
        final String newDescription = "Edited description text";

        String jobDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .addDescription(description)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickPipelineProject(PIPELINE_NAME)
                .clickEditDescription()
                .clearDescriptionField()
                .enterNewDescription(newDescription)
                .clickSaveButton()
                .getDescription()
                .getText();

        Assert.assertEquals(jobDescription, newDescription);
    }

    @Test
    public void testPipelineBuildNow() {
        String stageName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .getStage()
                .getText();

        Assert.assertEquals(stageName, "Hello");
    }

    @Test
    public void testPipelineConsoleOutputSuccess() {
        String text = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .clickBuildIcon()
                .getConsoleOutputField()
                .getText();

        Assert.assertTrue(text.contains("Finished: SUCCESS"), "Job does not finished success");
    }

    @Test
    public void testAddingDescriptionToPipeline() {
        final String pipelineName = "test_pipeline";
        final String descriptionText = "description text";
        String resultDescriptionText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(pipelineName)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickEditDescription()
                .enterNewDescription(descriptionText)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(resultDescriptionText, descriptionText);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testRenamePipeline() {
        final String newPipelineName = PIPELINE_NAME + "new";

        String projectName = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME)
                .clickRename()
                .clearNameField()
                .enterNewName(newPipelineName)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .getProjectName()
                .getText();

        Assert.assertEquals(projectName, newPipelineName);
    }

    @Test()
    public void testDeletePipelineLeftMenu() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickPipelineProject(PIPELINE_NAME)
                .clickDeletePipeline()
                .acceptAlert();

        Assert.assertFalse(getDriver().findElements(By.xpath("//tr[contains(@id,'job_')]")).size() > 0);
    }

    @Test
    public void testCreatingBasicPipelineProjectThroughJenkinsUI() {
        String resultOptionDefinitionFieldText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .scrollToPipelineSection()
                .getOptionTextInDefinitionField();

        Assert.assertEquals(resultOptionDefinitionFieldText, "Pipeline script");
    }

    @Test
    public void testDeletePipelineDropDownMenu() {
        final String name = PIPELINE_NAME + "1";

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(name)
                .acceptAlert();

        Assert.assertFalse(getDriver().findElements(By.xpath("//tr[contains(@id,'job_')]")).size() > 0);
    }

    @Test(dependsOnMethods = "testCreatingBasicPipelineProjectThroughJenkinsUI")
    public void testPipelineBuildingAfterChangesInCode() {
        BuildPage buildPage = new MainPage(getDriver())
                .getHeader()
                .clickLogo()
                .clickPipelineProject(PIPELINE_NAME)
                .clickConfigureButton()
                .clickPipelineLeftMenu()
                .clickScriptDropDownMenu()
                .selectHelloWord()
                .clickSaveButton()
                .clickBuildNow()
                .clickBuildIcon()
                .click1BuildHistory();

        Assert.assertTrue(buildPage.isDisplayedBuildTitle(), "Build #1 failed");
        Assert.assertTrue(buildPage.isDisplayedGreenIconV(), "Build #1 failed");
    }

    @Test
    public void testSetDescriptionPipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        String jobDescription = new PipelinePage(getDriver())
                .clickConfigureButton()
                .addDescription("Pipeline text")
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(jobDescription, "Pipeline text");
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        String jobName = new PipelinePage(getDriver())
                .clickConfigureButton()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds("2")
                .enterMaxOfBuildsToKeep("30")
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(jobName,"Pipeline " + PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testBuildPipeline() {
        final String namePipeline = "FirstPipeline";

        TestUtils.createPipeline(this, namePipeline, true);

        ConsoleOutputPage consoleOutputPage = new MainPage(getDriver())
                .clickPipelineProject(namePipeline)
                .clickBuildNow()
                .clickTrend()
                .clickBuildIcon();

        Assert.assertTrue(consoleOutputPage.isDisplayedGreenIconV(), "Build failed");
        Assert.assertTrue(consoleOutputPage.isDisplayedBuildTitle(), "Not found build");
    }

    @Test
    public void testChangesStatusOfLastBuild() {

        TestUtils.createPipeline(this, "Engineer", true);

        String text = new MainPage(getDriver())
                .clickPipelineProject("Engineer")
                .clickBuildNow()
                .clickChangeOnLeftSideMenu()
                .getTextOfPage();

        Assert.assertTrue(text.contains("No changes in any of the builds"),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");
    }

    @Test
    public void testMakeSeveralBuilds() {
        final String jobName = "Engineer";
        List<String> buildNumberExpected = Arrays.asList("#1", "#2", "#3");

        List buildNumber = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(jobName)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo().clickPipelineProject(jobName)
                .clickBuildNow()
                .clickBuildNow()
                .clickBuildNow()
                .clickTrend()
                .getBuildNumbers(3);

        Assert.assertEquals(buildNumber, buildNumberExpected);
    }

    @Test
    public void testCreateNewPipelineWithScript() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .selectScriptedPipelineAndSubmit()
                .getProjectName();

        Assert.assertEquals(projectName, "Pipeline " + PIPELINE_NAME);
    }

    @Test
    public void testDisablePipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);

        String jobStatus = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME)
                .clickDisableProject()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(PIPELINE_NAME);

        Assert.assertEquals(jobStatus, "Disabled");
    }

    @Test
    public void testEnablePipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);

        String jobStatus = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME)
                .clickDisableProject()
                .clickEnableProject()
                .getHeader()
                .clickLogo()
                .getJobBuildStatusIcon(PIPELINE_NAME);

        Assert.assertEquals(jobStatus, "Not built");
    }

    @Test
    public void testCreateDuplicatePipelineProject() {

        String jobExists = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineProject()
                .getItemInvalidMessage();

        Assert.assertEquals(jobExists, "» A job already exists with the name " + "‘" + PIPELINE_NAME + "’");
    }

    @Test
    public void testSortingPipelineProjectAplhabetically() {

        List<String> namesOfJobs = Arrays.asList("UProject", "SProject", "AProject");

        TestUtils.createPipeline(this, namesOfJobs.get(1), true);
        TestUtils.createPipeline(this, namesOfJobs.get(2), true);
        TestUtils.createPipeline(this, namesOfJobs.get(0), true);

        List<String> listNamesOfJobs = new MainPage(getDriver())
                .clickSortByName()
                .getListNamesOfJobs();

        Assert.assertEquals(listNamesOfJobs, namesOfJobs);
    }

    @Test
    public void testRenamePipelineDropDownMenu() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);

        String renamedPipeline = new MainPage(getDriver())
                .dropDownMenuClickRename(PIPELINE_NAME.replaceAll(" ", "%20"), new PipelinePage(getDriver()))
                .enterNewName(RENAME)
                .submitNewName()
                .getHeader()
                .clickLogo()
                .getProjectName()
                .getText();

        Assert.assertEquals(renamedPipeline, RENAME);
    }

    @Test
    public void testPipelineNameAllowedChar() {
        final String allowedChar = "_-+=”{},";

        String projectNameDashboard = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(allowedChar)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectNameMainPage(allowedChar);

        Assert.assertEquals(projectNameDashboard, allowedChar);
    }

    @DataProvider(name = "wrong-characters")
    public Object[][] providerWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-characters")
    public void testPipelineNameUnsafeChar(String wrongCharacters) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacters);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacters + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testDotBeforeNameProject() {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(".");

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» “.” is not an allowed name");
    }

    @Test
    public void testCreatePipelineWithSpaceInsteadOfName() {
          CreateItemErrorPage createItemErrorPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("  ")
                .selectPipelineProject()
                .clickOkButtonAndMoveToErrorPage();

        Assert.assertEquals(createItemErrorPage.getHeaderText(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "No name is specified");
    }

    @Test
    public void testSetDescription() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);

        String addDescription = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME)
                .clickEditDescription()
                .enterNewDescription(TEXT_DESCRIPTION)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(addDescription, TEXT_DESCRIPTION);
    }

    @Test
    public void testDiscardOldBuildsIsChecked() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        boolean discardOldBuildsCheckbox = new PipelinePage(getDriver())
                .clickConfigureButton()
                .selectDiscardOldBuildsandSave()
                .clickConfigureButton()
                .checkboxDiscardOldBuildsIsSelected();

        Assert.assertTrue(discardOldBuildsCheckbox);
    }

    @Test
    public void testDiscardOldBuildsParams() {
        final String days = "7";
        final String builds = "5";

        PipelineConfigPage pipelineConfigPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("test-pipeline")
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickConfigureButton()
                .clickDiscardOldBuildsCheckbox()
                .enterDaysToKeepBuilds(days)
                .enterMaxOfBuildsToKeep(builds)
                .clickSaveButton()
                .clickConfigureButton();

        Assert.assertEquals(pipelineConfigPage.getDaysToKeepBuilds(), days);
        Assert.assertEquals(pipelineConfigPage.getMaxNumbersOfBuildsToKeep(), builds);
    }

    @Ignore
    @Test
    public void testDiscardOldBuildsIsChecked0Days() {
        final String days = "0";
        final String errorMessage = "Not a positive integer";

        TestUtils.createPipeline(this, "test-pipeline", false);

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

        TestUtils.createPipeline(this, "test-pipeline", false);

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
    public void testDisableDuringCreation() {
        final String PIPELINE_NAME = "My_pipeline";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("name")))).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.cssSelector(".org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("toggle-switch-enable-disable-project")));

        WebElement enableToggles = getDriver().findElement(By.id("toggle-switch-enable-disable-project"));
        boolean isPipelineEnabled = Boolean.parseBoolean(getDriver().findElement(By.xpath("//input[@name='enable']")).getAttribute("value"));
        if (isPipelineEnabled) {
            enableToggles.click();
        }

        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.tagName("h1")), "Pipeline"));
        String disabledWarning = getDriver().findElement(By.id("enable-project")).getText();

        getDriver().findElement(By.xpath("//a[contains(@href,'configure')]")).click();

        getWait5().until(ExpectedConditions.textToBe(By.tagName("h2"), "General"));
        boolean isPipelineEnabledAfterDisable = Boolean.parseBoolean(getDriver().findElement(
                By.xpath("//input[@name='enable']")).getAttribute("value"));

        Assert.assertTrue(disabledWarning.contains("This project is currently disabled"));
        Assert.assertFalse(isPipelineEnabledAfterDisable, "Pipeline is enabled");
    }

    @Test
    public void testCreatePipelineWithTheSameName() {
        String actualErrorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineProject()
                .clickOkToCreateWithExistingName()
                .getErrorMessage();

        Assert.assertEquals(actualErrorMessage, "A job already exists with the name ‘" + PIPELINE_NAME + "’");
    }

    @Test
    public void testCreatePipelineGoingFromManageJenkinsPage() {
        List<String> jobList = new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getJobList();

        Assert.assertTrue(jobList.contains(PIPELINE_NAME));
    }


    @Test
    public void testSetPipelineDisplayName() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        PipelinePage pipelinePage = new PipelinePage(getDriver())
                .clickConfigureButton()
                .scrollAndClickAdvancedButton()
                .setDisplayName(RENAME)
                .clickSaveButton();

        Assert.assertEquals(pipelinePage.getProjectName(), "Pipeline " + RENAME);
        Assert.assertEquals(pipelinePage.getProjectNameSubtitle(), PIPELINE_NAME);
        Assert.assertEquals(pipelinePage.getHeader().clickLogo().getProjectName().getText(), RENAME);
    }

    @Test
    public void testAddDescriptionAfterRewrite() {
        String description = "description";
        String newDescription = "new description";

        String textPreview = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectPipelineAndOk()
                .addDescription(description)
                .clickPreview()
                .getPreviewText();
        Assert.assertEquals(textPreview, description);

        PipelinePage pipelinePage = new PipelineConfigPage(new PipelinePage(getDriver()))
                .clearDescriptionArea()
                .addDescription(newDescription)
                .clickSaveButton();
        String actualDescription = pipelinePage.getDescriptionText();
        Assert.assertTrue(actualDescription.contains(newDescription), "description not displayed");
    }

    @Test
    public void testAddBooleanParameterWithDescription() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        final String name = "Pipeline Boolean Parameter";
        final String description = "Some boolean parameters here";
        final String parameterName = "Boolean Parameter";

        BuildPage buildPage = new PipelinePage(getDriver())
                .clickConfigureButton()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .setDefaultBooleanParameter()
                .setBooleanParameterDescription(description)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton();

        Assert.assertEquals(buildPage.getBooleanParameterName(), name);
        Assert.assertEquals(buildPage.getBooleanParameterCheckbox(), "true");
        Assert.assertEquals(buildPage.getBooleanParameterDescription(), description);
    }

    @Test
    public void testAddBooleanParameter() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        final String name = "Pipeline Boolean Parameter";
        final String parameterName = "Boolean Parameter";

        BuildPage buildPage = new PipelinePage(getDriver())
                .clickConfigureButton()
                .clickAndAddParameter(parameterName)
                .setBooleanParameterName(name)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickBuildButton();

        Assert.assertEquals(buildPage.getBooleanParameterName(), name);
        Assert.assertNull(buildPage.getBooleanParameterCheckbox());
    }

    @Test
    public void testCancelPipelineDeletion(){
        final String jobName = "P1";
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(jobName)
                .selectPipelineAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickDelete(jobName)
                .dismissAlert();
        Assert.assertEquals(jobName,"P1");
    }
}

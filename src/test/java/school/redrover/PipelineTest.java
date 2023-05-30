package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = "PIPELINE_NAME";
    private static final String RENAME = "Pipeline Project";
    private static final String TEXT_DESCRIPTION = "This is a test description";

    private static final By buildNowButton = By.xpath("//div[@id = 'tasks']/div[3]//a");
    private static final By scriptButton = xpath("//div[@class = 'samples']/select");
    private static final By homePage = By.xpath("//h1[@class= 'job-index-headline page-headline']");

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

    public WebDriverWait webDriverWait10;

    public void scrollByElement(By by) throws InterruptedException {
        WebElement scroll = getDriver().findElement(by);
        new Actions(getDriver())
                .scrollToElement(scroll)
                .perform();
    }

    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    private void createWithoutDescription(String name) {
        getDriver().findElement(By.xpath("//a[@href = 'newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']//li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testCreatePipeline() {
        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
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
                .enterDescription(textDescription)
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
                .enterDescription(description)
                .clickSaveButton()
                .clickDashboard()
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

        WebElement projectName = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME)
                .clickRename()
                .clearNameField()
                .enterNewName(newPipelineName)
                .clickRenameButton()
                .clickDashboard().getProjectName();

        Assert.assertEquals(projectName.getText(), newPipelineName);
    }

    @Test()
    public void testDeletePipelineLeftMenu() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
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
                .getDefinitionFieldText();

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
                .clickDashboard()
                .clickJobDropDownMenu(name)
                .selectDeleteFromDropDownMenu()
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

        getDriver().findElement(By.xpath("//a[@href='/job/" + PIPELINE_NAME + "/configure']")).click();

        getDriver().findElement(By.name("description")).sendKeys("Pipeline text");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Pipeline " + PIPELINE_NAME,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, false);

        getDriver().findElement(By.xpath("//a[@href='/job/" + PIPELINE_NAME + "/configure']")).click();
        getDriver().findElement(By.xpath("//label[normalize-space()='Discard old builds']")).click();

        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys("2");
        getDriver().findElement(By.xpath("//input[@name='_.numToKeepStr']")).sendKeys("30");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Pipeline " + PIPELINE_NAME,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test(dependsOnMethods = {"testCreatePipeline"})
    public void testBuildPipeline() {
        final String namePipeline = "First Pipeline";

        TestUtils.createPipeline(this, namePipeline, true);

        new Actions(getDriver()).moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'First Pipeline')]"))).click().perform();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getDriver().findElement(By.cssSelector("#buildHistory>div>div>span>div>:nth-child(2)")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href$='console']"))).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("(//*[name()='svg'][@title='Success'])[1]")).isDisplayed(), "Build failed");
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent")).isDisplayed(), "Not found build");
    }

    @Test
    public void testChapterChangesOfPipelineSeeTheStatusOfLastBuild() {
        String changesBuild = "No changes in any of the builds";
        TestUtils.createPipeline(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'changes')]")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText().contains(changesBuild),
                "In the Pipeline Changes chapter, not displayed status of the latest build.");

    }

    @Test
    public void testCreateBuildNowVisibilityTheTimeStatusBuild() {
        TestUtils.createPipeline(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'buildTimeTrend')]")).click();

        WebElement successIcon = getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@tooltip=normalize-space('Success > Console Output')]")));
        WebElement timeAndDateLine = getDriver().findElement(By.xpath("//div[contains(@class, 'indent-multiline')]"));

        Assert.assertTrue(successIcon.isDisplayed(), "successIcon not displayed");
        Assert.assertTrue(timeAndDateLine.isDisplayed(), "timeAndDateLine not displayed");
    }

    @Ignore
    @Test
    public void testMakeSeveralBuilds() {
        TestUtils.createPipeline(this, "Engineer", true);
        List<String> buildNumberExpected = Arrays.asList("#1", "#2", "#3");
        List<String> buildNumber = new ArrayList<>();

        getDriver().findElement(By.xpath("//a[@href='job/Engineer/']")).click();
        WebElement newBuild = getDriver().findElement(By.xpath("//a[contains(@href, 'build?')]"));
        newBuild.click();
        newBuild.click();
        newBuild.click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'buildTimeTrend')]")).click();

        buildNumber.add(getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[href='1/']"))).getText());
        buildNumber.add(getDriver().findElement(By.cssSelector("[href='2/']")).getText());
        buildNumber.add(getDriver().findElement(By.cssSelector("[href='3/']")).getText());

        Assert.assertEquals(buildNumber, buildNumberExpected);
    }

    @Test
    public void testCreateNewPipelineWithScript() {

        getDriver().findElement(xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//li[@class = 'org_jenkinsci_plugins_workflow_job_WorkflowJob']"))).click();
        getDriver().findElement(xpath("//button[@id='ok-button']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(scriptButton));

        Select selectPipelineScript = new Select(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (scriptButton)));
        selectPipelineScript.selectByVisibleText("Scripted Pipeline");
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        Assert.assertEquals(getDriver().findElement(xpath("//h1[@class='job-index-headline page-headline']")).getText(),
                "Pipeline " + PIPELINE_NAME);
    }

    @Test
    public void testDisablePipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);
        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME.replaceAll(" ", "%20"))
                .clickDisableProject();

        Assert.assertTrue(pipelinePage.getEnableButton());
        Assert.assertEquals(pipelinePage.clickDashboard().getJobBuildStatusIcon(PIPELINE_NAME), "Disabled");
    }

    @Test
    public void testEnablePipeline() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);
        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickPipelineProject(PIPELINE_NAME.replaceAll(" ", "%20"))
                .clickDisableProject()
                .clickEnableProject();

        Assert.assertTrue(pipelinePage.getDisableButton());
        Assert.assertEquals(pipelinePage.clickDashboard()
                .getJobBuildStatusIcon(PIPELINE_NAME), "Not built");
    }

    @Test
    public void testCreateDuplicatePipelineProject() {

        String jobExists = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineProject()
                .getItemInvalidMessage();

        Assert.assertEquals(jobExists, "» A job already exists with the name " + "‘" + PIPELINE_NAME + "’");
    }

    @Test
    public void testSortingPipelineProjectAplhabetically() {

        TestUtils.createPipeline(this, "SProject", false);
        WebElement projectName1 = getDriver().findElement(homePage);
        String p1 = projectName1.getText().substring(9);
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        TestUtils.createPipeline(this, "AProject", false);
        WebElement projectName2 = getDriver().findElement(homePage);
        String p2 = projectName2.getText().substring(9);
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        TestUtils.createPipeline(this, "UProject", false);
        WebElement projectName3 = getDriver().findElement(homePage);
        String p3 = projectName3.getText().substring(9);

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add(p1);
        expectedNames.add(p2);
        expectedNames.add(p3);

        ArrayList<String> sortedExpectedProjectNames = new ArrayList<>();
        sortedExpectedProjectNames.addAll(expectedNames);
        Collections.sort(sortedExpectedProjectNames);
        System.out.println(sortedExpectedProjectNames);

        getDriver().findElement(By.id("jenkins-head-icon")).click();
        getDriver().findElement(By.xpath("//th[@initialsortdir='down']")).click();

        WebElement findProjectName1 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[1]"));
        String actualProjectName1 = findProjectName1.getText().substring(0, 8);
        WebElement findProjectName2 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[2]"));
        String actualProjectName2 = findProjectName2.getText().substring(0, 8);
        WebElement findProjectName3 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[3]"));
        String actualProjectName3 = findProjectName3.getText().substring(0, 8);

        List<String> actualProjectNames = new ArrayList<>();
        actualProjectNames.add(actualProjectName1);
        actualProjectNames.add(actualProjectName2);
        actualProjectNames.add(actualProjectName3);
        System.out.println(actualProjectNames);

        Assert.assertEquals(actualProjectNames, sortedExpectedProjectNames);
    }

    @Test
    public void testRenamePipelineDropDownMenu() {
        TestUtils.createPipeline(this, PIPELINE_NAME, true);

        FolderPage folderPage = new MainPage(getDriver())
                .clickJobDropDownMenu(PIPELINE_NAME.replaceAll(" ", "%20"))
                .clickRenameInDropDownMenu()
                .setNewName(RENAME)
                .clickRenameButton();

        Assert.assertEquals(folderPage.getFolderDisplayName(), "Pipeline " + RENAME);
        Assert.assertEquals(folderPage.clickDashboard()
                .getJobWebElement(RENAME).getText(), RENAME);
    }

    @Test
    public void testPipelineNameAllowedChar() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']")).click();
        getDriver().findElement(By.id("name")).sendKeys("_-+=”{},");
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        WebElement projectNameDashboard = getDriver().findElement(By.xpath("//td/a/span"));

        Assert.assertEquals(projectNameDashboard.getText(), "_-+=”{},");
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
    public void testCreatePipelineDashboardSliderNewItem() {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickOnSliderDashboardInDropDownMenu()
                .clickNewItemInDashboardDropDownMenu();

        PipelinePage PipelinePage = new NewJobPage(getDriver())
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton();

        MainPage mainPage = new PipelinePage(getDriver())
                .clickDashboard();

        Assert.assertEquals(mainPage.getProjectNameMainPage(PIPELINE_NAME), PIPELINE_NAME);
    }

    @Test
    public void testCreatePipelineWithSpaceInsteadOfName() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys("  ");

        WebElement typeProject = getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        typeProject.click();
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))).
                getText(), "Error");
        Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/p"))).
                getText(), "No name is specified");
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

    @Ignore
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
                .clickDashboard()
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
                .clickManageJenkins()
                .clickNewItem()
                .enterItemName(PIPELINE_NAME)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
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
        Assert.assertEquals(pipelinePage.clickDashboard().getProjectName().getText(), RENAME);
    }

    @Test
    public void testAddDescriptionAfterRewrite() {
        String description = "description";
        String newDescription = "new description";

        String textPreview = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectPipelineAndOk()
                .sendAreDescriptionInputString(description)
                .clickPreview()
                .getPreviewText();
        Assert.assertEquals(textPreview, description);

        new ConfigurePage(getDriver())
                .clearDescriptionArea()
                .sendAreDescriptionInputString(newDescription)
                .selectSaveButton();
        String actualDescription = new ProjectPage(getDriver()).getProjectDescription();
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
                .clickDashboard()
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
                .clickDashboard()
                .clickBuildButton();

        Assert.assertEquals(buildPage.getBooleanParameterName(), name);
        Assert.assertNull(buildPage.getBooleanParameterCheckbox());
    }
}

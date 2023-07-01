package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.FolderConfigPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseJobPage;
import school.redrover.model.jobsconfig.PipelineConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.*;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";
    private static final String RENAME = "Folder";
    private static final String DESCRIPTION = "Created new folder";
    private static final String DESCRIPTION_2 = "Created new Description";
    private static final String DISPLAY_NAME = "NewFolder";

    private void createdJobInFolder(String jobName, String folderName, TestUtils.JobType jobType, BaseConfigPage<?, ?> jobConfigPage) {
        new MainPage(getDriver())
                .clickJobName(folderName, new FolderPage(getDriver()))
                .clickNewItem()
                .enterItemName(jobName)
                .selectJobType(jobType)
                .clickOkButton(jobConfigPage)
                .getHeader()
                .clickLogo();
    }

    @Ignore
    private void moveJobToFolderFromDropDownMenu(String jobName, String folderName, BaseJobPage<?> jobPage) {
        new MainPage(getDriver())
                .dropDownMenuClickMove(jobName, jobPage)
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo();
    }

    private void moveJobToFolderFromSideMenu(String jobName, String folderName, BaseJobPage<?> jobPage) {
        new MainPage(getDriver())
                .clickJobName(jobName, jobPage)
                .clickMoveOnSideMenu()
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo();
    }

    @Test
    public void testCreateFromCreateAJob() {
        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.jobIsDisplayed(NAME), "error was not show name folder");
        Assert.assertTrue(mainPage.iconFolderIsDisplayed(), "error was not shown icon folder");
    }

    @Test
    public void testCreateFromNewItem() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        Assert.assertTrue(new MainPage(getDriver()).jobIsDisplayed(NAME), "error was not show name folder");
        Assert.assertTrue(new MainPage(getDriver()).iconFolderIsDisplayed(), "error was not shown icon folder");
    }

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testCreateWithExistingName() {
        CreateItemErrorPage errorPage = TestUtils.createJobWithExistingName(this, NAME, TestUtils.JobType.Folder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "A job already exists with the name ‘" + NAME + "’");
    }

    @DataProvider(name = "invalid-data")
    public Object[][] provideInvalidData() {
        return new Object[][]{{"!"}, {"#"}, {"$"}, {"%"}, {"&"}, {"*"}, {"/"}, {":"},
                {";"}, {"<"}, {">"}, {"?"}, {"@"}, {"["}, {"]"}, {"|"}, {"\\"}, {"^"}};
    }

    @Test(dataProvider = "invalid-data")
    public void testCreateFolderUsingInvalidData(String invalidData) {
        final String expectedErrorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        NewJobPage newJobPage = TestUtils.createFolderUsingInvalidData(this, invalidData, TestUtils.JobType.Folder);

        Assert.assertTrue(newJobPage.isOkButtonDisabled(), "error OK button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), expectedErrorMessage);
    }

    @Test
    public void testCreateFolderWithSpaceInsteadName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.Folder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testCreateWithExistingName")
    public void testRenameUsingDropDownMenu() {
        boolean newNameIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new FolderPage(getDriver()))
                .enterNewName(RENAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(RENAME);

        Assert.assertTrue(newNameIsDisplayed, "error was not show new name folder");
    }

    @Test(dependsOnMethods = "testRenameUsingDropDownMenu")
    public void testRenameToTheCurrentNameAndGetError() {
        CreateItemErrorPage createItemErrorPage = new MainPage(getDriver())
                .clickJobName(RENAME, new FolderPage(getDriver()))
                .clickRename()
                .enterNewName(RENAME)
                .clickRenameButtonAndGoError();

        Assert.assertEquals(createItemErrorPage.getError(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameToTheCurrentNameAndGetError")
    public void testRenameFromLeftSidePanel() {
        FolderPage folderPage =  new MainPage(getDriver())
                .clickJobName(RENAME, new FolderPage(getDriver()))
                .clickRename()
                .enterNewName(NAME)
                .clickRenameButton();

        Assert.assertEquals(folderPage.getJobName(), NAME);
        Assert.assertEquals(folderPage.getPageTitle(), "All [" + NAME + "] [Jenkins]");
    }

    @Test(dependsOnMethods = "testRenameFromLeftSidePanel")
    public void testConfigureFolderNameDescriptionHealthMetrics() {
        FolderPage folderPage = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickConfigure()
                .enterDisplayName(DISPLAY_NAME)
                .addDescription(DESCRIPTION)
                .addHealthMetrics()
                .clickSaveButton();

        Assert.assertEquals(folderPage.getJobName(), DISPLAY_NAME);
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION);
        Assert.assertTrue(folderPage.clickConfigure().clickHealthMetrics().isRecursive());
    }

    @Test(dependsOnMethods = "testConfigureFolderNameDescriptionHealthMetrics")
    public void testDeleteDisplayName() {
        String folderName = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickConfigure()
                .clearDisplayName()
                .clickSaveButton()
                .getJobName();

        Assert.assertEquals(folderName, NAME);
    }

    @Test(dependsOnMethods = "testDeleteDisplayName")
    public void testHealthMetricWithRecursive() {
        String pipelineName = "BadPipe";

        new MainPage(getDriver()).
                clickJobName(NAME, new FolderPage(getDriver()));

        TestUtils.createJob(this, RENAME, TestUtils.JobType.Folder, false);

        new FolderPage(getDriver())
                .clickConfigure()
                .addHealthMetrics()
                .clickSaveButton();

        String tooltipDescription = new FolderPage(getDriver())
                .clickNewItem()
                .selectJobType(TestUtils.JobType.Pipeline)
                .enterItemName(pipelineName)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .inputInScriptField("Broken")
                .clickSaveButton()
                .clickBuildNow()
                .getHeader()
                .clickLogo()
                .hoverOverWeather(NAME)
                .getTooltipDescription();

        Assert.assertEquals(tooltipDescription,
                "Worst health: " + NAME + " » " + RENAME + " » " + pipelineName + ": Build stability: All recent builds failed.");
    }

    @Test(dependsOnMethods = "testHealthMetricWithRecursive")
    public void testDeleteHealthMetrics(){
        boolean healthMetric = new MainPage(getDriver())
                .clickJobName(NAME,new FolderPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .removeHealthMetrics()
                .clickSaveButton()
                .clickConfigure()
                .clickHealthMetrics()
                .healthMetricIsVisible();

        Assert.assertTrue(healthMetric,"the deleted metric is no longer visible");
    }

    @Test(dependsOnMethods = "testDeleteHealthMetrics")
    public void testAddDescriptionFromFolderPage() {
        String folderDescription = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickEditDescription()
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(folderDescription, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddDescriptionFromFolderPage")
    public void testAddDescriptionPreview() {
        String previewText = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickEditDescription()
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddDescriptionPreview")
    public void testPreviewDescription() {
        String previewText = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickConfigure()
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewDescription")
    public void testEditDescription() {
        String newDescription = new FolderPage(getDriver())
                .clickEditDescription()
                .clearDescriptionField()
                .enterDescription(DESCRIPTION_2)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(newDescription, DESCRIPTION_2);
    }


    @Test(dependsOnMethods = "testEditDescription")
    public void testCancelDeleting() {
        boolean folderIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickDeleteJobThatIsMainPage()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NAME);

        Assert.assertTrue(folderIsDisplayed, "error was not show name folder");
    }

    @Test(dependsOnMethods = "testCancelDeleting")
    public void testCreateJobsInFolder() {
        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        createdJobInFolder(jobName.get(0), NAME, TestUtils.JobType.FreestyleProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(1), NAME, TestUtils.JobType.Pipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(2), NAME, TestUtils.JobType.MultiConfigurationProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(3), NAME, TestUtils.JobType.Folder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(4), NAME, TestUtils.JobType.MultibranchPipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(5), NAME, TestUtils.JobType.OrganizationFolder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .getJobList();

        jobName.sort(String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(createdJobList, jobName);
    }

    @Test(dependsOnMethods = "testCreateJobsInFolder")
    public void testDeleteFolder() {
        boolean welcomeIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(NAME)
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeIsDisplayed, "error was not show Welcome to Jenkins!");
    }

    @Test(dependsOnMethods = "testCreateFromNewItem")
    public void testMoveJobsToFolderFromDropDownMenu() {
        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        TestUtils.createJob(this, jobName.get(0), TestUtils.JobType.FreestyleProject, true);
        TestUtils.createJob(this, jobName.get(1), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, jobName.get(2), TestUtils.JobType.MultiConfigurationProject, true);
        TestUtils.createJob(this, jobName.get(3), TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, jobName.get(4), TestUtils.JobType.MultibranchPipeline, true);
        TestUtils.createJob(this, jobName.get(5), TestUtils.JobType.OrganizationFolder, true);

        moveJobToFolderFromDropDownMenu(jobName.get(0), NAME, new FreestyleProjectPage(getDriver()));
        moveJobToFolderFromDropDownMenu(jobName.get(1), NAME, new PipelinePage(getDriver()));
        moveJobToFolderFromDropDownMenu(jobName.get(2), NAME, new MultiConfigurationProjectPage(getDriver()));
        moveJobToFolderFromDropDownMenu(jobName.get(3), NAME, new FolderPage(getDriver()));
        moveJobToFolderFromDropDownMenu(jobName.get(4), NAME, new MultibranchPipelinePage(getDriver()));
        moveJobToFolderFromDropDownMenu(jobName.get(5), NAME, new OrganizationFolderPage(getDriver()));

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .getJobList();

        jobName.sort(String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(createdJobList, jobName);
    }

    @Test
    public void testMoveJobsToFolderFromSideMenu() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        for(Map.Entry<String, BaseJobPage<?>> entry : TestUtils.getJobMap(this).entrySet()) {
            TestUtils.createJob(this, entry.getKey(), TestUtils.JobType.valueOf(entry.getKey()), true);
            moveJobToFolderFromSideMenu(entry.getKey(), NAME, entry.getValue());
        }

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .getJobList();

        Assert.assertEquals(createdJobList, TestUtils.getJobList(this));
    }

    @Test
    public void testCreateFolderGoingFromBuildHistoryPage() {
        List<String> folderName = new MainPage(getDriver())
                .clickBuildsHistoryButton()
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .clickSaveButton()
                .getBreadcrumb()
                .clickDashboardButton()
                .getJobList();

        Assert.assertTrue(folderName.contains(NAME));
    }

    @Test(dataProvider = "invalid-data")
    public void testRenameFolderWithInvalidData(String invalidData) {

        final String expectedErrorMessage = "‘" + invalidData + "’ is an unsafe character";

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        String actualErrorMessage = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickRename()
                .enterNewName(invalidData)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        switch (invalidData) {
            case "&" -> Assert.assertEquals(actualErrorMessage, "‘&amp;’ is an unsafe character");
            case "<" -> Assert.assertEquals(actualErrorMessage, "‘&lt;’ is an unsafe character");
            case ">" -> Assert.assertEquals(actualErrorMessage, "‘&gt;’ is an unsafe character");
            default -> Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        }
    }

    @Test
    public void testDeleteFolderFromSideMenu() {

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        boolean welcomeIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickDeleteJobThatIsMainPage()
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeIsDisplayed, "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testCreateFolderWithLongName() {
        String longName = RandomStringUtils.randomAlphanumeric(256);
        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(longName)
                .selectJobAndOkAndGoToBugPage(TestUtils.JobType.Folder)
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "A problem occurred while processing the request.");
    }
}
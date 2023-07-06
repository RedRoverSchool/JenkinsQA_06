package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

    private void moveJobToFolderFromSideMenu(String jobName, String folderName, BaseJobPage<?> jobPage) {
        new MainPage(getDriver())
                .clickJobName(jobName, jobPage)
                .clickMoveOnSideMenu()
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo();
    }

    public List<String> createdJobList(String name) {
        return new MainPage(getDriver())
                .clickJobName(name, new FolderPage(getDriver()))
                .getJobList();
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
        Assert.assertTrue(mainPage.isIconFolderDisplayed(), "error was not shown icon folder");
    }

    @Test
    public void testCreateFromNewItem() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        Assert.assertTrue(new MainPage(getDriver()).jobIsDisplayed(NAME), "error was not show name folder");
        Assert.assertTrue(new MainPage(getDriver()).isIconFolderDisplayed(), "error was not shown icon folder");
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
    public void testCreateUsingInvalidData(String invalidData) {
        final String expectedErrorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        NewJobPage newJobPage = TestUtils.createFolderUsingInvalidData(this, invalidData, TestUtils.JobType.Folder);

        Assert.assertTrue(newJobPage.isOkButtonDisabled(), "error OK button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), expectedErrorMessage);
    }

    @Test
    public void testCreateWithSpaceInsteadName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.Folder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testCreateWithExistingName")
    public void testRenameFromDropDownMenu() {
        boolean newNameIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new FolderPage(getDriver()))
                .enterNewName(RENAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(RENAME);

        Assert.assertTrue(newNameIsDisplayed, "error was not show new name folder");
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
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
    public void testRenameFromSideMenu() {
        FolderPage folderPage = new MainPage(getDriver())
                .clickJobName(RENAME, new FolderPage(getDriver()))
                .clickRename()
                .enterNewName(NAME)
                .clickRenameButton();

        Assert.assertEquals(folderPage.getJobName(), NAME);
        Assert.assertEquals(folderPage.getPageTitle(), "All [" + NAME + "] [Jenkins]");
    }

    @Test(dependsOnMethods = "testRenameFromSideMenu")
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

        String tooltipDescription = new FolderPage(getDriver())
                .clickConfigure()
                .addHealthMetrics()
                .clickSaveButton()
                .clickNewItem()
                .selectJobType(TestUtils.JobType.Pipeline)
                .enterItemName(pipelineName)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .inputInScriptField("Broken")
                .clickSaveButton()
                .clickBuildNowFromSideMenu()
                .getHeader()
                .clickLogo()
                .hoverOverWeather(NAME)
                .getTooltipDescription();

        Assert.assertEquals(tooltipDescription,
                "Worst health: " + NAME + " » " + RENAME + " » " + pipelineName + ": Build stability: All recent builds failed.");
    }

    @Test(dependsOnMethods = "testHealthMetricWithRecursive")
    public void testDeleteHealthMetrics() {
        boolean healthMetric = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .removeHealthMetrics()
                .clickSaveButton()
                .clickConfigure()
                .clickHealthMetrics()
                .healthMetricIsVisible();

        Assert.assertTrue(healthMetric, "the deleted metric is no longer visible");
    }

    @Test(dependsOnMethods = "testDeleteHealthMetrics")
    public void testAddDescriptionFromProjectPage() {
        FolderPage folderPage = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION)
                .clickSaveButton();

        Assert.assertEquals(folderPage.getDescription(), DESCRIPTION);
        Assert.assertEquals(folderPage.getDescriptionButton(), "Edit description");
    }

    @Test(dependsOnMethods = "testAddDescriptionFromProjectPage")
    public void testPreviewDescriptionFromProjectPage() {
        String previewText = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickEditDescription()
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewDescriptionFromProjectPage")
    public void testPreviewDescriptionFromConfigurationPage() {
        String previewText = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickConfigure()
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewDescriptionFromConfigurationPage")
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
    public void testDeleteDescriptionUsingConfigPage() {
        String actualDescription = new MainPage(getDriver())
                .clickConfigureDropDown(NAME, new FolderConfigPage(new FolderPage(getDriver())))
                .clearDescriptionArea()
                .clickSaveButton()
                .getFolderDescription();

        Assert.assertTrue(actualDescription.isEmpty());
    }

    @Test(dependsOnMethods = "testDeleteDescriptionUsingConfigPage")
    public void testCancelDeletingFromSideMenu() {
        boolean folderIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickDeleteJobThatIsMainPage()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NAME);

        Assert.assertTrue(folderIsDisplayed, "error was not show name folder");
    }

    @Test
    public void testCreateJobsInFolder() {
        Map<String, BaseJobPage<?>> jobMap = TestUtils.getJobMap(this);

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        for (Map.Entry<String, BaseJobPage<?>> entry : TestUtils.getJobMap(this).entrySet()) {
            createdJobInFolder(entry.getKey(), NAME, TestUtils.JobType.valueOf(entry.getKey()),
                    new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        }

        List<String> createdJobList = createdJobList(NAME);
        List<String> jobNameList = new ArrayList<>(jobMap.keySet());

        Assert.assertEquals(jobNameList.size(), createdJobList.size());
        Assert.assertTrue(createdJobList.containsAll(jobNameList));
    }

    @Test(dependsOnMethods = "testCreateJobsInFolder")
    public void testDeleteItemFromDropDown() {

        MainPage welcomeIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(NAME)
                .clickYesButton();

        Assert.assertTrue(welcomeIsDisplayed.WelcomeIsDisplayed());
        Assert.assertEquals(welcomeIsDisplayed.clickMyViewsSideMenuLink().getStatusMessageText(), "This folder is empty");
    }

    @DataProvider(name = "jobType")
    public Object[][] JobTypes() {
        return new Object[][]{
                {TestUtils.JobType.FreestyleProject},
                {TestUtils.JobType.Pipeline},
                {TestUtils.JobType.MultiConfigurationProject},
                {TestUtils.JobType.Folder},
                {TestUtils.JobType.MultibranchPipeline},
                {TestUtils.JobType.OrganizationFolder}};
    }
    @Test(dataProvider = "jobType")
    public void testMoveJobToFolderFromDropDownMenu(TestUtils.JobType jobType) {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, jobType.name(), jobType, true);

        FolderPage folder = new MainPage(getDriver())
                .dropDownMenuClickMove(jobType.name(), new FolderPage(getDriver()))
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(NAME, new FolderPage(getDriver()));

        Assert.assertTrue(folder.jobIsDisplayed(jobType.name()), "Job is not present in Folder");
    }

    @Test
    public void testMoveJobsToFolderFromSideMenu() {
        Map<String, BaseJobPage<?>> jobMap = TestUtils.getJobMap(this);

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        for (Map.Entry<String, BaseJobPage<?>> entry : jobMap.entrySet()) {
            TestUtils.createJob(this, entry.getKey(), TestUtils.JobType.valueOf(entry.getKey()), true);
            moveJobToFolderFromSideMenu(entry.getKey(), NAME, entry.getValue());
        }

        List<String> createdJobList = createdJobList(NAME);
        List<String> jobNameList = new ArrayList<>(jobMap.keySet());

        Assert.assertEquals(jobNameList.size(), createdJobList.size());
        Assert.assertTrue(createdJobList.containsAll(jobNameList));
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
    public void testRenameWithInvalidData(String invalidData) {
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
    public void testDeleteItemFromSideMenu() {
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        boolean welcomeIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickDeleteJobThatIsMainPage()
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeIsDisplayed, "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testCreateWithLongName() {
        String longName = RandomStringUtils.randomAlphanumeric(256);

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(longName)
                .selectJobAndOkAndGoToBugPage(TestUtils.JobType.Folder)
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "A problem occurred while processing the request.");
    }

    @Test
    public void testCreateWithDotInsteadOfName() {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(".")
                .selectJobType(TestUtils.JobType.Folder);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» “.” is not an allowed name");
        Assert.assertTrue(newJobPage.isOkButtonDisabled(), "error OK button is enabled");
    }

    @Test
    public void testCreateFromPeoplePage() {
       MainPage  folder = new MainPage(getDriver())
                 .clickPeopleOnLeftSideMenu()
                 .clickNewItem()
                 .enterItemName(NAME)
                 .selectJobType(TestUtils.JobType.Folder)
                 .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                 .getHeader()
                 .clickLogo();

        Assert.assertTrue(folder.jobIsDisplayed(NAME), "Error: the folder name is not displayed");
        Assert.assertTrue(folder.isIconFolderDisplayed(), "Error: the folder icon is not displayed");
    }

}

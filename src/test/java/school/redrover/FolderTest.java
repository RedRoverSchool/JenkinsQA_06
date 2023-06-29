package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.FolderConfigPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseJobPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";
    private static final String NAME_2 = "Folder";
    private static final String DESCRIPTION = "Created new folder";
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

    @Test
    public void testCreateFromDashboard() {
        MainPage mainPage = new MainPage(getDriver())
                .getBreadcrumb()
                .clickNewItemDashboardDropdownMenu()
                .enterItemName(NAME_2)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.jobIsDisplayed(NAME_2), "error was not show name folder");
        Assert.assertTrue(mainPage.iconFolderIsDisplayed(), "error was not shown icon folder");
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

    @Test(dependsOnMethods = "testCreateWithExistingName")
    public void testRenameUsingDropDownMenu() {
        boolean newNameIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new FolderPage(getDriver()))
                .enterNewName(NAME_2)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NAME_2);

        Assert.assertTrue(newNameIsDisplayed, "error was not show new name folder");
    }

    @Test(dependsOnMethods = "testRenameUsingDropDownMenu")
    public void testRenameToTheCurrentNameAndGetError() {
        CreateItemErrorPage createItemErrorPage = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickRename()
                .enterNewName(NAME_2)
                .clickRenameButtonAndGoError();

        Assert.assertEquals(createItemErrorPage.getError(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameToTheCurrentNameAndGetError")
    public void testConfigureFolderNameDescriptionHealthMetrics() {
        FolderPage folderPage = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickConfigure()
                .enterDisplayName(DISPLAY_NAME)
                .addDescription(DESCRIPTION)
                .addHealthMetrics()
                .clickSaveButton();

        Assert.assertEquals(folderPage.getJobName(), DISPLAY_NAME);
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION);
        Assert.assertTrue(folderPage.clickConfigure().clickHealthMetrics().isRecursive());
    }

    @Test(dependsOnMethods = "testAddDescriptionFromFolderPage")
    public void testPreviewDescription() {
        String previewText = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickConfigure()
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewDescription")
    public void testCancelDeleting() {
        boolean folderIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickDeleteJobThatIsMainPage()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NAME_2);

        Assert.assertTrue(folderIsDisplayed, "error was not show name folder");
    }

    @Test(dependsOnMethods = "testCancelDeleting")
    public void testCreateJobsInFolder() {
        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        createdJobInFolder(jobName.get(0), NAME_2, TestUtils.JobType.FreestyleProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(1), NAME_2, TestUtils.JobType.Pipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(2), NAME_2, TestUtils.JobType.MultiConfigurationProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(3), NAME_2, TestUtils.JobType.Folder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(4), NAME_2, TestUtils.JobType.MultibranchPipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(5), NAME_2, TestUtils.JobType.OrganizationFolder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .getJobList();

        jobName.sort(String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(createdJobList, jobName);
    }

    @Test(dependsOnMethods = "testCreateJobsInFolder")
    public void testDeleteFolder() {
        boolean welcomeIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(NAME_2)
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeIsDisplayed, "error was not show Welcome to Jenkins!");
    }

    @Test(dependsOnMethods = {"testCreateFromDashboard", "testCreateFromNewItem"})
    public void testMoveJobsToFolderFromDropDownMenu() {
        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        TestUtils.createJob(this, jobName.get(0), TestUtils.JobType.FreestyleProject, true);
        TestUtils.createJob(this, jobName.get(1), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, jobName.get(2), TestUtils.JobType.MultiConfigurationProject, true);
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
        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        TestUtils.createJob(this, jobName.get(0), TestUtils.JobType.FreestyleProject, true);
        TestUtils.createJob(this, jobName.get(1), TestUtils.JobType.Pipeline, true);
        TestUtils.createJob(this, jobName.get(2), TestUtils.JobType.MultiConfigurationProject, true);
        TestUtils.createJob(this, jobName.get(3), TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, jobName.get(4), TestUtils.JobType.MultibranchPipeline, true);
        TestUtils.createJob(this, jobName.get(5), TestUtils.JobType.OrganizationFolder, true);

        moveJobToFolderFromSideMenu(jobName.get(0), NAME, new FreestyleProjectPage(getDriver()));
        moveJobToFolderFromSideMenu(jobName.get(1), NAME, new PipelinePage(getDriver()));
        moveJobToFolderFromSideMenu(jobName.get(2), NAME, new MultiConfigurationProjectPage(getDriver()));
        moveJobToFolderFromSideMenu(jobName.get(3), NAME, new FolderPage(getDriver()));
        moveJobToFolderFromSideMenu(jobName.get(4), NAME, new MultibranchPipelinePage(getDriver()));
        moveJobToFolderFromSideMenu(jobName.get(5), NAME, new OrganizationFolderPage(getDriver()));

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .getJobList();

        jobName.sort(String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(createdJobList, jobName);
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

    @Test(dependsOnMethods = "testConfigureFolderNameDescriptionHealthMetrics")
    public void testDeleteDisplayName() {
        String folderName = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickConfigure()
                .clearDisplayName()
                .clickSaveButton()
                .getJobName();

        Assert.assertEquals(folderName, NAME_2);
    }

    @Test(dependsOnMethods = "testDeleteDisplayName")
    public void testAddDescriptionFromFolderPage() {
        String folderDescription = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .clickAddDescription()
                .addDescription(DESCRIPTION)
                .clickSaveButton()
                .getFolderDescriptionFromFolderPage();

        Assert.assertEquals(folderDescription, DESCRIPTION);
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
}

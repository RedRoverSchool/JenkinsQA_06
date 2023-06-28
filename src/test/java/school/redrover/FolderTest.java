package school.redrover;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.FolderConfigPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseJobPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";
    private static final String NAME_2 = "Folder";
    private static final String DESCRIPTION = "Created new folder";
    private static final String DISPLAY_NAME = "NewFolder";

    private void createdJobInFolder(String folderName, Pair<String, TestUtils.JobType> pair) {
        new MainPage(getDriver())
                .clickJobName(folderName, new FolderPage(getDriver()))
                .clickNewItem()
                .enterItemName(pair.getValue0())
                .selectJobType(pair.getValue1())
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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

        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobAndOkAndGoError(TestUtils.JobType.Folder)
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "A job already exists with the name ‘" + NAME + "’");
    }

    @DataProvider(name = "invalid-data")
    public Object[][] provideInvalidData() {
        return new Object[][]{{"!"}, {"#"}, {"$"}, {"%"}, {"&"}, {"*"}, {"/"}, {":"},
                {";"}, {"<"}, {">"}, {"?"}, {"@"}, {"["}, {"]"}, {"|"}, {"\\"}, {"^"}};
    }

    @Test(dataProvider = "invalid-data")
    public void testCreateFolderUsingInvalidData(String invalidData) {
        final String expectedErrorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        String actualErrorMessage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(invalidData)
                .getItemInvalidMessage();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(dependsOnMethods = "testCreateWithExistingName")
    public void testCreateNewViewInFolder() {
        final String viewName = "Test View";

        boolean viewIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickNewView()
                .setNewViewName(viewName)
                .selectTypeViewClickCreate(TestUtils.ViewType.MyView, ViewPage.class)
                .clickAllOnFolderView()
                .viewIsDisplayed(viewName);

        Assert.assertTrue(viewIsDisplayed, "error was not shown created view");
    }

    @Test(dependsOnMethods = "testCreateNewViewInFolder")
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

    @Test(dependsOnMethods = "testConfigureFolderNameDescriptionHealthMetrics")
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

        List<Pair<String, TestUtils.JobType>> jobs = new ArrayList<>(List.of(
                Pair.with("Freestyle_Project", TestUtils.JobType.FreestyleProject),
                Pair.with("Pipeline project", TestUtils.JobType.Pipeline),
                Pair.with("Multi Configuration Project", TestUtils.JobType.MultiConfigurationProject),
                Pair.with("Folder", TestUtils.JobType.Folder),
                Pair.with("Multibranch Pipeline", TestUtils.JobType.MultibranchPipeline),
                Pair.with("Organization", TestUtils.JobType.OrganizationFolder)
        ));

        jobs.forEach(el -> createdJobInFolder(NAME_2, el));

        List<String> createdJobList = new MainPage(getDriver())
                .clickJobName(NAME_2, new FolderPage(getDriver()))
                .getJobList();

        List<String> result = jobs.stream().map(el -> el.getValue0()).toList().stream().sorted().toList();

        Assert.assertEquals(createdJobList, result);
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
}

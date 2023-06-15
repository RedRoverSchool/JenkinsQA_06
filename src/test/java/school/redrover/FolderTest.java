package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class FolderTest extends BaseTest {

    private static final String NAME = "FolderName";
    private static final String NAME2 = "FolderName2";
    private static final String NEW_NAME = "newTestName";
    private static final String DESCRIPTION = "Created new folder";
    private static final String DISPLAY_NAME = "NewFolder";

    private void createdJobInFolder(String jobName, String folderName, TestUtils.JobType jobType, BaseConfigPage<?,?> jobConfigPage){
        new MainPage(getDriver())
                .clickJobName(folderName, new FolderPage(getDriver()))
                .newItem()
                .enterItemName(jobName)
                .selectJobType(jobType)
                .clickOkButton(jobConfigPage)
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
                .getHeader()
                .clickNewItemDashboardDropdownMenu()
                .enterItemName(NAME2)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.jobIsDisplayed(NAME2), "error was not show name folder");
        Assert.assertTrue(mainPage.iconFolderIsDisplayed(), "error was not shown icon folder");
    }

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testErrorWhenCreateWithExistingName() {

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

    @Test(dependsOnMethods = "testErrorWhenCreateWithExistingName")
    public void testCreateNewViewInFolder() {
        final String viewName = "Test View";

        boolean viewIsDisplayed = new MainPage(getDriver())
                .clickJobName(NAME, new FolderPage(getDriver()))
                .clickNewView()
                .enterViewName(viewName)
                .selectMyViewAndClickCreate()
                .clickAll()
                .viewIsDisplayed(viewName);

        Assert.assertTrue(viewIsDisplayed, "error was not shown created view");
    }

    @Test(dependsOnMethods = "testCreateNewViewInFolder")
    public void testRename() {

        boolean newNameIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new FolderPage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NEW_NAME);

        Assert.assertTrue(newNameIsDisplayed,"error was not show new name folder");
    }

    @Test(dependsOnMethods = "testRename")
    public void testRenameNegative() {

        CreateItemErrorPage createItemErrorPage = new MainPage(getDriver())
                .clickJobName(NEW_NAME, new FolderPage(getDriver()))
                .rename()
                .enterNewName(NEW_NAME)
                .clickRenameButtonAndGoError();

        Assert.assertEquals(createItemErrorPage.getError(), "Error");
        Assert.assertEquals(createItemErrorPage.getErrorMessage(), "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = {"testCreateFromDashboard", "testCreateFromNewItem"})
    public void testMoveFolderToFolder() {

        String folderName = new MainPage(getDriver())
                .dropDownMenuClickMove(NAME2, new FolderPage(getDriver()))
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(NAME, new FolderPage(getDriver()))
                .getNestedFolder(NAME2);

        Assert.assertEquals(folderName, NAME2);
    }

    @Test(dependsOnMethods = "testRenameNegative")
    public void testConfigureFolderNameDescriptionHealthMetrics() {

        FolderPage folderPage = new MainPage(getDriver())
                .clickJobName(NEW_NAME, new FolderPage(getDriver()))
                .clickConfigureSideMenu()
                .enterDisplayName(DISPLAY_NAME)
                .setHealthMetricsType()
                .addDescription(DESCRIPTION)
                .clickSaveButton();

        Assert.assertEquals(folderPage.getFolderName(), DISPLAY_NAME);
        Assert.assertEquals(folderPage.getFolderDescription(), DESCRIPTION);
        Assert.assertTrue(folderPage.clickConfigureSideMenu().clickOnHealthMetricsType().isRecursive());
    }

    @Test(dependsOnMethods = "testConfigureFolderNameDescriptionHealthMetrics")
    public void testCancelDeleting() {

        boolean folderIsDisplayed = new MainPage(getDriver())
                .clickJobName(NEW_NAME, new FolderPage(getDriver()))
                .delete()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(NEW_NAME);

        Assert.assertTrue(folderIsDisplayed,"error was not show name folder");
    }

    @Test(dependsOnMethods = "testCancelDeleting")
    public void testCreateJobsInFolder() {

        List<String> jobName = Arrays.asList("Freestyle_Project", "Pipeline project", "Multi Configuration Project",
                "Folder", "Multibranch Pipeline", "Organization");

        createdJobInFolder(jobName.get(0), NEW_NAME, TestUtils.JobType.FreestyleProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(1), NEW_NAME, TestUtils.JobType.Pipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(2), NEW_NAME, TestUtils.JobType.MultiConfigurationProject,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(3), NEW_NAME, TestUtils.JobType.Folder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(4), NEW_NAME, TestUtils.JobType.MultibranchPipeline,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));
        createdJobInFolder(jobName.get(5), NEW_NAME, TestUtils.JobType.OrganizationFolder,
                new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())));

        List<String> createdJobList= new MainPage(getDriver())
                .clickJobName(NEW_NAME, new FolderPage(getDriver()))
                .getJobList();

        jobName.sort(String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(createdJobList, jobName);
    }

    @Test(dependsOnMethods = "testCreateJobsInFolder")
    public void testDeleteFolder() {

        boolean welcomeIsDisplayed = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(NEW_NAME)
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeIsDisplayed,"error was not show Welcome to Jenkins!");
    }

   @Test
    public void testTwoFoldersCreation() {
        final String FOLDER1_NAME = "My_folder";
        final String FOLDER2_NAME = "MyFolder2";
        List<String> expectedFoldersList = Arrays.asList(FOLDER1_NAME, FOLDER2_NAME);

        TestUtils.createJob(this, FOLDER1_NAME, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, FOLDER2_NAME, TestUtils.JobType.Folder, true);

       List<String> actualFoldersList  = new MainPage(getDriver())
                .getJobList();

        Assert.assertEquals(actualFoldersList, expectedFoldersList);
    }

    @DataProvider(name = "create-folder")
    public Object[][] provideFoldersNames() {
        return new Object[][]
                {{"My_folder"}, {"MyFolder2"}, {"FOLDER"}};
    }

    @Test(dataProvider = "create-folder")
    public void testFoldersCreationWithProvider(String provideNames) {
        TestUtils.createJob(this, provideNames, TestUtils.JobType.Folder, true);

        Assert.assertEquals(new MainPage(getDriver()).getOnlyProjectName(), provideNames);
    }

    @Test
    public void testMoveFreestyleProjectToFolder() {
        final String projectName = "FreestyleProject";

        boolean movedFreestyleProjectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()

                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()

                .clickMoveOnSideMenu()
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(NAME, new FolderPage(getDriver()))
                .nestedProjectIsDisplayed(projectName);

        Assert.assertTrue(movedFreestyleProjectName, "error was not show moved Freestyle Project");
    }

    @Test
    public void testMoveMultibranchPipelineToFolderByDrop() {
        final String nameMultibranchPipeline = "MultibranchPipeline1";
        final String nameFolder = "Folder1";

        TestUtils.createJob(this, nameFolder, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, nameMultibranchPipeline, TestUtils.JobType.MultibranchPipeline, true);

        String projectNameDisplays = new MainPage(getDriver())
                .dropDownMenuClickMove(nameMultibranchPipeline, new FolderPage(getDriver()))
                .selectDestinationFolder(nameFolder)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(nameFolder, new FolderPage(getDriver()))
                .getNestedFolder(nameMultibranchPipeline);

        Assert.assertEquals(projectNameDisplays, nameMultibranchPipeline);
    }

    @Test
    public void testMoveFolderToFolderFromSideMenu() {
        String folder1 = "Folder1";
        String folder2 = "Folder2";

        TestUtils.createJob(this, folder1, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, folder2, TestUtils.JobType.Folder, true);

        String nestedFolder = new MainPage(getDriver())
                .clickJobName(folder2, new FolderPage(getDriver()))
                .clickMoveOnSideMenu(folder2)
                .selectDestinationFolder(folder1)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(folder1, new FolderPage(getDriver()))
                .getNestedFolder(folder2);

        Assert.assertEquals(nestedFolder, folder2);
    }

    @Test
    public void testMoveMultibranchPipelineToFolderFromSideMenu() {
        final String nameMultibranchPipeline = "MultibranchPipeline1";
        final String nameFolder = "Folder1";

        TestUtils.createJob(this, nameFolder, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, nameMultibranchPipeline, TestUtils.JobType.MultibranchPipeline, true);

        String nameMultibranchPipelineDisplays = new MainPage(getDriver())
                .dropDownMenuClickMove(nameMultibranchPipeline, new MultibranchPipelinePage(getDriver()))
                .selectDestinationFolder(nameFolder)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(nameFolder, new FolderPage(getDriver()))
                .getMultibranchPipelineName();

        Assert.assertEquals(nameMultibranchPipelineDisplays, nameMultibranchPipeline);
    }

    @Test
    public void testMoveMultiConfigurationProjectToFolderFromSideMenu() {

        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        final String multiConfigurationProjectName = "MyMultiConfigurationProject";

        String createdMultiConfigurationProjectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(multiConfigurationProjectName)
                .selectJobType(TestUtils.JobType.MultiConfigurationProject)
                .clickOkButton(new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(multiConfigurationProjectName, new MultiConfigurationProjectPage(getDriver()))
                .clickMoveOnSideMenu()
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(NAME, new FolderPage(getDriver()))

                .getNestedMultiConfigurationProjectName(multiConfigurationProjectName);

        Assert.assertEquals(createdMultiConfigurationProjectName, multiConfigurationProjectName);
    }

    @Test
    public void testCreatePipelineProjectWithoutDescriptionInFolder() {
        final String folderName = "folderName";
        final String pipelineName = "pipelineName";

        String projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                .clickSaveButton()

                .clickNewItem()
                .enterItemName(pipelineName)
                .selectJobType(TestUtils.JobType.Pipeline)
                .clickOkButton(new PipelineConfigPage(new PipelinePage(getDriver())))
                .clickSaveButton()
                .getProjectName();

        FolderPage folderPage = new PipelinePage(getDriver())
                .getHeader()
                .clickLogo()
                .clickJobName(folderName, new FolderPage(getDriver()));

        Assert.assertTrue(folderPage.getNestedPipelineProjectName(pipelineName).contains(pipelineName));
        Assert.assertEquals(projectName, "Pipeline " + pipelineName);
    }

    @Test
    public void testMovePipelineToFolder() {
        TestUtils.createJob(this, "testFolder", TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, "testPipeline", TestUtils.JobType.Pipeline, true);

        String actualBreadcrumbText =
                new MainPage(getDriver())
                        .dropDownMenuClickMove("testPipeline", new FolderPage(getDriver()))
                        .selectDestinationFolder("testFolder")
                        .clickMoveButton().
                        getBreadcrumbText();

        Assert.assertEquals(actualBreadcrumbText, "Dashboard > testFolder > testPipeline");
    }

    @Test
    public void testMoveOrganizationFolderToFolderFromSideMenu() {
        final String organizationFolderName = "organizationFolder";
        TestUtils.createJob(this, NAME, TestUtils.JobType.Folder, true);

        String orgFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()

                .clickJobName(organizationFolderName, new OrganizationFolderPage(getDriver()))
                .clickMoveOnSideMenu()
                .selectDestinationFolder(NAME)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(NAME, new FolderPage(getDriver()))

                .getNestedOrganizationFolder(organizationFolderName);

        Assert.assertEquals(orgFolderName, organizationFolderName);
    }
}

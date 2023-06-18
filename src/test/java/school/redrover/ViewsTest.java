package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.FolderPage;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FolderConfigPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ViewsTest extends BaseTest {

    private void createNewFreestyleProjectFromMyViewsPage(String projectName) {
        new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(projectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo();
    }

    private void createNewFreestyleProjectAndNewView(String name) {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickDashboard()
                .createNewView()
                .setNewViewName(name)
                .selectListView()
                .clickCreateButton()
                .clickViewConfigOkButton()
                .clickDashboard()
                .clickViewJob(name)
                .clickEditView(name);
    }

    @Test
    public void testCreateAJobInThePageMyViews() {
        final String newViewNameRandom = RandomStringUtils.randomAlphanumeric(5);

        boolean isJobPresent = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(newViewNameRandom)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .verifyJobIsPresent(newViewNameRandom);

        Assert.assertTrue(isJobPresent);
    }

    @Test
    public void testCreateAJobFromMyViewsPage() {

        final String newViewNameRandom = RandomStringUtils.randomAlphanumeric(5);

        new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickCreateAJob()
                .enterAnItemName(newViewNameRandom)
                .clickFreestyleProject()
                .clickOkButton()
                .clickSaveButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Project " + newViewNameRandom);
    }

    @Test
    public void testAddDescriptionFromMyViewsPage() {
        final String newViewDescriptionRandom = RandomStringUtils.randomAlphanumeric(7);

        String description = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickOnDescription()
                .clearTextFromDescription()
                .enterDescription(newViewDescriptionRandom)
                .clickSaveButtonDescription()
                .getTextFromDescription();

        Assert.assertEquals(description, newViewDescriptionRandom);
    }

    @Test (dependsOnMethods = "testAddDescriptionFromMyViewsPage")
    public void testEditDescription() {

        final String newViewNewDescriptionRandom = RandomStringUtils.randomAlphanumeric(7);

        String description = new MyViewsPage(getDriver())
                .clickOnDescription()
                .clearTextFromDescription()
                .enterNewDescription(newViewNewDescriptionRandom)
                .clickSaveButtonDescription()
                .getTextFromDescription();

        Assert.assertEquals(description, newViewNewDescriptionRandom);
    }

    @Test
    public void testCreateListView() {
        String freestyleProjectName = "Test Freestyle Project";
        String expectedName = "TestName";
        String actualName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .createNewView()
                .setNewViewName(expectedName)
                .selectListView()
                .clickCreateButton()
                .clickSaveButton()
                .getViewName();

        assertEquals(actualName, expectedName);
    }

    @Test
    public void testCreateNewViewSecond() {
        final String newProjectName = "Test Freestyle Name";
        final String expectedViewName = "My New Vew";
        createNewFreestyleProjectFromMyViewsPage(newProjectName);
        String actualViewName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewViewButton()
                .setNewViewName(expectedViewName)
                .selectMyView()
                .clickCreateMyViewButton()
                .getActiveViewName();

        Assert.assertEquals(actualViewName, expectedViewName);
    }

    @Test(dependsOnMethods = "testCreateNewViewSecond")
    public void testRenameView() {
        final String expectedEditedMyViewText = "My View Edited";
        String actualViewName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickInactiveLastCreatedMyView()
                .editMyViewNameAndClickSubmitButton(expectedEditedMyViewText)
                .getActiveView();

        assertEquals(actualViewName, expectedEditedMyViewText);
    }

    @Test(dependsOnMethods = "testRenameView")
    public void testDeleteMyView() {
        final int numberOfAllViews;
        final int numberOfAllViewsAfterDeletion;

        numberOfAllViews = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .getListOfAllViews().size();

        numberOfAllViewsAfterDeletion = new MyViewsPage(getDriver())
                .clickInactiveLastCreatedMyView()
                .clickDeleteViewButton()
                .clickYesButton()
                .getListOfAllViews().size();

        assertEquals(numberOfAllViews - numberOfAllViewsAfterDeletion, 1 );
    }

    @Test
    public void testDeleteView() {
        final String freestyleProjectName = "Test Freestyle Project";
        final String viewName = "NewView";

        boolean isDeletedViewPresent = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickDashboard()
                .createNewView()
                .setNewViewName(viewName)
                .selectListView()
                .clickCreateButton()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickOnView(viewName)
                .clickDeleteView()
                .clickYesButton()
                .verifyViewIsPresent(viewName);

        Assert.assertFalse(isDeletedViewPresent);
    }

    @Test
    public void testMoveFolderToNewViewList() {
        final String folderName1 = "f1";
        final String folderName2 = "f2";
        final String viewName = "view1";

        TestUtils.createJob(this, folderName1, TestUtils.JobType.Folder, true);
        TestUtils.createJob(this, folderName2, TestUtils.JobType.Folder, true);

        ViewPage viewPage = new MainPage(getDriver())
                .createNewView()
                .setNewViewName(viewName)
                .selectListView()
                .clickCreateButton()
                .selectJobsInJobFilters(folderName1)
                .clickSaveButton();

        Assert.assertEquals(viewPage.getViewName(), viewName);
        Assert.assertEquals(viewPage.getJobName(folderName1), folderName1);
   }

    @Test(dependsOnMethods = "testMoveFolderToNewViewList")
    public void testCreateNewViewWithJobFilters() {
        final String folderName1 = "f1";
        final String folderName2 = "f2";
        final String viewName1 = "view1";
        final String viewName2 = "view2";
        final String jobName1 = "job1";
        final String jobName2 = "job2";
        final String jobName3 = "job3";
        final List<String> expectedViewJobs = Arrays.asList(folderName1 + " » " + jobName1, folderName1 + " » " + jobName3, folderName2);

         new MainPage(getDriver()).clickOnView(viewName1);

                TestUtils.createFreestyleProjectInsideFolderAndView(this, jobName1, viewName1, folderName1);
                TestUtils.createFreestyleProjectInsideFolderAndView(this, jobName2, viewName1, folderName1);
                TestUtils.createFreestyleProjectInsideFolderAndView(this, jobName3, viewName1, folderName1);

        ViewPage viewPage = new ViewPage(getDriver())
                .createNewView()
                .setNewViewName(viewName2)
                .selectListView()
                .clickCreateButton()
                .selectRecurseCheckbox()
                .scrollToAddJobFilterDropDown()
                .chooseJobsInJobFilters(folderName1 + " » " + jobName1)
                .chooseJobsInJobFilters(folderName1 + " » " + jobName3)
                .chooseJobsInJobFilters(folderName2)
                .clickSaveButton();

        List<String> actualViewJobsTexts = viewPage.getJobNamesList();

        Assert.assertEquals(viewPage.getViewName(), viewName2);
        Assert.assertEquals(actualViewJobsTexts.size(), 3);
        Assert.assertEquals(actualViewJobsTexts, expectedViewJobs);
    }

    @Test
    public void testCreateMyView() {
        String  newView = new MainPage(getDriver())
                 .clickNewItem()
                 .enterItemName("TestFolder")
                .selectJobType(TestUtils.JobType.Folder)
                 .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
                 .getHeader()
                 .clickLogo()
                 .clickJobName("TestFolder", new FolderPage(getDriver()))
                 .clickNewView()
                 .enterViewName("MyNewView")
                 .selectMyViewAndClickCreate()
                 .getMyView();

        assertEquals(newView, "MyNewView");
    }

    @Test
    public void testHelpForFeatureDescription() {
        this.createNewFreestyleProjectAndNewView("TestFreestyleName");

        String helpFeature = new ViewPage(getDriver())
                .clickHelpFeatureDescription()
                .getTextHelpFeatureDescription();

        Assert.assertEquals(
                helpFeature,
                "This message will be displayed on the view page . Useful " +
                        "for describing what this view does or linking to " +
                        "relevant resources. Can contain HTML tags or whatever" +
                        " markup language is defined for the system."
                );
    }

    @Test
    public void testAddViewDescriptionPreview(){
        final String projectName = "R_R";
        String randomText = "java test program";

        this.createNewFreestyleProjectAndNewView(projectName);

        String previewText =
                new ViewPage( getDriver())
                        .enterDescription(randomText)
                        .clickPreview()
                        .getPreviewText();

        String textDescription =
                new ViewPage(getDriver())
                        .clickViewConfigOkButton()
                        .getDescriptionText();

        Assert.assertEquals(previewText,randomText);
        Assert.assertEquals(textDescription,randomText);
    }
}
package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ViewPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class NewViewTest extends BaseTest {
    private static final String NEW_VIEW_NAME_RANDOM = "NEW_VIEW_NAME_RANDOM";
    private static final By CREATED_LIST_VIEW = By.xpath("//a[@href='/view/" + NEW_VIEW_NAME_RANDOM + "/']");
    private static final String RANDOM_LIST_VIEW_NAME = "RANDOM_LIST_VIEW_NAME";

    private void createNewFreestyleProjectFromMyViewsPage(String projectName) {
        new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(projectName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo();
    }

    private List<String> getListFromWebElements(List<WebElement> elements) {
        List<String> list = new ArrayList<>();
        for (WebElement element : elements) {
            list.add(element.getText());
        }

        return list;
    }

    @Test
    public void testCreateListView() {
        String freestyleProjectName = "Test Freestyle Project";
        String expectedName = "TestName";
        String actualName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectFreestyleProjectAndOk()
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

    @Test
    public void testDeleteView() {
        final String newProjectName = "Test Freestyle Name";
        this.createNewFreestyleProjectFromMyViewsPage(newProjectName);
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(CREATED_LIST_VIEW).click();
        getDriver().findElement(By.linkText("Delete View")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        List<String> listViews = getListFromWebElements(getDriver().findElements(
                By.xpath("//div[@class='tabBar']/div")));

        Assert.assertFalse(listViews.contains(RANDOM_LIST_VIEW_NAME));
    }

    @Test
    public void testMoveFolderToNewViewList() {
        final String folderName1 = "f1";
        final String folderName2 = "f2";
        final String viewName = "view1";

        TestUtils.createFolder(this, folderName1, true);
        TestUtils.createFolder(this, folderName2, true);

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
        WebElement newView = new MainPage(getDriver())
                 .clickNewItem()
                 .enterItemName("TestFolder")
                 .selectFolderAndOk()
                 .getHeader()
                 .clickLogo()
                 .clickFolderName("TestFolder")
                 .clickNewView()
                 .enterViewName("MyNewView")
                 .selectMyViewAndClickCreate()
                 .getMyView();

        assertEquals(newView.getText(), "MyNewView");
    }
    @Test
    public void testHelpForFeatureDescription() {
        final String newProjectName = "Test Freestyle Name";
        String randomName = "randomName";
        String expectedResult =
                "This message will be displayed on the view page . Useful " +
                        "for describing what this view does or linking to " +
                        "relevant resources. Can contain HTML tags or whatever" +
                        " markup language is defined for the system.";

        this.createNewFreestyleProjectFromMyViewsPage(newProjectName);

        getDriver().findElement(By.xpath("//div/a[@href='/newView']")).click();
        getDriver().findElement(By.xpath("//div/input[@checkurl='checkViewName']")).sendKeys(randomName);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//div/ol/li/a[@href='/']")).click();

        getDriver().findElement(By.xpath("//div/a[@href='/view/" + randomName + "/']")).click();
        getDriver().findElement(By.xpath("//div/span/a[@href='/view/" + randomName + "/configure']")).click();

        getDriver().findElement(By.xpath("//div/a[@helpurl='/help/view-config/description.html']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class='help-area tr']/div/div")).getText(),
                expectedResult
        );
    }
}
package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
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
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");

    private void createNewProjectFromMyViewsPage() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hudson_model_FreeStyleProject"))).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
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
        String freestyleProjectName = "TestFreestyleProject";
        String expectedName = "TestName";
        String actualName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickDashboard()
                .createNewView()
                .setNewViewName(expectedName)
                .selectListView()
                .clickCreateButton()
                .clickViewConfigOkButton()
                .getViewName();

        assertEquals(actualName, expectedName);
    }

    @Test
    public void testCreateNewViewSecond() {
        createNewProjectFromMyViewsPage();

        getDriver().findElement(By.cssSelector("a.addTab")).click();
        getDriver().findElement(By.cssSelector("input#name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.cssSelector("input#hudson\\.model\\.MyView + label")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("div.tab.active")).getText(), NEW_VIEW_NAME_RANDOM);
    }

    @Ignore
    @Test
    public void testRenameView() {
        createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
        getDriver().findElement(CREATED_LIST_VIEW).click();
        getDriver().findElement(By.linkText("Edit View")).click();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main']/input[@name='name']")).clear();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main']/input[@name='name']")).sendKeys("RenameView");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='/view/RenameView/']")).getText(), "RenameView");
    }

    @Test
    public void testDeleteView() {
        this.createNewProjectFromMyViewsPage();
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
                .clickViewConfigOkButton();

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
                .clickViewConfigOkButton();

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
                 .selectFolderAndOk().clickDashboard()
                 .clickFolderName("TestFolder")
                 .clickNewView()
                 .enterViewName("MyNewView")
                 .selectMyViewAndClickCreate()
                 .getMyView();

        assertEquals(newView.getText(), "MyNewView");
    }
    @Test
    public void testHelpForFeatureDescription() {

        String randomName = "randomName";
        String expectedResult =
                "This message will be displayed on the view page . Useful " +
                        "for describing what this view does or linking to " +
                        "relevant resources. Can contain HTML tags or whatever" +
                        " markup language is defined for the system.";

        this.createNewProjectFromMyViewsPage();

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
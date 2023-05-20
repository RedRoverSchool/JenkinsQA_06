package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertEquals;


public class NewViewTest extends BaseTest {
    private static final String NEW_VIEW_NAME_RANDOM = RandomStringUtils.randomAlphanumeric(5);
    private static final By CREATED_LIST_VIEW = By.xpath("//a[@href='/view/" + NEW_VIEW_NAME_RANDOM + "/']");
    private static final String RANDOM_LIST_VIEW_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final By SAVE_BUTTON = By.name("Submit");

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

    private void chooseJobsInJobFilters (String name) {
        List<WebElement> viewJobList = getDriver().findElements(By.xpath("//div[@class = 'listview-jobs']/span"));

        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
    }

    private void clickBreadcrumbPathItem(int n, String name) {
        List<WebElement> breadcrumbTree = getDriver().findElements(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a"));
        if (breadcrumbTree.get(breadcrumbTree.size() - n).getText().contains(name)) {
            breadcrumbTree.get(breadcrumbTree.size() - n).click();
        }
    }

    private void createFreestyleProjectInsideFolderAndView(String jobName, String viewName, String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//tr[@id='job_%s']//a", folderName)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/job/%s/newJob']", viewName, folderName)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("name")))).sendKeys(jobName);
        getDriver().findElement(By.xpath("//span[@class='label'][text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//div[@class='btn-decorator']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        clickBreadcrumbPathItem(3, viewName);
    }

    private List<String> getListTexts(List<WebElement> list) {
        if (list.size() > 0) {
            getWait10().until(ExpectedConditions.visibilityOfAllElements(list));
            List<String> textList = new ArrayList<>();
            for (WebElement element : list) {
                textList.add(element.getText());
            }
            return textList;
        }
        return null;
    }

    @Ignore
    @Test
    public void testCreateNewView() {
        createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@name='Submit']"))).click();

        Assert.assertTrue(getDriver().findElement(CREATED_LIST_VIEW).isDisplayed());
    }

    @Ignore
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

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/newView']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(viewName);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(SAVE_BUTTON).click();

        chooseJobsInJobFilters(folderName1);
        getWait2().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'tab active']")).getText(), viewName);
        Assert.assertEquals(getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']", folderName1))).getText(), folderName1);
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

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/']", viewName1)))).click();

        createFreestyleProjectInsideFolderAndView(jobName1, viewName1, folderName1);
        createFreestyleProjectInsideFolderAndView(jobName2, viewName1, folderName1);
        createFreestyleProjectInsideFolderAndView(jobName3, viewName1, folderName1);

        getWait2().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//a[@href='/newView']")))).click();
        getDriver().findElement(By.id("name")).sendKeys(viewName2);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//label[contains(text(), 'Recurse in subfolders')]")))).click();

        new Actions(getDriver()).scrollToElement(getDriver().findElement(By.id("yui-gen1-button"))).perform();
        chooseJobsInJobFilters(folderName1 + " » " + jobName1);
        chooseJobsInJobFilters(folderName1 + " » " + jobName3);
        chooseJobsInJobFilters(folderName2);
        getDriver().findElement(SAVE_BUTTON).click();

        List<WebElement> viewJobsList = getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("tr[id^=job_]>td:nth-child(3) span")));
        List<String> actualViewJobsTexts = getListTexts(viewJobsList);

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'tab active']")).getText(), viewName2);
        Assert.assertEquals(viewJobsList.size(), 3);
        Assert.assertEquals(actualViewJobsTexts, expectedViewJobs);
    }

    @Test
    public void testCreateMyView() {
        TestUtils.createFolder(this, "TestFolder", true);

        getDriver().findElement(By.linkText("TestFolder")).click();
        getDriver().findElement(By.xpath("//a[@title= 'New View']")).click();

        getDriver().findElement(By.name("name")).sendKeys("TestView");
        getDriver().findElement(By.xpath("//label[@for= 'hudson.model.MyView']")).click();
        getDriver().findElement(By.id("ok")).click();

        WebElement newView = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("TestView")));

        assertEquals(newView.getText(), "TestView");
    }
}
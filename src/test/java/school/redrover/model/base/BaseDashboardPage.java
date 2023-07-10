package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.jobs.FolderPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public abstract class BaseDashboardPage<Self extends BaseDashboardPage<?>> extends BaseMainHeaderPage<Self> {

    @FindBy(css = "#ok-button")
    private WebElement okButton;

    @FindBy(id = "description-link")
    private WebElement onDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement enterDescription;

    @FindBy(xpath = "//button[@class='jenkins-button jenkins-button--primary ']")
    private WebElement saveButtonDescription;

    @FindBy(xpath = "//div[@id='description']//div[not(@class)]")
    private WebElement descriptionText;

    @FindBy(xpath = "//span[contains(text(), 'Delete') and not(contains(text(), 'View'))]")
    private WebElement deleteInDropDownMenu;

    @FindBy(xpath = "//li//span[contains(text(), 'Rename')]")
    private WebElement renameInDropDownMenu;

    @FindBy(xpath = "//span[contains(text(), 'Move')]")
    private WebElement moveInDropDownMenu;

    @FindBy(xpath = "//span[text()='Build Now']")
    private WebElement buildNowInDropDownMenu;

    @FindBy(xpath = "//div//li//span[contains(text(),'Configure')]")
    private WebElement configureInDropDownMenu;

    @FindBy(xpath = "//a[contains(@href, 'github.com')]")
    private WebElement gitHubFromDropDownMenu;

    @FindBy(xpath = "//a[normalize-space(text())= 'Sign in']")
    private WebElement singInFromHubGitHub;

    @FindBy(xpath = "//div[@id = 'breadcrumb-menu' and @class = 'yui-module yui-overlay yuimenu visible']//li/a/span")
    private List<WebElement> listOfJobMenuItems;

    @FindBy(xpath = "//a[@href='newJob']")
    private WebElement createAJob;

    @FindBy(css = ".task-link-wrapper>a[href$='newJob']")
    private WebElement newItem;

    @FindBy(xpath = "//a[@href='newJob']/span[@class = 'trailing-icon']")
    private WebElement createAJobArrow;

    @FindBy(xpath = "//span/a[@href='/asynchPeople/']")
    private WebElement people;

    @FindBy(xpath = "//span/a[@href='/view/all/builds']")
    private WebElement buildHistory;

    @FindBy(xpath = "//a[contains(@href,'/newView')]")
    private WebElement newView;

    @FindBy(xpath = "//td[@class='jenkins-table__cell--tight']")
    private WebElement buildButton;

    @FindBy(xpath = "//a[contains(@tooltip,'Schedule a Build for ')]")
    private List<WebElement> jobsBuildLinks;

    @FindBy(css = ".jenkins-table__link")
    private List<WebElement> jobList;

    @FindBy(xpath = "//a[contains(text(), 'Name')]")
    private WebElement sortByName;

    @FindBy(xpath = "//a[@href='/computer/']")
    private WebElement buildExecutorStatus;

    @FindBy(xpath = "//div[@class = 'tab active']")
    private WebElement activeViewTab;

    @FindBy(linkText = "All")
    private WebElement allLink;

    @FindBy(css = "svg[title='Folder']")
    private WebElement iconFolder;

    @FindBy(xpath = "//td/a[text()='#1']/button")
    private WebElement dropDownBuildButton;

    @FindBy(xpath = "//a[contains(@href, 'confirmDelete')]")
    private WebElement deleteBuildDropDown;

    public BaseDashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    public Self clickOnDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(onDescription)).click();
        return (Self) this;
    }

    public Self enterDescription(String name) {
        getWait5().until(ExpectedConditions.visibilityOf(enterDescription)).sendKeys(name);
        return (Self) this;
    }

    public Self clickSaveButtonDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(saveButtonDescription)).click();
        return (Self) this;
    }

    public Self clearTextFromDescription() {
        getWait10().until(ExpectedConditions.visibilityOf(enterDescription)).clear();
        return (Self) this;
    }

    public Self openJobDropDownMenu(String jobName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(String.format("//a[contains(@href,'job/%s/')]/button", jobName
                                .replaceAll(" ", "%20")))))
                .sendKeys(Keys.RETURN);
        return (Self) this;
    }

    public NewJobPage selectNewItemInJobDropDownMenu(String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[contains(@href,'/job/%s/newJob')]", folderName)))).click();
        return new NewJobPage(getDriver());
    }

    public Self dropDownMenuClickDelete(String jobName) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteInDropDownMenu)).click();
        return (Self) this;
    }

    public <JobTypePage extends BasePage<?, ?>> RenamePage<JobTypePage> dropDownMenuClickRename(String jobName, JobTypePage jobTypePage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(renameInDropDownMenu)).click();
        return new RenamePage<>(jobTypePage);
    }

    public <JobTypePage extends BasePage<?, ?>> MovePage<JobTypePage> dropDownMenuClickMove(String jobName, JobTypePage jobTypePage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(moveInDropDownMenu)).click();
        return new MovePage<>(jobTypePage);
    }

    public Self clickJobDropdownMenuBuildNow(String jobName) {
        openJobDropDownMenu(jobName);
        getWait2().until(ExpectedConditions.elementToBeClickable(buildNowInDropDownMenu)).click();
        return (Self) this;
    }

    public <JobConfigPage extends BaseConfigPage<?, ?>> JobConfigPage clickConfigureDropDown(String jobName, JobConfigPage jobConfigPage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(configureInDropDownMenu)).click();
        return jobConfigPage;
    }

    public String getJobBuildStatusByWeatherIcon(String jobName) {
        WebElement buildStatus = getDriver().findElement(By.id(String.format("job_%s", jobName)))
                .findElement(By.xpath(".//*[contains(@href, 'build-status-static')]/.."));
        return buildStatus.getAttribute("tooltip");
    }

    public String getJobBuildStatusIcon(String jobName) {
        return getDriver().findElement(By.id(String.format("job_%s", jobName))).findElement(
                        By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']"))
                .getAttribute("tooltip");
    }

    public List<String> getListOfProjectMenuItems(String jobName) {
        openJobDropDownMenu(jobName);
        return TestUtils.getTexts(listOfJobMenuItems);
    }

    public String selectFromJobDropdownMenuTheGitHub(String jobName) {
        openJobDropDownMenu(jobName);
        gitHubFromDropDownMenu.click();
        return singInFromHubGitHub.getText();
    }

    public DeletePage<Self> dropDownMenuClickDeleteFolders(String jobName) {
        dropDownMenuClickDelete(jobName);
        return new DeletePage<>((Self) this);
    }

    public NewJobPage clickCreateAJob() {
        getWait2().until(ExpectedConditions.elementToBeClickable(createAJob)).click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickNewItem() {
        newItem.click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickCreateAJobArrow() {
        getWait2().until(ExpectedConditions.elementToBeClickable(createAJobArrow)).click();
        return new NewJobPage(getDriver());
    }

    public PeoplePage clickPeopleOnLeftSideMenu() {
        people.click();
        return new PeoplePage(getDriver());
    }

    public BuildHistoryPage clickBuildsHistoryButton() {
        TestUtils.click(this, buildHistory);
        return new BuildHistoryPage(getDriver());
    }

    public <JobPage extends BasePage<?, ?>> JobPage clickJobName(String jobName, JobPage jobPage) {
        WebElement job = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']", jobName.replaceAll(" ", "%20")))));
        new Actions(getDriver()).moveToElement(job).click(job).perform();
        return jobPage;
    }

    public Self acceptAlert() {
        getDriver().switchTo().alert().accept();
        return (Self) this;
    }


    public Self dismissAlert() {
        getDriver().switchTo().alert().dismiss();
        return (Self) this;
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(newView)).click();
        return new NewViewPage(getDriver());
    }

    public <ViewBasePage extends BaseDashboardPage<?>> ViewBasePage clickOnView(String viewName, ViewBasePage viewBasePage) {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//a[@href='/view/%s/']", viewName)))).click();
        return viewBasePage;
    }

    public Self clickPlayBuildForATestButton(String projectName) {
        TestUtils.click(this, getDriver().findElement(
                By.xpath("//a[@href='job/" + projectName + "/build?delay=0sec']")));
        return (Self) this;
    }

    public <JobTypePage extends BaseProjectPage<?>> BuildWithParametersPage<JobTypePage> clickBuildButton(JobTypePage jobTypePage) {
        getWait2().until(ExpectedConditions.elementToBeClickable(buildButton)).click();
        return new BuildWithParametersPage<>(jobTypePage);
    }

    public List<String> getJobList() {
        return jobList
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<WebElement> getProjectsList() {
        return getProjectStatusTable().findElements(By.xpath("./tbody/tr"));
    }

    private WebElement getProjectStatusTable() {

        return getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")))
                .findElement(By.id("projectstatus"));
    }

    public boolean projectStatusTableIsDisplayed() {
        return getProjectStatusTable().isDisplayed();
    }

    public String getJobName(String projectName) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//tr[@id='job_" + projectName + "']//a//span['" + projectName + "']")))
                .getText();
    }

    public boolean jobIsDisplayed(String jobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath(String.format("//a[@href='job/%s/']", jobName.replaceAll(" ", "%20")))))).isDisplayed();
    }

    public boolean verifyJobIsPresent(String jobName) {
        List<WebElement> jobs = jobList;
        boolean status = false;
        for (WebElement job : jobs) {
            if (!job.getText().equals(jobName)) {
                status = false;
            } else {
                new Actions(getDriver()).moveToElement(job).build().perform();
                status = true;
                break;
            }
        }
        return status;
    }

    public boolean verifyViewIsPresent(String viewName) {
        boolean status = false;

        List<WebElement> views = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tabBar']")))
                .findElements(By.xpath("//div[@class='tabBar']/div"));
        for (WebElement view : views) {
            if (view.getText().equals(viewName)) {
                status = true;
                break;
            }
        }
        return status;
    }

    public boolean isScheduleBuildOnDashboardAvailable(String jobName) {
        boolean status = false;

        List<WebElement> scheduleBuildLinks = jobsBuildLinks;
        for (WebElement link : scheduleBuildLinks) {
            String tooltip = link.getAttribute("tooltip");
            if (jobName.equals(tooltip.substring(tooltip.length() - jobName.length()))) {
                status = true;
                break;
            }
        }
        return status;
    }

    public Self clickSortByName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(sortByName)).click();
        return (Self) this;
    }

    public ManageNodesPage clickBuildExecutorStatus() {
        getWait2().until(ExpectedConditions.elementToBeClickable(buildExecutorStatus)).click();
        return new ManageNodesPage(getDriver());
    }

    public String getActiveViewName() {
        return TestUtils.getText(this, activeViewTab);
    }

    public FolderPage clickAllOnFolderView() {
        allLink.click();
        return new FolderPage(getDriver());
    }

    public boolean isIconFolderDisplayed() {
        return iconFolder.isDisplayed();
    }

    public Self hoverOverWeather(String jobName){
        WebElement weather = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//tr[@id = 'job_%s']/td[contains(@class,'healthReport')]", jobName))));
        new Actions(getDriver())
                .moveToElement(weather)
                .perform();
        return (Self)this;
    }

    public BuildPage clickBuildDropdownMenuDeleteBuild(String buildNumber) {
        openBuildDropDownMenu(buildNumber);
        getWait2().until(ExpectedConditions.elementToBeClickable(deleteBuildDropDown)).click();
        return new BuildPage(getDriver());
    }

    public Self openBuildDropDownMenu(String buildNumber) {
        Actions act = new Actions(getDriver());
        act.moveToElement(dropDownBuildButton).perform();
        dropDownBuildButton.sendKeys(Keys.RETURN);
        return (Self)this;
    }
}

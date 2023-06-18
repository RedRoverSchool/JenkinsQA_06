package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MainPage extends BaseMainHeaderPage<MainPage>  {

    @FindBy(xpath = "//div[@id = 'breadcrumbBar']//a")
    private WebElement dashboard;

    @FindBy(xpath = "//div[@id = 'breadcrumbBar']//button")
    private WebElement sliderDashboard;

    @FindBy(xpath = "//*[@id='yui-gen4']/a/span")
    private WebElement manageJenkinsInSliderDashboard;

    @FindBy(css = ".task-link-wrapper>a[href$='newJob']")
    private WebElement newItem;

    @FindBy(xpath = "//div[@id='main-panel']//span[text() = 'Create a job']")
    private WebElement createAJob;

    @FindBy(xpath = "//a[@href='newJob']/span[@class = 'trailing-icon']")
    private WebElement createAJobArrow;

    @FindBy(xpath = "//span/a[@href='/asynchPeople/']")
    private WebElement people;

    @FindBy(xpath = "//span/a[@href='/view/all/builds']")
    private WebElement buildHistory;

    @FindBy(css = "[href='/manage']")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href='/me/my-views']")
    private WebElement myViews;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private WebElement deleteInDropDownMenu;

    @FindBy(xpath = "//span[contains(text(), 'Rename')]")
    private WebElement renameInDropDownMenu;

    @FindBy(xpath = "//span[contains(text(), 'Move')]")
    private WebElement moveInDropDownMenu;

    @FindBy(xpath = "//span[text()='Build Now']")
    private WebElement buildNowInDropDownMenu;

    @FindBy(xpath = "//div//li//span[contains(text(),'Configure')]")
    private WebElement configureInDropDownMenu;

    @FindBy(xpath = "//td[@class='jenkins-table__cell--tight']")
    private WebElement buildButton;

    @FindBy(xpath = "//a[@href='/newView']")
    private WebElement newView;

    @FindBy(xpath = "//a[contains(@href,'api')]")
    private WebElement restApi;

    @FindBy(css = ".jenkins-table__link")
    private List<WebElement> jobList;

    @FindBy(xpath = "//div[@id = 'breadcrumb-menu' and @class = 'yui-module yui-overlay yuimenu visible']//li/a/span")
    private List<WebElement> listOfJobMenuItems;

    @FindBy(css = ".job-status-nobuilt td>a>span")
    private WebElement onlyJob;

    @FindBy(css = "svg[title='Folder']")
    private WebElement iconFolder;

    @FindBy(xpath = "//h1[text()='Welcome to Jenkins!']")
    private WebElement welcomeToJenkins;

    @FindBy(xpath = "//div[@class='empty-state-block']/h1")
    private WebElement welcomeJenkins;

    @FindBy(xpath = "//a[@href='/computer/']")
    private WebElement buildExecutorStatus;

    @FindBy(xpath = "//a[contains(text(), 'Name')]")
    private WebElement sortByName;

    @FindBy(xpath = "//a[contains(@href, 'github.com')]")
    private WebElement gitHubFromDropDownMenu;

    @FindBy(xpath = "//a[normalize-space(text())= 'Sign in']")
    private WebElement singInFromHubGitHub;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private void openJobDropDownMenu(String jobName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[contains(@href,'job/%s/')]/button", jobName
                        .replaceAll(" ", "%20")))))
                .sendKeys(Keys.RETURN);
    }

    private void  clickOnSliderDashboardInDropDownMenu() {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(dashboard).perform();
        actions.moveToElement(sliderDashboard).perform();
        sliderDashboard.sendKeys(Keys.RETURN);
    }

    public ManageJenkinsPage clickManageJenkinsOnDropDown() {
        clickOnSliderDashboardInDropDownMenu();
        getWait5().until(ExpectedConditions.elementToBeClickable(manageJenkinsInSliderDashboard)).click();
        return new ManageJenkinsPage(getDriver());
    }

    public NewJobPage clickNewItem() {
        newItem.click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickCreateAJob() {
        getWait2().until(ExpectedConditions.elementToBeClickable(createAJob)).click();
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

    public ManageJenkinsPage clickManageJenkinsPage() {
        manageJenkins.click();
        return new ManageJenkinsPage(getDriver());
    }

    public MyViewsPage clickMyViewsSideMenuLink() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myViews)).click();
        return new MyViewsPage(getDriver());
    }

    public <JobPage extends BasePage<?, ?>> JobPage clickJobName(String jobName, JobPage jobPage) {
        WebElement job = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']", jobName.replaceAll(" ","%20")))));
        new Actions(getDriver()).moveToElement(job).click(job).perform();
        return jobPage;
    }

    public MainPage dropDownMenuClickDelete(String jobName) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteInDropDownMenu)).click();
        return this;
    }

    public DeletePage<MainPage> dropDownMenuClickDeleteFolders(String jobName) {
        dropDownMenuClickDelete(jobName);
        return new DeletePage<>(this);
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return this;
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

    public MainPage clickJobDropdownMenuBuildNow(String jobName) {
        openJobDropDownMenu(jobName);
        getWait2().until(ExpectedConditions.elementToBeClickable(buildNowInDropDownMenu)).click();
        return this;
    }

    public <JobConfigPage extends BaseConfigPage<?, ?>> JobConfigPage clickConfigureDropDown(String jobName, JobConfigPage jobConfigPage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(configureInDropDownMenu)).click();
        return jobConfigPage;
    }

    public String getJobBuildStatusIcon(String jobName) {
        return getDriver().findElement(By.id(String.format("job_%s", jobName))).findElement(
                        By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']"))
                .getAttribute("tooltip");
    }

    public MainPage clickPlayBuildForATestButton(String projectName) {
        TestUtils.click(this, getDriver().findElement(
                By.xpath("//a[@href='job/" + projectName + "/build?delay=0sec']")));
        return new MainPage(getDriver());
    }

    public BuildPage clickBuildButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(buildButton)).click();
        return new BuildPage(getDriver());
    }

    public String getJobBuildStatus(String jobName) {
        openJobDropDownMenu(jobName);
        WebElement buildStatus = getDriver().findElement(By.id(String.format("job_%s", jobName)))
                .findElement(By.xpath(".//*[name()='svg']"));
        return buildStatus.getAttribute("tooltip");
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(newView)).click();
        return new NewViewPage(getDriver());
    }

    public ViewPage clickOnView(String viewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//a[@href='/view/%s/']", viewName)))).click();
        return new ViewPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink() {
        restApi.click();
        return new RestApiPage(getDriver());
    }

    public boolean isMainPageOpen() {
        return getWait5().until(ExpectedConditions.titleContains("Dashboard [Jenkins]"));
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

    public List<String> getListOfProjectMenuItems(String jobName) {
        openJobDropDownMenu(jobName);
        return TestUtils.getTexts(listOfJobMenuItems);
    }

    public String getOnlyProjectName() {
        return getProjectsList().get(0)
                .findElements(By.xpath("./td")).get(2)
                .getText();
    }

    private WebElement getProjectStatusTable() {

        return getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")))
                .findElement(By.id("projectstatus"));
    }

    public boolean projectStatusTableIsDisplayed() {
        return getProjectStatusTable().isDisplayed();
    }

    public String getJobName() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(onlyJob)).getText();
    }

    public String getProjectNameMainPage(String projectName) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[@id='job_" + projectName + "']//a//span['" + projectName + "']")))
                .getText();
    }

    public boolean jobIsDisplayed(String jobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//a[@href='job/" + jobName + "/']")))).isDisplayed();
    }

    public boolean iconFolderIsDisplayed(){
        return iconFolder.isDisplayed();
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public boolean WelcomeIsDisplayed() {
        return welcomeToJenkins.isDisplayed();
    }

    public String  getWelcomeText() {
        return welcomeJenkins.getText();
    }

    public ManageNodesPage clickBuildExecutorStatus() {
        getWait2().until(ExpectedConditions.elementToBeClickable(buildExecutorStatus)).click();
        return new ManageNodesPage(getDriver());
    }

    public MainPage clickSortByName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(sortByName)).click();
        return this;
    }

    public boolean verifyJobIsPresent(String jobName){
        List<WebElement> jobs = jobList;
        boolean status = false;
        for (WebElement job : jobs){
            if (!job.getText().equals(jobName)){
                status = false;
            }
            else{
                new Actions(getDriver()).moveToElement(job).build().perform();
                status = true;
            }
            break;
        }
        return status;
    }

    public MainPage dismissAlert() {
        getDriver().switchTo().alert().dismiss();
        return this;
    }

    public String getTitleValueOfBuildStatusIconElement() {
        WebElement buildStatusIcon
                = getWait5().until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//*[name()='svg'][@title='Success'])[1]")));
        return buildStatusIcon.getAttribute("title");

    }

    public String selectFromJobDropdownMenuTheGitHub(String jobName) {
        openJobDropDownMenu(jobName);
        gitHubFromDropDownMenu.click();
        return singInFromHubGitHub.getText();
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
}

package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

import school.redrover.runner.TestUtils;

import java.util.List;

public class MainPage extends BaseMainHeaderPage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    private void openJobDropDownMenu(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(String.format("//a[contains(@href,'job/%s/')]/button", jobName.replaceAll(" ", "%20")))))
                .sendKeys(Keys.RETURN);
    }

    private void  clickOnSliderDashboardInDropDownMenu() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//div[@id = 'breadcrumbBar']//a"))).perform();

        WebElement slider = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'breadcrumbBar']//button")));
        new Actions(getDriver()).moveToElement(slider).perform();
        slider.sendKeys(Keys.RETURN);
    }

    public NewJobPage clickNewItemInDashboardDropDownMenu() {
        clickOnSliderDashboardInDropDownMenu();
        getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@id = 'breadcrumb-menu-target']//span[text()='New Item']")))
                .click();
        return new NewJobPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkinsOnDropDown() {
        clickOnSliderDashboardInDropDownMenu();
        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        getDriver().findElement(sectionNameLocator).click();
        return new ManageJenkinsPage(getDriver());
    }

    public NewJobPage clickNewItem() {
        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickCreateAJob() {
        WebElement createAJob = getDriver()
                .findElement(By.xpath("//div[@id='main-panel']//span[text() = 'Create a job']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(createAJob));
        createAJob.click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickCreateAJobArrow() {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")))
                .click();

        return new NewJobPage(getDriver());
    }

    public PeoplePage clickPeopleOnLeftSideMenu() {
        getDriver().findElement(By.xpath("//*[@href='/asynchPeople/']")).click();
        return new PeoplePage(getDriver());
    }

    public BuildHistoryPage clickBuildsHistoryButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")));
        return new BuildHistoryPage(getDriver());
    }

    public ManageJenkinsPage navigateToManageJenkinsPage() {
        getDriver().findElement(By.cssSelector("[href='/manage']")).click();
        return new ManageJenkinsPage(getDriver());
    }

    public MyViewsPage clickMyViewsSideMenuLink() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/me/my-views']"))).click();
        return new MyViewsPage(getDriver());
    }

    public ConfigureGlobalSecurityPage navigateToConfigureGlobalSecurityPage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[text()='Configure Global Security']")).click();
        getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Configure Global Security']")));
        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public FolderPage clickFolderName(String folderName) {
        WebElement folder = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']",folderName.replaceAll(" ","%20")))));
        new Actions(getDriver()).moveToElement(folder).click(folder).perform();
        return new FolderPage(getDriver());
    }

    public PipelinePage clickPipelineProject(String pipelineName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']",pipelineName.replaceAll(" ","%20"))))).click();
        return new PipelinePage(getDriver());
    }

    public FreestyleProjectPage clickFreestyleProjectName(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']",jobName.replaceAll(" ","%20"))))).click();

        return new FreestyleProjectPage(getDriver());
    }

    public MultiConfigurationProjectPage clickMultiConfigurationProjectName(String jobName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath(String.format("//a[@href='job/%s/']",jobName.replaceAll(" ","%20")))))).click();
        return new MultiConfigurationProjectPage(getDriver());
    }

    public MultibranchPipelinePage clickMultibranchPipelineName(String multibranchPipeline) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[@href='job/%s/']",multibranchPipeline.replaceAll(" ","%20"))))).click();
        return new MultibranchPipelinePage(getDriver());
    }

    public MainPage dropDownMenuClickDelete(String jobName) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Delete')]"))).click();
        return this;
    }

    public DeleteFoldersPage dropDownMenuClickDeleteFolders(String jobName) {
        dropDownMenuClickDelete(jobName);
        return new DeleteFoldersPage(getDriver());
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return this;
    }

    public <JobTypePage extends BasePage<?>> RenamePage<JobTypePage> dropDownMenuClickRename(String jobName, JobTypePage jobTypePage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Rename')]"))).click();
        return new RenamePage<>(jobTypePage);
    }

    public <JobTypePage extends BasePage<?>> MovePage<JobTypePage> dropDownMenuClickMove(String jobName, JobTypePage jobTypePage) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Move')]"))).click();
        return new MovePage<>(jobTypePage);
    }

    public MainPage clickJobDropdownMenuBuildNow(String jobName) {
        openJobDropDownMenu(jobName);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Build Now']"))).click();
        return this;
    }

    public FreestyleProjectPage clickConfigureDropDown(String jobName) {
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div//li//span[contains(text(),'Configure')]"))).click();
        return new FreestyleProjectPage(getDriver());
    }
    public MultiConfigurationProjectPage clickJobMultiConfigurationProject(String jobName) {
        openJobDropDownMenu(jobName);
        WebElement job = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath(String.format("//a[@href='job/%s/']",jobName.replaceAll(" ","%20"))))));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(job).click(job).perform();
        return new MultiConfigurationProjectPage(getDriver());
    }

    public String getJobBuildStatusIcon(String jobName) {
        return getDriver().findElement(By.id(String.format("job_%s", jobName))).findElement(
                        By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']"))
                .getAttribute("tooltip");
    }

    public MainPage clickPlayBuildForATestButton(String projectName) {
        TestUtils.click(this, getDriver().findElement(By.xpath("//a[@href='job/" + projectName + "/build?delay=0sec']")));
        return new MainPage(getDriver());
    }

    public BuildPage clickBuildButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='jenkins-table__cell--tight']"))).click();
        return new BuildPage(getDriver());
    }

    public MainPage clickSchedulerBuildForPipeline(String pipelineName) {
        WebElement buildRunnerButton = getDriver()
                .findElement(By.xpath("//a[@title = 'Schedule a Build for " + pipelineName + "']"));
        buildRunnerButton.click();
        return this;
    }
    public String getJobBuildStatus(String jobName) {
        openJobDropDownMenu(jobName);
        WebElement buildStatus = getDriver().findElement(By.id(String.format("job_%s", jobName)))
                .findElement(By.xpath(".//*[name()='svg']"));
        return buildStatus.getAttribute("tooltip");
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/newView']"))).click();

        return new NewViewPage(getDriver());
    }

    public ViewPage clickOnView(String viewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/']", viewName)))).click();

        return new ViewPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink() {
        getDriver().findElement(By.xpath("//a[contains(@href,'api')]")).click();

        return new RestApiPage(getDriver());
    }

    public boolean isMainPageOpen() {
        return getWait5().until(ExpectedConditions.titleContains("Dashboard [Jenkins]"));
    }

    public List<String> getJobList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<WebElement> getProjectsList() {

        return getProjectStatusTable().findElements(By.xpath("./tbody/tr"));
    }

    public List<String> getListOfProjectMenuItems(String jobName) {
        openJobDropDownMenu(jobName);
        List<WebElement> menus = getDriver().findElements(
                By.xpath("//div[@id = 'breadcrumb-menu' and @class = 'yui-module yui-overlay yuimenu visible']//li/a/span"));

        return TestUtils.getTexts(menus);
    }

    public String getOnlyProjectName() {
        return getProjectsList().get(0)
                .findElements(By.xpath("./td")).get(2)
                .getText();
    }

    public WebElement getMainPanel() {

        return getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")));
    }

    public WebElement getProjectStatusTable() {

        return getMainPanel().findElement(By.id("projectstatus"));
    }

    public WebElement getProjectName() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".job-status-nobuilt td>a>span"))));
    }

    public String getProjectNameMainPage(String projectName) {
        return getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//tr[@id='job_" + projectName + "']//a//span['" + projectName + "']")))
                .getText();
    }

    public WebElement getJobWebElement(String jobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//a[@href='job/" + jobName + "/']"))));
    }

    public String getJobName(String jobName) {
        return getDriver().findElement(By.xpath(String.format("//span[contains(text(),'%s')]", jobName))).getText();
    }

    public WebElement getFolderName() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".jenkins-table__link"))));
    }

    public WebElement getNoJobsMainPageHeader() {
        return getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1"));
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public WebElement getWelcomeWebElement() {
        return getDriver().findElement(By.xpath("//h1[text()='Welcome to Jenkins!']"));
    }

    public ManageNodesPage clickBuildExecutorStatus() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/computer/']"))).click();
        return new ManageNodesPage(getDriver());
    }

    public List<String> getListNamesOfJobs() {
        List<WebElement> listOfJobs = getDriver().findElements(By.xpath("//tr[contains(@id,'job_')]"));

        return TestUtils.getListNames(listOfJobs);
    }

    public MainPage clickSortByName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Name')]"))).click();
        return this;
    }
    public boolean verifyJobIsPresent(String jobName){
        List<WebElement> jobs = getDriver().findElements(By.xpath("//*[@class='jenkins-table__link model-link inside']"));
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
}

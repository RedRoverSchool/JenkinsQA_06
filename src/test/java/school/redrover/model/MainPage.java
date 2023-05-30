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
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        new Actions(getDriver()).moveToElement(getWait2()
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='job/" + jobName + "/']"))))
                .perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + jobName + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);
    }

    public WebElement getLogoutButton() {
        return getDriver().findElement(By.xpath("//a[@href='/logout']"));
    }

    public WebElement projectsTable() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//table[@id='projectstatus']"))));
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

    public WebElement getProjectName() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".job-status-nobuilt td>a>span"))));
    }

    public String getProjectNameMainPage(String projectName) {
        return getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//tr[@id='job_" + projectName + "']//a//span['" + projectName + "']")))
                .getText();
    }

    public WebElement getFolderName() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".jenkins-table__link"))));
    }

    public WebElement getJobWebElement(String jobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//span[contains(text(),'" + jobName + "')]"))));
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public ProjectPage navigateToProjectPage() {
        WebElement firstJobLink = getDriver().findElement(By.xpath("//td/a"));
        new Actions(getDriver()).moveToElement(firstJobLink).click(firstJobLink).perform();
        return new ProjectPage(getDriver());
    }

    public FolderPage clickFolderName(String FolderName) {
        new Actions(getDriver()).moveToElement(getJobWebElement(FolderName)).click(getJobWebElement(FolderName)).perform();
        return new FolderPage(getDriver());
    }

    public MainPage clickJobDropdownMenu(String jobName) {
        WebElement projectName = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + jobName + "/']")));
        Actions action = new Actions(getDriver());
        action.moveToElement(projectName).perform();
        projectName.click();
        return this;
    }

    public WebElement getNoJobsMainPageHeader() {
        return getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1"));
    }

    public ManageJenkinsPage navigateToManageJenkinsPage() {
        getDriver().findElement(By.cssSelector("[href='/manage']")).click();
        return new ManageJenkinsPage(getDriver());
    }

    public ConfigureGlobalSecurityPage navigateToConfigureGlobalSecurityPage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[text()='Configure Global Security']")).click();
        getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Configure Global Security']")));
        return new ConfigureGlobalSecurityPage(getDriver());
    }

    public PipelinePage clickPipelineProject(String pipelineName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='job/" + pipelineName + "/']"))).click();
        return new PipelinePage(getDriver());
    }

    public String getJobName(String jobName) {
        return getDriver().findElement(By.xpath(String.format("//span[contains(text(),'%s')]", jobName))).getText();
    }

    public FreestyleProjectPage clickFreestyleProjectName(String name) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();

        return new FreestyleProjectPage(getDriver());
    }

    public String getJobBuildStatusIcon(String name) {
        return getDriver().findElement(By.id(String.format("job_%s", name))).findElement(
                        By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']"))
                .getAttribute("tooltip");
    }

    public MainPage clickPlayBuildForATestButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//a[@href='job/New%20Builds/build?delay=0sec']")));
        return new MainPage(getDriver());
    }

    public BuildPage clickBuildsHistoryButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")));
        return new BuildPage(getDriver());
    }

    public ViewPage clickNewItemButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")));
        return new ViewPage(getDriver());
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return this;
    }

    public MainPage clickJobDropDownMenu(String name) {
        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'job/" + name + "/')]/button[@class='jenkins-menu-dropdown-chevron']")));
        chevron.sendKeys(Keys.RETURN);
        return this;
    }

    public MainPage clickOnSliderDashboardInDropDownMenu() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//div[@id = 'breadcrumbBar']//a"))).perform();

        WebElement slider = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'breadcrumbBar']//button")));
        new Actions(getDriver()).moveToElement(slider).perform();
        slider.sendKeys(Keys.RETURN);
        return this;
    }

    public NewJobPage clickNewItemInDashboardDropDownMenu() {
        getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@id = 'breadcrumb-menu-target']//span[text()='New Item']")))
                .click();
        return new NewJobPage(getDriver());
    }

    public MainPage getMultiConfigPage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".jenkins-table__link")))).click();
        return this;
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

    public MyViewsPage clickMyViewsSideMenuLink() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/me/my-views']"))).click();
        return new MyViewsPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink() {
        getDriver().findElement(By.xpath("//a[contains(@href,'api')]")).click();

        return new RestApiPage(getDriver());
    }

    public MainPage scrollToRestApiInFooter() {
        TestUtils.scrollToElementByJavaScript(this, getDriver().findElement(By.xpath("//a[contains(text(),'REST API')]")));
        return this;
    }

    public String getBackgroundColorNotificationIcon() {
        return getDriver().findElement(By.id("visible-am-button")).getCssValue("background-color");
    }

    public MainPage hoverOverAdminLink() {
        Actions act = new Actions(getDriver());
        WebElement adminLink = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".login>a.model-link")));
        act.moveToElement(adminLink).perform();
        return this;
    }

    public String getTextDecorationValue() {
        WebElement adminLink = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".login>a.model-link")));
        return adminLink.getCssValue("text-decoration");
    }

    public MainPage openAdminDropdownMenu() {
        WebElement dropDownMenu = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath
                ("//a[@href='/user/admin']/button")));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", dropDownMenu);
        return this;
    }

    public WebElement openTabFromAdminDropdownMenu(By buttonLocator, By pageLocator) {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(pageLocator));
    }

    public MultiConfigurationProjectPage clickJobWebElement(String jobName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//span[contains(text(),'" + jobName + "')]")))).click();
        return new MultiConfigurationProjectPage(getDriver());
    }

    public MainPage selectDashboard() {
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
    }

    public WebElement getLinkVersion() {
        return getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']"));
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/newView']"))).click();

        return new NewViewPage(getDriver());
    }

    public WebElement getWelcomeWebElement() {
        return getDriver().findElement(By.xpath("//h1[text()='Welcome to Jenkins!']"));
    }

    public ViewPage clickOnView(String viewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/']", viewName)))).click();

        return new ViewPage(getDriver());
    }

    public String getLogOutBtnColor() {
        return getLogoutButton().getCssValue("background-color");
    }

    public MainPage mouseOverLogOutBtn() {
        new Actions(getDriver()).moveToElement(getLogoutButton()).perform();
        return this;
    }

    public MainPage clickJobDropdownMenuBuildNow() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Build Now']"))).click();
        return this;
    }

    public MultiConfigurationProjectPage clickJobMultiConfigurationProject(String jobName) {
        WebElement job = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//a[@href='job/" + jobName + "/']"))));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(job).click().perform();
        return new MultiConfigurationProjectPage(getDriver());
    }

    public String getJobBuildStatus(String jobName) {
        WebElement buildStatus = getDriver().findElement(By.id(String.format("job_%s", jobName)))
                .findElement(By.xpath(".//*[name()='svg']"));
        return buildStatus.getAttribute("tooltip");
    }

    public FreestyleProjectPage getProjectNameClick() {
        getProjectName().click();
        return new FreestyleProjectPage(getDriver());
    }

    public MainPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/configure']")))).click();

        return this;
    }

    public List<String> getJobList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public ManageJenkinsPage clickManageJenkins() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();

        return new ManageJenkinsPage(getDriver());
    }

    public MainPage clickYesDeletePage() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public WebElement getMainPanel() {

        return getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")));
    }

    public WebElement getProjectStatusTable() {

        return getMainPanel().findElement(By.id("projectstatus"));
    }

    public List<WebElement> getProjectsList() {

        return getProjectStatusTable().findElements(By.xpath("./tbody/tr"));
    }

    public String getOnlyProjectName() {
        return getProjectsList().get(0)
                .findElements(By.xpath("./td")).get(2)
                .getText();
    }

    public List<String> getListOfProjectMenuItems() {
        List<WebElement> menus = getDriver().findElements(
                By.xpath("//div[@id = 'breadcrumb-menu' and @class = 'yui-module yui-overlay yuimenu visible']//li/a/span"));

        return TestUtils.getTexts(menus);
    }

    public MainPage returnToMainPage() {
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();
        return this;
    }

    public ManageJenkinsPage clickManageJenkinsOnDropDown() {
        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        getDriver().findElement(sectionNameLocator).click();
        return new ManageJenkinsPage(getDriver());
    }

    public MultibranchPipelinePage clickMultibranchPipeline(String multibranchPipeline) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/a[1]/span[1]"))).click();
        return new MultibranchPipelinePage(getDriver());
    }

    public FolderPage clickToOpenFolder(String folderName) {
        getDriver().findElement(By.xpath(String.format("//span[text()='%s']", folderName))).click();
        return new FolderPage(getDriver());
    }
    public WebElement expectedErrorMessage() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//div[@id='itemname-required']"))));
    }

    public BuildPage clickBuildButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='jenkins-table__cell--tight']"))).click();
        return new BuildPage(getDriver());
    }

    public boolean isMainPageOpen() {
        return getWait5().until(ExpectedConditions.titleContains("Dashboard [Jenkins]"));
    }

    public MultibranchPipelinePage clickMultibranchProjectName(String projectName) {
        new Actions(getDriver()).moveToElement(getJobWebElement(projectName)).click(getJobWebElement(projectName)).perform();
        return new MultibranchPipelinePage(getDriver());
    }
}

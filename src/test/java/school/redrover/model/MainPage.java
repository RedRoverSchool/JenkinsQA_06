package school.redrover.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.time.Duration;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement newItemButton;

    @FindBy(xpath = "//a[@href='job/New%20Builds/build?delay=0sec']")
    private WebElement playBuildForATestButton;

    @FindBy(xpath = "//a[@href='/view/all/builds']")
    private WebElement buildsHistoryButton;

    private void openJobDropDownMenu(String jobName) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        new Actions(getDriver()).moveToElement(getJobWebElement(jobName)).perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + jobName + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public NewJobPage clickNewItem() {
        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public NewJobPage clickCreateAJob(){
        WebElement createAJob = getDriver()
                .findElement(By.xpath("//div[@id='main-panel']//span[text() = 'Create a job']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(createAJob));
        createAJob.click();
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
        WebElement projectName = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + jobName + "/']")));
        Actions action = new Actions(getDriver());
        action.moveToElement(projectName).perform();
        projectName.click();
        return this;
    }

    public WebElement getNoJobsMainPageHeader() {
        return getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1"));
    }

    public MainPage selectJobDropdownMenuDelete() {
        //getDriver().findElement(By.xpath("//a[contains(@data-message, 'Delete')]")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@data-message, 'Delete')]"))).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return this;
    }

    public ManageJenkinsPage navigateToManageJenkinsPage() {
        getDriver().findElement(By.cssSelector("[href='/manage']")).click();
        return new ManageJenkinsPage(getDriver());
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
        click(playBuildForATestButton);
        return new MainPage(getDriver());
    }

    public BuildPage clickBuildsHistoryButton() {
        click(buildsHistoryButton);
        return new BuildPage(getDriver());
    }

    public ViewPage clickNewItemButton() {
        click(newItemButton);
        return new ViewPage(getDriver());
    }

    public MainPage clickOnProjectDropDownMenu(String projectName) {
        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'job/" + projectName + "/')]/button[@class='jenkins-menu-dropdown-chevron']")));
        chevron.sendKeys(Keys.RETURN);
        return this;
    }

    public MainPage selectDeleteFromDropDownMenu() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='first-of-type']/li[4]"))).click();
        return this;
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return this;
    }

    public MainPage clickDropDownMenuFolderName(String nameFolder) {
        WebElement chevron = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'job/" + nameFolder + "/')]/button[@class='jenkins-menu-dropdown-chevron']")));
        chevron.sendKeys(Keys.RETURN);
        return this;
    }

    public MainPage clickOnSliderDashboardInDropDownMenu(){
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//div[@id = 'breadcrumbBar']//a"))).perform();

        WebElement slider = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'breadcrumbBar']//button")));
        new Actions(getDriver()).moveToElement(slider).perform();
        slider.sendKeys(Keys.RETURN);
        return this;
    }

    public NewJobPage clickNewItemInDashboardDropDownMenu(){
        getWait2().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id = 'breadcrumb-menu-target']//span[text()='New Item']")))
                .click();
        return new NewJobPage(getDriver());
    }

    public MovePage selectMoveFromDropDownMenu() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='first-of-type']/li[6]"))).click();
        return new MovePage(getDriver());
    }

    public RenameProjectPage selectRenameJobDropDownMenu(String jobName){
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Rename')]"))).click();
        return new RenameProjectPage(getDriver());
    }

    public RestApiPage clickOnRestApiLink(){
        getDriver().findElement(By.xpath("//a[contains(@href,'api')]")).click();

        return new RestApiPage(getDriver());
    }

    public MovePage selectMoveJobDropDownMenu(String jobName){
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Move')]"))).click();
        return new MovePage(getDriver());

    }
}

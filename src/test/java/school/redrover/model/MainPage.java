package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public NewJobPage clickNewItem() {
        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public WebElement getProjectName() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".job-status-nobuilt td>a>span"))));
    }

    public WebElement getFolderName() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".jenkins-table__link"))));
    }

    public WebElement getJobInList(String jobName) {
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
        new Actions(getDriver()).moveToElement(getJobInList(FolderName)).click(getJobInList(FolderName)).perform();
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

    public MovePage selectMoveFromDropDownMenu() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='first-of-type']/li[6]"))).click();
        return new MovePage(getDriver());
    }
}

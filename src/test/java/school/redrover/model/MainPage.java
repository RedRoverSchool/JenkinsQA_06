package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.time.Duration;

public class MainPage extends BasePage {

    private void openJobDropDownMenu(String jobName) {
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        actions.moveToElement(getJobInList(jobName)).perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + jobName + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);

    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private void openJobDropDownMenu(String jobName) {
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        actions.moveToElement(getJobName(jobName)).perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + jobName + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);

    }

    public  NewJobPage clickNewItem() {
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

    public String getTitle(){
        return getDriver().getTitle();
    }

    public ProjectPage navigateToProjectPage() {
        WebElement firstJobLink = getDriver().findElement(By.xpath("//td/a"));
        new Actions(getDriver()).moveToElement(firstJobLink).click(firstJobLink).perform();
        return new ProjectPage(getDriver());
    }

    public FolderPage clickFolderName(String folderName){
        new Actions(getDriver()).moveToElement(getJobInList(folderName)).click(getJobInList(folderName)).perform();
        return new FolderPage(getDriver());
    }

    public MainPage clickJobDropdownMenu(String jobName){
        WebElement projectName = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + jobName + "/']")));
        //WebElement projectName = getDriver().findElement(By.xpath("//a[@href='job/" + jobName + "/']"));
        Actions action = new Actions(getDriver());
        action.moveToElement(projectName).perform();
        projectName.click();
        return this;
    }

    public WebElement getNoJobsMainPageHeader(){
        return getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1"));
    }
    public MainPage selectJobDropdownMenuDelete(){
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

    public RenameProjectPage selectJobDropDownMenuRename(String jobName){
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Rename')]"))).click();
        return new RenameProjectPage(getDriver());
    }

    public MainPage selectJobDropDownMenuMove(String jobName){
        openJobDropDownMenu(jobName);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Move')]"))).click();
        return this;
    }

    public String getJobName(String jobName) {
        return getDriver().findElement(By.xpath(String.format("//span[contains(text(),'%s')]", jobName))).getText();
    }
}

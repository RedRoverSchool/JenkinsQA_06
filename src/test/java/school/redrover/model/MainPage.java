package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
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

    public WebElement getJobName(String jobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//span[contains(text(),'" + jobName + "')]"))));
    }

    public ProjectPage navigateToProjectPage() {
        getDriver().findElement(By.cssSelector(".jenkins-table__link")).click();
        return new ProjectPage(getDriver());
    }

    public FolderPage clickFolderName(String FolderName){
        new Actions(getDriver()).moveToElement(getJobName(FolderName)).click(getJobName(FolderName)).perform();
        return new FolderPage(getDriver());
    }

    public ManageJenkinsPage navigateToManageJenkinsPage() {
        getDriver().findElement(By.cssSelector("[href='/manage']")).click();
        return new ManageJenkinsPage(getDriver());
    }

    public PipelinePage clickPipelineProject(String pipelineName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='job/" + pipelineName + "/']"))).click();
        return new PipelinePage(getDriver());
    }
}

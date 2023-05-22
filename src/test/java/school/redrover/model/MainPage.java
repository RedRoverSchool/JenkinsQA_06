package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@tooltip='Schedule a Build for New Builds']")
    private WebElement playBuildForATestButton;

    @FindBy(xpath = "//a[@href='/view/all/builds']")
    private WebElement buildsHistoryButton;

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement newItemButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ViewPage clickNewItemButton() {
        click(newItemButton);
        return new ViewPage(getDriver());
    }

    public  NewJobPage newItem() {
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

    public MainPage clickPlayBuildForATestButton() {
        click(playBuildForATestButton);
        return new MainPage(getDriver());
    }

    public BuildsPage clickBuildsHistoryButton() {
        click(buildsHistoryButton);
        return new BuildsPage(getDriver());
    }
}

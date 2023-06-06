package school.redrover.model;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.cssSelector;

public class FreestyleProjectPage extends BaseMainHeaderPage<FreestyleProjectPage> {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage selectBuildNow() {
        getDriver().findElement(cssSelector("[href*='build?']")).click();
        getWait5().until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[@class='build-row-cell']")));
        return this;
    }

    public BuildPage selectBuildItemTheHistoryOnBuildPage() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(cssSelector("[href$='console']"))).click();
        return new BuildPage(getDriver());
    }

    public FreestyleProjectPage clickTheDisableProjectButton() {
        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text() = 'Disable Project']"))).click();
        return this;
    }

    public FreestyleProjectPage clickTheEnableProjectButton() {
        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text() = 'Enable']"))).click();
        return this;
    }

    public String getDescription() {
        return getDriver().findElement(By.xpath("//*[@id='description']/div")).getText();
    }

    public FreestyleProjectPage clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        return this;
    }
    public FreestyleProjectPage clickEditDescription() {
        getDriver().findElement(By.xpath("//*[@href = 'editDescription']")).click();
        return this;
    }

    public FreestyleProjectPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button")).click();
        return this;
    }
    public FreestyleProjectPage addDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        return this;
    }

    public FreestyleProjectPage removeOldDescriptionAndAddNew (String description) {
        WebElement oldDescription = getDriver().findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"));
        oldDescription.clear();
        oldDescription.sendKeys(description);
        return this;
    }

    public MainPage navigateToMainPageViaJenkinsIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='jenkins-head-icon']"))).click();
        return new MainPage(getDriver());
    }

    public String  getWarningMessage() {

        return getDriver().findElement(By.id("enable-project")).getText().substring(0,34);
    }

    public boolean isProjectDisabledButtonDisplayed() {

        return getDriver().findElement(By.xpath("//*[@id='disable-project']/button")).isDisplayed();
    }

    public FreestyleProjectPage clickPreviewButton () {
        getDriver().findElement(By.xpath("//a[@class = 'textarea-show-preview']")).click();
        return this;
    }

    public String getPreviewDescription () {
        return getDriver().findElement(By.xpath("//*[@class = 'textarea-preview']")).getText();
    }

    public String getProjectName() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
    }

    public boolean isProjectEnableButtonDisplayed() {
        return getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text() = 'Enable']"))).isDisplayed();
    }

    public int getBuildsQuantity() {
        return Arrays.asList(getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[@class = 'build-row multi-line overflow-checked']")))).size();
    }

    public RenamePage<FreestyleProjectPage> clickRenameProject(String projectName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '/job/" + projectName + "/confirm-rename']"))).click();
        return new RenamePage<>(this);
    }

    public MainPage clickDeleteProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '#']//span[text() = 'Delete Project' ]"))).click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        return new MainPage(getDriver());
    }



    public ConsoleOutputPage openConsoleOutputForBuild(){
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='build-status-link']"))).click();
        return new ConsoleOutputPage(getDriver());
    }

    public FreestyleProjectPage clickSaveButton() {
        getDriver().findElement(By.name("Submit")).click();
        return new FreestyleProjectPage(getDriver());
    }

    public MovePage<FreestyleProjectPage> clickMoveOnSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/move']")))).click();
        return new MovePage<>(this);
    }

    public int getSizeOfPermalinksList() {
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2")));

        List<WebElement> permalinks = getDriver()
                .findElements(By.xpath("//ul[@class='permalinks-list']//li"));

        return permalinks.size();
    }
}

package school.redrover.model.jobs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.model.base.BaseProjectPage;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class FreestyleProjectPage extends BaseProjectPage<FreestyleProjectPage> {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectConfigPage clickConfigure() {
        setupClickConfigure();
        return new FreestyleProjectConfigPage(this);
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

    public String  getWarningMessage() {

        return getDriver().findElement(By.id("enable-project")).getText().substring(0,34);
    }

    public FreestyleProjectPage clickPreviewButton () {
        getDriver().findElement(By.xpath("//a[@class = 'textarea-show-preview']")).click();
        return this;
    }

    public String getPreviewDescription () {
        return getDriver().findElement(By.xpath("//*[@class = 'textarea-preview']")).getText();
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

    public int getSizeOfPermalinksList() {
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2")));

        List<WebElement> permalinks = getDriver()
                .findElements(By.xpath("//ul[@class='permalinks-list']//li"));

        return permalinks.size();
    }

    public MainPage clickDashboard() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Dashboard"))).click();
        return new MainPage(getDriver());
    }

    public ChangesPage<FreestyleProjectPage> clickChangeOnLeftSideMenu() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'changes')]"))).click();
        return new ChangesPage<>(this);
    }

    private void  openJobOnBreadcrumbBarDropDownMenu() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By
                .xpath("//a[@class='model-link' and contains(@href,'job')]"))).perform();

        WebElement options = getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@class='model-link' and contains(@href,'job')]/button")));
        new Actions(getDriver()).moveToElement(options).perform();
        options.sendKeys(Keys.RETURN);
    }

    public FreestyleProjectPage clickDeleteProjectOnDropDown() {
        openJobOnBreadcrumbBarDropDownMenu();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//a[@class='yuimenuitemlabel' and @href='#']/span[text()='Delete Project']/.."))).click();
        return this;
    }

    public FreestyleProjectPage dismissAlert() {
        getDriver().switchTo().alert().dismiss();
        return this;
    }
}

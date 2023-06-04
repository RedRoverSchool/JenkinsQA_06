package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class FolderPage extends BaseMainHeaderPage<FolderPage> {

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage configure() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(2)")).click();
        return this;
    }

    public NewJobPage newItem() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(3)")).click();
        return new NewJobPage(getDriver());
    }

    public DeleteFoldersPage delete() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(4)")).click();
        return new DeleteFoldersPage(getDriver());
    }

    public FolderPage people() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(5)")).click();
        return this;
    }

    public FolderPage buildHistory() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(6)")).click();
        return this;
    }

    public RenamePage<FolderPage> rename() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(7)")).click();
        return new RenamePage<>(this);
    }

    public FolderPage credentials() {
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(8)")).click();
        return this;
    }

    public NewViewFolderPage clickNewView() {
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        return new NewViewFolderPage(getDriver());
    }

    public FolderPage addDescription(String description) {
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='description']"))).sendKeys(description);
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        return this;
    }

    public NewJobPage newJob() {
        getDriver().findElement(By.cssSelector("[href='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//ol[@id='breadcrumbs']//li[1]")))).click();
        return new MainPage(getDriver());
    }

    public WebElement getHeading1() {
        return getDriver().findElement(By.xpath("//h1"));
    }

    public WebElement getMultibranchPipelineName() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".jenkins-table__link"))));
    }

    public MainPage clickDashboard() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']"))).click();
        return new MainPage(getDriver());
    }

    public String getNestedFolder(String nameFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nameFolder + "/')]"))).getText();
    }

    public String getFolderName() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='main-panel']/h1"))));
    }

    public String getOriginalFolderNameIfDisplayNameSet() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='main-panel'][contains(text(), 'Folder name:')]"))));
    }

    public String getFolderDescription() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("view-message"))));
    }

    public FolderConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/configure']")))).click();
        return new FolderConfigPage(new FolderPage(getDriver()));
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

    public boolean nestedFolderIsVisibleAndClickable(String nestedFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nestedFolder + "/')]"))).isDisplayed()
                && getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'job/" + nestedFolder + "/')]"))).isEnabled();
    }

    public MovePage<FolderPage> clickMoveOnSideMenu(String folderName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//span/a[@href='/job/%s/move']", folderName)))).click();
        return new MovePage<>(this);
    }

    public WebElement getNestedOrganizationFolder(String nameFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nameFolder + "/')]")));
    }

    public String getNestedMultiConfigurationProjectName(String name) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + name + "/')]"))).getText();
    }

    public WebElement getNestedPipelineProject(String pipelineName) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + pipelineName + "/')]")));
    }

    public String getLastCreatedItemName() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id = 'projectstatus']//td/a"))).getText();
    }

    public WebElement getInnerJobWebElement(String innerJobName) {
        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//span[contains(text(),'" + innerJobName + "')]"))));
    }

    public FolderPage clickInnerFolder(String innerFolderName) {
        new Actions(getDriver()).moveToElement(getInnerJobWebElement(innerFolderName)).click(getInnerJobWebElement(innerFolderName)).perform();
        return new FolderPage(getDriver());
    }

    public String getBreadcrumbText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//div[@id='breadcrumbBar']")))
                .getText()
                .replaceAll("\\n", " > ");
    }

    public String getNestedFreestyleProjectName(String name) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + name + "/')]"))).getText();
    }
}

package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class FolderPage extends BaseMainHeaderPage<FolderPage> {

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage configure(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(2)")).click();
        return this;
    }

    public NewJobPage newItem(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(3)")).click();
        return new NewJobPage(getDriver());
    }

    public DeleteFolderPage delete(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(4)")).click();
        return new DeleteFolderPage(getDriver());
    }

    public FolderPage people(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(5)")).click();
        return this;
    }

    public FolderPage buildHistory(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(6)")).click();
        return this;
    }

    public RenameFolderPage rename(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(7)")).click();
        return new RenameFolderPage(getDriver());
    }

    public FolderPage credentials(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(8)")).click();
        return this;
    }

    public NewViewFolderPage clickNewView(){
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        return new NewViewFolderPage(getDriver());
    }

    public FolderPage addDescription(String description){
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='description']"))).sendKeys(description);
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        return this;
    }

    public NewJobPage newJob(){
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

    public WebElement getNestedFolder(String nameFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nameFolder + "/')]")));
    }

    public String getFolderDisplayName() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='main-panel']/h1"))));
    }

    public String getFolderName() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='main-panel'][contains(text(), 'Folder name:')]"))));
    }
    public String getFolderDescription() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("view-message"))));
    }

    public FolderConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/configure']")))).click();
        return new FolderConfigPage(getDriver());
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
        return getNestedFolder(nestedFolder).isDisplayed() && getNestedFolder(nestedFolder).isEnabled();
    }

    public MovePage<FolderPage> clickMoveOnSideMenu(String folderName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//span/a[@href='/job/%s/move']", folderName)))).click();
        return new MovePage<>(this);
    }
}

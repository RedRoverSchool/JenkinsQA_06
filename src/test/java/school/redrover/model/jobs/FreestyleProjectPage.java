package school.redrover.model.jobs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.model.base.BaseProjectPage;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class FreestyleProjectPage extends BaseProjectPage<FreestyleProjectPage> {

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(xpath = "//*[@href = 'editDescription']")
    private WebElement editDescriptionButton;

    @FindBy(xpath = "//*[@id='description']/form/div[2]/button")
    private WebElement saveDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionTextArea;

    @FindBy(id = "enable-project")
    private WebElement warningMessage;

    @FindBy(xpath = "//a[@class = 'textarea-show-preview']")
    private WebElement previewButton;

    @FindBy(xpath = "//*[@class = 'textarea-preview']")
    private WebElement descriptionPreviewButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "//ul[@class='permalinks-list']//li")
    private List<WebElement> permalinks;

    @FindBy(css = "[href$='console']")
    private WebElement buildItem;

    @FindBy(xpath = "//button[text() = 'Disable Project']")
    private WebElement disableProjectButton;

    @FindBy(xpath = "//button[text() = 'Enable']")
    private WebElement enableProjectButton;

    @FindBy(xpath = "//*[@id='description']/div")
    private WebElement description;

    @FindBy(xpath = "//a[@href = '#']//span[text() = 'Delete Project']")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[@class='build-status-link']")
    private WebElement consoleOutputButton;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//a[contains(@href, 'changes')]")
    private WebElement changesButton;

    @FindBy(xpath = "//a[@class='model-link' and contains(@href,'job')]")
    private WebElement jobOnBreadcrumbBarDropDownMenuButton;

    @FindBy(xpath = "//a[@class='model-link' and contains(@href,'job')]/button")
    private WebElement optionsBreadcrumbBarDropDownMenuButton;

    @FindBy(xpath = "//a[@class='yuimenuitemlabel' and @href='#']/span[text()='Delete Project']/..")
    private WebElement deleteButtonOnDropDownMenu;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectConfigPage clickConfigure() {
        setupClickConfigure();
        return new FreestyleProjectConfigPage(this);
    }

    public BuildPage selectBuildItemTheHistoryOnBuildPage() {
        getWait10().until(ExpectedConditions.visibilityOf(buildItem)).click();
        return new BuildPage(getDriver());
    }

    public FreestyleProjectPage clickTheDisableProjectButton() {
        getWait5().until(ExpectedConditions
                .elementToBeClickable(disableProjectButton)).click();
        return this;
    }

    public FreestyleProjectPage clickTheEnableProjectButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(enableProjectButton)).click();
        return this;
    }

    public String getDescription() {
        return description.getText();
    }

    public FreestyleProjectPage clickAddDescription() {
        addDescriptionButton.click();
        return this;
    }

    public FreestyleProjectPage clickEditDescription() {
        editDescriptionButton.click();
        return this;
    }

    public FreestyleProjectPage clickSaveDescription() {
        saveDescriptionButton.click();
        return this;
    }

    public FreestyleProjectPage addDescription(String description) {
        descriptionTextArea.sendKeys(description);
        return this;
    }

    public FreestyleProjectPage removeOldDescriptionAndAddNew (String description) {
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys(description);
        return this;
    }

    public String  getWarningMessage() {
        return warningMessage.getText().substring(0,34);
    }

    public FreestyleProjectPage clickPreviewButton () {
        previewButton.click();
        return this;
    }

    public String getPreviewDescription () {
        return descriptionPreviewButton.getText();
    }

    public MainPage clickDeleteProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        return new MainPage(getDriver());
    }

    public ConsoleOutputPage openConsoleOutputForBuild(){
        getWait5().until(ExpectedConditions.elementToBeClickable(consoleOutputButton)).click();
        return new ConsoleOutputPage(getDriver());
    }

    public FreestyleProjectPage clickSaveButton() {
        saveButton.click();
        return new FreestyleProjectPage(getDriver());
    }

    public int getSizeOfPermalinksList() {
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2")));
        return permalinks.size();
    }

    public MainPage clickDashboard() {
        getWait2().until(ExpectedConditions.visibilityOf(dashboardButton)).click();
        return new MainPage(getDriver());
    }

    public ChangesPage<FreestyleProjectPage> clickChangeOnLeftSideMenu() {
        getWait10().until(ExpectedConditions.visibilityOf(changesButton)).click();
        return new ChangesPage<>(this);
    }

    private void  openJobOnBreadcrumbBarDropDownMenu() {
        new Actions(getDriver()).moveToElement(jobOnBreadcrumbBarDropDownMenuButton).perform();

        WebElement options = getWait2().until(
                ExpectedConditions.elementToBeClickable(optionsBreadcrumbBarDropDownMenuButton));
        new Actions(getDriver()).moveToElement(options).perform();
        options.sendKeys(Keys.RETURN);
    }

    public FreestyleProjectPage clickDeleteProjectOnDropDown() {
        openJobOnBreadcrumbBarDropDownMenu();
        getWait2().until(ExpectedConditions.visibilityOf(deleteButtonOnDropDownMenu)).click();
        return this;
    }

    public FreestyleProjectPage dismissAlert() {
        getDriver().switchTo().alert().dismiss();
        return this;
    }
}

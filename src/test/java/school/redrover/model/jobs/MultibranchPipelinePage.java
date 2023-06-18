package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.jobsconfig.MultibranchPipelineConfigPage;
import school.redrover.model.RenamePage;
import school.redrover.model.base.BaseJobPage;

public class MultibranchPipelinePage extends BaseJobPage<MultibranchPipelinePage> {

    @FindBy(xpath = "//ol[@id='breadcrumbs']//li[1]")
    private WebElement breadcrumbsButton;

    @FindBy(xpath = "//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[8]/span[1]/a[1]")
    private WebElement renameButton;

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement descriptionMessage;

    @FindBy(xpath = "//form[@method='post']")
    private WebElement disableMessage;

    @FindBy(xpath = "//*[@id='description']/div[1]")
    private WebElement descriptionEmpty;

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement displayedName;

    @FindBy(xpath = "//h1/img")
    private WebElement iconDisplayed;

    @FindBy(xpath = "(//*[name()='svg'][@title='Folder'])[1]")
    private WebElement iconDisplayedDefault;

    @FindBy(xpath = "//span/a[contains(@href, 'configure')]")
    private WebElement configureSideMenu;

    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchPipelineConfigPage clickConfigure() {

        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(breadcrumbsButton)).click();

        return new MainPage(getDriver());
    }

    public RenamePage<MultibranchPipelinePage> renameMultibranchPipelinePage () {
        renameButton.click();

        return new RenamePage<>(this);
    }
  
    public String getDescription() {

        return getWait5().until(ExpectedConditions.visibilityOf(descriptionMessage)).getText();
    }

    public MultibranchPipelineConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.visibilityOf(configureSideMenu)).click();

        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    public String getTextFromDisableMessage() {

        return getWait5().until(ExpectedConditions.visibilityOf(disableMessage)).getText();
    }

    public String getDisplayedName() {

        return getWait5().until(ExpectedConditions.visibilityOf(displayedName)).getText().trim();
    }

    public boolean defaultIconIsDisplayed() {

        return getWait5().until(ExpectedConditions.visibilityOf(iconDisplayedDefault)).isDisplayed();
    }

    public boolean metadataFolderIconIsDisplayed() {

        return getWait5().until(ExpectedConditions.visibilityOf(iconDisplayed)).isDisplayed();
    }

    public boolean isDescriptionEmpty(){

        return descriptionEmpty.getText().isEmpty();
    }
}

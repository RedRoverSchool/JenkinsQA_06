package school.redrover.model.Jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.JobsConfig.MultibranchPipelineConfigPage;
import school.redrover.model.base.BaseJobPage;

public class MultibranchPipelinePage extends BaseJobPage<MultibranchPipelinePage> {

    @FindBy(xpath = "//ol[@id='breadcrumbs']//li[1]")
    private WebElement breadcrumbsButton;

    @FindBy(xpath = "//form[@method='post']")
    private WebElement disableMessage;

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
        setupClickConfigure();
        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement descriptionMessage;

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(breadcrumbsButton)).click();

        return new MainPage(getDriver());
    }

    public MultibranchPipelineConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.visibilityOf(configureSideMenu)).click();

        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    public String getTextFromDisableMessage() {

        return getWait5().until(ExpectedConditions.visibilityOf(disableMessage)).getText();
    }

    public boolean defaultIconIsDisplayed() {

        return getWait5().until(ExpectedConditions.visibilityOf(iconDisplayedDefault)).isDisplayed();
    }

    public boolean metadataFolderIconIsDisplayed() {

        return getWait5().until(ExpectedConditions.visibilityOf(iconDisplayed)).isDisplayed();
    }

    public String getDescriptionFromConfig() {

        return descriptionMessage.getText();
    }
}

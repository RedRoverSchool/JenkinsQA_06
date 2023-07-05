package school.redrover.model.base;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;

import java.util.List;

public abstract class BaseProjectPage<Self extends BaseProjectPage<?>> extends BaseJobPage<Self> {

    @FindBy(xpath = "//a[contains(@href, 'changes')]")
    private WebElement changesButton;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//form[@id='disable-project']/button")
    private WebElement disableButton;

    @FindBy(xpath = "//form[@id='enable-project']/button")
    private WebElement enableButton;

    @FindBy(css = "form#enable-project")
    private WebElement disabledMessage;

    @FindBy(css = "[href*='build?']")
    private WebElement buildNowButton;

    @FindBy(xpath = "//td[@class='build-row-cell']")
    private WebElement buildRowCell;

    @FindBy(xpath = "//h2[text()='Permalinks']")
    private WebElement permalinks;

    @FindBy(xpath = "//ul[@class='permalinks-list']//li")
    private List<WebElement> permalinksList;

    @FindBy(xpath = "//a[text()='trend']")
    private WebElement trend;

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public ChangesPage<Self> clickChangeOnLeftSideMenu() {
        getWait10().until(ExpectedConditions.visibilityOf(changesButton)).click();
        return new ChangesPage<>((Self)this);
    }

    public MainPage clickDeleteAndAccept() {
        getWait2().until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        getDriver().switchTo().alert().accept();
        return new MainPage(getDriver());
    }

    public Self clickDeleteAndCancel() {
        getWait2().until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        getDriver().switchTo().alert().dismiss();
        return (Self)this;
    }

    public Self clickDisable() {
        disableButton.click();
        return (Self)this;
    }

    public Self clickEnable() {
        getWait5().until(ExpectedConditions.elementToBeClickable(enableButton)).click();
        return (Self)this;
    }

    public String getDisableButtonText() {
        return disableButton.getText();
    }

    public String getEnableButtonText() {
        return enableButton.getText();
    }

    public String getDisabledMessageText(){
        return disabledMessage.getText().trim().substring(0, 34);
    }

    public Self clickBuildNowFromSideMenu() {
        buildNowButton.click();
        getWait10().until(ExpectedConditions.visibilityOf(buildRowCell));
        return (Self)this;
    }

    public BuildWithParametersPage<Self> clickBuildWithParameters() {
        buildNowButton.click();
        return new BuildWithParametersPage<>((Self) this);
    }

    public ConsoleOutputPage clickIconBuildOpenConsoleOutput(int buildNumber) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/" + buildNumber +  "/console')]"))).click();
        return new ConsoleOutputPage(getDriver());
    }

    public BuildPage clickNumberBuild(int buildNumber) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text() ,'#" + buildNumber +  "')]"))).click();
        return new BuildPage(getDriver());
    }

    public int getSizeOfPermalinksList() {
        getWait2().until(ExpectedConditions.visibilityOf(permalinks));
        return permalinksList.size();
    }

    public TimelinePage clickTrend() {
        trend.click();
        return new TimelinePage(getDriver());
    }
}

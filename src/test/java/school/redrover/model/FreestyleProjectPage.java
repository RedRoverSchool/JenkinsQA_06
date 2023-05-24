package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.Arrays;

import static org.openqa.selenium.By.cssSelector;

public class FreestyleProjectPage extends BasePage {

    @FindBy(xpath = "//*[@id='description']/div")
    private WebElement description;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(id = "enable-project")
    private WebElement warningMessage;

    @FindBy(xpath = "//*[@id='disable-project']/button")
    private WebElement projectDisabledButton;

    @FindBy(xpath = "//*[@id='jenkins-head-icon']")
    private WebElement jenkinsIconButton;


    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public FreestyleProjectPage selectBuildNow() {
        getDriver().findElement(cssSelector("[href*='build?']")).click();
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
        return description.getText();
    }

    public FreestyleProjectConfigPage clickAddDescription() {
        addDescriptionButton.click();
        return new FreestyleProjectConfigPage(getDriver());
    }

    public MainPage navigateToMainPageViaJenkinsIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(jenkinsIconButton)).click();
        return new MainPage(getDriver());
    }

    public String getWarningMessage() {
        return warningMessage.getText().substring(0, warningMessage.getText().indexOf("\n"));
    }

    public boolean isProjectDisabledButtonDisplayed() {

        return projectDisabledButton.isDisplayed();
    }

    public MainPage clickDashboard() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard"))).click();
        return new MainPage(getDriver());
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

    public RenameFreestyleProjectPage clickRenameProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '/job/Freestyle/confirm-rename']"))).click();
        return new RenameFreestyleProjectPage(getDriver());
    }
}

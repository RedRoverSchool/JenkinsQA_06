package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;
import school.redrover.model.jobs.FreestyleProjectPage;

public class BuildPage extends BaseMainHeaderPage<BuildPage> {

    @FindBy(xpath = "//span[@class='build-status-icon__outer']//*[local-name()='svg']")
    private WebElement greenIconV;

    @FindBy(xpath = "//h1")
    private WebElement buildHeader;

    @FindBy(xpath = "//div[@class='jenkins-form-description']")
    private WebElement description;

    @FindBy(xpath = "(//tr[@class='app-summary']/td//span)[1]")
    private WebElement buildInfo;

    @FindBy(name = "Submit")
    private WebElement deleteBuildButton;

    @FindBy(xpath = "//span[contains(text(), 'Console Output')]/..")
    private WebElement consoleOutputButton;

    @FindBy(xpath = "//span[text()='Edit Build Information']/..")
    private WebElement editBuildInformation;

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getBuildHeader() {
        return buildHeader;
    }

    public boolean isDisplayedGreenIconV() {

        return getWait5().until(ExpectedConditions.visibilityOf(greenIconV)).isDisplayed();
    }

    public boolean isDisplayedBuildTitle() {

        return getBuildHeader().getText().contains("Build #1");
    }

    public String getBuildInfo() {
        return buildInfo.getText().substring(0, buildInfo.getText().length() - 38);
    }

    public <JobTypePage extends BasePage<?, ?>> DeletePage<JobTypePage> clickDeleteBuild(JobTypePage jobTypePage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteBuildButton)).click();
        return new DeletePage<>(jobTypePage);
    }

    public ConsoleOutputPage clickConsoleOutput() {
        consoleOutputButton.click();
        return new ConsoleOutputPage(getDriver());
    }

    public EditBuildInformationPage clickEditBuildInformation() {
        getWait5().until(ExpectedConditions.elementToBeClickable(editBuildInformation)).click();

        return new EditBuildInformationPage(getDriver());
    }

    public String getBuildHeaderText() {

        return getWait5().until(ExpectedConditions.visibilityOf(buildHeader)).getText();
    }

    public FreestyleProjectPage deleteBuild() {
        deleteBuildButton.click();
        return new FreestyleProjectPage(getDriver());
    }
}

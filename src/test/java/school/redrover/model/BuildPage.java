package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class BuildPage extends BaseMainHeaderPage<BuildPage> {

    @FindBy(xpath = "//span[@class='build-status-icon__outer']//*[local-name()='svg']")
    private WebElement greenIconV;

    @FindBy(xpath = "//h1")
    private WebElement buildHeader;

    @FindBy(xpath = "//div[@class='jenkins-form-description']")
    private WebElement description;

    @FindBy(xpath = "//span[contains(text(), 'Delete build')]/..")
    private WebElement deleteBuildButton;

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

    public <JobTypePage extends BasePage<?, ?>> DeletePage<JobTypePage> clickDeleteBuild(JobTypePage jobTypePage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteBuildButton)).click();
        return new DeletePage<>(jobTypePage);
    }
}

package school.redrover.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class RenamePage <JobTypePage extends BasePage<?, ?>> extends BaseMainHeaderPage<RenamePage<JobTypePage>> {

    @FindBy(name = "newName")
    private WebElement newName;

    @FindBy(name = "Submit")
    private WebElement renameButton;

    @FindBy(css = ".error")
    private WebElement errorMessage;
    private final JobTypePage jobTypePage;

    public RenamePage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public RenamePage<JobTypePage> enterNewName(String name) {
        newName.clear();
        newName.sendKeys(name);
        return this;
    }

    public JobTypePage clickRenameButton() {
        renameButton.click();
        return jobTypePage;
    }

    public String getErrorMessage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(errorMessage)).click();
        return getWait10().until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public CreateItemErrorPage clickRenameButtonAndGoError() {
        renameButton.click();
        return new CreateItemErrorPage(getDriver());
    }
}

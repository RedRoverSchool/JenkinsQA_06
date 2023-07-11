package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;


public class NewJobPage extends BaseMainHeaderPage<NewJobPage> {

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement itemName;

    @FindBy(id = "itemname-required")
    private WebElement itemNameRequiredMessage;

    @FindBy(id = "itemname-invalid")
    private WebElement itemInvalidNameMessage;

    @FindBy(xpath = "//label[@class = 'h3']")
    private WebElement title;

    private TestUtils.JobType jobType;

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    public TestUtils.JobType getJobType() {
        return jobType;
    }

    public boolean isOkButtonEnabled() {
        return getWait5().until(ExpectedConditions.visibilityOf(okButton)).isEnabled();
    }

    public NewJobPage enterItemName(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOf(itemName)).sendKeys(jobName);
        return this;
    }

    public NewJobPage selectJobType(TestUtils.JobType jobType) {
        getDriver().findElement(jobType.getLocator()).click();
        this.jobType = jobType;
        return this;
    }

    public <JobConfigPage extends BaseConfigPage<?, ?>> JobConfigPage clickOkButton(JobConfigPage jobConfigPage) {
        getWait5().until(ExpectedConditions.visibilityOf(okButton)).click();
        return jobConfigPage;
    }

    public CreateItemErrorPage selectJobAndOkAndGoError(TestUtils.JobType jobType) {
        selectJobType(jobType);
        clickOkButton(null);
        return new CreateItemErrorPage(getDriver());
    }

    public String getItemInvalidMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(getItemInvalidNameMessage())).getText();
    }

    public String getItemNameRequiredMessage() {
        return itemNameRequiredMessage.getText();
    }

    private WebElement getItemInvalidNameMessage() {
        return itemInvalidNameMessage;
    }

    public String getItemNameRequiredErrorText() {
        return getWait2().until(ExpectedConditions.visibilityOf(itemNameRequiredMessage)).getText();
    }

    public String getTitle() {
        return getWait2().until(ExpectedConditions.visibilityOf(title)).getText();
    }

    public CreateBugPage selectJobAndOkAndGoToBugPage(TestUtils.JobType jobType) {
        selectJobType(jobType);
        clickOkButton(null);
        return new CreateBugPage(getDriver());
    }
}

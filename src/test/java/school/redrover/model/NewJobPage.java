package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.List;

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

    @FindBy(css = "label > span")
    private List<WebElement> listOfNewItems;

    @FindBy(xpath = "//*[@id='from']")
    private WebElement itemNameToPlaceHolder;

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getOkButton() {
        return getWait5().until(ExpectedConditions.visibilityOf(okButton));
    }

    public boolean okButtonDisabled() {
        return getOkButton().getAttribute("disabled").isEmpty();
    }

    public boolean okButtonIsEnabled() {
        return getOkButton().isEnabled();
    }

    public NewJobPage enterItemName(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOf(itemName)).sendKeys(jobName);
        return this;
    }

    public NewJobPage selectJobType(TestUtils.JobType jobType) {
        getDriver().findElement(jobType.getLocator()).click();
        return this;
    }

    public <JobConfigPage extends BaseConfigPage<?, ?>> JobConfigPage clickOkButton(JobConfigPage jobConfigPage) {
        getOkButton().click();
        return jobConfigPage;
    }

    public CreateItemErrorPage selectJobAndOkAndGoError(TestUtils.JobType jobType) {
        selectJobType(jobType);
        clickOkButton(null);
        return new CreateItemErrorPage(getDriver());
    }

    public String getItemInvalidMessage() {
        return getWait2().until(ExpectedConditions.visibilityOf(getItemInvalidNameMessage())).getText();
    }

    public boolean isOkButtonEnabled() {
        return getOkButton().isEnabled();
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

    public List<String> getListOfNewItems() {
        List<String> newList = new java.util.ArrayList<>(List.of());
        for (int i = 0; i < listOfNewItems.size(); i++) {
            newList.add(listOfNewItems.get(i).getText());
        }
        return newList;
    }

    public NewJobPage enterItemNameToPlaceHolder(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOf(itemNameToPlaceHolder)).sendKeys(jobName);
        return this;
    }
}

package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class   NewJobPage extends BaseMainHeaderPage<NewJobPage> {

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getOkButton() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//button[@id='ok-button']")));
    }

    public boolean okButtonDisabled(){
        return getOkButton().getAttribute("disabled").isEmpty();
    }
    public boolean okButtonIsEnabled(){
        return getOkButton().isEnabled();
    }

    public NewJobPage enterItemName(String jobName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(jobName);
        return this;
    }

    public NewJobPage selectJobType(TestUtils.JobType jobType) {
        getDriver().findElement(jobType.getLocator()).click();
        return this;
    }

    public <JobConfigPage extends BaseConfigPage<?,?>> JobConfigPage clickOkButton(JobConfigPage jobConfigPage) {
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
        return getDriver().findElement(By.id("itemname-required")).getText();
    }

    private WebElement getItemInvalidNameMessage() {
        return getDriver().findElement(By.id("itemname-invalid"));
    }

    public String getItemNameRequiredErrorText() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required"))).getText();
    }

    public String getTitle() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//label[@class = 'h3']"))).getText();
    }

    public List<String> getListOfNewItems() {
        List<WebElement> listOfNewItems = getDriver().findElements(By.cssSelector("label > span"));
        List<String> newList = new java.util.ArrayList<>(List.of());
        for(int i = 0; i< listOfNewItems.size(); i++) {
            newList.add(listOfNewItems.get(i).getText());
        }
        return newList;
    }

    public NewJobPage enterItemNameToPlaceHolder(String jobName){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='from']"))).sendKeys(jobName);
        return this;
    }
}

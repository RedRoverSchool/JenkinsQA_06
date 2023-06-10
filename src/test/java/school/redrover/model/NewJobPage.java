package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.List;

public class NewJobPage extends BaseMainHeaderPage<NewJobPage> {

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

    public NewJobPage selectTypeJob(int jobTypeByNumber) {
        List<WebElement> jobs = getDriver().findElements(By.cssSelector("#items>div>ul>li"));
        jobs.get(jobTypeByNumber - 1).click();
        return this;
    }

    public <JobConfigPage extends BaseConfigPage<?,?>>JobConfigPage selectTypeJobAndOk(int jobTypeByNumber, JobConfigPage jobConfigPage) {
        selectTypeJob(jobTypeByNumber);
        getOkButton().click();
        return jobConfigPage;
    }

    public CreateItemErrorPage selectJobAndOkAndGoError(int jobTypeByNumber, BaseConfigPage<?,?> jobConfigPage) {
        selectTypeJobAndOk(jobTypeByNumber, jobConfigPage);
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
}

package school.redrover.model.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseConfigPage<Self extends BaseConfigPage<?, ?>, JobPage extends BaseMainHeaderPage<?>> extends BaseMainHeaderPage<Self> {

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[contains(@name, 'description')]")
    private WebElement descriptionTextBox;

    @FindBy(xpath = "//a[contains(@previewendpoint, 'previewDescription')]")
    private WebElement preview;

    @FindBy(xpath = "//div[@class='textarea-preview']")
    private WebElement previewTextarea;

    @FindBy(xpath = "//h1")
    private WebElement title;

    private final JobPage jobPage;

    public BaseConfigPage(JobPage jobPage) {
        super(jobPage.getDriver());
        this.jobPage = jobPage;
    }

    protected JobPage getJobPage() {
        return jobPage;
    }

    public JobPage clickSaveButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return getJobPage();
    }

    public String getDescription() {
        return descriptionTextBox.getText();
    }

    public Self addDescription(String description) {
        descriptionTextBox.sendKeys(description);
        return (Self) this;
    }

    public Self clickPreview() {
        preview.click();
        return (Self) this;
    }

    public String getPreviewText() {
        return previewTextarea.getText();
    }

    public Self clearDescriptionArea() {
        descriptionTextBox.clear();
        return (Self) this;
    }

    public String getTitle(){
        return title.getText();
    }
}

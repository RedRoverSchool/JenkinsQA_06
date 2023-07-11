package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class EditBuildInformationPage extends BaseMainHeaderPage<EditBuildInformationPage> {

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement buildDescriptionTextArea;

    @FindBy(xpath = "//*[@name = 'Submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@name='displayName']")
    private WebElement displayNameField;

    @FindBy(xpath = "//a[text()='Preview']")
    private WebElement previewButton;

    @FindBy(xpath = "//div[@class=\"textarea-preview\"]")
    private WebElement previewTextarea;

    public EditBuildInformationPage(WebDriver driver) {
        super(driver);
    }

    public BuildPage clickSaveButton() {
        saveButton.click();

        return new BuildPage(getDriver());
    }

    public EditBuildInformationPage enterDisplayName(String displayName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(displayNameField))
                .sendKeys(displayName);
        return this;
    }

    public EditBuildInformationPage enterDescription(String description) {
        getWait5().until(ExpectedConditions.visibilityOf(buildDescriptionTextArea)).clear();
        buildDescriptionTextArea.sendKeys(description);

        return this;
    }

    public EditBuildInformationPage clickPreviewButton() {
        previewButton.click();

        return this;
    }

    public String getPreviewText() {

        return getWait5().until(ExpectedConditions.visibilityOf(previewTextarea)).getText();
    }
}

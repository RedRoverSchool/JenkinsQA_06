package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class EditBuildInformationPage extends BaseMainHeaderPage<EditBuildInformationPage> {

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement buildDescriptionTextArea;

    @FindBy(xpath = "//*[@name = 'Submit']")
    private WebElement saveButton;

    public EditBuildInformationPage(WebDriver driver) {
        super(driver);
    }

    public EditBuildInformationPage editBuildDescription(String newDescription) {
        buildDescriptionTextArea.clear();
        buildDescriptionTextArea.sendKeys(newDescription);

        return this;
    }

    public BuildPage clickSaveButton() {
        saveButton.click();

        return new BuildPage(getDriver());
    }
}

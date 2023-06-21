package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class CreateNodePage extends BaseMainHeaderPage<CreateNodePage> {

    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputName;

    @FindBy(xpath = "//textarea[@name='nodeDescription']")
    private WebElement descriptionTextarea;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    public CreateNodePage(WebDriver driver) {
        super(driver);
    }

    public CreateNodePage clearNameField() {
        getWait2().until(ExpectedConditions.elementToBeClickable(inputName)).clear();
        return this;
    }

    public ErrorNodePage clickSaveButtonWhenNameFieldEmpty() {
        getWait2().until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return new ErrorNodePage(getDriver());
    }

    public ManageNodesPage clickSaveButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return new ManageNodesPage(getDriver());
    }

    public CreateNodePage addDescription(String description) {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(descriptionTextarea)).sendKeys(description);
        return this;
    }
}

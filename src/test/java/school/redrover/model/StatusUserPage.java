package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StatusUserPage extends MainPage {

    @FindBy(xpath = "//a[@href='description-link/']")
    private WebElement editDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionInputField;

    @FindBy(id = "description-link")
    private WebElement addDescriptionLink;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement displayedDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement displayedDescriptionText;

    @FindBy(xpath = "//div[@id='main-panel']/div[contains(text(), 'ID')]")
    private WebElement userID;

    public StatusUserPage(WebDriver driver) {
        super(driver);
    }

    public StatusUserPage clickAddDescriptionLink() {
        addDescriptionLink.click();

        return this;
    }

    public StatusUserPage clearDescriptionInputField() {
        getWait10().until(ExpectedConditions.visibilityOf(descriptionInputField));
        descriptionInputField.clear();

        return this;
    }

    public StatusUserPage setDescription(String text) {
        descriptionInputField.sendKeys(text);

        return this;
    }

    public StatusUserPage clickSaveButton() {
        saveButton.click();

        return this;
    }

    public String getDescription() {

        return displayedDescription.getText();
    }

    public String getDescriptionText() {

        return displayedDescriptionText.getText();
    }

    public String getUserIDText() {

        return userID.getText();
    }

    public String getUserIDNameText() {

        return userID.getText();
    }
}

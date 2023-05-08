package school.redrover;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.base_abstract.BasePage;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement newItemButton;
    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement stringSearchItemName;
    @FindBy(xpath = "//span[text() = 'Multibranch Pipeline']")
    private WebElement multibranchPipelineButton;
    @FindBy(xpath = "//button[@id = 'ok-button']")
    private WebElement saveButton;
    @FindBy(xpath = "//input[@name = '_.displayNameOrNull']")
    private WebElement displayNameField;
    @FindBy(xpath = "//textarea[@name = '_.description']")
    private WebElement descriptionField;
    @FindBy(xpath = "//div[@id='main-panel']//h1")
    private WebElement multibranchPipelineDisplayName;

    protected MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickNewItemButton() {
        verifyElementToBeVisible(newItemButton);
        verifyElementToBeClickable(newItemButton).click();
        return this;
    }


    public MainPage inputAnItemName(String text) {
        input(text, stringSearchItemName);

        return this;
    }

    public MainPage clickMultibranchPipeline() {
        verifyElementToBeVisible(multibranchPipelineButton);
        verifyElementToBeClickable(multibranchPipelineButton).click();
        return this;
    }

    public MainPage clickOkButton() {
        verifyElementToBeVisible(saveButton);
        verifyElementToBeClickable(saveButton).click();
        return this;
    }

    public void enterAnItemNameTest() {
        stringSearchItemName.sendKeys("Test");
    }

    public void enterValueInDescriptionField() {
        verifyElementToBeClickable(descriptionField).click();
        descriptionField.sendKeys("Test");
    }

    public MainPage enterValueInDisplayNameField() {
        verifyElementToBeClickable(displayNameField).click();
        displayNameField.sendKeys("Test", Keys.ENTER);
        return this;
    }

    public String getNameMultibranchPipelineDisplayNameText() {
       return multibranchPipelineDisplayName.getText();
    }
}

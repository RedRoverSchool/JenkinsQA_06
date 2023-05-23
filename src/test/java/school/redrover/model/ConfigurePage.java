package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class ConfigurePage extends BasePage {
    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//label[normalize-space()='Build after other projects are built']")
    private WebElement buildTriggerCheckBox;

    @FindBy(xpath = "//div[@id='workflow-editor-1']//textarea")
    private WebElement areContentInputString;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//label[normalize-space()='Throttle builds']")
    private WebElement buildTriggersSection;

    @FindBy(xpath = "//div[@id='pipeline']")
    private WebElement pipelineSection;

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    public ConfigurePage sendAreDescriptionInputString(String text) {
        sendTextToInput(descriptionField, text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        click(buildTriggerCheckBox);
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {
        clickByJavaScript(areContentInputString);
        areContentInputString.sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {
        scrollToElementByJavaScript(buildTriggersSection);
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {
        scrollToElementByJavaScript(pipelineSection);
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton() {
        click(saveButton);
        return new JobPage(getDriver());
    }
}

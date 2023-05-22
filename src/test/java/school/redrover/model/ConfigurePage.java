package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    public ProjectPage saveConfigurePageAndGoToProjectPage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        return new ProjectPage(getDriver());
    }
}

package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class ConfigurePage extends BasePage {

    private static final By descriptionField = By.xpath("//textarea[@name='description']");
    private static final By buildTriggerCheckBox = By.xpath("//label[normalize-space()='Build after other projects are built']");
    private static final By areContentInputString = By.xpath("//div[@id='workflow-editor-1']//textarea");
    private static final By saveButton = By.xpath("//button[@name='Submit']");
    private static final By buildTriggersSection = By.xpath("//label[normalize-space()='Throttle builds']");
    private static final By pipelineSection = By.xpath("//div[@id='pipeline']");

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    public ConfigurePage sendAreDescriptionInputString(String text) {
        sendTextToInput((WebElement) descriptionField, text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        click((WebElement) buildTriggerCheckBox);
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {
        clickByJavaScript((WebElement) areContentInputString);
        ((WebElement) areContentInputString).sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {
        scrollToElementByJavaScript((WebElement) buildTriggersSection);
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {
        scrollToElementByJavaScript((WebElement) pipelineSection);
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton() {
        click((WebElement) saveButton);
        return new JobPage(getDriver());
    }
}


package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class ConfigurePage extends BasePage {

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    private WebElement getDescriptionField() {
        return getDriver().findElement(By.xpath("//textarea[@name='description']"));
    }

    private WebElement getBuildTriggerCheckBox() {
        return getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']"));
    }

    private WebElement getAreContentInputString() {
        return getDriver().findElement(By.xpath("//div[@id='workflow-editor-1']//textarea"));
    }

    private WebElement getSaveButton() {
        return getDriver().findElement(By.xpath("//button[@name='Submit']"));
    }

    private WebElement getBuildTriggersSection() {
        return getDriver().findElement(By.xpath("//label[normalize-space()='Throttle builds']"));
    }

    private WebElement getPipelineSection() {
        return getDriver().findElement(By.xpath("//div[@id='pipeline']"));
    }

    public ConfigurePage sendAreDescriptionInputString(String text) {
        sendTextToInput(getDescriptionField(), text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        click(getBuildTriggerCheckBox());
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {
        clickByJavaScript(getAreContentInputString());
        getAreContentInputString().sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {
        scrollToElementByJavaScript(getBuildTriggersSection());
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {
        scrollToElementByJavaScript(getPipelineSection());
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton() {
        click(getSaveButton());
        return new JobPage(getDriver());
    }
}

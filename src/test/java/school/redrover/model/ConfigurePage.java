package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v109.runtime.model.RemoteObject;
import school.redrover.model.base.BaseModel;

public class ConfigurePage extends BaseModel {

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    private WebElement getAreContentInputString() {
        return getDriver().findElement(By.xpath("//div[@id='workflow-editor-1']//textarea"));
    }

    private WebElement getBuildTriggersSection() {
        return getDriver().findElement(By.xpath("//label[normalize-space()='Throttle builds']"));
    }

    private WebElement getPipelineSection() {
        return getDriver().findElement(By.xpath("//div[@id='pipeline']"));
    }

    public ConfigurePage sendAreDescriptionInputString(String text) {
        sendTextToInput(getDriver().findElement(By.xpath("//textarea[@name='description']")), text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        click(getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']")));
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

    public JobPage selectSaveButton() {
        click(getDriver().findElement(By.xpath("//button[@name='Submit']")));
        return new JobPage(getDriver());
    }

    public ConfigurePage clickPreview() {
        getDriver().findElement(By.cssSelector("[previewendpoint$='previewDescription']")).click();
        return this;
    }

    public String getPreviewText() {
        return getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).getText();
    }

    public ConfigurePage clearDescriptionArea() {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        return this;
    }
}

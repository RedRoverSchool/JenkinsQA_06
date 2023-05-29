package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class ConfigurePage extends BaseMainHeaderPage<ConfigurePage> {

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
        TestUtils.sendTextToInput(this, getDriver().findElement(By.xpath("//textarea[@name='description']")), text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']")));
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {
        TestUtils.clickByJavaScript(this, getAreContentInputString());
        getAreContentInputString().sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {
        TestUtils.scrollToElementByJavaScript(this, getBuildTriggersSection());
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {
        TestUtils.scrollToElementByJavaScript(this, getPipelineSection());
        return new ConfigurePage(getDriver());
    }

    public JobPage selectSaveButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//button[@name='Submit']")));

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

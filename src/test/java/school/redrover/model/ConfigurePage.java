package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseModel;
import school.redrover.model.base.PageUtils;

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
        PageUtils.sendTextToInput(this, getDriver().findElement(By.xpath("//textarea[@name='description']")), text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {
        PageUtils.click(this, getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']")));
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {
        PageUtils.clickByJavaScript(this, getAreContentInputString());
        getAreContentInputString().sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {
        PageUtils.scrollToElementByJavaScript(this, getBuildTriggersSection());
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {
        PageUtils.scrollToElementByJavaScript(this, getPipelineSection());
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton() {
        PageUtils.click(this, getDriver().findElement(By.xpath("//button[@name='Submit']")));
        return new JobPage(getDriver());
    }
}

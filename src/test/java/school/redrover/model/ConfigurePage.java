package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseModel;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

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

    public ConfigurePage sendAreDescriptionInputString(BaseTest baseTest, String text) {
        TestUtils.sendTextToInput(baseTest, getDriver().findElement(By.xpath("//textarea[@name='description']")), text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox(BaseTest baseTest) {
        TestUtils.click(baseTest, getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']")));
        return this;
    }

    public ConfigurePage sendAreContentInputString(BaseTest baseTest, String text) {
        TestUtils.clickByJavaScript(baseTest, getAreContentInputString());
        getAreContentInputString().sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript(BaseTest baseTest) {
        TestUtils.scrollToElementByJavaScript(baseTest, getBuildTriggersSection());
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection(BaseTest baseTest) {
        TestUtils.scrollToElementByJavaScript(baseTest, getPipelineSection());
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton(BaseTest baseTest) {
        TestUtils.click(baseTest, getDriver().findElement(By.xpath("//button[@name='Submit']")));
        return new JobPage(getDriver());
    }
}

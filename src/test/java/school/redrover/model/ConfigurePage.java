package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class ConfigurePage extends BasePage {

    public ConfigurePage(WebDriver driver) {
        super(driver);
    }

    public ConfigurePage sendAreDescriptionInputString(String text) {

        WebElement descriptionField = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        sendTextToInput(descriptionField, text);
        return this;
    }

    public ConfigurePage clickBuildTriggerCheckBox() {

        WebElement buildTriggerCheckBox = getDriver().findElement(By.xpath("//label[normalize-space()='Build after other projects are built']"));
        click(buildTriggerCheckBox);
        return this;
    }

    public ConfigurePage sendAreContentInputString(String text) {

        WebElement areContentInputString = getDriver().findElement(By.xpath("//div[@id='workflow-editor-1']//textarea"));
        clickByJavaScript(areContentInputString);
        areContentInputString.sendKeys(text);
        return this;
    }

    public ConfigurePage scrollToBuildtriggersByJavaScript() {

        WebElement buildTriggersSection = getDriver().findElement(By.xpath("//label[normalize-space()='Throttle builds']"));
        scrollToElementByJavaScript(buildTriggersSection);
        return new ConfigurePage(getDriver());
    }

    public ConfigurePage scrollToPipelineSection() {

        WebElement pipelineSection = getDriver().findElement(By.xpath("//div[@id='pipeline']"));
        scrollToElementByJavaScript(pipelineSection);
        return new ConfigurePage(getDriver());
    }

    public JobPage clickSaveButton() {

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        click(saveButton);
        return new JobPage(getDriver());
    }
}

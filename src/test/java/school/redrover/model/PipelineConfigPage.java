package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.runner.TestUtils;

public class PipelineConfigPage extends BaseConfigPage<PipelineConfigPage, PipelinePage> {


    public PipelineConfigPage(PipelinePage pipelinePage) {
        super(pipelinePage);
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        WebElement scriptSection = getDriver().findElement(By.xpath("//div[@class='ace_content']"));
        WebElement advancedButton = getDriver().findElement(By.xpath("//div[@class='jenkins-section']//button[@type='button'][normalize-space()='Advanced']"));

        new Actions(getDriver()).scrollToElement(scriptSection).moveToElement(advancedButton).click().perform();
        return this;
    }

    public PipelineConfigPage setDisplayName(String displayName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);
        return this;
    }

    private WebElement getPipelineSection() {
        return getDriver().findElement(By.xpath("//div[@id='pipeline']"));
    }

    public PipelineConfigPage scrollToPipelineSection() {
        TestUtils.scrollToElementByJavaScript(this, getPipelineSection());
        return this;
    }

    public PipelineConfigPage clickPreview() {
        getDriver().findElement(By.cssSelector("[previewendpoint$='previewDescription']")).click();
        return this;
    }

    public String getOptionTextInDefinitionField() {
        String text = "";

        for (WebElement element : getDriver().findElements((By.cssSelector(
                "div[class='jenkins-section'] select.jenkins-select__input.dropdownList>option")))) {
            if (element.getAttribute("selected") != null &&
                    element.getAttribute("selected").equals("true")) {
                text = element.getText();
            }
        }
        return text;
    }

    public String getPreviewText() {
        return getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).getText();
    }

    public PipelineConfigPage clearDescriptionArea() {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        return this;
    }

    public PipelineConfigPage clickScriptDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text() = 'try sample Pipeline...']"))).click();
        return this;
    }

    public PipelinePage selectHelloWord() {
        getDriver().findElement(By.cssSelector("option[value='hello']")).click();
        return new PipelinePage(getDriver());
    }

    public PipelineConfigPage clickPipelineLeftMenu() {
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-section-id='pipeline']"))).click();
        return this;
    }

    public PipelineConfigPage clickAndAddParameter(String parameterName) {
        WebElement buildTriggersSection = getDriver().findElement(By.xpath("//label[normalize-space()='Poll SCM']"));
        WebElement addParameter = getDriver().findElement(By.xpath("//button[@class='hetero-list-add']"));

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='This project is parameterized']"))).click();

        new Actions(getDriver())
                .scrollToElement(buildTriggersSection)
                .moveToElement(addParameter)
                .click()
                .perform();

        getDriver().findElement(By.xpath("//a[normalize-space()='" + parameterName + "']")).click();
        return this;
    }

    public PipelineConfigPage setBooleanParameterName(String name) {
        WebElement scrollElement = getDriver().findElement(By.xpath("//label[normalize-space()='GitHub hook trigger for GITScm polling']"));
        WebElement inputName = getDriver().findElement(By.xpath("//input[contains(@name,'parameter.name')]"));

        new Actions(getDriver())
                .scrollToElement(scrollElement)
                .moveToElement(inputName)
                .click()
                .sendKeys(name)
                .perform();

        return this;
    }

    public PipelineConfigPage setDefaultBooleanParameter() {
        getDriver().findElement(By.xpath("//label[normalize-space()='Set by Default']")).click();
        return this;
    }

    public PipelineConfigPage setBooleanParameterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[contains(@name,'parameter.description')]")).sendKeys(description);
        return this;
    }

    public PipelinePage selectDiscardOldBuildsandSave() {
        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("Submit")).click();
        return new PipelinePage(getDriver());
    }

    public boolean checkboxDiscardOldBuildsIsSelected() {
        getDriver().findElement(By.id("cb2"));
        return true;
    }

    public PipelineConfigPage clickDiscardOldBuildsCheckbox() {
        getDriver().findElement(By.xpath("//label[normalize-space()='Discard old builds']")).click();
        return this;
    }

    public PipelineConfigPage enterDaysToKeepBuilds(String days) {
        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys(days);
        return this;
    }

    public PipelineConfigPage enterMaxOfBuildsToKeep(String builds) {
        getDriver().findElement(By.xpath("//input[@name='_.numToKeepStr']")).sendKeys(builds);
        return this;
    }
}
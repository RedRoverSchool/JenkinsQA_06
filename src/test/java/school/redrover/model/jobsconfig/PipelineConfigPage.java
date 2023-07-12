package school.redrover.model.jobsconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.jobs.PipelinePage;
import school.redrover.model.base.BaseConfigProjectsPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class PipelineConfigPage extends BaseConfigProjectsPage<PipelineConfigPage, PipelinePage> {

    @FindBy(xpath = "//div[@class='ace_content']")
    private WebElement scriptSection;

    @FindBy(xpath = "//textarea[@class='ace_text-input']")
    private WebElement scriptInputField;

    @FindBy(xpath = "//div[@class='jenkins-section']//button[@type='button'][normalize-space()='Advanced']")
    private WebElement advancedButton;

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement name;

    @FindBy(xpath = "//div[@id='pipeline']")
    private WebElement section;

    @FindBy(css = "div[class='jenkins-section'] select.jenkins-select__input.dropdownList>option")
    private List<WebElement> optionText;

    @FindBy(xpath = "//option[text() = 'try sample Pipeline...']")
    private WebElement script;

    @FindBy(css = "option[value='hello']")
    private WebElement helloWord;

    @FindBy(xpath = "//button[@data-section-id='pipeline']")
    private WebElement pipeline;

    @FindBy(xpath = "//label[normalize-space()='Poll SCM']")
    private WebElement buildTriggersSection;

    @FindBy(xpath = "//button[@class='hetero-list-add']")
    private WebElement addParameter;

    @FindBy(xpath = "//label[normalize-space()='This project is parameterized']")
    private WebElement parameterized;

    @FindBy(xpath = "//label[normalize-space()='GitHub hook trigger for GITScm polling']")
    private WebElement scrollElement;

    @FindBy(xpath = "//input[contains(@name,'parameter.name')]")
    private WebElement inputName;

    @FindBy(xpath = "//label[normalize-space()='Set by Default']")
    private WebElement setDefault;

    @FindBy(xpath = "//textarea[contains(@name,'parameter.description')]")
    private WebElement textarea;

    @FindBy(id = "cb2")
    private WebElement cb2;

    @FindBy(xpath = "//label[normalize-space()='Discard old builds']")
    private WebElement discard;

    @FindBy(xpath = "//label[normalize-space()='Throttle builds']")
    private WebElement labelThrottleBuilds;

    @FindBy(xpath = "//label[normalize-space()='Build after other projects are built']")
    private WebElement labelBuilt;

    @FindBy(xpath = "//div[@id='workflow-editor-1']//textarea")
    private WebElement workflowEditor;

    @FindBy(xpath = "//div[@class = 'samples']/select")
    private WebElement samples;

    @FindBy(name = "_.daysToKeepStr")
    private WebElement daysToKeep;

    @FindBy(name = "_.numToKeepStr")
    private WebElement numToKeep;

    @FindBy(xpath = "//*[@name='strategy']//div[@class='error']")
    private WebElement error;

    @FindBy(xpath = "//*[@name='strategy']/div/div")
    private WebElement strategy;

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement toggle;

    @FindBy(xpath = "//input[@name='enable']")
    private WebElement input;

    @FindBy(xpath = "//*[@name='strategy']//div[@class='error']")
    private WebElement errorMessage;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    public PipelineConfigPage(PipelinePage pipelinePage) {
        super(pipelinePage);
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        new Actions(getDriver()).scrollToElement(scriptSection).moveToElement(advancedButton).click().perform();
        return this;
    }

    public PipelineConfigPage setDisplayName(String displayName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(name)).sendKeys(displayName);
        return this;
    }

    public PipelineConfigPage scrollToPipelineSection() {
        TestUtils.scrollToElementByJavaScript(this, section);
        return this;
    }

    public String getOptionTextInDefinitionField() {
        String text = "";

        for (WebElement element : optionText) {
            if (element.getAttribute("selected") != null &&
                    element.getAttribute("selected").equals("true")) {
                text = element.getText();
            }
        }
        return text;
    }

    public PipelineConfigPage clickScriptDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(script)).click();
        return this;
    }

    public PipelinePage selectHelloWord() {
        helloWord.click();
        return new PipelinePage(getDriver());
    }

    public PipelineConfigPage clickPipelineLeftMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(pipeline)).click();
        return this;
    }

    public PipelineConfigPage clickAndAddParameter(String parameterName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(parameterized)).click();

        new Actions(getDriver())
                .scrollToElement(buildTriggersSection)
                .moveToElement(addParameter)
                .click()
                .perform();

        getDriver().findElement(By.xpath("//a[normalize-space()='" + parameterName + "']")).click();
        return this;
    }

    public PipelineConfigPage setBooleanParameterName(String name) {
        new Actions(getDriver())
                .scrollToElement(scrollElement)
                .moveToElement(inputName)
                .click()
                .sendKeys(name)
                .perform();

        return this;
    }

    public PipelineConfigPage setDefaultBooleanParameter() {
        setDefault.click();
        return this;
    }

    public PipelineConfigPage setBooleanParameterDescription(String description) {
        textarea.sendKeys(description);
        return this;
    }

    public PipelinePage selectDiscardOldBuildsandSave() {
        discard.click();
        saveButton.click();
        return new PipelinePage(getDriver());
    }

    public boolean checkboxDiscardOldBuildsIsSelected() {
        return cb2.isDisplayed();
    }

    public PipelineConfigPage clickDiscardOldBuildsCheckbox() {
        discard.click();
        return this;
    }

    public PipelineConfigPage enterDaysToKeepBuilds(String days) {
        daysToKeep.sendKeys(days);
        return this;
    }

    public PipelineConfigPage enterMaxOfBuildsToKeep(String builds) {
        numToKeep.sendKeys(builds);
        return this;
    }

    public PipelineConfigPage scrollToBuildTriggers() {
        TestUtils.scrollToElementByJavaScript(this, labelThrottleBuilds);
        return this;
    }

    public PipelineConfigPage clickBuildTriggerCheckBox() {
        labelBuilt.click();
        return this;
    }

    public PipelineConfigPage sendAreContentInputString(String text) {
        TestUtils.clickByJavaScript(this, workflowEditor);
        workflowEditor.sendKeys(text);
        return this;
    }

    public PipelinePage selectScriptedPipelineAndSubmit() {
        Select selectPipelineScript = new Select(getWait2().until(ExpectedConditions.elementToBeClickable(samples)));
        selectPipelineScript.selectByVisibleText("Scripted Pipeline");
        saveButton.click();
        return new PipelinePage(getDriver());
    }

    public String getDaysToKeepBuilds() {
        return daysToKeep.getAttribute("value");
    }

    public String getMaxNumbersOfBuildsToKeep() {
        return numToKeep.getAttribute("value");
    }

    public boolean isErrorMessageDisplayed() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(error)).isDisplayed();
    }

    public PipelineConfigPage clickOutsideOfInputField() {
        strategy.click();
        return this;
    }

    public PipelineConfigPage toggleDisableProject() {
        boolean isPipelineEnabled = Boolean.parseBoolean(getWait5().until(ExpectedConditions.elementToBeClickable(input)).getAttribute("value"));
        if (isPipelineEnabled) {
            toggle.click();
        }
        return this;
    }

    public boolean isProjectDisable() {

        return Boolean.parseBoolean(getWait5().until(ExpectedConditions.elementToBeClickable(input)).getAttribute("value"));
    }

    public String getErrorMessageStrategyDays() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(errorMessage)).getText();
    }

    public PipelineConfigPage inputInScriptField(String scriptText){
        scriptInputField.sendKeys(scriptText);
        return this;
    }
}

package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.runner.TestUtils;

import java.util.List;

public abstract class BaseConfigProjectsPage<Self extends BaseConfigPage<?, ?>, ProjectPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, ProjectPage> {

    @FindBy(xpath = "//label[normalize-space(text())='Throttle builds']")
    private WebElement throttleBuilds;

    @FindBy(xpath = "//button[contains(text(), 'Add build step')]")
    private WebElement addBuildStepButton;

    @FindBy(xpath = "//button[contains(text(), 'Add post-build action')]")
    private WebElement addPostBuildAction;

    @FindBy(xpath = "//a[contains(text(), 'Execute shell')]")
    private WebElement executeShell;

    @FindBy(xpath = "//span[@class='jenkins-checkbox']//input[@id='cb4']")
    private WebElement oldBuildCheckBox;

    @FindBy(xpath = "//input[@name='_.daysToKeepStr']")
    private WebElement daysToKeepBuilds;

    @FindBy(xpath = "//div[text()='Days to keep builds']")
    private WebElement nameFieldDaysToKeepBuilds;

    @FindBy(xpath = "//input[@name='_.numToKeepStr']")
    private WebElement maxNumOfBuildsToKeepNumber;

    @FindBy(xpath = "//span[text() = 'Enabled']")
    private WebElement enabled;

    @FindBy(xpath = "//label[@for='enable-disable-project']")
    private WebElement disabled;

    @FindBy(xpath = "//span[text() = 'Disabled']")
    private WebElement nameDisabledSwitch;

    @FindBy(xpath = "//span[text() = 'Enabled']")
    private WebElement nameEnabledSwitch;

    @FindBy(xpath = "//label[text()='GitHub project']")
    private WebElement checkBoxGitHubProject;

    @FindBy(css = "[name='_.projectUrlStr']")
    private WebElement inputLineProjectUrl;

    @FindBy(xpath = "//label[text()='This project is parameterized']")
    private WebElement projectIsParametrized;

    @FindBy(xpath = "//button[@class='hetero-list-add']")
    private WebElement addParameter;

    @FindBy(xpath = "//input[@name='parameter.name']")
    private WebElement inputParameterName;

    @FindBy(xpath = "//textarea[@name='parameter.choices']")
    private WebElement inputParameterChoices;

    @FindBy(xpath = "//textarea[@name='parameter.description']")
    private WebElement inputParameterDescription;

    @FindBy(xpath = "//label[normalize-space(text())='Set by Default']")
    private WebElement checkboxSetByDefault;

    @FindBy(xpath = "//select[@name='_.durationName']")
    private WebElement selectTimePeriod;

    @FindBy(xpath = "//label[text()='Retry Count']")
    private WebElement retryCount;

    @FindBy(xpath = "//input[@name='scmCheckoutRetryCount']")
    private WebElement checkoutRetryCountSCM;

    @FindBy(xpath = "//label[text()='Quiet period']")
    private WebElement quietPeriod;

    @FindBy(xpath = "//input[@name='quiet_period']")
    private WebElement inputQuietPeriod;

    @FindBy(xpath = "//label[text()='Execute concurrent builds if necessary']")
    private WebElement checkBoxExecuteConcurrentBuilds;

    @FindBy(xpath = "//div[@ref='cb8']/following-sibling::div[2]")
    private WebElement trueExecuteConcurrentBuilds;

    @FindBy(xpath = "//label[text()='Block build when upstream project is building']")
    private WebElement blockBuildWhenUpstreamProjectIsBuilding;

    @FindBy(xpath = "//button[@data-section-id='source-code-management']")
    private WebElement sourceCodeManagementLink;

    @FindBy(xpath = "//input[@id='radio-block-1']")
    private WebElement radioButtonGit;

    @FindBy(xpath = "//input[@name='_.url']")
    private WebElement inputRepositoryUrl;

    @FindBy(xpath = "//input[@name='jenkins-triggers-ReverseBuildTrigger']")
    private WebElement buildAfterOtherProjectsAreBuiltCheckBox;

    @FindBy(xpath = "//input[@name='_.upstreamProjects']")
    private WebElement projectsToWatchField;

    public BaseConfigProjectsPage(ProjectPage projectPage) {
        super(projectPage);
    }

    public Self addExecuteShellBuildStep(String command) {
        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton));
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(addPostBuildAction).click().perform();
        getWait2().until(ExpectedConditions.elementToBeClickable(addBuildStepButton)).click();
        executeShell.click();
        WebElement codeMirror = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("CodeMirror")));
        actions.scrollToElement(addPostBuildAction).click().perform();
        WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-lines")).get(0);
        codeLine.click();
        WebElement commandField = codeMirror.findElement(By.cssSelector("textarea"));
        commandField.sendKeys(command);

        return (Self) this;
    }

    public Self clickOldBuildCheckBox() {
        TestUtils.clickByJavaScript(this, oldBuildCheckBox);
        return (Self) this;
    }

    public Self enterDaysToKeepBuilds(int number) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", nameFieldDaysToKeepBuilds);
        TestUtils.sendTextToInput(this, daysToKeepBuilds, String.valueOf(number));

        return (Self) this;
    }

    public Self enterMaxNumOfBuildsToKeep(int number) {
        TestUtils.sendTextToInput(this, maxNumOfBuildsToKeepNumber, String.valueOf(number));

        return (Self) this;
    }

    public Self switchCheckboxDisable() {
        getWait2().until(ExpectedConditions.elementToBeClickable(enabled)).click();
        return (Self) this;
    }

    public Self switchCheckboxEnabled() {
        getWait2().until(ExpectedConditions.elementToBeClickable(disabled)).click();
        return (Self) this;
    }

    public String getTextDisable() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(nameDisabledSwitch)).getText();
    }

    public String getTextEnabled() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(nameEnabledSwitch)).getText();
    }

    public String getDaysToKeepBuilds(String attribute) {
        return daysToKeepBuilds.getAttribute(attribute);
    }

    public String getMaxNumOfBuildsToKeep(String attribute) {
        return maxNumOfBuildsToKeepNumber.getAttribute(attribute);
    }

    public Self clickGitHubProjectCheckbox() {
        checkBoxGitHubProject.click();
        return (Self) this;
    }

    public Self inputTextTheInputAreaProjectUrlInGitHubProject(String text) {
        inputLineProjectUrl.sendKeys(text);
        return (Self) this;
    }

    public Self checkProjectIsParametrized() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", projectIsParametrized);
        return (Self) this;
    }

    public Self selectParameterInDropDownByType(String type) {
        getDriver().findElement(By.xpath(String.format("//li/a[text()='%s']", type))).click();
        return (Self) this;
    }

    public Self openAddParameterDropDown() {
        getWait5().until(ExpectedConditions.elementToBeClickable(addParameter));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        getWait5().until(ExpectedConditions.elementToBeClickable(projectIsParametrized));
        js.executeScript("arguments[0].scrollIntoView();", projectIsParametrized);
        addParameter.click();
        return (Self) this;
    }

    public Self inputParameterName(String name) {
        inputParameterName.sendKeys(name);
        return (Self) this;
    }

    public Self inputParameterChoices(List<String> parameterChoices) {
        for (String element : parameterChoices) {
            inputParameterChoices.sendKeys(element + "\n");
        }
        return (Self) this;
    }

    public Self inputParameterDesc(String description) {
        inputParameterDescription.sendKeys(description);
        return (Self) this;
    }

    public Self selectCheckboxSetByDefault() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", checkboxSetByDefault);
        return (Self) this;
    }

    public Self openBuildStepOptionsDropdown() {
        TestUtils.scrollToElementByJavaScript(this, addBuildStepButton);
        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton)).click();
        return (Self) this;
    }

    public List<String> getOptionsInBuildStepDropdown() {
        return TestUtils.getTexts(getDriver().findElements(By.xpath("//button[text()='Add build step']/../../..//a")));
    }

    public Self checkThrottleBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", throttleBuilds);
        js.executeScript("arguments[0].click();", throttleBuilds);
        return (Self) this;
    }

    public Self selectTimePeriod(String timePeriod) {
        new Select(selectTimePeriod).selectByValue(timePeriod.toLowerCase());
        return (Self) this;
    }

    public String getTimePeriodText() {
        return new Select(selectTimePeriod).getFirstSelectedOption().getText();
    }

    public Self clickQuietPeriod() {
        quietPeriod.click();
        return (Self) this;
    }

    public Self inputQuietPeriod(String number) {
        inputQuietPeriod.clear();
        inputQuietPeriod.sendKeys(number);
        return (Self) this;
    }

    public String getQuietPeriod() {
        return inputQuietPeriod.getAttribute("value");
    }

    public Self clickRetryCount() {
        retryCount.click();
        return (Self) this;
    }

    public Self inputSCMCheckoutRetryCount(String count) {
        checkoutRetryCountSCM.clear();
        checkoutRetryCountSCM.sendKeys(count);
        return (Self) this;
    }

    public String getCheckoutRetryCountSCM() {
        return checkoutRetryCountSCM.getAttribute("value");
    }

    public Self clickCheckBoxExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", throttleBuilds);
        checkBoxExecuteConcurrentBuilds.click();
        return (Self) this;
    }

    public WebElement getTrueExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", throttleBuilds);
        return trueExecuteConcurrentBuilds;
    }

    public Self clickBlockBuildWhenUpstreamProjectIsBuilding() {
        blockBuildWhenUpstreamProjectIsBuilding.click();
        return (Self) this;
    }

    public Self clickSourceCodeManagementLink() {
        getWait5().until(ExpectedConditions.elementToBeClickable(sourceCodeManagementLink)).click();
        return (Self) this;
    }

    public Self clickRadioButtonGit() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", radioButtonGit);
        return (Self) this;
    }

    public Self inputRepositoryUrl(String text) {
        getWait5().until(ExpectedConditions.visibilityOf(inputRepositoryUrl)).sendKeys(text);
        return (Self) this;
    }

    public String getRepositoryUrlText() {
        return getWait5().until(ExpectedConditions.visibilityOf(inputRepositoryUrl)).getAttribute("value");
    }

    public Self clickBuildAfterOtherProjectsAreBuiltCheckBox() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", buildAfterOtherProjectsAreBuiltCheckBox);
        js.executeScript("arguments[0].click();", buildAfterOtherProjectsAreBuiltCheckBox);
        return (Self) this;
    }

    public Self inputProjectsToWatch(String projectName) {
        getWait5().until(ExpectedConditions.visibilityOf(projectsToWatchField)).sendKeys(projectName);
        return (Self) this;
    }
}

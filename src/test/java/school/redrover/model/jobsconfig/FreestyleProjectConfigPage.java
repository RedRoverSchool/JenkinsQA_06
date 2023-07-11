package school.redrover.model.jobsconfig;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.base.BaseConfigProjectsPage;

public class FreestyleProjectConfigPage extends BaseConfigProjectsPage<FreestyleProjectConfigPage, FreestyleProjectPage> {

    @FindBy(xpath = "//label[text()='Execute concurrent builds if necessary']")
    private WebElement executeConcurrentBuildsIfNecessary;

    @FindBy(xpath = "(//button[contains(text(),'Advanced')])[3]")
    private WebElement advancedDropdownMenu;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(xpath = "//a[text()='Invoke top-level Maven targets']")
    private WebElement invokeMavenTargetsButton;

    @FindBy(xpath = "//input[@name='blockBuildWhenUpstreamBuilding']")
    private WebElement trueBlockBuildWhenUpstreamProjectIsBuilding;

    @FindBy(xpath = "(//button[@class='task-link'])[5]")
    private WebElement postBuildActionsButton;

    @FindBy(xpath = "//button[text()='Add post-build action']")
    private WebElement addPostBuildActionDropDown;

    @FindBy(xpath = "//a[text()='E-mail Notification']")
    private WebElement emailNotificationType;

    @FindBy(xpath = "//input[@name='_.recipients']")
    private WebElement emailNotificationInputField;

    @FindBy(xpath = "//a[text()= 'Build other projects']")
    private WebElement buildOtherProjectsType;

    @FindBy(xpath = "//input[@name='buildTrigger.childProjects']")
    private WebElement buildOtherProjectsInputField;

    @FindBy(xpath = "//a[text()='Archive the artifacts']")
    private WebElement archiveTheArtifacts;

    @FindBy(xpath = "//div[@descriptorid = 'hudson.tasks.ArtifactArchiver']")
    private WebElement archiveArtifacts;

    @FindBy(xpath = "//*[@id='textarea._.targets']")
    private WebElement goalsField;

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement displayNameField;

    @FindBy(xpath = "//a[text()='Set GitHub commit status (universal)']")
    private WebElement gitHubCommitStatusType;

    @FindBy(xpath = "//*[contains(text(), 'Commit context')]//following-sibling::div//select")
    private WebElement commitContextSelect;

    @FindBy(xpath = "//input[@name='_.context']")
    private WebElement contextNameField;

    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    private FreestyleProjectConfigPage scrollToFooter() {
        new Actions(getDriver())
                .scrollToElement(footer)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage addInvokeMavenGoalsTargets(String goals) {
        new Actions(getDriver())
                .moveToElement(invokeMavenTargetsButton)
                .perform();

        getWait5().until(ExpectedConditions.visibilityOf(invokeMavenTargetsButton)).click();
        goalsField.sendKeys(goals);

        return this;
    }

    public FreestyleProjectConfigPage clickAdvancedDropdownMenu() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", executeConcurrentBuildsIfNecessary);
        getWait10().until(ExpectedConditions.elementToBeClickable(advancedDropdownMenu)).click();
        return this;
    }

    public boolean getTrueBlockBuildWhenUpstreamProjectIsBuilding() {
        return trueBlockBuildWhenUpstreamProjectIsBuilding.isSelected();
    }

    public FreestyleProjectConfigPage clickPostBuildActionsButton() {
        postBuildActionsButton.click();
        return this;
    }

    public FreestyleProjectConfigPage clickAddPostBuildActionDropDown() {
        scrollToFooter();
        getWait2().until(ExpectedConditions.elementToBeClickable(addPostBuildActionDropDown)).click();
        return this;
    }

    public FreestyleProjectConfigPage clickEmailNotification() {
        emailNotificationType.click();
        return this;
    }

    public FreestyleProjectConfigPage setEmailNotification(String email) {
        scrollToFooter();
        getWait2().until(ExpectedConditions.elementToBeClickable(emailNotificationInputField)).sendKeys(email);
        return this;
    }

    public String getEmailNotificationFieldText() {
        return getWait2().until(ExpectedConditions.visibilityOf(emailNotificationInputField)).getAttribute("value");
    }

    public FreestyleProjectConfigPage clickBuildOtherProjects() {
        buildOtherProjectsType.click();
        return this;
    }

    public FreestyleProjectConfigPage setBuildOtherProjects(String projectName) {
        scrollToFooter();
        getWait2().until(ExpectedConditions.elementToBeClickable(buildOtherProjectsInputField)).sendKeys(projectName);
        return this;
    }

    public FreestyleProjectConfigPage clickArchiveTheArtifacts() {
        archiveTheArtifacts.click();
        return  this;
    }

    public String getTextArchiveArtifacts() {
       return archiveArtifacts.getText();
    }

    public String getMavenGoals() {
        return goalsField.getAttribute("value");
    }

    public FreestyleProjectConfigPage setDisplayName(String displayName) {
        displayNameField.sendKeys(displayName);
        return this;
    }
    public FreestyleProjectConfigPage clickSetGitHubCommitStatus() {
        scrollToFooter();
        gitHubCommitStatusType.click();
        return this;
    }

    public FreestyleProjectConfigPage setGitHubCommitStatusContext(String status) {
        scrollToFooter();
        new Select(commitContextSelect).selectByVisibleText("Manually entered context name");
        getWait2().until(ExpectedConditions.elementToBeClickable(contextNameField)).sendKeys(status);
        return this;
    }

    public String getGitHubCommitStatus() {
        scrollToFooter();
        return contextNameField.getAttribute("value");
    }

}

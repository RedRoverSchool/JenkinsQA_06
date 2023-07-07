package school.redrover.model.jobsconfig;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.base.BaseConfigProjectsPage;

public class FreestyleProjectConfigPage extends BaseConfigProjectsPage<FreestyleProjectConfigPage, FreestyleProjectPage> {

    @FindBy(xpath = "//label[text()='Execute concurrent builds if necessary']")
    private WebElement executeConcurrentBuildsIfNecessary;

    @FindBy(xpath = "(//button[contains(text(),'Advanced')])[3]")
    private WebElement advancedDropdownMenu;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(xpath = "//*[text()='Execute shell']")
    private WebElement executeShellButton;

    @FindBy(xpath = "//*[text()='Invoke top-level Maven targets']")
    private WebElement invokeMavenTargetsButton;

    @FindBy(xpath = "//*[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//*[@id='textarea._.targets']")
    private WebElement goalsField;
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

    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    private FreestyleProjectConfigPage scrollToFooter() {
        new Actions(getDriver())
                .scrollToElement(footer)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage addBuildInvokeMavenTargets(String step) { //addBuildInvokeMavenTargets(String steps)
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait5().until(ExpectedConditions.visibilityOf(invokeMavenTargetsButton)).click();

        new Actions(getDriver())
                .click(goalsField)
                .sendKeys(step)
                .perform();
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
}

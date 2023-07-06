package school.redrover.model.jobsconfig;

import org.openqa.selenium.By;
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

    @FindBy(id = "yui-gen9")
    private WebElement executeShellButton;

    @FindBy(xpath = "//*[@id='yui-gen24']")
    private WebElement generalButton;

    @FindBy(xpath = "//*[@name='description']")
    private WebElement descriptionField;

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

    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    private FreestyleProjectConfigPage scrollToFooter() {
        new Actions(getDriver())
                .scrollToElement(footer)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage addBuildStepsExecuteShell(String buildSteps) {
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait10().until(ExpectedConditions.elementToBeClickable(executeShellButton)).click();
        generalButton.click();

        new Actions(getDriver())
                .click(descriptionField)
                .sendKeys(buildSteps)
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
}

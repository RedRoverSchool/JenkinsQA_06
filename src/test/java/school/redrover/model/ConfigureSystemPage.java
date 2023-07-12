package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class ConfigureSystemPage extends BaseMainHeaderPage<ConfigureSystemPage> {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//input[@name='_.smtpHost']")
    private WebElement smtpServerFieldExtendedEmailNotifications;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//input[@name='_.smtpPort']")
    private WebElement smtpPortFieldExtendedEmailNotifications;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//button[contains(@class, 'advancedButton')]")
    private WebElement advancedButtonExtendedEmailNotifications;

    @FindBy(xpath = "//button[@id='yui-gen3-button']")
    private WebElement addButtonExtendedEmailNotifications;

    @FindBy(xpath = "//span[@title='Jenkins Credentials Provider']")
    private WebElement jenkinsCredentialProvider;

    @FindBy(css = "#credentialsDialog_c input[name='_.username']")
    private WebElement usernameInputFieldInAddCredentialsPopUpWindow;

    @FindBy(css = "#credentialsDialog_c input[name='_.password']")
    private WebElement passwordInputFieldInAddCredentialsPopUpWindow;

    @FindBy(css = "#credentialsDialog_c button#credentials-add-submit-button")
    private WebElement addButtonAddCredentialsPopUpWindow;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//span[@class=\"jenkins-checkbox\"]//span[text()='Use SSL']")
    private WebElement useSSLCheckboxExtendedEmailNotifications;

    @FindBy(xpath = "//button[contains(text(), 'Default Triggers')]")
    private WebElement defaultTriggersButton;

    @FindBy(xpath = "//div[@class='setting-main']/span/label[@class='attach-previous ']")
    private List<WebElement> defaultTriggersList;

    @FindBy(xpath = "//input[@class='jenkins-input validated  '][@name='_.smtpHost']")
    private WebElement smtpServerFieldEmailNotifications;

    @FindBy(xpath = "(//button[contains(@class, 'advancedButton')])[last()]")
    private WebElement advancedButtonEmailNotifications;

    @FindBy(xpath = "//label[text()='Use SMTP Authentication']")
    private WebElement useSMTPAuthenticationCheckbox;

    @FindBy(xpath = "//div[@nameref='cb15']//input[@name='_.username']")
    private WebElement userNameSMTPAuthentication;

    @FindBy(xpath = "//div[@nameref='cb15']//input[@name='_.password']")
    private WebElement passwordSMTPAuthentication;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr has-help jenkins-form-item--tight']//label/span[text()='Use SSL']")
    private WebElement useSSLCheckboxEmailNotifications;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr  has-help']//input[@name='_.smtpPort']")
    private WebElement smtpPortFieldEmailNotifications;

    @FindBy(xpath = "//label[text()='Test configuration by sending test e-mail']")
    private WebElement testConfigurationBySendingTestEmailCheckbox;

    @FindBy(xpath = "//input[@name='sendTestMailTo']")
    private WebElement testEmailRecipientInputField;

    @FindBy(xpath = "//button[@id='yui-gen27-button']")
    private WebElement testConfigurationButton;

    @FindBy(xpath = "//select[@name='_.credentialsId']")
    private WebElement credentialsDropdown;

    @FindBy(xpath = "//div[@class='jenkins-validate-button__container__status']//div[@class='ok']")
    private WebElement testConfigurationMessage;

    @FindBy(xpath = "//div[@class='jenkins-form-label help-sibling'][text()='Content Token Reference']")
    private WebElement contentTokenReference;

    public ConfigureSystemPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {

        return title.getText();
    }

    public ConfigureSystemPage inputSmtpServerFieldExtendedEmailNotifications(String smtpServer) {
        TestUtils.scrollToElementByJavaScript(this, smtpServerFieldExtendedEmailNotifications);
        getWait10().until(ExpectedConditions.visibilityOf(smtpServerFieldExtendedEmailNotifications)).clear();
        smtpServerFieldExtendedEmailNotifications.sendKeys(smtpServer);
        return  this;
    }

    public ConfigureSystemPage inputSmtpPortFieldExtendedEmailNotifications(String smtpPort) {
        getWait2().until(ExpectedConditions.visibilityOf(smtpPortFieldExtendedEmailNotifications)).clear();
        smtpPortFieldExtendedEmailNotifications.sendKeys(smtpPort);
        return  this;
    }

    public ConfigureSystemPage clickAdvancedButtonExtendedEmailNotification() {
        advancedButtonExtendedEmailNotifications.click();
        return this;
    }

    public ConfigureSystemPage clickAddCredentialButton() {
        addButtonExtendedEmailNotifications.click();
        getWait2().until(ExpectedConditions.elementToBeClickable(jenkinsCredentialProvider)).click();
        return this;
    }

    public ConfigureSystemPage selectCreatedCredentials(String email) {
        credentialsDropdown.click();
        new Select(credentialsDropdown).selectByVisibleText(email + "/******");
        return this;
    }

    public ConfigureSystemPage inputUsernameIntoAddCredentialPopUpWindow(String username) {
        getWait5().until(ExpectedConditions.visibilityOf(usernameInputFieldInAddCredentialsPopUpWindow)).sendKeys(username);
        return this;
    }

    public ConfigureSystemPage inputPasswordIntoAddCredentialPopUpWindow(String password) {
        getWait5().until(ExpectedConditions.visibilityOf(passwordInputFieldInAddCredentialsPopUpWindow)).sendKeys(password);
        return this;
    }

    public ConfigureSystemPage clickAddButtonAddCredentialPopUp() {
        new Actions(getDriver()).scrollToElement(addButtonAddCredentialsPopUpWindow);
        getWait5().until(ExpectedConditions.elementToBeClickable(addButtonAddCredentialsPopUpWindow)).click();
        return this;
    }

    public ConfigureSystemPage checkUseSSLCheckbox() {
        new Actions(getDriver()).scrollToElement(useSSLCheckboxExtendedEmailNotifications).perform();
        getWait5().until(ExpectedConditions.visibilityOf(useSSLCheckboxExtendedEmailNotifications)).click();
        return this;
    }

    public ConfigureSystemPage clickDefaultTriggersButton() {
        TestUtils.scrollToElementByJavaScript(this, contentTokenReference);
        defaultTriggersButton.click();
        return this;
    }

    public ConfigureSystemPage checkAlwaysAndSuccessDefaultTriggers() {
        for (WebElement trigger: defaultTriggersList) {
            if(trigger.getText().equals("Always")) {
                trigger.click();
            }
            if(trigger.getText().equals("Success")) {
                trigger.click();
            }
        }
        return this;
    }

    public ConfigureSystemPage inputSmtpServerFieldEmailNotifications(String smtpServer) {
        TestUtils.scrollToElementByJavaScript(this, smtpServerFieldEmailNotifications);
        getWait5().until(ExpectedConditions.visibilityOf(smtpServerFieldEmailNotifications)).clear();
        smtpServerFieldEmailNotifications.sendKeys(smtpServer);
        return  this;
    }

    public ConfigureSystemPage clickAdvancedButtonEmailNotification() {
        advancedButtonEmailNotifications.click();
        return this;
    }

    public ConfigureSystemPage clickUseSMTPAuthenticationCheckbox() {
        getWait2().until(ExpectedConditions.elementToBeClickable(useSMTPAuthenticationCheckbox)).click();
        return this;
    }

    public ConfigureSystemPage inputUserNameAndPasswordSMTPAuthentication(String username, String password) {
        getWait5().until(ExpectedConditions.visibilityOf(userNameSMTPAuthentication)).sendKeys(username);
        passwordSMTPAuthentication.sendKeys(password);
        return this;
    }

    public ConfigureSystemPage checkUseSSLCheckboxEmailNotifications() {
        TestUtils.scrollToElementByJavaScript(this, useSSLCheckboxEmailNotifications);
        getWait2().until(ExpectedConditions.elementToBeClickable(useSSLCheckboxEmailNotifications)).click();
        return this;
    }

    public ConfigureSystemPage testEmailRecipientInputField(String port) {
        smtpPortFieldEmailNotifications.clear();
        smtpPortFieldEmailNotifications.sendKeys(port);
        return this;
    }

    public ConfigureSystemPage checkTestConfigurationBySendingTestEmailCheckbox() {
        testConfigurationBySendingTestEmailCheckbox.click();
        return this;
    }

    public ConfigureSystemPage inputEmailIntoTestEmailRecipientInputField(String email) {
        getWait2().until(ExpectedConditions.visibilityOf(testEmailRecipientInputField)).clear();
        testEmailRecipientInputField.sendKeys(email);
        return this;
    }

    public ConfigureSystemPage clickTestConfigurationButton() {
        TestUtils.scrollToElementByJavaScript(this, testConfigurationButton);
        testConfigurationButton.click();
        return this;
    }

    public String getConfigurationMessageText() {
        return getWait2().until(ExpectedConditions.visibilityOf(testConfigurationMessage)).getText();
    }
}

package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.ConfigureSystemPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class ConfigureSystemTest extends BaseTest {

    @Test
    public void testManageJenkinsEmailNotificationSetUp() {
        String smtpServer = "smtp.gmail.com";
        String smtpPort = "465";
        String username = "jenkins05test@gmail.com";
        String password = "bfdzlscazepasstj";
        String expectedTestConfigurationMessage = "Email was successfully sent";

        String actualTestConfigurationMessage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickConfigureSystemLink()
                .inputSmtpServerFieldExtendedEmailNotifications(smtpServer)
                .inputSmtpPortFieldExtendedEmailNotifications(smtpPort)
                .clickAdvancedButtonExtendedEmailNotification()
                .clickAddCredentialButton()
                .inputUsernameIntoAddCredentialPopUpWindow(username)
                .inputPasswordIntoAddCredentialPopUpWindow(password)
                .clickAddButtonAddCredentialPopUp()
                .selectCreatedCredentials(username)
                .checkUseSSLCheckbox()
                .clickDefaultTriggersButton()
                .checkAlwaysDefaultTriggers()
                .checkSuccessDefaultTriggers()
                .inputSmtpServerFieldEmailNotifications(smtpServer)
                .clickAdvancedButtonEmailNotification()
                .clickUseSMTPAuthenticationCheckbox()
                .inputUserNameAndPasswordSMTPAuthentication(username, password)
                .checkUseSSLCheckboxEmailNotifications()
                .inputSmtpPortEmailNotificationsField(smtpPort)
                .checkTestConfigurationBySendingTestEmailCheckbox()
                .inputEmailIntoTestEmailRecipientInputField(username)
                .clickTestConfigurationButton()
                .getConfigurationMessageText();

        Assert.assertEquals(actualTestConfigurationMessage, expectedTestConfigurationMessage);
        new ConfigureSystemPage(getDriver()).clickSaveButton();
    }

    @Test(dependsOnMethods = "testManageJenkinsEmailNotificationSetUp")
    public void testManageJenkinsEmailNotificationGoingBackToOriginalSettings() {

        ConfigureSystemPage configureSystemPage = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickConfigureSystemLink()
                .inputSmtpServerFieldExtendedEmailNotifications("")
                .inputSmtpPortFieldExtendedEmailNotifications("25")
                .clickAdvancedButtonExtendedEmailNotification()
                .unCheckUseSSLCheckboxExtendedEmailNotifications()
                .clickDefaultTriggersButton()
                .unCheckDefaultTriggerAlwaysCheckbox()
                .unCheckDefaultTriggerSuccessCheckbox()
                .inputSmtpServerFieldEmailNotifications("")
                .clickAdvancedButtonEmailNotification()
                .unCheckSMTPAuthenticationCheckbox()
                .unCheckUseSSLCheckboxEmailNotifications()
                .inputSmtpPortEmailNotificationsField("25")
                .clickSaveButton()
                .clickManageJenkinsPage()
                .clickConfigureSystemLink();

        Assert.assertTrue(configureSystemPage.isSmtpServerFieldExtendedEmailNotificationsEmpty());
        Assert.assertTrue(configureSystemPage.isSmtpPortFieldExtendedEmailNotificationsBackToOriginal());
        Assert.assertFalse(configureSystemPage.isUseSSLCheckboxChecked());
        Assert.assertFalse(configureSystemPage.isTriggersAlwaysChecked());
        Assert.assertFalse(configureSystemPage.isTriggersSuccessChecked());
        Assert.assertTrue(configureSystemPage.isSmtpServerFieldEmailNotificationsEmpty());
        Assert.assertFalse(configureSystemPage.isUseSMTPAuthenticationCheckboxChecked());
        Assert.assertFalse(configureSystemPage.isUseSSLCheckboxEmailNotificationsChecked());
        Assert.assertTrue(configureSystemPage.isSmtpPortFieldEmailNotificationsBackToOriginal());
    }
}

package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
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
                .checkAlwaysAndSuccessDefaultTriggers()
                .inputSmtpServerFieldEmailNotifications(smtpServer)
                .clickAdvancedButtonEmailNotification()
                .clickUseSMTPAuthenticationCheckbox()
                .inputUserNameAndPasswordSMTPAuthentication(username, password)
                .checkUseSSLCheckboxEmailNotifications()
                .testEmailRecipientInputField(smtpPort)
                .checkTestConfigurationBySendingTestEmailCheckbox()
                .inputEmailIntoTestEmailRecipientInputField(username)
                .clickTestConfigurationButton()
                .getConfigurationMessageText();

        Assert.assertEquals(actualTestConfigurationMessage, expectedTestConfigurationMessage);
    }
}

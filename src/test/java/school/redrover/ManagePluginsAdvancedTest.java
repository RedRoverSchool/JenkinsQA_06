package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class ManagePluginsAdvancedTest extends BaseTest {

    @Test
    public void testServerHelpInfo(){
        final String expectedServerHelpInfo = """
                If your Jenkins server sits behind a firewall and does not have the direct access to the internet, and if your server JVM is not configured appropriately ( See JDK networking properties for more details ) to enable internet connection, you can specify the HTTP proxy server name in this field to allow Jenkins to install plugins on behalf of you. Note that Jenkins uses HTTPS to communicate with the update center to download plugins.
                Leaving this field empty means Jenkins will try to connect to the internet directly.
                If you are unsure about the value, check the browser proxy configuration.""";
        String ServerHelpInfo = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickManagePlugins()
                .clickAdvancedSettings()
                .clickExtraInfoServerIcon()
                .getExtraInfoServerTextBox();

        Assert.assertEquals(ServerHelpInfo, expectedServerHelpInfo);
    }
}

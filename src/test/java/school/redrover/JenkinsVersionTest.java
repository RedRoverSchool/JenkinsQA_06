package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JenkinsVersionTest extends BaseTest {

    private static final String version = "Jenkins 2.387.2";
    private static final By VERSION_NUMBER = By.xpath("//a[@href = 'https://www.jenkins.io/']");

    public void assertVersion () {
        Assert.assertEquals(getDriver().findElement(VERSION_NUMBER).getText(), version);
    }

    @Test
    public void testVersionOnHomePage () {
        assertVersion();
    }

    @Test
    public void testVersionOnPeoplePage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[2]//a")).click();

        assertVersion();
    }

    @Test
    public void testVersionOnBuildHistoryPage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[3]//a")).click();

        assertVersion();
    }

    @Test
    public void testVersionOnManageJenkinsPage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[4]//a")).click();

        assertVersion();
    }

    @Test
    public void testVersionOnMyViewPage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[5]//a")).click();

        assertVersion();
    }
}

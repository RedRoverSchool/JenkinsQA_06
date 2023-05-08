package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterTest2 extends BaseTest {
    private static final By JENKINS_VERSION_BTN = By.xpath("//a[@rel='noopener noreferrer']");
    private static final By MANAGE_JENKINS_BTN = By.xpath("//span[contains(text(),'Manage Jenkins')]/..");
    private static final String JENKINS_VERSION = "Jenkins 2.387.2";
    private static final By REST_API_BTN = By.xpath("//a[contains(text(),'REST API')]");
    private static final String TITLE = "Remote API [Jenkins]";

    @Test
    public void testVerifyJenkinsVersionOnManageJenkinsPage() {
        getDriver().findElement(MANAGE_JENKINS_BTN).click();

        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(JENKINS_VERSION_BTN));

        Assert.assertEquals(getDriver().findElement(JENKINS_VERSION_BTN).getText(), JENKINS_VERSION, "Wrong version Jenkins");
    }

    @Test
    public void testVerifyRESTAPILink(){
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(REST_API_BTN));

        getDriver().findElement(REST_API_BTN).click();
        Assert.assertEquals(getDriver().getTitle(),TITLE, "Wrong page or page title");
    }

}

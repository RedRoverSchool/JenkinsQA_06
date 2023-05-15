package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterTest extends BaseTest {

    private static final By JENKINS_VERSION_BTN = By.xpath("//div[@class='page-footer__flex-row']//a[@rel='noopener noreferrer']");
    private static final By MANAGE_JENKINS_BTN = By.xpath("//span[contains(text(),'Manage Jenkins')]/..");
    private static final String JENKINS_VERSION = "Jenkins 2.387.2";
    private static final By REST_API_BTN = By.xpath("//a[contains(text(),'REST API')]");
    private static final String TITLE = "Remote API [Jenkins]";

    @Test
    public void testRestAPILink() {
        getDriver().findElement(By.cssSelector("a[href='api/']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel > h1")).getText(), "REST API");
    }

    @Test
    public void testJenkinsFooterLink() {
        getDriver().findElement(By.cssSelector("a[rel='noopener noreferrer']")).click();

        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1[class='page-title'] > span")).getText().trim(), "Jenkins");
    }

    @Test
    public void testVerifyJenkinsVersion() {

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/']")).getText(), "Jenkins 2.387.2");
    }

    @Test
    public void testCheckJenkinsVersionInPeoplePage(){
        WebElement peoplePageButton = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        peoplePageButton.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'People')]")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@rel='noopener noreferrer']")).getText(),"Jenkins 2.387.2");
    }

    @Test
    public void testVerifyJenkinsVersionOnManageJenkinsPage() {
        getDriver().findElement(MANAGE_JENKINS_BTN).click();

        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(JENKINS_VERSION_BTN)).perform();

        Assert.assertEquals(getDriver().findElement(JENKINS_VERSION_BTN).getText(), JENKINS_VERSION, "Wrong version Jenkins");
    }

    @Test
    public void testVerifyRESTAPILink() {
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(REST_API_BTN)).perform();

        getDriver().findElement(REST_API_BTN).click();
        Assert.assertEquals(getDriver().getTitle(),TITLE, "Wrong page or page title");
    }

}

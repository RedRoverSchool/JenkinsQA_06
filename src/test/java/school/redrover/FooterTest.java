package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterTest extends BaseTest {

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
        getDriver().findElement(By.xpath("//span[contains(text(),'Manage Jenkins')]/..")).click();

        new Actions(getDriver())
                .scrollToElement(getDriver().findElement(
                        By.xpath("//div[@class='page-footer__flex-row']//a[@rel='noopener noreferrer']"))).perform();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@class='page-footer__flex-row']//a[@rel='noopener noreferrer']")).getText(),
                "Jenkins 2.387.2", "Wrong version Jenkins");
    }

    @Test
    public void testVerifyRESTAPILink() {
        new Actions(getDriver())
                .scrollToElement(getDriver().findElement(By.xpath("//a[contains(text(),'REST API')]"))).perform();

        getDriver().findElement(By.xpath("//a[contains(text(),'REST API')]")).click();
        Assert.assertEquals(getDriver().getTitle(), "Remote API [Jenkins]", "Wrong page or page title");
    }

}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class FooterJenkinsVersionTest extends BaseTest {
    private final String expectedJenkinsVersion = "Jenkins 2.387.2";
    private final String expectedSiteTitle = "Jenkins";
    @Ignore
    @Test
    public void testFooterJenkinsVersion() {
        WebElement linkVersion = getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']"));
        Assert.assertEquals(linkVersion.getText(), "Jenkins 2.387.2");

        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        linkVersion.click();

        getWait2().until(numberOfWindowsToBe(2));

        for (String winHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(winHandle)) {
                getDriver().switchTo().window(winHandle);
                break;
            }
        }

        WebElement brandJenkins =
        getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1"))));

        Assert.assertEquals(brandJenkins.getText(), "Jenkins");
    }

    @Test
    public void testFooterJenkinsVersionOnNodesPage() {
        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//a[@target='_blank']"));
        String actualJenkinsVersion = jenkinsVersion.getText();

        Assert.assertEquals(actualJenkinsVersion, expectedJenkinsVersion, "Jenkins version does not match");
    }

    @Ignore
    @Test
    public void testClickOnJenkinsVersionOpensSiteOnNodesPage(){

        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        getDriver().findElement(By.xpath("//a[@target='_blank']")).click();

        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        String actualSiteTitle = getDriver().findElement(By.xpath("//h1[@class='page-title']/span")).getText();
        Assert.assertEquals(actualSiteTitle, expectedSiteTitle);
    }
}

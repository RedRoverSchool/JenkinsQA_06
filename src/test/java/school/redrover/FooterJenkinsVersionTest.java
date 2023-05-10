package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterJenkinsVersionTest extends BaseTest {
    private final String expectedJenkinsVersion = "Jenkins 2.387.2";
    private final String expectedSiteTitle = "Jenkins";
@Ignore
    @Test
    public void testFooterJenkinsVersion() {

        WebElement linkVersion = getDriver().findElement(By.cssSelector("a[target =  '_blank']"));
        Assert.assertEquals(linkVersion.getText(),"Jenkins 2.387.2");
        linkVersion.click();

        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        WebElement brandJenkins = getDriver().findElement(By.cssSelector(".page-title >span"));
        Assert.assertEquals(brandJenkins.getText(),"Jenkins");

    }

    @Test
    public void testFooterJenkinsVersionOnNodesPage() {
        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//a[@target='_blank']"));
        String actualJenkinsVersion = jenkinsVersion.getText();

        Assert.assertEquals(actualJenkinsVersion, expectedJenkinsVersion, "Jenkins version does not match");
    }

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

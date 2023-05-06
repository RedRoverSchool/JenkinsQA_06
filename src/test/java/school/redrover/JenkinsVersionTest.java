package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JenkinsVersionTest extends BaseTest {

    private final String expectedJenkinsVersion = "Jenkins 2.387.2";
    private final String expectedSiteTitle = "Jenkins";


    @Test
    public void testJenkinsVersionOnNodesPage() {
        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//a[@target='_blank']"));
        String actualJenkinsVersion = jenkinsVersion.getText();

        Assert.assertEquals(actualJenkinsVersion, expectedJenkinsVersion, "Jenkins version does not match");
    }

    @Test(dependsOnMethods = "testJenkinsVersionOnNodesPage")
    public void testClickOnJenkinsVersionOpensSiteOnNodesPage(){
        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        getDriver().findElement(By.xpath("//a[@target='_blank']")).click();

        String currentWindow = getDriver().getWindowHandle();
        for(String windowHandle : getDriver().getWindowHandles()){
            if(!currentWindow.equals(windowHandle)){
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        String actualSiteTitle = getDriver().findElement(By.xpath("//h1[@class='page-title']/span")).getText();
        Assert.assertEquals(actualSiteTitle, expectedSiteTitle);
    }
}

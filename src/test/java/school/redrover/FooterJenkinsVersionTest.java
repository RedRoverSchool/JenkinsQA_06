package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.JenkinsVersionPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.runner.BaseTest;


public class FooterJenkinsVersionTest extends BaseTest {

    @Test
    public void testFooterJenkinsVersion() {
        WebElement linkVersion = new MainPage(getDriver())
                .getLinkVersion();
        Assert.assertEquals(linkVersion.getText(), "Jenkins 2.387.2");

        WebElement switchLinkVersion = new JenkinsVersionPage(getDriver())
                .switchJenkinsDocPage()
                .jenkinsPage();

        Assert.assertEquals(switchLinkVersion.getText(), "Jenkins");
    }

    @Test
    public void testFooterJenkinsVersionOnNodesPage() {
        getDriver().findElement(By.xpath("//span[@class='pane-header-title']/a")).click();
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//a[@target='_blank']"));
        String actualJenkinsVersion = jenkinsVersion.getText();

        String expectedJenkinsVersion = "Jenkins 2.387.2";
        Assert.assertEquals(actualJenkinsVersion, expectedJenkinsVersion, "Jenkins version does not match");
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
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(getDriver())
                .navigateToManageJenkinsPage()
                .scrollToFooterPageByJenkinsVersionBTN();

        Assert.assertTrue(manageJenkinsPage.getVersionJenkinsFromFooter(), "Wrong version Jenkins");
    }


    private static final String version = "Jenkins 2.387.2";
    private static final By VERSION_NUMBER = By.xpath("//a[@href = 'https://www.jenkins.io/']");

    public void assertVersion () {
        Assert.assertEquals(getDriver().findElement(VERSION_NUMBER).getText(), version);
    }

    @Test
    public void testVersionOnBuildHistoryPage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[3]//a")).click();

        assertVersion();
    }

    @Test
    public void testVersionOnMyViewPage () {
        getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[5]//a")).click();

        assertVersion();
    }





}

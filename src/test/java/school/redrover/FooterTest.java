package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.JenkinsVersionPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;


public class FooterTest extends BaseTest {

    @Test
    public void testFooterJenkinsVersion() {
        WebElement linkVersion = new MainPage(getDriver())
                .getHeader()
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
        boolean isVersionJenkinsCorrect = new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .scrollToFooterPageByJenkinsVersionBTN()
                .isVersionJenkinsFromFooterCorrect();

        Assert.assertTrue(isVersionJenkinsCorrect, "Wrong version Jenkins");
    }

    @Test
    public void testFindRestApiInstruction() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'REST API')]"))).click();

        WebElement restApiInstructionTitle = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1[contains(text(),'REST API')]"));
        Assert.assertTrue(restApiInstructionTitle.isDisplayed(), "Element not found");
    }

    @Test
    public void restApiTest() {
        final String apiLink = "REST API";

        String mainPage = new MainPage(getDriver())
                .clickOnRestApiLink()
                .getRestApiPageTitle();

        Assert.assertEquals(mainPage, apiLink);
    }

    public void assertVersion () {
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href = 'https://www.jenkins.io/']")).getText(),"Jenkins 2.387.2" );
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

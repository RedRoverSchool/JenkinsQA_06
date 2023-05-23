package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.model.RestApiPage;
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
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(getDriver())
                .navigateToManageJenkinsPage()
                        .scrollToFooterPageByJenkinsVersionBTN();

        Assert.assertTrue(manageJenkinsPage.getVersionJenkinsFromFooter(), "Wrong version Jenkins");
    }

    @Test
    public void testVerifyRESTAPILink() {
        RestApiPage restApiPage = new MainPage(getDriver())
                .scrollToRestApiInFooter()
                .clickOnRestApiLink();

        Assert.assertTrue(restApiPage.isRestApiPageIsOpen(), "RestApi page is not open");
    }

}

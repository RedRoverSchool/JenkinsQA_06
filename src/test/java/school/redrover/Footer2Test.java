package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class Footer2Test extends BaseTest {

    @Test
    public void testRestApiLink() {
      String restApiTitle = new MainPage(getDriver()).clickOnRestApiLink().getRestApiPageTitle();

        Assert.assertEquals(restApiTitle,"REST API");
    }

    @Ignore
    @Test
    public void testJenkinsSiteOpenOnManageJenkinsPage()  {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();

        WebElement jenkinsVersionLink= getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']"));

        new Actions(getDriver())
                .scrollToElement(jenkinsVersionLink)
                .pause(Duration.ofSeconds(1))
                .click(jenkinsVersionLink)
                .perform();

        for (String window : getDriver().getWindowHandles()) {
             getDriver().switchTo().window(window);
        }

        WebElement jenkinsSiteTitle =getDriver().findElement(By.xpath("//h1"));
        getWait2().until(ExpectedConditions.visibilityOf(jenkinsSiteTitle));

        Assert.assertEquals(jenkinsSiteTitle.getText(), "Jenkins");
    }
}

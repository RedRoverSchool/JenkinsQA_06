package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class Footer2Test extends BaseTest {

    @Test
    public void testRestApiLink() {
        getDriver().findElement(By.xpath("//a[contains(@href,'api')]")).click();

        WebElement restApiPage =getDriver().findElement(By.xpath("//h1"));

        getWait2().until(ExpectedConditions.visibilityOf(restApiPage));
        Assert.assertEquals(restApiPage.getText(),"REST API");
    }

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

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultibranchPipeline26Test extends BaseTest {
    private static final String NAME_MULTIBRANCH_PROJECT = "multibranch";
    @Test
    public void testCreate() {

        getDriver().findElement(By.xpath("//div[@class='task '][1]")).click();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(NAME_MULTIBRANCH_PROJECT);

        getDriver().findElement(By.xpath("//*[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        WebElement multibranchElement = getDriver().findElement(By.xpath("//*[@id='job_multibranch']/td[3]/a/span"));
        Assert.assertEquals(multibranchElement.getText(), NAME_MULTIBRANCH_PROJECT);
    }
}

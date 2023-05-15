package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject2Test extends BaseTest {

    @Test
    public void testCreateMultiConfigurationProject() {
        final String nameOfProject = "MyMultiConfigurationProject";

        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector("#items"))));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("[name='Submit']")))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("#jenkins-head-icon")))).click();

        WebElement projectName = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector(".job-status-nobuilt td>a>span"))));

        Assert.assertEquals(projectName.getText(), nameOfProject);
    }
}

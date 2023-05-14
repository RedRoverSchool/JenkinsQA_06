package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class PipelineItemTest extends BaseTest {

    private final String pipelineName = "Test Pipeline";

    @Test
    public void testPipelineCreation()  {

        getDriver().findElement(By.cssSelector(".task:first-child")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("name")))).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getDriver().findElement(By.cssSelector("[name='Submit']")).click();

        getDriver().findElement(By.cssSelector(".jenkins-breadcrumbs__list-item:first-child .model-link")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='"+ pipelineName +"']")).isDisplayed());
    }

    @Test(dependsOnMethods = { "testPipelineCreation" })
    public void testPipelineDeletion() {

        WebElement pipeline = getDriver().findElement(By.xpath("//span[text()='"+ pipelineName +"']"));
        WebElement arrowDropdown = getDriver().findElement(By.xpath("//span[text()='"+ pipelineName +"']/following-sibling::button"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(pipeline).pause(Duration.ofSeconds(4)).perform();
        actions.moveToElement(arrowDropdown).click().perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector(".icon-edit-delete+span")))).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getDriver().findElements(By.xpath("//span[text()='"+ pipelineName +"']")).size()==0);
    }
}
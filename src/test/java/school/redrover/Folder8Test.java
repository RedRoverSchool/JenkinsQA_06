package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.util.List;

public class Folder8Test extends BaseTest {

    @Test
    public void testCreateFolderWithoutDescription() {
        final String expectedFolderName = "First folder";

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(expectedFolderName);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        WebElement titleName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        Assert.assertEquals(titleName.getText(), expectedFolderName);
    }

    @Test
        public void testCreatePipelineProjectWithoutDescriptionInFolder() {
            final String folderName = RandomStringUtils.randomAlphanumeric(8);
            final String pipelineName = RandomStringUtils.randomAlphanumeric(8);
            boolean isPipelinePresent = false;

            TestUtils.createFolder(this, folderName, false);

            getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();

            getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(pipelineName);
            getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
            getDriver().findElement(By.id("ok-button")).click();

            getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

            String actualPipelineName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
            getDriver().findElement(By.linkText("Dashboard")).click();

            getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='projectstatus']"))).findElement(By.linkText(folderName)).click();

            getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='projectstatus']")));
            List<WebElement> jobs = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));
            for (WebElement job : jobs) {
                String jobName = job.getText();
                if ( jobName.contains(pipelineName) ) {
                    isPipelinePresent = true;
                    break;
                }
            }

            Assert.assertEquals(actualPipelineName, "Pipeline " + pipelineName);
            Assert.assertTrue(isPipelinePresent);
        }
}

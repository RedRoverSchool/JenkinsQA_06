package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineJobTest extends BaseTest {

    private void backToDashboard() {
        getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li[1]/a")
        ).click();
    }
    @Ignore
    private void CreatePipelineProjectJob(String nameProject, String displayName) {
        getDriver().findElement(
                By.xpath("//*[@id='tasks']/div[1]/span/a")
        ).click();
        getDriver().findElement(
                By.name("name")
        ).sendKeys(nameProject);
        getDriver().findElement(
                By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]")
        ).click();
        getDriver().findElement(
                By.xpath("//*[@id='ok-button']")
        ).click();

        new Actions(getDriver())
                .scrollByAmount(0, 600)
                .click(getDriver().findElement(
                        By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[2]/div[1]/button")
                ))
                .perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[3]/div/div[2]/input")
        )).sendKeys(displayName);
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='main-panel']/form/div[1]/div[7]/div[3]/div/div/div[2]/div[2]/div/div[1]/select/option[2]")
        )).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.name("Submit")
        )).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='tasks']/div[3]/span/a")
        )).click();
        backToDashboard();
    }

    @Test
    public void testCreatePipelineProjectJob() {
        CreatePipelineProjectJob("RedRover","R&R");

        Assert.assertEquals(getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='job_RedRover']/td[3]/a/span"
                ))).getText(),"R&R");
    }
}

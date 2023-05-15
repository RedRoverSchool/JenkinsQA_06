package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProject2aTest extends BaseTest {
    @Test
    public void testCreateFreestyleProject() {
        String ProjectName = "Project";
        String Opisanie = "Opisanie";

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']")))
                .click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name = 'name'][@id = 'name']")))
                .sendKeys(ProjectName);
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")))
                .click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id = 'ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea [@name = 'description']")))
                .sendKeys(Opisanie);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@class = 'jenkins-button jenkins-button--primary ']")))
                .click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/'][@class = 'model-link']")))
                .click();

        String ExpectedName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'jenkins-table__link model-link inside']")))
                .getText();

        Assert.assertEquals(ProjectName, ExpectedName);
    }
}

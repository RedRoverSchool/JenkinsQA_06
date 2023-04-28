package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateTestsOnTheWelcomePage extends BaseTest {

    final String NAME_FIRST_PROJECT = "firstTest";

    @Test
    public void testNameTheFirstJob() {

        WebElement createAJobLink = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        createAJobLink.click();
        WebElement inputJobName = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        inputJobName.sendKeys(NAME_FIRST_PROJECT);
        WebElement pressFreestyleProject = getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[@class='hudson_model_FreeStyleProject']//span[@class='label']"));
        pressFreestyleProject.click();
        WebElement pressOkButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        pressOkButton.click();
        WebElement pressSubmitButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        pressSubmitButton.click();
        WebElement pressDashboardButton = getDriver().findElement(By.xpath("//li/a[@href='/'] [@class='model-link']"));
        pressDashboardButton.click();
        WebElement createdNameJob = getDriver().findElement(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span['test']"));
        String actualJobName = createdNameJob.getText();

        Assert.assertEquals(actualJobName, NAME_FIRST_PROJECT);
    }
}
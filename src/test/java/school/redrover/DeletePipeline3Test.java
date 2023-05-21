package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipeline3Test extends BaseTest {

    String namePipeline = "testPipeline";

    @Test
    public void testCreatePipeline() {
        getDriver().findElement(By.cssSelector(".task:first-child")).click();
        getWait10()
                .until(ExpectedConditions.elementToBeClickable((By.xpath("//input[@name='name']"))))
                .sendKeys(namePipeline);
        WebElement pipelineButton = getDriver().findElement(By.xpath("//div[contains(text(), 'Orchestrates')]"));
        pipelineButton.click();
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitButton.click();
        getDriver().findElement(By.xpath("//a[contains(text(), '" + namePipeline + "')]"))
                .isDisplayed();
    }

    @Test(dependsOnMethods = { "testCreatePipeline" })
    public void testDeletePipeline (){
        WebElement dashboardLink = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardLink.click();

        Actions actions = new Actions(getDriver());
        WebElement testPipelineLink =  getWait10()
                .until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[@href='job/" + namePipeline + "/']/span"))));
        actions.moveToElement(testPipelineLink).perform();
        getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + namePipeline + "/']/button"))).click();
        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]"))
                .click();

        getDriver().switchTo().alert().accept();

        Boolean isTestPipelineAbsence =
                ExpectedConditions.not(ExpectedConditions
                        .numberOfElementsToBe(By.xpath("//a[contains(text(), " + namePipeline + ")]"), 1))
                        .apply(getDriver());

        Assert.assertTrue(isTestPipelineAbsence);
    }
}

package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline10Test extends BaseTest {

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

        Actions action = new Actions(getDriver());
        WebElement testPipelineLink =  getWait10()
                .until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[@href='job/" + namePipeline + "/']/span"))));
        action.moveToElement(testPipelineLink).perform();

        Actions action1 = new Actions(getDriver());
        WebElement dropdownMenuButton = getDriver().findElement(By.xpath("//a[@href='job/" + namePipeline + "/']/button"));
        action1.moveToElement(dropdownMenuButton).perform();
        dropdownMenuButton.sendKeys(Keys.RETURN);

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

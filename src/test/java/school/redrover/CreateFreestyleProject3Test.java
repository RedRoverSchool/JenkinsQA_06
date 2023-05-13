package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProject3Test extends BaseTest {
    private static final String NAME = "Dynamo Kyiv";
    private static final String COMMAND_TEXT = "echo Hello";

    public void moveToElement(By by) {
        new Actions(getDriver()).moveToElement(getDriver()
                .findElement(by)).click().perform();
    }

    public void waitTwoSecondAndClick(By by) {
        getWait2().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitFiveSecondAndClick(By by) {
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitTenSecondAndClick(By by) {
        getWait10().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void findElementAndClick(By by) {
        getDriver().findElement(by).click();
    }

    public void waitTwoSecondAndInputText(By by, String text) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    @Test
    public void testCreateAndBuildFreestyleProject() {

        moveToElement(By.xpath("//*[@id='breadcrumbs']/li"));
        waitTwoSecondAndClick(By.xpath("//a[@href='/view/all/newJob']"));
        waitTwoSecondAndInputText(By.xpath("//*[@id='name']"), NAME);
        findElementAndClick(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        findElementAndClick(By.xpath("//*[@id='ok-button']"));
        waitTwoSecondAndInputText(By.xpath("//*[@name='description']"), NAME);

        new Actions(getDriver())
                .scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(), 0, 2600)
                .perform();

        waitTenSecondAndClick(By.xpath("//button[contains(text(), 'Add build step')]"));
        moveToElement(By.xpath("//a[contains(text(), 'Execute Windows batch command')]"));
        waitTwoSecondAndInputText(By.xpath("//textarea[@name = 'command']"), COMMAND_TEXT);
        findElementAndClick(By.xpath("//button[@name='Submit']"));
        waitFiveSecondAndClick(By.xpath("//a[contains(@href, 'build?delay')]"));
        //waitTenSecondAndClick(By.xpath("//a[@class ='model-link inside build-link display-name']"));
        waitFiveSecondAndClick(By.xpath("//a[@class='build-status-link']"));

        WebElement expectedResult = getWait2().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//pre[@class = 'console-output']")));
        Assert.assertTrue(expectedResult.getText().contains("Finished: SUCCESS"));
    }
}

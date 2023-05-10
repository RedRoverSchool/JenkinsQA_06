package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem2Test extends BaseTest {
@Ignore
    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        final String expectedNameOfMultibranchPipeline = "MyMultibranchPipeline";

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.click();
        fieldInputName.sendKeys(expectedNameOfMultibranchPipeline);

        WebElement buttonMultibranchPipeline = getDriver().findElement(By.xpath("//span[text() = 'Multibranch Pipeline']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()", buttonMultibranchPipeline);

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@type='submit']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        String actualNameOfMultibranchPipeline = titleName.getText();
        Assert.assertEquals(actualNameOfMultibranchPipeline, expectedNameOfMultibranchPipeline);
    }
}

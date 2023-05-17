package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject1Test extends BaseTest {
    private  final String PROJECT_NAME = "Project1";

    @Test
    public void testRenamePipelineProjectOnPipelineProjectPage()  {
        String newProjectName = "Project12";

        TestUtils.createPipeline(this, PROJECT_NAME,true);

        WebElement actualProjectName = getDriver().findElement(By.xpath("//td/a[contains(@href,'job')]/span"));
        actualProjectName.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//a[contains(@href,'confirm-rename')]")))).click();

        WebElement newNameInputField = getDriver().findElement(By.xpath("//input[@checkdependson='newName']"));
        newNameInputField.clear();
        newNameInputField.sendKeys(newProjectName);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")). getText(),"Pipeline " + newProjectName);
    }
}

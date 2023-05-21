package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.security.Key;
import java.time.Duration;

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

    @Test
    public void testDeletePipelineProjectOnDashboardProjectPage(){
        TestUtils.createPipeline(this, PROJECT_NAME,true);

        Actions action = new Actions(getDriver());
        WebElement projectNameButton=getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/a[@href='job/"+PROJECT_NAME+"/']")));
        action.moveToElement(projectNameButton).perform();

        WebElement dropDownMenuButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/a/button")));
        dropDownMenuButton.sendKeys(Keys.ENTER);

        WebElement deletePipeline = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Delete Pipeline']")));
        deletePipeline.click();

        getDriver().switchTo().alert().accept();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Welcome to Jenkins!");
    }

}

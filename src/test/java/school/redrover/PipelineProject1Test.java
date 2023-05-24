package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.PipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject1Test extends BaseTest {
    private  final String PROJECT_NAME = "Project1";

    @Test
    public void testRenamePipelineProjectOnPipelineProjectPage()  {

        TestUtils.createPipeline(this, PROJECT_NAME,false);
        String newProjectName = "Project2";

        new PipelinePage(getDriver())
                .clickOnRenameButtonOnSideMenu()
                .changeProjectName(newProjectName)
                .clickRenameButton();

        Assert.assertEquals(new PipelinePage(getDriver()).getNewProjectName(),"Pipeline " + newProjectName);
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

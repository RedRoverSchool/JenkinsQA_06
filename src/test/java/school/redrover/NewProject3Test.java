package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.BreadcrumbBar;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;

public class NewProject3Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String nameProject = "Engineer2";

        new MainPage(getDriver())
                .newItem()
                .enterItemName(nameProject)
                .selectFreestyleProjectAndOk()
                .projectSave();
        new BreadcrumbBar(getDriver()).selectDashboard();

        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer2/']")).getText();
        Assert.assertEquals(actualResult, nameProject);
    }

    @Test
    public void testCreatePipProject() {
        String expectedPipeline = "Pipeline Engineer";
        String nameProject = "Engineer";

        new MainPage(getDriver())
                .newItem()
                .enterItemName(nameProject)
                .selectPipelineAndOk()
                .clickSaveButton();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='headline']")).getText(), expectedPipeline);

        new BreadcrumbBar(getDriver()).selectDashboard();
        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer/']")).getText();
        Assert.assertEquals(actualResult, nameProject);
    }

    @Test
    public void testCreateMultiConfigurationProject() {

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        new NewJobPage(getDriver()).enterItemName("Engineer3").selectMultiConfigurationProjectAndOk();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("li:nth-child(1) > a")).click();

        WebElement result = getDriver().findElement(By.cssSelector("#projectstatus"));
        Assert.assertTrue(result.isDisplayed(), "project no display");
    }


}

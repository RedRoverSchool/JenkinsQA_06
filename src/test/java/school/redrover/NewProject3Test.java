package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.BreadcrumbBarPage;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;

public class NewProject3Test extends BaseTest {

     private final By HEADER_PIPELINE = By.cssSelector("[class$='headline']");

    @Test
    public void testCreateFreestyleProject() {
        String nameProject = "Engineer2";

        new MainPage(getDriver())
                .newItem()
                .enterItemName(nameProject)
                .selectFreestyleProjectAndOk()
                .projectSave();
        new BreadcrumbBarPage(getDriver()).selectDashboard();

        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(), nameProject);
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

        Assert.assertEquals(getDriver().findElement(HEADER_PIPELINE).getText(), expectedPipeline);
        new BreadcrumbBarPage(getDriver()).selectDashboard();
        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(), nameProject);
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

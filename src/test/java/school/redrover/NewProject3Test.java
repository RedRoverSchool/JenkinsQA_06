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
                .clickNewItem()
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
                .clickNewItem()
                .enterItemName(nameProject)
                .selectPipelineAndOk()
                .clickSaveButton();

        Assert.assertEquals(getDriver().findElement(HEADER_PIPELINE).getText(), expectedPipeline);
        new BreadcrumbBarPage(getDriver()).selectDashboard();
        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(), nameProject);
    }

    @Test
    public void testCreateMultiConfigurationProject() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer3")
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage();
        new BreadcrumbBarPage(getDriver()).selectDashboard();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("#projectstatus")).isDisplayed(), "project no display");
    }


}

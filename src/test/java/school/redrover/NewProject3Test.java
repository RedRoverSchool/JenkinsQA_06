package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.PipelinePage;
import school.redrover.runner.BaseTest;

public class NewProject3Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String nameProject = "Engineer2";

        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem()
                .enterItemName(nameProject)
                .selectFreestyleProjectAndOk()
                .clickSave();
        mainPage.selectDashboard();

        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(), nameProject);
    }

    @Test
    public void testCreatePipProject() {
        String expectedPipeline = "Pipeline Engineer";
        String nameProject = "Engineer";

        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem()
                .enterItemName(nameProject)
                .selectPipelineAndOk()
                .clickSaveButton();

        Assert.assertEquals(new PipelinePage(getDriver()).getHeaderPipeline().getText(), expectedPipeline);
        mainPage.selectDashboard();
        Assert.assertEquals(new MainPage(getDriver()).getProjectName().getText(), nameProject);
    }

    @Test
    public void testCreateMultiConfigurationProject() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem()
                .enterItemName("Engineer3")
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage();
        mainPage.selectDashboard();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("#projectstatus")).isDisplayed(), "project no display");
    }

    @Test
    public void testCreateNewFolder() {
        String nameProject = "Engineer";

        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem()
                .enterItemName(nameProject)
                .selectFolderAndOk()
                .clickSaveButton();
        mainPage.selectDashboard();

        Assert.assertTrue(mainPage.getFolderName().isDisplayed());
        mainPage.getFolderName().click();
        Assert.assertTrue(new FolderPage(getDriver()).getHeading1().getText().contains(nameProject), "folder cannot be opened");
    }

    @Test
    public void testCreateMultibranchPipeline() {
        String nameJob = "Engineer";
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickNewItem()
                .enterItemName(nameJob)
                .selectMultibranchPipelineAndOk()
                .saveButton();
        mainPage.selectDashboard();

        Assert.assertEquals(mainPage.getFolderName().getText(), nameJob);
    }
}

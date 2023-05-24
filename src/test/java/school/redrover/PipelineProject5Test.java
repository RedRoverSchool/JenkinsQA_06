package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.PipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject5Test extends BaseTest {
    public static String name = "My New Pipeline Project";
    public static String rename = "Pipeline Project";

    @Test
    public void testCreatePipelineProject() {
        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(name)
                .selectPipelineAndOk().clickSaveButton();

        Assert.assertEquals(pipelinePage.getProjectName(), "Pipeline " + name);
        Assert.assertEquals(pipelinePage.clickDashboard()
                .getJobWebElement(name).getText(), name);
    }

    @Test(dependsOnMethods = "testCreatePipelineProject")
    public void testDeletePipelineProject() {
        MainPage mainPage = new MainPage(getDriver())
                .clickPipelineProject(name.replaceAll(" ", "%20"))
                .clickDeletePipeline().acceptAlert();

        Assert.assertEquals(mainPage.getNoJobsMainPageHeader().getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testDeletePipelineProjectDropDown() {
        TestUtils.createPipeline(this, name, true);

        MainPage mainPage = new MainPage(getDriver())
                .clickJobDropDownMenu(name.replaceAll(" ", "%20"))
                .selectDeleteFromDropDownMenu()
                .acceptAlert();

        Assert.assertEquals(mainPage.getNoJobsMainPageHeader().getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testRenamePipelineFromProjectPage() {
        TestUtils.createPipeline(this, name, true);

        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickPipelineProject(name.replaceAll(" ", "%20"))
                .clickRename()
                .clearNameField()
                .enterNewName(rename)
                .clickRenameButton();

        Assert.assertEquals(pipelinePage.getProjectName(), "Pipeline " + rename);
        Assert.assertEquals(pipelinePage.clickDashboard()
                .getJobWebElement(rename).getText(), rename);
    }

    @Test
    public void testRenamePipelineDropDownMenu() {
        TestUtils.createPipeline(this, name, true);

        FolderPage folderPage = new MainPage(getDriver())
                .clickJobDropDownMenu(name.replaceAll(" ", "%20"))
                .clickRenameInDropDownMenu()
                .setNewName(rename)
                .clickRenameButton();

        Assert.assertEquals(folderPage.getFolderDisplayName(), "Pipeline " + rename);
        Assert.assertEquals(folderPage.clickDashboard()
                .getJobWebElement(rename).getText(), rename);
    }

    @Test
    public void testDisablePipeline() {
        TestUtils.createPipeline(this, name, true);
        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickPipelineProject(name.replaceAll(" ", "%20"))
                .clickDisableProject();

        Assert.assertTrue(pipelinePage.getEnableButton());
        Assert.assertEquals(pipelinePage.clickDashboard()
                .getJobBuildStatusIcon(name), "Disabled");
    }

    @Test
    public void testEnablePipeline() {
        TestUtils.createPipeline(this, name, true);
        PipelinePage pipelinePage = new MainPage(getDriver())
                .clickPipelineProject(name.replaceAll(" ", "%20"))
                .clickDisableProject()
                .clickEnableProject();

        Assert.assertTrue(pipelinePage.getDisableButton());
        Assert.assertEquals(pipelinePage.clickDashboard()
                .getJobBuildStatusIcon(name), "Not built");
    }

    @Test
    public void testPipelineNameUnsafeChar() {
        String[] testStrings = {"*", "&", "^", "%", "$", "#", "@", "!"};

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']"))));
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']"))).click();

        for (int i = 0; i < testStrings.length; i++) {
            String letter = testStrings[i];
            getDriver().findElement(By.id("name")).sendKeys(letter);

            WebElement message = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
            getWait2().until(ExpectedConditions.visibilityOf(message));

            Assert.assertEquals(message.getText(), "»" + " ‘" + letter + "’ " + "is an unsafe character");
            getWait2().until(ExpectedConditions.visibilityOf(message));
            getDriver().findElement(By.id("name")).clear();
        }
    }

    @Test
    public void testPipelineNameAllowedChar() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']")).click();
        getDriver().findElement(By.id("name")).sendKeys("_-+=”{},");
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        WebElement projectNameDashboard = getDriver().findElement(By.xpath("//td/a/span"));

        Assert.assertEquals(projectNameDashboard.getText(), "_-+=”{},");
    }
}
package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.MultibranchPipelineConfigPage;
import school.redrover.model.MultibranchPipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class MultibranchPipelineTest extends BaseTest {
    @Test
    public void createMultibranchPipelineTest() {
        MultibranchPipelinePage mainPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .displayName("Random name")
                .addDescription("Random Description")
                .clickSaveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())).titleMultibranchPipeline().getText().contains("Random Name"));
    }

    @Test
    public void testRenameMultibranchPipeline() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .clickSaveButton()
                .renameMultibranchPipelinePage()
                .enterNewName("MultibranchPipeline")
                .submitNewName();

        Assert.assertTrue(new MultibranchPipelinePage(getDriver()).multibranchPipeline().getText().contains("MultibranchPipeline"));
    }
    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        MultibranchPipelinePage pageWithOutDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipelineWhitOutDescription")
                .selectMultibranchPipelineAndOk()
                .displayName("Random name")
                .clickSaveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver())).viewDescription().getText().isEmpty());
    }
    @Test
    public void deleteMultibranchPipelineTest() {
        String WelcomeJenkinsPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .dropDownMenuClickDeleteFolders("MultibranchPipeline")
                .clickYes()
                .getWelcomeWebElement()
                .getText();

        Assert.assertEquals(WelcomeJenkinsPage, "Welcome to Jenkins!");
    }

    @Test
    public void testMoveMultibranchPipelineToFolder(){

        TestUtils.createFolder(this, "Folder", true);
        TestUtils.createMultibranchPipeline(this, "MultibranchPipeline", true);

        WebElement nameMultibranchPipeline = new MainPage(getDriver())
                .dropDownMenuClickMove("MultibranchPipeline",new FolderPage(getDriver()))
                .selectDestinationFolder("Folder")
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName("Folder")
                .getMultibranchPipelineName();

        Assert.assertTrue(nameMultibranchPipeline.isDisplayed());
    }

    @Test
    public void testCreateMultibranchPipelineWithDescription() {
        String MultibranchPipeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("RandomName")
                .selectMultibranchPipelineAndOk()
                .addDescription("DESCRIPTION")
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickMultibranchPipelineName("RandomName")
                .getDescription();

        Assert.assertEquals(MultibranchPipeline, "DESCRIPTION");
    }

    @Test
    public void createMultiPipeline(){
        for (int i = 0 ;i < 4; i++){
            String jobName = "M0"+i;
            TestUtils.createMultibranchPipeline(this,jobName,true);
        }
        MainPage mainPage = new MainPage(getDriver());
        List<String> jobs = mainPage.getJobList();
        Assert.assertTrue(jobs.size()==4);
    }
    @Test(dependsOnMethods = "createMultiPipeline")
    public void testFindCreatedMultibranchPipelineOnDashboard(){
        MainPage mainPage = new MainPage(getDriver());
        boolean status = mainPage.verifyJobIsPresent("M00");
        Assert.assertTrue(status);
    }
}

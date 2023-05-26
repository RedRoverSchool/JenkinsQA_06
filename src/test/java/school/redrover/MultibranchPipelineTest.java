package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import school.redrover.model.ProjectPage;
import school.redrover.runner.TestUtils;



public class MultibranchPipelineTest extends BaseTest {
    @Test
    public void createMultibranchPipelineTest() {
        MultibranchPipelinePage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .displayName("Random name")
                .enterDescription("Random Description")
                .saveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(getDriver()).titleMultibranchPipeline().getText().contains("Random Name"));
    }

    @Test
    public void testRenameMultibranchPipeline() {
        RenameMultibranchPipelinePage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .saveButton()
                .renameMultibranchPipelinePage()
                .enterNewName("MultibranchPipeline")
                .renameButton();

        Assert.assertTrue(new MultibranchPipelinePage(getDriver()).multibranchPipeline().getText().contains("MultibranchPipeline"));
    }
    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        MultibranchPipelinePage pageWithOutDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MineMultibranchPipelineWhitOutDescription")
                .selectMultibranchPipelineAndOk()
                .displayName("Random name")
                .saveButton();

        Assert.assertTrue(new MultibranchPipelineConfigPage(getDriver()).viewDescription().getText().isEmpty());
    }
    @Test
    public void deleteMultibranchPipelineTest() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MultibranchPipeline")
                .selectMultibranchPipelineAndOk()
                .saveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickJobDropDownMenu("MultibranchPipeline")
                .selectDeleteFromDropDownMenu()
                .clickYesDeleteJobDropDownMenu();

        Assert.assertEquals(new MainPage(getDriver()).getWelcomeWebElement().getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testCreateMultiPipeline() {

        final String nameMultiPipeline = "Multi";

        new MainPage(getDriver())
                .clickNewItemButton()
                .inputAnItemName(nameMultiPipeline)
                .clickMultiBranchPipeline()
                .clickSaveButton()
                .clickSaveButton()
                .clickDashBoardButton();

        String actualMultiBranchName = getDriver().findElement(By.xpath("//a[@href = 'job/Multi/']")).getText();

        Assert.assertEquals(actualMultiBranchName,nameMultiPipeline);
    }
}

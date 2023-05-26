package school.redrover;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultibranchPipelineConfigPage;
import school.redrover.model.MultibranchPipelinePage;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import school.redrover.model.ProjectPage;
import school.redrover.runner.TestUtils;


public class MultibranchPipelineTest extends BaseTest {
    @Test
    public void createMultibranchPipelineTest()  {
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
}
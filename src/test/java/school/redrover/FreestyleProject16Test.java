package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FreestyleProjectPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject16Test extends BaseTest {

    public static final String FREESTYLE_PROJECT_NAME = "New Freestyle Project";

    @Test
    public void testDisableFreestyleProject() {
        TestUtils.createFreestyleProject(this, "New Freestyle Project", true);

        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickFreestyleProjectName(FREESTYLE_PROJECT_NAME)
                .clickTheDisableProjectButton();
        Assert.assertEquals(freestyleProjectPage.getWarningMessage(), "This project is currently disabled");

        MainPage mainPage = freestyleProjectPage.navigateToMainPageViaJenkinsIcon();
        Assert.assertEquals(mainPage.getJobBuildStatusIcon(FREESTYLE_PROJECT_NAME), "Disabled");
    }

    @Test(dependsOnMethods = "testDisableFreestyleProject")
    public void testEnableFreestyleProject() {
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickFreestyleProjectName(FREESTYLE_PROJECT_NAME)
                .clickTheEnableProjectButton();

        Assert.assertTrue(freestyleProjectPage.isProjectDisabledButtonDisplayed());

        MainPage mainPage = freestyleProjectPage.navigateToMainPageViaJenkinsIcon();
        Assert.assertEquals(mainPage.getJobBuildStatusIcon(FREESTYLE_PROJECT_NAME), "Not built");
    }
}

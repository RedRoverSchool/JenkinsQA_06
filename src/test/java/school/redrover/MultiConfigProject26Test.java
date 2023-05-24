package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class MultiConfigProject26Test extends BaseTest {

    private static final String NAME_MULTICONFIG_PROJECT = "multiconfig";
    private static final String DESCRIP_MULTICONFIG_PROJECT = "multiconfig";
    @Test
    public void testCreate() {

        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME_MULTICONFIG_PROJECT)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .navigateToHomePageUsingJenkinsIcon()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), NAME_MULTICONFIG_PROJECT);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        WebElement projectDescription = new MainPage(getDriver())
                .clickJobWebElement(NAME_MULTICONFIG_PROJECT)
                .getAddDescription(DESCRIP_MULTICONFIG_PROJECT)
                .getSaveButton()
                .getInputAdd();

        Assert.assertEquals(projectDescription.getText(), DESCRIP_MULTICONFIG_PROJECT);
    }
}

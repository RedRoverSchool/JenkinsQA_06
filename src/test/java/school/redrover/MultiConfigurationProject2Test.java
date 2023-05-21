package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject2Test extends BaseTest {

    @Test
    public void testCreateMultiConfigurationProject()  {
        final String nameOfProject = "MyMultiConfigurationProject";

        WebElement projectName = new MainPage(getDriver())
                .newItem()
                .enterItemName(nameOfProject)
                .selectMultiConfigurationProjectAndOk()
                .saveConfigurePageAndGoToProjectPage()
                .navigateToHomePageUsingJenkinsIcon()
                .getProjectName();

        Assert.assertEquals(projectName.getText(), nameOfProject);
    }
}

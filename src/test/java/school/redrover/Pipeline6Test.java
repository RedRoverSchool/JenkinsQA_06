package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class Pipeline6Test extends BaseTest {

    private static final String NAME_PROJECT = RandomStringUtils.randomAlphanumeric(10);

    @Test
    public void testCreatePipelineProject() {

        WebElement pipeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME_PROJECT)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
                .getProjectName();

        Assert.assertEquals(pipeline.getText(), NAME_PROJECT);
    }
}

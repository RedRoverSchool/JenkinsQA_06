package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class PipelineProject10Test extends BaseTest {

    @Test
    public void testCreatePipelineProject() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("newProject")
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard();

                Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='newProject']"))
                        .getText(), "newProject");
    }
}

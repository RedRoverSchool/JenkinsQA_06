package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject7Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String freestyleProjectName = "Freestyle Project";

        TestUtils.createFreestyleProject(this, freestyleProjectName, true);

        WebElement newProjectOnTheDashboard = getWait5()
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//tbody//td[3]/a/span")));

        Assert.assertEquals(newProjectOnTheDashboard.getText(), freestyleProjectName);
    }
}

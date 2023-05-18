package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreeStyleProject13Test extends BaseTest {
    private String name = "FreestyleProject1";

    @Test
    public void testCreateFreestyleProjectNewItem() {
        TestUtils.createFreestyleProject(this, name, false);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//div[@id ='main-panel']//h1[text()='Project " + name + "']"))
                .getText(), "Project " + name);
    }

    @Test
    public void testFindFreestyleProjectOnDashboard() {
        TestUtils.createFreestyleProject(this, name, true);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//tr[@id='job_" + name + "']//a//span['" + name + "']"))
                .getText(), name);
    }
}
package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class OksanaTest extends BaseTest {
    @Test
    public void testSideElementTasks() {
        WebElement sidePanel = getDriver().findElement(By.xpath("//div[@id = 'tasks']"));
        List<WebElement> sideElementTask = sidePanel.findElements(By.tagName("a"));
        Assert.assertEquals(sideElementTask.size(), 5);
    }

}

package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupOlesyaTest extends BaseTest {

    @Test
    public void testInputHelpMsg() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();

        String expectedResultMsg = getDriver()
                .findElement(By.xpath("//div[@class = 'input-help']"))
                .getText();

        Assert.assertEquals(expectedResultMsg, "» Required field");
    }
}

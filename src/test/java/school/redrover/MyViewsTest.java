package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MyViewsTest extends BaseTest {

    @Test
    public void testMoveToMyViewsPage() {
        String firstUrl = getDriver().getCurrentUrl();
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        String secondUrl = getDriver().getCurrentUrl();

        Assert.assertNotEquals(firstUrl,secondUrl);
    }
}

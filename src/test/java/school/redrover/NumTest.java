package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class NumTest extends BaseTest {

    @Test
    public  void wikiTest() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        getDriver().get("https://wikipedia.org");

        Assert.assertEquals(getDriver().getTitle(),"Wikipedia");
        getDriver().quit();
    }
}

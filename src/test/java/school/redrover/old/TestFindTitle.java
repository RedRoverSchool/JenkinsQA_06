package school.redrover.old;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
@Ignore
public class TestFindTitle extends BaseTest {

    @Test
    public  void testYandex() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        getDriver().get("https://Yandex.ru");
        Assert.assertEquals(getDriver().getTitle(),"Дзен");
        getDriver().quit();
    }
}
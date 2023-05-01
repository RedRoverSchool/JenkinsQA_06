package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class QaRabbitHoleTest extends BaseTest {

    @Test
    public void testWelcomeJenkins() {
        WebElement h1WelcomeJenkins = getDriver().findElement(By.cssSelector(".empty-state-block>h1"));

        Assert.assertEquals(h1WelcomeJenkins.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testDashboard() {
        WebElement dashboard = getDriver().findElement((By.xpath("//li/a[@class='model-link']")));

        Assert.assertEquals(dashboard.getText(), "Dashboard");
    }
}

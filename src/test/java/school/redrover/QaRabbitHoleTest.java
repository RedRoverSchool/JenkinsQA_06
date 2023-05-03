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

    @Test
    public void testCreateJobTitle() {
        WebElement createjob = getDriver().findElement(By.xpath("//a[@href = 'newJob']/span"));

        Assert.assertEquals(createjob.getText(), "Create a job");
    }

    @Test
    public void testLogout() {
        WebElement logout = getDriver().findElement((By.xpath("//a[@href='/logout']")));
        logout.click();

        WebElement modalLogin = getDriver().findElement(By.xpath("//div[@class = 'simple-page']"));
        Assert.assertTrue(modalLogin.isDisplayed());
    }
}
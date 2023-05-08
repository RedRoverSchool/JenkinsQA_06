package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class AlexTest extends BaseTest {
    @Test
    public void testAddMultiConfProject() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement addNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        addNewItem.click();

        WebElement itemNameField = getDriver().findElement(By.xpath("//input[@id='name']"));
        itemNameField.sendKeys("Sample project");

        WebElement multiConfProject = getDriver().findElement(By.xpath("//span[normalize-space()='Multi-configuration project']"));
        multiConfProject.click();

        WebElement ok = getDriver().findElement(By.cssSelector("#ok-button"));
        ok.click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='description']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@class='jenkins-toggle-switch__label ']")).isEnabled());
    }
}

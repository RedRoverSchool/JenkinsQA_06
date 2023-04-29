package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class MyViewsTest extends BaseTest {

    @Test
    public void testMoveToMyViewsPage() {
        String firstUrl = getDriver().getCurrentUrl();
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        String secondUrl = getDriver().getCurrentUrl();

        Assert.assertNotEquals(firstUrl, secondUrl);
    }

    @Test
    public void testCreateAJobInThePageMyViews() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("First Project");
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();

        List<WebElement> table = getDriver().findElements(By.xpath("//tr[@class =' job-status-nobuilt']/td"));
        for (WebElement td : table) {

            Assert.assertTrue(td.getText().contains("First Project"));
        }
    }

    @Test
    public void testAddDescription() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Test");
        getDriver()
                .findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        WebElement description = getDriver().findElement(By.cssSelector("div#description"));

        Assert.assertEquals(description.getText().trim().substring(0, 4), "Test");
    }

}

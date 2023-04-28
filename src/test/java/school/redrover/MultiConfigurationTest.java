package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationTest extends BaseTest {

    @Test
    public void testCreateMultiConfig() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("test");
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("test");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='description']")).isDisplayed());
    }

    @Test
    public void testMoveToMultiConfigurationPage(){
        getDriver().findElement(By.linkText("New Item")).click();
        String gettingURL = getDriver().getCurrentUrl();
        String expectedURL = "http://localhost:8080/view/all/newJob";

        Assert.assertEquals(gettingURL,expectedURL);

        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).isDisplayed());
    }

}

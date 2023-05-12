package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Footer2Test extends BaseTest {

    @Test
    public void restApiLinkTest() {
        getDriver().findElement(By.xpath("//a[text()='REST API']")).click();

        WebElement restApiPage =getDriver().findElement(By.xpath("//h1[text()='REST API']"));

        getWait2().until(ExpectedConditions.visibilityOf(restApiPage));
        Assert.assertEquals(restApiPage.getText(),"REST API");
    }
}



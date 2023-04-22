package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class GroupAnaBelsTest extends BaseTest {
    @Ignore
    @Test
    public void testStasM() {
        getDriver().get("https://www.yahoo.com/");

        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id='ybarAccountProfile']/a"));
        submitButton.click();

        String title = getDriver().getTitle();
        Assert.assertEquals(title, "Yahoo");
    }
}


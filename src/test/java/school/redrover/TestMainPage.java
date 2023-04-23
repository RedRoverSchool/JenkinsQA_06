package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class TestMainPage extends BaseTest {
    @Test
    public void testMainPage() {

        WebElement startElement = getDriver().findElement(By.xpath("//div/section[1]/h2[@class = 'h4']"));
        Assert.assertEquals(startElement.getText(), "Start building your software project");

        WebElement setElement = getDriver().findElement(By.xpath("//div/section[2]/h2[@class = 'h4']"));
        Assert.assertEquals(setElement.getText(), "Set up a distributed build");
    }
}

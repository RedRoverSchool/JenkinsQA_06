package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupTwentyThreeTest extends BaseTest {
    @Test
    public void testJavaTutorial() throws InterruptedException {
        getDriver().get("https://www.w3schools.com/");

        WebElement textBox = getDriver().findElement(By.id("search2"));

        textBox.sendKeys("Java Tutorial\n");

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Java Tutorial");
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AndreiNevTest extends BaseTest {

    @Test
    public void testFirstJenkins() {

        WebElement startElement = getDriver().findElement(By.xpath("//section[@class='empty-state-section']/h2[text() = 'Start building your software project']"));
        Assert.assertEquals(startElement.getText(),"Start building your software project");

    }

}

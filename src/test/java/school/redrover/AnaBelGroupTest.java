package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class AnaBelGroupTest extends BaseTest {

    @Test
    public void testItem() throws InterruptedException {
        WebElement button = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        button.click();
        Thread.sleep(2000);
    }
}

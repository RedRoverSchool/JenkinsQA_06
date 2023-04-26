package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class GroupAndreyTest extends BaseTest {
    @Test
    public void testProjectCreation(){
        getDriver().findElement(By.xpath("//*[@href=\"newJob\"]")).click();

        getDriver().findElement(By.name("name")).sendKeys("qwerty");

        getDriver().findElement(By.xpath("//*[@class=\"hudson_model_FreeStyleProject\"]")).click();

        getDriver().findElement(By.className("btn-decorator")).click();

        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).click();

        getDriver().findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li[1]/a")).click();

        assertEquals(getDriver()
                .findElement(By.xpath("//*[@id=\"job_qwerty\"]/td[3]/a/span")).getText(), "qwerty");
    }
}

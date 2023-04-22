package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import org.testng.Assert;
@Ignore
public class Yuliya14Test extends BaseTest {

    @Test
    public void testComputerDatabase() {
        final String BASE_URL = "https://computer-database.gatling.io/computers";
        final String URL_COMPUTERS_381 = "https://computer-database.gatling.io/computers/381";

        getDriver().get(BASE_URL);

        Assert.assertEquals(getDriver().getTitle(),"Computers database");

        WebElement buttonACE = getDriver().findElement(By.xpath("//a[@href='/computers/381']"));
        buttonACE.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), URL_COMPUTERS_381);
    }
}

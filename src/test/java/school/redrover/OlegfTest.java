package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class OlegfTest extends BaseTest {
    @Test
    public void dishesTest() throws InterruptedException {

        getDriver().get("https://www.canvashomestore.com/");

        getDriver().findElement(By.name("q")).sendKeys("plate\n");
        List<WebElement> platesList = getDriver().findElements(By.xpath("//main//div[@class='grid__item']"));

        String finalPlate;
        for (int i = 0; i < platesList.size(); i++) {
            finalPlate = platesList.get(i).getText();
            assert finalPlate.contains("Blue");
        }

        getDriver().quit();
    }
}

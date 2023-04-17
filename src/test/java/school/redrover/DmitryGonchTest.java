package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DmitryGonchTest extends BaseTest {

    @Test
    public void testFirstDg() {
        getDriver().get("https://7745.by/");

        WebElement texBox = getDriver().findElement(By.name("keys"));
        texBox.sendKeys("процессор");
        texBox.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//h1[text() = 'Процессоры для компьютеров']"));

        Assert.assertEquals(text.getText(), "Процессоры для компьютеров");
    }
}

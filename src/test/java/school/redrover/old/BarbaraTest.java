package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class BarbaraTest extends BaseTest {
    @Ignore
    @Test
    public void testFindElement() {
        getDriver().get("https://www.w3schools.com/");

        WebElement textBox = getDriver().findElement(By.id("search2"));
        textBox.sendKeys("java ");
        WebElement button = getDriver().findElement(By.id("learntocode_searchbtn"));
        button.click();
        WebElement text = getDriver().findElement(By.xpath("//*[@id=\"leftmenuinnerinner\"]/a[1]"));

        Assert.assertEquals(text.getText(), "Java HOME");
    }
}



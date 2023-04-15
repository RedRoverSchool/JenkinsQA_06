package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
public class BarbaraTest extends BaseTest {
    @Test
    public void testFindElement() {
        getGetdriver().get("https://www.w3schools.com/");

        WebElement textBox = getGetdriver().findElement(By.id("search2"));
        textBox.sendKeys("java ");
        WebElement button = getGetdriver().findElement(By.id("learntocode_searchbtn"));
        button.click();
        WebElement text = getGetdriver().findElement(By.xpath("//*[@id=\"leftmenuinnerinner\"]/a[1]"));

        Assert.assertEquals(text.getText(), "Java HOME");
    }
}



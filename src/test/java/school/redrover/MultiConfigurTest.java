package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurTest extends BaseTest {
    private static final String NAME_OF_PROJECT = "New project";
    @Test
    public void createMultiConfigurationProjectTest() {
        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(NAME_OF_PROJECT);
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();
        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();
        getDriver().findElement(By.xpath("//*[@id='breadcrumbs']//a")).click();

        WebElement nameMultifigurationProject = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(nameMultifigurationProject.getText(),NAME_OF_PROJECT);
    }
}

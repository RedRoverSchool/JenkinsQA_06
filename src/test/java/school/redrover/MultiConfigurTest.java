package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurTest extends BaseTest {
    private static final String NAME_OF_PROJECT = "New project";
    private static final String DESCRIPTION ="Description";


    private void createBaseMultiConfigurationProject() {
        getDriver().findElement(By.xpath("//*[@id='tasks']//span/a")).click();
        getDriver().findElement(By.name("name")).sendKeys(NAME_OF_PROJECT);
        getDriver().findElement(By.xpath("//label//span[text() ='Multi-configuration project']")).click();
        getDriver().findElement(By.xpath("//div[@class ='btn-decorator']")).click();
        }

    @Test
    public void createMultiConfigurationProjectTest() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();

        WebElement nameMultifigurationProject = getDriver().findElement(By.xpath("//td//a//span[1]"));

        Assert.assertEquals(nameMultifigurationProject.getText(),NAME_OF_PROJECT);
    }

    @Test
    public void createMultiConfigurationProjectWithDescriptionTest() {
        createBaseMultiConfigurationProject();

        getDriver().findElement(By.name("description")).sendKeys(DESCRIPTION);
        getDriver().findElement(By.name("Submit")).click();

        WebElement nameDescription = getDriver().findElement(By.xpath("//div[@id ='description']//div"));

        Assert.assertEquals(nameDescription.getText(),DESCRIPTION);
    }
}

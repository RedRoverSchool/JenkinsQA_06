package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class CreateFreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String expectedProjectName = "Project Test";

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//*[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//*[@class='btn-decorator']")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        String actualProjectName = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualProjectName, expectedProjectName);
    }

    @Test
    public void testCreateFreestyleProject1() {
      String freestyleProjectName = "New job no.3";

      getDriver().findElement(By.cssSelector(".task-link")).click();

      getDriver().findElement(By.cssSelector("#name")).sendKeys(freestyleProjectName);
      getDriver().findElement(By.cssSelector(".label")).click();
      getDriver().findElement(By.cssSelector("#ok-button")).click();

      getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

      getDriver().findElement(By.cssSelector(".jenkins-breadcrumbs__list-item>.model-link")).click();

      Assert.assertEquals(getDriver().findElement(
              By.xpath("//*[@id='job_New job no.3']/td[3]/a/span")).getText(),freestyleProjectName);
    }
}

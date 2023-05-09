package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public void testCreateFreestyleWithEmptyName() {
        WebElement newItem = getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href*='/view/all/newJob']")));
        newItem.click();

        WebElement freestyleProject = getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='FreeStyleProject']")));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));
        WebElement errorText = getDriver().findElement(By.cssSelector("#itemname-required"));

        Assert.assertEquals(okButton.getAttribute("disabled"), "true");
        Assert.assertEquals(errorText.getText(), "» This field cannot be empty, please enter a valid name");
    }
}

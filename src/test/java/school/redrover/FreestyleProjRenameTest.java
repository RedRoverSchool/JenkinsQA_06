package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjRenameTest extends BaseTest {
    private final String OLD_NAME = "Freestyle Project Name";
    private final String NEW_NAME = "New Freestyle Project Name";

    @Test
    public void testRenameFreestyleProjectFromProjectPage() {
        createFreestyleProject();

        getDriver().findElement(By.xpath("//*[text()='Freestyle Project Name']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Freestyle%20Project%20Name/confirm-rename']")).click();

        getDriver().findElement(By.name("newName")).sendKeys(NEW_NAME);
        getDriver().findElement(By.name("Submit")).click();

        String expectedProjectName = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertTrue(expectedProjectName.contains(NEW_NAME));
    }

    private void createFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(OLD_NAME);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.id("jenkins-home-link")).click();
        getWait2();
    }
}

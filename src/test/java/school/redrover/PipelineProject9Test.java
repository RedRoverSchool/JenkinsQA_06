package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject9Test extends BaseTest {
    @Test
    public void testCreate() {
        final String projectName = "Test";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[normalize-space()='Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[normalize-space()='Pipeline "+ projectName +"']")).getText(), "Pipeline "+ projectName);
    }
  
    @Test
    public void testRename() {
        final String projectNewName = "New Test";

        getDriver().findElement(By.id("jenkins-home-link")).click();
        testCreate();

        getDriver().findElement(By.xpath("//a[@href='/job/Test/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Test/confirm-rename']")).click();
        WebElement nameField = getDriver().findElement(By.name("newName"));
        nameField.clear();
        nameField.sendKeys(projectNewName);
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='" + projectNewName +"']")).getText(), projectNewName);
    }
}

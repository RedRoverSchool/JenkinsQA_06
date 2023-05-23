package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject2Test extends BaseTest {

    @Test
    public void testNewProject() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);

        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("First project");

        WebElement freestyleProject = getDriver().findElement(By.xpath
                ("//li[@class='hudson_model_FreeStyleProject']"));
        freestyleProject.sendKeys(Keys.RETURN);

        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.sendKeys(Keys.RETURN);

        WebElement tabSave = getDriver().findElement(By.xpath("//button[normalize-space()='Save']"));
        tabSave.sendKeys(Keys.RETURN);

        WebElement tabDashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        tabDashboard.sendKeys(Keys.RETURN);

        WebElement textProject = getDriver().findElement(By.xpath
                ("//span[normalize-space()='First project']"));

        Assert.assertEquals(textProject.getText(), "First project");

    }

    @Ignore
    @Test(dependsOnMethods = "testNewProject")
    public void testAddDescription() {

        String text = "As a User, I want to add description to a freestyle project," +
                " so that I can make it clear for my colleagues what exactly this job does.\n" +
                "Acceptance criteria:\n" +
                "Ensure the User is able to:\n" +
                "Add a multiline text description to a created freestyle project\n" +
                "Preview the description before adding it\n" +
                "See the added description on the project page\n" +
                "Edit the project description";

        getDriver().findElement(By.xpath("//span[normalize-space()='First project']")).click();

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(
                text);

        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Hello! ");

        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement description = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//div[contains(text(),'Hello! As a User, I want to add description to a f')]")));

        Assert.assertEquals(description.getText(), "Hello! " + text);
    }

    @Test(dependsOnMethods = "testNewProject")
    public void testRenameProject() {

        getDriver().findElement(By.xpath("//span[normalize-space()='First project']")).click();

        getDriver().findElement(By.xpath("(//a[@class='task-link '])[5]")).click();

        WebElement newName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        newName.sendKeys(Keys.CONTROL+"a");
        newName.sendKeys(Keys.DELETE);
        newName.sendKeys("My first project");

        getDriver().findElement(By.xpath("//button[normalize-space()='Rename']")).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']")).click();

        WebElement projectName = getDriver().findElement(By.xpath("//span[normalize-space()='My first project']"));

        Assert.assertEquals(projectName.getText(), "My first project");
    }
}

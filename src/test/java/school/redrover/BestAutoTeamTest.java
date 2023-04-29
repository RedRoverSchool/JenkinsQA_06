package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BestAutoTeamTest extends BaseTest {

    @Test
    public void testIsJenkinsLoad() throws InterruptedException {

        WebElement jenkinsLogo = getDriver().findElement(By.xpath("//*[@id='jenkins-home-link']"));

        Assert.assertTrue(jenkinsLogo.isDisplayed());
    }
    @Ignore
    @Test
    public void testAddDescription() throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("TestDescription");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/*[1]")).getText(), "TestDescription");
    }

    @Ignore
    @Test
    public void testEditDescription() throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("TestDescription");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Edited");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/*[1]")).getText(), "EditedTestDescription");
    }

    @Test
    public void testNavigationPanelElementsDashboard() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[1]//span[@class = 'task-link-text']"));
        WebElement people = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[2]//span[@class = 'task-link-text']"));
        WebElement buildHistory = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[3]//span[@class = 'task-link-text']"));
        WebElement manageJenkins = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[4]//span[@class = 'task-link-text']"));
        WebElement myViews = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[5]//span[@class = 'task-link-text']"));

        Assert.assertEquals(newItem.getText(), "New Item");
        Assert.assertEquals(people.getText(), "People");
        Assert.assertEquals(buildHistory.getText(), "Build History");
        Assert.assertEquals(manageJenkins.getText(), "Manage Jenkins");
        Assert.assertEquals(myViews.getText(), "My Views");
    }
}
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
}
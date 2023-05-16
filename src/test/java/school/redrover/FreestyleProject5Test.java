package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject5Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']")));
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("First Freestyle Project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@class='model-link']")).click();
        WebElement projectName =
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span"));

        Assert.assertEquals(projectName.getText(), "First Freestyle Project");
    }

    @Test
    public void testDisableFreestyleProject(){
        TestUtils.createFreestyleProject(this, "First Freestyle Project", true);

        getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span")).click();
        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//form[@id='enable-project']"))
                .getText().trim().substring(0, 34),"This project is currently disabled" );

        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@class='model-link']")).click();
    }

    @Test
    public void testRenameFreestyleProject(){
        TestUtils.createFreestyleProject(this, "First Freestyle Project", true);

        WebElement mouseOnProject = getDriver()
                .findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']"));

        new Actions(getDriver())
                .moveToElement(mouseOnProject)
                .click(getWait5().until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//tr[@id='job_First Freestyle Project']/td[3]//button[@class='jenkins-menu-dropdown-chevron']"))))
                .perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='yui-gen6']")));
        getDriver().findElement(By.xpath("//li[@id='yui-gen6']")).click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='newName']"));
                inputField.clear();
                inputField.sendKeys("Second");
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@class='model-link']")).click();
        WebElement projectName =
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span"));

        Assert.assertEquals(projectName.getText(), "Second");
    }
}

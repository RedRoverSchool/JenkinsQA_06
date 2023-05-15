package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject4Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String nameOfProject = "NewFreestyleProject";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='items']"))));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("ol#breadcrumbs>li:first-child .model-link")).click();

        WebElement projectName = getDriver().findElement(By.cssSelector(".job-status-nobuilt td>a>span"));
        Assert.assertEquals(projectName.getText(), nameOfProject);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testDisableFreestyleProject() {

        getDriver().findElement(By.cssSelector(".job-status-nobuilt td>a>span")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement((By.cssSelector("#disable-project>.jenkins-button"))))).click();

        String disabledProject = getDriver().findElement(By.cssSelector("#enable-project")).getText();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate']")).isDisplayed());
        Assert.assertEquals(disabledProject.substring(0, disabledProject.indexOf("\n")), "This project is currently disabled");
    }
}

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
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject10Test extends BaseTest {

    @Test
    public void testCreateProject(){
        String nameProject = "FreestyleProject100";

        getDriver().findElement(By.xpath("//div[@class='task ']/span[@class='task-link-wrapper ']/a")).click();
        getDriver().findElement(By.xpath("//input[@class='jenkins-input']")).sendKeys(nameProject);

        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul[@class='j-item-options']/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']/ol[@class='jenkins-breadcrumbs__list']/li[@class='jenkins-breadcrumbs__list-item']/a")).click();

        WebElement checkResult = getDriver().findElement(By.xpath("//table[@class='jenkins-table  sortable']/tbody/tr[@id='job_FreestyleProject100']/td/a/span"));

        Assert.assertEquals(checkResult.getText(),nameProject);
    }


}

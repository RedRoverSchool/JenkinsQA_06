package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigProject26Test extends BaseTest {

    private static final String NAME_MULTICONFIG_PROJECT = "multiconfig";
    @Test
    public void testCreate() {

        getDriver().findElement(By.xpath("//div[@class='task '][1]")).click();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(NAME_MULTICONFIG_PROJECT);

        getDriver().findElement(By.xpath("//*[@class='hudson_matrix_MatrixProject']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        WebElement multiconfigElement = getDriver().findElement(By.xpath("//*[@id='job_multiconfig']/td[3]/a/span"));
        Assert.assertEquals(multiconfigElement.getText(), NAME_MULTICONFIG_PROJECT);
    }
}

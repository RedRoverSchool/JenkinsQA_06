package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolder26Test extends BaseTest {
    private static final String NAME_ORGFOLDER_PROJECT = "orgfolder";
    @Test
    public void testCreate() {

        getDriver().findElement(By.xpath("//div[@class='task '][1]")).click();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(NAME_ORGFOLDER_PROJECT);

        getDriver().findElement(By.xpath("//*[@class='hudson_matrix_MatrixProject']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        WebElement orgFolderElement = getDriver().findElement(By.xpath("//*[@id='job_orgfolder']/td[3]/a/span"));
        Assert.assertEquals(orgFolderElement.getText(), NAME_ORGFOLDER_PROJECT);
    }
}

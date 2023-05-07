package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.Arrays;
import java.util.List;

public class FolderTest3 extends BaseTest {
    public void createBaseFolder (String name) {
        getDriver().findElement(By.id("jenkins-home-link"));
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))));
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement nameinput=getDriver().findElement(By.name("name"));
        getWait2().until(ExpectedConditions.visibilityOf(nameinput));
        nameinput.sendKeys(name);

        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        WebElement okbutton = getDriver().findElement(By.id("ok-button"));
        okbutton.click();

        getWait2().until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Configuration']"), "Configuration"));
    }

        @Test
        public void testCreateFolder (){
            List<String> positivevalues = Arrays.asList("folder1");
             for (String value : positivevalues) {
                createBaseFolder(value);
                getDriver().findElement(By.xpath("//a[normalize-space()='"+value+"']")).click();

                Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+value+"']")).getText(),value);
            }
        }
}

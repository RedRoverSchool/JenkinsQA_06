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
    private static final By NAME=By.name("name");
    public void fillFolderNameField (String name) {
        getDriver().findElement(By.id("jenkins-home-link")).click();
        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))));
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement nameinput=getDriver().findElement(NAME);
        getWait5().until(ExpectedConditions.visibilityOf(nameinput));
        nameinput.sendKeys(name);
    }

    public void createBaseFolder (String name) {
        fillFolderNameField(name);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        WebElement okbutton = getDriver().findElement(By.id("ok-button"));
        okbutton.click();
    }

        @Test
        public void testCreateFolderPositive (){
            List<String> positivevalues = Arrays.asList("Folder1", " spaces ", "123");
             for (String value : positivevalues) {
                createBaseFolder(value);
                value = value.trim();
                getWait2().until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Configuration']"), "Configuration"));
                getDriver().findElement(By.xpath("//a[normalize-space()='"+value+"']")).click();

                Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+value+"']")).getText(),value);
            }
        }

        @Test
        public void testCreateFolderNegative () {
            List<String> negativevalues = Arrays.asList(" ");
            for (String value : negativevalues) {
                createBaseFolder(value);
                Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
            }
        }

        @Test
        public void testCreateFolderSpecSymbols () {
            List<String> specvalues = Arrays.asList("@", "#", "$", "%", "^", "&");
            for (String value : specvalues) {
                fillFolderNameField(value);
                Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), "» ‘"+value+"’ is an unsafe character");
                WebElement nameinput=getDriver().findElement(NAME);
                nameinput.clear();
            }
        }

}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MarishaSkySecondTest extends BaseTest {

        @Test
        public void testCreateItem() {
            WebElement buttonNewJob = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
            buttonNewJob.click();

            WebElement inputName = getDriver().findElement(By.xpath("//input[@name='name']"));
            inputName.sendKeys("Test");

            WebElement categoryFreeStyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
            categoryFreeStyleProject.click();

            WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
            buttonOk.click();

            WebElement buttonSubmit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
            buttonSubmit.click();

            WebElement text = getDriver().findElement(By.xpath("//h1[text()='Project Test']"));

            Assert.assertEquals(text.getText(), "Project Test");
        }

        @Test
        public void testCreateFolder() {
            WebElement buttonNewJob = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
            buttonNewJob.click();

            WebElement inputName = getDriver().findElement(By.xpath("//input[@name='name']"));
            inputName.sendKeys("Folder_new");

            WebElement categoryFolder = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
            categoryFolder.click();

            WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
            buttonOk.click();

            WebElement description = getDriver().findElement(By.xpath("//textarea[@name='_.description']"));
            description.click();
            description.sendKeys("Description for create new Folder");

            WebElement buttonSubmit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
            buttonSubmit.click();

            WebElement text = getDriver().findElement(By.cssSelector("h1"));

            getDriver().findElement(By.cssSelector(".icon-folder")).isDisplayed();
            Assert.assertEquals(text.getText(), "Folder_new");
        }
    }


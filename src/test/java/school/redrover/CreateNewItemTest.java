package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateNewItemTest extends BaseTest{

        @Test
        public void testCreateNewItem() {
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
    }


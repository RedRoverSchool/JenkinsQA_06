package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItemJenkinsTest extends BaseTest {

        @Test
        public void createNewJob()
        {
            WebElement buttonNewJob = getDriver().findElement(By.xpath("(//a[@href='/view/all/newJob'])[1]"));
            buttonNewJob.click();

            WebElement enterItemName = getDriver().findElement(By.xpath("//input[@name = \"name\"]"));
            enterItemName.sendKeys("job1");

            WebElement multiConfigProject  = getDriver().findElement(By.xpath("//li[@ class = \"hudson_matrix_MatrixProject\"]"));
            multiConfigProject.click();

            WebElement okButton  = getDriver().findElement(By.xpath("//button[@id = \"ok-button\"]"));
            okButton.click();

            WebElement saveButton  = getDriver().findElement(By.xpath("//button[@formNoValidate = \"formNoValidate\"]"));
            saveButton.click();
        }
    }




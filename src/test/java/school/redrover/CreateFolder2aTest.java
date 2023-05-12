package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolder2aTest extends BaseTest {

    @Test
    public void testCreateFolder() {

        String folderName = "Demo";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']")).click();
        getDriver().findElement(By.xpath("//input[@id = 'name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li [@ class = 'com_cloudbees_hudson_plugins_folder_Folder']")).click();
        getDriver().findElement(By.xpath("//div [@class = 'btn-decorator'][@style = 'position: fixed;']")).click();
        getDriver().findElement(By.xpath("//div [@class]// button [@formnovalidate='formNoValidate' and @name = 'Submit']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='/' and @class = 'model-link']")).click();


        String folderNameFakt = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .getText();

        Assert.assertEquals(folderNameFakt, folderName);
        }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import static java.sql.DriverManager.getDriver;

public class Folder6Test extends BaseTest {

    @Test
    public void testCreateFolder() {

        MainPage mainpage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("TC 00.04 New item Create Folder")
                .selectFolderAndOk()
                .clickSaveButton()
                .navigateToMainPageByBreadcrumbs();

     Assert.assertTrue(new MainPage(getDriver()).projectsTable().getText().contains("TC 00.04 New item Create Folder"));
    }

    @Test
    public void CreateMulticonfigurationProjectInFolderTest(){

        WebElement newItem = getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[1]/span[1]/a[1]/span[1]/*[1]"));
        newItem.click();

        WebElement createName = getDriver().findElement(By.xpath("//input[@id='name']"));
        createName.sendKeys("TC 00.04 New item Create Folder");

        WebElement buttonFolder = getDriver().findElement(By.xpath("//div[contains(text(),'Creates a container that stores nested items in it')]"));
        buttonFolder.click();

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
        buttonSave.click();

        WebElement buttonCreateAjob = getDriver().findElement(By.xpath("//span[contains(text(),'Create a job')]"));
        buttonCreateAjob.click();

        WebElement inputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputName.sendKeys("Mine Project");

        WebElement selectConfiguration = getDriver().findElement(By.xpath("//div[contains(text(),'Suitable for projects that need a large number of ')]"));
        selectConfiguration.click();

        WebElement buttonOkInto = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        buttonOkInto.click();

        WebElement buttonSaveInto = getDriver().findElement(By.xpath("//button[contains(text(),'Save')]"));
        buttonSaveInto.click();

        WebElement text = getDriver().findElement(By.xpath("//h1[contains(text(),'Project Mine Project')]"));
        Assert.assertEquals(text.getText(), "Project Mine Project");
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProject2Test extends BaseTest{
    @Test
    public void createMultiConfigurationProject(){
        String nameOfProject = "anyName";

        WebElement newItemLink = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        newItemLink.click();

        WebElement enterItemName = getDriver().findElement(By.xpath("//*[@id='name']"));
        enterItemName.sendKeys(nameOfProject);

        WebElement multiConfigBtn = getDriver()
                .findElement(By.xpath("//*[contains(@class,'hudson_ma')]/label/span"));
        multiConfigBtn.click();

        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okBtn.click();

        WebElement saveBtn = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveBtn.click();

        WebElement dashboardBtn = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashboardBtn.click();

        WebElement projectName = getDriver().findElement(By.xpath("//tr[@id='job_anyName']/td[3]/a/span"));

        Assert.assertEquals(projectName.getText(), nameOfProject);
    }
}

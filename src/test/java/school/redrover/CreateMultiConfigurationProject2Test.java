package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    @Test
    public void addDescriptionInMultiConfigurationProjectTest(){
        final String textDescription = "Text Description Test";

        getDriver().findElement(By.xpath("(//section[@class='empty-state-section'] )[1]//li")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Test1");
        getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name,'Submit')]"))).click();

        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='description']"))).sendKeys(textDescription);
        getDriver().findElement(By.xpath("//div[@id='description']//button[@name=\"Submit\"]")).click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualDescription.getText(),textDescription);
    }


}

package school.redrover;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipProjectTest extends BaseTest {
    @Test
    public void testCreatePipProject() {
        String expectedPipeline = "Pipeline Engineer";
        String expectedResult = "Engineer";

        getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='headline']")).getText(), expectedPipeline);

        getDriver().findElement(By.cssSelector("#breadcrumbBar > ol > li")).click();
        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer/']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testCreatePiplineProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement enterItemName = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        enterItemName.click();
        enterItemName.sendKeys("First");

        getDriver().findElement(By.xpath("//span[text() ='Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[@id ='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(), "Pipeline First");

        getDriver().findElement(By.xpath("//li[@class = 'jenkins-breadcrumbs__list-item'] [1]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']")).getText(), "First");
    }

    @Test
    public void testCreateNewPipeline() {
        WebElement newItem = getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']")));
        newItem.click();

        WebElement nameProject = getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[@name='name']")));
        nameProject.sendKeys("Project1");

        WebElement pipelineClick = getDriver()
                .findElement(By.xpath("//label//span[contains(text(), 'Pipeline')]"));
        pipelineClick.click();

        WebElement confirmButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        confirmButton.click();

        getDriver().findElement(By.xpath("//button[contains(@class,'jenkins-button jenkins-button--primary')]")).click();

        WebElement pipelineName = getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//h1[@class='job-index-headline page-headline']")));
        Assert.assertEquals(pipelineName.getText(), "Pipeline Project1" );
    }
}

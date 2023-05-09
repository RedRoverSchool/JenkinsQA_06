package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline3Test extends BaseTest {

    @Test
    public void testCreatePipelineViaNewItem() {
        final String itemName = "First Project";

        WebDriver driver = getDriver();

        WebElement newItemMenu = driver.findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemMenu.click();

        WebElement projectName = driver.findElement(By.xpath("//input[@name='name']"));
        projectName.sendKeys(itemName);

        WebElement pipelineSection = driver.findElement(By.xpath("//span[@class = 'label'] [text() = 'Pipeline']"));
        pipelineSection.click();

        WebElement okButton = driver.findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement submitButton = driver.findElement(By.xpath("//button[@name='Submit']"));
        submitButton.click();

        WebElement pipelineHeading = driver.findElement(By.xpath("//div[@id='main-panel']/h1"));
        Assert.assertEquals(pipelineHeading.getText(), "Pipeline " + itemName);

        WebElement breadscrumbDashboard = driver.findElement(By.xpath("//a[text()='Dashboard']"));
        breadscrumbDashboard.click();

        WebElement projectNameInProjectStatusTable = driver.findElement
                (By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));
        Assert.assertEquals(projectNameInProjectStatusTable.getText(), itemName);

    }
}

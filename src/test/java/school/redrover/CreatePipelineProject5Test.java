package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class CreatePipelineProject5Test extends BaseTest{

    @Test
    public void createPipelineProjectTest() {
        String name = "PipelineN";

        WebElement newItemButton = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='name']"));
        inputField.sendKeys(name);

        WebElement pipelineButton = getDriver().findElement
                (By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[2]/label/span"));
        pipelineButton.click();

        WebElement okButton = getDriver().findElement(By.xpath("//div[@class='btn-decorator']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement dashBoardButton = getDriver().findElement
                (By.xpath("//div[@id='breadcrumbBar']/ol/li[1]/a"));
        dashBoardButton.click();

        WebElement pipelineName = getDriver().findElement(By.xpath("//tr[@id='job_PipelineN']/td/a/span"));

        Assert.assertEquals(pipelineName.getText(), name);
    }
}

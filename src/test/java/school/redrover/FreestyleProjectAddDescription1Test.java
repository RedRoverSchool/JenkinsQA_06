package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProjectAddDescription1Test extends BaseTest {

    private void createFreestyleProject(){
        String nameProject = "ProjectN";

        TestUtils.createFreestyleProject(this,nameProject, true);
    }

    @Test
    public void testFreestyleProjectDescription(){
        createFreestyleProject();

        String description = "Testing\nmultiline\nproject\ndescription";

        WebElement projectBtn = getDriver().findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']"));
        projectBtn.click();

        WebElement configureBtn = getDriver().findElement(By.xpath("//*[@id='tasks']/div[5]"));
        configureBtn.click();

        WebElement descriptionField = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='jenkins-input   ']")));
        descriptionField.sendKeys(description);

        WebElement saveBtn = getDriver().findElement(By.xpath("//*[@name='Submit']"));
        saveBtn.click();

        WebElement projectDescription = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='description']/div[not(contains(@class, 'jenkins'))]")));

        Assert.assertEquals(projectDescription.getText(), description);

    }
}

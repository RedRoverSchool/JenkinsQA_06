package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BuildDescriptionTest extends BaseTest {

    public void newPipelineProject () {
        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        String projectName = "NewProject";
        inputField.sendKeys(projectName);

        WebElement jobType = getDriver()
                .findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]"));
        jobType.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        saveButton.click();
    }

    @Test
    public void testAddBuildDescription () {
        newPipelineProject();

        WebElement dashboardButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashboardButton.click();

        WebElement buildRunnerButton = getDriver()
                .findElement(By.xpath("//a[@title = 'Schedule a Build for NewProject']"));
        buildRunnerButton.click();

        WebElement buildHistoryButton = getDriver().findElement(By.xpath("//*[@id = 'tasks']/div[3]//a"));
        buildHistoryButton.click();

        WebElement buildButton = getDriver()
                .findElement(By.xpath("//a[contains (text(), '#1')]"));
        buildButton.sendKeys(Keys.RETURN);

        WebElement editBuildButton = getDriver()
                .findElement(By.xpath("//*[@href = '/job/NewProject/1/configure']"));
        editBuildButton.click();

        WebElement descriptionInput = getDriver().findElement(By.name("description"));
        String descriptionText = "NewDescription";
        descriptionInput.sendKeys(descriptionText);

        WebElement saveDescButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        saveDescButton.click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//*[@id='description']/div[1]")).getText(), descriptionText);
    }
}

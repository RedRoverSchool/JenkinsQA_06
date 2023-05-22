package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class FreestyleProjectRename2Test extends BaseTest {

    @Test
    public void freestyleProjectRenameFromDashboard() {
        final String  jobName = "AnotherName";
        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement projectName = getDriver().findElement(By.xpath("//input[@name='name']"));
        projectName.sendKeys("ThisProject");

        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
        freestyleProject.click();

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        buttonOk.click();

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        description.sendKeys("text");

        WebElement buttonSubmit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSubmit.click();

        WebElement renameItem = getDriver().findElement(By.xpath("//a[@href='/job/ThisProject/confirm-rename']"));
        renameItem.click();

        WebElement newName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        newName.clear();
        newName.sendKeys(jobName);

        WebElement submitButtonNewName = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitButtonNewName.click();

        WebElement newNameProject = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"));
        Assert.assertEquals(newNameProject.getText(), "Project "+ jobName);
    }
}

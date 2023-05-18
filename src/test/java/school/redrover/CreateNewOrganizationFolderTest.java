package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class CreateNewOrganizationFolderTest extends BaseTest {

    private void createNewOrganizationFolder(String folderName) {
        WebElement creatNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        creatNewItem.click();

        WebElement inputName = getDriver().findElement(By.xpath("//input[@name='name']"));
        inputName.sendKeys(folderName);

        WebElement organizationFolder = getDriver().findElement(By.xpath("//span[contains(text(),'Organization Folder')]"));
        organizationFolder.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        WebElement linkDashboard = getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]"));
        linkDashboard.click();
    }

    @Ignore
    @Test
    public void testCreateNewOrganizationFolderWithExistingName() {

        String name = "RedRoverSchool";
        createNewOrganizationFolder(name);

        WebElement creatNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        creatNewItem.click();

        WebElement inputName = getDriver().findElement(By.xpath("//input[@name='name']"));
        inputName.sendKeys("RedRoverSchool");

        WebElement validationMessege = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));

        Assert.assertEquals(validationMessege.getText(), "» A job already exists with the name ‘RedRoverSchool’");
    }
}

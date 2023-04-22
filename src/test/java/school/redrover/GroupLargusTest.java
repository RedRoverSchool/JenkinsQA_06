package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupLargusTest extends BaseTest {
    @Test
    public void testCreatedOrgFolderIsDisplayedInProjectListOnMainPage() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

        String projectName = "TestProject_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        WebElement inputProjectName = getDriver()
                .findElement(By.xpath("//input[@name='name']"));
        inputProjectName.sendKeys(projectName);

        WebElement itemOrgFolder = getDriver()
                .findElement(By.xpath("//span[text()='Organization Folder']/../.."));
        itemOrgFolder.click();
        WebElement buttonOK = getDriver().findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

        WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
        buttonHome.click();

        WebElement fieldNameInTable = getDriver()
                .findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));

        Assert.assertEquals(fieldNameInTable.getText(), projectName);
    }
    @Test
    public void testToChangeOfDiscription() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

        String projectName = "TestProject_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String newProjectName = "TestProject_new";

        WebElement inputProjectName = getDriver()
                .findElement(By.xpath("//input[@name='name']"));
        inputProjectName.sendKeys(projectName);

        WebElement itemFolder = getDriver()
                .findElement(By.xpath("//span[text()='Folder']/../.."));
        itemFolder.click();
        WebElement buttonOK = getDriver()
                .findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

        WebElement buttonHome = getDriver()
                .findElement(By.id("jenkins-home-link"));
        buttonHome.click();

        String locatorCreatedProject = "//a[@href='job/" + projectName + "/']";
        WebElement linkCreatedProjectInList = getDriver()
                .findElement(By.xpath(locatorCreatedProject));
        linkCreatedProjectInList.click();

        WebElement linkOnConfigure = getDriver()
                .findElement(By.xpath("//a[contains(@href, '/configure')]"));
        linkOnConfigure.click();

        WebElement inputDisplayName = getDriver()
                .findElement(By.xpath("//input[@name = '_.displayNameOrNull']"));
        inputDisplayName.sendKeys(newProjectName);

        WebElement buttonSave = getDriver()
                .findElement(By.name("Submit"));
        buttonSave.click();
        buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
        buttonHome.click();

        String locatorNewNameCreatedProject = "//a[@href='job/" + projectName + "/']/span";
        WebElement newNameInList = getDriver()
                .findElement(By.xpath(locatorNewNameCreatedProject));

        Assert.assertEquals(newNameInList.getText(), newProjectName);
    }
}

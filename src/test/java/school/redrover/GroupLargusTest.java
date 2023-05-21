package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class GroupLargusTest extends BaseTest {

    private final String projectName = "TestProject_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    @Test
    public void testCreatedOrgFolderIsDisplayedInProjectListOnMainPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

        WebElement inputProjectName = getDriver()
                .findElement(By.xpath("//input[@id='name']"));

        wait.until(ExpectedConditions.visibilityOf(inputProjectName));
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
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

        String newProjectName = "TestProject_new";

        WebElement inputProjectName = getDriver()
                .findElement(By.xpath("//input[@id='name']"));

        wait.until(ExpectedConditions.visibilityOf(inputProjectName));
        inputProjectName.sendKeys(projectName);

        WebElement itemFolder = getDriver()
                .findElement(By.xpath("//span[text()='Folder']/../.."));
        itemFolder.click();
        WebElement buttonOK = getDriver()
                .findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

        WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
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

        wait.until(ExpectedConditions.visibilityOf(inputDisplayName));
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
@Test
           public void testToDescription(){
    Actions actions = new Actions(getDriver());
    String projectName = "Myfolder";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//input[@ value='com.cloudbees.hudson.plugins.folder.Folder']/..")).click();
        WebElement okay = getDriver().findElement(By.id("ok-button"));
        actions.scrollToElement(okay).moveToElement(okay).click().build().perform();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.id("jenkins-home-link"));

    getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
    getDriver().findElement(By.id("name")).sendKeys(projectName);
    getDriver().findElement(By.xpath("//input[@ value='com.cloudbees.hudson.plugins.folder.Folder']/..")).click();
    WebElement message = getDriver().findElement(By.xpath("//div[contains(@id, 'invalid')]"));
    Assert.assertTrue(message.getText().contains(projectName));

}

}

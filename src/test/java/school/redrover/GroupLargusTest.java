package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
<<<<<<< HEAD
import org.openqa.selenium.support.FindBy;
=======
>>>>>>> de78ff3d (GroupLargusTest/IvanK/ add first test at Jenkins (#916))
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class GroupLargusTest extends BaseTest {
<<<<<<< HEAD
    private static class StringUtility {
        private static String uniqueStr(String str) {
            return str + " " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
    }

    @FindBy(xpath = "//div[@id='tasks']//a[contains(@href, 'newJob')]")
    private WebElement linkNewJob;

    @FindBy(id = "jenkins-home-link")
    private WebElement buttonHome;

    private WebDriverWait wait10;

    public final WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    public WebElement waitElementVisible(WebElement elem) {
        getWait10().until(ExpectedConditions.visibilityOf(elem));
        return elem;
    }

    public WebElement waitForClickOnElement(WebElement elem) {
        getWait10().until(ExpectedConditions.elementToBeClickable(elem));
        return elem;

    }
    @Test
    public void testCreatedOrgFolderIsDisplayedInProjectListOnMainPage() {
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

//        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
//        linkNewJob.click();
        waitForClickOnElement(linkNewJob).click();
=======

    private final String projectName = "TestProject_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    @Test
    public void testCreatedOrgFolderIsDisplayedInProjectListOnMainPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
>>>>>>> de78ff3d (GroupLargusTest/IvanK/ add first test at Jenkins (#916))

        WebElement inputProjectName = getDriver()
                .findElement(By.xpath("//input[@id='name']"));

<<<<<<< HEAD
//        wait.until(ExpectedConditions.visibilityOf(inputProjectName));
//        inputProjectName.sendKeys(projectName);
        String projectName = StringUtility.uniqueStr("TestProject");
        waitElementVisible(inputProjectName).sendKeys(projectName);
=======
        wait.until(ExpectedConditions.visibilityOf(inputProjectName));
        inputProjectName.sendKeys(projectName);
>>>>>>> de78ff3d (GroupLargusTest/IvanK/ add first test at Jenkins (#916))

        WebElement itemOrgFolder = getDriver()
                .findElement(By.xpath("//span[text()='Organization Folder']/../.."));
        itemOrgFolder.click();
        WebElement buttonOK = getDriver().findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

<<<<<<< HEAD
        //WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
=======
        WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
>>>>>>> de78ff3d (GroupLargusTest/IvanK/ add first test at Jenkins (#916))
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
<<<<<<< HEAD
        String projectName = StringUtility.uniqueStr("TestProject");
=======
>>>>>>> de78ff3d (GroupLargusTest/IvanK/ add first test at Jenkins (#916))
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
}

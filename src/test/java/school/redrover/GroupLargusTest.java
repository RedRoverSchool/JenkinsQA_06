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
        String newProjectName = "TestProject_new";

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

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
    public void testDisplayedErrorWhenDontInputInfoAboutGithub() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Actions actions= new Actions(getDriver());
        String projectDescription = "AdvancedProjectName";

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();

        wait.until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//input[@id='name']")))).sendKeys(projectName);

        getDriver().findElement(By.xpath("//span[text()='Pipeline']/../..")).click();

        getDriver().findElement(By.cssSelector("button#ok-button")).click();

        getDriver().findElement(By.xpath(
                "//label[contains(text(), 'Do not allow concurrent builds')]/..")).click();
        getDriver().findElement(By.xpath(
                "//label[contains(text(), 'Abort previous builds')]/..")).click();
        getDriver().findElement(By.xpath("//input[@name='githubProject']/following-sibling::label")).click();
        //scroll is needed
        WebElement buttonAdvancedProjectOptions = getDriver().findElement(By.xpath(
                "//div[contains(@class, 'jenkins-section')]//button[contains(@class, 'advanced')]"));
        actions
                .scrollToElement(buttonAdvancedProjectOptions)
                .scrollByAmount(0,300)
                .moveToElement(buttonAdvancedProjectOptions)
                .click()
                .build()
                .perform();

        WebElement inputAdvancedDisplayName = getDriver().findElement(By.name("_.displayNameOrNull"));

        inputAdvancedDisplayName.sendKeys(projectDescription);
        getDriver().findElement(By.xpath(
                "//div[@id='pipeline']/following-sibling::div[@class='jenkins-form-item']//select/" +
                        "option[@value='1']")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        getDriver().findElement(By.id("jenkins-home-link")).click();

        WebElement projectNameInList = getDriver().findElement(By.xpath(
                "//tr[@id='job_" + projectName + "']//span[text()='" + projectDescription + "']"));

        //button[@class='jenkins-menu-dropdown-chevron']
        actions
                .moveToElement(projectNameInList)
                .build().perform();
        WebElement dropdownListForProject = getDriver().findElement(By.xpath(
                "//tr[@id='job_" + projectName + "']//span[text()='" + projectDescription + "']/following-sibling::button"));
        actions
                .moveToElement(dropdownListForProject)
                .click(dropdownListForProject)
                .build().perform();

        getDriver().findElement(By.xpath("//a[@href='/job/" + projectName + "/configure']")).click();
        actions
                .scrollByAmount(0, 1000)
                .build().perform();

        WebElement errorMessage = getDriver().findElement(By.xpath(
                "//input[@name='_.url']/../following-sibling::div//div[@class='error']"));

        Assert.assertTrue(errorMessage.isEnabled());
        // and contains(text(), 'Please enter Git repository')
    }
}

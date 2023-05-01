package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JavaExpertsNationTest extends BaseTest {
    @Test
    public void testManageTitle() {
        getDriver().findElement(By.cssSelector("a[href='/manage']")).click();

        WebElement manageTitle = getDriver().findElement(By.tagName("h1"));
        WebElement securityTitle = getDriver().findElement(By.cssSelector("#main-panel section:nth-child(6) h2"));

        Assert.assertEquals(manageTitle.getText(), "Manage Jenkins");
        Assert.assertEquals(securityTitle.getText(), "Security");

        WebElement systemConfigurationTitle = getDriver().
                findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/h2"));

        Assert.assertEquals(systemConfigurationTitle.getText(), "System Configuration");
    }
    @Test
    public void testPeopleTitle() {
        WebElement people = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        people.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "People");
    }

    @Test
    public void testBuildHistoryTitle() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        buildHistory.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "Build History of Jenkins");
    }
    @Test
    public void testAddDescription(){
        WebElement addDescLink = getDriver().findElement(By.id("description-link"));
        addDescLink.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name = 'description']"))
        );

        WebElement inputField = getDriver().findElement(By.cssSelector("textarea[name = 'description']"));
        inputField.clear();

        String text = "Some description";
        inputField.sendKeys(text);

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualResult.getText(), text);

    }

    @Test
    public void testAddNewItem() {
        WebElement newItem = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        newItem.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        String firstJob = "First Job";
        inputField.sendKeys(firstJob);

        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        WebElement projectTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(projectTitle.getText(), "Project " + firstJob, "projectTitle does not match inputted text");
    }

    @Test
    public void testJenkins2_387_2Link(){
        WebElement jenkinsLink = getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/']"));
        String expectedURL = jenkinsLink.getAttribute("href");
        jenkinsLink.click();

        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        String newUrl = getDriver().getCurrentUrl();
        getDriver().switchTo().window(getDriver().getWindowHandle()).close();

        Assert.assertEquals(newUrl, expectedURL);
    }
    @Test
    public void testCreateNewFolder(){
        WebElement newItemButton = getDriver().findElement(
                By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.clear();
        String folderName = "My first folder";
        inputField.sendKeys(folderName);

        WebElement folderButton = getDriver().findElement(
                By.xpath("//*[@class='com_cloudbees_hudson_plugins_folder_Folder']/div[@class='desc']"));
        folderButton.click();

        getDriver().findElement(By.id("ok-button")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2));

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        saveButton.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2));

        String actualFolderName = getDriver().findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualFolderName, folderName);
    }
}

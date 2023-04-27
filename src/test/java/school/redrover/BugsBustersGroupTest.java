package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;


public class BugsBustersGroupTest extends BaseTest {

    @Test
    public void testHeadIconIsDisplayed() {
        WebElement icon = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(icon.isDisplayed());
    }

    @Test
    public void testDashboardButtonIsDisplayed() {
        WebElement dashboardButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']//button"));

        Assert.assertTrue(dashboardButton.isDisplayed());
    }

   @Test
    public void testMariaSearchBoxPresent() {
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@name='q']"));

        Assert.assertTrue(searchBox.isDisplayed());
    }

    @Ignore
    @Test
    public void testCreateAJobPageTitle(){
        WebElement createAJob = getDriver().findElement(By.xpath("//a[@href='newJob']/span"));
        createAJob.click();

        WebElement createAJobGetTitleText = getDriver().findElement(By.xpath("//div[@class='add-item-name']/label"));

        Assert.assertEquals(createAJobGetTitleText.getText(), "Enter an item name");
    }

    @Test
    public void testGetPeoplePage(){
        WebElement peoplePageMenu = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        peoplePageMenu.click();

        WebElement PeoplePageTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(PeoplePageTitle.getText(), "People");
    }

    @Test
    public void testAddDescription () {
        WebElement addDescription = getDriver().findElement(By.xpath("//*[@id='description-link']"));
        addDescription.click();

        WebElement textBox = getDriver().findElement(By.xpath("//textarea"));
        textBox.isDisplayed();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class = 'textarea-show-preview']"))
                .getText(), "Preview");
    }

    @Test
    public void testLogOut () {
        WebElement logOutIcon = getDriver().findElement(By.xpath("//a[@href = '/logout']"));
        logOutIcon.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),
                "Welcome to Jenkins!");
    }

    @Test
    public void testCreateJobPage () {
        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@href = 'newJob']"));
        createJobButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'add-item-name']/label"))
                        .getText(), "Enter an item name");
    }

    @Test
    public void testOkButtonIsDisabled() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']//a"));
        newItem.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

        Assert.assertFalse(okButton.isEnabled(), "Button is disabled");
    }

    @Test
    public void testOkButtonIsEnabled() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']//a"));
        newItem.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("Test-Folder");

        WebElement newFolder = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']//li[1]"));
        newFolder.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

        Assert.assertTrue(okButton.isEnabled(), "Button is enabled");
    }

    @Test
    public void testNewFolderIsCreated() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']//a"));
        newItem.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("Test-Folder");

        WebElement newFolder = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']//li[1]"));
        newFolder.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement dashboardButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']//a"));
        dashboardButton.click();

        WebElement createdFolder = getDriver().findElement(By.id("job_Test-Folder"));

        Assert.assertTrue(createdFolder.isDisplayed(), "Created folder is displayed");
    }

    @Test
    public void testMultibranchPipeline() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']//a"));
        newItem.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("Test-Multibranch-Pipeline");

        WebElement newPipeline = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']//li[2]"));
        newPipeline.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        List<WebElement> configurationMenuItems = getDriver().findElements(By.xpath("//*[@id='tasks']//button"));
        List<String> expectedMenuItems = Arrays.asList("General", "Branch Sources", "Build Configuration",
                "Scan Multibranch Pipeline Triggers", "Orphaned Item Strategy", "Appearance", "Health metrics", "Properties");

        for (int i = 0; i < configurationMenuItems.size(); i++) {
            String actualMenuItems = configurationMenuItems.get(i).getText();
            Assert.assertEquals(actualMenuItems, expectedMenuItems.get(i));
        }
    }
}
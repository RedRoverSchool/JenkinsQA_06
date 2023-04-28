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

    @Ignore
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

    @Test
    public void testNewFreestyleProject () {
        WebElement newItem = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItem.click();

        String text = "New Freestyle Project";
        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys(text);
        WebElement projectType = getDriver().findElement(
                By.xpath("//li[@class = 'hudson_model_FreeStyleProject']"));
        projectType.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        String descriptionText = "My new project";
        WebElement descriptionBox = getDriver().findElement(By.xpath("//*[@name = 'description']"));
        descriptionBox.sendKeys(descriptionText);
        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'main-panel']/h1")).getText(),
                "Project " + text);
    }

    @Test
    public void testMainPageMenuItems () {
        List<WebElement> menuList = getDriver().findElements(By.xpath("//div[@id= 'tasks']/div"));
        List <String> expectedList = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        for (int i = 0; i < menuList.size(); i++) {
            String actualList = menuList.get(i).getText();
            Assert.assertEquals(actualList, expectedList.get(i));
        }
    }

    @Test
    public void testSystemConfigurationMenu () {
        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        manageJenkins.click();

        List <WebElement> systemConfigMenu = getDriver().findElements(By.xpath(
                "//*[@id='main-panel']/section[2]/div/div[1]/a/dl/dt"));
        List <String> expectedMenuList = Arrays.asList("Configure System", "Manage Plugins", "Global Tool Configuration",
                "Manage Nodes and Clouds");

        for (int i = 0; i < systemConfigMenu.size(); i++) {
            String actualMenuList = systemConfigMenu.get(i).getText();
            Assert.assertEquals(actualMenuList, expectedMenuList.get(i));
        }
    }

    @Test
    public void testCredentials() {

        WebElement manageJenkins = getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a"));
        manageJenkins.click();

        WebElement manageCredentials = getDriver().findElement(By.xpath("//a[@href='credentials']//dl//dt"));
        manageCredentials.click();

        WebElement credentialsPage = getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__content']//h1"));
        Assert.assertEquals(credentialsPage.getText(), "Credentials");
    }

}
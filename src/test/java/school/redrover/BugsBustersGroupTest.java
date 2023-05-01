package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
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

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='createItem']/div[1]/div/label"))
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

    @Test
    public void testFolderCreation() {
        final String FOLDER_NAME = "My_folder";
        final String FOLDER_NAME1 = "My_folder1";
        List <String> expectedJobsList = Arrays.asList(FOLDER_NAME, FOLDER_NAME1);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement newItem = getDriver().findElement(By.xpath(
                "//a[@href='/view/all/newJob']"));
        newItem.click();
        WebElement nameField = getDriver().findElement(By.id("name"));
        nameField.sendKeys(FOLDER_NAME);
        WebElement folderButton = getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder"));
        folderButton.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement submitButton = getDriver().findElement(By.name("Submit"));
        submitButton.click();
        WebElement header = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(header.getText(),FOLDER_NAME);

        WebElement dashboardMenu = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardMenu.click();

        WebElement newItem1 = getDriver().findElement(By.xpath(
                "//a[@href='/view/all/newJob']"));
        newItem1.click();
        WebElement nameField1 = getDriver().findElement(By.id("name"));
        nameField1.sendKeys(FOLDER_NAME1);
        WebElement folderButton1 = getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder"));
        folderButton1.click();
        WebElement okButton1 = getDriver().findElement(By.id("ok-button"));
        okButton1.click();
        WebElement submitButton1 = getDriver().findElement(By.name("Submit"));
        submitButton1.click();
        WebElement header1 = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(header1.getText(),FOLDER_NAME1);

        WebElement dashboardMenu1 = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardMenu1.click();

        List <WebElement> jobsList = getDriver().findElements(By.xpath("//tr[@class=' job-status-']/td/a/span"));

        for (int i = 0; i < jobsList.size(); i++) {
            String actualJobsList = jobsList.get(i).getText();
            Assert.assertEquals(actualJobsList, expectedJobsList.get(i));
        }
    }
}

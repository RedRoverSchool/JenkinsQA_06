package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class HelloWorldGroupTest extends BaseTest{
    @Ignore
    @Test
    public void testUserName(){
        WebElement peopleElement = getDriver().findElement(By.xpath("//span[contains(text(), 'People')]/ancestor::a"));
        peopleElement.click();

        WebElement userIDElement = getDriver().findElement(By.xpath("//table[@id='people']//td//a"));
        userIDElement.click();

        WebElement userName = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(userName.getText(), "admin");
    }
    @Test
    public void testDescriptionEdit() {
        final String text = "HelloWorld";
        WebElement descr = getDriver().findElement(By.xpath("//*[@id='description-link']"));
        descr.click();

        WebElement descrArea = getDriver().findElement(By.xpath("//div[@id='description']//textarea"));
        descrArea.clear();
        descrArea.sendKeys(text);

        WebElement saveBtn = getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveBtn.click();

        WebElement descrText = getDriver().findElement(By.xpath("//*[@id='description']/div"));
        Assert.assertEquals(descrText.getText(), text);
    }

    @Ignore
    @Test
    public void testCreateNewFreestyleProject() {
        String projectName = "My new project";

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement itemNameInput = getDriver().findElement(By.xpath("//input[@id='name']"));
        itemNameInput.sendKeys(projectName);

        WebElement freeStyleProjectButton = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
        freeStyleProjectButton.click();

        WebElement okButton = getDriver().findElement(By.xpath("//div[@class='btn-decorator']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement dashboardCrumbs = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href='/']"));
        dashboardCrumbs.click();

        WebElement element = getDriver().findElement(By.xpath("//tr[@id='job_" + projectName + "']//td/a"));

        Assert.assertEquals(element.getText(), projectName);
    }
    @Test
    public void testSearch(){

        WebElement searchField = getDriver().findElement(By.xpath("//input[@name='q']"));
        searchField.click();
        searchField.sendKeys("&");
        searchField.sendKeys(Keys.ENTER);

        WebElement fieldError = getDriver().findElement(By.xpath("//div[@class='error']"));
        String text = "Nothing seems to match.";

        Assert.assertEquals(fieldError.getText(),text);
    }

    @Test
    public void testCreateNewProject() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement nameProject = getDriver().findElement(By.id("name"));
        nameProject.sendKeys("First");

        WebElement typeProject = getDriver().findElement(By.xpath("//input[@value='hudson.model.FreeStyleProject']/.."));
        typeProject.click();

        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href='/']")).click();

        WebElement firstProjectExist = getDriver().findElement(By.xpath("//td/a[@href='job/First/']"));
        Assert.assertEquals(firstProjectExist.getText(), "First");
    }
    @Test
    public void testFieldSearchSetting() throws InterruptedException {
        WebElement settings = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        settings.click();

        WebElement fieldSearch = getDriver().findElement(By.id("settings-search-bar"));
        fieldSearch.click();
        fieldSearch.sendKeys("users");

        WebElement drop = getDriver().findElement(By.xpath("//div[@class='jenkins-search__results']"));
        Thread.sleep(1000);
        drop.click();

        WebElement  element = getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__controls']"));
        String text = "Create User";
        Assert.assertEquals(element.getText(), text);
    }
    @Test
    public void testCreateAndDeleteMultibranchPipeline(){
        final String displayName = "Create";
        final String fieldName = "Check create and delete";

        WebElement itemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        itemButton.click();

        WebElement enterField = getDriver().findElement(By.xpath("//input[@name='name']"));
        enterField.sendKeys(fieldName);

        WebElement multiBranch = getDriver().findElement
                (By.xpath("//span[normalize-space(.)='Multibranch Pipeline']"));
        multiBranch.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement enterDisplayName = getDriver().findElement
                (By.xpath("//div[@class='setting-main']/input[@class='jenkins-input validated  ']"));
        enterDisplayName.sendKeys(displayName);

        WebElement saveButton = getDriver().findElement(By.xpath("//button[normalize-space(.)='Save']"));
        saveButton.click();

        WebElement checkDisplayName = getDriver().findElement(By.xpath("//h1[normalize-space()='Create']"));
        Assert.assertEquals(checkDisplayName.getText(),displayName);

        WebElement deleteMultibranch = getDriver().findElement
                (By.xpath("//span[normalize-space(.)='Delete Multibranch Pipeline']"));
        deleteMultibranch.click();

        WebElement confirmationButton = getDriver().findElement
                (By.xpath("//button[normalize-space(.)='Yes']"));
        confirmationButton.click();

        WebElement deleteCheck = getDriver().findElement
                (By.xpath("//h2[normalize-space(.)='Start building your software project']"));
        Assert.assertEquals(deleteCheck.getText(),"Start building your software project");
    }

    @Test
    public void testOkButtonIsDisabled(){

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        Assert.assertEquals(okButton.getAttribute("class").contains("disabled"),true);
    }


    @Test
    public void testJenkinsFreestyleCreateFolder() {
        final String itemText = "Folder";
        final String displayName = "General";
        final String description = "GeneralPlus";

        WebElement iconJenkins = getDriver().findElement(By.xpath("//div[@class='logo']//img[@alt='[Jenkins]']"));
        Assert.assertTrue(iconJenkins.isDisplayed());

        WebElement welcomeJenkins = getDriver().findElement(By.xpath("//div[@class='empty-state-block']//h1"));
        Assert.assertTrue(welcomeJenkins.isDisplayed());

        WebElement createItem = getDriver().findElement(By.xpath("//span[@class='task-link-wrapper ']//a[@href='/view/all/newJob']"));
        createItem.click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputField.sendKeys(itemText);

        WebElement fieldField = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        fieldField.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement generalDisplayName = getDriver().findElement(By.xpath("//div[@class='setting-main']//input"));
        generalDisplayName.sendKeys(displayName);

        WebElement generalDescription = getDriver().findElement(By.xpath("//div[@class='setting-main']//textarea"));
        generalDescription.sendKeys(description);

        WebElement generalSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        generalSave.click();

        WebElement createdFolder = getDriver().findElement(By.xpath("//h1[contains(text(),'General')]"));
        Assert.assertTrue(createdFolder.isDisplayed());
    }
}

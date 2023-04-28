package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JavaJitsuGroupTest extends BaseTest {
    @Test
    public void testCheckingConfiguration() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement dropDownElement = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownElement).perform();

        WebElement configure = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Configure']//parent::a")));
        Actions actions = new Actions(getDriver());
        actions.click(configure).perform();

        Assert.assertTrue(getDriver().findElement(By.xpath("//textarea[@name='_.description']")).isDisplayed());
    }

    @Test
    public void logoCheckingTest() {
        WebElement logoCheck = getDriver().findElement(By.xpath("//img[@id='jenkins-head-icon']"));
        Assert.assertTrue(logoCheck.isDisplayed());
    }

    @Test
    public void testNewItem() throws InterruptedException {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        newItem.click();
        WebElement nameInput = getDriver().findElement(By.xpath("//input[@id=\"name\"]"));
        Thread.sleep(2000);
        nameInput.sendKeys("JavaTest");
        WebElement freProject = getDriver().findElement(By.xpath("//span[text() =\"Freestyle project\"]"));
        freProject.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement configElement = getDriver().findElement(By.xpath("//div[@class = 'jenkins-app-bar__content']/h1"));
        Assert.assertEquals(configElement.getText(), "Configure");
    }

    @Test
    public void testManagePlugins() {
        WebElement manageJ = getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[4]/span[1]/a[1]"));
        manageJ.click();

        WebElement managePlugins = getDriver().findElement(By.xpath("//dt[contains(text(),'Manage Plugins')]"));
        managePlugins.click();

        WebElement text = getDriver().findElement(By.xpath("//h1[contains(text(),'Plugins')]"));
        Assert.assertEquals(text.getText(), "Plugins");
    }

    @Test
    public void testAddNew1Item() throws InterruptedException {

        WebElement newItemBtn = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemBtn.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input[@id='name']"));
        searchField.sendKeys("Mari5");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']//label"));
        freestyleProject.click();
        WebElement okBtn = getDriver().findElement(By.id("ok-button"));
        okBtn.click();
        Thread.sleep(2000);
        WebElement myDropDown = getDriver().findElement(By.cssSelector("a[class='model-link'] span[class='hidden-xs hidden-sm']"));
        myDropDown.click();

        getDriver().findElement(By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[1]"));
        getDriver().findElement(By.xpath("(//span[contains(text(),'Configure')])[1]"));

        WebElement myViews = getDriver().findElement(By.xpath("//div[5]//span[1]//a[1]"));
        Thread.sleep(2000);
        Assert.assertEquals(myViews.getText(), "My Views");
    }

    @Test
    public void testManageCredentials() {

        WebElement manageJenkins = getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a"));
        manageJenkins.click();

        WebElement manageCredentials = getDriver().findElement(By.xpath("//div[@id='main-panel']/section[3]/div/div[2]/a/dl/dt"));
        manageCredentials.click();

        WebElement credentialsPage = getDriver().findElement(By.xpath("//div[@id='main-panel']/div/div/h1"));
        Assert.assertEquals(credentialsPage.getText(), "Credentials");
    }

    @Test
    public void testNewItemFolder() {

        WebElement newItemElement = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemElement.click();
        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("newProject");
        WebElement folder = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folder.click();
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));
        okButton.click();
        Assert.assertEquals("General", getDriver().findElement(By.cssSelector("#general")).getText());

        WebElement displayName = getDriver().findElement(By.name("_.displayNameOrNull"));
        displayName.sendKeys("NewTest");
        WebElement description = getDriver().findElement(By.name("_.description"));
        description.sendKeys("Test project to QA Redrover.school");

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();
        Assert.assertEquals("NewTest", getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }

    @Test
    public void testNewUser() {

        WebElement manageJenkins = getDriver().findElement(By.cssSelector("a[href='/manage']"));
        manageJenkins.click();
        WebElement manageUsers = getDriver().findElement(By.cssSelector("a[href='securityRealm/']"));
        manageUsers.click();
        WebElement createUserButton = getDriver().findElement(By.xpath(" //a[@class='jenkins-button jenkins-button--primary']"));
        createUserButton.click();

        WebElement username = getDriver().findElement(By.name("username"));
        username.sendKeys("NewUser");
        WebElement password = getDriver().findElement(By.name("password1"));
        password.sendKeys("NewUser12345!");
        WebElement confirmPassword = getDriver().findElement(By.name("password2"));
        confirmPassword.sendKeys("NewUser12345!");
        WebElement fullName = getDriver().findElement(By.name("fullname"));
        fullName.sendKeys("Max Smith");
        WebElement email = getDriver().findElement(By.name("email"));
        email.sendKeys("max@gmail.com");

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();
        Assert.assertEquals("NewUser", getDriver().findElement(By.xpath("//tbody/tr[2]/td[2]")).getText());
    }

    @Test
    public void testCreateJob() throws InterruptedException {
        WebElement creJob = getDriver().findElement(By.xpath("//span[text()=\"Create a job\"]"));
        creJob.click();
        WebElement nameInput = getDriver().findElement(By.cssSelector("input#name"));
        Thread.sleep(2000);
        nameInput.sendKeys("JavaTest");
        WebElement pipeLine = getDriver().findElement(By.xpath("//span[text() =\"Pipeline\"]"));
        pipeLine.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement butSave = getDriver().findElement(By.cssSelector("button[name=\"Submit\"]"));
        butSave.click();
        WebElement nameJob = getDriver().findElement(By.xpath("//h1[text() =\"Pipeline JavaTest\"]"));
        Assert.assertEquals(nameJob.getText(), "Pipeline JavaTest");
    }

    @Test
    public void testAddDescription() {
        final String text = "text";
        WebElement addLink = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addLink.click();
        WebElement textInput = getDriver().findElement(By.cssSelector("textarea[name='description']"));
        textInput.clear();
        textInput.sendKeys(text);
        WebElement buttonSave = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        buttonSave.click();
        WebElement inputAdd = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(inputAdd.getText(), text);
    }

    @Test
    public void testCreateAJob() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement createAJob = getDriver().findElement(By.xpath("//span[text()='Create a job']"));
        createAJob.click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        inputName.sendKeys("NewProject");
        WebElement selectFreestyleProject = getDriver().findElement(By.xpath("//span[text() = 'Freestyle project']"));
        selectFreestyleProject.click();
        WebElement okBtn = getDriver().findElement(By.id("ok-button"));
        okBtn.click();
        Thread.sleep(2000);

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        description.sendKeys("My first Jenkins project");
        WebElement submitBtn = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        submitBtn.click();
        Thread.sleep(2000);

        WebElement projectTitle = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));

        Assert.assertEquals(projectTitle.getText(),"Project NewProject");
    }
    @Test
    public void testManageJenkins() {
        WebElement manageJenkins = getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]"));
        manageJenkins.click();

        WebElement manageJenkinsHeader = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(manageJenkinsHeader.getText(), "Manage Jenkins");
    }
    @Test
    public void testBuildExecutorStatus() {
        getDriver().findElement(By.xpath("//a[text()='Build Executor Status']")).click();
        getDriver().findElement(By.className("jenkins-button")).click();
        final String text = "New node name";
        getDriver().findElement(By.cssSelector("input[id = 'name']")).sendKeys(text);
        getDriver().findElement(By.className("jenkins-radio__label")).click();
        getDriver().findElement(By.cssSelector("#ok")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        WebElement ManageNodes = getDriver().findElement(By.xpath("//h1[text() = 'Manage nodes and clouds']"));
        Assert.assertEquals(ManageNodes.getText(),"Manage nodes and clouds");
    }
}

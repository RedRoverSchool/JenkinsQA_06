package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class NeedMoreCoffeeTest extends BaseTest {

    @Test
    public void testIdUser() {

        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a"));
        buttonPeople.sendKeys(Keys.RETURN);
        WebDriverWait waiter = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement buttonAdmin = waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"person-admin\"]/td[2]/a")));
        buttonAdmin.sendKeys(Keys.RETURN);
        WebElement textUserId = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]"));

        Assert.assertEquals(textUserId.getText(), "Jenkins User ID: admin");

    }

    @Test
    public void testNewProject() {

        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        field.sendKeys("project");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        freestyleProject.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement tabSave = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        tabSave.sendKeys(Keys.RETURN);
        WebElement textProject = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));

        Assert.assertEquals(textProject.getText(), "Project project");

    }

    @Test
    public void testNewProjectWithGitHub() {

        WebElement createAJob = getDriver().findElement(By.xpath("//span[normalize-space()='Create a job']"));
        createAJob.click();
        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("project");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
        freestyleProject.click();
        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.sendKeys(Keys.RETURN);
        WebDriverWait waiter = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement labelGitHubProject = waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath
                ("//label[normalize-space()='GitHub project']")));
        labelGitHubProject.click();
        WebElement fieldProjectUrl = getDriver().findElement(By.xpath("//input[@name='_.projectUrlStr']"));
        fieldProjectUrl.sendKeys("https://github.com/frolkov76/frolkov76-group_need_more_coffee.git");
        WebElement tabSave = getDriver().findElement(By.xpath("//button[normalize-space()='Save']"));
        tabSave.sendKeys(Keys.RETURN);
        WebElement labelGitHub = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[7]/span/a"));
        labelGitHub.sendKeys(Keys.RETURN);
        WebElement text = getDriver().findElement(By.xpath("//a[normalize-space()='frolkov76-group_need_more_coffee']"));

        Assert.assertEquals(text.getText(), "frolkov76-group_need_more_coffee");

    }

    @Test
    public void testNewFolder() {

        WebElement newItem = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        field.sendKeys("folder");
        WebElement folder = getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]/ul/li[1]"));
        folder.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement tabSave = getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        tabSave.sendKeys(Keys.RETURN);
        WebElement textFolder = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1"));

        Assert.assertEquals(textFolder.getText(), "folder");
    }

    @Test
    public void testNegativeNewItemSpecialSymbolDollar() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);
        WebElement newField = getDriver().findElement(By.xpath("//input[@id='name']"));

        newField.sendKeys("$", Keys.ENTER);

        WebDriverWait waiter = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement textError = waiter.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//div[@id='itemname-invalid']")));

        Assert.assertEquals(textError.getText(), "» ‘$’ is an unsafe character");

    }

    @Test
    public void testErrorTextEmptyField() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        newItem.sendKeys(Keys.RETURN);
        WebElement element = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"org_jenkinsci_plugins_workflow_job_WorkflowJob\"]"))));
        WebElement pipeline = getDriver().findElement(By.xpath("//*[@class=\"org_jenkinsci_plugins_workflow_job_WorkflowJob\"]"));
        pipeline.sendKeys(Keys.RETURN);
        WebElement textError = getDriver().findElement(By.xpath(" //div[@id='itemname-required']"));

        Assert.assertEquals(textError.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testMaxNumberOfSymbolForErrorMessage() {

        WebElement newItem = getDriver().findElement(By.xpath("//*[@class=\"task-link \"]"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        field.sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh " +
                "euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis " +
                "nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea co");
        WebElement pipeline = getDriver().findElement(By.xpath("//*[@class=\"org_jenkinsci_plugins_workflow_job_WorkflowJob\"]"));
        pipeline.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement element = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[normalize-space()='A problem occurred while processing the request.']"))));
        WebElement textPipeline = getDriver().findElement(By.xpath("//h2[normalize-space()='A problem occurred while processing the request.']"));

        Assert.assertEquals(textPipeline.getText(), "A problem occurred while processing the request.");

    }

    @Ignore
    @Test
    public void testNewMultiConfigurationProject() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.sendKeys(Keys.RETURN);
        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("project");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']"));
        freestyleProject.sendKeys(Keys.RETURN);
        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.sendKeys(Keys.RETURN);
        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        description.sendKeys("MultiConfigurationProject");
        WebElement tabSave = getDriver().findElement(By.xpath("//button[normalize-space()='Save']"));
        tabSave.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//div[normalize-space()='MultiConfigurationProject']"));

        Assert.assertEquals(text.getText(), "MultiConfigurationProject");
    }

    @Test
    public void testCreateUser() {

        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkins.sendKeys(Keys.RETURN);
        WebElement manageUsers = getDriver().findElement(By.xpath("//a[@href='securityRealm/']"));
        manageUsers.sendKeys(Keys.RETURN);
        WebElement createUsers = getDriver().findElement(By.xpath("//a[normalize-space()='Create User']"));
        createUsers.sendKeys(Keys.RETURN);
        WebElement userName = getDriver().findElement(By.xpath("//input[@id='username']"));
        userName.sendKeys("Eleonora");
        WebElement password = getDriver().findElement(By.xpath("//input[@name='password1']"));
        password.sendKeys("Abc123");
        WebElement confirmPassword = getDriver().findElement(By.xpath("//input[@name='password2']"));
        confirmPassword.sendKeys("Abc123");
        WebElement fullName = getDriver().findElement(By.xpath("//input[@name='fullname']"));
        fullName.sendKeys("Eleonora Butonaeva");
        WebElement emailAddress = getDriver().findElement(By.xpath("//input[@name='email']"));
        emailAddress.sendKeys("butonaevaeleonora@gmail.com");
        WebElement createUser = getDriver().findElement(By.xpath("//button[normalize-space()='Create User']"));
        createUser.sendKeys(Keys.RETURN);

        WebElement name = getDriver().findElement(By.xpath("//th[3]"));
        name.click();

        WebElement userId = getDriver().findElement(By.xpath("//a[normalize-space()='Eleonora']"));

        Assert.assertEquals(userId.getText(), "Eleonora");

        WebElement delete = getDriver().findElement(By.xpath("//a[@class='jenkins-table__button" +
                " jenkins-!-destructive-color']//*[name()='svg']"));
        delete.click();
        WebElement buttonYes = getDriver().findElement(By.xpath("//button[normalize-space()='Yes']"));
        buttonYes.sendKeys(Keys.RETURN);

    }
}





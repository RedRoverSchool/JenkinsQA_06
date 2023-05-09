package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BestAutoTeamTest extends BaseTest {

    @Test
    public void testIsJenkinsLoad() throws InterruptedException {

        WebElement jenkinsLogo = getDriver().findElement(By.xpath("//*[@id='jenkins-home-link']"));

        Assert.assertTrue(jenkinsLogo.isDisplayed());
    }

    @Ignore
    @Test
    public void testAddDescription() throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("TestDescription");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/*[1]")).getText(), "TestDescription");
    }

    @Ignore
    @Test
    public void testEditDescription() throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("TestDescription");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Edited");

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/*[1]")).getText(), "EditedTestDescription");
    }

    @Test
    public void testNavigationPanelElementsDashboard() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[1]//span[@class = 'task-link-text']"));
        WebElement people = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[2]//span[@class = 'task-link-text']"));
        WebElement buildHistory = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[3]//span[@class = 'task-link-text']"));
        WebElement manageJenkins = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[4]//span[@class = 'task-link-text']"));
        WebElement myViews = getDriver().findElement(By.xpath("//div[@id = 'tasks']//descendant::div[5]//span[@class = 'task-link-text']"));

        Assert.assertEquals(newItem.getText(), "New Item");
        Assert.assertEquals(people.getText(), "People");
        Assert.assertEquals(buildHistory.getText(), "Build History");
        Assert.assertEquals(manageJenkins.getText(), "Manage Jenkins");
        Assert.assertEquals(myViews.getText(), "My Views");
    }

    @Test
    public void testAddNewItem() {

        WebElement buttonNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        buttonNewItem.click();

        WebElement inputName = getDriver().findElement(By.xpath("//input[@name='name']"));
        inputName.sendKeys("New Item adding test");

        WebElement buttonFreestyleProject = getDriver().findElement(By.xpath("//li[@class ='hudson_model_FreeStyleProject']"));
        buttonFreestyleProject.click();

        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']"));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSave.click();

        WebElement titleOfPage = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));
        WebElement buttonAddDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));

        Assert.assertEquals(titleOfPage.getText(), "Project New Item adding test");
        Assert.assertEquals(buttonAddDescription.getText(), "Add description");
    }

    @Ignore
    @Test
    public void testJenkinsUser() {

        WebElement usernameFromPageHeader = getDriver().findElement(By.xpath("//div[@class='login page-header__hyperlinks']//a[@class='model-link']//span"));
        String actualUsername = usernameFromPageHeader.getText();
        WebElement linkPeople = getDriver().findElement(By.xpath("//div[@class='task '][2]//a"));
        linkPeople.click();
        WebElement usernameFromTablePeople = getDriver().findElement(By.xpath("//div[@id='main-panel']//td[3]"));
        String usernameFromTable = usernameFromTablePeople.getText();

        Assert.assertEquals(usernameFromTable, actualUsername, "Usernames are different");

    }

    @Test
    public void testRunJob() throws InterruptedException {
        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        Thread.sleep(2000);

        getDriver().findElement(By.xpath("//input[@class='jenkins-input']")).sendKeys("Test Job");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(), "Project Test Job");

        getDriver().findElement(By.xpath("//a[@href='/job/Test%20Job/build?delay=0sec']")).click();
        Thread.sleep(6000);

        getDriver().findElement(By.xpath("//tr[@class='build-row multi-line overflow-checked']//a[1]")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[@class='build-status-icon__outer']//*[@title='Success']")).isDisplayed());
    }
}
package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.BaseUtils;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupZeroBugTest extends BaseTest {
    private WebDriverWait webDriverWait;
    private final Faker faker = new Faker();

    private void clickHomePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    public final WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return webDriverWait;
    }

    @Test
    public void testFirstJobIsCreated() throws InterruptedException {

        int iteration = 3;

        String expectedHeaderHP = "Welcome to Jenkins!";
        String actualHeaderHP = getDriver().findElement(By.xpath("//h1[.='Welcome to Jenkins!']")).getText();
        Assert.assertEquals(actualHeaderHP, expectedHeaderHP, "Wrong text from header HP");

        for (int i = 1; i <= iteration; i++) {
            String jobName = "Job" + i + "";
            WebElement createBtn = getDriver().findElement(By.xpath("//span[text()='New Item']/../.."));
            createBtn.click();

            Thread.sleep(2000);
            String expectedHeaderItemName = "Enter an item name";
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String actualHeaderItemName = getDriver().findElement(By.xpath("//label[text()='Enter an item name']")).getText();
            Assert.assertEquals(actualHeaderItemName, expectedHeaderItemName, "Wrong text from Item HP");

            WebElement field = getDriver().findElement(By.cssSelector("#name"));
            field.sendKeys(jobName);

            WebElement createJobBtn = getDriver().findElement(By.xpath("//span[text()='Freestyle project']/../.."));
            createJobBtn.click();

            Thread.sleep(2000);
            WebElement oKBtn = getDriver().findElement(By.id("ok-button"));
            oKBtn.click();

            Thread.sleep(2000);
            WebElement jenkinsIcon = getDriver().findElement(By.cssSelector("#jenkins-name-icon"));
            jenkinsIcon.click();
            Thread.sleep(2000);
            String expectedHPTitle = "Dashboard [Jenkins]";
            String actualHPTitle = getDriver().getTitle();
            Assert.assertEquals(actualHPTitle, expectedHPTitle, "Wrong Title");
        }
        WebElement jenkinsIcon = getDriver().findElement(By.cssSelector("#jenkins-name-icon"));
        jenkinsIcon.click();

        Thread.sleep(2000);
        List<WebElement> jobList = getDriver().findElements(By.xpath("//tr[@class=' job-status-nobuilt']/td[3]"));
        System.out.println(jobList.size());
        int count = 1;
        for (WebElement job : jobList) {
            String jobExpectedName = "Job" + count + "";
            String jobActualName = job.getText();
            Assert.assertEquals(jobActualName, jobExpectedName, "Wrong job name");
            count++;
        }

        int countJob = 1;
        for (int i = 0; i < jobList.size(); i++) {
            WebElement jobNameElement = getDriver().findElement(By.xpath("//tr[@id='job_Job" + countJob + "']/td[3]/a/span"));
            jobNameElement.click();
            Thread.sleep(2000);
            WebElement deleteBtn = getDriver().findElement(By.xpath("//*[@class='icon-edit-delete icon-md']"));
            deleteBtn.click();
            Thread.sleep(2000);
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
            Thread.sleep(2000);
            countJob++;
        }

        Assert.assertEquals(actualHeaderHP, expectedHeaderHP, "Wrong text from header HP");
    }

    private void newJob(String jobName) {

        WebElement newJob = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']/.."));
        newJob.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys(jobName);

        WebElement freestyleProject = getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject"));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement gitProject = getDriver().findElement((By.xpath("//label[text()='GitHub project']")));
        gitProject.click();

        WebElement urlField = getDriver().findElement((By.xpath("//input[@name='_.projectUrlStr']")));
        urlField.sendKeys("https://github.com/Lighter888/ZeroBugJavaPractice");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        clickHomePage();
    }

    private void deleteJob() {

        WebElement deleteJob = getDriver().findElement(By.cssSelector(".task-link.confirmation-link"));
        deleteJob.click();

        try {
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert is present.");
        }
    }

    @Test
    public void testNewJobCreated() throws InterruptedException {

        String name = "Job" + faker.name().firstName();
        newJob(name);

        getDriver().findElement(By.xpath("//a[@href='job/%s/']".formatted(name))).click();
        Thread.sleep(2000);
        String actualNameJob = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();
        String expectedNameJob = "Project %s".formatted(name);
        Assert.assertEquals(actualNameJob, expectedNameJob, " The name of job is not equal");

        deleteJob();
    }
    @Test
    public void testJobBuild() {

        String name = "Job" + faker.name().firstName();
        newJob(name);

        for (int trial = 1; trial <= 3; trial++) {

            getDriver().findElement(By.id("jenkins-home-link")).click();

            String scheduleBuildXpath = "//a[@title='Schedule a Build for %s']".formatted(name);
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(scheduleBuildXpath)));
            WebElement scheduleBuild = getDriver().findElement(By.xpath(scheduleBuildXpath));
            getWait().until(ExpectedConditions.elementToBeClickable(scheduleBuild));
            scheduleBuild.click();

            WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']/.."));
            getWait().until(ExpectedConditions.elementToBeClickable(buildHistory));
            buildHistory.click();

            String numberBuildXpath = "//a[.='#%s']".formatted(trial);
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(numberBuildXpath)));
            WebElement numberBuild = getDriver().findElement(By.xpath(numberBuildXpath));

            String actualNumberBuild = numberBuild.getText();
            String expectedNumberBuild = "#" + trial;
            BaseUtils.log("Check Build #%s with %s".formatted(trial, name));
            Assert.assertEquals(actualNumberBuild, expectedNumberBuild, "Build has been scheduled incorrectly");
            getDriver().navigate().back();
        }
        getDriver().navigate().back();
        deleteJob();
    }
    @Test
    public void testRenameJob() throws InterruptedException {

        String name = "Job" + faker.name().firstName();
        newJob(name);


        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[@href='job/" + name + "/']")).click();
        BaseUtils.log("1. User click Job");

        Thread.sleep(1000);
        String expectResultJob = String.format("Project %s", name);
        String actualResultJob = getDriver().findElement(By.xpath("//h1[contains(.,'Project')]")).getText();
        Assert.assertEquals(actualResultJob, expectResultJob, "The function rename Job is not working");

        Thread.sleep(1000);
        getDriver().findElement(By.xpath("(//a[@class='task-link '])[6]")).click();
        BaseUtils.log("2. User click rename Job");

        Assert.assertTrue(getDriver().findElement(By.className("warning")).isDisplayed(), "Warning message not displayed");
        String newNameJob = "New";
        getDriver().findElement(By.xpath("//input[@name='newName']")).sendKeys(newNameJob);
        Thread.sleep(1000);
        getDriver().findElement(By.name("Submit")).click();
        BaseUtils.log("3. User click submit Btn");

        Thread.sleep(1000);
        String expectResultRenameJob = String.format("Project %s%s", name, newNameJob);
        String actualResultRenameJob = getDriver().findElement(By.xpath("//h1[contains(.,'Project')]")).getText();

        Assert.assertEquals(actualResultRenameJob, expectResultRenameJob, "The function rename Job is not working");

        deleteJob();
    }

    @Test
    public void testJenkinsVersionCheck() {

        String expectedResult = "Jenkins 2.387.2";
        WebElement versionNumber = getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']"));

        String actualResult = versionNumber.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSearchFieldIsPresent() {

        String expectedResult = "Welcome to Jenkins!";
        WebElement result = getDriver().findElement(By.xpath("//h1[.='Welcome to Jenkins!']"));
        String actualResult = result.getText();
        Assert.assertEquals(actualResult, expectedResult);

        WebElement searchField = getDriver().findElement(By.cssSelector("#search-box"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void testDashboardIsPresent() {

        String dashboardExpected = "Dashboard";
        WebElement dashboard = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        String dashboardActual = dashboard.getText();

        Assert.assertEquals(dashboardActual, dashboardExpected);
    }

    @Test
    public void testCreateJob() {

        WebElement createButton = getDriver().findElement(By.xpath("//span[text()= 'Create a job']"));
        createButton.click();

        WebElement textBox = getDriver().findElement(By.name("name"));
        textBox.sendKeys("Project_1");

        WebElement freestyleProject = getDriver().findElement(By.xpath("//span[.='Freestyle project']"));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[@href='/'][@class ='model-link']"));
        dashboard.click();

        WebElement projectName = getDriver().findElement(By.xpath("//a[@href = 'job/Project_1/']"));

        Assert.assertTrue(projectName.isDisplayed());
    }
    @Test
    public void testLogOutIconIsPresent() {

        String dashboardExpected = "log out";
        WebElement dashboard = getDriver().findElement(By.xpath("//a[@href='/logout']"));
        String dashboardActual = dashboard.getText();

        Assert.assertEquals(dashboardActual, dashboardExpected);
    }

    @Test
    public void testQuantityOfSubmenuInAdminMenu() {

        int expectedResultQuantityOfSubmenuList = 6;

        WebElement adminMenuList = getDriver().findElement(
                By.xpath("//a[@class='model-link']/span[@class='hidden-xs hidden-sm']"));
        adminMenuList.click();

        List<WebElement> menuAdminList = getDriver().findElements(By.xpath("//div[@id ='side-panel']/div/div"));
        int actualResultQuantityOfSubmenuList = menuAdminList.size();

       Assert.assertEquals(actualResultQuantityOfSubmenuList, expectedResultQuantityOfSubmenuList);
    }
}
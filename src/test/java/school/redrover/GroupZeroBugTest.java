package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
        for (int i=0; i< jobList.size(); i++) {
            WebElement jobNameElement = getDriver().findElement(By.xpath("//tr[@id='job_Job"+countJob+"']/td[3]/a/span"));
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


    private WebDriverWait webDriverWait3;

    public final WebDriverWait getWait3() {
        if (webDriverWait3 == null) {
            webDriverWait3 = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        }
        return webDriverWait3;
    }

    private void mainPage() {
        getDriver().get("http://localhost:8080/");
    }

    private void jobPage() {
        getDriver().get("http://localhost:8080/me/my-views/view/all/job/ZeroBugJavaPractice/");
    }

    private void newJob() {

        WebElement newJob = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']/.."));
        newJob.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys("ZeroBugJavaPractice");

        WebElement freestyleProject = getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject"));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement gitProject = getDriver().findElement((By.xpath("//label[text()='GitHub project']")));
        gitProject.click();

        WebElement urlField = getDriver().findElement((By.xpath("//input[@name='_.projectUrlStr']")));
        urlField.sendKeys("https://github.com/Lighter888/ZeroBugJavaPractice");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
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

    @Test(priority = 1)
    public void testNewJobCreated() {

        newJob();

        String actualNameJob = getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText();
        String expectedNameJob = "ZeroBugJavaPractice";
        Assert.assertEquals(actualNameJob, "Project " + expectedNameJob, " The name of job is not equal");

        deleteJob();
    }

    @Test(priority = 2)
    public void testJobBuild() {

        newJob();
        mainPage();

        for (int trial = 1; trial <= 3; trial++) {

            WebElement scheduleBuild = getDriver().findElement(By.xpath("//a[@title='Schedule a Build for ZeroBugJavaPractice']"));
            getWait3().until(ExpectedConditions.elementToBeClickable(scheduleBuild));
            scheduleBuild.click();

            WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']/.."));
            getWait3().until(ExpectedConditions.elementToBeClickable(buildHistory));
            buildHistory.click();

            getWait3().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='#%s']".formatted(trial))));
            WebElement numberBuild = getDriver().findElement(By.xpath("//a[.='#%s']".formatted(trial)));

            String actualNumberBuild = numberBuild.getText();
            String expectedNumberBuild = "#" + trial;
             BaseUtils.log("Check Build #%s".formatted(trial));
            Assert.assertEquals(actualNumberBuild, expectedNumberBuild, "Build has been scheduled incorrectly");
            mainPage();
        }

        jobPage();
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
}
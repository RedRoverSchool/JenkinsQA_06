package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupZeroBugTest extends BaseTest {

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
    public void verifyNewJobCreated() {

        newJob();

        String actualNameJob = getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText();
        String expectedNameJob = "ZeroBugJavaPractice";
        Assert.assertEquals(actualNameJob,"Project "+ expectedNameJob," The name of job is not equal");

        deleteJob();
    }

    @Test(priority = 2)
    public void verifyJobBuild() {

        newJob();
        mainPage();

        for (int trial = 1; trial <=3; trial++) {

            WebElement scheduleBuild = getDriver().findElement(By.xpath("//a[@title='Schedule a Build for ZeroBugJavaPractice']"));
            scheduleBuild.click();
            WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']/.."));
            buildHistory.click();
            String actualNumberBuild = getDriver().findElement(By.xpath("//a[.='#%s']".formatted(trial))).getText();
            String expectedNumberBuild = "#" + trial;
            Assert.assertEquals(actualNumberBuild,expectedNumberBuild, "Build has been scheduled incorrectly");
            mainPage();
        }

        jobPage();
        deleteJob();

    }
}

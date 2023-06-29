package school.redrover.cucumber;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.*;
import school.redrover.runner.CucumberDriver;
import school.redrover.runner.ProjectUtils;
import school.redrover.runner.TestUtils;


public class FreestyleTest {

    private MainPage mainPage;
    private NewJobPage newJobPage;

    private FreestyleProjectPage freestylePage;
    private FreestyleProjectConfigPage freestyleProjectConfigPage;

    @When("Go to NewJob")
    public void goToNewJob() {
        newJobPage = new MainPage(CucumberDriver.getDriver()).clickNewItem();
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        newJobPage.enterItemName(name);
    }

    @And("Choose job type as {string}")
    public void setJobType(String jobType) {
        newJobPage = newJobPage.selectJobType(TestUtils.JobType.valueOf(jobType));
    }

    @And("Choose job type as Freestyle")
    public void setJobTypeAsFreestyle() {
        newJobPage = newJobPage.selectJobType(TestUtils.JobType.FreestyleProject);
    }

    @And("Click Ok and go to config")
    public void clickOkAndGoToConfig() {
        newJobPage.clickOkButton(newJobPage.getJobType().createConfigPage(CucumberDriver.getDriver()));
    }

    @And("Go home")
    public void goHome() {
        ProjectUtils.get(CucumberDriver.getDriver());
        mainPage = new MainPage(CucumberDriver.getDriver());
    }

    @And("Job with name {string} is exists")
    public void checkJobName(String jobName) {
        Assert.assertTrue(mainPage.jobIsDisplayed(jobName));
    }

    @And("Save config and go to Freestyle job")
    public void saveConfigAndGoToFreestyleJob() {
        freestylePage = new FreestyleProjectConfigPage(new FreestyleProjectPage(CucumberDriver.getDriver()))
                .clickSaveButton();
    }

    @Then("Freestyle job name is {string}")
    public void assertFreestyleJobName(String jobName) {
        Assert.assertEquals(freestylePage.getJobName(), jobName);
    }

    @When("Click Freestyle job {string}")
    public void clickFreestyleJob(String jobName) {
        freestylePage = new MainPage(CucumberDriver.getDriver()).clickJobName(jobName, new FreestyleProjectPage(CucumberDriver.getDriver()));
    }

    @And("Click Freestyle configure")
    public void clickFreestyleConfigure() {
        freestyleProjectConfigPage = freestylePage.clickConfigure();
    }

    @And("Type Freestyle job description as {string}")
    public void setFreestyleJobDescription(String jobDescription) {
        freestyleProjectConfigPage.addDescription(jobDescription);
    }

    @Then("Job description is {string}")
    public void assertFreestyleJobDescription(String jobDescription) {
        Assert.assertEquals(freestylePage.getDescription(), jobDescription);
    }
}

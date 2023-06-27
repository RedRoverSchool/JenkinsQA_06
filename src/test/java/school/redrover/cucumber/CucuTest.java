package school.redrover.cucumber;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.jobs.*;
import school.redrover.model.jobsconfig.*;
import school.redrover.runner.CucumberDriver;
import school.redrover.runner.ProjectUtils;
import school.redrover.runner.TestUtils;


public class CucuTest {

    private MainPage mainPage;
    private NewJobPage newJobPage;

    private FreestyleProjectPage freestylePage;
    private FreestyleProjectConfigPage freestyleProjectConfigPage;

    @When("Go1 to NewJob")
    public void goToNewJob() {
        newJobPage = new MainPage(CucumberDriver.getDriver()).clickNewItem();
    }

    @And("Type1 job name {string}")
    public void enterItemName(String name) {
        newJobPage.enterItemName(name);
    }

    @And("Choose1 job type as {string}")
    public void setJobType(String jobType) {
        newJobPage = newJobPage.selectJobType(TestUtils.JobType.valueOf(jobType));
    }

    @And("Choose1 job type as Freestyle")
    public void setJobTypeAsFreestyle() {
        newJobPage = newJobPage.selectJobType(TestUtils.JobType.FreestyleProject);
    }

    @And("Click1 Ok and go to config")
    public void clickOkAndGoToConfig() {
        newJobPage.clickOkButton(newJobPage.getJobType().createConfigPage(CucumberDriver.getDriver()));
    }


    @And("Save1 config and go to Freestyle job")
    public void saveConfigAndGoToFreestyleJob() {
        freestylePage = new FreestyleProjectConfigPage(new FreestyleProjectPage(CucumberDriver.getDriver()))
                .clickSaveButton();
    }

    @Then("Freestyle1 job name is {string}")
    public void assertFreestyleJobName(String jobName) {
        Assert.assertEquals(freestylePage.getJobName(), jobName);
    }

}

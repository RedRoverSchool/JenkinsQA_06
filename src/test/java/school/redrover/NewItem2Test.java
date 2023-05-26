package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem2Test extends BaseTest {

    @DataProvider(name = "all-jobs-creation")
    public Object[][] provideNamesAndTypesOfJobs() {
        return new Object[][]{
                {"Freestyle_project", "hudson_model_FreeStyleProject"},
                {"Pipeline", "org_jenkinsci_plugins_workflow_job_WorkflowJob"},
                {"Multiconfiguration-project", "hudson_matrix_MatrixProject"},
                {"Folder", "com_cloudbees_hudson_plugins_folder_Folder"},
                {"Multibranch-Pipeline", "org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject"},
                {"Organization-Folder", "jenkins_branch_OrganizationFolder"}
        };
    }

    @Test(dataProvider = "all-jobs-creation")
    public void testCreateAllJobs(String name, String jobType) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(name);
        getDriver().findElement(By.className(jobType)).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getWait5().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.tagName("h1")), name));

        getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span")).getText(), name);
    }

}

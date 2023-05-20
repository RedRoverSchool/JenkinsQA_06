package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem2Test extends BaseTest {

    private static final By NEW_ITEM_BUTTON = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME_INPUT_FIELD = By.xpath("//input[@id='name']");
    private static final By OK_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    @Ignore
    @Test
    public void testCreateFolder() {
        final String expectedFolderName = "First folder";

        WebElement buttonCreateItem = getDriver().findElement(NEW_ITEM_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(NAME_INPUT_FIELD);
        getWait5().until(ExpectedConditions.visibilityOf(fieldInputName));
        fieldInputName.sendKeys(expectedFolderName);

        WebElement buttonFolder = getDriver().findElement(By.xpath("//span[text()='Folder']"));
        buttonFolder.click();

        WebElement buttonOk = getDriver().findElement(OK_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk));
        buttonOk.click();

        WebElement buttonSave = getDriver().findElement(SAVE_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();

        WebElement titleName = getDriver().findElement(By.xpath("//h1"));
        getWait5().until(ExpectedConditions.visibilityOf(titleName));

        String actualFolderName = titleName.getText();
        Assert.assertEquals(actualFolderName, expectedFolderName);
    }

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

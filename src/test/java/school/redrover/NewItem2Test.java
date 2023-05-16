package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private static final By MULTIBRANCH_PIPELINE_TYPE = By.xpath("//span[text() = 'Multibranch Pipeline']");
    private static final By OK_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    @Test
    public void testCreateNewItemWithNullName() {
        final String expectedErrorMessage = "» This field cannot be empty, please enter a valid name";

        WebElement buttonCreateItem = getDriver().findElement(NEW_ITEM_BUTTON);
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(NAME_INPUT_FIELD);
        getWait5().until(ExpectedConditions.elementToBeClickable(fieldInputName));
        fieldInputName.click();

        WebElement buttonMultibranchPipeline = getDriver().findElement(MULTIBRANCH_PIPELINE_TYPE);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()", buttonMultibranchPipeline);

        getWait5().until(ExpectedConditions.elementToBeClickable(buttonMultibranchPipeline));
        buttonMultibranchPipeline.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id = 'itemname-required']"));

        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }

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

    @Ignore
    @Test(dataProvider = "all-jobs-creation")
    public void testAllJobsCreation(String name, String jobType) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(name);

        getDriver().findElement(By.className(jobType)).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getWait2().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.tagName("h1")), name));

        getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/span")).getText(), name);
    }

    @DataProvider(name = "invalid-data")
    public Object[][] provideInvalidData() {
        return new Object[][]{{"!"}, {"#"}, {"$"}, {"%"}, {"&"}, {"*"}, {"/"}, {":"},
                {";"}, {"<"}, {">"}, {"?"}, {"@"}, {"["}, {"]"}, {"|"}, {"\\"}, {"^"}};
    }

    @Test(dataProvider = "invalid-data")
    public void testCreateFolderUsingInvalidData(String invalidData) {
        String errorMessage = "» ‘" + invalidData + "’ is an unsafe character";

        WebElement createItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        createItemButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Folder']"))).click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        fieldInputName.clear();
        fieldInputName.sendKeys(invalidData);

        WebElement resultMessage = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
        String messageValue = resultMessage.getText();

        Assert.assertEquals(messageValue, errorMessage);
    }
}

package school.redrover;

import org.openqa.selenium.By;
<<<<<<< HEAD
=======
>>>>>>> ug/pipiline
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProject5Test extends BaseTest {

<<<<<<< HEAD
    public  static String projectName = "FirstProject";

    private static final By CREATE_BUTTON = By.xpath("//span[text()= 'Create a job']");
    private static final By TEXT_BOX = By.name("name");
    private static final By PIPELINE = By.xpath("//span[.='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[@name = 'Submit']");
    private static final By DASHBOARD = By.xpath("//a[@href='/'][@class ='model-link']");
    private static final By PROJECT_IS_IN_DASHBOARD = By.xpath("//span[.='FirstProject']");

    @Test
    public void testCreatePipelineProject() {

        getDriver().findElement(CREATE_BUTTON).click();
        getDriver().findElement(TEXT_BOX).sendKeys(projectName);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD).click();

        Assert.assertEquals(getDriver().findElement(PROJECT_IS_IN_DASHBOARD).getText(), projectName);
=======
    private By NEW_ITEM = By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']");

    private By INPUT_NAME = By.xpath("//div//input[@name = 'name'][@id ='name']");
    private By CREATE_PIPELINE = By.xpath(
            "//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
    private By OK_BUTTON = By.id("ok-button");

    private By SAVE_BUTTON = By.name("Submit");
    private By CREATE_A_JOB = By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a/span[1]");

    @Test

    public void testCreatePipelineProjectNewItem() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys("Project1");
        getDriver().findElement(CREATE_PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'main-panel']//h1['Pipeline Project1']")).getText(),
                "Pipeline Project1");
    }
    @Test

        public void testCreatePipelineProjectCreateJob() {
        getDriver().findElement(CREATE_A_JOB).click();
        getDriver().findElement(INPUT_NAME).sendKeys("Project2");
        getDriver().findElement(CREATE_PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'main-panel']//h1['Pipeline Project2']")).getText(),
                "Pipeline Project2");

>>>>>>> ug/pipiline
    }
}

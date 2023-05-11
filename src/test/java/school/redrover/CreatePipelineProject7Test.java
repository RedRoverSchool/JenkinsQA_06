package school.redrover;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

    public class CreatePipelineProject7Test extends BaseTest {
        private String name1 = "Project1";
        private By NEW_ITEM = By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']");

        private By INPUT_NAME = By.xpath("//div//input[@name = 'name'][@id ='name']");
        private By CREATE_PIPELINE = By.xpath(
                "//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
        private By OK_BUTTON = By.id("ok-button");

        private By SAVE_BUTTON = By.name("Submit");
        private By DASHBOARD_BUTTON = By.xpath("//div[@id='breadcrumbBar']//a");

        @Test

        public void testCreatePipelineProjectNewItem() {

            getDriver().findElement(NEW_ITEM).click();
            getDriver().findElement(INPUT_NAME).sendKeys(name1);
            getDriver().findElement(CREATE_PIPELINE).click();
            getDriver().findElement(OK_BUTTON).click();
            getDriver().findElement(SAVE_BUTTON).click();

            Assert.assertEquals(getDriver().findElement(By.xpath(
                            "//div[@id = 'main-panel']//h1['Pipeline " + name1 + "']")).getText(),
                    "Pipeline " + name1);
        }
        @Test
        public void testFindPipelineOnTheDashboard(){
            getDriver().findElement(NEW_ITEM).click();
            getDriver().findElement(INPUT_NAME).sendKeys(name1);
            getDriver().findElement(CREATE_PIPELINE).click();
            getDriver().findElement(OK_BUTTON).click();
            getDriver().findElement(SAVE_BUTTON).click();

            getDriver().findElement(DASHBOARD_BUTTON).click();

            Assert.assertEquals(getDriver().findElement(By.xpath(
                    "//tr[@id='job_"+ name1 +"']//a//span['"+ name1 +"']")).getText(), name1);
        }
}


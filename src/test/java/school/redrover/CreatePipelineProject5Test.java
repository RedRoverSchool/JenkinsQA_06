package school.redrover;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProject5Test extends BaseTest{
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
        }
}

=======
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class CreatePipelineProject5Test extends BaseTest{

    @Test
    public void createPipelineProjectTest() {
        String name = "PipelineN";

        WebElement newItemButton = getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='name']"));
        inputField.sendKeys(name);

        WebElement pipelineButton = getDriver().findElement
                (By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[2]/label/span"));
        pipelineButton.click();

        WebElement okButton = getDriver().findElement(By.xpath("//div[@class='btn-decorator']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement dashBoardButton = getDriver().findElement
                (By.xpath("//div[@id='breadcrumbBar']/ol/li[1]/a"));
        dashBoardButton.click();

        WebElement pipelineName = getDriver().findElement(By.xpath("//tr[@id='job_PipelineN']/td/a/span"));

        Assert.assertEquals(pipelineName.getText(), name);
    }
}
>>>>>>> origin/main

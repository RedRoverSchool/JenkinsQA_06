package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class DeletePipeline2Test extends BaseTest {

    private void createTestPipeline() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        WebElement selectItemTypePipeline = getDriver().findElement(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']"));
        selectItemTypePipeline.click();

        WebElement newItemNameInput = getDriver().findElement(By.xpath("//label[@for='name']/following-sibling::input"));

        newItemNameInput.sendKeys("test-pipeline");
        newItemNameInput.sendKeys(Keys.RETURN);

        getDriver().get(getDriver().getCurrentUrl().replaceAll("/job/.+", ""));

    }

    @Test
    public void testPipelineDeletion() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        createTestPipeline();

        WebElement pipelineInList = getDriver().findElement(By.xpath("//a[@href='job/test-pipeline/']"));
        pipelineInList.click();

        WebElement deletePipeline = getDriver().findElement(By.xpath("//a[@data-url='/job/test-pipeline/doDelete']"));

        // attempt deletion
        deletePipeline.click();

        // cancel deletion
        getDriver().switchTo().alert().dismiss();

        //re-attempt deletion
        deletePipeline.click();
        getDriver().switchTo().alert().accept();

        boolean pipelineDeleted = false;
        try {
            getDriver().findElement(By.xpath("//h1[text()='Welcome to Jenkins!']"));
            pipelineDeleted = true; // won't execute if there is no welcome heading
        } catch (NoSuchElementException noWelcomeHeading){
        } finally {
            try {
                getDriver().findElement(By.xpath("//a[@href='job/test-pipeline/']"));
            } catch (NoSuchElementException deletedPipelineNotFound) {
                pipelineDeleted = true;
            } finally {
                Assert.assertTrue(pipelineDeleted);
            }
        }
    }
}
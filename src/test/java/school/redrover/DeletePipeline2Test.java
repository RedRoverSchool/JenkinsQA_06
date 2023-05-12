package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipeline2Test extends BaseTest {

    // for creation method
    private static By NEW_ITEM_BUTTON = By.xpath("//a[@href='/view/all/newJob']");
    private static By ITEM_TYPE_PIPELINE = By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
    private static By NEW_ITEM_NAME_INPUT = By.xpath("//label[@for='name']/following-sibling::input");
    private static By CONFIGURE_SAVE_BUTTON = By.xpath("//script[@src='/adjuncts/3868c5e4/lib/form/apply/apply.js']/preceding-sibling::button[1]");

    // for deletion test
    private static By PIPELINE_IN_LIST = By.xpath("//a[@href='job/test-pipeline/']");
    private static By DELETE_PIPELINE = By.xpath("//a[@data-url='/job/test-pipeline/doDelete']");
    private static By WELCOME_HEADING = By.xpath("//h1[text()='Welcome to Jenkins!']");

    private void createTestPipeline() {

        WebElement newItemButton = getDriver().findElement(NEW_ITEM_BUTTON);
        newItemButton.click();

        WebElement newItemNameInput = getDriver().findElement(NEW_ITEM_NAME_INPUT);
        WebElement itemTypePipeline = getDriver().findElement(ITEM_TYPE_PIPELINE);
        itemTypePipeline.click();

        newItemNameInput.click();
        newItemNameInput.sendKeys("test-pipeline");
        newItemNameInput.sendKeys(Keys.RETURN);

        WebElement configureSaveButton = getDriver().findElement(CONFIGURE_SAVE_BUTTON);
        configureSaveButton.click();

        // returns to dashboard
        getDriver().get(getDriver().getCurrentUrl().replaceAll("/job/.+", ""));

    }

    @Test
    public void testDeletePipeline_2() throws InterruptedException {

        createTestPipeline();

        WebElement pipelineInList = getDriver().findElement(PIPELINE_IN_LIST);
        pipelineInList.click();

        WebElement deletePipeline = getDriver().findElement(DELETE_PIPELINE);
        deletePipeline.click();

        // cancel deletion
        getDriver().switchTo().alert().dismiss();

        //re-attempt deletion
        deletePipeline.click();
        getDriver().switchTo().alert().accept();

        boolean pipelineDeleted = false;

        try {
            // presence of welcome heading means that there are no items, including the folder
            WebElement welcomeH1 = getDriver().findElement(WELCOME_HEADING);
        } catch (NoSuchElementException noWelcomeHeading) {
            pipelineDeleted = true;
        } finally {
            try {
                WebElement pipelineThatShouldNotExist = getDriver().findElement(PIPELINE_IN_LIST);
            } catch (NoSuchElementException noDeletedPipeline) {
                pipelineDeleted = true;
            }
        }

        Assert.assertTrue(pipelineDeleted);

    }
}

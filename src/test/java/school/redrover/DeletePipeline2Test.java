package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipeline2Test extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By TEXT_FIELD = By.id("name");
    private static final By PIPELINE_PROJECT = By.xpath("//span[normalize-space()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");

    private static final By DELETE_BUTTON = By.xpath("//span[contains(text(),'Delete Pipeline')]");
    private static final String PROEJCT_NAME = "Java";



    public void createPipeline(){
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(TEXT_FIELD).sendKeys(PROEJCT_NAME);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    @Test
    public void testDeletePipeline(){
        createPipeline();
        getDriver().findElement(DELETE_BUTTON).click();
        getDriver().switchTo().alert().accept();
    }
}

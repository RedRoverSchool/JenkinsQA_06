package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProject4Test extends BaseTest {

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
    }
}

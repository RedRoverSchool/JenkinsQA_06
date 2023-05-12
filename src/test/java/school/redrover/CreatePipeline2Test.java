package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipeline2Test extends BaseTest {
    public static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    public static final By TEXT_NAME_FIELD = By.xpath("//input[@id='name']");
    public static final By PROJECT_NAME = By.xpath("//span[normalize-space()='Pipeline']");
    public static String name = "Sample project";
    public static final By OK_BUTTON = By.id("ok-button");
    public static final By SAVE_BUTTON = By.cssSelector("button[name='Submit']");
    public static final By PAGE_HEADLINE = By.xpath("//h1[normalize-space()='Pipeline "+ name +"']");
    @Test
    public void testCreatePipeline() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(TEXT_NAME_FIELD).sendKeys(name);
        getDriver().findElement(PROJECT_NAME).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(PAGE_HEADLINE).getText(), "Pipeline "+ name);
    }
}
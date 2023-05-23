package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ConfigureFolder2Test extends BaseTest {
    private static final String PROJECT_NAME = "qwerty";
    private static final By FOLDER_SUBMIT_BUTTON = By.xpath("//button [@name='Submit']");
    private static final By CONFIGURE_FOLDER_BUTTON = By.xpath("//a [@href='/job/qwerty/configure']");

    @Test
    public void testSetDisplayName(){
        String FOLDER_NAME = "folder";
        TestUtils.createFolder(this, PROJECT_NAME, false);

        getWait2().until(ExpectedConditions.elementToBeClickable(CONFIGURE_FOLDER_BUTTON)).click();

        getDriver().findElement(By.xpath("//input [@name='_.displayNameOrNull']")).sendKeys(FOLDER_NAME);
        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();

        assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), FOLDER_NAME);
    }

    @Test
    public void testAddDescription(){
        String fodlerDescription = "description of the Folder Project";
        TestUtils.createFolder(this, PROJECT_NAME, false);

        getWait2().until(ExpectedConditions.elementToBeClickable(CONFIGURE_FOLDER_BUTTON)).click();

        getDriver().findElement(By.name("_.description")).sendKeys(fodlerDescription);
        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();

        assertEquals(getDriver().findElement(By.xpath("//div[@id='view-message']")).getText(), fodlerDescription);
    }

    @Test
    public void testAddHealthMetric(){
        TestUtils.createFolder(this, PROJECT_NAME, false);
        getWait2().until(ExpectedConditions.elementToBeClickable(CONFIGURE_FOLDER_BUTTON)).click();

        getDriver().findElement(By.xpath("//button [@class='jenkins-button advanced-button advancedButton']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id='yui-gen1-button']"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='yuimenuitemlabel']"))).click();

        assertTrue(getDriver().findElement(By.xpath("//div[@name='healthMetrics']")).isDisplayed());

        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();
    }
}

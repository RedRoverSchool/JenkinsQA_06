package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.time.Duration;

public class BuildHistoryPageTest extends BaseTest {
    private static final String NAME_PIPELINE = "Pipeline2023";
    private static final String BUILD_DESCRIPTION = "For QA";
    private static final By LOGO_JENKINS = By.id("jenkins-name-icon");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By BUILD_HISTORY = By.xpath("//a[@href='/view/all/builds']");
    private static final By BUILD_SCHEDULE = By.xpath("//a[@href='job/" + NAME_PIPELINE + "/build?delay=0sec']");
    private static final By SERIAL_NUMBER_OF_BUILD = By.xpath(
            "//a[@href='/job/" + NAME_PIPELINE + "/1/']");
    private static final By DROP_DOWN_SERIAL_NUMBER = By.xpath(
            "//a[@href='/job/" + NAME_PIPELINE + "/1/']/button");
    private static final By EDIT_BUILD_INFORMATION = By.xpath("//a[@href='/job/" + NAME_PIPELINE + "/1/configure']");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@name='description']");
    private static final By DESCRIPTION_TEXT = By.xpath("//div[@id='description']/div[1]");

    protected void clickDropDownSerialNumberOfBuild() {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath(
                "//a[@class='jenkins-table__link jenkins-table__badge model-link inside']//button[@class='jenkins-menu-dropdown-chevron']"
        )));
    }
    protected void clickEditBuildInformation() {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath(
                "//ul[@class='first-of-type']//a[@href='/job/" + NAME_PIPELINE + "/1/configure']"
        )));
    }
    @Test
    public void testNavigateToBuildHistoryPage() throws InterruptedException {

        final String expectedBuildHistoryPageUrl = "http://localhost:8080/view/all/builds";
        final String expectedBuildHistoryPageTitle = "All [Jenkins]";

        WebElement buildHistorySideMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
        buildHistorySideMenu.click();

        String actualBuildHistoryPageTitle = getDriver().getTitle();
        String actualBuildHistoryPageUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualBuildHistoryPageTitle, expectedBuildHistoryPageTitle);
        Assert.assertEquals(actualBuildHistoryPageUrl, expectedBuildHistoryPageUrl);
    }
    @Ignore
    @Test
    public void testAddDescriptionForBuild(){

        TestUtils.createPipeline(this, NAME_PIPELINE, true);

        getDriver().findElement(LOGO_JENKINS).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUILD_SCHEDULE)).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(BUILD_HISTORY)).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(SERIAL_NUMBER_OF_BUILD));
        new Actions(getDriver()).moveToElement(getDriver().findElement(SERIAL_NUMBER_OF_BUILD))
                .pause(Duration.ofSeconds(5))
                .moveToElement(getDriver().findElement(DROP_DOWN_SERIAL_NUMBER))
                .pause(Duration.ofSeconds(5))
                .perform();

        clickDropDownSerialNumberOfBuild();

        getWait10().until(ExpectedConditions.elementToBeClickable(EDIT_BUILD_INFORMATION));
        clickEditBuildInformation();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_FIELD)).sendKeys(BUILD_DESCRIPTION);

        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXT).getText(), BUILD_DESCRIPTION);
    }

    @Test
    public void testAddDescriptionToBuild()  {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        TestUtils.createPipeline(this, NAME_PIPELINE, true);

        getDriver().findElement(LOGO_JENKINS).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(BUILD_SCHEDULE)).click();
        getDriver().findElement(BUILD_HISTORY).click();

        new Actions(getDriver()).moveToElement(getDriver().findElement(SERIAL_NUMBER_OF_BUILD)).perform();

        js.executeScript("arguments[0].click();", getDriver().findElement(By
                .xpath("//a[@href='/job/" + NAME_PIPELINE + "/1/']/button")));

        getWait2().until(ExpectedConditions.elementToBeClickable(EDIT_BUILD_INFORMATION)).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_FIELD)).sendKeys(BUILD_DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXT).getText(), BUILD_DESCRIPTION);
    }
}
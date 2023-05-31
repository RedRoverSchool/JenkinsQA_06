package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.ConsoleOutputPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;


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
            "//a[contains(@class, 'badge model-link inside')]/button");
    private static final By EDIT_BUILD_INFORMATION = By.xpath("//a[@href='/job/" + NAME_PIPELINE + "/1/configure']");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@name='description']");
    private static final By DESCRIPTION_TEXT = By.xpath("//div[@id='description']/div[1]");

    private final String freestyleProjectName = "FreestyleName";

    @Test
    public void testNavigateToBuildHistoryPage() {

        final String expectedBuildHistoryPageUrl = "http://localhost:8080/view/all/builds";
        final String expectedBuildHistoryPageTitle = "All [Jenkins]";

        WebElement buildHistorySideMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
        buildHistorySideMenu.click();

        String actualBuildHistoryPageTitle = getDriver().getTitle();
        String actualBuildHistoryPageUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualBuildHistoryPageTitle, expectedBuildHistoryPageTitle);
        Assert.assertEquals(actualBuildHistoryPageUrl, expectedBuildHistoryPageUrl);
    }

    @Test
    public void testAddDescriptionForBuild1(){

        TestUtils.createPipeline(this, NAME_PIPELINE, true);

        getDriver().findElement(LOGO_JENKINS).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUILD_SCHEDULE)).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(BUILD_HISTORY)).click();

        new Actions(getDriver()).moveToElement(getWait5().until(ExpectedConditions.visibilityOfElementLocated(SERIAL_NUMBER_OF_BUILD)))
                .perform();
        getDriver().findElement(DROP_DOWN_SERIAL_NUMBER).sendKeys(Keys.RETURN);

        getWait10().until(ExpectedConditions.elementToBeClickable(EDIT_BUILD_INFORMATION)).sendKeys(Keys.RETURN);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_FIELD)).sendKeys(BUILD_DESCRIPTION);

        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXT).getText(), BUILD_DESCRIPTION);
    }

    @Test
    public void testAddDescriptionToBuild() {
        String buildDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME_PIPELINE)
                .selectPipelineAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickPipelineProject(NAME_PIPELINE)
                .clickEditDescription()
                .enterNewDescription(BUILD_DESCRIPTION)
                .clickSaveButton()
                .getDescription().getText();

        Assert.assertEquals(buildDescription, BUILD_DESCRIPTION);
    }

    @Test
    public void testConsoleFreestyleBuildLocation() {
        String consoleOutputText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectFreestyleProject()
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .selectBuildNow()
                .clickDashboard()
                .clickBuildsHistoryButton()
                .clickProjectBuildConsole(freestyleProjectName)
                .getConsoleOutputText();

        String actualLocation = new ConsoleOutputPage(getDriver())
                .getParameterFromConsoleOutput(consoleOutputText, "workspace");

        Assert.assertEquals(actualLocation, "Building in workspace /var/jenkins_home/workspace/" + freestyleProjectName);
    }

    @Test
    public void testConsoleOutputFreestyleBuildStartedByUser() {
        final String currentUser = new MainPage(getDriver()).getCurrentUserName();

        final String userConsoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(freestyleProjectName)
                .selectFreestyleProject()
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .selectBuildNow()
                .clickDashboard()
                .clickBuildsHistoryButton()
                .clickProjectBuildConsole(freestyleProjectName)
                .getStartedByUser();

        Assert.assertEquals(currentUser, userConsoleOutput);
    }
}

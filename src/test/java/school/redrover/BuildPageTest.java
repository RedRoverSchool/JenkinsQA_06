package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.BuildHistoryPage;
import school.redrover.model.ConsoleOutputPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;


public class BuildPageTest extends BaseTest {
    private static final String NAME_PIPELINE = "Pipeline2023";
    private static final String BUILD_DESCRIPTION = "For QA";
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

        getDriver().findElement(By.id("jenkins-name-icon")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='job/" + NAME_PIPELINE + "/build?delay=0sec']"))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/builds']"))).click();

        new Actions(getDriver()).moveToElement(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/job/" + NAME_PIPELINE + "/1/']"))))
                .perform();
        getDriver().findElement(By.xpath("//a[contains(@class, 'badge model-link inside')]/button")).sendKeys(Keys.RETURN);

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + NAME_PIPELINE + "/1/configure']"))).sendKeys(Keys.RETURN);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(BUILD_DESCRIPTION);

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), BUILD_DESCRIPTION);
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

    @Ignore
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
        final String currentUser = new MainPage(getDriver()).getHeader().getCurrentUserName();

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

    @Test
    public void testConsoleOutputFreestyleBuildStatus(){
        final String consoleOutput = new MainPage(getDriver())
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

        String actualStatus = new ConsoleOutputPage(getDriver())
                .getParameterFromConsoleOutput(consoleOutput, "Finished");

        Assert.assertEquals(actualStatus, "Finished: SUCCESS");
    }

    @Test
    public void verifyStatusBroken(){

        final String namePipeline = "NewBuilds";
        final String textToDescriptionField = "What's up";
        final String textToPipelineScript = "Test";
        final String expectedStatusMessageText = "broken since this build";

        String actualStatusMessageText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(namePipeline)
                .selectPipelineAndOk()
                .addDescription(textToDescriptionField)
                .scrollToBuildTriggers()
                .clickBuildTriggerCheckBox()
                .scrollToPipelineSection()
                .sendAreContentInputString(textToPipelineScript)
                .clickSaveButton()
                .clickDashboard()
                .clickPlayBuildForATestButton("NewBuilds")
                .clickBuildsHistoryButton()
                .getStatusMessageText();
        Assert.assertEquals(actualStatusMessageText,expectedStatusMessageText);
    }

    @Test
    public void testPresenceProjectNameOnBuildHistoryTimeline() {
        final String itemName = "TestProject";
        String projectNameOnBuildHistoryTimeline = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(itemName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickPlayBuildForATestButton(itemName)
                .clickBuildsHistoryButton()
                .clickBuildNameOnTimeline(itemName + " #1")
                .getBubbleTitleOnTimeline();
        Assert.assertEquals(projectNameOnBuildHistoryTimeline, itemName + " #1");
    }
}

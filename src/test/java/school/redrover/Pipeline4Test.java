package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline4Test extends BaseTest {
    private static final String NAME = "Pipeline With Schedule";
    private static final String PIPELINE_DESCRIPTION = "New pipeline with every 1 min build schedule";
    private static final String ONE_MINUTE_TIMER = "* * * * *";

    private static final By CREATE_A_JOB_ARROW = By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']");
    private static final By PROJECT_NAME = By.id("name");
    private static final By PIPELINE_PROJECT= By.xpath("//label/span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By PROJECT_DESCRIPTION = By.name("description");
    private static final By BUILD_TRIGGERS = By.xpath("//div[@class='jenkins-section__title'][contains(text(),'Build Triggers')]");
    private static final By BUILD_PERIODICALLY = By.id("cb11");
    private static final By SCHEDULE = By.name("_.spec");
    private static final By PIPELINE = By.xpath("//div[@class='jenkins-section__title'][contains(text(),'Pipeline')]");
    private static final By SCRIPT_SAMPLES = By.xpath("//div[@class = 'samples']/select");
    private static final By SCRIPT_HELLO_WORLD = By.xpath("//option[text()='Hello World']");
    private static final By APPLY_BUTTON = By.name("Apply");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By NOTIFICATION_TEXT = By.xpath("//div[id='notification-bar']//span");
    private static final By DASHBOARD = By.id("jenkins-home-link");

    private static void scrollByVisibleElement(By by, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
    }

    private static void clickByJS(By by, WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(by));
    }

    private static WebElement waitForVisibleElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private static WebElement waitForClickableElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Test
    public void testCreateAPeriodicalBuildProject() {
        getDriver().findElement(CREATE_A_JOB_ARROW).click();
        waitForClickableElement(PROJECT_NAME, getWait2()).sendKeys(NAME);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();

        waitForClickableElement(PROJECT_DESCRIPTION, getWait2()).sendKeys(PIPELINE_DESCRIPTION);

        scrollByVisibleElement(BUILD_TRIGGERS, getDriver());
        getDriver().findElement(BUILD_PERIODICALLY).click();
        waitForVisibleElement(SCHEDULE, getWait2()).sendKeys(ONE_MINUTE_TIMER);

        scrollByVisibleElement(PIPELINE, getDriver());
        waitForVisibleElement(SCRIPT_SAMPLES, getWait2()).click();
        waitForClickableElement(SCRIPT_HELLO_WORLD, getWait2()).click();
        waitForClickableElement(APPLY_BUTTON, getWait2()).click();

        final String notification = waitForVisibleElement(NOTIFICATION_TEXT, getWait2()).getText();

        Assert.assertEquals(notification, "Saved");

        waitForClickableElement(SAVE_BUTTON, getWait2()).click();

        getDriver().findElement(DASHBOARD).click();







    }

    // /*
    //         * Step 3: Get data after Pipeline was created
    //         */
    //        String createdPipelineName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1"))).getText();
    //        String createdPipelineDescription = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description>div"))).getText();
    //        String buildHistoryStatus = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("no-builds"))).getText();
    //        By alertElement = By.xpath("//div[@class='alert alert-info']");
    //        String alertInfo = getWait2().until(ExpectedConditions.visibilityOfElementLocated(alertElement)).getText();
    //
    //        /*
    //         * Assert(make sure) that:
    //         * 1. The Pipeline was created (name && description as expected)
    //         * Or we can't continue
    //         */
    //        Assert.assertEquals(createdPipelineName, "Pipeline " + DATA_PIPE_LINE_NAME);
    //        Assert.assertEquals(createdPipelineDescription, DATA_PIPELINE_DESCRIPTION);
    //
    //        /*
    //         * Assert(make sure) that:
    //         * 1. No builds available at the time
    //         * 2. This Pipeline has not yet run
    //         * We have to be sure that we do not have builds yet to confirm the changes in the future
    //         */
    //        Assert.assertEquals(buildHistoryStatus, "No builds");
    //        Assert.assertEquals(alertInfo, "No data available. This Pipeline has not yet run.");
    //
    //        /*
    //         * Step 4: Wait 2 minutes
    //         * This is a necessary sleep to check if scheduled builds are building every 1 min.
    //         * Please do not remove
    //         */
    //        TimeUnit.MINUTES.sleep(2);
    //
    //        /*
    //         * Step 5: In 2 minutes get a new data:
    //         * 1. Pipeline name and description to be sure we are still working with the same pipeline
    //         * 2. stageHeaderName (Stage name from the Script)
    //         * 3. averageRunTimeInfo in ms (shows the average time per build)
    //         * 4. List of builds created for 2 minutes to get amount of builds in History and builds in Stage
    //         */
    //        String actualPipelineName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1"))).getText();
    //        String actualPipelineDescription = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description>div"))).getText();
    //        String stageHeaderName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='jobsTable']//tr/th[@class = 'stage-header-name-0']"))).getText();
    //        String averageRunTimeInfo = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='stage-start']/div[@class='cell-color'][contains(text(),  'Average stage times:')]")
    //        )).getText().trim();
    //        BaseUtils.logf(averageRunTimeInfo);
    //        int averageRunTime = averageRunTime(averageRunTimeInfo);
    //
    //        List<WebElement> builds = getDriver().findElements(By.xpath("//table[@class='jobsTable']//tr[@class ='job SUCCESS']"));
    //        int buildsOnStageView = builds.size();
    //
    //        builds = getDriver().findElements(By.xpath("//table[@class='pane jenkins-pane stripped']//tr[@class ='build-row multi-line overflow-checked']"));
    //        int buildsOnHistoryView = builds.size();
    //
    //        /*
    //         * Assert that:
    //         * 1. We are working with the Pipeline which we created earlier (name && description are the same as after creation)
    //         * 2. "No builds" message is not displayed
    //         * 3. "No data available. This Pipeline has not yet run." is not displayed
    //         * 4. Two new builds are shown on the Stage View
    //         * 5. Two new builds are shown on the History View
    //         * 6. stageHeaderName = "Hello" (Stage name from the Script)
    //         * 7. The difference between builds' run time == 1 min
    //         */
    //        Assert.assertEquals(createdPipelineName, actualPipelineName);
    //        Assert.assertEquals(createdPipelineDescription, actualPipelineDescription);
    //        //2. "No builds" message is not displayed
    //        //3. "No data available. This Pipeline has not yet run." is not displayed
    //        Assert.assertEquals(buildsOnStageView, 2);
    //        Assert.assertEquals(buildsOnHistoryView, 2);
    //        Assert.assertEquals(stageHeaderName, "Hello");
    //        Assert.assertTrue(averageRunTime <= 1000);
    //        //7. The difference between builds' run time == 1 min
    //
    //        /*
    //         * Step 6: Go to each build and get a log ---- currently implemented for the last build only
    //         */
    //        WebElement lastBuild = getDriver().findElement(By.xpath("//table[@class='pane jenkins-pane stripped']//td//a[@class='model-link inside build-link']"));
    //        lastBuild.click();
    //        Thread.sleep(500);
    //
    //        WebElement consoleOutputMenu = getDriver().findElement(By.linkText("Console Output"));
    //        clickByJavaScript(consoleOutputMenu, getDriver());
    //        Thread.sleep(1000);
    //
    //        String consoleOutputText = getDriver().findElement(By.className("console-output")).getText();
    //        String consoleNode7Text = getDriver().findElement(By.xpath("//pre[@class='console-output']/span[@class='pipeline-node-7']")).getText();
    //
    //        /*
    //         * Assert that in each build we ran the Script from Configurations:
    //         * 1. Started by timer
    //         * 2. Has the ".jenkins/workspace/Pipeline With Schedule" api
    //         * 3. Has the "(Hello)" Stage name as described in Script
    //         * 4. Ran "Hello World" as described in Script
    //         * */
    //        Assert.assertTrue(consoleOutputText.contains("Started by timer"));
    //        Assert.assertTrue(consoleOutputText.contains(".jenkins/workspace/Pipeline With Schedule"));
    //        Assert.assertTrue(consoleOutputText.contains("(Hello)"));
    //        Assert.assertEquals(consoleNode7Text, "Hello World");

}

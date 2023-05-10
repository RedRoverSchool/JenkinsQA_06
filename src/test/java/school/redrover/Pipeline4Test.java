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

import java.time.Duration;
import java.util.List;

public class Pipeline4Test extends BaseTest {
    private static final String NAME = "Pipeline With Schedule";
    private static final String PIPELINE_DESCRIPTION = "New pipeline with every 1 min build schedule";
    private static final String ONE_MINUTE_TIMER = "* * * * *";

    private static final By CREATE_A_JOB_ARROW = By.xpath("//a[@href='newJob']/span[@class='trailing-icon']");
    private static final By PROJECT_NAME = By.id("name");
    private static final By PIPELINE_PROJECT = By.xpath("//label/span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By PROJECT_DESCRIPTION = By.name("description");
    private static final By BUILD_TRIGGERS = By.xpath("//div[@class='jenkins-section__title'][contains(text(),'Build Triggers')]");
    private static final By BUILD_PERIODICALLY = By.xpath("//label[text()='Build periodically']");
    private static final By BUILD_PERIODICALLY_CHECKBOX = By.xpath("//input[@name='hudson-triggers-TimerTrigger']");
    private static final By SCHEDULE = By.name("_.spec");
    private static final By PIPELINE = By.xpath("//div[@class='jenkins-section__title'][contains(text(),'Pipeline')]");
    private static final By SCRIPT_SAMPLES = By.xpath("//div[@class='samples']/select");
    private static final By SCRIPT_BOX = By.xpath("//div[@class = 'ace_line']");
    private static final By SCRIPT_HELLO_WORLD = By.xpath("//option[text()='Hello World']");
    private static final By SCRIPT_TEXT = By.xpath("//span[@class='ace_identifier'] | //span[@class='ace_string'] ");
    private static final By SCRIPT_OPTION = By.xpath("//select[@class='jenkins-select__input dropdownList']/option[@selected='true']");
    private static final By APPLY_BUTTON = By.name("Apply");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By NOTIFICATION_TEXT = By.xpath("//div[@id='notification-bar']/span");
    private static final By DASHBOARD = By.id("jenkins-home-link");
    private static final By PROJECT_TABLE = By.id("projectstatus");
    private static final By CREATED_PROJECTS = By.xpath("//table[@id='projectstatus']/tbody/tr");

    private static void scrollByVisibleElement(By by, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
    }

    private static WebElement waitForVisibleElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private static WebElement waitForClickableElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private static String getText(By by, WebDriver driver) {
        List<WebElement> elements = driver.findElements(by);
        StringBuilder text = new StringBuilder();
        for (WebElement element : elements) {
            text.append(element.getText()).append(" ");
        }

        return text.toString();
    }

    private static void goToDashboard(WebDriver driver) {
        driver.findElement(DASHBOARD).click();
        waitForPageToBeLoaded(driver);
    }

    private static void goToProjectConfiguration(String projectName, WebDriver driver) {
        driver.findElement(By.xpath("//a/span[text()='" + projectName + "']")).click();
        waitForPageToBeLoaded(driver);
        driver.findElement(By.xpath("//a[contains(@href, '/configure')]")).click();
        waitForPageToBeLoaded(driver);
    }

    public static void waitForPageToBeLoaded(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    private static void click(By by, WebDriver driver) {
        driver.findElement(by).click();
    }

    @Test
    public void testCreateAPeriodicalBuildProject() {
        click(CREATE_A_JOB_ARROW, getDriver());
        waitForVisibleElement(PROJECT_NAME, getWait2()).sendKeys(NAME);
        click(PIPELINE_PROJECT, getDriver());
        click(OK_BUTTON, getDriver());

        waitForVisibleElement(PROJECT_DESCRIPTION, getWait2()).sendKeys(PIPELINE_DESCRIPTION);

        scrollByVisibleElement(BUILD_TRIGGERS, getDriver());
        waitForClickableElement(BUILD_PERIODICALLY, getWait2()).click();
        waitForVisibleElement(SCHEDULE, getWait2()).sendKeys(ONE_MINUTE_TIMER);

        scrollByVisibleElement(PIPELINE, getDriver());
        waitForClickableElement(SCRIPT_SAMPLES, getWait2()).click();
        waitForClickableElement(SCRIPT_HELLO_WORLD, getWait2()).click();
        waitForClickableElement(APPLY_BUTTON, getWait2()).click();

        Assert.assertEquals(waitForVisibleElement(NOTIFICATION_TEXT, getWait2()).getText(), "Saved");

        click(SAVE_BUTTON, getDriver());

        goToDashboard(getDriver());

        Assert.assertTrue(getDriver().findElement(PROJECT_TABLE).isDisplayed());

        final List<WebElement> PROJECTS = getDriver().findElements(CREATED_PROJECTS);

        Assert.assertEquals(PROJECTS.size(), 1);
        Assert.assertEquals(PROJECTS.get(0).getAttribute("id"), "job_" + NAME);
        Assert.assertTrue(PROJECTS.get(0).getText().contains(NAME));

        goToProjectConfiguration(NAME, getDriver());
        final String ACTUAL_DESCRIPTION = getDriver().findElement(PROJECT_DESCRIPTION).getText();

        scrollByVisibleElement(BUILD_TRIGGERS, getDriver());
        final String ACTUAL_BUILD_PERIODICALLY_CHECKED = waitForVisibleElement(BUILD_PERIODICALLY_CHECKBOX, getWait2())
                .getAttribute("checked");
        final String ACTUAL_SCHEDULE = getDriver().findElement(SCHEDULE).getText();

        scrollByVisibleElement(PIPELINE, getDriver());
        final String ACTUAL_SCRIPT_OPTION = waitForVisibleElement(SCRIPT_OPTION, getWait2()).getText();
        waitForVisibleElement(SCRIPT_BOX, getWait2());
        final String ACTUAL_SCRIPT_TEXT = getText(SCRIPT_TEXT, getDriver());
        final String ACTUAL_USE_GROOVY_SANDBOX_CHECK = getDriver().findElement(By.name("_.sandbox")).getAttribute("checked");

        Assert.assertEquals(ACTUAL_DESCRIPTION, PIPELINE_DESCRIPTION);
        Assert.assertEquals(ACTUAL_BUILD_PERIODICALLY_CHECKED, "true");
        Assert.assertEquals(ACTUAL_SCHEDULE, ONE_MINUTE_TIMER);
        Assert.assertEquals(ACTUAL_SCRIPT_OPTION, "Pipeline script");
        Assert.assertEquals(ACTUAL_SCRIPT_TEXT, "pipeline agent any stages stage 'Hello' steps echo 'Hello World' ");
        Assert.assertEquals(ACTUAL_USE_GROOVY_SANDBOX_CHECK, "true");
    }

}

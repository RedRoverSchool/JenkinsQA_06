package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

    @Ignore
    public class CreateFreestyleProjectJobTest extends BaseTest {
    private static final String NAME_PROJECT = "Hello world";
    private static final String URL_GITHUB = "https://github.com/kriru/firstJava.git";
    private static final String DESCRIPTION = " java test program";
    private static final By DASHBOARD = By.xpath("//*[@id='breadcrumbs']/li[1]/a");
    private static final By NEW_ITEM = By.xpath("//*[@id='tasks']/div[1]/span/a");
    private static final By FREESTYLE_PROJECT = By.cssSelector(".icon-freestyle-project");
    private static final By ITEM_NAME_ENTER = By.name("name");
    private static final By OK_BUTTON = By.xpath("//*[@id='ok-button']");
    private static final By DESCRIPTION_ENTER = By.xpath("//*[@name='description']");
    private static final By URL_ENTER = By.xpath("//*[@checkdependson='credentialsId']");
    private static final By EXECUTE_WIN_COMMAND = By.xpath("//*[@name='description']");
    private static final By APPLY_BUTTON = By.name("Apply");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By BUILD_NOW_LINK = By.xpath("//*[@id='tasks']/div[4]/span/a");
    private static final By BUILD_NAME = By.xpath("//*[@class='model-link inside build-link display-name']");
    private static final By CONSOLE_OUT_LINK = By.xpath("//*[@class='icon-terminal icon-xlg']");
    private static final By GIT_RADIO_BUTTON = By.xpath("//*[@for='radio-block-1']");
    private static final By BUILD_STEPS_BUTTON = By.xpath("//*[@id='yui-gen9-button']");
    private static final By EXECUTE_SHELL_DROPDOWN = By.xpath("//*[@id='yui-gen24']");
    private static final By CONSOLE_OUT = By.xpath("//*[@class='console-output']");

    public void scroll(int deltaY) {
        new Actions(getDriver())
                .scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(), 0, deltaY)
                .perform();
    }

    public void clickPerform(By by) {
        new Actions(getDriver())
                .click(getDriver().findElement(by))
                .perform();
    }

    public void clickPerform(WebElement webElement) {
        new Actions(getDriver())
                .click(webElement)
                .perform();
    }

    public void CreateFreestyleProjectJob(String nameProject) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(ITEM_NAME_ENTER).sendKeys(NAME_PROJECT);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        clickPerform(OK_BUTTON);
        getDriver().findElement(DESCRIPTION_ENTER).sendKeys(NAME_PROJECT.concat(DESCRIPTION));
        scroll(600);
        clickPerform(getWait2().until(ExpectedConditions.elementToBeClickable(GIT_RADIO_BUTTON)));
        getWait2().until(ExpectedConditions.elementToBeClickable(URL_ENTER)).sendKeys(URL_GITHUB);
        scroll(2000);
        getWait2().until(ExpectedConditions.elementToBeClickable(BUILD_STEPS_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(EXECUTE_SHELL_DROPDOWN)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(EXECUTE_WIN_COMMAND)).sendKeys("javac ".concat(NAME_PROJECT.concat(".java\njava ".concat(NAME_PROJECT))));
        getWait2().until(ExpectedConditions.elementToBeClickable(APPLY_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
        clickPerform(getWait2().until(ExpectedConditions.elementToBeClickable(BUILD_NOW_LINK)));
        getWait5().until(ExpectedConditions.elementToBeClickable(BUILD_NAME)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(CONSOLE_OUT_LINK)).click();
    }

    @Test
    public void testCreateFreestyleProjectJob() {
        CreateFreestyleProjectJob("Hello world");

        Assert.assertTrue(getWait5().until(ExpectedConditions.elementToBeClickable(CONSOLE_OUT)).getText().contains("Finished: SUCCESS"));
    }
}
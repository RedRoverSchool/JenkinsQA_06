package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

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
    private static final By GIT_RADIO_BUTTON = By.xpath("//*[@id='radio-block-1']");
    private static final By BUILD_STEPS_BUTTON = By.xpath("//*[@id='yui-gen9-button']");
    private static final By EXECUTE_SHELL_DROPDOWN = By.xpath("//*[@id='yui-gen24']");
    private static final By CONSOLE_OUT = By.xpath("//*[@class='console-output']");

    public void scroll(int deltaY) {
        new Actions(getDriver())
                .scrollFromOrigin( WheelInput.ScrollOrigin.fromViewport(),0, deltaY)
                .perform();
    }
    public void clickPerform(By by) {
        new Actions(getDriver())
                .click(getDriver().findElement(by))
                .perform();
    }
    public void CreateFreestyleProjectJob(String nameProject) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(ITEM_NAME_ENTER).sendKeys(nameProject);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(DESCRIPTION_ENTER).sendKeys(nameProject.concat(DESCRIPTION));
        scroll(600);
        clickPerform(GIT_RADIO_BUTTON);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().findElement(URL_ENTER).sendKeys(URL_GITHUB);
        scroll(2000);
        clickPerform(BUILD_STEPS_BUTTON);
        clickPerform(EXECUTE_SHELL_DROPDOWN);
        getDriver().findElement(EXECUTE_WIN_COMMAND).sendKeys("javac ".concat( nameProject.concat( ".java\njava ".concat( nameProject))));
        getDriver().findElement(APPLY_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        clickPerform(BUILD_NOW_LINK);
        getDriver().findElement(BUILD_NAME).click();
        getDriver().findElement(CONSOLE_OUT_LINK).click();
    }

    @Test
    public void testCreateFreestyleProject() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(ITEM_NAME_ENTER).sendKeys(NAME_PROJECT);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(DESCRIPTION_ENTER).sendKeys(NAME_PROJECT.concat(DESCRIPTION));
        scroll(600);
        clickPerform(GIT_RADIO_BUTTON);
        getDriver().findElement(URL_ENTER).sendKeys(URL_GITHUB);
        scroll(2000);
        clickPerform(BUILD_STEPS_BUTTON);
        clickPerform(EXECUTE_SHELL_DROPDOWN);
        getDriver().findElement(EXECUTE_WIN_COMMAND).sendKeys("javac ".concat( NAME_PROJECT.concat( ".java\njava ".concat(NAME_PROJECT))));
        getDriver().findElement(APPLY_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        clickPerform(BUILD_NOW_LINK);
        getWait5().until(ExpectedConditions.elementToBeClickable(BUILD_NAME)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(CONSOLE_OUT_LINK)).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.elementToBeClickable(CONSOLE_OUT)).getText().contains("Finished: SUCCESS"));
    }
}

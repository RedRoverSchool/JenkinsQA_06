package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import javax.swing.*;

public class BuildHistoryPageTest extends BaseTest {
    private static final String NAME_PIPELINE = "Pipeline2023";
    private static final String BUILD_DESCRIPTION = "For QA";
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By LOGO_JENKINS = By.id("jenkins-name-icon");
    private static final By INPUT_NAME_FIELD = By.xpath("//input[@id='name']");
    private static final By PIPELINE_PROJECT = By.xpath("//label/span[text()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By H1_NAME_PIPELINE = By.xpath("//h1");
    private static final By BUILD_HISTORY = By.xpath("//span[text()='Build History']");
    private static final By BUILD_SCHEDULE = By.xpath("//span[@class='build-button-column-icon-reference-holder']");
    private static final By SERIAL_NUMBER_OF_BUILD = By.xpath(
            "//a[@class='jenkins-table__link jenkins-table__badge model-link inside']");
    private static final By DROP_DOWN_SERIAL_NUMBER = By.xpath(
            "//a[@class='jenkins-table__link jenkins-table__badge model-link inside']/button[@class='jenkins-menu-dropdown-chevron']");
    private static final By EDIT_BUILD_INFORMATION = By.xpath("//span[text()='Edit Build Information']");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@name='description']");
    private static final By DESCRIPTION_TEXT = By.id("description");

    private void createNewPipeline(String name){
        getDriver().findElement(NEW_ITEM).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NAME_FIELD));
        getDriver().findElement(INPUT_NAME_FIELD).sendKeys(NAME_PIPELINE);

        getDriver().findElement(PIPELINE_PROJECT).click();

        getDriver().findElement(OK_BUTTON).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(H1_NAME_PIPELINE));
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

    @Test
    public void testAddDescriptionForBuild(){
        createNewPipeline(NAME_PIPELINE);

        getDriver().findElement(LOGO_JENKINS).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(BUILD_SCHEDULE)).click();

        getDriver().findElement(BUILD_HISTORY).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(SERIAL_NUMBER_OF_BUILD));
        new Actions(getDriver()).moveToElement(getDriver().findElement(SERIAL_NUMBER_OF_BUILD))
                .moveToElement(getDriver().findElement(DROP_DOWN_SERIAL_NUMBER))
                .click()
                .moveToElement(getDriver().findElement(EDIT_BUILD_INFORMATION))
                .click()
                .perform();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_FIELD)).sendKeys(BUILD_DESCRIPTION);

        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXT).getText(), BUILD_DESCRIPTION);
    }
}

package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultibranchPipeline2Test extends BaseTest {
    private static final String RANDOM_NAME_MULTIBRANCH_PIPELINE = RandomStringUtils.randomAlphanumeric(5);
    private static final String DESCRIPTION = "Description";
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By NEW_ITEM_BUTTON = By.linkText("New Item");
    private static final By FIELD_FOR_NAME = By.id("name");
    private static final By MULTI_BRANCH_BUTTON = By.cssSelector("div img.icon-pipeline-multibranch-project");

    private void createMultibranchPipeline(){
        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getDriver().findElement(FIELD_FOR_NAME).sendKeys(RANDOM_NAME_MULTIBRANCH_PIPELINE);
        scrollByVisibleElement(MULTI_BRANCH_BUTTON, getDriver());
        WebElement multibranchButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(MULTI_BRANCH_BUTTON));
        multibranchButton.click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private static void scrollByVisibleElement(By by, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
    }

    @Ignore
    @Test
    public void testCreateMultibranchPipelineWithDescription(){
        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getDriver().findElement(FIELD_FOR_NAME).sendKeys(RANDOM_NAME_MULTIBRANCH_PIPELINE);
        WebElement multibranchButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")));
        multibranchButton.click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.name("_.description")).sendKeys(DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("div#main-panel h1")).getText(),RANDOM_NAME_MULTIBRANCH_PIPELINE);
        Assert.assertEquals(getDriver().findElement(By.cssSelector("div#view-message")).getText(),DESCRIPTION);
    }

    @Test
    public void testRenameMultibranchPipeline(){
        createMultibranchPipeline();
        getDriver().findElement(By.xpath("//div[@id='side-panel']/div[@id='tasks']/div[8]/span[1]/a")).click();
        getDriver().findElement(By.cssSelector("input.jenkins-input.validated")).sendKeys(RANDOM_NAME_MULTIBRANCH_PIPELINE);
        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),RANDOM_NAME_MULTIBRANCH_PIPELINE + RANDOM_NAME_MULTIBRANCH_PIPELINE);
    }

    @Test
    public void testDeleteMultibranchPipeline(){
        createMultibranchPipeline();

        getDriver().findElement(By.cssSelector("a span [class='icon-edit-delete icon-md']")).click();
        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary")).click();
        WebElement welcomeText = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Welcome to Jenkins!']")));
        String getTextWelcome =welcomeText.getText();

        Assert.assertEquals(getTextWelcome,"Welcome to Jenkins!");
    }
}

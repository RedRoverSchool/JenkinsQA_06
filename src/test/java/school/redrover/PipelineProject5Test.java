package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.PipelinePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject5Test extends BaseTest {
    public static String name = "My New Pipeline Project";
    public static String rename = "Pipeline Project";
    public static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    public static final By INPUT_NAME = By.id("name");
    public static final By PIPELINE_PROJECT_TYPE = By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
    public static final By OK_BUTTON = By.id("ok-button");
    public static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
    public static final By JENKINS_LOGO = By.id("jenkins-name-icon");
    public static final By PROJECT_IN_DASHBOARD_TABLE = By.xpath("//span[normalize-space()='" + name + "']");

    @Test
    public void testCreatePipelineProject() {
        getDriver().findElement(NEW_ITEM).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(INPUT_NAME)).sendKeys(name);
        getDriver().findElement(PIPELINE_PROJECT_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON)).click();
        getDriver().findElement(JENKINS_LOGO).click();

        WebElement projectName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(PROJECT_IN_DASHBOARD_TABLE));

        Assert.assertEquals(projectName.getText(), name);

        projectName.click();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//h1[normalize-space()='Pipeline " + name + "']"))
                        .getText(), "Pipeline " + name);
    }

    @Test(dependsOnMethods = "testCreatePipelineProject")
    public void testDeletePipelineProject() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(PROJECT_IN_DASHBOARD_TABLE)).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Delete Pipeline')]"))).click();
        getDriver().switchTo().alert().accept();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//h1[normalize-space()='Welcome to Jenkins!']"))
                .getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testDeletePipelineProjectDropDown() {
        TestUtils.createPipeline(this, name, true);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(PROJECT_IN_DASHBOARD_TABLE));

        getWait2().until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@class ='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']")))
                .sendKeys(Keys.RETURN);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Delete Pipeline"))).click();
        getWait2().until(ExpectedConditions.alertIsPresent()).accept();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//h1[normalize-space()='Welcome to Jenkins!']"))
                .getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testRenamePipelineFromProjectPage() {
        TestUtils.createPipeline(this, name, true);

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(PROJECT_IN_DASHBOARD_TABLE)).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//span[1]//a[1]"))).click();

        WebElement inputRename = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='newName']")));
        inputRename.clear();
        inputRename.sendKeys(rename + Keys.TAB);

        Assert.assertEquals(getDriver().findElements(By.xpath("//div[@class='validation-error-area validation-error-area--visible']")).isEmpty(), true);
        Assert.assertEquals(getDriver().findElements(By.xpath("//div[@class='validation-error-area validation-error-area--visible']")).isEmpty(), true);

        getDriver().findElement(By.xpath("//button[normalize-space()='Rename']")).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[normalize-space()='Pipeline " + rename + "']")))
                .getText(), "Pipeline " + rename);

        getDriver().findElement(JENKINS_LOGO).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + rename + "']"))).getText(), rename);
    }

    @Test
    public void testRenamePipelineDropDownMenu() {
        TestUtils.createPipeline(this, name, true);

        getWait2().until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[@class ='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']")))
                .sendKeys(Keys.RETURN);

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Rename"))).click();

        WebElement inputRename = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='newName']")));
        inputRename.clear();
        inputRename.sendKeys(rename + Keys.TAB);

        Assert.assertEquals(getDriver().findElements(By.xpath("//div[@class='validation-error-area validation-error-area--visible']")).isEmpty(), true);

        getDriver().findElement(By.xpath("//button[normalize-space()='Rename']")).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[normalize-space()='Pipeline " + rename + "']")))
                .getText(), "Pipeline " + rename);

        getDriver().findElement(JENKINS_LOGO).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='" + rename + "']"))).getText(), rename);
    }

    @Test
    public void testDisablePipeline() {
        TestUtils.createPipeline(this, name, true);
        String projectName = name.replaceAll(" ", "%20");
        PipelinePage mainPage = new MainPage(getDriver())
                .clickPipelineProject(projectName)
                .clickDisableProject();
        Assert.assertEquals(getDriver().findElement(By.id("enable-project")).isDisplayed(), true);

        Assert.assertEquals(mainPage.clickDashboard()
                .getJobBuildStatusIcon(name), "Disabled");
    }

    @Test
    public void testPipelineNameUnsafeChar() {
        String[] testStrings = {"*", "&", "^", "%", "$", "#", "@", "!"};

        getDriver().findElement(NEW_ITEM).click();
        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']"))));
        getWait2().until(ExpectedConditions.elementToBeClickable(PIPELINE_PROJECT_TYPE)).click();

        for (int i = 0; i < testStrings.length; i++) {
            String letter = testStrings[i];
            getDriver().findElement(INPUT_NAME).sendKeys(letter);

            WebElement message = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
            getWait2().until(ExpectedConditions.visibilityOf(message));

            Assert.assertEquals(message.getText(), "»" + " ‘" + letter + "’ " + "is an unsafe character");
            getWait2().until(ExpectedConditions.visibilityOf(message));
            getDriver().findElement(INPUT_NAME).clear();
        }
    }

    @Test
    public void testPipelineNameAllowedChar() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(PIPELINE_PROJECT_TYPE).click();
        getDriver().findElement(INPUT_NAME).sendKeys("_-+=”{},");
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(JENKINS_LOGO).click();
        WebElement projectNameDashboard = getDriver().findElement(By.xpath("//td/a/span"));

        Assert.assertEquals(projectNameDashboard.getText(), "_-+=”{},");
    }
}
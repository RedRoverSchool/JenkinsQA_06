package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

public class MultiConfigurationTest extends BaseTest {
    private static final String MULTI_CONFIGURATION_NAME = RandomStringUtils.randomAlphanumeric(5);
    private static final String MULTI_CONFIGURATION_NEW_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");

    @Test
    public void testCreateMultiConfiguration() {
        MainPage mainPage = new MainPage(getDriver());
        final String projectName = mainPage.newItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProject()
                .saveConfigurePageAndGoToProjectPage()
                .navigateToHomePageUsingJenkinsIcon()
                .getProjectName().getText();

        Assert.assertEquals(projectName, MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testCheckExceptionToMultiConfigurationPage() {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement multiconfigButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        multiconfigButton.click();
        String exceptionText = getDriver().findElement(By.xpath("//div[text() ='» This field cannot be empty, please enter a valid name']")).getText();

        Assert.assertEquals(exceptionText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testAddedDescriptionToMultiConfiguration() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement projectButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        projectButton.click();
        WebElement okButton = getWait10().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();
        getDriver().findElement(By.name("description")).sendKeys("Description");
        WebElement saveButton = getWait10().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        saveButton.click();
        WebElement descriptionText = getDriver().findElement(By.cssSelector("div#description"));

        Assert.assertEquals(descriptionText.getText().trim().substring(0, 11), "Description");
    }


    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testRenameMultiConfigurationProjectFromDashboard() {

        WebElement newName = new MainPage(getDriver())
                .navigateToProjectPage()
                .clickRename()
                .enterNewName(MULTI_CONFIGURATION_NEW_NAME)
                .submitNewName()
                .getNameProject();

        Assert.assertEquals(newName.getText(), ("Project " + MULTI_CONFIGURATION_NEW_NAME));
    }

    @Test
    public void testDisabledMultiConfigurationProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement projectButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        projectButton.click();
        WebElement okButton = getWait10().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();
        getDriver().findElement(By.cssSelector("label.jenkins-toggle-switch__label ")).click();
        WebElement saveButton = getWait10().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("form#enable-project")).getText().trim().substring(0, 34), "This project is currently disabled");
    }

    @Test
    public void testProjectDisabled() {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='name']"))).sendKeys("Project001");
        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.jenkins-toggle-switch__label"))).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertTrue(getWait10().until(ExpectedConditions.textToBePresentInElement(
                getDriver().findElement(By.xpath("//form[@id='enable-project']")), "This project is currently disabled")));
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectFromDashboard")
    public void testDeleteProject() {
        WebElement projectName = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + MULTI_CONFIGURATION_NEW_NAME + "/']")));

        Actions action = new Actions(getDriver());
        action.moveToElement(projectName).perform();
        projectName.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@data-message, 'Delete')]"))).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

        Assert.assertEquals(getDriver().getTitle(), "Dashboard [Jenkins]");

        List<WebElement> projects = getDriver().findElements(By.xpath("//a[@href='job/" + MULTI_CONFIGURATION_NEW_NAME + "/']"));

        Assert.assertEquals(projects.size(), 0);
    }

    @Test
    public void testCheckGeneralParametersDisplayedAndClickable() {
        getDriver().findElement(
                By.xpath("//span[@class='task-link-wrapper ']/a[@href='/view/all/newJob']")).click();

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys("TestProject");

        getDriver().findElement(
                By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();

        getDriver().findElement(
                By.id("ok-button")).click();

        boolean checkboxesVisibleClickable = true;
        for (int i = 4; i <= 8; i++) {
            if (!getDriver().findElement(By.id("cb" + i)).isDisplayed() || !getDriver().findElement(By.id("cb" + i)).isEnabled()) {
                checkboxesVisibleClickable = false;
                break;
            }
        }

        Assert.assertTrue(checkboxesVisibleClickable);
    }
}

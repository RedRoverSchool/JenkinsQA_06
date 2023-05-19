package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

public class MultiConfigurationTest extends BaseTest {
    private static final String MULTI_CONFIGURATION_NAME = RandomStringUtils.randomAlphanumeric(5);
    private static final String MULTI_CONFIGURATION_NEW_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");

    private String getProjectNewName() {
        return getDriver().findElement(By.xpath("//h1[contains(@class, 'matrix-project-headline page-headline')]"))
                .getText();
    }

    private void createMultiConfigurationProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement multiconfigurationButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        multiconfigurationButton.click();
        WebElement okButton = getWait10().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();
        WebElement saveButton = getWait10().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        saveButton.click();
    }

    @Test
    public void testCreateMultiConfiguration() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement multiconfigurationButton = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Multi-configuration project']")));
        multiconfigurationButton.click();
        WebElement okButton = getWait10().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();
        WebElement saveButton = getWait10().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']//h1[@class='matrix-project-headline page-headline']")).getText(),"Project " + MULTI_CONFIGURATION_NAME);
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

    @Test
    public void testRenameMultiConfigurationProject() {
        createMultiConfigurationProject();

        WebElement renameButton = getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[7]/span[1]/a[1]"));
        renameButton.click();
        WebElement fieldName = getDriver().findElement(By.xpath("//input[@class='jenkins-input validated  ']"));
        fieldName.sendKeys(MULTI_CONFIGURATION_NAME);
        getDriver().findElement(By.cssSelector("div#bottom-sticker button")).click();
        WebElement renameName = getDriver().findElement(By.cssSelector("h1.matrix-project-headline.page-headline"));

        Assert.assertEquals(renameName.getText(), "Project " + MULTI_CONFIGURATION_NAME + MULTI_CONFIGURATION_NAME);
    }

    @Test
    public void testRenameMultiConfigurationProjectFromDashboard() {
        createMultiConfigurationProject();

        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(By.xpath("//a[contains(@class,'jenkins-table__link model-link inside')]"))
                .click();
        getDriver()
                .findElement(By.xpath("//a[@href = '/job/" + MULTI_CONFIGURATION_NAME + "/confirm-rename']"))
                .click();

        WebElement newName = getDriver().findElement(By.xpath("//input[@checkdependson='newName']"));

        newName.clear();
        newName.sendKeys(MULTI_CONFIGURATION_NEW_NAME);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getProjectNewName(), ("Project " + MULTI_CONFIGURATION_NEW_NAME));
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

    @Test(dependsOnMethods = "testCreateMultiConfiguration")
    public void testDeleteProject() {
        WebElement projectName = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='job/" + MULTI_CONFIGURATION_NAME + "/']")));

        Actions action = new Actions(getDriver());
        action.moveToElement(projectName).perform();
        projectName.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@data-message, 'Delete')]"))).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

        Assert.assertEquals(getDriver().getTitle(), "Dashboard [Jenkins]");

        List<WebElement> projects = getDriver().findElements(By.xpath("//a[@href='job/" + MULTI_CONFIGURATION_NAME + "/']"));

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

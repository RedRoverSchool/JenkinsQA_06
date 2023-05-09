package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationTest extends BaseTest {
    private static final String MULTI_CONFIGURATION_NAME = RandomStringUtils.randomAlphanumeric(5);
    private static final String MULTI_CONFIGURATION_NEW_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final String DESCRIPTION = "Description";
    private static final String DESCRIPTION_RANDOM = RandomStringUtils.randomAlphanumeric(5);
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final By NEW_ITEM_LINK = By.linkText("New Item");
    private static final By ITEM_NAME_FIELD = By.id("name");
    private static final By NEW_NAME_FIELD = By.xpath("//a[contains(@class,'jenkins-table__link model-link inside')]");
    private static final By RENAME_BUTTON_SIDE_MENU = By.xpath("//a[@href = '/job/" + MULTI_CONFIGURATION_NAME + "/confirm-rename']");
    private static final By PROJECT_NEW_NAME = By.xpath("//div[@id='main-panel']//h1");
    private static final By RENAME_BUTTON = By.xpath("//button[@name='Submit']");

    private String getProjectNewName() {
        getWait5();
        return getDriver().findElement(PROJECT_NEW_NAME).getText();
    }

    private void createMultiConfigurationProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement projectButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        projectButton.click();
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void clickNewItemLink() {
        getDriver().findElement(NEW_ITEM_LINK).click();
    }

    private void inputItemName() {
        getDriver().findElement(ITEM_NAME_FIELD).sendKeys(MULTI_CONFIGURATION_NAME);
    }

    private void clickMultiConfigurationProjectTab() {
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
    }

    private void clickOkButton() {
        getDriver().findElement(OK_BUTTON).click();
    }

    private void clickSaveButton() {
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void clickDashboardButton() {
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
    }

    private void clickProjectLink() {
        getDriver().findElement(NEW_NAME_FIELD).click();
    }

    private void clickRenameButton() {
        getDriver().findElement(RENAME_BUTTON_SIDE_MENU).click();
    }

    private void inputNewName() {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).sendKeys(MULTI_CONFIGURATION_NEW_NAME);
    }

    private void clickSubmitButton() {
        getDriver().findElement(RENAME_BUTTON).click();
    }

    private void getProjectName() {
        getDriver().findElement(PROJECT_NEW_NAME).getText();
    }

    private void createMultiConfigProjectWithReturnToHomePage() {
        clickNewItemLink();
        inputItemName();
        clickMultiConfigurationProjectTab();
        clickOkButton();
        clickSaveButton();
        clickDashboardButton();
    }

    @Test
    public void testCreateMultiConfig() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("test");
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='description']")).isDisplayed());
    }

    @Test
    public void testCheckExceptionToMultiConfigurationPage() {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement element = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        element.click();
        String exceptionText = getDriver().findElement(By.xpath("//div[text() ='» This field cannot be empty, please enter a valid name']")).getText();

        Assert.assertEquals(exceptionText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testAddedDescriptionToMultiConfiguration() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(MULTI_CONFIGURATION_NAME);
        WebElement projectButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        projectButton.click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.name("description")).sendKeys(DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();
        WebElement descriptionText = getDriver().findElement(By.cssSelector("div#description"));

        Assert.assertEquals(descriptionText.getText().trim().substring(0, 11), "Description");
    }

    @Test
    public void testRenameMultiConfigurationProject() {
        this.createMultiConfigurationProject();

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
        createMultiConfigProjectWithReturnToHomePage();

        clickProjectLink();
        clickRenameButton();
        inputNewName();
        clickSubmitButton();

        Assert.assertEquals(getProjectNewName(), ("Project " + MULTI_CONFIGURATION_NEW_NAME));
    }
}

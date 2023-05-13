package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class FreestyleProject1Test extends BaseTest {
    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String RENAME_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String descriptionText = "Freestyle text";
    private static final String enableButton = "Enable";
    private static final String textEnable = "Disable Project";
    private static final String changes = "Changes";
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By FREESTYLE_PROJECT = By.cssSelector(".hudson_model_FreeStyleProject");
    private static final By BUTTON_OK = By.cssSelector("#ok-button");
    private static final By SUBMIT = By.name("Submit");
    private static final By DESCRIPTION = By.name("description");
    private static final By ASSERT_ICON = By.xpath("//h1");
    private static final By DASHBOARD_ICON = By.xpath("//a[normalize-space()='Dashboard']");
    private static final By PROJECT_IN_DASHBOARD = By.xpath("//a[@class='jenkins-table__link model-link inside']//span");
    private static final By DISABLE_BUTTON = By.name("Submit");
    private static final By RENAME = By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/confirm-rename']");
    private static final By RENAME_LINE = By.xpath("//input[@name='newName']");
    private static final By CHANGES_iCON = By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/changes']");
    private static final By DELETE = By.xpath("//span[contains(text(),'Delete Project')]");
    private static final By SCREEN = By.xpath("//a[@class='jenkins-table__link model-link inside']");

    private void createFreestyleProject() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(NAME).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(BUTTON_OK).click();

        getDriver().findElement(SUBMIT).click();
    }

    @Test
    public void testCreateNewFreestyleProject() {
        createFreestyleProject();

        Assert.assertEquals("Project " + FREESTYLE_NAME, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testFindNewProjectOnDashboard() {
        createFreestyleProject();

        getDriver().findElement(DASHBOARD_ICON).click();

        Assert.assertEquals(FREESTYLE_NAME, getDriver().findElement(PROJECT_IN_DASHBOARD).getText());
    }

    @Test
    public void testFindNewProjectOnDashboardAndOpen() {
        createFreestyleProject();

        getDriver().findElement(DASHBOARD_ICON).click();
        getDriver().findElement(PROJECT_IN_DASHBOARD).click();

        Assert.assertEquals("Project " + FREESTYLE_NAME, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testCreateFreestyleProjectWithDescription() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(NAME).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(BUTTON_OK).click();

        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Project " + FREESTYLE_NAME, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Ignore
    @Test
    public void testDisableFreestyleProject() {
        createFreestyleProject();

        getDriver().findElement(DISABLE_BUTTON).click();

        Assert.assertEquals(enableButton, getDriver().findElement(SUBMIT).getText());
    }
    @Ignore
    @Test
    public void testEnableFreestyleProject() {
        createFreestyleProject();

        getDriver().findElement(DISABLE_BUTTON).click();
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals(textEnable, getDriver().findElement(DISABLE_BUTTON).getText());
    }

    @Test
    public void testRenameFreestyleProject() {
        createFreestyleProject();

        getDriver().findElement(RENAME).click();

        getDriver().findElement(RENAME_LINE).clear();
        getDriver().findElement(RENAME_LINE).sendKeys(RENAME_NAME);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Project " + RENAME_NAME, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testNavigateToChangePage() {
        createFreestyleProject();

        getDriver().findElement(CHANGES_iCON).click();
        Assert.assertEquals(changes, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testDeleteProject() {
        createFreestyleProject();

        getDriver().findElement(DELETE).click();
        getDriver().switchTo().alert().accept();

        Assert.assertFalse(getDriver().findElements(SCREEN)
                .stream().map(WebElement::getText).toList().contains(FREESTYLE_NAME));
    }
}

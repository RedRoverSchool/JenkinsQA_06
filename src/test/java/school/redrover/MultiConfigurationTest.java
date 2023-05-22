package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectConfigPage;
import school.redrover.model.ProjectPage;
import school.redrover.runner.BaseTest;


public class MultiConfigurationTest extends BaseTest {
    private static final String MULTI_CONFIGURATION_NAME = RandomStringUtils.randomAlphanumeric(5);
    private static final String MULTI_CONFIGURATION_NEW_NAME = RandomStringUtils.randomAlphabetic(5);
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");

    @Test
    public void testCreateMultiConfiguration() {
        MainPage mainPage = new MainPage(getDriver());
        final String projectName = mainPage.clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
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
        ProjectPage disabledProjPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk()
                .toggleDisable()
                .saveConfigurePageAndGoToProjectPage();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("form#enable-project")).getText().trim().substring(0, 34), "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisabledMultiConfigurationProject")
    public void testEnabledMultiConfigurationProject(){
        ProjectPage enabledProjPage = new MainPage(getDriver())
                .navigateToProjectPage()
                .enableProject();

        Assert.assertEquals(enabledProjPage.getDisableButton().getText(), "Disable Project");
    }

    @Test(dependsOnMethods = "testRenameMultiConfigurationProjectFromDashboard")
    public void testJobDropdownDelete() {
        MainPage deletedProjPage = new MainPage((getDriver()))
                .clickJobDropdownMenu(MULTI_CONFIGURATION_NEW_NAME)
                .selectJobDropdownMenuDelete();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getNoJobsMainPageHeader().getText(),"Welcome to Jenkins!");
    }

    @Test(dependsOnMethods = "testEnabledMultiConfigurationProject")
    public void testProjectPageDelete(){
        MainPage deletedProjPage = new MainPage(getDriver())
                .navigateToProjectPage()
                .deleteProject();

        Assert.assertEquals(deletedProjPage.getTitle(), "Dashboard [Jenkins]");

        Assert.assertEquals(deletedProjPage.getNoJobsMainPageHeader().getText(),"Welcome to Jenkins!");
    }

    @Test
    public void testCheckGeneralParametersDisplayedAndClickable() {
        MultiConfigurationProjectConfigPage config = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(MULTI_CONFIGURATION_NAME)
                .selectMultiConfigurationProjectAndOk();

        boolean checkboxesVisibleClickable = true;
        for (int i = 4; i <= 8; i++) {
            WebElement checkbox = config.getCheckboxById(i);
            if (!checkbox.isDisplayed() || !checkbox.isEnabled()) {
                checkboxesVisibleClickable = false;
                break;
            }
        }

        Assert.assertTrue(checkboxesVisibleClickable);
    }
}

package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class ProjectFolder2Test extends BaseTest {
    private final String FOLDER1_NAME = "My_folder";
    private final String FOLDER2_NAME = "MyFolder2";
    private final String FOLDER3_NAME = "FOLDER";
    private final String DESCRIPTION = "This is a test folder";

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME_FIELD = By.id("name");
    private static final By FOLDER_TYPE = By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By FOLDER_HEADER = By.tagName("h1");
    private static final By DASHBOARD_LINK = By.xpath("//a[@href='/'][@class='model-link']");
    private static final By FOLDER_IN_LIST = By.xpath("//a[@class='jenkins-table__link model-link inside']/span");
    private static final By JOB_HEADER = By.tagName("h1");

    private void createAFolder(String name) {
        getDriver().findElement(NEW_ITEM).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD)).sendKeys(name);

        getDriver().findElement(FOLDER_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        getWait2().until(ExpectedConditions.textToBe(FOLDER_HEADER, name));
        getDriver().findElement(DASHBOARD_LINK).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(NEW_ITEM));
    }

    @Test
    public void testCreateFolder() {
        createAFolder(FOLDER1_NAME);

        String actualJob = getDriver().findElement(FOLDER_IN_LIST).getText();

        getDriver().findElement(FOLDER_IN_LIST).click();

        Assert.assertEquals(getDriver().findElement(JOB_HEADER).getText(), FOLDER1_NAME);
        Assert.assertEquals(actualJob, FOLDER1_NAME);
    }

    @Ignore
    @Test
    public void testTwoFoldersCreation() {
        List<String> expectedFoldersList = Arrays.asList(FOLDER1_NAME, FOLDER2_NAME);

        createAFolder(FOLDER1_NAME);
        createAFolder(FOLDER2_NAME);

        List<WebElement> actualFoldersList = getDriver().findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));

        for (int i = 0; i < actualFoldersList.size(); i++) {

            Assert.assertEquals(actualFoldersList.get(actualFoldersList.size() - 1 - i).getText(), expectedFoldersList.get(i));
        }
    }

    @DataProvider(name = "create-folder")
    public Object[][] provideFoldersNames() {
        return new Object[][]
                {{FOLDER1_NAME}, {FOLDER2_NAME}, {FOLDER3_NAME}};
    }

    @Test(dataProvider = "create-folder")
    public void testFoldersCreationWithProvider(String provideNames) {
        createAFolder(provideNames);
        getDriver().findElement(DASHBOARD_LINK).click();

        getWait10().until(ExpectedConditions.presenceOfElementLocated(NEW_ITEM));

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span")).getText(), provideNames);
    }

    @Test
    public void testConfigureFolderWithDescription() {
        createAFolder(FOLDER1_NAME);

        getDriver().findElement(
                By.xpath("//a[@class='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']")).sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Configure"))).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("_.description"))).sendKeys(DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();

        String actualDescriptionAfterAddingIt = getWait2().until(ExpectedConditions.presenceOfElementLocated((By.id("view-message")))).getText();

        getDriver().findElement(DASHBOARD_LINK).click();

        getDriver().findElement(FOLDER_IN_LIST).click();
        String actualDescriptionAfterOpeningFolder = getDriver().findElement(By.id("view-message")).getText();

        Assert.assertTrue(actualDescriptionAfterAddingIt.contains(DESCRIPTION));
        Assert.assertEquals(actualDescriptionAfterOpeningFolder, actualDescriptionAfterAddingIt);
    }

    @Test
    public void testCreateFolderFromExistingFolder() {
        final String COPY_FOLDER = "Copy_folder";

        createAFolder(FOLDER1_NAME);

        getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']"))
                .sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Configure"))).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("_.description"))).sendKeys(DESCRIPTION);
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD_LINK).click();

        getDriver().findElement(NEW_ITEM).click();

        getWait2().until(ExpectedConditions.presenceOfElementLocated((NAME_FIELD))).sendKeys(COPY_FOLDER);
        getDriver().findElement(FOLDER_TYPE).click();
        getDriver().findElement(By.id("from")).sendKeys(FOLDER1_NAME);
        getDriver().findElement(OK_BUTTON).click();

        String copiedFolderDescription = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("_.description"))).getText();

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(By.id("view-message"));

        Assert.assertTrue(getDriver().findElement(By.id("view-message")).getText().contains(DESCRIPTION));
        Assert.assertEquals(copiedFolderDescription, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testDeleteFolder() {
        getWait2().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@class='jenkins-table__link model-link inside']/button"))).sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Delete Folder"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertFalse(getDriver().findElement(By.id("main-panel")).getText().contains(FOLDER1_NAME), "Deleted folder is displayed");
    }
}

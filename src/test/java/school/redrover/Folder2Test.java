package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class Folder2Test extends BaseTest {

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

        getWait10().until(ExpectedConditions.presenceOfElementLocated(NAME_FIELD));

        getDriver().findElement(NAME_FIELD).sendKeys(name);
        getDriver().findElement(FOLDER_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));

        getDriver().findElement(SAVE_BUTTON).click();

        getWait2().until(ExpectedConditions.textToBe(FOLDER_HEADER,name));
    }

    @Test
    public void testFolderCreation() {
        final String FOLDER_NAME = "My folder";

        createAFolder(FOLDER_NAME);

        getDriver().findElement(DASHBOARD_LINK).click();

        String actualJobsList = getDriver().findElement(FOLDER_IN_LIST).getText();

        getDriver().findElement(FOLDER_IN_LIST).click();

        Assert.assertEquals(getDriver().findElement(JOB_HEADER).getText(),FOLDER_NAME);
        Assert.assertEquals(actualJobsList,FOLDER_NAME);
    }

    @Test
    public void testTwoFoldersCreation() {
        final String FOLDER1_NAME = "My main folder";
        final String FOLDER2_NAME = "My folder";
        List<String> expectedFoldersList = Arrays.asList(FOLDER1_NAME, FOLDER2_NAME);

        createAFolder(FOLDER1_NAME);
        getDriver().findElement(DASHBOARD_LINK).click();

        getWait10().until(ExpectedConditions.presenceOfElementLocated(NEW_ITEM));

        createAFolder(FOLDER2_NAME);
        getDriver().findElement(DASHBOARD_LINK).click();

        List <WebElement> actualFoldersList = getDriver().findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));

        for (int i = 0; i < actualFoldersList.size(); i++) {

            Assert.assertEquals(actualFoldersList.get(actualFoldersList.size() - 1 - i).getText(), expectedFoldersList.get(i));
        }
    }
}

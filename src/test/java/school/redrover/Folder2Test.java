package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder2Test extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME_FIELD = By.id("name");
    private static final By FOLDER_TYPE = By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@name='_.description']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By FOLDER_HEADER = By.tagName("h1");
    private static final By DASHBOARD_LINK = By.xpath("//a[@href='/'][@class='model-link']");
    private static final By FOLDER_IN_LIST = By.xpath("//a[@class='jenkins-table__link model-link inside']/span");
    private static final By JOB_HEADER = By.tagName("h1");

    public void createAFolder(String name, String description) {
        getDriver().findElement(NEW_ITEM).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(NAME_FIELD));
        getDriver().findElement(NAME_FIELD).sendKeys(name);
        getDriver().findElement(FOLDER_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        getDriver().findElement(DESCRIPTION_FIELD).sendKeys(description);
        getDriver().findElement(SAVE_BUTTON).click();
        getWait2().until(ExpectedConditions.textToBe(FOLDER_HEADER,name));
    }

    @Test
    public void testFolderCreation() {
        final String FOLDER_NAME = "My_folder";
        final String DESCRIPTION = "";

        createAFolder(FOLDER_NAME,DESCRIPTION);

        getDriver().findElement(DASHBOARD_LINK).click();

        String actualJobsList = getDriver().findElement(FOLDER_IN_LIST).getText();

        getDriver().findElement(FOLDER_IN_LIST).click();

        Assert.assertTrue(getDriver().getCurrentUrl().contains(FOLDER_NAME));
        Assert.assertEquals(getDriver().findElement(JOB_HEADER).getText(),FOLDER_NAME);
        Assert.assertEquals(actualJobsList,FOLDER_NAME);
    }
}

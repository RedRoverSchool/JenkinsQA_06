package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class User3Test extends BaseTest {

    private static final By MANAGE_JENKINS_BUTTON = By.xpath("//a[@href='/manage']");
    private static final By MANAGE_USERS_BUTTON = By.xpath("//a[@href='securityRealm/']");
    private static final By FIRST_USER_ID_NAME = By.xpath("//td/a[contains(@href, 'user/')][1]");
    private static final By INPUT_DESCRIPTION_FIELD = By.xpath("//textarea[contains(@name, 'description')]");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By DESCRIPTION_FIELD = By.xpath("//div[@id='description']/div[1]");
    private static final By FIRST_JENKINS_USER_ID = By.xpath("//div[@id='description']/following-sibling::*[1]");
    private static final String expectedDescription = "Role: Administrator";
    private static String userId;

    public void addDescriptionToFirstUser() {

        WebElement buttonManageJenkins = getWait5()
                .until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_BUTTON));
        buttonManageJenkins.click();

        WebElement buttonManageUsers = getWait5()
                .until(ExpectedConditions.elementToBeClickable(MANAGE_USERS_BUTTON));
        buttonManageUsers.click();

        WebElement textTestedUserId = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(FIRST_USER_ID_NAME));
        userId = textTestedUserId.getText();

        WebElement buttonSettings = getWait5()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'" + userId + "/configure')]")));
        buttonSettings.click();

        WebElement fieldDescription = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(INPUT_DESCRIPTION_FIELD));
        fieldDescription.clear();
        fieldDescription.sendKeys(expectedDescription);

        WebElement buttonSave = getWait5()
                .until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        buttonSave.click();
    }

    @Test
    public void testInputInfoIntoDescription() {

        addDescriptionToFirstUser();

        WebElement textInfoUserId = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(FIRST_JENKINS_USER_ID));
        String infoUserId = textInfoUserId.getText();

        Assert.assertEquals(infoUserId, "Jenkins User ID: " + userId);

        WebElement textActualDescription = getDriver()
                .findElement(DESCRIPTION_FIELD);
        String actualDescription = textActualDescription.getText();

        Assert.assertEquals(actualDescription, expectedDescription);
    }
}

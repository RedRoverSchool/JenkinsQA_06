package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MulticonfigerationProject4Test extends BaseTest {
    private static final By NEW_ITEM_LINK_XPATH = By.xpath("//a[@href='/view/all/newJob']");
    private static final String MULTI_CONFIGURATION_PROJECT_NAME = "MyProject";
    private static final By ENTER_AN_ITEM_NAME_BAR_XPATH = By.xpath("//input[@name='name']");
    private static final By MULTI_CONFIGURATION_CHOICE_XPATH = By
            .xpath("//span[contains(text(), 'Multi-configuration project')]");
    private static final By OK_BUTTON_XPATH = By.xpath("//button[@id='ok-button']");
    private static final By SAVE_BUTTON_XPATH = By.xpath("//button[@name='Submit']");
    private static final By CONFIGURE_LINK_XPATH = By.xpath("//a[@href='/job/MyProject/configure']");
    private static final By ENABLE_TOGGLE_XPATH = By.xpath("//span[@id='toggle-switch-enable-disable-project']");



    @Test
    public void testDisableMultiConfigurationProject() {
        String expectedResult = "This project is currently disabled";
        WebElement newItemLink = getDriver().findElement(NEW_ITEM_LINK_XPATH);
        newItemLink.click();

        WebElement enterAnItemNameBar = getWait5()
                .until(ExpectedConditions.elementToBeClickable(ENTER_AN_ITEM_NAME_BAR_XPATH));
        enterAnItemNameBar.sendKeys(MULTI_CONFIGURATION_PROJECT_NAME);

        WebElement multiConfigurationChoice = getDriver().findElement(MULTI_CONFIGURATION_CHOICE_XPATH);
        multiConfigurationChoice.click();

        WebElement okButton = getDriver().findElement(OK_BUTTON_XPATH);
        okButton.click();

        WebElement saveButton = getDriver().findElement(SAVE_BUTTON_XPATH);
        saveButton.click();

        WebElement configureLink = getDriver().findElement(CONFIGURE_LINK_XPATH);
        configureLink.click();

        WebElement enableToggle = getWait5().until(ExpectedConditions.elementToBeClickable(ENABLE_TOGGLE_XPATH));
        enableToggle.click();

        saveButton = getDriver().findElement(SAVE_BUTTON_XPATH);
        saveButton.click();

        WebElement projectIsDisabledMessage = getWait2()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//form[@id='enable-project']")));

        Assert.assertEquals(projectIsDisabledMessage.getText().substring(0,34), expectedResult);
    }
}

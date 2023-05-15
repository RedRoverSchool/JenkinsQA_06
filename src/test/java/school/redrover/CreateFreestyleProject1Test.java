package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProject1Test extends BaseTest {
    private static final By NEW_ITEM_LINK_XPATH = By.xpath("//a[@href='/view/all/newJob']");
    private static final String FREESTYLE_PROJECT_NAME = "Freestyle project";
    private static final By ENTER_AN_ITEM_NAME_BAR_XPATH = By.xpath("//input[@name='name']");
    private static final By SAVE_BUTTON_XPATH = By.xpath("//button[@name='Submit']");
    private static final By JENKINS_HOME_LINK_XPATH = By.
            xpath("//div[@id='breadcrumbBar']/ol/li/a");
    private static final By FREE_STYLE_PROJECT_XPATH = By
            .xpath("//span[contains(text(), 'Freestyle project')]");
    private static final By OK_BUTTON_XPATH = By.xpath("//button[@id='ok-button']");

    @Test
    public void createNewFreestyleProjectTest() {

        WebElement newItemLink = getDriver().findElement(NEW_ITEM_LINK_XPATH);
        newItemLink.click();

        WebElement enterAnItemNameBar = getWait5().until(ExpectedConditions.elementToBeClickable(ENTER_AN_ITEM_NAME_BAR_XPATH));
        enterAnItemNameBar.click();
        enterAnItemNameBar.sendKeys(FREESTYLE_PROJECT_NAME);

        WebElement folder = getDriver().findElement(FREE_STYLE_PROJECT_XPATH);
        folder.click();

        WebElement okButton = getDriver().findElement(OK_BUTTON_XPATH);
        okButton.click();

        WebElement saveButton = getDriver().findElement(SAVE_BUTTON_XPATH);
        saveButton.click();

        WebElement jenkinsHomeLink = getDriver().findElement(JENKINS_HOME_LINK_XPATH);
        jenkinsHomeLink.click();

        WebElement newProjectOnTheDashboard = getWait5()
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//tbody//td[3]/a/span")));

        Assert.assertEquals(newProjectOnTheDashboard.getText(), FREESTYLE_PROJECT_NAME);
    }
}

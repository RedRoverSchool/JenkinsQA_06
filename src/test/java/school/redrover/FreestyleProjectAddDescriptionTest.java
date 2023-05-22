package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class FreestyleProjectAddDescriptionTest extends BaseTest {
    private static final By NEW_ITEM_BUTTON = By.xpath("//a[contains(@href, 'newJob')]");
    private static final By ITEM_NAME_FIELD = By.xpath("//input[@id='name']");
    private static final By NEW_ITEM_OK_BUTTON = By.xpath("//button[@id='ok-button']");
    private static final By DASHBOARD_BUTTON = By.xpath("//a[contains(text(), 'Dashboard')]");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@name='description']");
    private static final By CONFIGURE_SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By PROJECT_DESCRIPTION = By.xpath("//div[@id='description']/div[not(contains(@class, 'jenkins'))]");
    private static final By CONFIGURE_BUTTON = By.xpath("//a[contains(@href, 'configure')]");


    private void createFreestyleProject(String nameOfProject) {
        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class, 'FreeStyleProject')]/label"))).click();
        getDriver().findElement(ITEM_NAME_FIELD).sendKeys(nameOfProject);
        getDriver().findElement(NEW_ITEM_OK_BUTTON).click();
        getDriver().findElement(CONFIGURE_SAVE_BUTTON).click();
        getDriver().findElement(DASHBOARD_BUTTON).click();
    }

    @Test
    public void testAddDescriptionToTheProjectViaConfigure() {
        final String nameOfProject = "Leprechaun";
        createFreestyleProject(nameOfProject);

        WebElement createdProjectNameInJobs = getDriver().findElement(By.xpath("//span[contains(text(),'" + nameOfProject + "')]/ancestor::a"));

        createdProjectNameInJobs.click();
        getDriver().findElement(CONFIGURE_BUTTON).click();

        String descriptionText = "My project description!";
        getDriver().findElement(DESCRIPTION_FIELD).sendKeys(descriptionText);
        getDriver().findElement(CONFIGURE_SAVE_BUTTON).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.
                visibilityOfElementLocated((PROJECT_DESCRIPTION))).getText(), descriptionText);
    }
}

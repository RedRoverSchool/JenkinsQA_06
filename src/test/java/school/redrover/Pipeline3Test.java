package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline3Test extends BaseTest {

    private final String itemName = "First Project";

    private static final By NEW_ITEM_MENU = By.xpath("//a[@href='/view/all/newJob']");
    private static final By PROJECT_NAME_FIELD = By.xpath("//input[@name='name']");
    private static final By PIPELINE_SECTION = By.xpath("//span[@class = 'label'] [text() = 'Pipeline']");
    private static final By OK_BUTTON = By.xpath("//button[@id='ok-button']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By PIPELINE_HEADING = By.xpath("//div[@id='main-panel']/h1");
    private static final By BREADSCRUMB_DASHBOARD = By.xpath("//a[text()='Dashboard']");
    private static final By PROJECT_NAME_IN_PROJECT_STATUS_TABLE =
            By.xpath("//a[@class='jenkins-table__link model-link inside']//span");

    @Test
    public void testCreatePipelineViaNewItem() {

        getDriver().findElement(NEW_ITEM_MENU).click();
        getDriver().findElement(PROJECT_NAME_FIELD).sendKeys(itemName);
        getDriver().findElement(PIPELINE_SECTION).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SUBMIT_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(PIPELINE_HEADING).getText(), "Pipeline " + itemName);

        getDriver().findElement(BREADSCRUMB_DASHBOARD).click();

        Assert.assertEquals(getDriver().findElement(PROJECT_NAME_IN_PROJECT_STATUS_TABLE).getText(), itemName);
    }

    @Test(dependsOnMethods = "testCreatePipelineViaNewItem")
    public void testCreatePipelineWithTheSameName() {

        final String expectedErrorMessage = "A job already exists with the name ‘" + itemName + "’";

        getDriver().findElement(BREADSCRUMB_DASHBOARD).click();
        getDriver().findElement(NEW_ITEM_MENU).click();
        getDriver().findElement(PROJECT_NAME_FIELD).sendKeys(itemName);

        WebElement errorMessageAJobAlreadyExists = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));
        errorMessageAJobAlreadyExists.isDisplayed();

        getDriver().findElement(PIPELINE_SECTION).click();

        getDriver().findElement(OK_BUTTON).click();

        Assert.assertEquals(getDriver().findElement
                (By.xpath("//div[@id='main-panel']/p")).getText(), expectedErrorMessage);
    }
}


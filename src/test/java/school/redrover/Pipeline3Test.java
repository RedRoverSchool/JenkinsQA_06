package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;

public class Pipeline3Test extends BaseTest {

    private final String itemName = "First Project";

    private static final By NEW_ITEM_MENU = By.xpath("//a[@href='/view/all/newJob']");
    private static final By PROJECT_NAME_FIELD = By.xpath("//input[@name='name']");
    private static final By PIPELINE_SECTION = By.xpath("//span[@class = 'label'] [text() = 'Pipeline']");
    private static final By OK_BUTTON = By.xpath("//button[@id='ok-button']");
    private static final By BREADSCRUMB_DASHBOARD = By.xpath("//a[text()='Dashboard']");

    @Ignore
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


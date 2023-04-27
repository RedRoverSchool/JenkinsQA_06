package school.redrover;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class MultibranchPipelineTest extends BaseTest {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement newItemButton;
    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement stringSearchItemName;
    @FindBy(xpath = "//span[text() = 'Multibranch Pipeline']")
    private WebElement multibranchPipelineButton;
    @FindBy(xpath = "//button[@id = 'ok-button']")
    private WebElement saveButton;
    @FindBy(xpath = "//input[@name = '_.displayNameOrNull']")
    private WebElement displayNameField;
    @FindBy(xpath = "//textarea[@name = '_.description']")
    private WebElement descriptionField;
    @FindBy(xpath = "//div[@id='main-panel']//h1")
    private WebElement multibranchPipelineDisplayName;

    public WebDriverWait webDriverWait10;

    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    public final void verifyElementVisible(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementClickable(WebElement element) {
        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickNewItemButton() {
        verifyElementVisible(newItemButton);
        verifyElementClickable(newItemButton).click();
    }

    public void enterAnItemName() {
        stringSearchItemName.sendKeys("TestName");
    }

    public void clickMultibranchPipeline() {
        verifyElementVisible(multibranchPipelineButton);
        verifyElementClickable(multibranchPipelineButton).click();
    }

    public void enterTinaInDisplayNameField() {
        verifyElementClickable(displayNameField).click();
        displayNameField.sendKeys("Tina", Keys.ENTER);
    }

    public void enterValueInDescriptionField() {
        verifyElementClickable(descriptionField).click();
        descriptionField.sendKeys("Test");
    }
    public void clickSaveButton() {
        verifyElementVisible(saveButton);
        verifyElementClickable(saveButton).click();
    }

    @Test
    public void createMultibranchPipelineTest() {
        final String expectedRes = "Tina";
        PageFactory.initElements(getDriver(), this);
        clickNewItemButton();
        enterAnItemName();
        clickMultibranchPipeline();
        clickSaveButton();
        getWait10();
        enterValueInDescriptionField();
        enterTinaInDisplayNameField();

        Assert.assertEquals(multibranchPipelineDisplayName.getText(), expectedRes );
    }
}

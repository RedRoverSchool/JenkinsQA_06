package school.redrover;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class  CreateMultibranchPipeline1Test extends BaseTest {

    private WebDriverWait webDriverWait10;

    public WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    public void verifyElementToBeVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementToBeClickable(WebElement element) {

        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getText(WebElement element) {

        return element.getText();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void click(WebElement element) {
        verifyElementToBeVisible(element);
        verifyElementToBeClickable(element).click();
    }

    public void input(String text, WebElement element) {
        element.sendKeys(text);
    }

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


    public void clickNewItemButton() {
        verifyElementToBeVisible(newItemButton);
        verifyElementToBeClickable(newItemButton).click();
    }

    public void inputAnItemName(String text) {
        verifyElementToBeVisible(stringSearchItemName);
        input(text, stringSearchItemName);

    }

    public void clickMultibranchPipeline() {
        verifyElementToBeVisible(multibranchPipelineButton);
        verifyElementToBeClickable(multibranchPipelineButton).click();

    }

    public void clickOkButton() {
        verifyElementToBeVisible(saveButton);
        verifyElementToBeClickable(saveButton).click();
    }

    public void enterValueInDescriptionField() {
        verifyElementToBeClickable(descriptionField).click();
        descriptionField.sendKeys("Test");
    }

    public void enterValueInDisplayNameField() {
        verifyElementToBeClickable(displayNameField).click();
        displayNameField.sendKeys("Test", Keys.ENTER);
    }

    public String getNameMultibranchPipelineDisplayNameText() {
        return multibranchPipelineDisplayName.getText();
    }

    @Test
    public void createMultibranchPipelineTest() {
        final String itemName = "TestName";
        final String expectedRes = "Test";
        PageFactory.initElements(getDriver(), this);

        clickNewItemButton();
        inputAnItemName(itemName);
        clickMultibranchPipeline();
        clickOkButton();
        enterValueInDescriptionField();
        enterValueInDisplayNameField();

        String actualRes = getNameMultibranchPipelineDisplayNameText();

        Assert.assertEquals(actualRes, expectedRes);
    }
}

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
    @FindBy(xpath = "//span[text()='Organization Folder']")
    private WebElement organizationFolderLabel;
    @FindBy(xpath = "//label[text()='Abort builds']")
    private WebElement abortBuildsLabel;
    @FindBy(xpath = "//div[@id='orphaned-item-strategy']")
    private WebElement orphanedItemStrategy;
    @FindBy(xpath = "//div[normalize-space()='Child Scan Triggers']")
    private  WebElement childScanTriggers;
    @FindBy(xpath = "//div[contains(@class,'jenkins-form-item jenkins-form-item--tight')]"
            + "//div//div[contains(@class,'optionalBlock-container "
            + "jenkins-form-item jenkins-form-item--tight')]"
            + "//select[contains(@name,'_.interval')]")
    private WebElement intervalDropDownMenu;
    @FindBy(xpath = "//div[contains(@class,'jenkins-form-item jenkins-form-item--tight')]"
            + "//div//div[contains(@class,'optionalBlock-container "
            + "jenkins-form-item jenkins-form-item--tight')]//option"
            + "[contains(@value,'7')]")
    private WebElement interval7d;
    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement buttonSubmitSave;
    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement actualResult;
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

    public void enterAnItemNameTest() {
        stringSearchItemName.sendKeys("Test");
    }

    public void clickMultibranchPipeline() {
        verifyElementVisible(multibranchPipelineButton);
        verifyElementClickable(multibranchPipelineButton).click();
    }

    public void clickOrganizationFolderLabel() {
        verifyElementVisible(organizationFolderLabel);
        verifyElementClickable(organizationFolderLabel).click();
    }

    public void clickAbortBuildsLabel() {
        verifyElementVisible(abortBuildsLabel);
        verifyElementClickable(abortBuildsLabel).click();
    }

    public void clickIntervalDropDownMenu() {
        verifyElementVisible(intervalDropDownMenu);
        verifyElementClickable(intervalDropDownMenu).click();
    }

    public void clickInterval7d() {
        verifyElementVisible(interval7d);
        verifyElementClickable(interval7d).click();
    }

    public void clickSave() {
        verifyElementVisible(buttonSubmitSave);
        verifyElementClickable(buttonSubmitSave).click();
    }

    public void enterTinaInDisplayNameField() {
        verifyElementClickable(displayNameField).click();
        displayNameField.sendKeys("Tina", Keys.ENTER);
    }

    public void enterNameInDisplayNameField() {
        verifyElementClickable(displayNameField).click();
        displayNameField.sendKeys("Test");
    }
    public void enterValueInDescriptionField() {
        verifyElementClickable(descriptionField).click();
        descriptionField.sendKeys("Test");
    }
    public void clickOkButton() {
        verifyElementVisible(saveButton);
        verifyElementClickable(saveButton).click();
    }

    public void scrollByElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    @Test
    public void createMultibranchPipelineTest() {
        final String expectedRes = "Tina";
        PageFactory.initElements(getDriver(), this);
        clickNewItemButton();
        enterAnItemName();
        clickMultibranchPipeline();
        clickOkButton();
        enterValueInDescriptionField();
        enterTinaInDisplayNameField();

        Assert.assertEquals(multibranchPipelineDisplayName.getText(), expectedRes );
    }

    @Test
    public void testCreateOrganizationFolder() {
        final String expectedResult = "Test";
        PageFactory.initElements(getDriver(), this);
        clickNewItemButton();
        enterAnItemNameTest();
        clickOrganizationFolderLabel();
        clickOkButton();
        enterNameInDisplayNameField();
        enterValueInDescriptionField();
        scrollByElement(orphanedItemStrategy);
        clickAbortBuildsLabel();
        scrollByElement(childScanTriggers);
        clickIntervalDropDownMenu();
        clickInterval7d();
        clickSave();

        Assert.assertEquals(actualResult.getText(), expectedResult);
    }
}

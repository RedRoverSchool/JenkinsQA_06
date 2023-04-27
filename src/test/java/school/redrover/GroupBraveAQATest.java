package school.redrover;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupBraveAQATest extends BaseTest {

    @FindBy(css = "a[href='newJob']")
    private WebElement welcomePage_createAJobLink;
    @FindBy(css = ".add-item-name .h3")
    private WebElement newItemPage_header;
    @FindBy(xpath = "//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")
    private WebElement newItemFolderLink;
    @FindBy(xpath = "//div[@class='input-validation-message']")
    private WebElement newItemInputValidationMessage;

    private WebDriverWait webDriverWait10;

    public WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    public void verifyElementVisible(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementIsClickable(WebElement element) {
        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getText(WebElement element){
       if(!element.getText().isEmpty()){
           verifyElementVisible(element);
       }
       return element.getText();
    }

    public String getNewItemPage_header(){
        return getText(newItemPage_header);
    }
    public void clickWelcomePage_createAJobLink(){
        verifyElementIsClickable(welcomePage_createAJobLink).click();
    }
    @Test
    public void test_NewItemPageOpensByClickingCreateAJobLink_Successfully(){
        PageFactory.initElements(getDriver(), this);
        clickWelcomePage_createAJobLink();
        String actualHeader = getNewItemPage_header();

        Assert.assertEquals(actualHeader, "Enter an item name");
    }

    @Test
    public void testCreateFolderWithEmptyName() {
        PageFactory.initElements(getDriver(), this);
        clickWelcomePage_createAJobLink();
        verifyElementVisible(newItemFolderLink);
        newItemFolderLink.click();
        verifyElementVisible(newItemInputValidationMessage);

        Assert.assertEquals(newItemInputValidationMessage.getText(),
                "» This field cannot be empty, please enter a valid name",
                "The validation message is incorrect");
    }
}

package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class NewNodePage extends BaseMainHeaderPage<NewNodePage> {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nodeNameField;

    @FindBy(xpath = "//label")
    private WebElement permanentAgentButton;

    @FindBy(xpath = "//label[@for='copy']")
    private WebElement copyExistingNodeButton;

    @FindBy(xpath = "//input[@name='from']")
    private WebElement existingNodeField;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement createButton;

    public NewNodePage(WebDriver driver){
        super(driver);
    }

    public NewNodePage inputNodeNameField(String text) {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(nodeNameField)).sendKeys(text);
        return this;
    }

    public NewNodePage clickPermanentAgentRadioButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(permanentAgentButton)).click();
        return this;
    }

    public NewNodePage clickCopyExistingNode(){
        getWait2().until(ExpectedConditions.elementToBeClickable(copyExistingNodeButton)).click();
        return this;
    }

    public NewNodePage inputExistingNode(String existingNodeName){
        getWait2().until(ExpectedConditions
                .visibilityOf(existingNodeField)).sendKeys(existingNodeName);
        return this;
    }

    public CreateNodePage clickCreateButton() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(createButton)).click();
        return new CreateNodePage(getDriver());
    }

    public ErrorNodePage clickCreateButtonAndGoError() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(createButton)).click();
        return new ErrorNodePage(getDriver());
    }
}

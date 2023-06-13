package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class NewNodePage extends BaseMainHeaderPage<NewNodePage> {

    public NewNodePage(WebDriver driver){
        super(driver);
    }

    public NewNodePage inputNodeNameField(String text) {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys(text);
        return this;
    }

    public NewNodePage clickPermanentAgentRadioButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//label"))).click();
        return this;
    }

    public NewNodePage clickCopyExistingNode(){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='copy']"))).click();
        return this;
    }

    public NewNodePage inputExistingNode(String existingNodeName){
        getWait2().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[@name='from']"))).sendKeys(existingNodeName);
        return this;
    }

    public CreateNodePage clickCreateButton() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        return new CreateNodePage(getDriver());
    }

    public ErrorNodePage clickCreateButtonAndGoError() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        return new ErrorNodePage(getDriver());
    }
}

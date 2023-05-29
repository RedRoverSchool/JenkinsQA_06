package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewNodePage extends MainPage {

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

    public CreateNodePage clickCreateButton() {
        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        return new CreateNodePage(getDriver());
    }
}

package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateNodePage extends MainPage {

    public CreateNodePage(WebDriver driver){
        super(driver);
    }

    public CreateNodePage clearNameField() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']"))).clear();
        return this;
    }

    public ErrorNodePage clickSaveButtonWhenNameFieldEmpty() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        return new ErrorNodePage(getDriver());
    }
}
